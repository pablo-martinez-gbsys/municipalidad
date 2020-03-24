package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * Implementación de Tabla con carga perezosa.
 *
 * @param <E> Elemento genérico que extiende de EntidadBase
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 16/05/2018 16:23
 */
public class TablaControlLazy<E extends EntidadBase> extends LazyDataModel<E> implements TablaControl<E> {

    private static final long serialVersionUID = 1L;
    //Constantes para ordenamiento
    private static final String COMA = ", ";
    private static final String ASC = " ASC";
    private static final String DESC = " DESC";
    //Servicios para obtener la cantidad y los datos
    private final CantidadDatos cantidadDatos;
    private final ConsultaDatos<E> consultaDatos;
    //Manejo Interno
    private final EntidadBaseSeleccion<E> seleccion;
    private final List<TablaListener<E>> listeners;
    //Cantidad y Datos
    private Long cantidad;
    private List<E> datos;

    /**
     * Constructor para tabla vacía, con implementación tonta para no cargar
     * nada.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    public TablaControlLazy() {
        this.cantidadDatos = (filtros) -> 0L;
        this.consultaDatos = (inicio, tamanoPagina, ordenamiento, filtros) -> Collections.emptyList();
        this.seleccion = new EntidadBaseSeleccion<>();
        this.listeners = Collections.emptyList();
    }

    /**
     * Constructor con implementación de métodos para obtener los registros y la
     * cantidad total de registros de la BD.
     *
     * @param cantidadDatos Implementación para obtener la cantidad total de
     * registros de la BD
     * @param consultaDatos Implementación para obtener los registros de la BD
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    public TablaControlLazy(@NonNull CantidadDatos cantidadDatos, @NonNull ConsultaDatos<E> consultaDatos) {
        this.cantidadDatos = cantidadDatos;
        this.consultaDatos = consultaDatos;
        this.seleccion = new EntidadBaseSeleccion<>();
        this.listeners = new ArrayList<>();
        this.listeners.add(seleccion);
    }

    /**
     * Agrega un listener a TablaControlLazy.
     *
     * @param listener Listener de TablaControlLazy
     * @return Objeto TablaControlLazy
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    public TablaControlLazy agregarListener(@NonNull TablaListener<E> listener) {
        this.listeners.add(listener);
        return this;
    }

    /**
     * Se encarga de llamar a los servicios requeridos para cargar la tabla de
     * forma perezosa.
     *
     * @param inicio Registro inicial de la consulta
     * @param tamanoPagina Cantidad de registros de la consulta
     * @param ordenamiento Ordenamiento de la tabla a ser aplicado en la
     * consulta
     * @param filtros Mapa con los filtros de la tabla a ser aplicados en la
     * consulta
     * @return Lista de elementos resultantes de la consulta
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    private List<E> consultarPerezoso(int inicio, int tamanoPagina, String ordenamiento, Map<String, Object> filtros) {
        try {
            cantidad = cantidadDatos.cantidad(filtros);
            if (cantidad > 0L) {
                //Se ajusta el inicio cuando la pagina es mas pequeña
                inicio = cantidad >= inicio ? inicio : 0;
                datos = consultaDatos.consultar(inicio, tamanoPagina, ordenamiento, filtros);
            } else {
                datos = Collections.emptyList();
            }
        } catch (Throwable ex) {
            cantidad = 0L;
            datos = Collections.emptyList();
            throw ex;
        }

        listeners.forEach((listener) -> listener.onLoad(cantidad, datos));

        sobreEscribir();

        return datos;
    }

    /**
     * Sobre escribe los valores de LazyDataModel.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    private void sobreEscribir() {
        setRowCount(cantidad.intValue());
        setWrappedData(datos);
    }

    /**
     * Concatena la sentencia de ordenamiento de un campo.
     *
     * @param sortField Campo a ordenar
     * @param sortOrder Dirección del ordenamiento
     * @return Concatenación del campo de ordenamiento
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    private String obtenerOrdenamiento(String sortField, SortOrder sortOrder) {
        StringBuilder ordenamiento = new StringBuilder();
        obtenerOrdenamientoCampo(ordenamiento, sortField, sortOrder, false);
        return ordenamiento.toString();
    }

    /**
     * Concatena las sentencias de ordenamiento de un List&lt;SortMeta&gt;.
     *
     * @param multiSortMeta Lista de elementos para el ordenamiento de la
     * consulta
     * @return Concatenación de todos los elementos de List&lt;SortMeta&gt;
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    private String obtenerOrdenamiento(List<SortMeta> multiSortMeta) {
        if (multiSortMeta == null) {
            return "";
        }
        StringBuilder ordenamiento = new StringBuilder();
        boolean coma = false;
        for (SortMeta sortMeta : multiSortMeta) {
            String sortField = sortMeta.getSortField();
            SortOrder sortOrder = sortMeta.getSortOrder();
            obtenerOrdenamientoCampo(ordenamiento, sortField, sortOrder, coma);
            coma = true;
        }
        return ordenamiento.toString();
    }

    /**
     * Concatena la sentencia de ordenamiento de un campo.
     *
     * @param ordenamiento Cadena donde se concatena el ordenamiento
     * @param sortField Campo de la consulta
     * @param sortOrder Dirección del ordenamiento
     * @param coma Indicador de si se debe poner coma al inicio
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    private void obtenerOrdenamientoCampo(StringBuilder ordenamiento, String sortField, SortOrder sortOrder, boolean coma) {
        if (sortField != null
                && sortOrder != null
                && !SortOrder.UNSORTED.equals(sortOrder)) {
            if (coma) {
                ordenamiento.append(COMA);
            }
            ordenamiento.append(sortField);
            ordenamiento.append(SortOrder.ASCENDING.equals(sortOrder) ? ASC : DESC);
        }
    }

    /**
     * Obtiene una copia de los datos de la tabla que están cargados en el
     * momento.
     *
     * @return copia de los datos de la tabla
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/09/2018 13:23
     */
    @Override
    public List<E> getDatos() {
        return Collections.unmodifiableList(datos);
    }

    /**
     * Método de Primefaces que utiliza el DataTable para realizar la carga
     * perezosa.
     *
     * @param inicio Registro inicial de la consulta
     * @param tamanoPagina Cantidad de registros de la consulta
     * @param sortField Nombre del campo a ordenar
     * @param sortOrder Dirección en la que se ordena el campo a ordenar
     * @param filtros Mapa con los filtros de la tabla a ser aplicados en la
     * consulta
     * @return Lista de elementos resultantes de la consulta
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    @Override
    public List<E> load(int inicio, int tamanoPagina, String sortField, SortOrder sortOrder, Map<String, Object> filtros) {
        String ordenamiento = obtenerOrdenamiento(sortField, sortOrder);
        return consultarPerezoso(inicio, tamanoPagina, ordenamiento, filtros);
    }

    /**
     * Método de Primefaces que utiliza el DataTable para realizar la carga
     * perezosa.
     *
     * @param inicio Registro inicial de la consulta
     * @param tamanoPagina Cantidad de registros de la consulta
     * @param multiSortMeta Lista de elementos para el ordenamiento de la
     * consulta
     * @param filtros Mapa con los filtros de la tabla a ser aplicados en la
     * consulta
     * @return Lista de elementos resultantes de la consulta
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    @Override
    public List<E> load(int inicio, int tamanoPagina, List<SortMeta> multiSortMeta, Map<String, Object> filtros) {
        String ordenamiento = obtenerOrdenamiento(multiSortMeta);
        return consultarPerezoso(inicio, tamanoPagina, ordenamiento, filtros);
    }

    /**
     * Obtiene el valor llave de un Elemento de la tabla.
     *
     * @param object Elemento de la tabla
     * @return Valor llave del elemento de la tabla
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    @Override
    public Object getRowKey(E object) {
        return object.hashCode();
    }

    /**
     * Obtiene un elemento de la tabla a partir de su llave.
     *
     * @param rowKey Llave del elemento de la tabla
     * @return Elemento de la tabla correspondiente a la llave
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 18/05/2018 16:23
     */
    @Override
    public E getRowData(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        Integer hashCode = Integer.valueOf(rowKey);
        return datos
                .stream()
                .filter((elemento) -> elemento.hashCode() == hashCode)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<E> obtenerSeleccionados() {
        return seleccion.obtenerSeleccionados();
    }

    @Override
    public void limpiarSeleccionados() {
        seleccion.limpiarSeleccionados();
    }

    @Override
    public void seleccionarUno(E elemento) {
        seleccion.seleccionarUno(elemento);
    }

    @Override
    public void seleccionarVarios(E elemento) {
        seleccion.seleccionarVarios(elemento);
    }

}

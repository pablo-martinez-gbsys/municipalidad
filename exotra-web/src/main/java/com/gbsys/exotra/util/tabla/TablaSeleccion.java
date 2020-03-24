package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

/**
 * Clase encargada de manejar la selección de elementos de la tabla.
 *
 * @param <E> Tipo de elemento de la tabla
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 03/07/2017 14:48
 */
public abstract class TablaSeleccion<E extends EntidadBase> implements TablaSeleccionable<E> {

    private static final long serialVersionUID = 1L;

    private final List<E> seleccionados;

    /**
     * Construye una lista de selección vacía.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public TablaSeleccion() {
        this.seleccionados = new ArrayList<>();
    }

    /**
     * Construye una lista de selección con los valores enviados por parámetro.
     *
     * @param seleccionados Lista de elementos seleccionados
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public TablaSeleccion(@NonNull List<E> seleccionados) {
        this.seleccionados = seleccionados;
    }

    /**
     * Devuelve un listado de registros, que tengan la variable seleccionado en
     * true.
     *
     * @return Listado de seleccionados en la página desplegada
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    @Override
    public List<E> obtenerSeleccionados() {
        return this.seleccionados;
    }

    /**
     * Desmarca todos los elementos que están seleccionados.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    @Override
    public void limpiarSeleccionados() {
        seleccionados.forEach((elemento) -> setSeleccionado(elemento, false));
        this.seleccionados.clear();
    }

    /**
     * Marca solo un checkbox de la tabla.
     *
     * @param elemento el objeto que ha sido seleccionado en la tabla
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    @Override
    public void seleccionarUno(E elemento) {
        if (isSeleccionado(elemento)) {
            limpiarSeleccionados();
            agregarSeleccionado(elemento);
        } else {
            limpiarSeleccionados();
        }
    }

    /**
     * Marca varios checkbox de la tabla.
     *
     * @param elemento el objeto que ha sido seleccionado en la tabla
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    @Override
    public void seleccionarVarios(E elemento) {
        if (isSeleccionado(elemento)) {
            agregarSeleccionado(elemento);
        } else {
            removerSeleccionado(elemento);
        }
    }

    /**
     * Agrega un elemento a la lista de seleccionados.
     *
     * @param elemento Elemento a seleccionar
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public void agregarSeleccionado(E elemento) {
        if (!contieneSeleccionado(elemento)) {
            setSeleccionado(elemento, true);
            this.seleccionados.add(elemento);
        }
    }

    /**
     * Elimina un elemento de la lista de seleccionados.
     *
     * @param elemento Elemento a remover de la lista de seleccionados
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public void removerSeleccionado(E elemento) {
        setSeleccionado(elemento, false);
        this.seleccionados.remove(elemento);
    }

    /**
     * Determina si un elemento es parte de la lista de seleccionados.
     *
     * @param elemento Elemento a validar si está en la lista de seleccionados
     * @return true si es parte de la lista, false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public boolean contieneSeleccionado(E elemento) {
        return this.seleccionados.contains(elemento);
    }

    /**
     * Determina si un elemento es seleccionado.
     *
     * @param elemento Elemento a validar si es seleccionado
     * @return true si es seleccionado o false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public abstract boolean isSeleccionado(E elemento);

    /**
     * Establece el estado de un elemento.
     *
     * @param elemento Elemento a trabajar
     * @param seleccion Estado a establecer
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 14:48
     */
    public abstract void setSeleccionado(E elemento, boolean seleccion);

}

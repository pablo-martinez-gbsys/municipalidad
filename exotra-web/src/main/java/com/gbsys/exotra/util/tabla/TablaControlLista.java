package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * Implementación de Tabla con carga desde Lista.
 *
 * @param <E> Elemento genérico que extiende de EntidadBase
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 16/05/2018 16:23
 */
public class TablaControlLista<E extends EntidadBase> implements TablaControl<E> {

    private static final long serialVersionUID = 1L;

    /**
     * Obtiene los datos de la tabla.
     *
     * @return datos de la tabla
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/09/2018 13:23
     */
    @Getter
    private final List<E> datos;
    /**
     * Elementos seleccionados.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/09/2018 13:23
     */
    private final EntidadBaseSeleccion<E> seleccion;

    /**
     * Constructor con implementación de lista vacía.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 16/05/2018 16:23
     */
    public TablaControlLista() {
        this.datos = Collections.<E>emptyList();
        this.seleccion = new EntidadBaseSeleccion<>();
    }

    /**
     * Constructor con implementación de lista por referencia.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 16/05/2018 16:23
     *
     * @param datos Lista de referencia
     */
    public TablaControlLista(List<E> datos) {
        this.datos = datos;
        this.seleccion = new EntidadBaseSeleccion<>();
    }

    /**
     * Constructor con implementación de lista por referencia y lista de
     * elementos seleccionados.
     *
     * @param datos Lista de referencia
     * @param seleccionados Lista de seleccionados
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 16/05/2018 16:23
     */
    public TablaControlLista(List<E> datos, List<E> seleccionados) {
        this.datos = datos;
        this.seleccion = new EntidadBaseSeleccion<>(seleccionados);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> obtenerSeleccionados() {
        return seleccion.obtenerSeleccionados();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void limpiarSeleccionados() {
        seleccion.limpiarSeleccionados();
    }

    /**
     * {@inheritDoc}
     */
    public void seleccionarTodos() {
        datos.forEach(seleccion::agregarSeleccionado);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void seleccionarUno(E elemento) {
        seleccion.seleccionarUno(elemento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void seleccionarVarios(E elemento) {
        seleccion.seleccionarVarios(elemento);
    }

}

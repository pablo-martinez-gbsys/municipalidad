package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.util.List;

/**
 * Clase encargada de manejar la selección de elementos de tipo objeto base de
 * la tabla.
 *
 * @param <E> Tipo de elemento de la tabla
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 03/07/2017 15:13
 */
public class EntidadBaseSeleccion<E extends EntidadBase> extends TablaSeleccion<E> implements TablaListener<E> {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una lista de selección vacía.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 15:13
     */
    public EntidadBaseSeleccion() {
        super();
    }

    /**
     * Construye una lista de selección con los valores enviados por parámetro.
     *
     * @param seleccionados Lista de elementos seleccionados
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 15:13
     */
    public EntidadBaseSeleccion(List<E> seleccionados) {
        super(seleccionados);
    }

    /**
     * Determina si el objeto base es seleccionado.
     *
     * @param elemento Objeto base a validar si es seleccionado
     * @return true si es seleccionado o false en caso contrario
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 15:13
     */
    @Override
    public boolean isSeleccionado(E elemento) {
        return elemento.isSeleccionado();
    }

    /**
     * Establece el estado de seleccionado de objeto base.
     *
     * @param elemento Elemento a trabajar
     * @param seleccion Estado a establecer
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 15:13
     */
    @Override
    public void setSeleccionado(E elemento, boolean seleccion) {
        elemento.setSeleccionado(seleccion);
    }

    /**
     * Evento cuando se cargan los elementos de la tabla.
     *
     * @param cantidad Cantidad de elementos de la tabla
     * @param elementos Lista de elementos cargados.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 15:13
     */
    @Override
    public void onLoad(long cantidad, List<E> elementos) {
        if (elementos != null) {
            for (E elemento : elementos) {
                if (contieneSeleccionado(elemento)) {
                    removerSeleccionado(elemento);
                    agregarSeleccionado(elemento);
                    //setSeleccionado(elemento, true);
                }
            }
        }
    }

}

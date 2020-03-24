package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.io.Serializable;
import java.util.List;

/**
 * Interfaz encargada para notificar las acciones realizadas por la tabla.
 *
 * @param <E> Elemento que extiende de objeto base
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 03/07/2017 15:41
 */
@FunctionalInterface
public interface TablaListener<E extends EntidadBase> extends Serializable {

    /**
     * Evento cuando se cargan los elementos de la tabla.
     *
     * @param cantidad Cantidad de elementos de la tabla
     * @param elementos Lista de elementos cargados.
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 03/07/2017 15:41
     */
    void onLoad(long cantidad, List<E> elementos);
}

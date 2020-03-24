package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.io.Serializable;
import java.util.List;

/**
 * Interfaz que indica los métodos comunes en todas las implementaciones de
 * Tabla.
 *
 * @param <E> Elemento genérico que extiende de EntidadBase
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 16/05/2018 16:23
 */
public interface TablaControl<E extends EntidadBase> extends TablaSeleccionable<E>, Serializable {

    /**
     * Obtiene los datos de la tabla.
     *
     * @return datos de la tabla
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/09/2018 13:23
     */
    List<E> getDatos();
}

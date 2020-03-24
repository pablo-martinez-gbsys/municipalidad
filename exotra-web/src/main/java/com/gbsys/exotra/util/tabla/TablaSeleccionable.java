package com.gbsys.exotra.util.tabla;

import com.gbsys.exotra.entidad.EntidadBase;
import java.io.Serializable;
import java.util.List;

/**
 * Interfaz que indica los métodos comunes en todas las implementaciones de
 * Tabla.
 *
 * @param <E> Elemento genérico que extiende de EntidadBase
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 16/05/2018 16:23
 */
public interface TablaSeleccionable<E extends EntidadBase> extends Serializable {

    /**
     * Devuelve un listado de registros, que tengan el atributo seleccionado en
     * true.
     *
     * @return Listado de seleccionados en la página desplegada
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/05/2017 08:48
     */
    public List<E> obtenerSeleccionados();

    /**
     * Desmarca todos los elementos que están seleccionados.
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/05/2017 08:48
     */
    public void limpiarSeleccionados();

    /**
     * Marca solo un checkbox de la tabla.
     *
     * @param elemento el objeto que ha sido seleccionado en la tabla
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/05/2017 08:48
     */
    public void seleccionarUno(E elemento);

    /**
     * Marca varios checkbox de la tabla.
     *
     * @param elemento el objeto que ha sido seleccionado en la tabla
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 21/05/2017 08:48
     */
    public void seleccionarVarios(E elemento);
}

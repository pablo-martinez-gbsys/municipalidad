package com.gbsys.exotra.util.tabla;

import java.io.Serializable;
import java.util.Map;

/**
 * Interfaz que define como debe ser el método que obtiene la cantidad de datos
 * de una TablaControlLazy.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 16/05/2018 14:30
 */
@FunctionalInterface
public interface CantidadDatos extends Serializable {

    /**
     * Obtiene la cantidad de registros para una TablaControlLazy.
     *
     * @param filtros Filtro a ser aplicados a la consulta a la BD
     * @return Cantidad total de elementos de la consulta
     */
    Long cantidad(Map<String, Object> filtros);

}

package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Interfaz encargada de definir las condiciones de las consultas.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public interface Condicion {
    
    /**
     * Operador AND.
     */
    String AND = " AND ";
    /**
     * Operador OR.
     */
    String OR = " OR ";
    /**
     * Prefijo de los parámetros, en JPA corresponde a dos puntos (:).
     */
    String PREFIJO_PARAMETRO = ":";

    /**
     * Aplica la condición a la consulta y los parámetros.
     *
     * @param consulta Consulta de JPA
     * @param parametros Parámetros de la consulta
     * @param columna Columna de la consulta
     * @param campo Nombre del campo de la consulta
     * @param valor Valor del campo de la consulta
     */
    void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor);
}

package com.gbsys.exotra.util.filtro;

import java.util.Map;
import lombok.Value;

/**
 * Modelo encargado de representar la consulta y los valores de los parámetros.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 29/11/2017 16:54
 */
@Value
public class ConsultaParametros {

    /**
     * Consulta generada por el filtro con sus respectivos parámetros.
     */
    private final String consulta;
    
    /**
     * Parámetros del filtro con sus respectivos valores.
     */
    private final Map<String, Object> parametros;
}

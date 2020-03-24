package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición Constante, solo se agrega en los parámetros y no se concatena en la
 * consulta.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionConstante implements Condicion {

    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        parametros.put(campo, valor);
    }
}

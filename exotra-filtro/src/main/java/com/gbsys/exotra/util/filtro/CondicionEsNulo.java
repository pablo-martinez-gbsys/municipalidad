package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición Es Nulo (IS NULL).
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionEsNulo implements Condicion {
    
    private static final String OPERADOR = " IS NULL";
    
    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        consulta.append(AND);
        consulta.append(columna);
        consulta.append(OPERADOR);
    }
}

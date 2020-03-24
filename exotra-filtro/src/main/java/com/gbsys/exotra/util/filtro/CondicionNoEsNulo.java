package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición No Es Nulo (IS NOT NULL).
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionNoEsNulo implements Condicion {
    
    private static final String OPERADOR = " IS NOT NULL";
    
    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        consulta.append(AND);
        consulta.append(columna);
        consulta.append(OPERADOR);
    }
}

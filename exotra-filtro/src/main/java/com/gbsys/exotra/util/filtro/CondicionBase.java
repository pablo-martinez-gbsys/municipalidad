package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición Base con el comportamiento general de una condición.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
abstract class CondicionBase implements Condicion {

    private final String operador;

    public CondicionBase(String operador) {
        this.operador = operador;
    }

    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        consulta.append(AND);
        consulta.append(columna);
        consulta.append(operador);
        consulta.append(PREFIJO_PARAMETRO);
        consulta.append(campo);
        parametros.put(campo, valor);
    }

}

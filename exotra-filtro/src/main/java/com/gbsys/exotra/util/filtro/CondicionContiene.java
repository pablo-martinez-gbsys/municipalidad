package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición Contiene (LIKE con % al inicio y al final).
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionContiene implements Condicion {

    protected static final String OPERADOR = " LIKE ";
    protected static final String UPPER_INICIO = "UPPER(";
    protected static final String UPPER_FIN = ")";
    protected static final String SIGNO = "%";

    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        consulta.append(AND);
        consulta.append(UPPER_INICIO);
        consulta.append(columna);
        consulta.append(UPPER_FIN);
        consulta.append(OPERADOR);
        consulta.append(PREFIJO_PARAMETRO);
        consulta.append(campo);
        if (valor instanceof String) {
            String valorStr = agregarSignos(valor.toString());
            parametros.put(campo, valorStr);
        } else {
            parametros.put(campo, valor);
        }
    }

    /**
     * Agrega los signos de porcentaje a el valor.
     *
     * @param valor String de la consulta a los que se le van agregar los signo
     * de porcentaje
     * @return valor con los signos de porcentaje.
     */
    protected String agregarSignos(String valor) {
        return SIGNO + valor.toUpperCase() + SIGNO;
    }
}

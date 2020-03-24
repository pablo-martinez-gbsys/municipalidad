package com.gbsys.exotra.util.filtro;

/**
 * Condición Inicia Con (LIKE con % al final) .
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionIniciaCon extends CondicionContiene {

    /**
     * Agrega los signos de porcentaje a el valor.
     *
     * @param valor String de la consulta a los que se le van agregar los signo
     * de porcentaje
     * @return valor con los signos de porcentaje.
     */
    @Override
    protected String agregarSignos(String valor) {
        return SIGNO + valor.toUpperCase();
    }
}

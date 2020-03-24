package com.gbsys.exotra.util.filtro;

/**
 * Condición Termina Con (LIKE con % al inicio).
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionTerminaCon extends CondicionContiene {

    /**
     * Agrega los signos de porcentaje a el valor.
     *
     * @param valor String de la consulta a los que se le van agregar los signo
     * de porcentaje
     * @return valor con los signos de porcentaje.
     */
    @Override
    protected String agregarSignos(String valor) {
        return valor.toUpperCase() + SIGNO;
    }
}

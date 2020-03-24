package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición que agrega la columna del filtro literalmente como esta en el value
 * de @Filtro.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionLiteral implements Condicion {

    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        consulta.append(AND);
        consulta.append(columna);
        parametros.put(campo, valor);
    }
}

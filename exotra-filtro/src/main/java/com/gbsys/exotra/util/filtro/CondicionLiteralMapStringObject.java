package com.gbsys.exotra.util.filtro;

import java.util.Map;

/**
 * Condición que agrega la columna del filtro literalmente como esta en el value
 * de @Filtro y aplica los valores llave/valor a los parámetros.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionLiteralMapStringObject implements Condicion {

    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        consulta.append(AND);
        consulta.append(columna);
        @SuppressWarnings("unchecked")
        Map<String, Object> mapa = (Map<String, Object>) valor;
        for (Map.Entry<String, Object> fila : mapa.entrySet()) {
            parametros.put(fila.getKey(), fila.getValue());
        }
    }
}

package com.gbsys.exotra.util.filtro;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Condición Especial para Map&lt;String, Object&gt;, que aplica todas las
 * llave/valor al filtro con CondicionContiene si el valor es String,
 * CondicionEn si el valor es una Colección o CondicionIgual para los demás
 * casos, donde la llave es la columna y el valor es el valor.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 14/05/2018 13:51
 */
public class CondicionMapStringObject implements Condicion {

    private final Condicion condicionIgual;
    private final Condicion condicionContiene;
    private final Condicion condicionEn;

    public CondicionMapStringObject() {
        this.condicionIgual = new CondicionIgual();
        this.condicionContiene = new CondicionContiene();
        this.condicionEn = new CondicionEn();
    }

    @Override
    public void aplicar(StringBuilder consulta, Map<String, Object> parametros, String columna, String campo, Object valor) {
        AtomicInteger contador = new AtomicInteger(0);
        @SuppressWarnings("unchecked")
        Map<String, Object> mapa = (Map<String, Object>) valor;
        for (Entry<String, Object> fila : mapa.entrySet()) {
            if (fila.getValue() instanceof String) {
                condicionContiene.aplicar(consulta, parametros, fila.getKey(), campo + contador.getAndIncrement(), fila.getValue());
            } else if (fila.getValue() instanceof Collection) {
                condicionEn.aplicar(consulta, parametros, fila.getKey(), campo + contador.getAndIncrement(), fila.getValue());
            } else {
                condicionIgual.aplicar(consulta, parametros, fila.getKey(), campo + contador.getAndIncrement(), fila.getValue());
            }
        }
    }

}

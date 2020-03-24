package com.gbsys.exotra.constante;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumerado encargado de representar los estados de las tareas.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@RequiredArgsConstructor
public enum EstadoTramite {
    PENDIENTE("01"),
    APROBADO("02"),
    RECHAZADO("03");

    @Getter
    private final String valor;
}

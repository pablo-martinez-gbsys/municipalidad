package com.gbsys.exotra.util.reporte;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumerado para manejar los reportes del sistema.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes.
 * @version 1.0.0
 * @date 27/11/2017 20:30
 */
@RequiredArgsConstructor
public enum Reporte {

    REPORTE_TAREAS("Reporte de Tareas", "/reporte_tareas.jasper");

    /**
     * Nombre del reporte.
     */
    @Getter
    private final String nombre;
    /**
     * Ruta del reporte.
     */
    @Getter
    private final String ruta;

    @Override
    public String toString() {
        return "Reporte=" + name();
    }

}

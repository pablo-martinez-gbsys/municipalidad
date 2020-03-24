package com.gbsys.exotra.filtro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Filtro para busqueda de cantidad de Tareas.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class TareaFiltroCantidad extends TareaFiltro {

    private static final String SELECT_CANTIDAD = "SELECT COUNT(t) ";

    @Override
    public String consulta() {
        return SELECT_CANTIDAD + FROM_TABLAS;
    }

    @Override
    public boolean incluirOrderBy() {
        return false;
    }
}

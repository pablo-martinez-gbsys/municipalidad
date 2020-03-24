package com.gbsys.exotra.mapper;

import com.gbsys.exotra.filtro.TareaFiltro;
import com.gbsys.exotra.filtro.TareaFiltroCantidad;
import org.mapstruct.Mapper;

/**
 * Mapper para convertir entidades y modelos de Tarea.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Mapper(componentModel = "cdi")
public interface FiltroMapper {
 
    TareaFiltroCantidad aCantidad(TareaFiltro filtro);
}

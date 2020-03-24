package com.gbsys.exotra.mapper;

import com.gbsys.exotra.entidad.Tarea;
import com.gbsys.exotra.modelo.TareaModelo;
import com.gbsys.exotra.xsd.XTarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para convertir entidades y modelos de Tarea.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Mapper(componentModel = "cdi")
public interface TareaMapper {

    Tarea aEntidad(TareaModelo modelo);
    
    @Mapping(target = "seleccionado", ignore = true)
    Tarea aEntidad(XTarea tipo);

    TareaModelo aModelo(Tarea entidad);
    
    XTarea aTipo(Tarea entidad);
}

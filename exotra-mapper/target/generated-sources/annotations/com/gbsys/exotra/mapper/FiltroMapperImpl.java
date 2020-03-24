package com.gbsys.exotra.mapper;

import com.gbsys.exotra.filtro.TareaFiltro;
import com.gbsys.exotra.filtro.TareaFiltroCantidad;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T15:20:20-0600",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@ApplicationScoped
public class FiltroMapperImpl implements FiltroMapper {

    @Override
    public TareaFiltroCantidad aCantidad(TareaFiltro filtro) {
        if ( filtro == null ) {
            return null;
        }

        TareaFiltroCantidad tareaFiltroCantidad = new TareaFiltroCantidad();

        tareaFiltroCantidad.setFecDesde( filtro.getFecDesde() );
        tareaFiltroCantidad.setFecHasta( filtro.getFecHasta() );
        tareaFiltroCantidad.setEstTarea( filtro.getEstTarea() );
        Map<String, Object> map = filtro.getFiltroTabla();
        if ( map != null ) {
            tareaFiltroCantidad.setFiltroTabla( new HashMap<String, Object>( map ) );
        }
        else {
            tareaFiltroCantidad.setFiltroTabla( null );
        }
        tareaFiltroCantidad.setOrdenamiento( filtro.getOrdenamiento() );

        return tareaFiltroCantidad;
    }
}

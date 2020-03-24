package com.gbsys.exotra.mapper;

import com.gbsys.exotra.constante.EstadoTarea;
import com.gbsys.exotra.entidad.Tarea;
import com.gbsys.exotra.modelo.TareaModelo;
import com.gbsys.exotra.xsd.XEstadoTarea;
import com.gbsys.exotra.xsd.XTarea;
import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T15:20:21-0600",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@ApplicationScoped
public class TareaMapperImpl implements TareaMapper {

    @Override
    public Tarea aEntidad(TareaModelo modelo) {
        if ( modelo == null ) {
            return null;
        }

        Tarea tarea = new Tarea();

        tarea.setSeleccionado( modelo.isSeleccionado() );
        tarea.setId( modelo.getId() );
        tarea.setDscTarea( modelo.getDscTarea() );
        tarea.setFecTarea( modelo.getFecTarea() );
        tarea.setEstTarea( modelo.getEstTarea() );

        return tarea;
    }

    @Override
    public Tarea aEntidad(XTarea tipo) {
        if ( tipo == null ) {
            return null;
        }

        Tarea tarea = new Tarea();

        tarea.setId( tipo.getId() );
        tarea.setDscTarea( tipo.getDscTarea() );
        tarea.setFecTarea( tipo.getFecTarea() );
        tarea.setEstTarea( xEstadoTareaToEstadoTarea( tipo.getEstTarea() ) );

        return tarea;
    }

    @Override
    public TareaModelo aModelo(Tarea entidad) {
        if ( entidad == null ) {
            return null;
        }

        TareaModelo tareaModelo = new TareaModelo();

        tareaModelo.setSeleccionado( entidad.isSeleccionado() );
        tareaModelo.setId( entidad.getId() );
        tareaModelo.setDscTarea( entidad.getDscTarea() );
        tareaModelo.setFecTarea( entidad.getFecTarea() );
        tareaModelo.setEstTarea( entidad.getEstTarea() );

        return tareaModelo;
    }

    @Override
    public XTarea aTipo(Tarea entidad) {
        if ( entidad == null ) {
            return null;
        }

        XTarea xTarea = new XTarea();

        xTarea.setId( entidad.getId() );
        xTarea.setDscTarea( entidad.getDscTarea() );
        xTarea.setFecTarea( entidad.getFecTarea() );
        xTarea.setEstTarea( estadoTareaToXEstadoTarea( entidad.getEstTarea() ) );

        return xTarea;
    }

    protected EstadoTarea xEstadoTareaToEstadoTarea(XEstadoTarea xEstadoTarea) {
        if ( xEstadoTarea == null ) {
            return null;
        }

        EstadoTarea estadoTarea;

        switch ( xEstadoTarea ) {
            case PENDIENTE: estadoTarea = EstadoTarea.PENDIENTE;
            break;
            case LISTA: estadoTarea = EstadoTarea.LISTA;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + xEstadoTarea );
        }

        return estadoTarea;
    }

    protected XEstadoTarea estadoTareaToXEstadoTarea(EstadoTarea estadoTarea) {
        if ( estadoTarea == null ) {
            return null;
        }

        XEstadoTarea xEstadoTarea;

        switch ( estadoTarea ) {
            case PENDIENTE: xEstadoTarea = XEstadoTarea.PENDIENTE;
            break;
            case LISTA: xEstadoTarea = XEstadoTarea.LISTA;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + estadoTarea );
        }

        return xEstadoTarea;
    }
}

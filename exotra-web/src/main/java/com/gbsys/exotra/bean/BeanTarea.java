package com.gbsys.exotra.bean;

import com.gbsys.exotra.constante.EstadoTarea;
import com.gbsys.exotra.entidad.Tarea;
import com.gbsys.exotra.servicio.ServicioTarea;
import com.gbsys.exotra.util.tabla.CantidadDatos;
import com.gbsys.exotra.util.tabla.ConsultaDatos;
import com.gbsys.exotra.util.tabla.TablaControl;
import com.gbsys.exotra.util.tabla.TablaControlLazy;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean encargado del mantenimiento de tareas.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Named
@ViewScoped
public class BeanTarea extends BeanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicioTarea servicioTarea;
    @Getter
    @Setter
    private Tarea tarea;
    @Getter
    private TablaControl<Tarea> pendientes;
    @Getter
    private TablaControl<Tarea> listas;

    public BeanTarea() {
        inicializar();
    }

    @Override
    void inicializar() {
        tarea = new Tarea();
    }

    @Override
    @PostConstruct
    void inicializarPost() {
        pendientes = cargarPorEstado(EstadoTarea.PENDIENTE);
        listas = cargarPorEstado(EstadoTarea.LISTA);
    }

    private TablaControlLazy<Tarea> cargarPorEstado(EstadoTarea estado) {
        CantidadDatos cantidad = (filtros)
                -> servicioTarea.cantidadPorEstado(estado);
        ConsultaDatos<Tarea> datos = (inicio, tamano, orden, filtros)
                -> servicioTarea.consultarPorEstado(estado, inicio, tamano);
        return new TablaControlLazy<>(cantidad, datos);
    }

    public void guardar() {
        try {
            servicioTarea.guardar(tarea);
            mensajeInformacion("mensaje.info.ServicioTarea.guardar");
            tarea = new Tarea();
        } catch (Exception ex) {
            tarea.setId(null);
            throw ex;
        }
    }

    public void lista(Long id) {
        servicioTarea.actualizarEstadoPorId(EstadoTarea.LISTA, id);
        mensajeInformacion("mensaje.info.ServicioTarea.lista");
    }

    public void borrar(Long id) {
        servicioTarea.eliminarPorId(id);
        mensajeInformacion("mensaje.info.ServicioTarea.borrar");
    }
}

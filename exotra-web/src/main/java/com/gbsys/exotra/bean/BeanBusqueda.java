package com.gbsys.exotra.bean;

import com.gbsys.exotra.constante.EstadoTarea;
import com.gbsys.exotra.entidad.Tarea;
import com.gbsys.exotra.filtro.TareaFiltro;
import com.gbsys.exotra.filtro.TareaFiltroCantidad;
import com.gbsys.exotra.mapper.FiltroMapper;
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
public class BeanBusqueda extends BeanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FiltroMapper filtroMapper;
    @Inject
    private ServicioTarea servicioTarea;
    @Getter
    @Setter
    private TareaFiltro tareaFiltro;
    @Getter
    private TablaControl<Tarea> tareas;
    @Getter
    private EstadoTarea[] estados;

    public BeanBusqueda() {
        inicializar();
    }

    @Override
    void inicializar() {
        tareaFiltro = new TareaFiltro();
        estados = EstadoTarea.values();
        tareas = new TablaControlLazy<>();
    }

    @Override
    @PostConstruct
    void inicializarPost() {
    }

//    public void buscar() {
//        CantidadDatos cantidad = (filtros) -> {
//            TareaFiltroCantidad filtroCantidad = filtroMapper.aCantidad(tareaFiltro);
//            filtroCantidad.setFiltroTabla(filtros);
//            return servicioTarea.cantidadPorFiltro(filtroCantidad);
//        };
//        ConsultaDatos<Tarea> datos = (inicio, tamano, orden, filtros) -> {
//            tareaFiltro.setFiltroTabla(filtros);
//            if (orden != null && !orden.trim().isEmpty()) {
//                tareaFiltro.setOrdenamiento(orden);
//            }
//            return servicioTarea.consultarPorFiltro(tareaFiltro, inicio, tamano);
//        };
//        tareas = new TablaControlLazy<>(cantidad, datos);
//    }
//
//    public void limpiar() {
//        tareaFiltro = new TareaFiltro();
//        tareas = new TablaControlLazy<>();
//    }

}

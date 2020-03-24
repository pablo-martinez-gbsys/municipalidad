package com.gbsys.exotra.servicio;

import com.gbsys.exotra.constante.EstadoTarea;
import com.gbsys.exotra.entidad.QTarea;
import com.gbsys.exotra.entidad.Tarea;
import com.gbsys.exotra.exception.ServicioException;
import com.gbsys.exotra.filtro.TareaFiltro;
import com.gbsys.exotra.filtro.TareaFiltroCantidad;
import com.gbsys.exotra.util.ServicioLogger;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Servicio encargado de realizar todas las operaciones de base de datos
 * relacionadas a la entidad Tarea.
 *
 * /**
 * Entidad encargada de representar la tabla GBS_TAREA.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Stateless
@ServicioLogger
public class ServicioTarea extends ServicioBase<Tarea> {

    /**
     * Guarda la tarea en la base de datos y retorna la entidad con su ID.
     *
     * @param tarea tarea a guardar
     * @return tarea guardada
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    @Override
    public Tarea guardar(Tarea tarea) {
        try {
            tarea.setEstTarea(EstadoTarea.PENDIENTE);
            tarea.setFecTarea(new Date());
            return super.guardar(tarea);
        } catch (Exception ex) {
            throw new ServicioException("mensaje.error.ServicioTarea.guardar", ex);
        }
    }

    /**
     * Actualiza el estado de la tarea por su ID.
     *
     * @param estado Nuevo estado de la tarea
     * @param id ID de la tarea a actualizar
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    public void actualizarEstadoPorId(EstadoTarea estado, Long id) {
        try {
            QTarea tarea = QTarea.tarea;
            qf
                    .update(tarea)
                    .set(tarea.estTarea, estado)
                    .where(tarea.id.eq(id))
                    .execute();
        } catch (Exception ex) {
            throw new ServicioException("mensaje.error.ServicioTarea.actualizarEstadoPorId", ex);
        }
    }

    /**
     * Elimina una tarea por su ID.
     *
     * @param id id de la tarea a eliminar
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    public void eliminarPorId(Long id) {
        try {
            QTarea tarea = QTarea.tarea;
            qf
                    .delete(tarea)
                    .where(tarea.id.eq(id))
                    .execute();
        } catch (Exception ex) {
            throw new ServicioException("mensaje.error.ServicioTarea.eliminarPorId", ex);
        }
    }

    /**
     * Obtiene la cantidad de tareas con el estado indicado.
     *
     * @param estado estado a consultar
     * @return cantidad de tareas con el estado indicado
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    public Long cantidadPorEstado(EstadoTarea estado) {
        try {
            QTarea tarea = QTarea.tarea;
            return qf
                    .selectFrom(tarea)
                    .where(tarea.estTarea.eq(estado))
                    .orderBy(tarea.fecTarea.desc())
                    .fetchCount();
        } catch (Exception ex) {
            throw new ServicioException("mensaje.error.ServicioTarea.cantidadPorEstado", ex);
        }
    }

    /**
     * Obtiene las tareas con el estado indicado usando paginación.
     *
     * @param estado estado a consultar
     * @param inicio fila inicial
     * @param tamano cantidad de filas
     * @return tareas con el estado indicado
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    public List<Tarea> consultarPorEstado(EstadoTarea estado, int inicio, int tamano) {
        try {
            QTarea tarea = QTarea.tarea;
            return qf
                    .selectFrom(tarea)
                    .where(tarea.estTarea.eq(estado))
                    .orderBy(tarea.fecTarea.desc())
                    .offset(inicio)
                    .limit(tamano)
                    .fetch();
        } catch (Exception ex) {
            throw new ServicioException("mensaje.error.ServicioTarea.consultarPorEstado", ex);
        }
    }

    /**
     * Obtiene la cantidad de tareas aplicando filtrado.
     *
     * @param filtro filtro a aplicar
     * @return cantidad de tareas que cumplen con el filtro
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    public Long cantidadPorFiltro(TareaFiltroCantidad filtro) {
        try {
            List<Object> resultados = consultarFiltro(filtro);
            return resultados.isEmpty() ? 0L : aLong(resultados.get(0));
        } catch (Exception ex) {
            throw new ServicioException("mensaje.info.ServicioTarea.cantidadPorFiltro", ex);
        }
    }

    /**
     * Obtiene las tareas aplicando filtrado y usando paginación.
     *
     * @param filtro filtro a aplicar
     * @param inicio fila inicial
     * @param cantidad cantidad de filas
     * @return tareas que cumplen con el filtro
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 27/01/2020
     */
    public List<Tarea> consultarPorFiltro(TareaFiltro filtro, int inicio, int cantidad) {
        try {
            List<Tarea> resultados = consultarFiltro(filtro, inicio, cantidad);
            return resultados;
        } catch (Exception ex) {
            throw new ServicioException("mensaje.info.ServicioTarea.consultarPorFiltro", ex);
        }
    }
}

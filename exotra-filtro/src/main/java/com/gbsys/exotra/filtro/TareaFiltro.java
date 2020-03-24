package com.gbsys.exotra.filtro;

import com.gbsys.exotra.constante.EstadoTarea;
import com.gbsys.exotra.util.filtro.CondicionMapStringObject;
import com.gbsys.exotra.util.filtro.CondicionMayorIgual;
import com.gbsys.exotra.util.filtro.CondicionMenorIgual;
import com.gbsys.exotra.util.filtro.Filtro;
import com.gbsys.exotra.util.filtro.FiltroBase;
import java.util.Date;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Filtro para busqueda de Tareas.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TareaFiltro extends FiltroBase {

    private static final long serialVersionUID = 1L;
    private static final String SELECT_CAMPOS
            = "SELECT t ";
    protected static final String FROM_TABLAS
            = "  FROM Tarea t ";

    @Override
    public String consulta() {
        return SELECT_CAMPOS + FROM_TABLAS;
    }

    @Filtro(value = "t.fecTarea", condicion = CondicionMayorIgual.class)
    private Date fecDesde;
    @Filtro(value = "t.fecTarea", condicion = CondicionMenorIgual.class)
    private Date fecHasta;
    @Filtro("t.estTarea")
    private EstadoTarea estTarea;
    @Filtro(value = "", condicion = CondicionMapStringObject.class)
    private Map<String, Object> filtroTabla;
    private String ordenamiento = "t.fecTarea DESC";
    
    @Override
    public String ordenamiento() {
        return getOrdenamiento();
    }
}

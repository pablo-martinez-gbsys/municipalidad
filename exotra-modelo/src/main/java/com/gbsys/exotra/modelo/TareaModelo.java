package com.gbsys.exotra.modelo;

import com.gbsys.exotra.constante.EstadoTarea;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Modelo encargado de representar la entidad Tarea.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class TareaModelo extends ModeloBase {

    private Long id;
    @NotNull
    private String dscTarea;
    private Date fecTarea;
    private EstadoTarea estTarea;
}

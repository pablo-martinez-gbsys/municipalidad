package com.gbsys.exotra.entidad;

import com.gbsys.exotra.constante.EstadoTarea;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidad encargada de representar la tabla GBS_TAREA.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Entity
@Table(name = "GBS_TAREA")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Tarea extends EntidadBase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_GBS_TAREA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_GBS_TAREA", sequenceName = "SEQ_GBS_TAREA", allocationSize = 1)
    private Long id;
    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "DSC_TAREA")
    private String dscTarea;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_TAREA")
    private Date fecTarea;
    @Enumerated(EnumType.STRING)
    @Column(name = "EST_TAREA")
    private EstadoTarea estTarea;
}

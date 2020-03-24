/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbsys.exotra.modelo;

import java.io.Serializable;
import lombok.Data;
import java.util.List;
import javax.ejb.Stateless;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author GBSYS. Diseñado y Desarrollado por: Pablo Martínez
 * @version 1.0.0
 * @date 06/03/2020
 */
@Data
@EqualsAndHashCode(of = {"idPersona"}, callSuper = false)
public class RespuestaBase extends ModeloBase {
private static final long serialVersionUID = 1L;
    private Long idPersona;
    private String numIdentificacion;
    private String nombreCompleto;
    private Long tipoIdentificacion;
    private String codRespuesta;
    private String observacion;
    private String tipoTramite;
    private String dscTramite;
    private String numTramite;
    private Long idCarpeta;
    private Long estado;
    private List<Validaciones> validaciones;

}

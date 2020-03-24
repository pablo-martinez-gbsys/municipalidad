/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbsys.exotra.modelo;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class ExoneracionModelo extends ModeloBase {

    private static final long serialVersionUID = 1L;

    private Integer idTipoTramite;

    private Boolean tramiteOficio;

    private Integer idModulo;

    private Integer idMunicipal;

    private Date fecRecepcionDocumentos;

    private Integer estadoTramite;

    private Boolean tramiteInterno;

    private Integer idTramitePadre;

    private Boolean indSubioDocumentos;
    private String numeroFolio;
    private String observacion;
    private String telefono;
    private String telefonoCelular;
    private String correo;
    private String nombreCompleto;
    private String numeroOficio;
    private String numeroTramite;
    private String direccionPropiedad;
    private String usuario;
    private String identificacion;
 
    private Integer idPersonaTramitadora;
    private Boolean ingresar2020;
    //private Integer requisitos;
}

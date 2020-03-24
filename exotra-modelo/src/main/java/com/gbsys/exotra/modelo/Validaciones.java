/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbsys.exotra.modelo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author GBSYS. Diseñado y Desarrollado por: Pablo Martínez
 * @version 1.0.0
 * @date 06/03/2020
 */
@Data
public class Validaciones implements Serializable {
    
    private String idPesona;
    private String codigo;
    private String descripcion;
    private String validaciones;
    private String tiene;
    
}

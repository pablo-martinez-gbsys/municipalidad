package com.gbsys.exotra.cliente.seguridad;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Modelo para la respuesta de error del servicio web.
 *
 * @author Ing. Melanie Morales Arroyo
 * @since 16/07/2018
 * @version 1.0.0
 */
@XmlRootElement
@Data
public class MensajeError {

    //Código del error.
    private int codigo;
    //Descripción del error.
    private String descripcion;
}

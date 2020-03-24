package com.gbsys.exotra.cliente.seguridad;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Modelo que representa el usuario y clave de la petición.
 *
 * @author Ing. Melanie Morales Arroyo.
 * @version 1.0.0
 * @date 10/07/2018
 */
@Data
@EqualsAndHashCode(of = {"usuario"})
public class ModeloAutenticacion {

    private String usuario;
    private String clave;
}

package com.gbsys.exotra.modelo;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Modelo encargado de representar la selección para los combos y listados.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Ing. Herman Barrantes
 * @version 1.0.0
 * @date 10/04/2017 07:45
 */
@Value
@EqualsAndHashCode(of = {"codigo"}, callSuper = false)
public class Seleccion implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Object codigo;
    private final String descripcion;

}

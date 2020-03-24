package com.gbsys.exotra.entidad;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad Base encargada de cumplir la función de heredar características
 * comunes a las demás entidades.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
public abstract class EntidadBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private transient boolean seleccionado;

}

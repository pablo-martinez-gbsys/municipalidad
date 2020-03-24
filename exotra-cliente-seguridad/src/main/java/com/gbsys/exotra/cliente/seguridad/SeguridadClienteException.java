package com.gbsys.exotra.cliente.seguridad;

import javax.ejb.ApplicationException;

/**
 * Excepción encargada de encapsular las excepciones lazadas por los servicios.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 13/11/2017 17:21
 */
@ApplicationException(rollback = true)
public class SeguridadClienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SeguridadClienteException(String message) {
        super(message);
    }

    public SeguridadClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeguridadClienteException(Throwable cause) {
        super(cause);
    }

}

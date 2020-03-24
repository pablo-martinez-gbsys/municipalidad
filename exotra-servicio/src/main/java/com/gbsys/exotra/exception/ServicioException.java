package com.gbsys.exotra.exception;

import javax.ejb.ApplicationException;

/**
 * Excepción encargada de encapsular las excepciones lazadas por los servicios.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 13/11/2017 17:21
 */
@ApplicationException(rollback = true)
public class ServicioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServicioException(String message) {
        super(message);
    }

    public ServicioException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicioException(Throwable cause) {
        super(cause);
    }

}

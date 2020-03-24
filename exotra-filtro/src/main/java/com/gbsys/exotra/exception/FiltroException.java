package com.gbsys.exotra.exception;

import javax.ejb.ApplicationException;

/**
 * Excepción encargada de encapsular las excepciones lazadas por los filtros.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 30/11/2017 07:34
 */
@ApplicationException(rollback = true)
public class FiltroException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FiltroException(String message) {
        super(message);
    }

    public FiltroException(String message, Throwable cause) {
        super(message, cause);
    }

    public FiltroException(Throwable cause) {
        super(cause);
    }

}

package com.gbsys.exotra.exception;

import javax.ejb.ApplicationException;

/**
 * Excepción encargada de encapsular las excepciones lazadas por los
 * utilitarios.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 18/11/2017 16:51
 */
@ApplicationException(rollback = true)
public class UtilException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

}

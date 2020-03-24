/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbsys.exotra.seguridad.exception;
import javax.ejb.ApplicationException;
/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class ServicioExotraException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServicioExotraException(String message) {
        super(message);
    }

    public ServicioExotraException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicioExotraException(Throwable cause) {
        super(cause);
    }
}

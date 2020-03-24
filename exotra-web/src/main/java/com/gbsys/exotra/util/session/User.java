package com.gbsys.exotra.util.session;

import com.gbsys.exotra.modelo.RespuestaBase;
import java.io.Serializable;
import lombok.Data;

/**
 * Modelo encargado de almacenar los datos del usuario actualmente logeado.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String fullname;
    private String email;
    private Long idSystem;
    private RespuestaBase respuestaBase;
}

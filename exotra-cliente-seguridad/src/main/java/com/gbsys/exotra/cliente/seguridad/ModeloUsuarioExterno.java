package com.gbsys.exotra.cliente.seguridad;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Claudia
 */
@XmlRootElement
@Data
public class ModeloUsuarioExterno {

    private long id;
    private long idSistema;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String fechaUltimaConexion;
    private String fechaVenceCuenta;
    private boolean activo;
}

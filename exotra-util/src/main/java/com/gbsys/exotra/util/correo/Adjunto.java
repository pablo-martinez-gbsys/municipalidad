package com.gbsys.exotra.util.correo;

import com.gbsys.exotra.util.Formato;
import lombok.Value;

/**
 * Entidad encargada de representar un archivo ajunto.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 02/05/2017 11:02
 */
@Value
public class Adjunto {

    private final String nombre;
    private final String descripcion;
    private final Formato formato;
    private final byte[] archivo;
}

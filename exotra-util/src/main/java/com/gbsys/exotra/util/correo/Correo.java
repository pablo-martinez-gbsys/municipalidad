package com.gbsys.exotra.util.correo;

import java.util.Collection;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

/**
 * Entidad encargada de representar un correo.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 02/05/2017 11:01
 */
@Value
@Builder
public class Correo {

    private final String asunto;
    private final String mensaje;
    @Singular
    private final Collection<String> contactos;
    @Singular
    private final Collection<String> copias;
    @Singular
    private final Collection<String> copiasOcultas;
    @Singular
    private final Collection<Adjunto> adjuntos;

}

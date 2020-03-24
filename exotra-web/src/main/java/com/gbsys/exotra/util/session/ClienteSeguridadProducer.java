package com.gbsys.exotra.util.session;

import com.gbsys.exotra.cliente.seguridad.ClienteSeguridad;
import com.gbsys.exotra.seguridad.WSIngresoTramites;
import com.gbsys.exotra.seguridad.WSIngresoTramitesSoap;
import java.io.Serializable;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.extern.slf4j.Slf4j;

/**
 * Productor de clientes de seguridad para CDI.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Slf4j
@ApplicationScoped
public class ClienteSeguridadProducer implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Produces
    public ClienteSeguridad cliente() {
        try {
            return new ClienteSeguridad("http://10.1.1.45:8080/seguridad-ws/api/");
        } catch (Exception ex) {
            log.error("ClienteSeguridadProducer.cliente()", ex);
            return null;
        }
    }
    
     @Produces
    public WSIngresoTramitesSoap crearCliente() {
        try {
            /**
             * CORREGIR, la URL esta quemada, cargarla desde un servicio de BD.
             */
            URL url = new URL("http://ws-declaraciones.curridabat.go.cr/WS_IngresoTramites.asmx?WSDL");
            WSIngresoTramitesSoap cliente = new WSIngresoTramites(url).getWSIngresoTramitesSoap();
            return cliente;
        } catch (Exception ex) {
            return null;
        }
    }
}

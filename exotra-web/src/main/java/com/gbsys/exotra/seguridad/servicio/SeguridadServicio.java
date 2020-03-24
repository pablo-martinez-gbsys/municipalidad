/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbsys.exotra.seguridad.servicio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbsys.exotra.modelo.ExoneracionModelo;

import com.gbsys.exotra.seguridad.WSIngresoTramites;
import com.gbsys.exotra.seguridad.WSIngresoTramitesSoap;
import com.gbsys.exotra.seguridad.exception.ServicioExotraException;
import com.gbsys.exotra.util.session.ClienteSeguridadProducer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDateTime.now;
import java.util.Date;
import javax.ejb.Stateless;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author Usuario
 */
@Slf4j
@Stateless
public class SeguridadServicio {

    private static final String ERROR_GENERICO = "Error al obtener respuesta.";
    private static final String MENSAJE_GENERICO = "Favor revisar si el Web Service y verificar si se encuentra funcionando correctamente.";

    @Inject
    private WSIngresoTramitesSoap ingresoTramitesSoap;

    
      /**
     * Metodo que consulta al WS el login 
     *
     * @author GBSYS. Diseñado y Desarrollado por: Pablo Martinez
     * @version 1.0.0
     * @date 09/03/2020
     */
    
    public String login(String user, String password) {
        try {

            String respuestaLogin = ingresoTramitesSoap.loginTramitesWeb(user, password);
            return respuestaLogin;
        } catch (Exception ex) {
            log.error(ERROR_GENERICO, ex);
            throw new ServicioExotraException(ERROR_GENERICO, ex);
        }
    }


    /**
     * Metodo que consulta al WS las propiedades segun la cedula
     *
     * @author GBSYS. Diseñado y Desarrollado por: Pablo Martinez
     * @version 1.0.0
     * @date 11/03/2020
     */
    public String consultarPropiedades(String userInterno, String passInterno, String identificacion) {
        try {

            String respuestaTramite = ingresoTramitesSoap.propiedadesIdentificacion(userInterno, passInterno, identificacion);
            return respuestaTramite;
        } catch (Exception ex) {
            log.error(ERROR_GENERICO, ex);
            throw new ServicioExotraException(ERROR_GENERICO, ex);
        }
    }
    
       /**
 * Metodo que se encarga de crear el tramite desde el WS
 *
 * @author GBSYS. Diseñado y Desarrollado por: Pablo Martinez
 * @version 1.0.0
 * @date 11/03/2020
 */

    public String CrearTramite(ExoneracionModelo exoneracionModelo, String userInterno, String passInterno) {
        try {

            String respuestaCrearTramite = ingresoTramitesSoap.crearTramite(
                    exoneracionModelo.getIdTipoTramite(),
                    exoneracionModelo.getTramiteOficio(),
                    exoneracionModelo.getIdModulo(),
                    exoneracionModelo.getIdMunicipal(),
                    exoneracionModelo.getFecRecepcionDocumentos(),
                    exoneracionModelo.getEstadoTramite(),
                    exoneracionModelo.getTramiteInterno(),
                    exoneracionModelo.getIdTramitePadre(),
                    exoneracionModelo.getIndSubioDocumentos(),
                    exoneracionModelo.getObservacion(),
                    exoneracionModelo.getNumeroTramite(),
                    exoneracionModelo.getNombreCompleto(),
                    exoneracionModelo.getIdentificacion(),
                    userInterno,
                    passInterno,
                    exoneracionModelo.getIngresar2020()
            );
            return respuestaCrearTramite;
        } catch (Exception ex) {
            log.error(ERROR_GENERICO, ex);
            throw new ServicioExotraException(ERROR_GENERICO, ex);
        }
    }

}

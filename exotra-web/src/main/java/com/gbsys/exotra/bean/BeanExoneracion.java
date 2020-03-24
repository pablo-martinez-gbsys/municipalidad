/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbsys.exotra.bean;

import com.gbsys.exotra.filtro.TareaFiltro;
import com.gbsys.exotra.modelo.ExoneracionModelo;
import com.gbsys.exotra.modelo.RespuestaBase;
import com.gbsys.exotra.seguridad.servicio.SeguridadServicio;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import com.gbsys.exotra.util.FechaUtil;
import java.text.DateFormat;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.json.JSONArray;

/**
 *
 * @author Usuario
 */
@Slf4j
@ManagedBean
@ViewScoped
public class BeanExoneracion extends BeanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private ExoneracionModelo exoneracionModelo;

    @Getter
    @Setter
    String respuestaPropiedad;
    @Getter
    @Setter
    private RespuestaBase respuestaBase;

    @Getter
    @Setter
    Boolean continuar;

    @Setter
    String userInterno;

    @Setter
    String passInterno;

    @Inject
    private SeguridadServicio seguridadServicio;

    public BeanExoneracion() {
        inicializar();
    }

    @Override
    void inicializar() {

        try {

            seguridadServicio = new SeguridadServicio();
            exoneracionModelo = new ExoneracionModelo();

        } catch (Exception ex) {

            Messages.addGlobalError(ex.getMessage());
        }
    }

    @PostConstruct
    @Override
    void inicializarPost() {
        userInterno = "adUserExternoPrueba";
        passInterno = "adPrueba";
        exoneracionModelo.setIdTipoTramite(Integer.parseInt(Faces.getSession().getAttribute("pIdTipoTramite").toString()));
        exoneracionModelo.setIdentificacion(Faces.getSession().getAttribute("pIdentificacion").toString());
        exoneracionModelo.setIdPersonaTramitadora(Integer.parseInt(Faces.getSession().getAttribute("pIdentificacion").toString()));
        exoneracionModelo.setTelefono(Faces.getSession().getAttribute("pTelefono").toString());
        exoneracionModelo.setTelefonoCelular(Faces.getSession().getAttribute("pTelefonoCelular").toString());
        exoneracionModelo.setNombreCompleto(Faces.getSession().getAttribute("pNombreCompleto").toString());
        exoneracionModelo.setUsuario(Faces.getSession().getAttribute("pNombreCompleto").toString());
        exoneracionModelo.setCorreo(Faces.getSession().getAttribute("pCorreo").toString());
        consultarPropiedades();

    }

    /**
     *
     * @author Pablo Martinez Metodo que se encarga de consultar las propiedades
     * que tiene la persona de session
     *
     */
    public void consultarPropiedades() {

        try {

            respuestaPropiedad = seguridadServicio.consultarPropiedades(userInterno, passInterno, exoneracionModelo.getIdentificacion());

            JSONArray jsonArray = new JSONArray(respuestaPropiedad);

            if (jsonArray.length() == 1) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    org.primefaces.json.JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    exoneracionModelo.setNumeroFolio(jsonObject1.optString("Num_Referencia"));
                    exoneracionModelo.setDireccionPropiedad(jsonObject1.optString("Direccion"));
                    continuar = true;
                }

            } else {
                for (int i = 0; i < jsonArray.length(); i++) {
                    org.primefaces.json.JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    exoneracionModelo.setNumeroFolio(jsonObject1.optString("Num_Referencia"));
                    exoneracionModelo.setDireccionPropiedad(jsonObject1.optString("Direccion"));

                }
                continuar = false;

            }

        } catch (Exception ex) {

            Messages.addGlobalError(ex.getMessage());
        }

    }

    /**
     *
     * @author Pablo Martinez Metodo que se encarga de crear el trámite
     *
     */
    public void crearTramite() {
        try {

            if (continuar == true) {

                exoneracionModelo.setIdModulo(1);
                exoneracionModelo.setTramiteOficio(false);
                exoneracionModelo.setIdTramitePadre(0);
                exoneracionModelo.setIdMunicipal(3);
                exoneracionModelo.setIndSubioDocumentos(true);
                exoneracionModelo.setIngresar2020(false);//este campo siempre debe ir False para no afectar los otros sistemas
                exoneracionModelo.setTramiteInterno(true);
                exoneracionModelo.setEstadoTramite(5);
                exoneracionModelo.setNumeroOficio("1");
                exoneracionModelo.setNumeroTramite("0");
                exoneracionModelo.setIdTipoTramite(1);
                
                
                exoneracionModelo.setFecRecepcionDocumentos(FechaUtil.formatearFecha(FechaUtil.obtenerFechaActual(),FechaUtil.FORMATO_FECHA_YYYY_MM_DD));

                String respuestaTramite = seguridadServicio.CrearTramite(exoneracionModelo, userInterno, passInterno);

                respuestaBase = new RespuestaBase();
                JSONParser parser = new JSONParser();

                JSONObject jsonTramite = (JSONObject) parser.parse(respuestaTramite);
                respuestaBase.setCodRespuesta((String) jsonTramite.get("pCodRespuesta"));
                respuestaBase.setObservacion((String) jsonTramite.get("pObservacion"));

                if (respuestaBase.getCodRespuesta().equals("00")) {

                    Messages.addFlashGlobalInfo(respuestaBase.getObservacion());

                } else {

                    Messages.addFlashGlobalError(respuestaBase.getObservacion());

                }
            }

        } catch (Exception ex) {

            Messages.addGlobalError(ex.getMessage());
        }

    }

}


package com.gbsys.exotra.seguridad;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WS_IngresoTramitesSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSIngresoTramitesSoap {


    /**
     * 
     * @param telefonoCelular
     * @param telefonoTrabajo
     * @param telefonoCasa
     * @param primerApellido
     * @param pass
     * @param direccion
     * @param segundoApellido
     * @param identificacion
     * @param fax
     * @param nombre
     * @param correoElectronico
     * @param tipoCedula
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/ingresoUsuario")
    @WebResult(name = "ingresoUsuarioResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "ingresoUsuario", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.IngresoUsuario")
    @ResponseWrapper(localName = "ingresoUsuarioResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.IngresoUsuarioResponse")
    public String ingresoUsuario(
        @WebParam(name = "nombre", targetNamespace = "http://tempuri.org/")
        String nombre,
        @WebParam(name = "primerApellido", targetNamespace = "http://tempuri.org/")
        String primerApellido,
        @WebParam(name = "segundoApellido", targetNamespace = "http://tempuri.org/")
        String segundoApellido,
        @WebParam(name = "telefonoCelular", targetNamespace = "http://tempuri.org/")
        String telefonoCelular,
        @WebParam(name = "telefonoCasa", targetNamespace = "http://tempuri.org/")
        String telefonoCasa,
        @WebParam(name = "telefonoTrabajo", targetNamespace = "http://tempuri.org/")
        String telefonoTrabajo,
        @WebParam(name = "fax", targetNamespace = "http://tempuri.org/")
        String fax,
        @WebParam(name = "correoElectronico", targetNamespace = "http://tempuri.org/")
        String correoElectronico,
        @WebParam(name = "direccion", targetNamespace = "http://tempuri.org/")
        String direccion,
        @WebParam(name = "identificacion", targetNamespace = "http://tempuri.org/")
        String identificacion,
        @WebParam(name = "tipoCedula", targetNamespace = "http://tempuri.org/")
        String tipoCedula,
        @WebParam(name = "pass", targetNamespace = "http://tempuri.org/")
        String pass);

    /**
     * 
     * @param tramiteInterno
     * @param numeroTramite
     * @param estadoTramite
     * @param userInterno
     * @param ingresar2020
     * @param idModulo
     * @param idTramitePadre
     * @param identificacion
     * @param idMunicipal
     * @param tramiteOficio
     * @param passInterno
     * @param fecRecepcionDocumentos
     * @param indSubioDocumentos
     * @param usuario
     * @param idTipoTramite
     * @param observacion
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/crearTramite")
    @WebResult(name = "crearTramiteResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "crearTramite", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.CrearTramite")
    @ResponseWrapper(localName = "crearTramiteResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.CrearTramiteResponse")
    public String crearTramite(
        @WebParam(name = "Id_Tipo_Tramite", targetNamespace = "http://tempuri.org/")
        int idTipoTramite,
        @WebParam(name = "Tramite_Oficio", targetNamespace = "http://tempuri.org/")
        boolean tramiteOficio,
        @WebParam(name = "Id_Modulo", targetNamespace = "http://tempuri.org/")
        int idModulo,
        @WebParam(name = "Id_Municipal", targetNamespace = "http://tempuri.org/")
        int idMunicipal,
        @WebParam(name = "Fec_Recepcion_Documentos", targetNamespace = "http://tempuri.org/")
        Date fecRecepcionDocumentos,
        @WebParam(name = "Estado_Tramite", targetNamespace = "http://tempuri.org/")
        int estadoTramite,
        @WebParam(name = "Tramite_Interno", targetNamespace = "http://tempuri.org/")
        boolean tramiteInterno,
        @WebParam(name = "ID_Tramite_Padre", targetNamespace = "http://tempuri.org/")
        int idTramitePadre,
        @WebParam(name = "Ind_Subio_Documentos", targetNamespace = "http://tempuri.org/")
        boolean indSubioDocumentos,
        @WebParam(name = "Observacion", targetNamespace = "http://tempuri.org/")
        String observacion,
        @WebParam(name = "Numero_Tramite", targetNamespace = "http://tempuri.org/")
        String numeroTramite,
        @WebParam(name = "Usuario", targetNamespace = "http://tempuri.org/")
        String usuario,
        @WebParam(name = "identificacion", targetNamespace = "http://tempuri.org/")
        String identificacion,
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno,
        @WebParam(name = "ingresar2020", targetNamespace = "http://tempuri.org/")
        boolean ingresar2020);

    /**
     * 
     * @param pass
     * @param identificacion
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/loginTramitesWeb")
    @WebResult(name = "loginTramitesWebResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "loginTramitesWeb", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.LoginTramitesWeb")
    @ResponseWrapper(localName = "loginTramitesWebResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.LoginTramitesWebResponse")
    public String loginTramitesWeb(
        @WebParam(name = "identificacion", targetNamespace = "http://tempuri.org/")
        String identificacion,
        @WebParam(name = "pass", targetNamespace = "http://tempuri.org/")
        String pass);

    /**
     * 
     * @param userInterno
     * @param passInterno
     * @param identificacion
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/validacionUsuarioLogueado")
    @WebResult(name = "validacionUsuarioLogueadoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "validacionUsuarioLogueado", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.ValidacionUsuarioLogueado")
    @ResponseWrapper(localName = "validacionUsuarioLogueadoResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.ValidacionUsuarioLogueadoResponse")
    public String validacionUsuarioLogueado(
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno,
        @WebParam(name = "Identificacion", targetNamespace = "http://tempuri.org/")
        String identificacion);

    /**
     * 
     * @param userInterno
     * @param passInterno
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/catalogoTiposTramites")
    @WebResult(name = "catalogoTiposTramitesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "catalogoTiposTramites", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.CatalogoTiposTramites")
    @ResponseWrapper(localName = "catalogoTiposTramitesResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.CatalogoTiposTramitesResponse")
    public String catalogoTiposTramites(
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno);

    /**
     * 
     * @param userInterno
     * @param passInterno
     * @param tipoTramite
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/catalogoRequisitosTipoTramite")
    @WebResult(name = "catalogoRequisitosTipoTramiteResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "catalogoRequisitosTipoTramite", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.CatalogoRequisitosTipoTramite")
    @ResponseWrapper(localName = "catalogoRequisitosTipoTramiteResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.CatalogoRequisitosTipoTramiteResponse")
    public String catalogoRequisitosTipoTramite(
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno,
        @WebParam(name = "tipoTramite", targetNamespace = "http://tempuri.org/")
        int tipoTramite);

    /**
     * 
     * @param userInterno
     * @param passInterno
     * @param identificacion
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/propiedadesIdentificacion")
    @WebResult(name = "propiedadesIdentificacionResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "propiedadesIdentificacion", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.PropiedadesIdentificacion")
    @ResponseWrapper(localName = "propiedadesIdentificacionResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.PropiedadesIdentificacionResponse")
    public String propiedadesIdentificacion(
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno,
        @WebParam(name = "identificacion", targetNamespace = "http://tempuri.org/")
        String identificacion);

    /**
     * 
     * @param idCarpeta
     * @param numeroTramite
     * @param userInterno
     * @param passInterno
     * @param documento
     * @param extencion
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/insercionDocumentos")
    @WebResult(name = "insercionDocumentosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "insercionDocumentos", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.InsercionDocumentos")
    @ResponseWrapper(localName = "insercionDocumentosResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.InsercionDocumentosResponse")
    public String insercionDocumentos(
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno,
        @WebParam(name = "idCarpeta", targetNamespace = "http://tempuri.org/")
        int idCarpeta,
        @WebParam(name = "documento", targetNamespace = "http://tempuri.org/")
        byte[] documento,
        @WebParam(name = "numeroTramite", targetNamespace = "http://tempuri.org/")
        String numeroTramite,
        @WebParam(name = "extencion", targetNamespace = "http://tempuri.org/")
        String extencion);

    /**
     * 
     * @param idDetalleEstado
     * @param userInterno
     * @param fechaInicio
     * @param fechaFinal
     * @param passInterno
     * @param numTramite
     * @param identificacion
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/consultaTramites")
    @WebResult(name = "consultaTramitesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "consultaTramites", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.ConsultaTramites")
    @ResponseWrapper(localName = "consultaTramitesResponse", targetNamespace = "http://tempuri.org/", className = "com.gbsys.exotra.seguridad.ConsultaTramitesResponse")
    public String consultaTramites(
        @WebParam(name = "userInterno", targetNamespace = "http://tempuri.org/")
        String userInterno,
        @WebParam(name = "passInterno", targetNamespace = "http://tempuri.org/")
        String passInterno,
        @WebParam(name = "identificacion", targetNamespace = "http://tempuri.org/")
        String identificacion,
        @WebParam(name = "idDetalleEstado", targetNamespace = "http://tempuri.org/")
        int idDetalleEstado,
        @WebParam(name = "fechaInicio", targetNamespace = "http://tempuri.org/")
        String fechaInicio,
        @WebParam(name = "fechaFinal", targetNamespace = "http://tempuri.org/")
        String fechaFinal,
        @WebParam(name = "numTramite", targetNamespace = "http://tempuri.org/")
        String numTramite);

}

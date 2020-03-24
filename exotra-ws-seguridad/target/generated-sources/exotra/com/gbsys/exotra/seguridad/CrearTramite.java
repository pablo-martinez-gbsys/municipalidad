
package com.gbsys.exotra.seguridad;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id_Tipo_Tramite" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Tramite_Oficio" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Id_Modulo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Id_Municipal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Fec_Recepcion_Documentos" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="Estado_Tramite" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Tramite_Interno" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ID_Tramite_Padre" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Ind_Subio_Documentos" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Numero_Tramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="passInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ingresar2020" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idTipoTramite",
    "tramiteOficio",
    "idModulo",
    "idMunicipal",
    "fecRecepcionDocumentos",
    "estadoTramite",
    "tramiteInterno",
    "idTramitePadre",
    "indSubioDocumentos",
    "observacion",
    "numeroTramite",
    "usuario",
    "identificacion",
    "userInterno",
    "passInterno",
    "ingresar2020"
})
@XmlRootElement(name = "crearTramite")
public class CrearTramite
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Id_Tipo_Tramite")
    protected int idTipoTramite;
    @XmlElement(name = "Tramite_Oficio")
    protected boolean tramiteOficio;
    @XmlElement(name = "Id_Modulo")
    protected int idModulo;
    @XmlElement(name = "Id_Municipal")
    protected int idMunicipal;
    @XmlElement(name = "Fec_Recepcion_Documentos", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date fecRecepcionDocumentos;
    @XmlElement(name = "Estado_Tramite")
    protected int estadoTramite;
    @XmlElement(name = "Tramite_Interno")
    protected boolean tramiteInterno;
    @XmlElement(name = "ID_Tramite_Padre")
    protected int idTramitePadre;
    @XmlElement(name = "Ind_Subio_Documentos")
    protected boolean indSubioDocumentos;
    @XmlElement(name = "Observacion")
    protected String observacion;
    @XmlElement(name = "Numero_Tramite")
    protected String numeroTramite;
    @XmlElement(name = "Usuario")
    protected String usuario;
    protected String identificacion;
    protected String userInterno;
    protected String passInterno;
    protected boolean ingresar2020;

    /**
     * Obtiene el valor de la propiedad idTipoTramite.
     * 
     */
    public int getIdTipoTramite() {
        return idTipoTramite;
    }

    /**
     * Define el valor de la propiedad idTipoTramite.
     * 
     */
    public void setIdTipoTramite(int value) {
        this.idTipoTramite = value;
    }

    /**
     * Obtiene el valor de la propiedad tramiteOficio.
     * 
     */
    public boolean isTramiteOficio() {
        return tramiteOficio;
    }

    /**
     * Define el valor de la propiedad tramiteOficio.
     * 
     */
    public void setTramiteOficio(boolean value) {
        this.tramiteOficio = value;
    }

    /**
     * Obtiene el valor de la propiedad idModulo.
     * 
     */
    public int getIdModulo() {
        return idModulo;
    }

    /**
     * Define el valor de la propiedad idModulo.
     * 
     */
    public void setIdModulo(int value) {
        this.idModulo = value;
    }

    /**
     * Obtiene el valor de la propiedad idMunicipal.
     * 
     */
    public int getIdMunicipal() {
        return idMunicipal;
    }

    /**
     * Define el valor de la propiedad idMunicipal.
     * 
     */
    public void setIdMunicipal(int value) {
        this.idMunicipal = value;
    }

    /**
     * Obtiene el valor de la propiedad fecRecepcionDocumentos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFecRecepcionDocumentos() {
        return fecRecepcionDocumentos;
    }

    /**
     * Define el valor de la propiedad fecRecepcionDocumentos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecRecepcionDocumentos(Date value) {
        this.fecRecepcionDocumentos = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoTramite.
     * 
     */
    public int getEstadoTramite() {
        return estadoTramite;
    }

    /**
     * Define el valor de la propiedad estadoTramite.
     * 
     */
    public void setEstadoTramite(int value) {
        this.estadoTramite = value;
    }

    /**
     * Obtiene el valor de la propiedad tramiteInterno.
     * 
     */
    public boolean isTramiteInterno() {
        return tramiteInterno;
    }

    /**
     * Define el valor de la propiedad tramiteInterno.
     * 
     */
    public void setTramiteInterno(boolean value) {
        this.tramiteInterno = value;
    }

    /**
     * Obtiene el valor de la propiedad idTramitePadre.
     * 
     */
    public int getIDTramitePadre() {
        return idTramitePadre;
    }

    /**
     * Define el valor de la propiedad idTramitePadre.
     * 
     */
    public void setIDTramitePadre(int value) {
        this.idTramitePadre = value;
    }

    /**
     * Obtiene el valor de la propiedad indSubioDocumentos.
     * 
     */
    public boolean isIndSubioDocumentos() {
        return indSubioDocumentos;
    }

    /**
     * Define el valor de la propiedad indSubioDocumentos.
     * 
     */
    public void setIndSubioDocumentos(boolean value) {
        this.indSubioDocumentos = value;
    }

    /**
     * Obtiene el valor de la propiedad observacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Define el valor de la propiedad observacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroTramite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTramite() {
        return numeroTramite;
    }

    /**
     * Define el valor de la propiedad numeroTramite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTramite(String value) {
        this.numeroTramite = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad identificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Define el valor de la propiedad identificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificacion(String value) {
        this.identificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad userInterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserInterno() {
        return userInterno;
    }

    /**
     * Define el valor de la propiedad userInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserInterno(String value) {
        this.userInterno = value;
    }

    /**
     * Obtiene el valor de la propiedad passInterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassInterno() {
        return passInterno;
    }

    /**
     * Define el valor de la propiedad passInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassInterno(String value) {
        this.passInterno = value;
    }

    /**
     * Obtiene el valor de la propiedad ingresar2020.
     * 
     */
    public boolean isIngresar2020() {
        return ingresar2020;
    }

    /**
     * Define el valor de la propiedad ingresar2020.
     * 
     */
    public void setIngresar2020(boolean value) {
        this.ingresar2020 = value;
    }

}


package com.gbsys.exotra.seguridad;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="userInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="passInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idCarpeta" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="numeroTramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "userInterno",
    "passInterno",
    "idCarpeta",
    "documento",
    "numeroTramite",
    "extencion"
})
@XmlRootElement(name = "insercionDocumentos")
public class InsercionDocumentos
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected String userInterno;
    protected String passInterno;
    protected int idCarpeta;
    protected byte[] documento;
    protected String numeroTramite;
    protected String extencion;

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
     * Obtiene el valor de la propiedad idCarpeta.
     * 
     */
    public int getIdCarpeta() {
        return idCarpeta;
    }

    /**
     * Define el valor de la propiedad idCarpeta.
     * 
     */
    public void setIdCarpeta(int value) {
        this.idCarpeta = value;
    }

    /**
     * Obtiene el valor de la propiedad documento.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumento() {
        return documento;
    }

    /**
     * Define el valor de la propiedad documento.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumento(byte[] value) {
        this.documento = value;
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
     * Obtiene el valor de la propiedad extencion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtencion() {
        return extencion;
    }

    /**
     * Define el valor de la propiedad extencion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtencion(String value) {
        this.extencion = value;
    }

}

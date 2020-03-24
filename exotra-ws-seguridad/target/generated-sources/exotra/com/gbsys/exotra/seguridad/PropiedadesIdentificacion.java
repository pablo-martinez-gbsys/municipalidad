
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
 *         &lt;element name="identificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "identificacion"
})
@XmlRootElement(name = "propiedadesIdentificacion")
public class PropiedadesIdentificacion
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected String userInterno;
    protected String passInterno;
    protected String identificacion;

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

}

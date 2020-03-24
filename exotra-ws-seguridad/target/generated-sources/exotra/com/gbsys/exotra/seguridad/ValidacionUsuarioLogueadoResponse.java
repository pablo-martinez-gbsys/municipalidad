
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
 *         &lt;element name="validacionUsuarioLogueadoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "validacionUsuarioLogueadoResult"
})
@XmlRootElement(name = "validacionUsuarioLogueadoResponse")
public class ValidacionUsuarioLogueadoResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected String validacionUsuarioLogueadoResult;

    /**
     * Obtiene el valor de la propiedad validacionUsuarioLogueadoResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidacionUsuarioLogueadoResult() {
        return validacionUsuarioLogueadoResult;
    }

    /**
     * Define el valor de la propiedad validacionUsuarioLogueadoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidacionUsuarioLogueadoResult(String value) {
        this.validacionUsuarioLogueadoResult = value;
    }

}

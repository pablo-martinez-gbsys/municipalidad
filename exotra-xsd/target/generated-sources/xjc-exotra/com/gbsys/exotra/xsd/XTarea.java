//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.03.13 a las 03:20:13 PM CST 
//


package com.gbsys.exotra.xsd;

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
 * <p>Clase Java para XTarea complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="XTarea"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="DscTarea" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FecTarea" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="EstTarea" type="{}XEstadoTarea"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XTarea", propOrder = {
    "id",
    "dscTarea",
    "fecTarea",
    "estTarea"
})
@XmlRootElement(name = "Tarea")
public class XTarea
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Id")
    protected Long id;
    @XmlElement(name = "DscTarea", required = true)
    protected String dscTarea;
    @XmlElement(name = "FecTarea", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date fecTarea;
    @XmlElement(name = "EstTarea", required = true)
    @XmlSchemaType(name = "string")
    protected XEstadoTarea estTarea;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad dscTarea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDscTarea() {
        return dscTarea;
    }

    /**
     * Define el valor de la propiedad dscTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDscTarea(String value) {
        this.dscTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad fecTarea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFecTarea() {
        return fecTarea;
    }

    /**
     * Define el valor de la propiedad fecTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecTarea(Date value) {
        this.fecTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad estTarea.
     * 
     * @return
     *     possible object is
     *     {@link XEstadoTarea }
     *     
     */
    public XEstadoTarea getEstTarea() {
        return estTarea;
    }

    /**
     * Define el valor de la propiedad estTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link XEstadoTarea }
     *     
     */
    public void setEstTarea(XEstadoTarea value) {
        this.estTarea = value;
    }

}

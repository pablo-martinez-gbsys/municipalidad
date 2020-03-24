//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.03.13 a las 03:20:13 PM CST 
//


package com.gbsys.exotra.xsd;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (com.gbsys.exotra.xsd.DateTimeConverter.parseDate(value));
    }

    public String marshal(Date value) {
        return (com.gbsys.exotra.xsd.DateTimeConverter.printDate(value));
    }

}

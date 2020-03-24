package com.gbsys.exotra.xsd;

import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

/**
 * Clase encargada de convertir las fechas de los servicios web a Date.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 30/05/2017 15:47
 */
public class DateTimeConverter {

    /**
     * Convierte de String a Date.
     *
     * @param xmlDateTime fecha como String
     * @return fecha como Date
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 30/05/2017 15:47
     */
    public static Date parseDateTime(String xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        return DatatypeConverter.parseDateTime(xmlDateTime).getTime();
    }

    /**
     * Convierte de Date a String.
     *
     * @param javaDateTime fecha como Date
     * @return fecha como String
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 30/05/2017 15:47
     */
    public static String printDateTime(Date javaDateTime) {
        if (javaDateTime == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(javaDateTime);
        return DatatypeConverter.printDateTime(cal);
    }

    /**
     * Convierte de String a Date.
     *
     * @param xmlDateTime fecha como String
     * @return fecha como Date
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/07/2017 09:38
     */
    public static Date parseDate(String xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        return DatatypeConverter.parseDate(xmlDateTime).getTime();
    }

    /**
     * Convierte de Date a String.
     *
     * @param javaDateTime fecha como Date
     * @return fecha como String
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/07/2017 09:38
     */
    public static String printDate(Date javaDateTime) {
        if (javaDateTime == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(javaDateTime);
        return DatatypeConverter.printDate(cal);
    }

    /**
     * Convierte de String a Date.
     *
     * @param xmlDateTime fecha como String
     * @return fecha como Date
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/07/2017 09:38
     */
    public static Date parseTime(String xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        return DatatypeConverter.parseTime(xmlDateTime).getTime();
    }

    /**
     * Convierte de Date a String.
     *
     * @param javaDateTime fecha como Date
     * @return fecha como String
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/07/2017 09:38
     */
    public static String printTime(Date javaDateTime) {
        if (javaDateTime == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(javaDateTime);
        return DatatypeConverter.printTime(cal);
    }

}

package com.gbsys.exotra.xsd;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase encargada de convertir las fechas de los servicios web a Date.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 23/05/2018 14:47
 */
public class DateTimeConverter8 {

    /**
     * Convierte de String a Date.
     *
     * @param xmlDateTime fecha como String
     * @return fecha como Date
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 30/05/2017 15:47
     */
    public static LocalDateTime parseDateTime(String xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        return LocalDateTime.parse(xmlDateTime);
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
    public static String printDateTime(LocalDateTime javaDateTime) {
        if (javaDateTime == null) {
            return null;
        }
        return javaDateTime.toString();
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
    public static LocalDate parseDate(String xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        return LocalDate.parse(xmlDateTime);
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
    public static String printDate(LocalDate javaDateTime) {
        if (javaDateTime == null) {
            return null;
        }
        return javaDateTime.toString();
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
    public static LocalTime parseTime(String xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        return LocalTime.parse(xmlDateTime);
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
    public static String printTime(LocalTime javaDateTime) {
        if (javaDateTime == null) {
            return null;
        }
        return javaDateTime.toString();
    }

}

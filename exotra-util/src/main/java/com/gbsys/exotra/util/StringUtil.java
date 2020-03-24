package com.gbsys.exotra.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import static java.util.Collections.emptyList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilitario encargado de realizar operaciones sobre los Strings.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 18/11/2017 16:51
 */
@Slf4j
@UtilityClass
public class StringUtil {

    /**
     * Patrón para validar si un String es una dirección de correo valida.
     *
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 02/05/2017 13:29
     */
    private static final String PATRON_CORREO = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String PATRON_INTEGER = "#,##0";
    private static final String PATRON_DOUBLE = "#,##0.00000";
    private static final String PATRON_COLON = "'¢' #,##0.00";
    private static final char SEPARADOR = '-';

    /**
     * Realiza un substring a un String, validando que no sea null ni que sea
     * más corto que el largo especificado.
     *
     * @param texto Texto al que se le tiene que hacer substring.
     * @param largo Longitud del substring.
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 02/05/2017 11:08
     * @return Substring del texto.
     */
    public static String subString(String texto, int largo) {
        if (texto != null && texto.length() >= largo) {
            return texto.substring(0, largo);
        }
        return texto;
    }

    /**
     * Convierte un String a una lista de String, separando el texto original
     * por comas.
     *
     * @param texto Texto original.
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 02/05/2017 11:44
     * @return Lista de String separada por coma.
     */
    public static List<String> separarPorComa(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return emptyList();
        }
        return Arrays.asList(texto.replaceAll("\n", ",").split("\\s*,+\\s*"));
    }

    /**
     * Valida si un String corresponde a una dirección de correo valida.
     *
     * @param correo Dirección de correo a validar
     * @version 1..0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 08/05/2017 13:29
     * @return true si es una dirección de correo valida, false en caso
     * contrario.
     */
    public static boolean esCorreo(String correo) {
        try {
            Pattern pattern
                    = Pattern.compile(PATRON_CORREO, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(correo);
            return matcher.find();
        } catch (Exception ex) {
            log.error("StringUtil.esCorreo({})", correo, ex);
            return false;
        }
    }

    /**
     * Remueve los acentos y los remplaza por la letra correspondiente sin
     * acento.
     *
     * @param texto Texto a cambiar
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 28/07/2017 13:29
     * @return Texto sin acentos
     */
    public static String removerAcentos(@NonNull String texto) {
        return texto
                .replaceAll("[ÀÁÂÄÃÅÆàáâãäåæ]", "A")
                .replaceAll("[ÈÉÊËèéêë]", "E")
                .replaceAll("[ÌÍÎÏìíîïĩ]", "I")
                .replaceAll("[ÒÓÔÖÕØòóôöõøð]", "O")
                .replaceAll("[ÙÚÛÜùúûü]", "U")
                .replaceAll("[Çç]", "C")
                .replaceAll("[Ð]", "D")
                .replaceAll("[ß]", "B")
                .replaceAll("[ýÝ]", "Y")
                .toUpperCase();
    }

    /**
     * Remueve los acentos y los caracteres especiales, solo deja los siguientes
     * caracteres: "'., -".
     *
     * @param texto Texto a remover caracteres especiales
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 28/07/2017 13:29
     * @return Texto sin caracteres especiales
     */
    public static String removerAcentosCaracteresEspeciales(@NonNull String texto) {
        return removerCaracteresEspeciales(removerAcentos(texto));
    }

    /**
     * Remueve los caracteres especiales, solo deja los siguientes caracteres:
     * "'., -".
     *
     * @param texto Texto a remover caracteres especiales
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 07/08/2017 08:29
     * @return Texto sin caracteres especiales
     */
    public static String removerCaracteresEspeciales(@NonNull String texto) {
        return texto
                .replaceAll("[´`^]", "'")
                .replaceAll("[^A-Za-z0-9ÑñÁáÉéÍíÓóÚú'., -]+", "");
    }

    /**
     * Obtiene el formateador de números a String.
     *
     * @param formato Formato a utilizar
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return Objeto para formatear números a String
     */
    private static DecimalFormat obtenerFormateadorNumerico(String formato) {
        Locale locale = new Locale("es", "CR");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        return new DecimalFormat(formato, symbols);
    }

    /**
     * Convierte un número a String con un formato.
     *
     * @param xdouble número a convertir
     * @param patron patrón a utilizar
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return número formateado a String
     */
    public static String numeroAString(Double xdouble, String patron) {
        DecimalFormat formateador = obtenerFormateadorNumerico(patron);
        return formateador.format(xdouble);
    }

    /**
     * Convierte un número a String con un formato de colones.
     *
     * @param xlong número a convertir
     * @param patron patrón a utilizar
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return número formateado a String
     */
    public static String numeroAString(Long xlong, String patron) {
        DecimalFormat formateador = obtenerFormateadorNumerico(patron);
        return formateador.format(xlong);
    }

    /**
     * Convierte un número a String.
     *
     * @param xdouble número a convertir
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return número formateado a String
     */
    public static String numeroAString(Double xdouble) {
        return numeroAString(xdouble, PATRON_DOUBLE);
    }

    /**
     * Convierte un número a String.
     *
     * @param xlong número a convertir
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return número formateado a String
     */
    public static String numeroAString(Long xlong) {
        return numeroAString(xlong, PATRON_DOUBLE);
    }

    /**
     * Convierte un número a String con un formato de colones.
     *
     * @param xdouble número a convertir
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return número formateado a String
     */
    public static String numeroAStringColon(Double xdouble) {
        return numeroAString(xdouble, PATRON_COLON);
    }

    /**
     * Convierte un número a String con un formato de colones.
     *
     * @param xlong número a convertir
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 21/08/2017 17:08
     * @return número formateado a String
     */
    public static String numeroAStringColon(Long xlong) {
        return numeroAString(xlong, PATRON_COLON);
    }

    public static String obtenerIdentificadorUnico() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
    }

    /**
     * Obtiene el trim de un valor String
     *
     * @param valor valor
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Katherine Ledezma
     * @date 21/08/2017 17:08
     * @return null o String
     */
    public static String trim(String valor) {
        if (valor == null) {
            return null;
        } else {
            return valor.trim();
        }
    }

    /**
     * Obtiene el uppercase de un valor String
     *
     * @param valor valor
     * @return null o String
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Katherine Ledezma
     * @version 1.0.0
     * @date 21/08/2017 17:08
     */
    public static String toUpperCase(String valor) {
        if (valor == null) {
            return null;
        } else {
            return valor.toUpperCase();
        }
    }

    /**
     * Obtiene el lowercase de un valor String
     *
     * @param valor valor
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Katherine Ledezma
     * @date 21/08/2017 17:08
     * @return null o String
     */
    public static String toLowerCase(String valor) {
        if (valor == null) {
            return null;
        } else {
            return valor.toLowerCase();
        }
    }

    /**
     * Agrega una máscara de caracteres a la izquierda de un String.
     *
     * @param cadena String base.
     * @param largo Cantidad de caracteres de la máscara.
     * @param token Caracter de relleno de la máscara.
     * @return String String con la máscara.
     *
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Jordan Espinoza C.
     * @date 12/06/2018 17:08
     */
    public static String completarConTokenIzq(String cadena, int largo, String token) {
        if (token == null) {
            return cadena;
        }
        if (cadena.length() >= largo) {
            return cadena;
        }
        StringBuilder sb = new StringBuilder(cadena);
        while (sb.length() < largo) {
            sb.insert(0, token);
        }
        return sb.toString();
    }
}

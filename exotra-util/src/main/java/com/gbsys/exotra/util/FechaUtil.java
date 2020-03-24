package com.gbsys.exotra.util;

import com.gbsys.exotra.exception.UtilException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de contener varios métodos utilitarios de fechas, que pueden
 * utilizarse en la aplicación.
 *
 * @version 1.0.0
 * @author Adam M. Gamboa Gonzalez
 * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
 * @date 21/08/2014
 */
@Slf4j
@UtilityClass
public class FechaUtil {

    /**
     * Formato predeterminado para la fecha.
     */
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    /**
     * Formato predeterminado para la hora.
     */
    public static final String FORMATO_HORA = "hh:mm:ss a";
    /**
     * Formato predeterminado para la fecha y hora.
     */
    public static final String FORMATO_FECHA_HORA
            = FORMATO_FECHA + " " + FORMATO_HORA;
    /**
     * Fecha nula en la Base de Datos.
     */
    public static final long FECHA_0001 = -62135748000000L;

    /**
     * Formato predeterminado para la fecha.
     */
    public static final String FORMATO_FECHA_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * Metodo encargado de retornar la fecha actual del servidor
     *
     * @return Fecha actual
     */
    public static Date obtenerFechaActual() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Deja el tiempo en cero, retorna solo la fecha
     *
     * @param fecha Fecha a truncar
     * @version 1.0.0
     * @author Adam M. Gamboa Gonzalez
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Fecha truncada
     */
    public static Date truncFecha(Date fecha) {
        if (fecha == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Obtiene el tiempo (hora, minuto, segundo) máximo de la fecha indicada.
     *
     * @param fecha fecha a agregar el máximo tiempo
     * @return tiempo máximo de la fecha indicada
     *
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 06/08/2018 11:37
     */
    public static Date tiempoMaximo(Date fecha) {
        if (fecha == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Metodo encargado de obtener y regresar el anno de la fecha actual del
     * sistema.
     *
     * @version 1.0.0
     * @author Adam M. Gamboa Gonzalez
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Anno actual
     */
    public static Integer obtenerAnoActual() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * Metodo encargado de obtener el dia del mes, de la fecha recibida como
     * parametro.
     *
     * @param fecha Fecha a la cual se desea obtener el dia del mes.
     * @version 1.0.0
     * @author Adam M. Gamboa Gonzalez
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Dia del mes
     */
    public static int obtenerDiaDelMes(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Metodo encargado de obtener el Mes de una fecha. El mes lo retorna como
     * un numero indicando el numero del mes. Los valores retornados van de 1
     * hasta 12, donde cada numero es el respectivo mes.
     *
     * @param fecha Fecha a obtener el mes.
     * @version 1.0.0
     * @author Adam M. Gamboa Gonzalez
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Mes de la fecha
     */
    public static int obtenerMesDelAnno(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * Metodo encargado de obtener el año de un objeto Date, con una fecha
     * especifica.
     *
     * @param fecha Fecha a obtener año.
     * @version 1.0.0
     * @author Adam M. Gamboa Gonzalez
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Año de la fecha
     */
    public static int obtenerAnno(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.YEAR);
    }

    /**
     * Convierte a String un Date con el formato dd/MM/yyyy.
     *
     * @param fecha Fecha a convertir
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 30/03/2017 13:30
     * @return Fecha convertida a String con el formato dd/MM/yyyy
     */
    public static String convertirFecha(Date fecha) {
        return convertirFecha(fecha, FORMATO_FECHA);
    }

    /**
     * Convierte a String un Date con el formato HH:mm:ss.
     *
     * @param fecha Fecha a convertir
     * @version 1.0.0
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @date 14/08/2017 08:30
     * @return Fecha convertida a String con el formato HH:mm:ss
     */
    public static String convertirHora(Date fecha) {
        return convertirFecha(fecha, FORMATO_HORA);
    }

    /**
     * Formatea un Date a un String con el formato especifico. ejemplo de un
     * Date a un -> "10-09-2010""
     *
     * @param fecha java.util.Date
     * @param formato String
     * @version 1.0.0
     * @author wPorras
     * @date 2011/08/30
     * @return fechaNueva String
     */
    public static String convertirFecha(Date fecha, String formato) {
        Locale cr = new Locale("es", "CR");
        SimpleDateFormat formatoSdf = new SimpleDateFormat(formato, cr);
        String fechaNueva = formatoSdf.format(fecha);
        return fechaNueva;
    }

    public static Date formatearFecha(Date fecha, String formato) {
        try {
            Locale cr = new Locale("es", "CR");
            SimpleDateFormat formatoSdf = new SimpleDateFormat(formato, cr);
            String fechaNueva = formatoSdf.format(fecha);
            Date fechaNueva2 = formatoSdf.parse(fechaNueva);
            return fechaNueva2;
        } catch (Exception ex) {
            log.error("FechaUtil.formatearFecha({}, {})", fecha, formato, ex);
            throw new UtilException("No se pudo obtener la fecha", ex);
        }
    }

    public static Date obtenerFecha(String fecha, String formato) {
        try {
            Locale cr = new Locale("es", "CR");
            SimpleDateFormat format = new SimpleDateFormat(formato, cr);
            Date fechaNueva = format.parse(fecha);
            return fechaNueva;
        } catch (Exception ex) {
            log.error("FechaUtil.obtenerFecha({}, {})", fecha, formato, ex);
            throw new UtilException("No se pudo obtener la fecha", ex);
        }
    }

    /**
     * Método retorna la fecha defecto 01/01/1970.
     *
     * @version 1.0.0
     * @author Adam M. Gamboa Gonzalez
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return fecha defecto 01/01/1970
     */
    public static Date fechaDefecto1970() {
        try {
            return FechaUtil.obtenerFecha("01/01/1970", FORMATO_FECHA);
        } catch (Exception ex) {
            log.error("FechaUtil.fechaDefecto1970()", ex);
            throw new UtilException(ex);
        }
    }

    /**
     * Valida si la fecha es 01/01/1970 12:00:00 a.m.
     *
     * @param date fecha a validar
     * @version 1.0.0
     * @author GBSYS.Diseñado y Desarrollado por:Ing. Oscar González Pérez
     * @date 24/07/2017 12:00
     * @return true si la fecha es igual a 01/01/1970 12:00:00 a.m., false en
     * caso contrario
     */
    public static boolean isFechaDefecto1970(Date date) {
        return fechaDefecto1970().compareTo(date) == 0;
    }

    /**
     * Valida si la fecha es 01/01/0001 12:00:00 a.m.
     *
     * @param date fecha a validar
     * @version 1.0.0
     * @author GBSYS.Diseñado y Desarrollado por: Ing. Jordan Espinoza C.
     * @date 09/08/2017 12:00
     * @return true si la fecha es igual a 01/01/0001 12:00:00 a.m., false en
     * caso contrario
     *
     */
    public static boolean isFecha0001(Date date) {
        if (date == null) {
            return false;
        } else {
            return date.getTime() == FECHA_0001;
        }
    }

    /**
     * Suma/resta días a la fecha.
     *
     * @param fecha Fecha a sumar/restar
     * @param dias Días a sumar/restar
     * @version 1.0.0
     * @author GBSYS.Diseñado y Desarrollado por: Jordan Espinoza C.
     * @date 03/05/2018
     * @return Fecha con los días sumados/restados.
     */
    public static Date sumarRestarDias(Date fecha, Integer dias) {
        if (fecha == null || dias == null || dias.equals(0)) {
            return fecha;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();

    }

    /**
     * Obtiene el primer día del mes de la fecha indicada.
     *
     * @param fecha fecha a obtener primer día
     * @return primer día del mes de la fecha indicada
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/08/2019 14:52
     */
    public static Date obtenerPrimerDiaMes(Date fecha) {
        if (fecha == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Obtiene el último día del mes de la fecha indicada.
     *
     * @param fecha fecha a obtener último día
     * @return último día del mes de la fecha indicada
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/08/2019 14:52
     */
    public static Date obtenerUltimoDiaMes(Date fecha) {
        if (fecha == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Obtiene el primer día del mes de la fecha actual.
     *
     * @return primer día del mes de la fecha actual
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/08/2019 14:52
     */
    public static Date obtenerPrimerDiaMes() {
        return obtenerPrimerDiaMes(new Date());
    }

    /**
     * Obtiene el último día del mes de la fecha actual.
     *
     * @return último día del mes de la fecha actual
     *
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 07/08/2019 14:52
     */
    public static Date obtenerUltimoDiaMes() {
        return obtenerUltimoDiaMes(new Date());
    }

}

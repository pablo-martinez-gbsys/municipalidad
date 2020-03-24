package com.gbsys.exotra.util;

import com.gbsys.exotra.exception.UtilException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de obtener las etiquetas del recurso de mensajes.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 18/11/2017 16:51
 */
@Slf4j
@UtilityClass
public class EtiquetasUtil {

    /**
     * Definición de la ruta del archivo de propiedades, donde están definidas
     * las etiquetas.
     */
    private static final String ARCHIVO_ETIQUETA = "com.gbsys.exotra.etiquetas";
    /**
     * Definición de la ruta del archivo de propiedades, donde están definidos
     * los mensajes.
     */
    private static final String ARCHIVO_MENSAJE = "com.gbsys.exotra.mensajes";

    /**
     * Definición de variables para el manejo de las propiedades por archivo
     */
    private static ResourceBundle recursoEtiquetas = null;
    private static ResourceBundle recursoMensajes = null;

    /**
     * Obtiene una propiedad identificada con la llave enviada como parámetros,
     * para obtener un valor del archivo de etiquetas.
     *
     * @param key Identificador de la etiqueta
     * @version 1.0.1
     * @author Adam M. Gamboa González
     * @date 05 de Marzo de 2011
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Valor de la etiqueta
     */
    public static String obtenerEtiqueta(String key) {
        try {
            if (recursoEtiquetas == null) {
                recursoEtiquetas = ResourceBundle.getBundle(
                        ARCHIVO_ETIQUETA,
                        FacesContext.getCurrentInstance().getViewRoot().getLocale());
            }
            return recursoEtiquetas.containsKey(key)
                    ? recursoEtiquetas.getString(key)
                    : key;
        } catch (Exception ex) {
            log.error("EtiquetasUtil.obtenerEtiqueta({})", key, ex);
            throw new UtilException("No se puede obtener la etiqueta", ex);
        }
    }

    /**
     * Retorna el mensaje internacionalizado para el key enviado, aplicando los
     * parametros que
     *
     * @param key java.lang.String llave del valor
     * @param argumentos java.lang.Object [] argumentos para aplicar al mensaje
     * @version 1.0.1
     * @author Adam M. Gamboa González
     * @date 05 de Marzo de 2011
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return java.lang.String mensaje internacionalizado
     */
    public static String obtenerEtiqueta(String key, Object... argumentos) {
        return MessageFormat.format(obtenerEtiqueta(key), argumentos);
    }

    /**
     * Obtiene una propiedad identificada con la llave enviada como parametros,
     * para obtener un valor del archivo de mensajes.
     *
     * @param key Identificador del mensaje
     * @version 1.0.1
     * @author Adam M. Gamboa González
     * @date 05 de Marzo de 2011
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return Valor del mensaje
     */
    public static String obtenerMensaje(String key) {
        try {
            if (recursoMensajes == null) {
                recursoMensajes = ResourceBundle.getBundle(
                        ARCHIVO_MENSAJE,
                        FacesContext.getCurrentInstance().getViewRoot().getLocale());
            }
            return recursoMensajes.containsKey(key)
                    ? recursoMensajes.getString(key)
                    : key;
        } catch (Exception ex) {
            log.error("EtiquetasUtil.obtenerMensaje({})", key, ex);
            throw new UtilException("No se puede obtener el mensaje", ex);
        }
    }

    /**
     * Retorna el mensaje internacionalizado para el key enviado, aplicando los
     * parametros que
     *
     * @param key java.lang.String llave del valor
     * @param argumentos java.lang.Object [] argumentos para aplicar al mensaje
     * @version 1.0.1
     * @author Adam M. Gamboa González
     * @date 05 de Marzo de 2011
     * @author Consorcio Siansa-Indra. Modificado por: Lsanchez
     * @date 21/08/2014
     * @return java.lang.String mensaje internacionalizado
     */
    public static String obtenerMensaje(String key, Object... argumentos) {
        return MessageFormat.format(obtenerMensaje(key), argumentos);
    }

}

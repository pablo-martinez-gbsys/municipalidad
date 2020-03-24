package com.gbsys.exotra.util;

import com.gbsys.exotra.exception.UtilException;
import java.io.InputStream;
import java.util.Properties;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Lee el archivos indicado en la ubicación recibida como parámetro.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 18/11/2017 16:51
 */
@Slf4j
@UtilityClass
public class LecturaArchivoUtil {

    private static final String MENSAJE_ERROR = "No se puede cargar archivo indicado";

    /**
     * Lee el archivo indicado en la ubicación recibida como parámetro. La
     * ubicación debe ir separada por el caracteres "/" en cada carpeta o
     * paquete. Debe colocarse la extensión del archivo.
     *
     * @param url Url del archivo a leer
     * @version 1.0.0
     * @author Consorcio Siansa-Indra.
     * @author yarias
     * @date 21/08/2014
     * @return InputStream del archivo leído. Si no hay leído el archivo retorna
     */
    public static InputStream cargarArchivo(String url) {
        try {
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
        } catch (Exception ex) {
            log.error("LecturaArchivoUtil.cargarArchivo({})", url, ex);
            throw new UtilException(MENSAJE_ERROR, ex);
        }
    }

    /**
     * Lee de un archivo properties en la ubicación recibida como parámetro. La
     * ubicación debe ir separada por el caracteres "/" en cada carpeta o
     * paquete. Debe colocarse la extensión del archivo.
     *
     * @param url Url del archivo a leer
     * @version 1.0.0
     * @author Consorcio Siansa-Indra.
     * @author yarias
     * @date 21/08/2014
     * @return Properties del archivo leido
     */
    public static Properties cargarProperties(String url) {
        try {
            InputStream is = cargarArchivo(url);
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (Exception ex) {
            log.error("LecturaArchivoUtil.cargarProperties({})", url, ex);
            throw new UtilException(MENSAJE_ERROR, ex);
        }
    }
}

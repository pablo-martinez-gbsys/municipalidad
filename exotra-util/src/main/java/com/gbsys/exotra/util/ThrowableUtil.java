package com.gbsys.exotra.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.joining;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utilitario encargado de manejar todo los relacionado a Throwables.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 02/05/2017 12:03
 */
@UtilityClass
public class ThrowableUtil {

    /**
     * Obtiene la causa final de un Throwable.
     *
     * @param ex Throwable original
     * @return Throwable causante
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 02/05/2017 12:03
     */
    public static Throwable obtenerCausa(@NonNull Throwable ex) {
        Throwable causa = ex;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }
        return causa;
    }

    /**
     * Obtiene la lista de mensajes que causan la Exception.
     *
     * @param ex Throwable original
     * @return Lista de mensajes
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 12/05/2017 06:03
     */
    public static List<String> obtenerMensajes(@NonNull Throwable ex) {
        List<String> mensajes = new ArrayList<>();
        Throwable causa = ex;
        mensajes.add(causa.getMessage());
        while (causa.getCause() != null) {
            causa = causa.getCause();
            mensajes.add(causa.getMessage());
        }
        return mensajes;
    }

    /**
     * Obtiene la lista de mensajes en un único String separado por cambio de
     * línea que causan la Exception.
     *
     * @param ex Throwable original
     * @return Lista de mensajes
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 12/05/2017 06:03
     */
    public static String obtenerConjuntoMensajes(@NonNull Throwable ex) {
        return obtenerMensajes(ex).stream().collect(joining("\n"));
    }

    /**
     * Obtiene el mensaje del StackTrace.
     *
     * @param ex Throwable original
     * @return String con el mensaje del StackTrace
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 12/05/2017 06:03
     */
    public static String obtenerStackTrace(@NonNull Throwable ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}

package com.gbsys.exotra.util;

import java.lang.reflect.Parameter;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Interceptor encargado de escribir en el log si ocurre un error en el
 * servicio.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 30/05/2018 07:06
 */
@Slf4j(topic = "servicio")
@ServicioLogger
@Interceptor
public class ServicioLoggerInterceptor {

    /**
     * Método invocado por el interceptor para imprimir los errores en el log.
     *
     * @param context Contexto de CDI.
     * @return Resultado del método invocado
     * @throws Exception Exception original en caso de error en el método
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 30/05/2018 07:06
     */
    @AroundInvoke
    public Object escribirLogger(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (Throwable th) {
            log.error(mensaje(context), th);
            throw th;
        }
    }

    /**
     * Concatena el mensaje a mostrar en el log.
     *
     * @param context Contexto de CDI
     * @return Mensaje a mostrar en el log
     * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
     * @version 1.0.0
     * @date 30/05/2018 07:06
     */
    private String mensaje(InvocationContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\nClase: ");
        sb.append(context.getTarget().getClass().getName());
        sb.append("\r\nMétodo: ");
        sb.append(context.getMethod().getName());
        Parameter[] parametros = context.getMethod().getParameters();
        Object[] valores = context.getParameters();
        sb.append("\r\nParámetros: ");
        for (int i = 0; i < parametros.length; i++) {
            sb.append("\r\n-> ");
            sb.append(parametros[i].getName());
            sb.append(": ");
            sb.append(valores[i]);
        }
        sb.append("\r\n");
        return sb.toString();
    }
}

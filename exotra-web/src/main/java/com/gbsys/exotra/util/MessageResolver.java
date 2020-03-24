package com.gbsys.exotra.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 * Resuelve cual va ser el archivo de mensajes y como mostrar los mensajes en
 * JSF.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 30/05/2018 10:52
 */
@WebListener
public class MessageResolver implements ServletContextListener {

    private static final String BASE_NAME = "com.gbsys.exotra.mensajes";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Messages.setResolver((message, params) -> {
            ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, Faces.getLocale());
            if (bundle.containsKey(message)) {
                message = bundle.getString(message);
            }
            return params.length > 0 ? MessageFormat.format(message, params) : message;
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}

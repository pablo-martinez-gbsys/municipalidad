package com.gbsys.exotra.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase Utilitaria, que contiene los métodos para realizar el despliegue de
 * errores en la aplicación. Contiene procedimientos para cada uno de los
 * posibles distintos tipos de mensajes a desplegar ERROR, INFO, WARN, FATAL,
 * además de los distintos lugar en donde mostrar dichos mensajes, Globalmente o
 * el mensaje va a un componente en específico.
 *
 * @version 1.0.0
 * @author Adam M. Gamboa González
 * @author Consorcio Siansa-Indra.
 * @authorLsanchez
 * @date 21/08/2014
 * @deprecated Se prefiere el uso de Messages de Omnifaces
 */
@Slf4j
@UtilityClass
@Deprecated
public class MensajeUtil {

    /**
     * Agrega un mensaje de tipo ERROR, a la cola de faces, para ser mostrado
     * por alguno de los componentes &lt; p:messages/> en la pantalla.
     *
     * @param msg Mensaje de error a desplegar.
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeError(String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Agrega un mensaje de tipo INFORMACION, a la cola de faces, para ser
     * mostrado por alguno de los componentes &lt;p:messages/> en la pantalla.
     *
     * @param msg Mensaje de informativo a desplegar.
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeInfo(String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Agrega un mensaje de tipo Advertencia, a la cola de faces, para ser
     * mostrado por alguno de los componentes &lt;p:messages/> en la pantalla.
     *
     * @param msg Mensaje de advertencia a desplegar.
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeAdvertencia(String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_WARN);
    }

    /**
     * Agrega un mensaje de tipo FATAL, a la cola de faces, para ser mostrado
     * por alguno de los componentes &lt;p :messages /> en la pantalla.
     *
     * @param msg Mensaje de error fatal a desplegar.
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeFatal(String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_FATAL);
    }

    /**
     * Agrega un mensaje de error por componente
     *
     * @param componente Id del componente a mostrar mensaje
     * @param msg Mensaje a mostrar
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeErrorComponente(String componente, String msg) {
        agregarMensaje(componente, msg, FacesMessage.SEVERITY_ERROR);
        UIInput uii = (UIInput) JSFUtil.obtenerComponente(componente);
        if (uii != null) {
            uii.setValid(false);
        }
    }

    /**
     * Activa el color de un componentes especifico
     *
     * @param componente Componente a colocar Focus
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarFocusComponente(String componente) {
        UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
        if (uui != null) {
            uui.setValid(false);
        }
    }

    /**
     * Agrega un mensaje de error por componente
     *
     * @param componentes String[]
     * @param msg String
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeErrorComponente(String componentes[], String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_ERROR);
        if (componentes != null) {
            for (String componente : componentes) {
                if (componente != null) {
                    UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
                    if (uui != null) {
                        uui.setValid(false);
                    }
                }
            }
        }
    }

    /**
     * Agrega de un mensaje de información
     *
     * @param componente String Id del componente a mostrar mensaje
     * @param msg String Mensaje a mostrar
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeInfoComponente(String componente, String msg) {
        agregarMensaje(componente, msg, FacesMessage.SEVERITY_INFO);
        UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
        if (uui != null) {
            uui.setValid(true);
        }
    }

    /**
     * Agrega de un mensaje de advertencia
     *
     * @param componente String Id del mensaje a mostrar
     * @param msg String Mensaje a mostrar
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeAdvertenciaComponente(String componente, String msg) {
        agregarMensaje(componente, msg, FacesMessage.SEVERITY_WARN);
        UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
        if (uui != null) {
            uui.setValid(false);
        }
    }

    /**
     * Agrega de un mensaje de advertencia a un componente
     *
     * @param componentes String[] Listados de id, de los componentes a mostrar
     * mensaje
     * @param msg String Mensaje a mostrar
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeAdvertenciaComponente(String componentes[], String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_WARN);
        if (componentes != null) {
            for (String componente : componentes) {
                UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
                if (uui != null) {
                    uui.setValid(false);
                }
            }
        }
    }

    /**
     * Agrega de un mensaje fatal a un componente
     *
     * @param componente String Id del componente a mostrar mensaje
     * @param msg String Mensaje a mostrar
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeFatalComponente(String componente, String msg) {
        agregarMensaje(componente, msg, FacesMessage.SEVERITY_FATAL);
        UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
        if (uui != null) {
            uui.setValid(true);
        }
    }

    /**
     * Agrega de un mensaje fatal a un componente
     *
     * @param componentes String[] Listado de componentes a agregar mensaje
     * @param msg String Mensaje a mostrar
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void agregarMensajeFatalComponente(String componentes[], String msg) {
        agregarMensaje(null, msg, FacesMessage.SEVERITY_FATAL);
        if (componentes != null) {
            for (String componente : componentes) {
                UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
                if (uui != null) {
                    uui.setValid(true);
                }
            }
        }
    }

    /**
     * Utiliza el FacesContex para desplegar un mensaje en un objeto de la UI
     *
     * @param id identificador del objeto
     * @param mensaje mensaje a mostrar
     * @param severity severidad del mensaje
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    private static void agregarMensaje(String id, String mensaje, FacesMessage.Severity tipo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = crearFacesMessage(tipo, mensaje);
        context.addMessage(id, fm);
    }

    /**
     * /**
     * Método encargado de crear un FacesMessage que será desplegado en la
     * aplicación.
     *
     * @param tipo Tipo de Mensaje
     * @param mensaje Texto del mensaje
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     * @return FacesMessage Creado
     */
    public static FacesMessage crearFacesMessage(FacesMessage.Severity tipo, String mensaje) {
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(tipo);
        fm.setSummary(mensaje);
        return fm;
    }

    /**
     * Método encargado de marcar en rojo el campo dado.
     *
     * @param componente Componente a marcar en rojo.
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void marcarEnRojo(String componente) {
        UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
        if (uui != null) {
            uui.setValid(false);
        }
    }

    /**
     * Método que se encarga de marcar en rojo los campos dados en el arreglo.
     *
     * @param componentes Listado de componentes a marcar en Rojo
     * @version 1.0.0
     * @author Adam M. Gamboa González
     * @author Consorcio Siansa-Indra.
     * @authorLsanchez
     * @date 21/08/2014
     */
    public static void marcarEnRojo(String componentes[]) {
        for (String componente : componentes) {
            UIInput uui = (UIInput) JSFUtil.obtenerComponente(componente);
            if (uui != null) {
                uui.setValid(false);
            }
        }
    }
}

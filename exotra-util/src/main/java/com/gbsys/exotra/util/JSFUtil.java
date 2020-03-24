package com.gbsys.exotra.util;

import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilitarios para JSF.
 *
 * @author Herman Barrantes
 * @version 1.0.0
 * @date 11/05/2018
 */
@Slf4j
@UtilityClass
public class JSFUtil {

    /**
     * Obtiene un componente.
     *
     * @param id Id del componente
     * @return Objeto UIComponent correspondiente al Id
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public static UIComponent obtenerComponente(String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        return root.findComponent(id);
    }

    /**
     * Obtiene el mapa de la sesión actual.
     *
     * @return Mapa de la sesión actual
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public static Map<String, Object> sesion() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap();
    }

    /**
     * Obtiene el Scope de Flash de la sesión actual.
     *
     * @return Mapa del Scope de Flash actual
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public static Flash flash() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getFlash();
    }

    /**
     * Método encargado de obtener del contexto del contenedor, el id de la
     * sesión.
     *
     * @return Id de la sesión del navegador
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public static String obtenerIDSesion() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) ctx.getExternalContext().getSession(true);
        String idSesion = null;

        if (httpSession != null) {
            idSesion = httpSession.getId();
        }

        return idSesion;
    }

    /**
     * Se encargada de redireccionar por medio de código a cualquier página
     * dentro del proyecto, que se especifique en el parámetro url.
     *
     * @param url Direccion de la página a donde se desea redireccionar,
     * (empieza desde la raíz del contexto)
     * @throws IOException
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public static void redireccionarPagina(String url) throws IOException {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
    }

    /**
     * Obtiene el HttpServletRequest de la petición.
     *
     * @return HttpServletRequest de la petición
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public HttpServletRequest obtenerHttpServletRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            return (HttpServletRequest) context.getExternalContext().getRequest();
        }
        return null;
    }

    /**
     * Obtiene el HttpServletResponse de la respuesta.
     *
     * @return HttpServletResponse de la respuesta
     * @author Herman Barrantes
     * @version 1.0.0
     * @date 11/05/2018
     */
    public HttpServletResponse obtenerHttpServletResponse() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            return (HttpServletResponse) context.getExternalContext().getResponse();
        }
        return null;
    }
}

package com.gbsys.exotra.util;

import com.gbsys.exotra.exception.FiltroException;
import com.gbsys.exotra.exception.ServicioException;
import com.gbsys.exotra.exception.UtilException;
import javax.ejb.EJBException;
import javax.el.ELException;
import javax.faces.FacesException;

/**
 * Constantes del proyecto web.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 30/05/2018 10:52
 */
public class Constants {

    /**
     * Página principal.
     */
    public static final String DEFAULT_INDEX_PAGE = "pagina/index.xhtml";

    /**
     * Página de login.
     */
    public static final String DEFAULT_LOGIN_PAGE = "pagina/login.xhtml";

    /**
     * Página de error.
     */
    public static final String DEFAULT_ERROR_PAGE = "pagina/error.xhtml";

    /**
     * Página de 404 no encontrado.
     */
    public static final String DEFAULT_NOT_FOUND_PAGE = "pagina/404.xhtml";

    /**
     * Página de acceso denegado.
     */
    public static final String DEFAULT_ACCESS_DENIED_PAGE = "pagina/access.xhtml";

    /**
     * Página de sesión expirada.
     */
    public static final String DEFAULT_EXPIRED_PAGE = "pagina/expired.xhtml";

    /**
     * Indica si está desactivado el filtro de seguridad.
     */
    public static final boolean DISABLE_FILTER = false;
    /**
     * Lista separada por coma de recursos a ignorar.
     */
    public static final String IGNORED_RESOURCES = "";

    /**
     * Tipos de excepciones que se tienen que desenvolver.
     */
    @SuppressWarnings("unchecked")
    public static final Class<? extends Throwable>[] TYPES_TO_UNWRAP
            = new Class[]{FacesException.class, ELException.class, EJBException.class};

    /**
     * Tipos de excepciones que se tienen que manejar.
     */
    @SuppressWarnings("unchecked")
    public static final Class<? extends Throwable>[] TYPES_TO_HANDLE
            = new Class[]{ServicioException.class, UtilException.class, FiltroException.class};

    /**
     * Variable usada para almacenar en sesión la lista de recursos.
     */
    public static final String RECURSOS = "recursos";
}

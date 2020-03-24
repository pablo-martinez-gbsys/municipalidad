package com.gbsys.exotra.util.session;

import com.gbsys.exotra.util.Constants;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;

/**
 * Created by rmpestano on 04/02/17 Controls if user is logged in so AdminFilter
 * can send user to login page when it is not logged in.
 */
@SessionScoped
public class AdminSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Produces
    @Named("user")
    @Setter
    @Getter
    private User user;

    /**
     * Indica si el usuario esta logeado o no.
     *
     * @return true si está logeado, false en caso contrario
     */
    public boolean isLoggedIn() {
        return user != null;
    }

    /**
     * avoid multiple redirects when redirecting user back to previous page
     * after session expiration.
     */
    @Getter
    @Setter
    private boolean userRedirected = false;

    /**
     * Verifica si el usuario tiene acceso a un recurso.
     *
     * @param nomRecurso nombre del recurso
     * @return true si el usuario tiene acceso, false en caso contrario
     */
    public static boolean verificarRecurso(String nomRecurso) {
        @SuppressWarnings("unchecked")
        List<String> listadoRecursos = (List<String>) Faces.getSession().getAttribute(Constants.RECURSOS);
        return listadoRecursos != null && listadoRecursos.contains(nomRecurso);
    }

}

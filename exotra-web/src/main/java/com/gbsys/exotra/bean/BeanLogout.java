package com.gbsys.exotra.bean;

import com.gbsys.exotra.util.Constants;
import org.omnifaces.util.Faces;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by rmpestano on 03/02/17.
 */
@Named
@RequestScoped
public class BeanLogout {

    public void logout() throws IOException {
        String loginPage = Constants.DEFAULT_LOGIN_PAGE;
        Faces.invalidateSession();
        Faces.redirect(loginPage);
//        Faces.getSession().invalidate();
//        ExternalContext ec = Faces.getExternalContext();
//        ec.redirect(ec.getRequestContextPath() + loginPage);
    }

}

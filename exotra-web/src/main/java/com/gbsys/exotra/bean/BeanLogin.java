package com.gbsys.exotra.bean;

import com.gbsys.exotra.cliente.seguridad.ClienteSeguridad;

import com.gbsys.exotra.cliente.seguridad.ModeloUsuarioExterno;
import com.gbsys.exotra.modelo.RespuestaBase;
import com.gbsys.exotra.seguridad.servicio.SeguridadServicio;
import com.gbsys.exotra.util.Constants;
import com.gbsys.exotra.util.session.AdminSession;
import com.gbsys.exotra.util.session.User;
import java.io.FileReader;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.JSONParser;

/**
 * Bean encargado del acceso al sistema.
 *
 * @author GBSYS. Diseñado y Desarrollado por: Ing. Herman Barrantes
 * @version 1.0.0
 * @date 27/01/2020
 */
@Slf4j
@ViewScoped
@Named
public class BeanLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClienteSeguridad clienteSeguridad;
    @Inject
    private AdminSession adminSession;
    @Inject
    private SeguridadServicio seguridadServicio;
    @Getter
    @Setter
    private RespuestaBase respuestaBase;
    @Getter
    @Setter
    @NotNull
    @Size(min = 3, max = 20)
    private String username;
    @Getter
    @Setter
    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    public void login() {
        try {
             respuestaBase = new RespuestaBase();
            JSONParser parser = new JSONParser();
            String respuetaLogin = seguridadServicio.login(username, password);
            JSONObject jsonLogin = (JSONObject) parser.parse(respuetaLogin);

            respuestaBase.setCodRespuesta((String) jsonLogin.get("pCodRespuesta"));
            respuestaBase.setObservacion((String) jsonLogin.get("pObservacion"));

            if (respuestaBase.getCodRespuesta().equals("00")) {

               
                Faces.getSession().setAttribute("pIdTipoTramite", (String) jsonLogin.get("pIdTipoTramite"));
                Faces.getSession().setAttribute("pTelefono", (String) jsonLogin.get("pTelefono"));
                Faces.getSession().setAttribute("pTelefonoCelular", (String) jsonLogin.get("pTelefonoCelular"));
                Faces.getSession().setAttribute("pIdentificacion", (String) jsonLogin.get("pIdentificacion"));
                Faces.getSession().setAttribute("pNombreCompleto", (String) jsonLogin.get("pNombreCompleto"));
                Faces.getSession().setAttribute("pTipoIdentificacion", (Long) jsonLogin.get("pTipoIdentificacion"));
                Faces.getSession().setAttribute("pCorreo", (String) jsonLogin.get("pCorreo"));
                
                
                respuestaBase.setNumTramite((String) jsonLogin.get("pNumTramite"));
                respuestaBase.setIdCarpeta((Long) jsonLogin.get("pIdCarpeta"));
                respuestaBase.setIdPersona((Long) jsonLogin.get("pId_persona"));
                respuestaBase.setNumIdentificacion((String) jsonLogin.get("pIdentificacion"));
                respuestaBase.setNombreCompleto((String) jsonLogin.get("pNombreCompleto"));
                respuestaBase.setTipoIdentificacion((Long) jsonLogin.get("pTipoIdentificacion"));
                User user = new User();
                user.setRespuestaBase(respuestaBase);
                user.setId(1L);
                user.setUsername(respuestaBase.getNombreCompleto());
                user.setEmail("prueba");
                user.setFullname(respuestaBase.getNombreCompleto());
                user.setIdSystem(1L);
               
                adminSession.setUser(user);
                Messages.addFlashGlobalInfo("mensaje.info.login.exitoso", user.getFullname());
                Faces.redirect("pagina/tramite/exoneracion.xhtml");

            } else {
                Messages.addFlashGlobalInfo(respuestaBase.getObservacion());

            }
//               ModeloUsuarioExterno usuarioExterno = clienteSeguridad.credenciales("herman.barrantes", "gbsystem01");
//            List<String> listadoRecursos = clienteSeguridad.recursos("herman.barrantes", "gbsystem01");
//            Faces.getSession().setAttribute(Constants.RECURSOS, listadoRecursos);
//            User user = new User();
//            user.setId(1L);
//            user.setUsername("pablo");
//            user.setEmail("prueba");
//            user.setFullname("Pablo Martinez");
//            user.setIdSystem(1L);
//            adminSession.setUser(user);
//            Messages.addFlashGlobalInfo("mensaje.info.login.exitoso", user.getFullname());
//            Faces.redirect("pagina/tramite/exoneracion.xhtml");

        } catch (Exception ex) {
            adminSession.setUser(null);
            Messages.addGlobalError(ex.getMessage());
        }
    }

}

package com.gbsys.exotra.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class RedirectUtil {

    /**
     * Se encarga de redireccionar a la indicada como parámetro.
     *
     * @param hsrq Petición de Request
     * @param hsr Respuesta de Response
     * @param urlpagina URL a navegar
     * @throws IOException
     */
    public static void redireccionar(HttpServletRequest hsrq, HttpServletResponse hsr, String urlpagina) throws IOException, ServletException {

        if (isAjaxRequest(hsrq)) {//En caso de ser ajax
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"").append(urlpagina).append("\"></redirect></partial-response>");
            hsr.setHeader("Cache-Control", "no-cache");
            hsr.setCharacterEncoding("UTF-8");
            hsr.setContentType("text/xml");
            PrintWriter pw = hsr.getWriter();
            pw.println(sb.toString());
            pw.flush();
        } else {//En caso de ser navegacion entonces forma facil.
            hsr.sendRedirect(urlpagina);
        }
    }

    /**
     * Se encarga de redireccionar a la indicada como parámetro.
     *
     * @param hsrq Petición de Request
     * @param hsr Respuesta de Response
     * @param url URL a navegar
     * @throws IOException
     */
    public static void redireccionarPost(HttpServletRequest hsrq, HttpServletResponse hsr, String url) throws IOException, ServletException {

        if (isAjaxRequest(hsrq)) {//En caso de ser ajax
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"").append(url)
                    .append("\"></redirect></partial-response>");
            hsr.setHeader("Cache-Control", "no-cache");
            hsr.setCharacterEncoding("UTF-8");
            hsr.setContentType("text/xml");
            PrintWriter pw = hsr.getWriter();
            pw.println(sb.toString());
            pw.flush();
        } else {//En caso de ser navegacion entonces forma facil.
            RequestDispatcher disp = hsrq.getRequestDispatcher(url);
            disp.forward(hsrq, hsr);
        }
    }

    /**
     * Encuentra si la acción que disparó el request fue por medio de ajax o no.
     *
     * @param request Request a encontrar si es ajax
     * @return Si es ajax o no.
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        boolean check = false;
        String facesRequest = request.getHeader("Faces-Request");
        if (facesRequest != null && facesRequest.equals("partial/ajax")) {
            check = true;
        }
        return check;
    }

}

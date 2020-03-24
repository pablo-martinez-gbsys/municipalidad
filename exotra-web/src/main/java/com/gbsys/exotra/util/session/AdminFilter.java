package com.gbsys.exotra.util.session;

import com.gbsys.exotra.util.Constants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Based on
 * https://github.com/conventions/core/blob/master/src/main/java/org/conventionsframework/filter/ConventionsFilter.java
 * Created by rafael-pestano on 07/01/17.
 *
 * This filter controls when user must be redirected to logon or index page and
 * saves current url to redirect back when session expires
 */
@Slf4j
@WebFilter(urlPatterns = {"/*"})
public class AdminFilter implements Filter {

    private static final String FACES_RESOURCES = "/javax.faces.resource";

    private boolean disableFilter;
    private String loginPage;
    private String errorPage;
    private String indexPage;
    private String redirectPrefix;

    @Inject
    AdminSession adminSession;

    private final List<String> ignoredResources = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        disableFilter = Constants.DISABLE_FILTER;
        if (!disableFilter) {
            try {
                loginPage = Constants.DEFAULT_LOGIN_PAGE;
                errorPage = Constants.DEFAULT_ERROR_PAGE;
                indexPage = Constants.DEFAULT_INDEX_PAGE;

                //removes leading '/'
                errorPage = errorPage.startsWith("/") ? errorPage.substring(1) : errorPage;
                loginPage = loginPage.startsWith("/") ? loginPage.substring(1) : loginPage;
                indexPage = indexPage.startsWith("/") ? indexPage.substring(1) : indexPage;

                ignoredResources.add("/" + loginPage.substring(0, loginPage.lastIndexOf(".")));//we need leading slash for ignoredResources
                ignoredResources.add("/" + errorPage.substring(0, errorPage.lastIndexOf(".")));

                String configuredResouces = Constants.IGNORED_RESOURCES;
                if (has(configuredResouces)) {
                    this.ignoredResources.addAll(Arrays.asList(configuredResouces.split(",")));
                    for (String ignoredResource : ignoredResources) {
                        if (!ignoredResource.startsWith("/")) { //we need leading slash for ignoredResources beucase getServletPath (in this#skipResource) returns a string with leading slash
                            ignoredResources.set(ignoredResources.indexOf(ignoredResource), "/" + ignoredResource);
                        }
                    }
                }

            } catch (Exception ex) {
                log.error("problem initializing admin filter", ex);
            }
        }

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (disableFilter) {
            chain.doFilter(req, resp);
            return;
        }
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getRequestURI().equals(request.getContextPath() + "/")
                || (adminSession.isLoggedIn() && request.getRequestURI().endsWith(loginPage))) {
            response.sendRedirect(getRedirectPrefix(request) + request.getContextPath() + "/" + indexPage);
            return;
        }

        if (request.getRequestURI().contains(request.getContextPath() + "/public/")) {
            chain.doFilter(req, resp);
            return;
        }

        if (skipResource(request, response) || adminSession.isLoggedIn()) {
            if (!adminSession.isUserRedirected() && adminSession.isLoggedIn() && has(request.getHeader("Referer")) && request.getHeader("Referer").contains("?page=")) {
                adminSession.setUserRedirected(true);
                String pageFromURL = request.getContextPath() + extractPageFromURL(request.getHeader("Referer"));
                log.info("Redirecting user back to " + pageFromURL);
                response.sendRedirect(getRedirectPrefix(request) + pageFromURL);
                return;
            }
            try {
                chain.doFilter(req, resp);
            } catch (FileNotFoundException ex) {
                log.warn("File not found", ex);
                response.sendError(404);
            }
        } else { //resource not skipped (e.g a page that is not logon page) AND user not logged in
            redirectToLogon(request, (HttpServletResponse) resp);
            return;
        }

    }

    private String extractPageFromURL(String referer) {
        try {
            URL url = new URL(referer);
            String[] params = url.getQuery().split("&");
            for (String param : params) {
                String[] split = param.split("=");
                if ("page".equals(split[0])) {
                    return URLDecoder.decode(split[1], "UTF-8");
                }
            }
        } catch (MalformedURLException | UnsupportedEncodingException ex) {
            log.warn("Could not extract page from url", ex);
        }
        return indexPage;
    }

    @Override
    public void destroy() {

    }

    /**
     * skips faces-resources, index, error or logon pages
     *
     * @param request
     * @return true if resource must be skipped by the filter false otherwise
     */
    private boolean skipResource(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getServletPath();
        if (path.contains(".")) {
            path = path.substring(0, path.lastIndexOf("."));
        }
        boolean skip = path.startsWith(FACES_RESOURCES) || shouldIgnoreResource(path) || response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        return skip;
    }

    private void redirectToLogon(HttpServletRequest request, HttpServletResponse response) {
        try {
            String referer = request.getHeader("Referer");
            String recoveryUrlParams;
            //get request parameters
            if (has(referer) && referer.contains("?")) {
                recoveryUrlParams = referer.substring(referer.lastIndexOf("?") + 1);
            } else {
                recoveryUrlParams = request.getQueryString();
            }
            //saves page where user were
            String requestedPage = request.getRequestURI();
            StringBuilder recoveryUrl = null;
            if (!loginPage.equals(requestedPage) && requestedPage.contains(".")) {
                if (requestedPage.startsWith(request.getContextPath())) {
                    requestedPage = requestedPage.replaceFirst(request.getContextPath(), "");
                }
                recoveryUrl = new StringBuilder(requestedPage);
                if (has(recoveryUrlParams)) {
                    recoveryUrl.append("?").append(recoveryUrlParams);
                }
            }
            /*
             if saved page is not null and is not index page then send user to logon page and save
            / previous page in url param named 'page'
             */
            String redirectUrl = request.getContextPath() + "/" + loginPage
                    + ((recoveryUrl != null) && isValidRecoveryUrl(recoveryUrl)
                    ? "?page=" + URLEncoder.encode(recoveryUrl.toString(), "UTF-8")
                    : "");
            if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
                //redirect on ajax request: //http://stackoverflow.com/questions/13366936/jsf-filter-not-redirecting-after-initial-redirect
                response.setContentType("text/xml");
                response.getWriter()
                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                        .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", redirectUrl);
            } else {//normal redirect
                response.sendRedirect(getRedirectPrefix(request) + redirectUrl);
            }

        } catch (Exception ex) {
            log.error("Could not redirect to " + loginPage, ex);
        }

    }

    /**
     * Skip error pages, login and index page as recovery url because it doesn't
     * make sense redirecting user to such pages
     *
     * @param recoveryUrl
     * @return
     */
    private boolean isValidRecoveryUrl(StringBuilder recoveryUrl) {
        return !recoveryUrl.toString().contains(Constants.DEFAULT_INDEX_PAGE)
                && !recoveryUrl.toString().contains(Constants.DEFAULT_ACCESS_DENIED_PAGE)
                && !recoveryUrl.toString().contains(Constants.DEFAULT_EXPIRED_PAGE)
                && !recoveryUrl.toString().contains(Constants.DEFAULT_LOGIN_PAGE);
    }

    /**
     * @param path
     * @return true if requested path starts with a ignored resource (configured
     * in admin-config.properties)
     */
    private boolean shouldIgnoreResource(String path) {
        for (String ignoredResource : ignoredResources) {
            if (path.startsWith(ignoredResource)) {
                return true;
            }
        }
        return false;
    }

    private String getRedirectPrefix(HttpServletRequest request) {
        if (redirectPrefix == null) {
            String url = request.getRequestURL().toString();
            String uri = request.getRequestURI();
            int offset = url.indexOf(uri);
            redirectPrefix = url.substring(0, offset);
            if (useHttps(request)) {
                log.warn("Changing request scheme to https.");
                redirectPrefix = redirectPrefix.replace("http:", "https:");
            }
        }
        return redirectPrefix;
    }

    private static boolean useHttps(HttpServletRequest request) {
        String protoHeader = request.getHeader("X-Forwarded-Proto");
        return request.isSecure() || (protoHeader != null && protoHeader.toLowerCase().equals("https"));
    }

    /**
     * @return TRUE when given text has any character, FALSE otherwise
     */
    private boolean has(String text) {
        return text != null && text.trim().length() > 0;
    }
}

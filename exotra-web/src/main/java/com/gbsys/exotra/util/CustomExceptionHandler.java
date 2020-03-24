package com.gbsys.exotra.util;

import org.omnifaces.util.Exceptions;
import org.omnifaces.util.Messages;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.config.WebXml;

/**
 * Based on:
 * https://github.com/conventions/core/blob/master/src/main/java/org/conventionsframework/exception/ConventionsExceptionHandler.java
 * This handler adds FacesMessages when BusinessExceptions are thrown OR sends
 * user to error page when unexpected exception are raised.
 *
 * @author rafael-pestano
 */
@Slf4j
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
        this.wrapped = exceptionHandler;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        FacesContext context = FacesContext.getCurrentInstance();
        findErrorMessages(context);
        handleException(context);
        wrapped.handle();
    }

    /**
     * @param context
     * @throws Throwable
     */
    private void handleException(FacesContext context) {
        Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents = getUnhandledExceptionQueuedEvents().iterator();

        if (unhandledExceptionQueuedEvents.hasNext()) {
            Throwable exception = unhandledExceptionQueuedEvents.next().getContext().getException();
            unhandledExceptionQueuedEvents.remove();

            Throwable rootCause = Exceptions.unwrap(exception, Constants.TYPES_TO_UNWRAP);

            if (shouldHandleException(rootCause)) {
                handleException(context, rootCause);
                return;
            }

            //send user to error page when unexpected exceptions are raised
            goToErrorPage(context, rootCause);
        }

    }

    private boolean shouldHandleException(Throwable rootCause) {
        for (Class<? extends Throwable> type : Constants.TYPES_TO_HANDLE) {
            if (Exceptions.is(rootCause, type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param context
     * @param ex
     * @throws Throwable
     */
    private void goToErrorPage(FacesContext context, Throwable ex) {
        log.warn("goToErrorPage", ex);
        if (ex instanceof FileNotFoundException) {
            throw new FacesException(ex);
        }
        @SuppressWarnings("unchecked")
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        String referer = request.getHeader("Referer");
        sessionMap.put("userAgent", request.getHeader("user-agent"));
        sessionMap.put("requestedUri", has(referer) ? referer : request.getRequestURL());
        sessionMap.put("stacktrace", ex);
        sessionMap.put("errorMessage", ex != null ? ex.getMessage() : "");
        sessionMap.put("exceptionType", ex != null ? ex.getClass().getName() : null);
        String userIp = request.getHeader("x-forwarded-for") != null ? request.getHeader("x-forwarded-for").split(",")[0] : request.getRemoteAddr();
        sessionMap.put("userIp", userIp);

        String errorPage = WebXml.INSTANCE.findErrorPageLocation(ex);
        if (!has(errorPage)) {
            errorPage = Constants.DEFAULT_ERROR_PAGE;
        }
        try {
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + errorPage);
        } catch (IOException ioex) {
            log.error("Could not redirect user to error page: " + context.getExternalContext().getRequestContextPath() + errorPage, ioex);
        }
    }

    /**
     * @param context
     * @param ex application business exception
     */
    private void handleException(FacesContext context, Throwable ex) {
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            throw new FacesException(ex);
        }
        Messages.addGlobalError(ex.getMessage());
        validationFailed(context);
        context.renderResponse();
    }

    /**
     * Set primefaces validationFailled callback param
     */
    private void validationFailed(FacesContext context) {
        @SuppressWarnings("unchecked")
        Map<Object, Object> callbackParams = (Map<Object, Object>) context.getAttributes().get("CALLBACK_PARAMS");
        if (callbackParams == null) {
            callbackParams = new HashMap<>();
            callbackParams.put("CALLBACK_PARAMS", callbackParams);
        }
        callbackParams.put("validationFailed", true);

    }

    /**
     * If there is any faces message queued add PrimeFaces validation failed
     *
     * @param context
     */
    private void findErrorMessages(FacesContext context) {
        if (context.getMessageList().isEmpty() || context.isValidationFailed()) {
            return;
        }
        for (FacesMessage msg : context.getMessageList()) {
            if (msg.getSeverity().equals(FacesMessage.SEVERITY_ERROR) || msg.getSeverity().equals(FacesMessage.SEVERITY_FATAL)) {
                validationFailed(context);
                break;
            }
        }
    }

    /**
     * @return TRUE when given text has any character, FALSE otherwise
     */
    private boolean has(String text) {
        return text != null && text.trim().length() > 0;
    }

}

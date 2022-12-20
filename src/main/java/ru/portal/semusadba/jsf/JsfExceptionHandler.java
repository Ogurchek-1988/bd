package ru.portal.semusadba.jsf;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.io.RuntimeIOException;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.*;
import javax.faces.event.ExceptionQueuedEvent;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



@Slf4j
public class JsfExceptionHandler extends ExceptionHandlerWrapper {
    private final Set<Class<?>> BLACK_LIST = new HashSet<>(Arrays.asList(RuntimeIOException.class));

    public JsfExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            return;
        }
        ExternalContext externalContext = facesContext.getExternalContext();

        Iterable<ExceptionQueuedEvent> exceptionQueuedEvents = getUnhandledExceptionQueuedEvents();
        if (exceptionQueuedEvents != null && exceptionQueuedEvents.iterator() != null) {
            Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents = getUnhandledExceptionQueuedEvents().iterator();
            if (unhandledExceptionQueuedEvents.hasNext()) {
                try {
                    Throwable exception = unhandledExceptionQueuedEvents.next().getContext().getException();

                    unhandledExceptionQueuedEvents.remove();

                    if (!inBlackList(exception)) {
                        long time = System.currentTimeMillis();
                        logException(exception, time);
                        var request = (HttpServletRequest) externalContext.getRequest();
                        String redirectUrl = "/public/error/error.xhtml?errorTimestamp=" + time;

                        boolean isAjax = facesContext.getPartialViewContext().isAjaxRequest();
                        if (exception.getClass().equals(ViewExpiredException.class)) {
                            redirectUrl = request.getRequestURL().toString();
                            log.info("Redirect URL when ViewExpiredException: {}", redirectUrl);
                        }
                        if (isAjax) {
                            PartialResponseWriter writer = facesContext.getPartialViewContext().getPartialResponseWriter();
                            try {
                                writer.redirect(redirectUrl);
                                externalContext.responseReset();

                                writer.startDocument();
                                writer.redirect(redirectUrl);
                                writer.endDocument();

                            } catch (IOException e) {
                                log.error("Unexpected error during ajax request redirect", e);
                            }
                        } else {
                            try {
                                externalContext.redirect(redirectUrl);
                            } catch (IOException e) {
                                log.error("Unexpected error during faces redirect", e);
                            }
                        }
                        facesContext.responseComplete();
                    }

                } catch (Exception ex) {
                    log.error("Could not handle exception!", ex);
                }
            }
        }
    }

    private boolean inBlackList(Throwable exception) {
        return BLACK_LIST.contains(exception.getClass());
    }

    private void logException(Throwable exception, long time) {
        log.debug("Logging exception: {}", exception.getClass());
        if (exception.getClass().equals(ViewExpiredException.class)) {
            log.error("View expired exception: {}", exception.getMessage());
        } else {
            log.error("An exception occurred during response render. Error code [{}], reason ", time, exception);
        }
    }

}

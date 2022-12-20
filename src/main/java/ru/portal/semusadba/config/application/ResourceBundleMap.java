package ru.portal.semusadba.config.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Locale;


@Slf4j
@Component(value = "i18n")
public class ResourceBundleMap extends HashMap {

    @Override
    public String get(Object key) {
        ServletRequest request = (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String message;
        try {
            message = getMessageSource().getMessage((String) key, null, request.getLocale());
        } catch (NoSuchMessageException e) {
            message = "???" + key + "???";
        }
        return message;
    }

    public String get(Object key, Locale locale){
        String message;
        try {
            message = getMessageSource().getMessage((String) key, null, locale);
        } catch (NoSuchMessageException e) {
            message = "???" + key + "???";
        }
        return message;
    }


    public String format(Object key, Object... values) {
        String s = get(key);
        try {
            return String.format(s, values);
        } catch (IllegalFormatException e) {
            log.warn("Can not format string with key {} using values {}. Exception: {}", key, values, e);
            return s;
        }
    }

    private ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setBasename("ru/sem/portfolio/i18n/Messages");
        rbms.setDefaultEncoding("UTF-8");
        return rbms;
    }


}

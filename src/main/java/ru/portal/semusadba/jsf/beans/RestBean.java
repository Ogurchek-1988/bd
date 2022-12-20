package ru.portal.semusadba.jsf.beans;

import lombok.extern.slf4j.Slf4j;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class RestBean {

    protected static final String ENCODING = "UTF-8";

    public String getRequestURI() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRequestURI();
    }


    public String getRequestParam(String paramName) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getParameter(paramName) != null) {
            return request.getParameter(paramName);
        } else {
            return null;
        }
    }


    public Boolean getBoolRequestParam(String paramName) {
        String paramStr = getRequestParam(paramName);
        if (paramStr == null) {
            return null;
        }
        try {
            return Boolean.valueOf(paramStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public Long getLongRequestParam(String paramName) {
        String paramStr = getRequestParam(paramName);
        if (paramStr == null) {
            return null;
        }
        try {
            return Long.valueOf(paramStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public Integer getIntRequestParam(String paramName) {
        String paramStr = getRequestParam(paramName);
        if (paramStr == null) {
            return null;
        }
        try {
            return Integer.valueOf(paramStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public List<Long> getLongListRequestParam(String paramName) {
        String paramStr = getRequestParam(paramName);
        if (paramStr == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(paramStr.split(",")).map(p -> {
            try {
                return Long.valueOf(p);
            } catch (NumberFormatException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


    public Set<Long> getLongSetRequestParam(String paramName) {
        return new HashSet<>(getLongListRequestParam(paramName));
    }



    public Date getDateRequestParam(String paramName, String format) {
        String paramStr = getRequestParam(paramName);
        if (paramStr == null) {
            return null;
        }

        SimpleDateFormat f = new SimpleDateFormat(format);
        try {
            return f.parse(paramStr);
        } catch (ParseException e) {
            return null;
        }
    }


    public void sendErrorResponse(int status, String message) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(status, message);
        } catch (IOException e) {
            log.error("Cannot send error, status: {}, message: {}", status, message, e);
        }
    }

    @Override
    public String toString() { // Something sometimes trying to call this => LazyInitializationException
        return getClass().getName() + ";" + Integer.toString(hashCode(), 16);
    }
}


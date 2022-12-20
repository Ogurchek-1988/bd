package ru.portal.semusadba.network.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseWrapper implements Serializable {
    String message;

    public ResponseWrapper(String message) {
        this.message = message;
    }
}
package ru.portal.semusadba.network.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefreshRequest implements Serializable {
    private String refreshToken;
}

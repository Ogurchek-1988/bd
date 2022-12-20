package ru.portal.semusadba.network.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RefreshResponse implements Serializable {
    String accessToken;
    String refreshToken;
    String tokenType;
}
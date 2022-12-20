package ru.portal.semusadba.validate;

import ru.portal.semusadba.network.request.RegisterRequest;

import java.util.Optional;

public interface UserValidator {
    Optional<String> getErrorMessage(RegisterRequest request);
}
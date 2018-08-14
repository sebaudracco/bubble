package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class InvalidCredentialsException extends AuthenticationException {
    private static final long serialVersionUID = -4834003835215460648L;

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}

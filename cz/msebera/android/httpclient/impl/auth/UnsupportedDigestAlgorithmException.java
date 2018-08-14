package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class UnsupportedDigestAlgorithmException extends RuntimeException {
    private static final long serialVersionUID = 319558534317118022L;

    public UnsupportedDigestAlgorithmException(String message) {
        super(message);
    }

    public UnsupportedDigestAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }
}

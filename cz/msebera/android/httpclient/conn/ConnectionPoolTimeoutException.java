package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class ConnectionPoolTimeoutException extends ConnectTimeoutException {
    private static final long serialVersionUID = -7898874842020245128L;

    public ConnectionPoolTimeoutException(String message) {
        super(message);
    }
}

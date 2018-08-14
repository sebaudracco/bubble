package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

public class HttpCacheEntrySerializationException extends IOException {
    private static final long serialVersionUID = 9219188365878433519L;

    public HttpCacheEntrySerializationException(String message) {
    }

    public HttpCacheEntrySerializationException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}

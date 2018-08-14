package com.danikula.videocache;

public class ProxyCacheException extends Exception {
    private static final String LIBRARY_VERSION = ". Version: 2.7.0";

    public ProxyCacheException(String message) {
        super(message + LIBRARY_VERSION);
    }

    public ProxyCacheException(String message, Throwable cause) {
        super(message + LIBRARY_VERSION, cause);
    }

    public ProxyCacheException(Throwable cause) {
        super("No explanation error. Version: 2.7.0", cause);
    }
}

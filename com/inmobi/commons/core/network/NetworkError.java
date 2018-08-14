package com.inmobi.commons.core.network;

import cz.msebera.android.httpclient.HttpStatus;

public class NetworkError {
    private ErrorCode f7737a;
    private String f7738b;

    public enum ErrorCode {
        NETWORK_UNAVAILABLE_ERROR(0),
        UNKNOWN_ERROR(-1),
        NETWORK_IO_ERROR(-2),
        OUT_OF_MEMORY_ERROR(-3),
        INVALID_ENCRYPTED_RESPONSE_RECEIVED(-4),
        RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT(-5),
        GZIP_DECOMPRESSION_FAILED(-6),
        BAD_REQUEST(-7),
        HTTP_NO_CONTENT(HttpStatus.SC_NO_CONTENT),
        HTTP_NOT_MODIFIED(HttpStatus.SC_NOT_MODIFIED),
        HTTP_SEE_OTHER(HttpStatus.SC_SEE_OTHER),
        HTTP_SERVER_NOT_FOUND(404),
        HTTP_MOVED_TEMP(HttpStatus.SC_MOVED_TEMPORARILY),
        HTTP_INTERNAL_SERVER_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR),
        HTTP_NOT_IMPLEMENTED(HttpStatus.SC_NOT_IMPLEMENTED),
        HTTP_BAD_GATEWAY(HttpStatus.SC_BAD_GATEWAY),
        HTTP_SERVER_NOT_AVAILABLE(HttpStatus.SC_SERVICE_UNAVAILABLE),
        HTTP_GATEWAY_TIMEOUT(HttpStatus.SC_GATEWAY_TIMEOUT),
        HTTP_VERSION_NOT_SUPPORTED(HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED);
        
        private int f7736a;

        private ErrorCode(int i) {
            this.f7736a = i;
        }

        public int getValue() {
            return this.f7736a;
        }

        public static ErrorCode fromValue(int i) {
            if (HttpStatus.SC_BAD_REQUEST <= i && HttpStatus.SC_INTERNAL_SERVER_ERROR > i) {
                return BAD_REQUEST;
            }
            for (ErrorCode errorCode : values()) {
                if (errorCode.f7736a == i) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    public NetworkError(ErrorCode errorCode, String str) {
        this.f7737a = errorCode;
        this.f7738b = str;
    }

    public ErrorCode m10332a() {
        return this.f7737a;
    }

    public String m10333b() {
        return this.f7738b;
    }
}

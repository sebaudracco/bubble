package com.fyber.currency;

import com.fyber.p094b.C2516n.C2515a;

public class VirtualCurrencyErrorResponse implements C2515a {
    private final ErrorType f6424a;
    private final String f6425b;
    private final String f6426c;

    public enum ErrorType {
        ERROR_INVALID_RESPONSE,
        ERROR_INVALID_RESPONSE_SIGNATURE,
        SERVER_RETURNED_ERROR,
        ERROR_OTHER
    }

    public VirtualCurrencyErrorResponse(ErrorType errorType, String str, String str2) {
        this.f6424a = errorType;
        this.f6425b = str;
        this.f6426c = str2;
    }

    public ErrorType getError() {
        return this.f6424a;
    }

    public String getErrorCode() {
        return this.f6425b;
    }

    public String getErrorMessage() {
        return this.f6426c != null ? this.f6426c : "";
    }
}

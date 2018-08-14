package com.core42matters.android.registrar;

class LimitAdTrackingException extends Exception {
    LimitAdTrackingException() {
        super("Advertising ID should not be associated");
    }
}

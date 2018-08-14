package com.firebase.jobdispatcher;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.List;

public final class ValidationEnforcer$ValidationException extends RuntimeException {
    private final List<String> mErrors;

    public ValidationEnforcer$ValidationException(String msg, @NonNull List<String> errors) {
        super(msg + ": " + TextUtils.join("\n  - ", errors));
        this.mErrors = errors;
    }

    public List<String> getErrors() {
        return this.mErrors;
    }
}

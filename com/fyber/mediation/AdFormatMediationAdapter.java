package com.fyber.mediation;

import android.support.annotation.NonNull;
import com.fyber.mediation.p108b.C2580a;
import java.util.Collections;
import java.util.Map;

public abstract class AdFormatMediationAdapter<R, E extends Exception> implements ProviderRequester<R, E> {
    protected ProviderRequesterListener<R, E> providerRequesterListener;
    protected C2580a request;

    protected Map<String, String> getContextData() {
        return this.request != null ? this.request.m8237d() : Collections.emptyMap();
    }

    public void setProviderRequesterListener(@NonNull ProviderRequesterListener<R, E> providerRequesterListener) {
        this.providerRequesterListener = providerRequesterListener;
    }

    protected <T> T getAttribute(String str) {
        return this.request.mo3970a(str);
    }

    protected <T> T getAttribute(String str, Class<T> cls) {
        return this.request.m8231a(str, (Class) cls);
    }

    protected <T> T getAttribute(String str, Class<T> cls, T t) {
        return this.request.mo3971a(str, cls, t);
    }
}

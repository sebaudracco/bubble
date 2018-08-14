package com.fyber.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/* compiled from: TimeParameterProvider */
public final class C2670r implements C2648n {
    private Map<String, String> f6628a = new HashMap(2);

    public C2670r() {
        this.f6628a.put("timezone_id", TimeZone.getDefault().getID());
    }

    public final synchronized Map<String, String> mo4003a() {
        this.f6628a.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        return this.f6628a;
    }
}

package com.fyber.utils;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/* compiled from: SessionParameterProvider */
public final class C2668p implements C2648n {
    private final Map<String, String> f6627a = Collections.singletonMap("session_id", UUID.randomUUID().toString());

    public final synchronized Map<String, String> mo4003a() {
        return this.f6627a;
    }
}

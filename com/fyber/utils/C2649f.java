package com.fyber.utils;

import com.fyber.Fyber;
import java.util.Collections;
import java.util.Map;

/* compiled from: GlobalParamsProvider */
public final class C2649f implements C2648n {
    public final synchronized Map<String, String> mo4003a() {
        Map<String, String> c;
        c = Fyber.getConfigs().m7602c();
        if (C2664l.m8520a(c)) {
            c = Collections.emptyMap();
        }
        return c;
    }
}

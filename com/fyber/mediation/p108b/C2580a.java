package com.fyber.mediation.p108b;

import android.support.annotation.NonNull;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.C2664l;
import com.fyber.utils.C2684x;
import com.fyber.utils.StringUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProviderRequest */
public final class C2580a implements C2579k {
    public static String f6458a = "AD_ID";
    private Map<String, String> f6459b;
    private Map<String, Object> f6460c;
    private Map<String, String> f6461d;

    public final /* synthetic */ C2579k mo3973b(String str, Object obj) {
        return m8228a(str, obj);
    }

    public final String mo3972a() {
        return "";
    }

    public final Map<String, Object> m8235b() {
        if (this.f6460c == null) {
            this.f6460c = new HashMap();
            this.f6461d = new C2684x(this.f6460c);
        }
        return this.f6460c;
    }

    public final <T> T mo3970a(@NonNull String str) {
        if (this.f6460c != null) {
            return this.f6460c.get(str);
        }
        return null;
    }

    public final <T> T m8231a(String str, Class<T> cls) {
        if (C2664l.m8521b(this.f6460c)) {
            Object obj = this.f6460c.get(str);
            if (obj != null && cls.isAssignableFrom(obj.getClass())) {
                return cls.cast(obj);
            }
        }
        return null;
    }

    public final <T> T mo3971a(String str, Class<T> cls, T t) {
        T a = m8231a(str, (Class) cls);
        return a != null ? a : t;
    }

    public final C2580a m8229a(String str, String str2) {
        if (this.f6459b == null) {
            this.f6459b = new HashMap();
        }
        this.f6459b.put(str, str2);
        return this;
    }

    public final Map<String, String> m8236c() {
        return this.f6459b;
    }

    public final C2580a m8228a(String str, Object obj) {
        if (StringUtils.notNullNorEmpty(str)) {
            m8235b().put(str, obj);
        }
        return this;
    }

    public final Map<String, String> m8237d() {
        if (C2664l.m8521b(this.f6460c)) {
            return this.f6461d;
        }
        return Collections.emptyMap();
    }
}

package com.fyber.requesters.p097a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fyber.Fyber;
import com.fyber.utils.C2646d;
import com.fyber.utils.C2664l;
import com.fyber.utils.C2672t;
import com.fyber.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ContainerRequest */
public final class C2623c implements C2579k {
    protected String f6527a;
    protected String f6528b;
    protected boolean f6529c;
    protected int[] f6530d;
    protected Map<String, Object> f6531e;
    private String f6532f;
    private C2634m f6533g;

    public final /* synthetic */ C2579k mo3973b(String str, Object obj) {
        return m8402a(str, obj);
    }

    public C2623c(C2623c c2623c) {
        this.f6527a = c2623c.f6527a;
        this.f6528b = c2623c.f6528b;
        this.f6529c = c2623c.f6529c;
        this.f6530d = c2623c.f6530d;
        if (C2664l.m8521b(c2623c.f6531e)) {
            this.f6531e = new HashMap(c2623c.f6531e);
        }
    }

    public final C2623c m8411b(String str) {
        this.f6528b = str;
        return this;
    }

    public final C2623c m8405a(boolean z) {
        this.f6529c = z;
        return this;
    }

    public final C2623c m8414c(String str) {
        this.f6527a = str;
        return this;
    }

    public final C2623c m8406a(int... iArr) {
        this.f6530d = iArr;
        return this;
    }

    public final C2623c m8417d(String str) {
        this.f6532f = str;
        return this;
    }

    public final String m8413b() {
        return this.f6532f;
    }

    public final String mo3972a() {
        return this.f6528b;
    }

    public final boolean m8415c() {
        return this.f6529c;
    }

    public final C2623c m8404a(Map<String, String> map) {
        Map g = m8421g();
        if (C2664l.m8520a(g)) {
            g = new HashMap();
            m8401i().put("CUSTOM_PARAMS_KEY", g);
        }
        g.putAll(map);
        return this;
    }

    public final C2623c m8403a(String str, String str2) {
        Map g = m8421g();
        if (g == null) {
            g = new HashMap();
            m8401i().put("CUSTOM_PARAMS_KEY", g);
        }
        g.put(str, str2);
        return this;
    }

    public final C2623c m8416d() {
        Map g = m8421g();
        if (g != null) {
            g.clear();
        }
        return this;
    }

    public final C2623c m8418e(String str) {
        Map g = m8421g();
        if (g != null) {
            g.remove(str);
        }
        return this;
    }

    public final C2623c m8402a(String str, Object obj) {
        if (StringUtils.notNullNorEmpty(str)) {
            m8401i().put(str, obj);
        }
        return this;
    }

    public final C2634m m8419e() {
        if (this.f6533g == null) {
            m8420f();
        }
        return this.f6533g;
    }

    public final C2623c m8420f() {
        this.f6533g = new C2634m(C2672t.m8534a(C2646d.m8469a(this.f6528b), Fyber.getConfigs().m7608i()));
        if (Fyber.getConfigs().m7607h()) {
            Fyber.getConfigs().m7604e().m8432a(this, this.f6533g);
        }
        this.f6533g.m8436b();
        return this;
    }

    protected final Map<String, String> m8421g() {
        if (this.f6531e != null) {
            return (Map) m8401i().get("CUSTOM_PARAMS_KEY");
        }
        return null;
    }

    private Map<String, Object> m8401i() {
        if (this.f6531e == null) {
            this.f6531e = new HashMap();
        }
        return this.f6531e;
    }

    public final <T> T mo3970a(@NonNull String str) {
        if (this.f6531e == null || this.f6531e.get(str) == null) {
            return Fyber.getConfigs().m7598a(str);
        }
        return this.f6531e.get(str);
    }

    public final <T> T m8408a(String str, Class<T> cls) {
        if (C2664l.m8521b(this.f6531e)) {
            Object obj = this.f6531e.get(str);
            if (obj != null && cls.isAssignableFrom(obj.getClass())) {
                return cls.cast(obj);
            }
        }
        return null;
    }

    public final <T> T mo3971a(String str, Class<T> cls, T t) {
        T a = m8408a(str, (Class) cls);
        return a != null ? a : t;
    }

    @Nullable
    public final String m8422h() {
        return this.f6527a;
    }
}

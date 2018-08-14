package com.fyber.p094b;

import com.fyber.utils.C2657h;
import com.fyber.utils.C2669q;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: SignedResponseNetworkOperation */
public abstract class C2506l<T, V> extends C2478g<V> {
    protected String f6229a;

    protected abstract T mo3926a(int i, String str, String str2);

    protected abstract V mo3927a(T t);

    protected abstract T mo3928a(String str);

    public C2506l(C2672t c2672t, String str) {
        super(c2672t);
        if (StringUtils.nullOrEmpty(str)) {
            throw new IllegalArgumentException("Security token must be set");
        }
        this.f6229a = str;
    }

    public C2506l(C2672t c2672t, String str, Map<String, String> map) {
        super(c2672t.m8547e(), map);
        if (StringUtils.nullOrEmpty(str)) {
            throw new IllegalArgumentException("Security token must be set");
        }
        this.f6229a = str;
    }

    protected final V mo3899a(C2657h c2657h) throws IOException {
        Object a;
        int b = c2657h.m8464b();
        String str = (String) c2657h.m8466c();
        List a2 = c2657h.m8463a("X-Sponsorpay-Response-Signature");
        String str2 = (a2 == null || a2.size() <= 0) ? "" : (String) a2.get(0);
        FyberLogger.m8448d(b_(), String.format(Locale.ENGLISH, "Server Response, status code: %d, response body: %s, signature: %s", new Object[]{Integer.valueOf(b), str, str2}));
        if (C2506l.m7972a(b) || !m7977a(str, str2)) {
            a = mo3926a(b, str, str2);
        } else {
            a = mo3928a(str);
        }
        return mo3927a(a);
    }

    protected final boolean m7977a(String str, String str2) {
        return C2669q.m8529a(str, this.f6229a).equals(str2);
    }

    protected static boolean m7972a(int i) {
        return i < 200 || i > 299;
    }
}

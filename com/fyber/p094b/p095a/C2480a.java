package com.fyber.p094b.p095a;

import android.support.annotation.NonNull;
import com.fyber.ads.internal.C2424c;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2479c.C2476a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2602f;
import java.util.HashMap;
import java.util.Map;

/* compiled from: CacheEventNetworkOperation */
public final class C2480a extends C2479c {

    /* compiled from: CacheEventNetworkOperation */
    public static class C2477a extends C2476a<C2480a, C2477a> {
        protected final /* bridge */ /* synthetic */ C2476a mo3897b() {
            return this;
        }

        public C2477a(@NonNull C2424c c2424c, @NonNull C2602f<?, C2623c> c2602f) {
            super(c2424c.toString(), (String) ((C2623c) c2602f.m8344c()).m8408a("TRACKING_URL_KEY", String.class));
            m7860a(c2602f.m8345d() == 1 ? "no_fill" : "fill");
            this.b.m8543b(((C2623c) c2602f.m8344c()).m8413b());
            Map hashMap = new HashMap(4);
            hashMap.put("cache_age", String.valueOf(System.currentTimeMillis() - c2602f.m8342b()));
            hashMap.put("cache_result", "hit");
            hashMap.put("ttl", ((C2623c) c2602f.m8344c()).mo3970a("CACHE_TTL").toString());
            hashMap.put("hits", String.valueOf(c2602f.m8346e()));
            m7861a(hashMap);
        }

        public final C2480a m7868d() {
            return (C2480a) super.mo3898c();
        }

        public final /* bridge */ /* synthetic */ C2479c mo3898c() {
            return (C2480a) super.mo3898c();
        }

        protected final /* synthetic */ C2479c mo3896a() {
            return new C2480a(this);
        }
    }

    private C2480a(C2476a c2476a) {
        super(c2476a);
    }

    protected final String b_() {
        return "CacheEventNetworkOperation";
    }
}

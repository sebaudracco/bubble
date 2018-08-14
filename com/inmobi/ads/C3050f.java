package com.inmobi.ads;

import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3159a;
import com.inmobi.commons.core.utilities.info.C3163e;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.signals.LocationInfo;
import com.inmobi.signals.p119a.C3247c;
import com.inmobi.signals.p120b.C3255b;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/* compiled from: AdNetworkRequest */
public final class C3050f extends NetworkRequest {
    private static final String f7417d = C3050f.class.getSimpleName();
    private long f7418e;
    private String f7419f = "json";
    private String f7420g;
    private int f7421h = 1;
    private String f7422i;
    private String f7423j;
    private Map<String, String> f7424k;
    private Map<String, String> f7425l;
    private final String f7426m;
    private MonetizationContext f7427n;

    public C3050f(String str, long j, C3169d c3169d) {
        super(RequestType.POST, str, true, c3169d, true);
        this.f7418e = j;
        this.c.put("im-plid", String.valueOf(this.f7418e));
        this.c.putAll(C3163e.m10462g());
        this.c.putAll(DisplayInfo.m10423c());
        this.c.put("u-appIS", C3159a.m10427a().m10430b());
        this.c.putAll(LocationInfo.m10813a().m10833g());
        this.c.putAll(LocationInfo.m10813a().m10832f());
        this.c.putAll(C3255b.m10898b());
        this.c.putAll(C3255b.m10901d());
        this.c.putAll(C3247c.m10853c());
        this.c.putAll(C3247c.m10854d());
        this.c.putAll(C3247c.m10848a());
        this.f7426m = UUID.randomUUID().toString();
        this.c.put("client-request-id", this.f7426m);
        this.c.put("sdk-flavor", "row");
    }

    public void mo6238a() {
        Object obj;
        super.mo6238a();
        this.c.put("format", this.f7419f);
        this.c.put("mk-ads", String.valueOf(this.f7421h));
        this.c.put("adtype", this.f7422i);
        if (this.f7423j != null) {
            this.c.put("p-keywords", this.f7423j);
        }
        if (this.f7427n == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7417d, "Null m10 context using activity as default");
            obj = "M10N_CONTEXT_ACTIVITY";
        } else if (this.f7427n == MonetizationContext.MONETIZATION_CONTEXT_OTHER) {
            obj = "M10N_CONTEXT_OTHER";
        } else {
            obj = "M10N_CONTEXT_ACTIVITY";
        }
        this.c.put("m10n_context", obj);
        if (this.f7424k != null) {
            for (Entry entry : this.f7424k.entrySet()) {
                if (!this.c.containsKey(entry.getKey())) {
                    this.c.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.f7425l != null) {
            this.c.putAll(this.f7425l);
        }
    }

    public void mo6239a(String str) {
        this.f7422i = str;
    }

    public String m9778b() {
        return this.f7422i;
    }

    public void m9779b(String str) {
        this.f7419f = str;
    }

    public String m9781c() {
        return this.f7420g;
    }

    public void m9782c(String str) {
        this.f7420g = str;
    }

    public void m9774a(int i) {
        this.f7421h = i;
    }

    public int m9783d() {
        return this.f7421h;
    }

    public long m9785e() {
        return this.f7418e;
    }

    public void m9784d(String str) {
        this.f7423j = str;
    }

    public void mo6240a(Map<String, String> map) {
        this.f7424k = map;
    }

    public void m9780b(Map<String, String> map) {
        this.f7425l = map;
    }

    public void m9775a(MonetizationContext monetizationContext) {
        this.f7427n = monetizationContext;
    }

    public String m9786f() {
        return this.f7426m;
    }
}

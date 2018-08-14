package com.fyber.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2424c;
import com.fyber.ads.internal.Offer;
import com.fyber.mediation.C2573a;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2604g;
import com.fyber.requesters.p097a.p098a.C2610j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: AdRequestResponse */
public abstract class C2410a<A extends Ad<A, ?>> {
    protected A f6009a;
    protected int f6010b = -1;
    protected int f6011c = -1;
    protected C2623c f6012d;
    private int f6013e;
    private List<Offer> f6014f;
    private boolean f6015g;
    private C2604g f6016h;

    protected abstract C2482a<? extends C2479c, ? extends C2482a<?, ?>> mo3849b(@NonNull C2423a c2423a);

    protected abstract A mo3851h();

    protected C2410a(C2623c c2623c, List<Offer> list) {
        this.f6012d = c2623c;
        this.f6014f = list;
        this.f6015g = true;
    }

    public final int m7611a() {
        return this.f6013e;
    }

    public final List<Offer> m7619b() {
        return this.f6014f;
    }

    public final <T extends C2410a<A>> T m7612a(int i) {
        this.f6013e = i;
        return this;
    }

    public final <T extends C2410a<A>> T m7613a(C2604g c2604g) {
        this.f6016h = c2604g;
        return this;
    }

    public final C2604g m7620c() {
        return this.f6016h;
    }

    public final String m7621d() {
        return this.f6012d.m8422h();
    }

    private int mo3852k() {
        int size = this.f6014f.size();
        for (int i = 0; i < size; i++) {
            Offer offer = (Offer) this.f6014f.get(i);
            if (offer != null && offer.getProviderStatus() == 0) {
                return i;
            }
        }
        return -1;
    }

    public final boolean m7622e() {
        return this.f6015g;
    }

    public final C2623c m7623f() {
        return this.f6012d;
    }

    public final boolean m7624g() {
        return mo3852k() != -1;
    }

    public final A m7626i() {
        if (this.f6009a == null) {
            this.f6009a = mo3851h();
        }
        return this.f6009a;
    }

    @Nullable
    public final Offer m7627j() {
        int i;
        if (this.f6010b == -1) {
            this.f6010b = mo3852k();
            int i2 = (this.f6010b == this.f6011c || this.f6011c == -1) ? 0 : 1;
            i = i2;
        } else {
            i = 0;
        }
        if (this.f6010b == -1) {
            return null;
        }
        Offer offer = (Offer) this.f6014f.get(this.f6010b);
        if (i == 0) {
            return offer;
        }
        Map hashMap = new HashMap();
        C2610j b = C2573a.f6454a.m8221b(offer.getProviderType(), m7626i().getAdFormat());
        if (b != null) {
            hashMap.putAll(C2424c.m7673a(0, b.m8376b(offer.getProviderRequest().mo3972a())));
        }
        C2410a.m7609a((C2482a) ((C2482a) mo3849b(C2423a.ValidationFill).m7860a("show")).m7861a(hashMap), offer);
        return offer;
    }

    protected final void m7614a(@NonNull C2423a c2423a) {
        m7616a(c2423a, null, null);
    }

    protected final void m7615a(@NonNull C2423a c2423a, String str) {
        m7616a(c2423a, str, null);
    }

    protected final void m7616a(@NonNull C2423a c2423a, String str, Map<String, String> map) {
        C2410a.m7609a((C2482a) ((C2482a) mo3849b(c2423a).m7860a(str)).m7861a((Map) map), m7627j());
    }

    private static void m7609a(C2482a c2482a, Offer offer) {
        c2482a.m7890a(offer).mo3907b();
    }

    public final <T extends C2410a<A>> T m7617b(int i) {
        this.f6011c = i;
        return this;
    }
}

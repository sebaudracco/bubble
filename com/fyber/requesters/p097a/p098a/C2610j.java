package com.fyber.requesters.p097a.p098a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fyber.Fyber;
import com.fyber.ads.internal.Offer;
import com.fyber.mediation.ProviderRequester;
import com.fyber.mediation.ProviderRequesterListener;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.requesters.p097a.p098a.C2611k.C2607a;
import com.fyber.utils.FyberLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* compiled from: NetworkAgent */
public final class C2610j<R, E extends Exception> implements ProviderRequesterListener<R, E> {
    private C2611k<R, E> f6507a;
    private ProviderRequester<R, E> f6508b;
    private List<C2596p<C2580a>> f6509c;
    private List<C2595e<C2580a>> f6510d;
    private Map<String, C2602f<C2611k<R, E>, C2580a>> f6511e;
    private final C2490b<R> f6512f;
    private C2580a f6513g;

    /* compiled from: NetworkAgent */
    public interface C2490b<R> {
        void mo3919a(@Nullable R r, Offer offer);
    }

    /* compiled from: NetworkAgent */
    public static class C2609a<R, E extends Exception> {
        ProviderRequester<R, E> f6503a;
        List<C2596p<C2580a>> f6504b = new ArrayList();
        List<C2595e<C2580a>> f6505c = new ArrayList();
        private C2490b<R> f6506d;

        public C2609a(ProviderRequester<R, E> providerRequester) {
            this.f6503a = providerRequester;
        }

        public final C2609a<R, E> m8369a(List<C2596p<C2580a>> list) {
            this.f6504b.addAll(list);
            return this;
        }

        public final C2609a<R, E> m8371b(List<C2595e<C2580a>> list) {
            this.f6505c.addAll(list);
            return this;
        }

        public final C2609a<R, E> m8368a(C2490b<R> c2490b) {
            this.f6506d = c2490b;
            return this;
        }

        public final C2610j<R, E> m8370a() {
            return new C2610j();
        }
    }

    private C2610j(C2609a<R, E> c2609a) {
        this.f6511e = new HashMap(1);
        this.f6509c = c2609a.f6504b;
        this.f6510d = c2609a.f6505c;
        this.f6508b = c2609a.f6503a;
        this.f6512f = c2609a.f6506d;
        this.f6508b.setProviderRequesterListener(this);
    }

    public final void setAdAvailable(@NonNull R r, @NonNull C2580a c2580a) {
        if (this.f6507a != null) {
            this.f6507a.m8379a((Object) r);
            c2580a.m8228a("responded_at", Long.valueOf(System.currentTimeMillis()));
            c2580a.m8228a("PROVIDER_STATUS", Integer.valueOf(0));
            C2602f a = new C2602f(this.f6507a).m8340a((C2579k) c2580a).m8339a(0);
            String a2 = c2580a.mo3972a();
            C2602f c2602f = (C2602f) this.f6511e.get(a2);
            if (c2602f != null && c2602f.m8345d() == 0) {
                a.m8343b(c2602f.m8348g() + 1);
            }
            this.f6511e.put(a2, a);
            this.f6507a = null;
        }
    }

    public final void setAdNotAvailable(@NonNull C2580a c2580a) {
        if (this.f6507a != null) {
            this.f6507a.m8379a(null);
            c2580a.m8228a("responded_at", Long.valueOf(System.currentTimeMillis()));
            c2580a.m8228a("PROVIDER_STATUS", Integer.valueOf(1));
            this.f6507a = null;
        }
    }

    public final void setAdError(@NonNull E e, @NonNull C2580a c2580a) {
        if (this.f6507a != null) {
            this.f6507a.m8378a((Exception) e);
            c2580a.m8228a("responded_at", Long.valueOf(System.currentTimeMillis()));
            c2580a.m8228a("PROVIDER_STATUS", Integer.valueOf(2));
            this.f6507a = null;
        }
    }

    public final C2602f<C2611k<R, E>, C2580a> m8374a(String str) {
        FyberLogger.m8448d("NetworkAgent", "Removing network entry for cacheKey = " + str);
        return (C2602f) this.f6511e.remove(str);
    }

    public final C2602f<C2611k<R, E>, C2580a> m8376b(String str) {
        return (C2602f) this.f6511e.get(str);
    }

    private boolean m8373a(C2602f<C2611k<R, E>, C2580a> c2602f, C2580a c2580a) {
        for (C2595e a : this.f6510d) {
            if (!a.mo3995a(c2602f, c2580a)) {
                return false;
            }
        }
        return true;
    }

    public final Future<Boolean> m8375a(@NonNull Context context, @NonNull final Offer offer) {
        Object obj;
        C2607a c26081 = new C2607a<R, E>(this) {
            final /* synthetic */ C2610j f6502b;

            public final void mo3998a(R r) {
                if (r != null) {
                    offer.getProviderRequest().m8228a("PROVIDER_STATUS", Integer.valueOf(0));
                } else {
                    offer.getProviderRequest().m8228a("PROVIDER_STATUS", Integer.valueOf(1));
                }
                if (this.f6502b.f6512f != null) {
                    this.f6502b.f6512f.mo3919a(r, offer);
                }
            }

            public final void mo3997a() {
                offer.getProviderRequest().m8228a("PROVIDER_STATUS", Integer.valueOf(2));
            }
        };
        C2579k c2579k = this.f6513g;
        C2579k providerRequest = offer.getProviderRequest();
        if (c2579k == null || providerRequest == null) {
            obj = 1;
        } else if (((Long) c2579k.mo3971a("responded_at", Long.class, Long.valueOf(-1))).longValue() != -1) {
            r0 = 1;
        } else {
            for (C2596p a : this.f6509c) {
                if (a.mo3996a(c2579k, providerRequest)) {
                    r0 = 1;
                    break;
                }
            }
            obj = null;
        }
        if (obj == null) {
            FyberLogger.m8448d("NetworkAgent", "There is an ongoing request, not forwarding the incoming one...");
            this.f6507a.m8377a(c26081);
            return new FutureTask(this.f6507a);
        }
        this.f6513g = offer.getProviderRequest();
        C2602f c2602f = (C2602f) this.f6511e.get(this.f6513g.mo3972a());
        if (c2602f == null || !m8373a(c2602f, this.f6513g)) {
            this.f6507a = new C2611k();
            this.f6507a.m8377a(c26081);
            this.f6513g.m8228a("requested_at", Long.valueOf(System.currentTimeMillis()));
            Future<Boolean> a2 = Fyber.getConfigs().m7599a(this.f6507a);
            this.f6508b.isAdAvailable(context, this.f6513g);
            return a2;
        }
        c2602f.m8347f();
        C2611k c2611k = (C2611k) c2602f.m8341a();
        c2611k.m8377a(c26081);
        Runnable futureTask = new FutureTask(c2611k);
        Fyber.getConfigs().m7600a(futureTask);
        return futureTask;
    }
}

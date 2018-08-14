package com.fyber.requesters.p097a;

import android.os.Handler;
import com.fyber.C2409a;
import com.fyber.Fyber;
import com.fyber.ads.C2410a;
import com.fyber.requesters.Callback;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.p097a.p098a.C2602f;
import com.fyber.utils.C2412c;

/* compiled from: DispatchableCallback */
public abstract class C2588f<U, V> {
    private C2623c f6470a;
    protected final Class<? extends Callback>[] f6471b;
    protected Callback f6472c;
    protected Handler f6473d;

    protected abstract void mo3989a(V v);

    protected abstract void mo3990b(U u);

    public C2588f(Class<? extends Callback>... clsArr) {
        this.f6471b = clsArr;
    }

    public final C2588f<U, V> m8278a(Handler handler) {
        this.f6473d = handler;
        return this;
    }

    public final C2588f<U, V> m8279a(Callback callback) {
        this.f6472c = callback;
        return this;
    }

    public final C2588f<U, V> m8281a(C2588f c2588f) {
        this.f6472c = c2588f.f6472c;
        return this;
    }

    public final C2588f<U, V> m8280a(C2623c c2623c) {
        this.f6470a = c2623c;
        return this;
    }

    public final void m8282a(final RequestError requestError) {
        m8277a(new C2412c(this) {
            final /* synthetic */ C2588f f6535b;

            public final void mo3844a() {
                this.f6535b.f6472c.onRequestError(requestError);
            }
        });
    }

    public final void m8287c(final U u) {
        if (this.f6470a.m8415c()) {
            if (!(u instanceof C2410a)) {
                Fyber.getConfigs().m7603d().m8387a(u, this.f6470a);
            } else if (((C2410a) u).m7622e()) {
                Fyber.getConfigs().m7603d().m8387a(u, this.f6470a);
            }
        }
        m8277a(new C2412c(this) {
            final /* synthetic */ C2588f f6537b;

            public final void mo3844a() {
                this.f6537b.mo3990b(u);
            }
        });
    }

    public final void m8288d(final V v) {
        if (this.f6470a.m8415c()) {
            Fyber.getConfigs().m7603d().m8389b(v, this.f6470a);
        }
        m8277a(new C2412c(this) {
            final /* synthetic */ C2588f f6539b;

            public final void mo3844a() {
                this.f6539b.mo3989a(v);
            }
        });
    }

    public final void m8283a(final C2602f<?, ?> c2602f) {
        m8277a(new C2412c(this) {
            final /* synthetic */ C2588f f6541b;

            public final void mo3844a() {
                switch (c2602f.m8345d()) {
                    case 0:
                        this.f6541b.mo3990b(c2602f.m8341a());
                        return;
                    case 1:
                        this.f6541b.mo3989a(c2602f.m8341a());
                        return;
                    default:
                        this.f6541b.f6472c.onRequestError(RequestError.UNKNOWN_ERROR);
                        return;
                }
            }
        });
    }

    public final boolean m8285a() {
        if (this.f6472c == null) {
            return false;
        }
        for (Class isAssignableFrom : this.f6471b) {
            if (isAssignableFrom.isAssignableFrom(this.f6472c.getClass())) {
                return true;
            }
        }
        return false;
    }

    private void m8277a(C2412c c2412c) {
        if (this.f6473d != null) {
            this.f6473d.post(c2412c);
            return;
        }
        Fyber.getConfigs();
        C2409a.m7595b(c2412c);
    }
}

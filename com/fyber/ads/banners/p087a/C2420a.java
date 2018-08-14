package com.fyber.ads.banners.p087a;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.fyber.C2409a;
import com.fyber.Fyber;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.banners.BannerAd;
import com.fyber.ads.banners.BannerAdListener;
import com.fyber.ads.banners.mediation.BannerWrapper;
import com.fyber.ads.internal.C2419b;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2424c;
import com.fyber.ads.internal.C2425d;
import com.fyber.ads.internal.Offer;
import com.fyber.mediation.C2573a;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.p096b.C2485a.C2483a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2602f;
import com.fyber.requesters.p097a.p098a.C2610j;
import com.fyber.utils.C2412c;
import com.fyber.utils.FyberLogger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BannerAdRequestResponse */
public final class C2420a extends C2410a<BannerAd> implements C2417c, C2418d, C2419b<BannerAdListener> {
    private BannerAdListener f6033e;
    private AtomicInteger f6034f = new AtomicInteger(0);
    private FrameLayout f6035g;
    private ViewGroup f6036h;
    private BannerWrapper f6037i;
    private int f6038j = 80;

    /* compiled from: BannerAdRequestResponse */
    class C24131 extends C2412c {
        final /* synthetic */ C2420a f6027a;

        C24131(C2420a c2420a) {
            this.f6027a = c2420a;
        }

        public final void mo3844a() {
            if (this.f6027a.f6036h != null) {
                this.f6027a.f6036h.setVisibility(8);
            } else if (this.f6027a.f6035g != null) {
                this.f6027a.f6035g.setVisibility(8);
            }
        }
    }

    /* compiled from: BannerAdRequestResponse */
    class C24142 extends C2412c {
        final /* synthetic */ C2420a f6028a;

        C24142(C2420a c2420a) {
            this.f6028a = c2420a;
        }

        public final void mo3844a() {
            if (this.f6028a.f6036h != null) {
                this.f6028a.f6036h.setVisibility(0);
            } else if (this.f6028a.f6035g != null) {
                this.f6028a.f6035g.setVisibility(0);
            }
        }
    }

    /* compiled from: BannerAdRequestResponse */
    class C24164 extends C2412c {
        final /* synthetic */ C2420a f6032a;

        C24164(C2420a c2420a) {
            this.f6032a = c2420a;
        }

        public final void mo3844a() {
            View view = this.f6032a.f6037i.getView();
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            if (this.f6032a.f6035g != null) {
                viewGroup = (ViewGroup) this.f6032a.f6035g.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
                this.f6032a.f6035g = null;
            }
            this.f6032a.f6037i.destroy();
            this.f6032a.f6037i = null;
            C2421b.m7671a(C2425d.READY_TO_CHECK_OFFERS);
        }
    }

    public C2420a(C2623c c2623c, List<Offer> list) {
        super(c2623c, list);
    }

    public final void mo3846a(ViewGroup viewGroup) {
        if (this.f6037i == null) {
            m7651b("This BannerAd appears to have been already destroyed");
        } else {
            this.f6036h = viewGroup;
        }
    }

    public final void mo3850c(int i) {
        if (this.f6037i == null) {
            m7651b("This BannerAd appears to have been already destroyed");
        } else if (i == 80 || i == 48) {
            this.f6038j = i;
        }
    }

    public final void mo3852k() {
        if (this.f6037i != null) {
            Fyber.getConfigs();
            C2409a.m7595b(new C24131(this));
            return;
        }
        m7651b("This BannerAd appears to have been already destroyed");
    }

    public final void mo3853l() {
        if (this.f6037i != null) {
            Fyber.getConfigs();
            C2409a.m7595b(new C24142(this));
            return;
        }
        m7651b("This BannerAd appears to have been already destroyed");
    }

    public final void mo3845a(final Activity activity) {
        if (this.f6037i == null) {
            m7651b("There's no BannerWrapper for this BannerAd - this banner will not be shown");
        } else if (C2421b.m7670a().m7675a()) {
            this.f6037i.setBannerEventListener(this);
            C2602f c2602f = null;
            if (this.d != null) {
                c2602f = Fyber.getConfigs().m7603d().m8388a(this.d.mo3972a());
            }
            final Map a = C2424c.m7674a(c2602f);
            Fyber.getConfigs();
            C2409a.m7595b(new C2412c(this) {
                final /* synthetic */ C2420a f6031c;

                public final void mo3844a() {
                    if (this.f6031c.f6036h != null) {
                        this.f6031c.f6036h.addView(this.f6031c.f6037i.getView());
                    } else {
                        this.f6031c.f6035g = new FrameLayout(activity.getApplicationContext());
                        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, this.f6031c.f6038j | 1);
                        this.f6031c.f6035g.addView(this.f6031c.f6037i.getView());
                        activity.addContentView(this.f6031c.f6035g, layoutParams);
                    }
                    this.f6031c.m7616a(C2423a.ShowImpression, null, a);
                    C2420a c2420a = this.f6031c;
                    this.f6031c.f6037i.getView();
                    c2420a.mo3856o();
                }
            });
            Offer j = m7627j();
            if (j == null) {
                mo3848a("There is no offer to show");
                return;
            }
            C2580a providerRequest = j.getProviderRequest();
            C2610j b = C2573a.f6454a.m8221b(j.getProviderType(), AdFormat.BANNER);
            if (b != null) {
                a.putAll(C2424c.m7673a(1, b.m8374a(providerRequest.mo3972a())));
            }
            C2421b.m7671a(C2425d.SHOWING_OFFERS);
        } else if (C2421b.m7670a().equals(C2425d.SHOWING_OFFERS)) {
            mo3848a("A banner is already being displayed");
        } else if (C2421b.m7670a().equals(C2425d.REQUESTING_OFFERS)) {
            mo3848a("A request operation is in progress");
        } else {
            mo3848a("Unknown error occurred");
        }
    }

    public final void mo3854m() {
        if (this.f6037i != null) {
            if (this.d != null) {
                Fyber.getConfigs().m7603d().m8388a(this.d.mo3972a());
            }
            Fyber.getConfigs();
            C2409a.m7595b(new C24164(this));
            FyberLogger.m8448d("BannerAdRequestResponse", "\"destroy()\" has been called on this BannerAd instance");
            Offer j = m7627j();
            if (j == null) {
                mo3848a("There is no offer to show");
                return;
            }
            C2580a providerRequest = j.getProviderRequest();
            C2610j b = C2573a.f6454a.m8221b(j.getProviderType(), AdFormat.BANNER);
            if (b != null) {
                b.m8374a(providerRequest.mo3972a());
                return;
            }
            return;
        }
        m7651b("\"destroy()\" was already called on this BannerAd instance");
    }

    public final boolean mo3855n() {
        return this.f6037i != null && C2421b.m7670a().m7675a();
    }

    public final void mo3856o() {
        if (!this.f6034f.compareAndSet(0, 1)) {
            m7616a(C2423a.ShowRotation, null, Collections.singletonMap("position", String.valueOf(this.f6034f.getAndIncrement())));
        }
        if (this.f6033e != null) {
            this.f6033e.onAdLoaded((BannerAd) this.a);
        }
    }

    public final void mo3857p() {
        m7614a(C2423a.ShowClick);
        if (this.f6033e != null) {
            this.f6033e.onAdClicked((BannerAd) this.a);
        }
    }

    public final void mo3848a(String str) {
        m7614a(C2423a.ShowError);
        if (this.f6033e != null) {
            this.f6033e.onAdError((BannerAd) this.a, str);
        }
    }

    public final void mo3858q() {
        if (this.f6033e != null) {
            this.f6033e.onAdLeftApplication((BannerAd) this.a);
        }
    }

    protected final C2482a<? extends C2479c, ? extends C2482a<?, ?>> mo3849b(@NonNull C2423a c2423a) {
        return new C2483a(c2423a);
    }

    private void m7651b(String str) {
        FyberLogger.m8448d("BannerAdRequestResponse", str);
        mo3848a("The \"destroy()\" method appears to have been already called");
    }

    public final void m7657a(BannerWrapper bannerWrapper) {
        this.f6037i = bannerWrapper;
    }

    public final /* synthetic */ Ad mo3851h() {
        return new BannerAd(m7621d(), this, this);
    }
}

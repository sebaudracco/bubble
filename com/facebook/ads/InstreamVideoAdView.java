package com.facebook.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1830a;
import com.facebook.ads.internal.adapters.C1913u;
import com.facebook.ads.internal.adapters.C1914l;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.internal.view.p034b.C2185c;
import com.facebook.ads.p051a.C1835a;
import java.util.EnumSet;

public class InstreamVideoAdView extends RelativeLayout implements Ad {
    private final Context f3986a;
    private final String f3987b;
    private final AdSize f3988c;
    private DisplayAdController f3989d;
    @Nullable
    private C1914l f3990e;
    private boolean f3991f;
    @Nullable
    private InstreamVideoAdListener f3992g;
    @Nullable
    private View f3993h;
    @Nullable
    private Bundle f3994i;
    @Nullable
    private C2185c f3995j;

    class C18341 extends C1830a {
        final /* synthetic */ InstreamVideoAdView f3984a;

        class C18331 implements OnLongClickListener {
            final /* synthetic */ C18341 f3983a;

            C18331(C18341 c18341) {
                this.f3983a = c18341;
            }

            public boolean onLongClick(View view) {
                boolean z = false;
                if (this.f3983a.f3984a.f3993h == null || this.f3983a.f3984a.f3995j == null) {
                    return false;
                }
                this.f3983a.f3984a.f3995j.setBounds(0, 0, this.f3983a.f3984a.f3993h.getWidth(), this.f3983a.f3984a.f3993h.getHeight());
                C2185c f = this.f3983a.f3984a.f3995j;
                if (!this.f3983a.f3984a.f3995j.m6997a()) {
                    z = true;
                }
                f.m6996a(z);
                return true;
            }
        }

        C18341(InstreamVideoAdView instreamVideoAdView) {
            this.f3984a = instreamVideoAdView;
        }

        public void mo3551a() {
            if (this.f3984a.f3992g != null) {
                this.f3984a.f3992g.onAdClicked(this.f3984a);
            }
        }

        public void mo3552a(View view) {
            if (view == null) {
                throw new IllegalStateException("Cannot present null view");
            }
            this.f3984a.f3993h = view;
            this.f3984a.removeAllViews();
            this.f3984a.f3993h.setLayoutParams(new LayoutParams(-1, -1));
            this.f3984a.addView(this.f3984a.f3993h);
            if (C2005a.m6340b(this.f3984a.f3986a)) {
                this.f3984a.f3995j = new C2185c();
                this.f3984a.f3995j.m6995a(this.f3984a.f3987b);
                this.f3984a.f3995j.m6999b(this.f3984a.f3986a.getPackageName());
                if (this.f3984a.f3989d.m5608a() != null) {
                    this.f3984a.f3995j.m6993a(this.f3984a.f3989d.m5608a().m6292a());
                }
                this.f3984a.f3993h.getOverlay().add(this.f3984a.f3995j);
                this.f3984a.f3993h.setOnLongClickListener(new C18331(this));
            }
        }

        public void mo3553a(AdAdapter adAdapter) {
            if (this.f3984a.f3989d != null) {
                this.f3984a.f3991f = true;
                if (this.f3984a.f3992g != null) {
                    this.f3984a.f3992g.onAdLoaded(this.f3984a);
                }
            }
        }

        public void mo3554a(C2097a c2097a) {
            if (this.f3984a.f3992g != null) {
                this.f3984a.f3992g.onError(this.f3984a, AdError.getAdErrorFromWrapper(c2097a));
            }
        }

        public void mo3555b() {
            if (this.f3984a.f3992g != null) {
                this.f3984a.f3992g.onLoggingImpression(this.f3984a);
            }
        }

        public void mo3563c() {
            if (this.f3984a.f3992g != null) {
                this.f3984a.f3992g.onAdVideoComplete(this.f3984a);
            }
        }
    }

    class C18362 implements C1835a {
        final /* synthetic */ InstreamVideoAdView f3985a;

        C18362(InstreamVideoAdView instreamVideoAdView) {
            this.f3985a = instreamVideoAdView;
        }

        public void mo3564a(C1913u c1913u) {
            this.f3985a.f3991f = true;
            if (this.f3985a.f3992g != null) {
                this.f3985a.f3992g.onAdLoaded(this.f3985a);
            }
        }

        public void mo3565a(C1913u c1913u, View view) {
            if (view == null) {
                throw new IllegalStateException("Cannot present null view");
            }
            this.f3985a.f3993h = view;
            this.f3985a.removeAllViews();
            this.f3985a.f3993h.setLayoutParams(new LayoutParams(-1, -1));
            this.f3985a.addView(this.f3985a.f3993h);
        }

        public void mo3566a(C1913u c1913u, AdError adError) {
            if (this.f3985a.f3992g != null) {
                this.f3985a.f3992g.onError(this.f3985a, adError);
            }
        }

        public void mo3567b(C1913u c1913u) {
            if (this.f3985a.f3992g != null) {
                this.f3985a.f3992g.onAdClicked(this.f3985a);
            }
        }

        public void mo3568c(C1913u c1913u) {
        }

        public void mo3569d(C1913u c1913u) {
            if (this.f3985a.f3992g != null) {
                this.f3985a.f3992g.onAdVideoComplete(this.f3985a);
            }
        }
    }

    public InstreamVideoAdView(Context context, Bundle bundle) {
        this(context, bundle.getString("placementID"), (AdSize) bundle.get("adSize"));
        this.f3994i = bundle;
    }

    public InstreamVideoAdView(Context context, String str, AdSize adSize) {
        super(context);
        this.f3991f = false;
        this.f3986a = context;
        this.f3987b = str;
        this.f3988c = adSize;
        this.f3989d = getController();
    }

    private final void m5422a() {
        if (this.f3989d != null) {
            this.f3989d.m5618b(true);
            this.f3989d = null;
            this.f3989d = getController();
            this.f3990e = null;
            this.f3991f = false;
            removeAllViews();
        }
    }

    private void m5423a(String str) {
        if (this.f3994i != null) {
            this.f3990e = new C1914l();
            this.f3990e.m5880a(getContext(), new C18362(this), this.f3989d.m5623g(), this.f3994i.getBundle("adapter"), EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.f3989d.m5614a(str);
    }

    private DisplayAdController getController() {
        this.f3989d = new DisplayAdController(getContext(), this.f3987b, f.m, AdPlacementType.INSTREAM, this.f3988c.toInternalAdSize(), C2101d.ADS, 1, true);
        this.f3989d.m5611a(new C18341(this));
        return this.f3989d;
    }

    public void destroy() {
        if (this.f3995j != null && C2005a.m6340b(this.f3986a)) {
            this.f3995j.m6998b();
            if (this.f3993h != null) {
                this.f3993h.getOverlay().remove(this.f3995j);
            }
        }
        m5422a();
    }

    public String getPlacementId() {
        return this.f3987b;
    }

    public Bundle getSaveInstanceState() {
        C1913u c1913u = this.f3990e != null ? this.f3990e : (C1913u) this.f3989d.m5624h();
        if (c1913u == null) {
            return null;
        }
        Bundle g = c1913u.mo3678g();
        if (g == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("adapter", g);
        bundle.putString("placementID", this.f3987b);
        bundle.putSerializable("adSize", this.f3988c);
        return bundle;
    }

    public boolean isAdLoaded() {
        return this.f3991f;
    }

    public void loadAd() {
        m5423a(null);
    }

    public void loadAdFromBid(String str) {
        m5423a(str);
    }

    public void setAdListener(InstreamVideoAdListener instreamVideoAdListener) {
        this.f3992g = instreamVideoAdListener;
    }

    public boolean show() {
        if (this.f3991f && (this.f3989d != null || this.f3990e != null)) {
            if (this.f3990e != null) {
                this.f3990e.mo3677e();
            } else {
                this.f3989d.m5616b();
            }
            this.f3991f = false;
            return true;
        } else if (this.f3992g == null) {
            return false;
        } else {
            this.f3992g.onError(this, AdError.INTERNAL_ERROR);
            return false;
        }
    }
}

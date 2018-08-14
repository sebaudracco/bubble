package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1830a;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.C2102g;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.view.p034b.C2182a;
import com.facebook.ads.internal.view.p034b.C2185c;

public class AdView extends RelativeLayout implements Ad {
    private static final C2101d f3965a = C2101d.ADS;
    private final DisplayMetrics f3966b;
    private final e f3967c;
    private final String f3968d;
    private DisplayAdController f3969e;
    private AdListener f3970f;
    private View f3971g;
    private C2185c f3972h;
    private volatile boolean f3973i;

    public AdView(Context context, final String str, AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.f3966b = getContext().getResources().getDisplayMetrics();
        this.f3967c = adSize.toInternalAdSize();
        this.f3968d = str;
        this.f3969e = new DisplayAdController(context, str, C2102g.m6756a(this.f3967c), AdPlacementType.BANNER, adSize.toInternalAdSize(), f3965a, 1, false);
        this.f3969e.m5611a(new C1830a(this) {
            final /* synthetic */ AdView f3964b;

            class C18291 implements OnLongClickListener {
                final /* synthetic */ C18311 f3962a;

                C18291(C18311 c18311) {
                    this.f3962a = c18311;
                }

                public boolean onLongClick(View view) {
                    boolean z = false;
                    this.f3962a.f3964b.f3972h.setBounds(0, 0, this.f3962a.f3964b.f3971g.getWidth(), this.f3962a.f3964b.f3971g.getHeight());
                    C2185c f = this.f3962a.f3964b.f3972h;
                    if (!this.f3962a.f3964b.f3972h.m6997a()) {
                        z = true;
                    }
                    f.m6996a(z);
                    return true;
                }
            }

            public void mo3551a() {
                if (this.f3964b.f3970f != null) {
                    this.f3964b.f3970f.onAdClicked(this.f3964b);
                }
            }

            public void mo3552a(View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                this.f3964b.f3971g = view;
                this.f3964b.removeAllViews();
                this.f3964b.addView(this.f3964b.f3971g);
                if (this.f3964b.f3971g instanceof C2182a) {
                    C2102g.m6757a(this.f3964b.f3966b, this.f3964b.f3971g, this.f3964b.f3967c);
                }
                if (this.f3964b.f3970f != null) {
                    this.f3964b.f3970f.onAdLoaded(this.f3964b);
                }
                if (C2005a.m6340b(this.f3964b.getContext())) {
                    this.f3964b.f3972h = new C2185c();
                    this.f3964b.f3972h.m6995a(str);
                    this.f3964b.f3972h.m6999b(this.f3964b.getContext().getPackageName());
                    if (this.f3964b.f3969e.m5608a() != null) {
                        this.f3964b.f3972h.m6993a(this.f3964b.f3969e.m5608a().m6292a());
                    }
                    if (this.f3964b.f3971g instanceof C2182a) {
                        this.f3964b.f3972h.m6994a(((C2182a) this.f3964b.f3971g).getViewabilityChecker());
                    }
                    this.f3964b.f3971g.setOnLongClickListener(new C18291(this));
                    this.f3964b.f3971g.getOverlay().add(this.f3964b.f3972h);
                }
            }

            public void mo3553a(AdAdapter adAdapter) {
                if (this.f3964b.f3969e != null) {
                    this.f3964b.f3969e.m5616b();
                }
            }

            public void mo3554a(C2097a c2097a) {
                if (this.f3964b.f3970f != null) {
                    this.f3964b.f3970f.onError(this.f3964b, AdError.getAdErrorFromWrapper(c2097a));
                }
            }

            public void mo3555b() {
                if (this.f3964b.f3970f != null) {
                    this.f3964b.f3970f.onLoggingImpression(this.f3964b);
                }
            }
        });
    }

    private void m5377a(String str) {
        if (!this.f3973i) {
            this.f3969e.m5614a(str);
            this.f3973i = true;
        } else if (this.f3969e != null) {
            this.f3969e.m5617b(str);
        }
    }

    public void destroy() {
        if (this.f3969e != null) {
            this.f3969e.m5618b(true);
            this.f3969e = null;
        }
        if (this.f3972h != null && C2005a.m6340b(getContext())) {
            this.f3972h.m6998b();
            this.f3971g.getOverlay().remove(this.f3972h);
        }
        removeAllViews();
        this.f3971g = null;
        this.f3970f = null;
    }

    public void disableAutoRefresh() {
        if (this.f3969e != null) {
            this.f3969e.m5622f();
        }
    }

    public String getPlacementId() {
        return this.f3968d;
    }

    public void loadAd() {
        m5377a(null);
    }

    public void loadAdFromBid(String str) {
        m5377a(str);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f3971g != null) {
            C2102g.m6757a(this.f3966b, this.f3971g, this.f3967c);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.f3969e != null) {
            if (i == 0) {
                this.f3969e.m5621e();
            } else if (i == 8) {
                this.f3969e.m5620d();
            }
        }
    }

    public void setAdListener(AdListener adListener) {
        this.f3970f = adListener;
    }
}

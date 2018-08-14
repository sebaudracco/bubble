package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p056q.p076c.C2142b;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p059a.C1877d;
import com.facebook.ads.internal.p065h.C1990d;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.view.p034b.C2182a;
import com.facebook.ads.internal.view.p034b.C2182a.C1901b;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

public class C1904k extends BannerAdapter {
    private static final String f4281a = C1904k.class.getSimpleName();
    @Nullable
    private C1901b f4282b;
    @Nullable
    private C2182a f4283c;
    private C1933s f4284d;
    private BannerAdapterListener f4285e;
    private Map<String, Object> f4286f;
    @Nullable
    private C2012c f4287g;
    private Context f4288h;
    private long f4289i;
    private C1996a f4290j;

    class C19032 extends C1885c {
        final /* synthetic */ C1904k f4280a;

        C19032(C1904k c1904k) {
            this.f4280a = c1904k;
        }

        public void mo3673a() {
            if (this.f4280a.f4285e != null) {
                this.f4280a.f4285e.onBannerLoggingImpression(this.f4280a);
            }
        }
    }

    private void m5849a(C1990d c1990d) {
        this.f4289i = 0;
        this.f4290j = null;
        final C1931r a = C1931r.m6042a((JSONObject) this.f4286f.get("data"));
        if (C1877d.m5639a(this.f4288h, a, this.f4287g)) {
            this.f4285e.onBannerError(this, AdError.NO_FILL);
            return;
        }
        this.f4282b = new C1901b(this) {
            final /* synthetic */ C1904k f4279b;

            public void mo3669a() {
                this.f4279b.f4284d.m6060b();
            }

            public void mo3670a(int i) {
                if (i == 0 && this.f4279b.f4289i > 0 && this.f4279b.f4290j != null) {
                    C1999b.m6321a(C1998a.m6316a(this.f4279b.f4289i, this.f4279b.f4290j, a.m6051g()));
                    this.f4279b.f4289i = 0;
                    this.f4279b.f4290j = null;
                }
            }

            public void mo3671a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && C1874b.m5633a(parse.getAuthority()) && this.f4279b.f4285e != null) {
                    this.f4279b.f4285e.onBannerAdClicked(this.f4279b);
                }
                C1873a a = C1874b.m5632a(this.f4279b.f4288h, this.f4279b.f4287g, a.mo3642c(), parse, map);
                if (a != null) {
                    try {
                        this.f4279b.f4290j = a.mo3621a();
                        this.f4279b.f4289i = System.currentTimeMillis();
                        a.mo3622b();
                    } catch (Throwable e) {
                        Log.e(C1904k.f4281a, "Error executing action", e);
                    }
                }
            }

            public void mo3672b() {
                if (this.f4279b.f4284d != null) {
                    this.f4279b.f4284d.m5670a();
                }
            }
        };
        this.f4283c = new C2182a(this.f4288h, new WeakReference(this.f4282b), c1990d.m6297f());
        this.f4283c.m6979a(c1990d.m6299h(), c1990d.m6300i());
        this.f4284d = new C1933s(this.f4288h, this.f4287g, this.f4283c, this.f4283c.getViewabilityChecker(), new C19032(this));
        this.f4284d.m6058a(a);
        this.f4283c.loadDataWithBaseURL(C2142b.m6857a(), a.m6048d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        if (this.f4285e != null) {
            this.f4285e.onBannerAdLoaded(this, this.f4283c);
        }
    }

    public void loadBannerAd(Context context, C2012c c2012c, e eVar, BannerAdapterListener bannerAdapterListener, Map<String, Object> map) {
        this.f4288h = context;
        this.f4287g = c2012c;
        this.f4285e = bannerAdapterListener;
        this.f4286f = map;
        m5849a((C1990d) this.f4286f.get("definition"));
    }

    public void onDestroy() {
        if (this.f4283c != null) {
            C2142b.m6858a(this.f4283c);
            this.f4283c.destroy();
            this.f4283c = null;
            this.f4282b = null;
        }
    }
}

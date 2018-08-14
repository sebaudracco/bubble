package com.facebook.ads.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.C1885c;
import com.facebook.ads.internal.adapters.C1931r;
import com.facebook.ads.internal.adapters.C1933s;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p076c.C2142b;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.p034b.C2182a;
import com.facebook.ads.internal.view.p034b.C2182a.C1901b;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class C2340f implements C1923a {
    private static final String f5734a = C2340f.class.getSimpleName();
    private final C1832a f5735b;
    private final C2182a f5736c;
    private final C1901b f5737d;
    private final C1933s f5738e;
    private final C2012c f5739f;
    private C1931r f5740g;
    private long f5741h = System.currentTimeMillis();
    private long f5742i;
    private C1996a f5743j;

    class C23392 extends C1885c {
        final /* synthetic */ C2340f f5733a;

        C23392(C2340f c2340f) {
            this.f5733a = c2340f;
        }

        public void mo3673a() {
            this.f5733a.f5735b.mo3561a("com.facebook.ads.interstitial.impression.logged");
        }
    }

    public C2340f(final AudienceNetworkActivity audienceNetworkActivity, final C2012c c2012c, C1832a c1832a) {
        this.f5735b = c1832a;
        this.f5739f = c2012c;
        this.f5737d = new C1901b(this) {
            final /* synthetic */ C2340f f5731c;
            private long f5732d = 0;

            public void mo3669a() {
                this.f5731c.f5738e.m6060b();
            }

            public void mo3670a(int i) {
            }

            public void mo3671a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && "close".equals(parse.getAuthority())) {
                    audienceNetworkActivity.finish();
                    return;
                }
                long j = this.f5732d;
                this.f5732d = System.currentTimeMillis();
                if (this.f5732d - j >= 1000) {
                    if ("fbad".equals(parse.getScheme()) && C1874b.m5633a(parse.getAuthority())) {
                        this.f5731c.f5735b.mo3561a("com.facebook.ads.interstitial.clicked");
                    }
                    C1873a a = C1874b.m5632a(audienceNetworkActivity, c2012c, this.f5731c.f5740g.mo3642c(), parse, map);
                    if (a != null) {
                        try {
                            this.f5731c.f5743j = a.mo3621a();
                            this.f5731c.f5742i = System.currentTimeMillis();
                            a.mo3622b();
                        } catch (Throwable e) {
                            Log.e(C2340f.f5734a, "Error executing action", e);
                        }
                    }
                }
            }

            public void mo3672b() {
                this.f5731c.f5738e.m5670a();
            }
        };
        this.f5736c = new C2182a(audienceNetworkActivity, new WeakReference(this.f5737d), 1);
        this.f5736c.setLayoutParams(new LayoutParams(-1, -1));
        C1885c c23392 = new C23392(this);
        this.f5738e = new C1933s(audienceNetworkActivity, c2012c, this.f5736c, this.f5736c.getViewabilityChecker(), c23392);
        c1832a.mo3560a(this.f5736c);
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.f5740g = C1931r.m6043b(intent);
            if (this.f5740g != null) {
                this.f5738e.m6058a(this.f5740g);
                this.f5736c.loadDataWithBaseURL(C2142b.m6857a(), this.f5740g.m6048d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                this.f5736c.m6979a(this.f5740g.m6052h(), this.f5740g.m6053i());
                return;
            }
            return;
        }
        this.f5740g = C1931r.m6041a(bundle.getBundle("dataModel"));
        if (this.f5740g != null) {
            this.f5736c.loadDataWithBaseURL(C2142b.m6857a(), this.f5740g.m6048d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
            this.f5736c.m6979a(this.f5740g.m6052h(), this.f5740g.m6053i());
        }
    }

    public void mo3684a(Bundle bundle) {
        if (this.f5740g != null) {
            bundle.putBundle("dataModel", this.f5740g.m6054j());
        }
    }

    public void mo3686i() {
        this.f5736c.onPause();
    }

    public void mo3687j() {
        if (!(this.f5742i <= 0 || this.f5743j == null || this.f5740g == null)) {
            C1999b.m6321a(C1998a.m6316a(this.f5742i, this.f5743j, this.f5740g.m6051g()));
        }
        this.f5736c.onResume();
    }

    public void onDestroy() {
        if (this.f5740g != null) {
            C1999b.m6321a(C1998a.m6316a(this.f5741h, C1996a.XOUT, this.f5740g.m6051g()));
            if (!TextUtils.isEmpty(this.f5740g.mo3642c())) {
                Map hashMap = new HashMap();
                this.f5736c.getViewabilityChecker().m6919a(hashMap);
                hashMap.put("touch", C2119j.m6798a(this.f5736c.getTouchData()));
                this.f5739f.mo3717h(this.f5740g.mo3642c(), hashMap);
            }
        }
        C2142b.m6858a(this.f5736c);
        this.f5736c.destroy();
    }

    public void setListener(C1832a c1832a) {
    }
}

package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.p055d.C1850a;
import com.facebook.ads.internal.p055d.C1960b;
import com.facebook.ads.internal.p056q.p057a.C1912o;
import com.facebook.ads.internal.p056q.p076c.C2145d;
import com.facebook.ads.internal.p056q.p076c.C2145d.C2144a;
import com.facebook.ads.internal.p060b.C1909b;
import com.facebook.ads.internal.p060b.C1944c;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.C2322d;
import com.facebook.ads.internal.view.p053e.C2323c;
import com.facebook.ads.internal.view.p053e.p054b.C2228a;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2230d;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p053e.p085c.C2265a;
import com.facebook.ads.internal.view.p053e.p085c.C2270b;
import com.facebook.ads.internal.view.p053e.p085c.C2272c;
import com.facebook.ads.internal.view.p053e.p085c.C2281d;
import com.facebook.ads.internal.view.p053e.p085c.C2281d.C2280a;
import com.facebook.ads.internal.view.p053e.p085c.C2283e;
import com.facebook.ads.internal.view.p053e.p085c.C2298i;
import com.facebook.ads.internal.view.p053e.p085c.C2305k;
import com.facebook.ads.internal.view.p053e.p085c.C2311l;
import com.facebook.ads.p051a.C1835a;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class C1914l extends C1913u implements C1912o<Bundle> {
    static final /* synthetic */ boolean f4301e = (!C1914l.class.desiredAssertionStatus());
    @Nullable
    protected C2012c f4302a;
    @Nullable
    protected C2249b f4303b;
    @Nullable
    protected JSONObject f4304c;
    @Nullable
    protected Context f4305d;
    private final C1839f<C2229b> f4306f = new C19051(this);
    private final C1839f<C2235l> f4307g = new C19062(this);
    private final C1839f<C2230d> f4308h = new C19073(this);
    private final C1839f<C2228a> f4309i = new C19084(this);
    @Nullable
    private C1835a f4310j;
    @Nullable
    private String f4311k;
    private boolean f4312l = false;
    @Nullable
    private C2322d f4313m;
    @Nullable
    private String f4314n;
    private boolean f4315o = false;
    private C1960b f4316p;

    class C19051 extends C1839f<C2229b> {
        final /* synthetic */ C1914l f4291a;

        C19051(C1914l c1914l) {
            this.f4291a = c1914l;
        }

        public Class<C2229b> mo3580a() {
            return C2229b.class;
        }

        public void m5857a(C2229b c2229b) {
            if (this.f4291a.f4310j != null) {
                this.f4291a.f4310j.mo3569d(this.f4291a);
            }
        }
    }

    class C19062 extends C1839f<C2235l> {
        final /* synthetic */ C1914l f4292a;

        C19062(C1914l c1914l) {
            this.f4292a = c1914l;
        }

        public Class<C2235l> mo3580a() {
            return C2235l.class;
        }

        public void m5860a(C2235l c2235l) {
            this.f4292a.f4312l = true;
            if (this.f4292a.f4310j != null) {
                this.f4292a.f4310j.mo3564a(this.f4292a);
            }
        }
    }

    class C19073 extends C1839f<C2230d> {
        final /* synthetic */ C1914l f4293a;

        C19073(C1914l c1914l) {
            this.f4293a = c1914l;
        }

        public Class<C2230d> mo3580a() {
            return C2230d.class;
        }

        public void m5863a(C2230d c2230d) {
            if (this.f4293a.f4310j != null) {
                this.f4293a.f4310j.mo3566a(this.f4293a, AdError.INTERNAL_ERROR);
            }
        }
    }

    class C19084 extends C1839f<C2228a> {
        final /* synthetic */ C1914l f4294a;

        C19084(C1914l c1914l) {
            this.f4294a = c1914l;
        }

        public Class<C2228a> mo3580a() {
            return C2228a.class;
        }

        public void m5866a(C2228a c2228a) {
            if (this.f4294a.f4310j != null) {
                this.f4294a.f4310j.mo3567b(this.f4294a);
            }
        }
    }

    class C19116 implements C1850a {
        final /* synthetic */ C1914l f4300a;

        C19116(C1914l c1914l) {
            this.f4300a = c1914l;
        }

        public void mo3587a() {
            this.f4300a.f4303b.setVideoURI(this.f4300a.mo3685h());
        }

        public void mo3588b() {
            this.f4300a.f4303b.setVideoURI(this.f4300a.mo3685h());
        }
    }

    private void m5875a(Context context, C1835a c1835a, JSONObject jSONObject, C2012c c2012c, @Nullable Bundle bundle, EnumSet<CacheFlag> enumSet) {
        this.f4305d = context;
        this.f4310j = c1835a;
        this.f4302a = c2012c;
        this.f4304c = jSONObject;
        this.f4312l = false;
        JSONObject jSONObject2 = jSONObject.getJSONObject("video");
        this.f4314n = jSONObject.optString("ct");
        this.f4303b = new C2249b(context);
        mo3682a();
        this.f4303b.getEventBus().m6328a(this.f4306f, this.f4307g, this.f4308h, this.f4309i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C1909b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            final /* synthetic */ C1914l f4299a;

            protected void mo3675a(boolean z, boolean z2, C1944c c1944c) {
                this.f4299a.m5886f();
            }
        });
        if (bundle != null) {
            this.f4313m = new C2323c(context, c2012c, this.f4303b, arrayList, this.f4314n, bundle.getBundle(C1403a.f2082l));
        } else {
            this.f4313m = new C2323c(context, c2012c, this.f4303b, (List) arrayList, this.f4314n);
        }
        this.f4310j.mo3565a((C1913u) this, this.f4303b);
        if (C2145d.m6867c(context) == C2144a.MOBILE_INTERNET && jSONObject2.has("videoHDURL") && !jSONObject2.isNull("videoHDURL")) {
            this.f4311k = jSONObject2.getString("videoHDURL");
        } else {
            this.f4311k = jSONObject2.getString(AudienceNetworkActivity.VIDEO_URL);
        }
        if (enumSet.contains(CacheFlag.VIDEO)) {
            this.f4316p = new C1960b(context);
            this.f4316p.m6170a(this.f4311k);
            this.f4316p.m6169a(new C19116(this));
            return;
        }
        this.f4303b.setVideoURI(mo3685h());
    }

    private String mo3685h() {
        Object obj = "";
        if (!(this.f4316p == null || this.f4311k == null)) {
            obj = this.f4316p.m6172b(this.f4311k);
        }
        return TextUtils.isEmpty(obj) ? this.f4311k : obj;
    }

    protected void mo3682a() {
        if (!f4301e && this.f4305d == null) {
            throw new AssertionError();
        } else if (f4301e || this.f4304c != null) {
            C2223b c2272c;
            LayoutParams layoutParams;
            JSONObject optJSONObject = this.f4304c.optJSONObject("text");
            JSONObject jSONObject = optJSONObject == null ? new JSONObject() : optJSONObject;
            this.f4303b.m7105a(new C2305k(this.f4305d));
            C2223b c2311l = new C2311l(this.f4305d);
            this.f4303b.m7105a(c2311l);
            this.f4303b.m7105a(new C2281d(c2311l, C2280a.INVSIBLE));
            this.f4303b.m7105a(new C2270b(this.f4305d));
            String b = m5882b();
            if (b != null) {
                c2272c = new C2272c(this.f4305d, b);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                c2272c.setLayoutParams(layoutParams);
                c2272c.setCountdownTextColor(-1);
                this.f4303b.m7105a(c2272c);
            }
            if (this.f4304c.has("cta") && !this.f4304c.isNull("cta")) {
                JSONObject jSONObject2 = this.f4304c.getJSONObject("cta");
                c2311l = new C2283e(this.f4305d, jSONObject2.getString("url"), this.f4302a, this.f4314n, jSONObject2.getString("text"));
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(10);
                layoutParams2.addRule(11);
                c2311l.setLayoutParams(layoutParams2);
                this.f4303b.m7105a(c2311l);
            }
            Object d = m5884d();
            if (!TextUtils.isEmpty(d)) {
                this.f4303b.m7105a(new C2265a(this.f4305d, d, this.f4314n, new float[]{0.0f, 0.0f, 8.0f, 0.0f}));
            }
            int c = m5883c();
            if (c > 0) {
                c2272c = new C2298i(this.f4305d, c, jSONObject.optString("skipAdIn", "Skip Ad in"), jSONObject.optString("skipAd", "Skip Ad"));
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                c2272c.setLayoutParams(layoutParams);
                c2272c.setPadding(0, 0, 0, 30);
                this.f4303b.m7105a(c2272c);
            }
        } else {
            throw new AssertionError();
        }
    }

    public final void m5880a(Context context, C1835a c1835a, C2012c c2012c, Bundle bundle, EnumSet<CacheFlag> enumSet) {
        try {
            m5875a(context, c1835a, new JSONObject(bundle.getString("ad_response")), c2012c, bundle, enumSet);
        } catch (JSONException e) {
            c1835a.mo3566a((C1913u) this, AdError.INTERNAL_ERROR);
        }
    }

    public final void mo3676a(Context context, C1835a c1835a, Map<String, Object> map, C2012c c2012c, EnumSet<CacheFlag> enumSet) {
        try {
            m5875a(context, c1835a, (JSONObject) map.get("data"), c2012c, null, enumSet);
        } catch (JSONException e) {
            c1835a.mo3566a((C1913u) this, AdError.INTERNAL_ERROR);
        }
    }

    protected String m5882b() {
        String str = null;
        if (f4301e || this.f4304c != null) {
            try {
                JSONObject jSONObject = this.f4304c.getJSONObject("capabilities");
                if (jSONObject.has("countdown") && !jSONObject.isNull("countdown")) {
                    jSONObject = jSONObject.getJSONObject("countdown");
                    if (jSONObject.has("format")) {
                        str = jSONObject.optString("format");
                    }
                }
            } catch (Throwable e) {
                Log.w(String.valueOf(C1914l.class), "Invalid JSON", e);
            }
            return str;
        }
        throw new AssertionError();
    }

    protected int m5883c() {
        int i = -1;
        if (f4301e || this.f4304c != null) {
            try {
                JSONObject jSONObject = this.f4304c.getJSONObject("capabilities");
                if (jSONObject.has("skipButton") && !jSONObject.isNull("skipButton")) {
                    jSONObject = jSONObject.getJSONObject("skipButton");
                    if (jSONObject.has("skippableSeconds")) {
                        i = jSONObject.getInt("skippableSeconds");
                    }
                }
            } catch (Throwable e) {
                Log.w(String.valueOf(C1914l.class), "Invalid JSON", e);
            }
            return i;
        }
        throw new AssertionError();
    }

    @Nullable
    protected String m5884d() {
        String str = null;
        if (f4301e || this.f4304c != null) {
            try {
                JSONObject jSONObject = this.f4304c.getJSONObject("capabilities");
                if (jSONObject.has("adChoices") && !jSONObject.isNull("adChoices")) {
                    jSONObject = jSONObject.getJSONObject("adChoices");
                    if (jSONObject.has("url")) {
                        str = jSONObject.getString("url");
                    }
                }
            } catch (Throwable e) {
                Log.w(String.valueOf(C1914l.class), "Invalid JSON", e);
            }
            return str;
        }
        throw new AssertionError();
    }

    public boolean mo3677e() {
        if (!this.f4312l || this.f4303b == null) {
            return false;
        }
        if (this.f4313m.m7337l() > 0) {
            this.f4303b.m7102a(this.f4313m.m7337l());
        }
        this.f4303b.m7104a(C2222a.AUTO_STARTED);
        return true;
    }

    protected void m5886f() {
        if (this.f4302a != null && !this.f4315o) {
            this.f4315o = true;
            this.f4302a.mo3709a(this.f4314n, new HashMap());
            if (this.f4310j != null) {
                this.f4310j.mo3568c(this);
            }
        }
    }

    public Bundle mo3678g() {
        if (this.f4313m == null || this.f4304c == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle(C1403a.f2082l, this.f4313m.mo3678g());
        bundle.putString("ad_response", this.f4304c.toString());
        return bundle;
    }

    public void onDestroy() {
        if (this.f4303b != null) {
            this.f4303b.m7111f();
            this.f4303b.m7116k();
        }
        this.f4310j = null;
        this.f4302a = null;
        this.f4311k = null;
        this.f4312l = false;
        this.f4314n = null;
        this.f4303b = null;
        this.f4313m = null;
        this.f4304c = null;
        this.f4305d = null;
        this.f4315o = false;
    }
}

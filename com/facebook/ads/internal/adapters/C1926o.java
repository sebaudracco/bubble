package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2020c;
import com.facebook.ads.internal.p033n.C2026f;
import com.facebook.ads.internal.p033n.C2027g;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.p033n.C2030j;
import com.facebook.ads.internal.p033n.e$d;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2124p;
import com.facebook.ads.internal.p056q.p057a.C2134x;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p059a.C1875c;
import com.facebook.ads.internal.p059a.C1877d;
import com.facebook.ads.internal.p059a.C1877d.C1876a;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2097a;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.silvermob.sdk.Const.BannerType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C1926o extends ab implements C1876a {
    private static final String f4373a = C1926o.class.getSimpleName();
    private C2026f f4374A;
    private String f4375B;
    private String f4376C;
    private C2028h f4377D;
    private List<e> f4378E;
    private int f4379F = -1;
    private int f4380G;
    private String f4381H;
    private boolean f4382I;
    private boolean f4383J;
    private boolean f4384K;
    private boolean f4385L;
    private boolean f4386M;
    private long f4387N = 0;
    private C1996a f4388O = null;
    @Nullable
    private C2012c f4389P;
    private e$d f4390Q;
    private Context f4391b;
    private ac f4392c;
    private Uri f4393d;
    private String f4394e;
    private String f4395f;
    private String f4396g;
    private String f4397h;
    private String f4398i;
    private C2026f f4399j;
    private C2026f f4400k;
    private C2027g f4401l;
    private String f4402m;
    private C1875c f4403n;
    private Collection<String> f4404o;
    private boolean f4405p;
    private boolean f4406q;
    private boolean f4407r;
    private int f4408s;
    private int f4409t;
    private int f4410u;
    private int f4411v;
    private String f4412w;
    private String f4413x;
    private C2030j f4414y;
    private String f4415z;

    private boolean m5937H() {
        return this.f4394e != null && this.f4394e.length() > 0 && ((this.f4399j != null || this.f4382I) && this.f4400k != null);
    }

    private void m5938I() {
        if (!this.f4386M) {
            if (this.f4389P != null) {
                this.f4389P.mo3708a(this.f4402m);
            }
            this.f4386M = true;
        }
    }

    private void m5940a(Context context, JSONObject jSONObject, C2012c c2012c, String str, int i, int i2) {
        this.f4382I = true;
        this.f4391b = context;
        this.f4389P = c2012c;
        this.f4379F = i;
        this.f4380G = i2;
        m5942a(jSONObject, str);
    }

    private void m5941a(Map<String, String> map, final Map<String, String> map2) {
        try {
            final Map c = m5944c(map);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ C1926o f4372c;

                public void run() {
                    if (!TextUtils.isEmpty(this.f4372c.f4381H)) {
                        Map hashMap = new HashMap();
                        hashMap.putAll(map2);
                        hashMap.putAll(c);
                        if (this.f4372c.f4389P != null) {
                            this.f4372c.f4389P.mo3714e(this.f4372c.f4381H, hashMap);
                        }
                    }
                }
            }, (long) (this.f4408s * 1000));
        } catch (Exception e) {
        }
    }

    private void m5942a(JSONObject jSONObject, String str) {
        int i = 0;
        JSONArray jSONArray = null;
        if (this.f4383J) {
            throw new IllegalStateException("Adapter already loaded data");
        } else if (jSONObject != null) {
            C2028h c2028h;
            JSONArray optJSONArray;
            int length;
            List arrayList;
            ab c1926o;
            C2110d.m6772a(this.f4391b, "Audience Network Loaded");
            this.f4381H = str;
            Object a = C2119j.m6799a(jSONObject, "fbad_command");
            this.f4393d = TextUtils.isEmpty(a) ? null : Uri.parse(a);
            this.f4394e = C2119j.m6799a(jSONObject, "title");
            this.f4395f = C2119j.m6799a(jSONObject, FacebookAdapter.KEY_SUBTITLE_ASSET);
            this.f4396g = C2119j.m6799a(jSONObject, "body");
            this.f4397h = C2119j.m6799a(jSONObject, "call_to_action");
            if (TextUtils.isEmpty(this.f4397h)) {
                this.f4397h = null;
            }
            this.f4398i = C2119j.m6799a(jSONObject, FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET);
            this.f4399j = C2026f.m6472a(jSONObject.optJSONObject("icon"));
            this.f4400k = C2026f.m6472a(jSONObject.optJSONObject(BannerType.IMAGE));
            this.f4401l = C2027g.m6476a(jSONObject.optJSONObject("star_rating"));
            this.f4402m = C2119j.m6799a(jSONObject, "used_report_url");
            this.f4405p = jSONObject.optBoolean("manual_imp");
            this.f4406q = jSONObject.optBoolean("enable_view_log");
            this.f4407r = jSONObject.optBoolean("enable_snapshot_log");
            this.f4408s = jSONObject.optInt("snapshot_log_delay_second", 4);
            this.f4409t = jSONObject.optInt("snapshot_compress_quality", 0);
            this.f4410u = jSONObject.optInt("viewability_check_initial_delay", 0);
            this.f4411v = jSONObject.optInt("viewability_check_interval", 1000);
            JSONObject optJSONObject = jSONObject.optJSONObject("ad_choices_icon");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("native_ui_config");
            if (optJSONObject2 != null) {
                try {
                    if (optJSONObject2.length() != 0) {
                        c2028h = new C2028h(optJSONObject2);
                        this.f4377D = c2028h;
                        if (optJSONObject != null) {
                            this.f4374A = C2026f.m6472a(optJSONObject);
                        }
                        this.f4375B = C2119j.m6799a(jSONObject, "ad_choices_link_url");
                        this.f4376C = C2119j.m6799a(jSONObject, "request_id");
                        this.f4403n = C1875c.m5634a(jSONObject.optString("invalidation_behavior"));
                        jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
                        this.f4404o = C1877d.m5638a(jSONArray);
                        this.f4412w = C2119j.m6799a(jSONObject, BaseVideoPlayerActivity.VIDEO_URL);
                        this.f4413x = C2119j.m6799a(jSONObject, "video_mpd");
                        if (jSONObject.has("video_autoplay_enabled")) {
                            this.f4414y = C2030j.DEFAULT;
                        } else {
                            this.f4414y = jSONObject.optBoolean("video_autoplay_enabled") ? C2030j.ON : C2030j.OFF;
                        }
                        this.f4415z = C2119j.m6799a(jSONObject, "video_report_url");
                        optJSONArray = jSONObject.optJSONArray("carousel");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            length = optJSONArray.length();
                            arrayList = new ArrayList(length);
                            while (i < length) {
                                c1926o = new C1926o();
                                c1926o.m5940a(this.f4391b, optJSONArray.getJSONObject(i), this.f4389P, str, i, length);
                                arrayList.add(new e(this.f4391b, c1926o, null, this.f4390Q));
                                i++;
                            }
                            this.f4378E = arrayList;
                        }
                        this.f4383J = true;
                        this.f4384K = m5937H();
                    }
                } catch (JSONException e) {
                    this.f4377D = null;
                }
            }
            c2028h = null;
            this.f4377D = c2028h;
            if (optJSONObject != null) {
                this.f4374A = C2026f.m6472a(optJSONObject);
            }
            this.f4375B = C2119j.m6799a(jSONObject, "ad_choices_link_url");
            this.f4376C = C2119j.m6799a(jSONObject, "request_id");
            this.f4403n = C1875c.m5634a(jSONObject.optString("invalidation_behavior"));
            try {
                jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.f4404o = C1877d.m5638a(jSONArray);
            this.f4412w = C2119j.m6799a(jSONObject, BaseVideoPlayerActivity.VIDEO_URL);
            this.f4413x = C2119j.m6799a(jSONObject, "video_mpd");
            if (jSONObject.has("video_autoplay_enabled")) {
                if (jSONObject.optBoolean("video_autoplay_enabled")) {
                }
                this.f4414y = jSONObject.optBoolean("video_autoplay_enabled") ? C2030j.ON : C2030j.OFF;
            } else {
                this.f4414y = C2030j.DEFAULT;
            }
            this.f4415z = C2119j.m6799a(jSONObject, "video_report_url");
            try {
                optJSONArray = jSONObject.optJSONArray("carousel");
                length = optJSONArray.length();
                arrayList = new ArrayList(length);
                while (i < length) {
                    c1926o = new C1926o();
                    c1926o.m5940a(this.f4391b, optJSONArray.getJSONObject(i), this.f4389P, str, i, length);
                    arrayList.add(new e(this.f4391b, c1926o, null, this.f4390Q));
                    i++;
                }
                this.f4378E = arrayList;
            } catch (Throwable e3) {
                Log.e(f4373a, "Unable to parse carousel data.", e3);
            }
            this.f4383J = true;
            this.f4384K = m5937H();
        }
    }

    private Map<String, String> m5944c(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        if (map.containsKey("view")) {
            hashMap.put("view", map.get("view"));
        }
        if (map.containsKey("snapshot")) {
            hashMap.put("snapshot", map.get("snapshot"));
        }
        return hashMap;
    }

    public String mo3629A() {
        return this.f4415z;
    }

    public List<e> mo3630B() {
        return !c_() ? null : this.f4378E;
    }

    public int mo3631C() {
        return this.f4379F;
    }

    public int mo3632D() {
        return this.f4380G;
    }

    public C2020c mo3633E() {
        return C2020c.AN;
    }

    public String mo3689G() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return this.f4396g;
    }

    public C1875c mo3690a() {
        return this.f4403n;
    }

    public void mo3635a(int i) {
        if (c_() && i == 0 && this.f4387N > 0 && this.f4388O != null) {
            C1999b.m6321a(C1998a.m6316a(this.f4387N, this.f4388O, this.f4376C));
            this.f4387N = 0;
            this.f4388O = null;
        }
    }

    public void mo3636a(Context context, ac acVar, C2012c c2012c, Map<String, Object> map, e$d com_facebook_ads_internal_n_e_d) {
        this.f4391b = context;
        this.f4392c = acVar;
        this.f4389P = c2012c;
        this.f4390Q = com_facebook_ads_internal_n_e_d;
        JSONObject jSONObject = (JSONObject) map.get("data");
        m5942a(jSONObject, C2119j.m6799a(jSONObject, "ct"));
        if (C1877d.m5639a(context, this, c2012c)) {
            acVar.mo3602a(this, new C2097a(AdErrorType.NO_FILL, "No Fill"));
            return;
        }
        if (acVar != null) {
            acVar.mo3601a(this);
        }
        C1998a.f4695a = this.f4376C;
    }

    public void mo3637a(View view, List<View> list) {
    }

    public void mo3638a(ac acVar) {
        this.f4392c = acVar;
    }

    public void mo3639a(Map<String, String> map) {
        if (c_() && !this.f4385L) {
            if (this.f4392c != null) {
                this.f4392c.mo3603b(this);
            }
            Map hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            if (this.f4382I) {
                hashMap.put("cardind", String.valueOf(this.f4379F));
                hashMap.put("cardcnt", String.valueOf(this.f4380G));
            }
            if (!(TextUtils.isEmpty(mo3642c()) || this.f4389P == null)) {
                this.f4389P.mo3709a(mo3642c(), hashMap);
            }
            if (mo3646f() || mo3645e()) {
                m5941a((Map) map, hashMap);
            }
            this.f4385L = true;
        }
    }

    public boolean a_() {
        return c_() && this.f4393d != null;
    }

    public Collection<String> mo3692b() {
        return this.f4404o;
    }

    public void mo3640b(Map<String, String> map) {
        if (!c_()) {
            return;
        }
        if (C2005a.m6342c(this.f4391b) && C2134x.m6838a((Map) map)) {
            Log.e(f4373a, "Click happened on lockscreen ad");
            return;
        }
        Map hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        C2110d.m6772a(this.f4391b, "Click logged");
        if (this.f4392c != null) {
            this.f4392c.mo3604c(this);
        }
        if (this.f4382I) {
            hashMap.put("cardind", String.valueOf(this.f4379F));
            hashMap.put("cardcnt", String.valueOf(this.f4380G));
        }
        C1873a a = C1874b.m5632a(this.f4391b, this.f4389P, this.f4381H, this.f4393d, hashMap);
        if (a != null) {
            try {
                this.f4387N = System.currentTimeMillis();
                this.f4388O = a.mo3621a();
                a.mo3622b();
            } catch (Throwable e) {
                Log.e(f4373a, "Error executing action", e);
            }
        }
    }

    public void b_() {
    }

    public String mo3642c() {
        return this.f4381H;
    }

    public boolean c_() {
        return this.f4383J && this.f4384K;
    }

    public boolean mo3644d() {
        return c_() && this.f4405p;
    }

    public boolean mo3645e() {
        return c_() && this.f4407r;
    }

    public boolean mo3646f() {
        return c_() && this.f4406q;
    }

    public boolean mo3647g() {
        return c_() && this.f4377D != null;
    }

    public boolean mo3648h() {
        return true;
    }

    public int mo3649i() {
        return (this.f4409t < 0 || this.f4409t > 100) ? 0 : this.f4409t;
    }

    public int mo3650j() {
        return this.f4410u;
    }

    public int mo3651k() {
        return this.f4411v;
    }

    public C2026f mo3652l() {
        return !c_() ? null : this.f4399j;
    }

    public C2026f mo3653m() {
        return !c_() ? null : this.f4400k;
    }

    public C2028h mo3654n() {
        return !c_() ? null : this.f4377D;
    }

    public String mo3655o() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return this.f4394e;
    }

    public void onDestroy() {
    }

    public String mo3657p() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return this.f4395f;
    }

    public String mo3658q() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return C2124p.m6809a(this.f4396g);
    }

    public String mo3659r() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return this.f4397h;
    }

    public String mo3660s() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return this.f4398i;
    }

    public C2027g mo3661t() {
        if (!c_()) {
            return null;
        }
        m5938I();
        return this.f4401l;
    }

    public C2026f mo3662u() {
        return !c_() ? null : this.f4374A;
    }

    public String mo3663v() {
        return !c_() ? null : this.f4375B;
    }

    public String mo3664w() {
        return !c_() ? null : "AdChoices";
    }

    public String mo3665x() {
        return !c_() ? null : this.f4412w;
    }

    public String mo3666y() {
        return !c_() ? null : this.f4413x;
    }

    public C2030j mo3667z() {
        return !c_() ? C2030j.DEFAULT : this.f4414y;
    }
}

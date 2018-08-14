package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2020c;
import com.facebook.ads.internal.p033n.C2026f;
import com.facebook.ads.internal.p033n.C2027g;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.p033n.C2030j;
import com.facebook.ads.internal.p033n.e$d;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2097a;
import com.google.android.gms.plus.PlusShare;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class C1935t extends ab implements C1891x {
    private ac f4460a;
    private InMobiNative f4461b;
    private boolean f4462c;
    private View f4463d;
    private String f4464e;
    private String f4465f;
    private String f4466g;
    private C2027g f4467h;
    private C2026f f4468i;
    private C2026f f4469j;

    public String mo3629A() {
        return null;
    }

    public List<e> mo3630B() {
        return null;
    }

    public int mo3631C() {
        return 0;
    }

    public int mo3632D() {
        return 0;
    }

    public C2020c mo3633E() {
        return C2020c.INMOBI;
    }

    public C1895g mo3634F() {
        return C1895g.INMOBI;
    }

    public void mo3635a(int i) {
    }

    public void mo3636a(final Context context, ac acVar, C2012c c2012c, Map<String, Object> map, e$d com_facebook_ads_internal_n_e_d) {
        C2110d.m6772a(context, C1939y.m6121a(mo3634F()) + " Loading");
        JSONObject jSONObject = (JSONObject) map.get("data");
        Object optString = jSONObject.optString("account_id");
        Long valueOf = Long.valueOf(jSONObject.optLong("placement_id"));
        if (TextUtils.isEmpty(optString) || valueOf == null) {
            acVar.mo3602a(this, new C2097a(AdErrorType.MEDIATION_ERROR, "Mediation Error"));
            return;
        }
        this.f4460a = acVar;
        InMobiSdk.init(context, optString);
        this.f4461b = new InMobiNative(valueOf.longValue(), new NativeAdListener(this) {
            final /* synthetic */ C1935t f4459b;

            public void onAdDismissed(InMobiNative inMobiNative) {
            }

            public void onAdDisplayed(InMobiNative inMobiNative) {
            }

            public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                C2110d.m6772a(context, C1939y.m6121a(this.f4459b.mo3634F()) + " Failed with InMobi error: " + inMobiAdRequestStatus.getMessage());
                if (this.f4459b.f4460a != null) {
                    this.f4459b.f4460a.mo3602a(this.f4459b, new C2097a(AdErrorType.MEDIATION_ERROR.getErrorCode(), inMobiAdRequestStatus.getMessage()));
                }
            }

            public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                try {
                    int optInt;
                    int optInt2;
                    JSONObject jSONObject = new JSONObject((String) inMobiNative.getAdContent());
                    this.f4459b.f4464e = jSONObject.optString("title");
                    this.f4459b.f4465f = jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
                    this.f4459b.f4466g = jSONObject.optString("cta");
                    JSONObject optJSONObject = jSONObject.optJSONObject("icon");
                    if (optJSONObject != null) {
                        optInt = optJSONObject.optInt("width");
                        optInt2 = optJSONObject.optInt("height");
                        this.f4459b.f4468i = new C2026f(optJSONObject.optString("url"), optInt, optInt2);
                    }
                    optJSONObject = jSONObject.optJSONObject("screenshots");
                    if (optJSONObject != null) {
                        optInt = optJSONObject.optInt("width");
                        optInt2 = optJSONObject.optInt("height");
                        this.f4459b.f4469j = new C2026f(optJSONObject.optString("url"), optInt, optInt2);
                    }
                    try {
                        this.f4459b.f4467h = new C2027g(Double.parseDouble(jSONObject.optString("rating")), 5.0d);
                    } catch (Exception e) {
                    }
                    this.f4459b.f4462c = true;
                    if (this.f4459b.f4463d != null) {
                        this.f4459b.f4461b;
                        InMobiNative.bind(this.f4459b.f4463d, inMobiNative);
                    }
                    if (this.f4459b.f4460a != null) {
                        C2110d.m6772a(context, C1939y.m6121a(this.f4459b.mo3634F()) + " Loaded");
                        this.f4459b.f4460a.mo3601a(this.f4459b);
                    }
                } catch (Exception e2) {
                    if (this.f4459b.f4460a != null) {
                        C2110d.m6772a(context, C1939y.m6121a(this.f4459b.mo3634F()) + " Failed. Internal AN SDK error");
                        this.f4459b.f4460a.mo3602a(this.f4459b, C2097a.m6746a(AdErrorType.INTERNAL_ERROR, "Internal Error"));
                    }
                }
            }

            public void onUserLeftApplication(InMobiNative inMobiNative) {
            }
        });
        this.f4461b.load();
    }

    public void mo3637a(View view, List<View> list) {
        this.f4463d = view;
        if (c_()) {
            InMobiNative inMobiNative = this.f4461b;
            InMobiNative.bind(this.f4463d, this.f4461b);
        }
    }

    public void mo3638a(ac acVar) {
        this.f4460a = acVar;
    }

    public void mo3639a(Map<String, String> map) {
        this.f4460a.mo3603b(this);
    }

    public void mo3640b(Map<String, String> map) {
        if (c_()) {
            this.f4460a.mo3604c(this);
            this.f4461b.reportAdClickAndOpenLandingPage(null);
        }
    }

    public void b_() {
        if (c_()) {
            InMobiNative inMobiNative = this.f4461b;
            InMobiNative.unbind(this.f4463d);
        }
        this.f4463d = null;
    }

    public String mo3642c() {
        return null;
    }

    public boolean c_() {
        return this.f4461b != null && this.f4462c;
    }

    public boolean mo3644d() {
        return false;
    }

    public boolean mo3645e() {
        return false;
    }

    public boolean mo3646f() {
        return false;
    }

    public boolean mo3647g() {
        return false;
    }

    public boolean mo3648h() {
        return true;
    }

    public int mo3649i() {
        return 0;
    }

    public int mo3650j() {
        return 0;
    }

    public int mo3651k() {
        return 0;
    }

    public C2026f mo3652l() {
        return this.f4468i;
    }

    public C2026f mo3653m() {
        return this.f4469j;
    }

    public C2028h mo3654n() {
        return null;
    }

    public String mo3655o() {
        return this.f4464e;
    }

    public void onDestroy() {
        b_();
        this.f4461b = null;
        this.f4460a = null;
    }

    public String mo3657p() {
        return null;
    }

    public String mo3658q() {
        return this.f4465f;
    }

    public String mo3659r() {
        return this.f4466g;
    }

    public String mo3660s() {
        return null;
    }

    public C2027g mo3661t() {
        return null;
    }

    public C2026f mo3662u() {
        return null;
    }

    public String mo3663v() {
        return null;
    }

    public String mo3664w() {
        return "Ad";
    }

    public String mo3665x() {
        return null;
    }

    public String mo3666y() {
        return null;
    }

    public C2030j mo3667z() {
        return C2030j.DEFAULT;
    }
}

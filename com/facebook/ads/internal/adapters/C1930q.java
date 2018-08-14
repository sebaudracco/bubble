package com.facebook.ads.internal.adapters;

import android.content.Context;
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
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdErrorType;
import com.flurry.android.ads.FlurryAdNative;
import com.flurry.android.ads.FlurryAdNativeAsset;
import com.flurry.android.ads.FlurryAdNativeListener;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class C1930q extends ab implements C1891x {
    private static volatile boolean f4430a;
    private ac f4431b;
    private FlurryAdNative f4432c;
    private boolean f4433d;
    private String f4434e;
    private String f4435f;
    private String f4436g;
    private String f4437h;
    private String f4438i;
    private C2026f f4439j;
    private C2026f f4440k;
    private C2026f f4441l;

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
        return C2020c.FLURRY;
    }

    public C1895g mo3634F() {
        return C1895g.YAHOO;
    }

    public void mo3635a(int i) {
    }

    public void mo3636a(final Context context, ac acVar, C2012c c2012c, Map<String, Object> map, e$d com_facebook_ads_internal_n_e_d) {
        JSONObject jSONObject = (JSONObject) map.get("data");
        String optString = jSONObject.optString("api_key");
        String optString2 = jSONObject.optString("placement_id");
        synchronized (C1930q.class) {
            if (!f4430a) {
                C2110d.m6772a(context, C1939y.m6121a(mo3634F()) + " Initializing");
                FlurryAgent.setLogEnabled(true);
                FlurryAgent.init(context, optString);
                f4430a = true;
            }
        }
        C2110d.m6772a(context, C1939y.m6121a(mo3634F()) + " Loading");
        this.f4431b = acVar;
        this.f4432c = new FlurryAdNative(context, optString2);
        this.f4432c.setListener(new FlurryAdNativeListener(this) {
            final /* synthetic */ C1930q f4429b;

            public void onAppExit(FlurryAdNative flurryAdNative) {
            }

            public void onClicked(FlurryAdNative flurryAdNative) {
                if (this.f4429b.f4431b != null) {
                    this.f4429b.f4431b.mo3604c(this.f4429b);
                }
            }

            public void onCloseFullscreen(FlurryAdNative flurryAdNative) {
            }

            public void onCollapsed(FlurryAdNative flurryAdNative) {
            }

            public void onError(FlurryAdNative flurryAdNative, FlurryAdErrorType flurryAdErrorType, int i) {
                C2110d.m6772a(context, C1939y.m6121a(this.f4429b.mo3634F()) + " Failed with FlurryError: " + flurryAdErrorType.toString());
                if (this.f4429b.f4431b != null) {
                    this.f4429b.f4431b.mo3602a(this.f4429b, C2097a.m6746a(AdErrorType.MEDIATION_ERROR, flurryAdErrorType.toString()));
                }
            }

            public void onExpanded(FlurryAdNative flurryAdNative) {
            }

            public void onFetched(FlurryAdNative flurryAdNative) {
                if (this.f4429b.f4431b != null) {
                    if (flurryAdNative.isVideoAd()) {
                        C2110d.m6772a(context, C1939y.m6121a(this.f4429b.mo3634F()) + " Failed. AN does not support Flurry video ads");
                        this.f4429b.f4431b.mo3602a(this.f4429b, new C2097a(AdErrorType.MEDIATION_ERROR, "video ad"));
                        return;
                    }
                    this.f4429b.f4433d = true;
                    FlurryAdNativeAsset asset = flurryAdNative.getAsset("headline");
                    if (asset != null) {
                        this.f4429b.f4434e = asset.getValue();
                    }
                    asset = flurryAdNative.getAsset("summary");
                    if (asset != null) {
                        this.f4429b.f4435f = asset.getValue();
                    }
                    asset = flurryAdNative.getAsset("source");
                    if (asset != null) {
                        this.f4429b.f4436g = asset.getValue();
                    }
                    asset = flurryAdNative.getAsset("appCategory");
                    if (asset != null) {
                        this.f4429b.f4438i = asset.getValue();
                    }
                    asset = flurryAdNative.getAsset("callToAction");
                    if (asset != null) {
                        this.f4429b.f4437h = asset.getValue();
                    } else if (flurryAdNative.getAsset("appRating") != null) {
                        this.f4429b.f4437h = "Install Now";
                    } else {
                        this.f4429b.f4437h = CtaButton.DEFAULT_CTA_TEXT;
                    }
                    asset = flurryAdNative.getAsset("secImage");
                    if (asset != null) {
                        this.f4429b.f4439j = new C2026f(asset.getValue(), 82, 82);
                    }
                    asset = flurryAdNative.getAsset("secHqImage");
                    if (asset != null) {
                        this.f4429b.f4440k = new C2026f(asset.getValue(), 1200, 627);
                    }
                    asset = flurryAdNative.getAsset("secBrandingLogo");
                    if (asset != null) {
                        this.f4429b.f4441l = new C2026f(asset.getValue(), 20, 20);
                    }
                    C2110d.m6772a(context, C1939y.m6121a(this.f4429b.mo3634F()) + " Loaded");
                    this.f4429b.f4431b.mo3601a(this.f4429b);
                }
            }

            public void onImpressionLogged(FlurryAdNative flurryAdNative) {
                if (this.f4429b.f4431b != null) {
                    this.f4429b.f4431b.mo3603b(this.f4429b);
                }
            }

            public void onShowFullscreen(FlurryAdNative flurryAdNative) {
            }
        });
        this.f4432c.fetchAd();
    }

    public void mo3637a(View view, List<View> list) {
        if (this.f4432c != null) {
            this.f4432c.setTrackingView(view);
        }
    }

    public void mo3638a(ac acVar) {
        this.f4431b = acVar;
    }

    public void mo3639a(Map<String, String> map) {
    }

    public void mo3640b(Map<String, String> map) {
    }

    public void b_() {
        if (this.f4432c != null) {
            this.f4432c.removeTrackingView();
        }
    }

    public String mo3642c() {
        return null;
    }

    public boolean c_() {
        return this.f4433d;
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
        return this.f4439j;
    }

    public C2026f mo3653m() {
        return this.f4440k;
    }

    public C2028h mo3654n() {
        return null;
    }

    public String mo3655o() {
        return this.f4434e;
    }

    public void onDestroy() {
        b_();
        this.f4431b = null;
        if (this.f4432c != null) {
            this.f4432c.destroy();
            this.f4432c = null;
        }
    }

    public String mo3657p() {
        return this.f4436g;
    }

    public String mo3658q() {
        return this.f4435f;
    }

    public String mo3659r() {
        return this.f4437h;
    }

    public String mo3660s() {
        return this.f4438i;
    }

    public C2027g mo3661t() {
        return null;
    }

    public C2026f mo3662u() {
        return this.f4441l;
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

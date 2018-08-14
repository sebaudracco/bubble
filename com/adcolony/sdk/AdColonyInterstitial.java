package com.adcolony.sdk;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.adcolony.sdk.aa.C0595a;
import org.json.JSONObject;

public class AdColonyInterstitial {
    public static final int ADCOLONY_IAP_ENGAGEMENT_END_CARD = 0;
    public static final int ADCOLONY_IAP_ENGAGEMENT_OVERLAY = 1;
    private AdColonyInterstitialListener f401a;
    private C0673c f402b;
    private AdColonyAdOptions f403c;
    private C0694g f404d;
    private int f405e;
    private String f406f;
    private String f407g;
    private String f408h;
    private int f409i;
    private String f410j;
    private boolean f411k;
    private boolean f412l;
    private boolean f413m;

    AdColonyInterstitial(String adSessionId, AdColonyInterstitialListener listener, String zoneId) {
        this.f401a = listener;
        this.f410j = zoneId;
        this.f406f = adSessionId;
    }

    public boolean show() {
        if (!C0594a.m612b()) {
            return false;
        }
        C0740l a = C0594a.m605a();
        if (this.f412l) {
            new C0595a().m622a("This ad object has already been shown. Please request a new ad via AdColony.requestInterstitial.").m624a(aa.f481e);
            return false;
        } else if (this.f411k) {
            new C0595a().m622a("This ad object has expired. Please request a new ad via AdColony.requestInterstitial.").m624a(aa.f481e);
            return false;
        } else if (a.m1293w()) {
            new C0595a().m622a("Can not show ad while an interstitial is already active.").m624a(aa.f481e);
            return false;
        } else if (m568a((AdColonyZone) a.m1276f().get(this.f410j))) {
            new C0595a().m622a("Skipping show()").m624a(aa.f480d);
            return false;
        } else {
            JSONObject a2 = C0802y.m1453a();
            C0802y.m1462a(a2, "zone_id", this.f410j);
            C0802y.m1472b(a2, "type", 0);
            C0802y.m1462a(a2, "id", this.f406f);
            if (this.f403c != null) {
                C0802y.m1465a(a2, "pre_popup", this.f403c.f369a);
                C0802y.m1465a(a2, "post_popup", this.f403c.f370b);
            }
            AdColonyZone adColonyZone = (AdColonyZone) a.m1276f().get(this.f410j);
            if (adColonyZone != null && adColonyZone.isRewarded() && a.m1279i() == null) {
                new C0595a().m622a("Rewarded ad: show() called with no reward listener set.").m624a(aa.f481e);
            }
            new af("AdSession.launch_ad_unit", 1, a2).m695b();
            return true;
        }
    }

    boolean m568a(AdColonyZone adColonyZone) {
        if (adColonyZone == null) {
            return true;
        }
        if (adColonyZone.getPlayFrequency() <= 1) {
            return false;
        }
        if (adColonyZone.m602b() == 0) {
            adColonyZone.m599a(adColonyZone.getPlayFrequency() - 1);
            return false;
        }
        adColonyZone.m599a(adColonyZone.m602b() - 1);
        return true;
    }

    public boolean cancel() {
        if (this.f402b == null || (C0594a.m614d() && !(C0594a.m613c() instanceof AdColonyInterstitialActivity))) {
            return false;
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f402b.m1053b());
        new af("AdSession.on_request_close", this.f402b.m1057c(), a).m695b();
        return true;
    }

    public AdColonyInterstitialListener getListener() {
        return this.f401a;
    }

    public void setListener(@NonNull AdColonyInterstitialListener listener) {
        this.f401a = listener;
    }

    public String getZoneID() {
        return this.f410j;
    }

    public boolean isExpired() {
        return this.f411k || this.f412l;
    }

    public boolean destroy() {
        C0594a.m605a().m1283m().m1155c().remove(this.f406f);
        return true;
    }

    boolean m567a() {
        if (!C0594a.m614d() || !C0594a.m612b()) {
            return false;
        }
        C0594a.m605a().m1270c(true);
        C0594a.m605a().m1256a(this.f402b);
        C0594a.m605a().m1253a(this);
        new C0595a().m622a("Launching fullscreen Activity via AdColonyInterstitial's launch method.").m624a(aa.f478b);
        C0594a.m613c().startActivity(new Intent(C0594a.m613c(), AdColonyInterstitialActivity.class));
        this.f412l = true;
        return true;
    }

    void m565a(JSONObject jSONObject) {
        if (jSONObject.length() > 0) {
            this.f404d = new C0694g(jSONObject);
        }
    }

    void m563a(C0673c c0673c) {
        this.f402b = c0673c;
    }

    void m566a(boolean z) {
        this.f411k = z;
    }

    void m562a(AdColonyAdOptions adColonyAdOptions) {
        this.f403c = adColonyAdOptions;
    }

    void m561a(int i) {
        this.f409i = i;
    }

    void m570b(int i) {
        this.f405e = i;
    }

    String m569b() {
        if (this.f407g == null) {
            return "";
        }
        return this.f407g;
    }

    void m564a(String str) {
        this.f407g = str;
    }

    String m573c() {
        if (this.f408h == null) {
            return "";
        }
        return this.f408h;
    }

    void m571b(String str) {
        this.f408h = str;
    }

    C0673c m574d() {
        return this.f402b;
    }

    int m575e() {
        return this.f405e;
    }

    String m576f() {
        return this.f406f;
    }

    void m572b(boolean z) {
        this.f413m = z;
    }

    boolean m577g() {
        return this.f404d != null;
    }

    C0694g m578h() {
        return this.f404d;
    }

    boolean m579i() {
        return this.f413m;
    }
}

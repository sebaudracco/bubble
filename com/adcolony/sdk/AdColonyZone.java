package com.adcolony.sdk;

import android.support.annotation.NonNull;
import com.adcolony.sdk.aa.C0595a;
import org.json.JSONObject;

public class AdColonyZone {
    public static final int BANNER = 1;
    public static final int INTERSTITIAL = 0;
    public static final int NATIVE = 2;
    static final int f451a = 0;
    static final int f452b = 1;
    static final int f453c = 2;
    static final int f454d = 3;
    static final int f455e = 4;
    static final int f456f = 5;
    static final int f457g = 6;
    private String f458h;
    private String f459i;
    private String f460j;
    private String f461k;
    private int f462l = 5;
    private int f463m;
    private int f464n;
    private int f465o;
    private int f466p;
    private int f467q;
    private int f468r;
    private boolean f469s;
    private boolean f470t;

    AdColonyZone(@NonNull String zoneId) {
        this.f458h = zoneId;
    }

    private int m597c(int i) {
        if (C0594a.m612b() && !C0594a.m605a().m1277g() && !C0594a.m605a().m1278h()) {
            return i;
        }
        m598c();
        return 0;
    }

    private boolean m596a(boolean z) {
        if (C0594a.m612b() && !C0594a.m605a().m1277g() && !C0594a.m605a().m1278h()) {
            return z;
        }
        m598c();
        return false;
    }

    private String m594a(String str) {
        return m595a(str, "");
    }

    private String m595a(String str, String str2) {
        if (C0594a.m612b() && !C0594a.m605a().m1277g() && !C0594a.m605a().m1278h()) {
            return str;
        }
        m598c();
        return str2;
    }

    public String getZoneID() {
        return m594a(this.f458h);
    }

    public int getRemainingViewsUntilReward() {
        return m597c(this.f464n);
    }

    public int getRewardAmount() {
        return m597c(this.f467q);
    }

    public String getRewardName() {
        return m594a(this.f459i);
    }

    public int getViewsPerReward() {
        return m597c(this.f465o);
    }

    public int getZoneType() {
        return this.f463m;
    }

    boolean m601a() {
        return this.f462l == 0;
    }

    public boolean isValid() {
        return m596a(this.f469s);
    }

    public boolean isRewarded() {
        return this.f470t;
    }

    public int getPlayFrequency() {
        return m597c(this.f466p);
    }

    void m600a(af afVar) {
        boolean z = true;
        JSONObject c = afVar.m698c();
        JSONObject f = C0802y.m1480f(c, "reward");
        this.f459i = C0802y.m1468b(f, "reward_name");
        this.f467q = C0802y.m1473c(f, "reward_amount");
        this.f465o = C0802y.m1473c(f, "views_per_reward");
        this.f464n = C0802y.m1473c(f, "views_until_reward");
        this.f460j = C0802y.m1468b(f, "reward_name_plural");
        this.f461k = C0802y.m1468b(f, "reward_prompt");
        this.f470t = C0802y.m1477d(c, "rewarded");
        this.f462l = C0802y.m1473c(c, "status");
        this.f463m = C0802y.m1473c(c, "type");
        this.f466p = C0802y.m1473c(c, "play_interval");
        this.f458h = C0802y.m1468b(c, "zone_id");
        if (this.f462l == 1) {
            z = false;
        }
        this.f469s = z;
    }

    void m599a(int i) {
        this.f468r = i;
    }

    int m602b() {
        return this.f468r;
    }

    void m603b(int i) {
        this.f462l = i;
    }

    private void m598c() {
        new C0595a().m622a("The AdColonyZone API is not available while AdColony is disabled.").m624a(aa.f483g);
    }
}

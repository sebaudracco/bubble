package com.facebook.ads.internal.adapters;

import com.google.ads.mediation.facebook.FacebookAdapter;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.silvermob.sdk.Const.BannerType;
import java.io.Serializable;
import org.json.JSONObject;

public class C1886d implements Serializable {
    private static final long serialVersionUID = 85021702336014823L;
    private final String f4204a;
    private final String f4205b;
    private final String f4206c;
    private final String f4207d;
    private final String f4208e;
    private final String f4209f;
    private final int f4210g;
    private final int f4211h;
    private final String f4212i;
    private final boolean f4213j;
    private final boolean f4214k;
    private final int f4215l;

    private C1886d(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, String str7, boolean z, boolean z2, int i3) {
        this.f4204a = str;
        this.f4205b = str2;
        this.f4206c = str3;
        this.f4207d = str4;
        this.f4208e = str5;
        this.f4209f = str6;
        this.f4210g = i;
        this.f4211h = i2;
        this.f4212i = str7;
        this.f4213j = z;
        this.f4214k = z2;
        this.f4215l = i3;
    }

    static C1886d m5756a(JSONObject jSONObject) {
        int parseInt;
        int parseInt2;
        String optString = jSONObject.optString("title");
        String optString2 = jSONObject.optString(FacebookAdapter.KEY_SUBTITLE_ASSET);
        String optString3 = jSONObject.optString("body");
        String optString4 = jSONObject.optString("call_to_action");
        if ("null".equalsIgnoreCase(optString4)) {
            optString4 = "";
        }
        String optString5 = jSONObject.optString("fbad_command");
        if ("null".equalsIgnoreCase(optString5)) {
            optString5 = "";
        }
        String str = "";
        JSONObject optJSONObject = jSONObject.optJSONObject(BannerType.IMAGE);
        if (optJSONObject != null) {
            str = optJSONObject.optString("url");
            parseInt = Integer.parseInt(optJSONObject.optString("width"));
            parseInt2 = Integer.parseInt(optJSONObject.optString("height"));
        } else {
            parseInt2 = 0;
            parseInt = 0;
        }
        String optString6 = jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL);
        boolean optBoolean = jSONObject.optBoolean("video_autoplay_enabled");
        return new C1886d(optString, optString2, optString3, optString4, optString5, str, parseInt, parseInt2, optString6, optBoolean, jSONObject.optBoolean("video_autoplay_with_sound"), optBoolean ? jSONObject.optInt("unskippable_seconds", 0) : 0);
    }

    public String m5757a() {
        return this.f4204a;
    }

    public String m5758b() {
        return this.f4205b;
    }

    public String m5759c() {
        return this.f4206c;
    }

    public String m5760d() {
        return this.f4207d;
    }

    public String m5761e() {
        return this.f4208e;
    }

    public String m5762f() {
        return this.f4209f;
    }

    public int m5763g() {
        return this.f4210g;
    }

    public int m5764h() {
        return this.f4211h;
    }

    public String m5765i() {
        return this.f4212i;
    }

    public boolean m5766j() {
        return this.f4213j;
    }

    public boolean m5767k() {
        return this.f4214k;
    }

    public int m5768l() {
        return this.f4215l;
    }
}

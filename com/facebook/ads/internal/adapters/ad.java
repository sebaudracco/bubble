package com.facebook.ads.internal.adapters;

import android.text.TextUtils;
import com.appnext.core.Ad;
import com.facebook.ads.internal.p052j.C2000c;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.silvermob.sdk.Const.BannerType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ad implements Serializable {
    private static final long serialVersionUID = -5352540727250859603L;
    private final String f4173a;
    private final String f4174b;
    private final byte[] f4175c;
    private final String f4176d;
    private final String f4177e;
    private final String f4178f;
    private final String f4179g;
    private final String f4180h;
    private final String f4181i;
    private final String f4182j;
    private final int f4183k;
    private final int f4184l;
    private final C1900j f4185m;
    private final C1900j f4186n;
    private final double f4187o;
    private final int f4188p;
    private final List<String> f4189q;
    private final String f4190r;
    private final String f4191s;
    private final String f4192t;
    private final String f4193u;
    private String f4194v;
    private String f4195w;

    private ad(String str, String str2, byte[] bArr, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, int i2, C1900j c1900j, C1900j c1900j2, double d, int i3, List<String> list, String str10, String str11, String str12, String str13) {
        this.f4173a = str;
        this.f4174b = str2;
        this.f4175c = bArr;
        this.f4176d = str3;
        this.f4177e = str4;
        this.f4178f = str5;
        this.f4179g = str6;
        this.f4180h = str7;
        this.f4181i = str8;
        this.f4182j = str9;
        this.f4183k = i;
        this.f4184l = i2;
        this.f4185m = c1900j;
        this.f4186n = c1900j2;
        this.f4187o = d;
        this.f4188p = i3;
        this.f4189q = list;
        this.f4190r = str10;
        this.f4191s = str11;
        this.f4192t = str12;
        this.f4193u = str13;
    }

    public static ad m5718a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("layout");
        return new ad(jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL), jSONObject.optString("ct"), C2000c.m6324a(jSONObject.optString("end_card_markup")), jSONObject.optString("activation_command"), jSONObject.optString("advertiser_name"), jSONObject.optString("title"), jSONObject.optString(FacebookAdapter.KEY_SUBTITLE_ASSET), jSONObject.optString("body"), jSONObject.optJSONObject("icon") != null ? jSONObject.optJSONObject("icon").optString("url") : "", jSONObject.optJSONObject(BannerType.IMAGE) != null ? jSONObject.optJSONObject(BannerType.IMAGE).optString("url") : "", jSONObject.optInt("skippable_seconds"), jSONObject.optInt("video_duration_sec"), optJSONObject != null ? C1900j.m5828a(optJSONObject.optJSONObject(Ad.ORIENTATION_PORTRAIT)) : new C1900j(), optJSONObject != null ? C1900j.m5828a(optJSONObject.optJSONObject(Ad.ORIENTATION_LANDSCAPE)) : new C1900j(), jSONObject.optDouble("rating_value", 0.0d), jSONObject.optInt("rating_count", 0), m5719a(jSONObject.optJSONArray("end_card_images")), jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"), jSONObject.optString("ad_choices_link_url"), m5720b(jSONObject));
    }

    private static List<String> m5719a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            CharSequence optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    private static String m5720b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        return optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored");
    }

    public String m5721a() {
        return this.f4173a;
    }

    void m5722a(String str) {
        this.f4194v = str;
    }

    public String m5723b() {
        return this.f4174b;
    }

    void m5724b(String str) {
        this.f4195w = str;
    }

    public byte[] m5725c() {
        return this.f4175c;
    }

    public String m5726d() {
        return this.f4176d;
    }

    public String m5727e() {
        return this.f4177e;
    }

    public String m5728f() {
        return this.f4178f;
    }

    public String m5729g() {
        return this.f4179g;
    }

    public String m5730h() {
        return this.f4180h;
    }

    public String m5731i() {
        return this.f4181i;
    }

    public String m5732j() {
        return this.f4182j;
    }

    public int m5733k() {
        return this.f4183k;
    }

    public String m5734l() {
        return this.f4194v;
    }

    int m5735m() {
        return this.f4184l;
    }

    public C1900j m5736n() {
        return this.f4185m;
    }

    public List<String> m5737o() {
        return Collections.unmodifiableList(this.f4189q);
    }

    public String m5738p() {
        return this.f4190r;
    }

    public String m5739q() {
        return this.f4191s;
    }

    public String m5740r() {
        return this.f4192t;
    }

    public String m5741s() {
        return this.f4195w;
    }

    public String m5742t() {
        return this.f4193u;
    }
}

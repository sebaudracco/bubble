package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.appnext.core.Ad;
import com.facebook.ads.internal.p056q.p077d.C2150a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1936v implements Serializable {
    private static final long serialVersionUID = 8751287062553772011L;
    private final List<C1886d> f4470a;
    private final String f4471b;
    private final String f4472c;
    private final C1900j f4473d;
    private final String f4474e;
    private final String f4475f;
    private final String f4476g;
    private final C1900j f4477h;
    private final String f4478i;
    private final int f4479j;
    private final int f4480k;

    private C1936v(List<C1886d> list, String str, String str2, String str3, String str4, String str5, String str6, C1900j c1900j, C1900j c1900j2, int i, int i2) {
        this.f4470a = list;
        this.f4471b = str;
        this.f4472c = str2;
        this.f4473d = c1900j2;
        this.f4474e = str3;
        this.f4475f = str4;
        this.f4476g = str6;
        this.f4477h = c1900j;
        this.f4478i = str5;
        this.f4479j = i;
        this.f4480k = i2;
    }

    public static C1936v m6107a(JSONObject jSONObject, Context context) {
        JSONObject jSONObject2 = null;
        String optString = jSONObject.optString("ad_choices_link_url");
        String optString2 = jSONObject.optString("ct");
        String optString3 = jSONObject.optString("title");
        int optInt = jSONObject.optInt("viewability_check_initial_delay", 0);
        int optInt2 = jSONObject.optInt("viewability_check_interval", 1000);
        String str = "";
        JSONObject optJSONObject = jSONObject.optJSONObject("icon");
        if (optJSONObject != null) {
            str = optJSONObject.optString("url");
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        if (optJSONObject2 != null) {
            optJSONObject = optJSONObject2.optJSONObject(Ad.ORIENTATION_PORTRAIT);
            jSONObject2 = optJSONObject2.optJSONObject(Ad.ORIENTATION_LANDSCAPE);
        } else {
            optJSONObject = null;
        }
        optJSONObject2 = jSONObject.optJSONObject("generic_text");
        String optString4 = optJSONObject2 != null ? optJSONObject2.optString("sponsored", "Sponsored") : "Sponsored";
        C1900j a = C1900j.m5828a(optJSONObject);
        C1900j a2 = C1900j.m5828a(jSONObject2);
        String optString5 = jSONObject.optString("request_id", "");
        JSONArray optJSONArray = jSONObject.optJSONArray("carousel");
        List arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            arrayList.add(C1886d.m5756a(jSONObject));
        } else {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(C1886d.m5756a(optJSONArray.getJSONObject(i)));
                } catch (Exception e) {
                    C2150a.m6888a(e, context);
                    e.printStackTrace();
                }
            }
        }
        return new C1936v(arrayList, optString, optString2, optString3, str, optString5, optString4, a, a2, optInt, optInt2);
    }

    public String m6108a() {
        return this.f4472c;
    }

    public String m6109b() {
        return this.f4474e;
    }

    public String m6110c() {
        return this.f4475f;
    }

    public List<C1886d> m6111d() {
        return Collections.unmodifiableList(this.f4470a);
    }

    public String m6112e() {
        return this.f4471b;
    }

    public String m6113f() {
        return this.f4478i;
    }

    public String m6114g() {
        return this.f4476g;
    }

    public C1900j m6115h() {
        return this.f4477h;
    }

    public C1900j m6116i() {
        return this.f4473d;
    }

    public int m6117j() {
        return this.f4479j;
    }

    public int m6118k() {
        return this.f4480k;
    }
}

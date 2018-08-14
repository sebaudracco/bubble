package com.facebook.ads.internal.p052j;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class C1998a {
    public static String f4695a = null;
    private String f4696b;
    private Map<String, Object> f4697c;
    private int f4698d;
    private String f4699e;

    public enum C1996a {
        OPEN_STORE(0),
        OPEN_LINK(1),
        XOUT(2),
        OPEN_URL(3),
        SHOW_INTERSTITIAL(4);
        
        int f4691f;

        private C1996a(int i) {
            this.f4691f = i;
        }
    }

    public enum C1997b {
        LOADING_AD(0);
        
        int f4694b;

        private C1997b(int i) {
            this.f4694b = i;
        }
    }

    public C1998a(String str, Map<String, Object> map, int i, String str2) {
        this.f4696b = str;
        this.f4697c = map;
        this.f4698d = i;
        this.f4699e = str2;
    }

    public static C1998a m6316a(long j, C1996a c1996a, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        Map hashMap = new HashMap();
        hashMap.put("Time", String.valueOf(currentTimeMillis - j));
        hashMap.put("AdAction", String.valueOf(c1996a.f4691f));
        return new C1998a("bounceback", hashMap, (int) (currentTimeMillis / 1000), str);
    }

    public static C1998a m6317a(C1997b c1997b, String str, long j, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("LatencyType", String.valueOf(c1997b.f4694b));
        hashMap.put("AdPlacementType", str);
        hashMap.put("Time", String.valueOf(j));
        String str3 = "latency";
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (str2 == null) {
            str2 = f4695a;
        }
        return new C1998a(str3, hashMap, currentTimeMillis, str2);
    }

    public static C1998a m6318a(@Nullable Throwable th, @Nullable String str) {
        Map hashMap = new HashMap();
        if (th != null) {
            hashMap.put("ex", th.getClass().getSimpleName());
            hashMap.put("ex_msg", th.getMessage());
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String str2 = "error";
        if (str == null) {
            str = f4695a;
        }
        return new C1998a(str2, hashMap, currentTimeMillis, str);
    }

    public JSONObject m6319a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f4696b);
            jSONObject.put("data", new JSONObject(this.f4697c));
            jSONObject.put("time", this.f4698d);
            jSONObject.put("request_id", this.f4699e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}

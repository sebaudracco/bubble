package com.bgjd.ici.p030e;

import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p030e.C1485h.C1484a;

public class C1487j {
    private int f2417a = 0;
    private int f2418b = 0;
    private int f2419c = 0;
    private String f2420d;
    private String f2421e;
    private String f2422f;
    private String f2423g;
    private String f2424h;
    private boolean f2425i = false;
    private String f2426j = "";
    private boolean f2427k = false;
    private String f2428l = "";
    private String f2429m = "";

    public C1487j(JSONObject jSONObject) {
        boolean z = true;
        try {
            this.f2417a = jSONObject.getInt("id");
            this.f2423g = jSONObject.getString("name");
            this.f2421e = jSONObject.getString(C1484a.f2396c);
            this.f2424h = jSONObject.getString("version");
            this.f2420d = jSONObject.getString("url");
            if (this.f2420d.length() == 0) {
                this.f2420d = String.format("%s/api/v1/addin/download/%s/%s", new Object[]{C1403a.f2076f, Integer.valueOf(3), "ologger-core"});
            }
            if (jSONObject.has("package") && jSONObject.has("lib")) {
                this.f2428l = jSONObject.getString("package");
                this.f2422f = String.format("%s.%s", new Object[]{this.f2428l, this.f2423g});
                this.f2427k = jSONObject.getBoolean("status");
                this.f2423g = jSONObject.getString("lib");
                this.f2425i = true;
                return;
            }
            int i;
            this.f2422f = jSONObject.getString(C1484a.f2398e);
            this.f2419c = jSONObject.has(C1484a.f2400g) ? jSONObject.getInt(C1484a.f2400g) : 0;
            if (jSONObject.has("type")) {
                i = jSONObject.getInt("type");
            } else {
                i = 0;
            }
            this.f2418b = i;
            if (!(jSONObject.has("name") && jSONObject.has(C1484a.f2398e) && jSONObject.has("url"))) {
                z = false;
            }
            this.f2425i = z;
        } catch (JSONException e) {
            this.f2426j = e.getMessage();
        }
    }

    public boolean m3143a() {
        return this.f2427k;
    }

    public String m3144b() {
        return this.f2428l;
    }

    public String m3147c() {
        return this.f2429m;
    }

    public int m3150d() {
        return this.f2417a;
    }

    public void m3141a(int i) {
        this.f2417a = i;
    }

    public int m3152e() {
        return this.f2418b;
    }

    public void m3145b(int i) {
        this.f2418b = i;
    }

    public int m3154f() {
        return this.f2419c;
    }

    public void m3148c(int i) {
        this.f2419c = i;
    }

    public String m3155g() {
        return this.f2420d;
    }

    public void m3142a(String str) {
        this.f2420d = str;
    }

    public String m3156h() {
        return this.f2421e;
    }

    public void m3146b(String str) {
        this.f2421e = str;
    }

    public String m3157i() {
        return this.f2422f;
    }

    public void m3149c(String str) {
        this.f2422f = str;
    }

    public String m3158j() {
        return this.f2423g;
    }

    public void m3151d(String str) {
        this.f2423g = str;
    }

    public String m3159k() {
        return this.f2424h;
    }

    public void m3153e(String str) {
        this.f2424h = str;
    }

    public String m3160l() {
        return this.f2426j;
    }

    public boolean m3161m() {
        return this.f2425i;
    }
}

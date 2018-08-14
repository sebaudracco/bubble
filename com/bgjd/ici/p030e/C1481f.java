package com.bgjd.ici.p030e;

import android.database.Cursor;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;

public class C1481f {
    private int f2383a = 0;
    private String f2384b = "";
    private String f2385c = "";
    private int f2386d = 0;
    private long f2387e = 0;
    private int f2388f = 0;

    public C1481f(Cursor cursor) {
        this.f2383a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2384b = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        this.f2385c = cursor.getString(cursor.getColumnIndexOrThrow("package"));
        this.f2386d = cursor.getInt(cursor.getColumnIndexOrThrow("is_system"));
        this.f2387e = cursor.getLong(cursor.getColumnIndexOrThrow("installdate"));
        this.f2388f = cursor.getInt(cursor.getColumnIndexOrThrow("hassdk"));
    }

    public int m3085a() {
        return this.f2383a;
    }

    public void m3086a(int i) {
        this.f2383a = i;
    }

    public String m3089b() {
        return this.f2384b;
    }

    public void m3088a(String str) {
        this.f2384b = str;
    }

    public String m3092c() {
        return this.f2385c;
    }

    public void m3091b(String str) {
        this.f2385c = str;
    }

    public int m3094d() {
        return this.f2386d;
    }

    public void m3090b(int i) {
        this.f2386d = i;
    }

    public int m3095e() {
        return this.f2388f;
    }

    public void m3093c(int i) {
        this.f2388f = i;
    }

    public long m3096f() {
        return this.f2387e;
    }

    public void m3087a(long j) {
        this.f2387e = j;
    }

    public JSONObject m3097g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f2384b.replaceAll("[^\\w\\s\\-]*", ""));
            jSONObject.put("b", this.f2385c);
            jSONObject.put("c", this.f2386d);
            jSONObject.put("d", this.f2387e);
            jSONObject.put("e", this.f2388f);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}

package com.bgjd.ici.p030e;

import android.database.Cursor;
import com.appnext.base.p023b.C1048i;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.coremedia.iso.boxes.UserBox;

public class C1477b {
    private int f2363a = 0;
    private int f2364b = 0;
    private int f2365c = 0;
    private int f2366d = 0;
    private String f2367e = "";
    private double f2368f = 0.0d;
    private double f2369g = 0.0d;
    private double f2370h = 0.0d;

    public C1477b(Cursor cursor) {
        this.f2363a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2364b = cursor.getInt(cursor.getColumnIndexOrThrow("bid"));
        this.f2365c = cursor.getInt(cursor.getColumnIndexOrThrow("pid"));
        this.f2366d = cursor.getInt(cursor.getColumnIndexOrThrow("lid"));
        this.f2367e = cursor.getString(cursor.getColumnIndexOrThrow(UserBox.TYPE));
        this.f2368f = cursor.getDouble(cursor.getColumnIndexOrThrow(C1048i.ko));
        this.f2369g = cursor.getDouble(cursor.getColumnIndexOrThrow("lon"));
        this.f2370h = cursor.getDouble(cursor.getColumnIndexOrThrow("radius"));
    }

    public C1477b(int i, int i2, int i3, String str, double d, double d2, double d3) {
        this.f2364b = i;
        this.f2365c = i2;
        this.f2366d = i3;
        this.f2367e = str;
        this.f2368f = d;
        this.f2369g = d2;
        this.f2370h = d3;
    }

    public C1477b(JSONObject jSONObject) {
        try {
            this.f2364b = jSONObject.getInt("id");
            this.f2365c = jSONObject.getInt("pid");
            this.f2366d = jSONObject.getInt("lid");
            this.f2367e = jSONObject.getString(UserBox.TYPE);
            this.f2368f = ((Double) jSONObject.get(C1048i.ko)).doubleValue();
            this.f2369g = ((Double) jSONObject.get("lon")).doubleValue();
            this.f2370h = ((Double) jSONObject.get("rad")).doubleValue();
        } catch (JSONException e) {
        }
    }

    public String m3067a() {
        return String.format("INSERT OR IGNORE INTO %s (bid,pid,lid,uuid,lat,lon,radius) VALUES(%s,%s,%s,'%s',%s,%s,%s)", new Object[]{C1403a.f2059A, Integer.valueOf(this.f2364b), Integer.valueOf(this.f2365c), Integer.valueOf(this.f2366d), this.f2367e, Double.valueOf(this.f2368f), Double.valueOf(this.f2369g), Double.valueOf(this.f2370h)});
    }

    public String toString() {
        return m3067a();
    }

    public int m3068b() {
        return this.f2364b;
    }

    public int m3069c() {
        return this.f2365c;
    }

    public int m3070d() {
        return this.f2366d;
    }

    public String m3071e() {
        return this.f2367e;
    }

    public double m3072f() {
        return this.f2368f;
    }

    public double m3073g() {
        return this.f2369g;
    }

    public double m3074h() {
        return this.f2370h;
    }
}

package com.bgjd.ici.p030e;

import android.database.Cursor;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1403a;

public class C1482g {
    private int f2389a = 0;
    private int f2390b = 0;
    private String f2391c = "";
    private String f2392d = "";
    private int f2393e = 0;

    public int m3098a() {
        return this.f2389a;
    }

    public int m3099b() {
        return this.f2390b;
    }

    public String m3100c() {
        return this.f2391c;
    }

    public String m3101d() {
        return this.f2392d;
    }

    public int m3102e() {
        return this.f2393e;
    }

    public C1482g(Cursor cursor) {
        this.f2389a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2390b = cursor.getInt(cursor.getColumnIndexOrThrow("data_type"));
        this.f2391c = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        this.f2392d = cursor.getString(cursor.getColumnIndexOrThrow("logs"));
        this.f2393e = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
    }

    public C1482g(int i, String str, String str2) {
        this.f2390b = i;
        this.f2391c = str;
        this.f2392d = str2;
    }

    public String m3103f() {
        return String.format("INSERT OR IGNORE INTO %s (data_type,name,logs) VALUES(%s,'%s','%s')", new Object[]{C1403a.f2096z, Integer.valueOf(this.f2390b), this.f2391c, this.f2392d});
    }

    public String toString() {
        return String.format("INSERT OR IGNORE INTO %s (data_type,name,logs) VALUES(%s,'%s','%s')", new Object[]{C1403a.f2096z, Integer.valueOf(this.f2390b), this.f2391c, this.f2392d});
    }

    public JSONObject m3104g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f2390b);
            jSONObject.put("b", this.f2391c);
            jSONObject.put("c", this.f2392d.length() > 0 ? new JSONObject(this.f2392d) : new JSONObject());
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}

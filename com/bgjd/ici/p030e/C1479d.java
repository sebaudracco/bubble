package com.bgjd.ici.p030e;

import android.database.Cursor;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1404b;

public class C1479d {
    int f2373a = 0;
    int f2374b = 0;
    String f2375c = "";
    String f2376d = "";
    int f2377e = 0;
    long f2378f = 0;
    long f2379g = 0;

    public C1479d(Cursor cursor) {
        this.f2373a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2374b = cursor.getInt(cursor.getColumnIndexOrThrow("brw_id"));
        this.f2375c = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        this.f2376d = cursor.getString(cursor.getColumnIndexOrThrow("url"));
        this.f2377e = cursor.getInt(cursor.getColumnIndexOrThrow(C1404b.as));
        this.f2378f = cursor.getLong(cursor.getColumnIndexOrThrow(C1404b.at));
        this.f2379g = cursor.getLong(cursor.getColumnIndexOrThrow("date"));
    }

    public JSONObject m3077a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f2375c);
            jSONObject.put("b", this.f2376d);
            jSONObject.put("c", this.f2377e);
            jSONObject.put("d", this.f2378f);
            jSONObject.put("e", this.f2379g);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public int m3078b() {
        return this.f2377e;
    }

    public int m3079c() {
        return this.f2374b;
    }

    public int m3080d() {
        return this.f2373a;
    }
}

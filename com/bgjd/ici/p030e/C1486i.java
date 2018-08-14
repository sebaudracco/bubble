package com.bgjd.ici.p030e;

import android.database.Cursor;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;

public class C1486i {
    private int f2414a = 0;
    private String f2415b = "";
    private int f2416c = 0;

    public C1486i(Cursor cursor) {
        this.f2414a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2415b = cursor.getString(cursor.getColumnIndexOrThrow("processname"));
        this.f2416c = cursor.getInt(cursor.getColumnIndexOrThrow("importance"));
    }

    public JSONObject m3139a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f2415b);
            jSONObject.put("b", this.f2416c);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public int m3140b() {
        return this.f2414a;
    }
}

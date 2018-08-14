package com.vungle.publisher;

import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class wy {
    final String f11282a;
    final String f11283b;
    final String f11284c;

    public wy(String str, JSONObject jSONObject) throws JSONException {
        this.f11282a = jSONObject.getString(SchemaSymbols.ATTVAL_EXTENSION);
        this.f11283b = jSONObject.getString("url");
        this.f11284c = str;
    }

    public String m14117a() {
        return this.f11282a;
    }

    public String m14118b() {
        return this.f11283b;
    }

    public String m14119c() {
        return this.f11284c;
    }
}

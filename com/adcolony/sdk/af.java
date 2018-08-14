package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import org.json.JSONException;
import org.json.JSONObject;

class af {
    private String f527a;
    private JSONObject f528b;

    af(JSONObject jSONObject) {
        try {
            this.f528b = jSONObject;
            this.f527a = jSONObject.getString("m_type");
        } catch (JSONException e) {
            new C0595a().m622a("JSON Error in ADCMessage constructor: ").m622a(e.toString()).m624a(aa.f484h);
        }
    }

    af(String str, int i) {
        try {
            this.f527a = str;
            this.f528b = new JSONObject();
            this.f528b.put("m_target", i);
        } catch (JSONException e) {
            new C0595a().m622a("JSON Error in ADCMessage constructor: ").m622a(e.toString()).m624a(aa.f484h);
        }
    }

    af(String str, int i, String str2) {
        try {
            this.f527a = str;
            this.f528b = C0802y.m1454a(str2);
            this.f528b.put("m_target", i);
        } catch (JSONException e) {
            new C0595a().m622a("JSON Error in ADCMessage constructor: ").m622a(e.toString()).m624a(aa.f484h);
        }
    }

    af(String str, int i, JSONObject jSONObject) {
        try {
            this.f527a = str;
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            this.f528b = jSONObject;
            this.f528b.put("m_target", i);
        } catch (JSONException e) {
            new C0595a().m622a("JSON Error in ADCMessage constructor: ").m622a(e.toString()).m624a(aa.f484h);
        }
    }

    af m692a() {
        return m694a((JSONObject) null);
    }

    af m693a(String str) {
        return m694a(C0802y.m1454a(str));
    }

    af m694a(JSONObject jSONObject) {
        try {
            af afVar = new af("reply", this.f528b.getInt("m_origin"), jSONObject);
            afVar.f528b.put("m_id", this.f528b.getInt("m_id"));
            return afVar;
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCMessage's create_reply(): ").m622a(e.toString()).m624a(aa.f484h);
            return new af("JSONException", 0);
        }
    }

    void m695b() {
        C0594a.m610a(this.f527a, this.f528b);
    }

    JSONObject m698c() {
        return this.f528b;
    }

    void m697b(JSONObject jSONObject) {
        this.f528b = jSONObject;
    }

    String m699d() {
        return this.f527a;
    }

    void m696b(String str) {
        this.f527a = str;
    }
}

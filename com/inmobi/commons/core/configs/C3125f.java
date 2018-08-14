package com.inmobi.commons.core.configs;

import com.mobfox.sdk.networking.RequestParams;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PkConfig */
public class C3125f extends C3045a {
    private String f7668a = "010001";
    private String f7669b = "E72409364B865B757E1D6B8DB73011BBB1D20C1A9F931ADD3C4C09E2794CE102F8AA7F2D50EB88F9880A576E6C7B0E95712CAE9416F7BACB798564627846E93B";
    private String f7670c = "rsa";
    private String f7671d = SchemaSymbols.ATTVAL_TRUE_1;

    public String mo6231a() {
        return "pk";
    }

    public void mo6232a(JSONObject jSONObject) throws JSONException {
        super.mo6232a(jSONObject);
        this.f7668a = jSONObject.getString("e");
        this.f7669b = jSONObject.getString(RequestParams.f9036M);
        this.f7670c = jSONObject.getString("alg");
        this.f7671d = jSONObject.getString("ver");
    }

    public JSONObject mo6233b() throws JSONException {
        JSONObject b = super.mo6233b();
        b.put("e", this.f7668a);
        b.put(RequestParams.f9036M, this.f7669b);
        b.put("alg", this.f7670c);
        b.put("ver", this.f7671d);
        return b;
    }

    public boolean mo6234c() {
        if (this.f7668a.trim().length() == 0 || this.f7669b.trim().length() == 0 || this.f7670c.trim().length() == 0 || this.f7671d.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public C3045a mo6235d() {
        return new C3125f();
    }

    public String m10212e() {
        return this.f7668a;
    }

    public String m10213f() {
        return this.f7669b;
    }

    public String m10214g() {
        return this.f7671d;
    }
}

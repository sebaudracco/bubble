package com.yandex.metrica.impl.ob;

import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

public final class cm {
    private final String f12114a;
    private final int f12115b;
    private final boolean f12116c;

    public cm(JSONObject jSONObject) throws JSONException {
        this.f12114a = jSONObject.getString("name");
        this.f12116c = jSONObject.getBoolean(SchemaSymbols.ATTVAL_REQUIRED);
        this.f12115b = jSONObject.optInt("version", -1);
    }

    public cm(String str, int i, boolean z) {
        this.f12114a = str;
        this.f12115b = i;
        this.f12116c = z;
    }

    public cm(String str, boolean z) {
        this(str, -1, z);
    }

    public JSONObject m15602a() throws JSONException {
        JSONObject put = new JSONObject().put("name", this.f12114a).put(SchemaSymbols.ATTVAL_REQUIRED, this.f12116c);
        if (this.f12115b != -1) {
            put.put("version", this.f12115b);
        }
        return put;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        cm cmVar = (cm) o;
        if (this.f12115b != cmVar.f12115b) {
            return false;
        }
        if (this.f12116c != cmVar.f12116c) {
            return false;
        }
        if (this.f12114a != null) {
            return this.f12114a.equals(cmVar.f12114a);
        }
        if (cmVar.f12114a != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f12114a != null) {
            hashCode = this.f12114a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = ((hashCode * 31) + this.f12115b) * 31;
        if (this.f12116c) {
            i = 1;
        }
        return hashCode + i;
    }
}

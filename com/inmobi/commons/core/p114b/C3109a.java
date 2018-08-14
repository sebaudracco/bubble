package com.inmobi.commons.core.p114b;

import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashConfig */
public class C3109a extends C3045a {
    private static final String f7607a = C3109a.class.getSimpleName();
    private JSONObject f7608b;
    private boolean f7609c = false;

    private JSONObject m10112g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", true);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", true);
        return jSONObject;
    }

    public C3109a() {
        try {
            this.f7608b = m10112g();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7607a, "Error in default telemetry config");
        }
    }

    public void mo6232a(JSONObject jSONObject) {
        try {
            super.mo6232a(jSONObject);
            this.f7608b = jSONObject.getJSONObject("telemetry");
            this.f7609c = jSONObject.has("shouldProcessCatchEvent") ? jSONObject.getBoolean("shouldProcessCatchEvent") : false;
        } catch (JSONException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7607a, "Error parsing Crash Config " + e.toString());
        }
    }

    public JSONObject mo6233b() {
        try {
            JSONObject b = super.mo6233b();
            b.put("telemetry", this.f7608b);
            b.put("shouldProcessCatchEvent", this.f7609c);
            return b;
        } catch (JSONException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7607a, "Error parsing Crash Config " + e.toString());
            return null;
        }
    }

    public JSONObject m10118e() {
        return this.f7608b;
    }

    public String mo6231a() {
        return "crashReporting";
    }

    public boolean mo6234c() {
        return true;
    }

    public C3045a mo6235d() {
        return new C3109a();
    }

    public boolean m10119f() {
        return this.f7609c;
    }
}

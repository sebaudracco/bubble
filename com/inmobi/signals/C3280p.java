package com.inmobi.signals;

import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import cz.msebera.android.httpclient.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SignalsConfig */
public class C3280p extends C3045a {
    private static final String f8286a = C3045a.class.getSimpleName();
    private C3279b f8287b = new C3279b();
    private C3278a f8288c = new C3278a();
    private JSONObject f8289d;

    /* compiled from: SignalsConfig */
    public static class C3278a {
        private boolean f8259a = false;
        private String f8260b = "http://dock.inmobi.com/carb/v1/i";
        private String f8261c = "http://dock.inmobi.com/carb/v1/o";
        private int f8262d = 86400;
        private int f8263e = 3;
        private int f8264f = 60;
        private int f8265g = 60;
        private long f8266h = 307200;

        public boolean m11015a() {
            return this.f8259a;
        }

        public String m11016b() {
            return this.f8260b;
        }

        public String m11017c() {
            return this.f8261c;
        }

        public int m11018d() {
            return this.f8262d;
        }

        public int m11019e() {
            return this.f8263e;
        }

        public int m11020f() {
            return this.f8264f;
        }

        public int m11021g() {
            return this.f8265g;
        }

        public long m11022h() {
            return this.f8266h;
        }
    }

    /* compiled from: SignalsConfig */
    public static class C3279b {
        private boolean f8267a = false;
        private int f8268b = HttpStatus.SC_MULTIPLE_CHOICES;
        private int f8269c = 3;
        private int f8270d = 50;
        private String f8271e = "https://sdkm.w.inmobi.com/user/e.asm";
        private int f8272f = 3;
        private int f8273g = 60;
        private boolean f8274h = false;
        private boolean f8275i = false;
        private int f8276j = 0;
        private boolean f8277k = false;
        private boolean f8278l = false;
        private int f8279m = 0;
        private boolean f8280n = false;
        private boolean f8281o = false;
        private boolean f8282p = false;
        private boolean f8283q = false;
        private int f8284r = 180;
        private int f8285s = 50;

        public boolean m11061a() {
            return this.f8267a;
        }

        public int m11062b() {
            return this.f8268b;
        }

        public int m11063c() {
            return this.f8269c;
        }

        public int m11064d() {
            return this.f8270d;
        }

        public String m11065e() {
            return this.f8271e;
        }

        public int m11066f() {
            return this.f8272f;
        }

        public int m11067g() {
            return this.f8273g;
        }

        public boolean m11068h() {
            return this.f8274h && this.f8267a;
        }

        public boolean m11069i() {
            return this.f8275i && this.f8267a;
        }

        public int m11070j() {
            return this.f8276j;
        }

        public boolean m11071k() {
            return this.f8277k && this.f8267a;
        }

        public boolean m11072l() {
            return this.f8278l && this.f8267a;
        }

        public int m11073m() {
            return this.f8279m;
        }

        public boolean m11074n() {
            return this.f8280n && this.f8267a;
        }

        public boolean m11075o() {
            return this.f8281o && this.f8267a;
        }

        public boolean m11076p() {
            return this.f8282p && this.f8267a;
        }

        public boolean m11077q() {
            return this.f8283q && this.f8267a;
        }

        public int m11078r() {
            return this.f8284r;
        }

        public int m11079s() {
            return this.f8285s;
        }
    }

    private JSONObject m11080h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", true);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", true);
        return jSONObject;
    }

    public C3280p() {
        try {
            this.f8289d = m11080h();
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8286a, "Default telemetry config provided for ads is invalid.", e);
        }
    }

    public String mo6231a() {
        return "signals";
    }

    public void mo6232a(JSONObject jSONObject) throws JSONException {
        super.mo6232a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("ice");
        this.f8287b.f8268b = jSONObject2.getInt("sampleInterval");
        this.f8287b.f8270d = jSONObject2.getInt("sampleHistorySize");
        this.f8287b.f8269c = jSONObject2.getInt("stopRequestTimeout");
        this.f8287b.f8267a = jSONObject2.getBoolean("enabled");
        this.f8287b.f8271e = jSONObject2.getString("endPoint");
        this.f8287b.f8272f = jSONObject2.getInt("maxRetries");
        this.f8287b.f8273g = jSONObject2.getInt("retryInterval");
        this.f8287b.f8274h = jSONObject2.getBoolean("locationEnabled");
        this.f8287b.f8275i = jSONObject2.getBoolean("sessionEnabled");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("w");
        this.f8287b.f8276j = jSONObject3.getInt("wf");
        this.f8287b.f8278l = jSONObject3.getBoolean("cwe");
        this.f8287b.f8277k = jSONObject3.getBoolean("vwe");
        jSONObject3 = jSONObject2.getJSONObject("c");
        this.f8287b.f8280n = jSONObject3.getBoolean("oe");
        this.f8287b.f8282p = jSONObject3.getBoolean("cce");
        this.f8287b.f8281o = jSONObject3.getBoolean("vce");
        this.f8287b.f8279m = jSONObject3.getInt("cof");
        jSONObject2 = jSONObject2.getJSONObject("ar");
        this.f8287b.f8283q = jSONObject2.getBoolean("e");
        this.f8287b.f8284r = jSONObject2.getInt("sampleInterval");
        this.f8287b.f8285s = jSONObject2.getInt("maxHistorySize");
        jSONObject2 = jSONObject.getJSONObject("carb");
        this.f8288c.f8259a = jSONObject2.getBoolean("enabled");
        this.f8288c.f8260b = jSONObject2.getString("getEndPoint");
        this.f8288c.f8261c = jSONObject2.getString("postEndPoint");
        this.f8288c.f8262d = jSONObject2.getInt("retrieveFrequency");
        this.f8288c.f8263e = jSONObject2.getInt("maxRetries");
        this.f8288c.f8264f = jSONObject2.getInt("retryInterval");
        this.f8288c.f8265g = jSONObject2.getInt("timeoutInterval");
        this.f8288c.f8266h = jSONObject2.getLong("maxGetResponseSize");
        this.f8289d = jSONObject.optJSONObject("telemetry");
    }

    public JSONObject mo6233b() throws JSONException {
        JSONObject b = super.mo6233b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sampleInterval", this.f8287b.f8268b);
        jSONObject.put("stopRequestTimeout", this.f8287b.f8269c);
        jSONObject.put("sampleHistorySize", this.f8287b.f8270d);
        jSONObject.put("enabled", this.f8287b.f8267a);
        jSONObject.put("endPoint", this.f8287b.f8271e);
        jSONObject.put("maxRetries", this.f8287b.f8272f);
        jSONObject.put("retryInterval", this.f8287b.f8273g);
        jSONObject.put("locationEnabled", this.f8287b.f8274h);
        jSONObject.put("sessionEnabled", this.f8287b.f8275i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("wf", this.f8287b.f8276j);
        jSONObject2.put("vwe", this.f8287b.f8277k);
        jSONObject2.put("cwe", this.f8287b.f8278l);
        jSONObject.put("w", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("cof", this.f8287b.f8279m);
        jSONObject2.put("vce", this.f8287b.f8281o);
        jSONObject2.put("cce", this.f8287b.f8282p);
        jSONObject2.put("oe", this.f8287b.f8280n);
        jSONObject.put("c", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("e", this.f8287b.f8283q);
        jSONObject2.put("sampleInterval", this.f8287b.f8284r);
        jSONObject2.put("maxHistorySize", this.f8287b.f8285s);
        jSONObject.put("ar", jSONObject2);
        b.put("ice", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("enabled", this.f8288c.f8259a);
        jSONObject.put("getEndPoint", this.f8288c.f8260b);
        jSONObject.put("postEndPoint", this.f8288c.f8261c);
        jSONObject.put("retrieveFrequency", this.f8288c.f8262d);
        jSONObject.put("maxRetries", this.f8288c.f8263e);
        jSONObject.put("retryInterval", this.f8288c.f8264f);
        jSONObject.put("timeoutInterval", this.f8288c.f8265g);
        jSONObject.put("maxGetResponseSize", this.f8288c.m11022h());
        b.put("carb", jSONObject);
        b.put("telemetry", this.f8289d);
        return b;
    }

    public boolean mo6234c() {
        if (this.f8287b.f8268b < 0 || this.f8287b.f8270d < 0 || this.f8287b.f8269c < 0 || this.f8287b.f8271e.trim().length() == 0 || this.f8287b.f8272f < 0 || this.f8287b.f8273g < 0 || this.f8287b.m11070j() < 0 || this.f8287b.m11073m() < 0 || this.f8287b.f8285s < 0 || this.f8287b.f8284r < 0 || this.f8288c.f8260b.trim().length() == 0 || this.f8288c.f8261c.trim().length() == 0) {
            return false;
        }
        if (!this.f8288c.f8260b.startsWith("http://") && !this.f8288c.f8260b.startsWith("https://")) {
            return false;
        }
        if ((this.f8288c.f8261c.startsWith("http://") || this.f8288c.f8261c.startsWith("https://")) && this.f8288c.f8262d >= 0 && this.f8288c.f8263e >= 0 && this.f8288c.f8264f >= 0 && this.f8288c.f8265g >= 0 && this.f8288c.f8266h >= 0) {
            return true;
        }
        return false;
    }

    public C3045a mo6235d() {
        return new C3280p();
    }

    public JSONObject m11086e() {
        return this.f8289d;
    }

    public C3279b m11087f() {
        return this.f8287b;
    }

    public C3278a m11088g() {
        return this.f8288c;
    }
}

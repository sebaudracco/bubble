package com.inmobi.commons.core.p115d;

import com.inmobi.commons.core.configs.C3045a;
import cz.msebera.android.httpclient.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TelemetryConfig */
class C3138e extends C3045a {
    private C3137d f7726a = new C3137d();
    private String f7727b = "https://sdkm.w.inmobi.com/metrics/e.asm?v=1&";
    private int f7728c = HttpStatus.SC_MULTIPLE_CHOICES;
    private int f7729d = 60;
    private int f7730e = 50;
    private int f7731f = 3;
    private int f7732g = 1000;
    private int f7733h = 10;
    private long f7734i = 259200;

    public String mo6231a() {
        return "telemetry";
    }

    public boolean mo6234c() {
        if (this.f7726a == null || this.f7726a.m10298c() < 0 || this.f7727b.trim().length() == 0) {
            return false;
        }
        if ((this.f7727b.startsWith("http://") || this.f7727b.startsWith("https://")) && this.f7729d >= 0 && this.f7728c >= 0 && this.f7731f >= 0 && this.f7733h > 0 && this.f7730e > 0 && this.f7732g > 0) {
            return true;
        }
        return false;
    }

    public void mo6232a(JSONObject jSONObject) throws JSONException {
        super.mo6232a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.f7726a.m10296b(jSONObject2.getBoolean("enabled"));
        this.f7726a.m10293a(jSONObject2.getInt("samplingFactor"));
        this.f7726a.m10294a(jSONObject2.getBoolean("metricEnabled"));
        m10301a(jSONObject.getString("url"));
        m10300a(jSONObject.getInt("processingInterval"));
        m10306f(jSONObject.getInt("retryInterval"));
        m10303c(jSONObject.getInt("maxBatchSize"));
        m10302b(jSONObject.getInt("maxRetryCount"));
        m10304d(jSONObject.getInt("maxEventsToPersist"));
        m10305e(jSONObject.getInt("memoryThreshold"));
        m10308a((long) jSONObject.getInt("eventTTL"));
    }

    public JSONObject mo6233b() throws JSONException {
        JSONObject b = super.mo6233b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", this.f7726a.m10297b());
        jSONObject.put("samplingFactor", this.f7726a.m10298c());
        jSONObject.put("metricEnabled", this.f7726a.m10299d());
        b.put("base", jSONObject);
        b.put("url", m10315g());
        b.put("processingInterval", m10320l());
        b.put("retryInterval", m10316h());
        b.put("maxBatchSize", m10318j());
        b.put("maxRetryCount", m10319k());
        b.put("maxEventsToPersist", m10321m());
        b.put("memoryThreshold", m10317i());
        b.put("eventTTL", m10313e());
        return b;
    }

    public long m10313e() {
        return this.f7734i;
    }

    public void m10308a(long j) {
        this.f7734i = j;
    }

    public boolean m10314f() {
        return this.f7726a.m10297b();
    }

    private void m10301a(String str) {
        this.f7727b = str;
    }

    public String m10315g() {
        return this.f7727b;
    }

    private void m10300a(int i) {
        this.f7728c = i;
    }

    private void m10302b(int i) {
        this.f7731f = i;
    }

    private void m10303c(int i) {
        this.f7730e = i;
    }

    private void m10304d(int i) {
        this.f7732g = i;
    }

    private void m10305e(int i) {
        this.f7733h = i;
    }

    private void m10306f(int i) {
        this.f7729d = i;
    }

    public int m10316h() {
        return this.f7729d;
    }

    public int m10317i() {
        return this.f7733h;
    }

    public int m10318j() {
        return this.f7730e;
    }

    public int m10319k() {
        return this.f7731f;
    }

    public int m10320l() {
        return this.f7728c;
    }

    public int m10321m() {
        return this.f7732g;
    }

    public C3045a mo6235d() {
        return new C3138e();
    }

    public C3137d m10322n() {
        return this.f7726a;
    }
}

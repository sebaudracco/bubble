package com.inmobi.commons.core.p115d;

import com.mopub.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TelemetryComponentConfig */
public class C3137d {
    private int f7721a = 0;
    private String f7722b = "telemetry";
    private boolean f7723c = true;
    private boolean f7724d = true;
    private Map<String, C3136a> f7725e = new HashMap();

    /* compiled from: TelemetryComponentConfig */
    static final class C3136a {
        private String f7718a;
        private int f7719b;
        private boolean f7720c;

        public C3136a(String str, int i, boolean z) {
            m10287a(str);
            m10286a(i);
            m10288a(z);
        }

        public String m10285a() {
            return this.f7718a;
        }

        public void m10287a(String str) {
            this.f7718a = str;
        }

        public void m10286a(int i) {
            this.f7719b = i;
        }

        public void m10288a(boolean z) {
            this.f7720c = z;
        }

        public boolean m10289b() {
            return this.f7720c;
        }
    }

    public C3137d(String str, JSONObject jSONObject, C3137d c3137d) {
        boolean z = true;
        if (jSONObject == null) {
            m10290a(str, c3137d);
            return;
        }
        String str2;
        JSONArray jSONArray;
        int i;
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    str2 = str;
                    this.f7722b = str2;
                    if (jSONObject.has("enabled")) {
                        z = jSONObject.getBoolean("enabled");
                    }
                    this.f7723c = z;
                    m10293a(jSONObject.has("samplingFactor") ? jSONObject.getInt("samplingFactor") : c3137d.m10298c());
                    m10294a(jSONObject.has("metricEnabled") ? jSONObject.getBoolean("metricEnabled") : c3137d.m10299d());
                    this.f7725e = new HashMap();
                    if (jSONObject.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
                        jSONArray = jSONObject.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
                        for (i = 0; i < jSONArray.length(); i++) {
                            C3136a c3136a = new C3136a();
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            c3136a.m10287a(jSONObject2.getString("type"));
                            c3136a.m10286a(jSONObject2.has("samplingFactor") ? jSONObject2.getInt("samplingFactor") : m10298c());
                            c3136a.m10288a(jSONObject2.has("metricEnabled") ? jSONObject2.getBoolean("metricEnabled") : m10299d());
                            this.f7725e.put(c3136a.m10285a(), c3136a);
                        }
                    }
                }
            } catch (JSONException e) {
                m10290a(str, c3137d);
                return;
            }
        }
        str2 = c3137d.m10292a();
        this.f7722b = str2;
        if (jSONObject.has("enabled")) {
            z = jSONObject.getBoolean("enabled");
        }
        this.f7723c = z;
        if (jSONObject.has("samplingFactor")) {
        }
        m10293a(jSONObject.has("samplingFactor") ? jSONObject.getInt("samplingFactor") : c3137d.m10298c());
        if (jSONObject.has("metricEnabled")) {
        }
        m10294a(jSONObject.has("metricEnabled") ? jSONObject.getBoolean("metricEnabled") : c3137d.m10299d());
        this.f7725e = new HashMap();
        if (jSONObject.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
            jSONArray = jSONObject.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            for (i = 0; i < jSONArray.length(); i++) {
                C3136a c3136a2 = new C3136a();
                JSONObject jSONObject22 = jSONArray.getJSONObject(i);
                c3136a2.m10287a(jSONObject22.getString("type"));
                if (jSONObject22.has("samplingFactor")) {
                }
                c3136a2.m10286a(jSONObject22.has("samplingFactor") ? jSONObject22.getInt("samplingFactor") : m10298c());
                if (jSONObject22.has("metricEnabled")) {
                }
                c3136a2.m10288a(jSONObject22.has("metricEnabled") ? jSONObject22.getBoolean("metricEnabled") : m10299d());
                this.f7725e.put(c3136a2.m10285a(), c3136a2);
            }
        }
    }

    public String m10292a() {
        return this.f7722b;
    }

    public boolean m10297b() {
        return this.f7723c;
    }

    public C3136a m10291a(String str) {
        C3136a c3136a = (C3136a) this.f7725e.get(str);
        return c3136a != null ? c3136a : new C3136a(str, m10298c(), m10299d());
    }

    private void m10290a(String str, C3137d c3137d) {
        m10296b(true);
        m10295b(str);
    }

    public int m10298c() {
        return this.f7721a;
    }

    public boolean m10299d() {
        return this.f7724d;
    }

    public void m10294a(boolean z) {
        this.f7724d = z;
    }

    public void m10296b(boolean z) {
        this.f7723c = z;
    }

    public void m10293a(int i) {
        this.f7721a = i;
    }

    public void m10295b(String str) {
        this.f7722b = str;
    }
}

package com.inmobi.commons.core.configs;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.inmobi.commons.core.configs.ConfigError.ErrorCode;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

final class ConfigNetworkResponse {
    private static final String f7637a = ConfigNetworkResponse.class.getName();
    private Map<String, C3045a> f7638b;
    private Map<String, ConfigResponse> f7639c = new HashMap();
    private C3143c f7640d;
    private ConfigError f7641e;
    private long f7642f;

    public static class ConfigResponse {
        private ConfigResponseStatus f7634a;
        private C3045a f7635b;
        private ConfigError f7636c;

        public enum ConfigResponseStatus {
            SUCCESS(200),
            NOT_MODIFIED(HttpStatus.SC_NOT_MODIFIED),
            PRODUCT_NOT_FOUND(404),
            INTERNAL_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR),
            UNKNOWN(-1);
            
            private int f7633a;

            private ConfigResponseStatus(int i) {
                this.f7633a = i;
            }

            public int getValue() {
                return this.f7633a;
            }

            public static ConfigResponseStatus fromValue(int i) {
                for (ConfigResponseStatus configResponseStatus : values()) {
                    if (configResponseStatus.f7633a == i) {
                        return configResponseStatus;
                    }
                }
                return UNKNOWN;
            }
        }

        public ConfigResponse(JSONObject jSONObject, C3045a c3045a) {
            this.f7635b = c3045a;
            if (jSONObject != null) {
                m10155a(jSONObject);
            }
        }

        private void m10155a(JSONObject jSONObject) {
            try {
                this.f7634a = ConfigResponseStatus.fromValue(jSONObject.getInt("status"));
                if (this.f7634a == ConfigResponseStatus.SUCCESS) {
                    this.f7635b.mo6232a(jSONObject.getJSONObject(FirebaseAnalytics$Param.CONTENT));
                    if (!this.f7635b.mo6234c()) {
                        m10157a(new ConfigError(ErrorCode.PARSING_ERROR, "The received config has failed validation."));
                        Logger.m10359a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f7637a, "Config type:" + this.f7635b.mo6231a() + " Error code:" + m10159c().m10153a() + " Error message:" + m10159c().m10154b());
                    }
                } else if (this.f7634a == ConfigResponseStatus.NOT_MODIFIED) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f7637a, "Config type:" + this.f7635b.mo6231a() + " Config not modified");
                } else {
                    m10157a(new ConfigError(ErrorCode.CONFIG_SERVER_INTERNAL_ERROR, this.f7634a.toString()));
                    Logger.m10359a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f7637a, "Config type:" + this.f7635b.mo6231a() + " Error code:" + m10159c().m10153a() + " Error message:" + m10159c().m10154b());
                }
            } catch (Throwable e) {
                m10157a(new ConfigError(ErrorCode.PARSING_ERROR, e.getLocalizedMessage()));
                Logger.m10360a(InternalLogLevel.INTERNAL, ConfigNetworkResponse.f7637a, "Config type:" + this.f7635b.mo6231a() + " Error code:" + m10159c().m10153a() + " Error message:" + m10159c().m10154b(), e);
            }
        }

        public C3045a m10156a() {
            return this.f7635b;
        }

        public ConfigResponseStatus m10158b() {
            return this.f7634a;
        }

        public ConfigError m10159c() {
            return this.f7636c;
        }

        public void m10157a(ConfigError configError) {
            this.f7636c = configError;
        }

        public boolean m10160d() {
            return this.f7636c != null;
        }
    }

    ConfigNetworkResponse(Map<String, C3045a> map, C3143c c3143c, long j) {
        this.f7638b = map;
        this.f7640d = c3143c;
        this.f7642f = j;
        m10164d();
    }

    private void m10164d() {
        if (this.f7640d.m10351a()) {
            for (Entry entry : this.f7638b.entrySet()) {
                ConfigResponse configResponse = new ConfigResponse(null, (C3045a) entry.getValue());
                configResponse.m10157a(new ConfigError(ErrorCode.NETWORK_ERROR, "Network error in fetching config."));
                this.f7639c.put(entry.getKey(), configResponse);
            }
            m10162a(new ConfigError(ErrorCode.NETWORK_ERROR, this.f7640d.m10355e().m10333b()));
            Logger.m10359a(InternalLogLevel.INTERNAL, f7637a, "Error code:" + m10166b().m10153a() + " Error message:" + m10166b().m10154b());
            try {
                Map hashMap = new HashMap();
                hashMap.put("name", m10161a(this.f7638b));
                hashMap.put("errorCode", String.valueOf(this.f7640d.m10355e().m10332a().getValue()));
                hashMap.put("reason", this.f7640d.m10355e().m10333b());
                hashMap.put("latency", Long.valueOf(this.f7642f));
                C3135c.m10255a().m10280a("root", "InvalidConfig", hashMap);
                return;
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7637a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f7640d.m10352b());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (this.f7638b.get(str) != null) {
                    this.f7639c.put(str, new ConfigResponse(jSONObject2, (C3045a) this.f7638b.get(str)));
                }
            }
        } catch (Throwable e2) {
            m10162a(new ConfigError(ErrorCode.PARSING_ERROR, e2.getLocalizedMessage()));
            Logger.m10360a(InternalLogLevel.INTERNAL, f7637a, "Error code:" + m10166b().m10153a() + " Error message:" + m10166b().m10154b(), e2);
            try {
                Map hashMap2 = new HashMap();
                hashMap2.put("name", m10161a(this.f7638b));
                hashMap2.put("errorCode", "ParsingError");
                hashMap2.put("reason", e2.getLocalizedMessage());
                hashMap2.put("latency", Long.valueOf(this.f7642f));
                C3135c.m10255a().m10280a("root", "InvalidConfig", hashMap2);
            } catch (Exception e3) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7637a, "Error in submitting telemetry event : (" + e3.getMessage() + ")");
            }
        }
    }

    public Map<String, ConfigResponse> m10165a() {
        return this.f7639c;
    }

    public ConfigError m10166b() {
        return this.f7641e;
    }

    private void m10162a(ConfigError configError) {
        this.f7641e = configError;
    }

    private static String m10161a(Map<String, C3045a> map) {
        String str = "";
        for (String str2 : map.keySet()) {
            str = str + str2 + ",";
        }
        return str.substring(0, str.length() - 1);
    }
}

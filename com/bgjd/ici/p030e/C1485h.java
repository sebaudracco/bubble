package com.bgjd.ici.p030e;

import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1485h {
    private static final String f2405a = "PLGM";
    private String f2406b = "";
    private String f2407c = "";
    private int f2408d = 0;
    private int f2409e = 0;
    private int f2410f = 0;
    private String f2411g = SchemaSymbols.ATTVAL_FALSE_0;
    private int f2412h = 0;
    private boolean f2413i = false;

    public static final class C1484a {
        public static final String f2394a = "id";
        public static final String f2395b = "version";
        public static final String f2396c = "checksum";
        public static final String f2397d = "name";
        public static final String f2398e = "class";
        public static final String f2399f = "minsdk";
        public static final String f2400g = "expire";
        public static final String f2401h = "type";
        private static String f2402i = "com.internal.plugin.%sPlugin";
        private static String f2403j = "com.bgjd.ici.plugin.MarketPluginIntentSettings";
        private static String f2404k = "com.internal.plugin.PluginEventListener";

        public static final class C1483a {
            public static String m3105a() {
                return "handleEvent";
            }

            public static String m3106b() {
                return "start";
            }

            public static String m3107c() {
                return "stop";
            }

            public static String m3108d() {
                return "setBeaconService";
            }

            public static String m3109e() {
                return "setExternalIntentSettings";
            }

            public static String m3110f() {
                return "setAdditional";
            }

            public static String m3111g() {
                return "isValid";
            }

            public static String m3112h() {
                return "getPluginIntentSettings";
            }

            public static String m3113i() {
                return "setPackageName";
            }

            public static String m3114j() {
                return "setServiceName";
            }

            public static String m3115k() {
                return "getServiceBootstrap";
            }

            public static String m3116l() {
                return "setPluginEventListener";
            }

            public static String m3117m() {
                return "onBeaconServiceConnect";
            }

            public static String m3118n() {
                return "getConnectivityChange";
            }

            public static String m3119o() {
                return "IsRunning";
            }
        }

        public static String m3120a() {
            return f2404k;
        }

        public static String m3121a(String str) {
            return String.format(f2402i, new Object[]{str});
        }

        public static String m3122b() {
            return C1404b.aP;
        }

        public static String m3123c() {
            return f2403j;
        }
    }

    public C1485h(JSONObject jSONObject) {
        try {
            m3125a(jSONObject.getString("name"));
            m3129b(jSONObject.getString(C1484a.f2398e));
            m3124a(jSONObject.getInt(C1484a.f2399f));
            m3131c(jSONObject.getInt("id"));
            m3128b(jSONObject.getInt(C1484a.f2400g));
            m3132c(jSONObject.getString("version"));
            m3134d(jSONObject.getInt("type"));
            this.f2413i = true;
        } catch (Throwable e) {
            C1402i.m2898a(f2405a, e, e.getMessage());
        }
    }

    public boolean m3126a() {
        return this.f2413i;
    }

    public String m3127b() {
        return this.f2406b;
    }

    public void m3125a(String str) {
        this.f2406b = str;
    }

    public int m3130c() {
        return this.f2408d;
    }

    public void m3124a(int i) {
        this.f2408d = i;
    }

    public String m3133d() {
        return this.f2407c;
    }

    public void m3129b(String str) {
        this.f2407c = str;
    }

    public int m3135e() {
        return this.f2410f;
    }

    public void m3128b(int i) {
        this.f2410f = i;
    }

    public int m3136f() {
        return this.f2409e;
    }

    public void m3131c(int i) {
        this.f2409e = i;
    }

    public String m3137g() {
        return this.f2411g;
    }

    public void m3132c(String str) {
        this.f2411g = str;
    }

    public int m3138h() {
        return this.f2412h;
    }

    public void m3134d(int i) {
        this.f2412h = i;
    }
}

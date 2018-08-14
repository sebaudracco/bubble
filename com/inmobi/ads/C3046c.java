package com.inmobi.ads;

import android.graphics.Color;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mobfox.sdk.bannerads.SizeUtils;
import com.mopub.common.AdType;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdConfig */
public final class C3046c extends C3045a {
    private static final String f7374a = C3046c.class.getSimpleName();
    private static final String f7375b = ("row".equals("staging") ? "http://i.w.inmobi.com/showad.asm" : "http://i.w.inmobi.com/showad.asm");
    private static final String f7376c = ("row".equals("staging") ? "https://sdktm.w.inmobi.com/sdkpubreq" : "https://sdktm.w.inmobi.com/sdkpubreq");
    private static final Object f7377d = new Object();
    private String f7378e = f7375b;
    private String f7379f = f7376c;
    private int f7380g = 20;
    private int f7381h = 60;
    private int f7382i = 60;
    private C3038b f7383j;
    private Map<String, C3038b> f7384k;
    private C3039c f7385l = new C3039c();
    private C3042f f7386m = new C3042f();
    private C3040d f7387n = new C3040d();
    private C3044h f7388o = new C3044h();
    private JSONObject f7389p;
    private C3041e f7390q = new C3041e();
    private C3043g f7391r = new C3043g();
    private C3037a f7392s = new C3037a();

    /* compiled from: AdConfig */
    public static final class C3037a {
        private int f7329a = 3;
        private int f7330b = 1;
        private int f7331c = 10;
        private long f7332d = 104857600;
        private long f7333e = 259200;

        public int m9610a() {
            return this.f7329a;
        }

        public int m9611b() {
            return this.f7330b;
        }

        public int m9612c() {
            return this.f7331c;
        }

        public long m9613d() {
            return this.f7333e;
        }

        public long m9614e() {
            return this.f7332d;
        }
    }

    /* compiled from: AdConfig */
    static final class C3038b {
        private int f7334a = 1;
        private int f7335b;
        private int f7336c;
        private long f7337d;

        C3038b() {
        }

        public boolean m9623a() {
            if (this.f7335b <= 0 || this.f7334a < 0 || this.f7336c < 0 || this.f7337d < 0) {
                return false;
            }
            return true;
        }

        public int m9624b() {
            return this.f7334a;
        }

        public int m9625c() {
            return this.f7335b;
        }

        public int m9626d() {
            return this.f7336c;
        }

        public long m9627e() {
            return this.f7337d;
        }
    }

    /* compiled from: AdConfig */
    public static final class C3039c {
        private int f7338a = 3;
        private int f7339b = 60;
        private int f7340c = 120;
        private int f7341d = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        private int f7342e = 10;
        private long f7343f = 10800;

        public int m9634a() {
            return this.f7338a;
        }

        public int m9635b() {
            return this.f7339b;
        }

        public int m9636c() {
            return this.f7340c;
        }

        public int m9637d() {
            return this.f7341d;
        }

        public int m9638e() {
            return this.f7342e;
        }

        public long m9639f() {
            return this.f7343f;
        }
    }

    /* compiled from: AdConfig */
    public static final class C3040d {
        private long f7344a = 432000;
        private int f7345b = 3;
        private int f7346c = 60;
        private String f7347d = "https://i.l.inmobicdn.net/sdk/sdk/500/android/mraid.js";

        public long m9644a() {
            return this.f7344a;
        }

        public int m9645b() {
            return this.f7345b;
        }

        public int m9646c() {
            return this.f7346c;
        }

        public String m9647d() {
            return this.f7347d;
        }
    }

    /* compiled from: AdConfig */
    public static final class C3041e {
        private boolean f7348a = false;
        private long f7349b = 259200;
        private int f7350c = 5;

        public long m9653a() {
            return this.f7349b;
        }

        public boolean m9654b() {
            return this.f7348a;
        }

        public int m9655c() {
            return this.f7350c;
        }
    }

    /* compiled from: AdConfig */
    public static final class C3042f {
        private int f7351a = 60;
        private int f7352b = 320;
        private int f7353c = SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT;
        private int f7354d = 100;
        private String f7355e = "#00000000";
        private int f7356f = Color.parseColor("#00000000");
        private int f7357g = 5;
        private int f7358h = 20;
        private long f7359i = 5242880;
        private ArrayList<String> f7360j = new ArrayList(Arrays.asList(new String[]{"video/mp4"}));

        public int m9667a() {
            return this.f7352b;
        }

        public int m9668b() {
            return this.f7353c;
        }

        public int m9669c() {
            return this.f7354d;
        }

        public int m9670d() {
            return this.f7356f;
        }

        public int m9671e() {
            return this.f7357g;
        }

        public int m9672f() {
            return this.f7358h;
        }

        public long m9673g() {
            return this.f7359i;
        }

        public List<String> m9674h() {
            return this.f7360j;
        }

        public int m9675i() {
            return this.f7351a;
        }
    }

    /* compiled from: AdConfig */
    public static final class C3043g {
        private int f7361a = 3;
        private long f7362b = 3145728;
        private long f7363c = 31457280;
        private ArrayList<String> f7364d = new ArrayList(Arrays.asList(new String[]{"video/mp4"}));

        public int m9683a() {
            return this.f7361a;
        }

        public long m9684b() {
            return this.f7362b;
        }

        public long m9685c() {
            return this.f7363c;
        }

        public List<String> m9686d() {
            return this.f7364d;
        }
    }

    /* compiled from: AdConfig */
    public static final class C3044h {
        private int f7365a = 50;
        private int f7366b = 1000;
        private int f7367c = 100;
        private int f7368d = 250;
        private int f7369e = 50;
        private int f7370f = 0;
        private boolean f7371g = true;
        private boolean f7372h = true;

        public int m9695a() {
            return this.f7365a;
        }

        public int m9697b() {
            return this.f7366b;
        }

        public int m9698c() {
            return this.f7369e;
        }

        public int m9699d() {
            return this.f7370f;
        }

        public void m9696a(int i) {
            this.f7370f = i;
        }

        public int m9700e() {
            return this.f7367c;
        }

        public int m9701f() {
            return this.f7368d;
        }

        boolean m9702g() {
            return this.f7371g;
        }

        boolean m9703h() {
            return this.f7372h;
        }
    }

    private JSONObject m9711s() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", 1);
        jSONObject2.put("fetchLimit", 1);
        jSONObject2.put("minThreshold", 0);
        jSONObject2.put("timeToLive", 3300);
        jSONObject.put("base", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", 12);
        jSONObject2.put("fetchLimit", 3);
        jSONObject2.put("minThreshold", 0);
        jSONObject2.put("timeToLive", 3300);
        jSONObject.put("inlban", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", 100);
        jSONObject2.put("fetchLimit", 5);
        jSONObject2.put("minThreshold", 2);
        jSONObject2.put("timeToLive", 3300);
        jSONObject.put("native", jSONObject2);
        return jSONObject;
    }

    private JSONObject m9712t() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", true);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", true);
        return jSONObject;
    }

    public C3046c() {
        try {
            m9710b(m9711s());
            this.f7389p = m9712t();
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7374a, "Default config provided for ads is invalid.", e);
        }
    }

    public String mo6231a() {
        return "ads";
    }

    public void mo6232a(JSONObject jSONObject) throws JSONException {
        int i = 0;
        super.mo6232a(jSONObject);
        if (jSONObject.has("url")) {
            this.f7378e = jSONObject.getString("url");
        }
        if (jSONObject.has("requestUrl")) {
            this.f7379f = jSONObject.getString("requestUrl");
        }
        this.f7380g = jSONObject.getInt("minimumRefreshInterval");
        this.f7381h = jSONObject.getInt("defaultRefreshInterval");
        this.f7382i = jSONObject.getInt("fetchTimeout");
        m9710b(jSONObject.getJSONObject("cache"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("imai");
        this.f7385l.f7338a = jSONObject2.getInt("maxRetries");
        this.f7385l.f7339b = jSONObject2.getInt("pingInterval");
        this.f7385l.f7340c = jSONObject2.getInt("pingTimeout");
        this.f7385l.f7341d = jSONObject2.getInt("maxDbEvents");
        this.f7385l.f7342e = jSONObject2.getInt("maxEventBatch");
        this.f7385l.f7343f = jSONObject2.getLong("pingCacheExpiry");
        jSONObject2 = jSONObject.getJSONObject("rendering");
        this.f7386m.f7351a = jSONObject2.getInt("renderTimeout");
        this.f7386m.f7353c = jSONObject2.getInt("picHeight");
        this.f7386m.f7352b = jSONObject2.getInt("picWidth");
        this.f7386m.f7354d = jSONObject2.getInt("picQuality");
        this.f7386m.f7355e = jSONObject2.getString("webviewBackground");
        this.f7386m.f7357g = jSONObject2.getInt("maxVibrationDuration");
        this.f7386m.f7358h = jSONObject2.getInt("maxVibrationPatternLength");
        this.f7386m.f7359i = (long) jSONObject2.getJSONObject("savecontent").getInt("maxSaveSize");
        synchronized (f7377d) {
            this.f7386m.f7360j.clear();
            JSONArray jSONArray = jSONObject2.getJSONObject("savecontent").getJSONArray("allowedContentType");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.f7386m.f7360j.add(jSONArray.getString(i2));
            }
        }
        jSONObject2 = jSONObject.getJSONObject(AdType.MRAID);
        this.f7387n.f7344a = jSONObject2.getLong("expiry");
        this.f7387n.f7345b = jSONObject2.getInt("maxRetries");
        this.f7387n.f7346c = jSONObject2.getInt("retryInterval");
        this.f7387n.f7347d = jSONObject2.getString("url");
        if (jSONObject.has("telemetry")) {
            this.f7389p = jSONObject.getJSONObject("telemetry");
        }
        jSONObject2 = jSONObject.getJSONObject("viewability");
        this.f7388o.f7365a = jSONObject2.getInt("impressionMinPercentageViewed");
        this.f7388o.f7366b = jSONObject2.getInt("impressionMinTimeViewed");
        this.f7388o.f7367c = jSONObject2.optInt("visibilityThrottleMillis", 100);
        this.f7388o.f7368d = jSONObject2.optInt("impressionPollIntervalMillis", 250);
        this.f7388o.f7371g = jSONObject2.optBoolean("moatEnabled", false);
        this.f7388o.f7372h = jSONObject2.optBoolean("iasEnabled", false);
        jSONObject2 = jSONObject2.getJSONObject("video");
        this.f7388o.f7369e = jSONObject2.getInt("impressionMinPercentageViewed");
        this.f7388o.f7370f = jSONObject2.getInt("impressionMinTimeViewed");
        jSONObject2 = jSONObject.getJSONObject("preload").getJSONObject("base");
        this.f7390q.f7348a = jSONObject2.getBoolean("enabled");
        this.f7390q.f7349b = jSONObject2.getLong("placementExpiry");
        this.f7390q.f7350c = jSONObject2.getInt("maxPreloadedAds");
        jSONObject2 = jSONObject.getJSONObject("vastVideo");
        this.f7391r.f7361a = jSONObject2.getInt("maxWrapperLimit");
        this.f7391r.f7362b = jSONObject2.getLong("optimalVastVideoSize");
        this.f7391r.f7363c = jSONObject2.getLong("vastMaxAssetSize");
        synchronized (f7377d) {
            this.f7391r.f7364d.clear();
            JSONArray jSONArray2 = jSONObject2.getJSONArray("allowedContentType");
            while (i < jSONArray2.length()) {
                this.f7391r.f7364d.add(jSONArray2.getString(i));
                i++;
            }
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject("assetCache");
        this.f7392s.f7330b = jSONObject3.getInt("retryInterval");
        this.f7392s.f7329a = jSONObject3.getInt("maxRetries");
        this.f7392s.f7331c = jSONObject3.getInt("maxCachedAssets");
        this.f7392s.f7332d = (long) jSONObject3.getInt("maxCacheSize");
        this.f7392s.f7333e = jSONObject3.getLong("timeToLive");
    }

    private void m9710b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.f7383j = new C3038b();
        this.f7383j.f7334a = jSONObject2.getInt("maxCacheSize");
        this.f7383j.f7335b = jSONObject2.getInt("fetchLimit");
        this.f7383j.f7336c = jSONObject2.getInt("minThreshold");
        this.f7383j.f7337d = jSONObject2.getLong("timeToLive");
        jSONObject.remove("base");
        this.f7384k = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject3 = jSONObject.getJSONObject(str);
            C3038b c3038b = new C3038b();
            c3038b.f7334a = jSONObject3.has("maxCacheSize") ? jSONObject3.getInt("maxCacheSize") : this.f7383j.f7334a;
            c3038b.f7335b = jSONObject3.has("fetchLimit") ? jSONObject3.getInt("fetchLimit") : this.f7383j.f7335b;
            c3038b.f7336c = jSONObject3.has("minThreshold") ? jSONObject3.getInt("minThreshold") : this.f7383j.f7336c;
            c3038b.f7337d = jSONObject3.has("timeToLive") ? (long) jSONObject3.getInt("timeToLive") : this.f7383j.f7337d;
            this.f7384k.put(str, c3038b);
        }
    }

    public JSONObject mo6233b() throws JSONException {
        JSONObject b = super.mo6233b();
        b.put("url", this.f7378e);
        b.put("requestUrl", this.f7379f);
        b.put("minimumRefreshInterval", this.f7380g);
        b.put("defaultRefreshInterval", this.f7381h);
        b.put("fetchTimeout", this.f7382i);
        b.put("cache", m9713u());
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("maxRetries", this.f7385l.m9634a());
        jSONObject.put("pingInterval", this.f7385l.m9635b());
        jSONObject.put("pingTimeout", this.f7385l.m9636c());
        jSONObject.put("maxDbEvents", this.f7385l.m9637d());
        jSONObject.put("maxEventBatch", this.f7385l.m9638e());
        jSONObject.put("pingCacheExpiry", this.f7385l.m9639f());
        b.put("imai", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("renderTimeout", this.f7386m.m9675i());
        jSONObject.put("picWidth", this.f7386m.m9667a());
        jSONObject.put("picHeight", this.f7386m.m9668b());
        jSONObject.put("picQuality", this.f7386m.m9669c());
        jSONObject.put("webviewBackground", this.f7386m.f7355e);
        jSONObject.put("maxVibrationDuration", this.f7386m.m9671e());
        jSONObject.put("maxVibrationPatternLength", this.f7386m.m9672f());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxSaveSize", this.f7386m.m9673g());
        jSONObject2.put("allowedContentType", new JSONArray(this.f7386m.m9674h()));
        jSONObject.put("savecontent", jSONObject2);
        b.put("rendering", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("expiry", this.f7387n.m9644a());
        jSONObject.put("maxRetries", this.f7387n.m9645b());
        jSONObject.put("retryInterval", this.f7387n.m9646c());
        jSONObject.put("url", this.f7387n.m9647d());
        b.put(AdType.MRAID, jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("impressionMinPercentageViewed", this.f7388o.m9695a());
        jSONObject.put("impressionMinTimeViewed", this.f7388o.m9697b());
        jSONObject.put("visibilityThrottleMillis", this.f7388o.m9700e());
        jSONObject.put("impressionPollIntervalMillis", this.f7388o.m9701f());
        jSONObject.put("moatEnabled", this.f7388o.m9702g());
        jSONObject.put("iasEnabled", this.f7388o.m9703h());
        jSONObject2 = new JSONObject();
        jSONObject2.put("impressionMinPercentageViewed", this.f7388o.m9698c());
        jSONObject2.put("impressionMinTimeViewed", this.f7388o.m9699d());
        jSONObject.put("video", jSONObject2);
        b.put("viewability", jSONObject);
        jSONObject = new JSONObject();
        jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.f7390q.m9654b());
        jSONObject2.put("placementExpiry", this.f7390q.m9653a());
        jSONObject2.put("maxPreloadedAds", this.f7390q.m9655c());
        jSONObject.put("base", jSONObject2);
        b.put("preload", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("maxWrapperLimit", this.f7391r.m9683a());
        jSONObject.put("optimalVastVideoSize", this.f7391r.m9684b());
        jSONObject.put("vastMaxAssetSize", this.f7391r.m9685c());
        jSONObject.put("allowedContentType", new JSONArray(this.f7391r.m9686d()));
        b.put("vastVideo", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("retryInterval", this.f7392s.m9611b());
        jSONObject.put("maxRetries", this.f7392s.m9610a());
        jSONObject.put("maxCachedAssets", this.f7392s.m9612c());
        jSONObject.put("maxCacheSize", this.f7392s.m9614e());
        jSONObject.put("timeToLive", this.f7392s.m9613d());
        b.put("assetCache", jSONObject);
        if (this.f7389p != null) {
            b.put("telemetry", this.f7389p);
        }
        return b;
    }

    private JSONObject m9713u() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", this.f7383j.m9624b());
        jSONObject2.put("fetchLimit", this.f7383j.m9625c());
        jSONObject2.put("minThreshold", this.f7383j.m9626d());
        jSONObject2.put("timeToLive", this.f7383j.m9627e());
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.f7384k.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            C3038b c3038b = (C3038b) entry.getValue();
            jSONObject3.put("maxCacheSize", c3038b.m9624b());
            jSONObject3.put("fetchLimit", c3038b.m9625c());
            jSONObject3.put("minThreshold", c3038b.m9626d());
            jSONObject3.put("timeToLive", c3038b.m9627e());
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    public boolean mo6234c() {
        if ((!this.f7378e.startsWith("http://") && !this.f7378e.startsWith("https://")) || ((!this.f7379f.startsWith("http://") && !this.f7379f.startsWith("https://")) || this.f7380g < 0 || this.f7381h < 0 || this.f7382i <= 0)) {
            return false;
        }
        if (this.f7383j == null || !this.f7383j.m9623a()) {
            return false;
        }
        for (Entry value : this.f7384k.entrySet()) {
            if (!((C3038b) value.getValue()).m9623a()) {
                return false;
            }
        }
        if (this.f7385l.m9637d() < 0 || this.f7385l.m9638e() < 0 || this.f7385l.m9634a() < 0 || this.f7385l.m9635b() < 0 || this.f7385l.m9636c() <= 0 || this.f7385l.m9639f() <= 0) {
            return false;
        }
        if (this.f7387n.m9644a() < 0 || this.f7387n.m9646c() < 0 || this.f7387n.m9645b() < 0 || (!this.f7387n.m9647d().startsWith("http://") && !this.f7387n.m9647d().startsWith("https://"))) {
            return false;
        }
        if (this.f7386m.m9675i() < 0 || this.f7386m.m9668b() < 0 || this.f7386m.m9667a() < 0 || this.f7386m.m9669c() < 0 || this.f7386m.m9671e() < 0 || this.f7386m.m9672f() < 0 || this.f7386m.m9673g() < 0 || this.f7386m.f7355e == null || this.f7386m.f7355e.trim().length() == 0) {
            return false;
        }
        try {
            this.f7386m.f7356f = Color.parseColor(this.f7386m.f7355e);
            if (this.f7387n.m9645b() < 0 || this.f7387n.m9646c() < 0 || this.f7387n.m9647d() == null || this.f7387n.m9647d().trim().length() == 0) {
                return false;
            }
            if (this.f7388o.m9695a() <= 0 || this.f7388o.m9695a() > 100 || this.f7388o.m9697b() < 0 || this.f7388o.m9698c() <= 0 || this.f7388o.m9698c() > 100 || this.f7388o.m9699d() < 0 || this.f7388o.m9700e() < 50 || this.f7388o.m9700e() * 5 > this.f7388o.m9697b() || this.f7388o.m9701f() < 50 || this.f7388o.m9701f() * 4 > this.f7388o.m9697b()) {
                return false;
            }
            if (this.f7390q.f7349b < 0 || this.f7390q.f7350c <= 0) {
                return false;
            }
            if (this.f7391r.f7362b > 31457280 || this.f7391r.f7362b <= 0 || this.f7391r.f7361a < 0 || this.f7391r.f7363c <= 0 || this.f7391r.f7363c > 31457280) {
                return false;
            }
            if (this.f7392s.m9611b() < 0 || this.f7392s.m9612c() > 20 || this.f7392s.m9612c() < 0 || this.f7392s.m9613d() < 0 || this.f7392s.m9614e() < 0 || this.f7392s.m9610a() < 0) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7374a, "Webview color specified in config is invalid.", e);
            return false;
        }
    }

    public C3045a mo6235d() {
        return new C3046c();
    }

    public String m9720e() {
        return this.f7378e;
    }

    public String m9721f() {
        return this.f7379f;
    }

    public int m9722g() {
        return this.f7380g;
    }

    public int m9723h() {
        return this.f7381h;
    }

    public int m9724i() {
        return this.f7382i;
    }

    public C3038b m9714a(String str) {
        C3038b c3038b = (C3038b) this.f7384k.get(str);
        if (c3038b == null) {
            return this.f7383j;
        }
        return c3038b;
    }

    public C3039c m9725j() {
        return this.f7385l;
    }

    public C3042f m9726k() {
        return this.f7386m;
    }

    public C3040d m9727l() {
        return this.f7387n;
    }

    public C3044h m9728m() {
        return this.f7388o;
    }

    public JSONObject m9729n() {
        return this.f7389p;
    }

    public C3041e m9730o() {
        return this.f7390q;
    }

    public C3043g m9731p() {
        return this.f7391r;
    }

    public C3037a m9732q() {
        return this.f7392s;
    }
}

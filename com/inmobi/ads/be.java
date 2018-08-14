package com.inmobi.ads;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.inmobi.ads.C2968a.C2965a;
import com.inmobi.ads.C3049e.C3030a;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.p111b.C3028a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PrefetchAdStore */
public class be implements C3030a {
    private static final String f7250a = be.class.getSimpleName();
    private boolean f7251b = false;
    private final C2909a f7252c;
    private final C3047d f7253d;
    private C3056i f7254e;
    private long f7255f = 0;

    /* compiled from: PrefetchAdStore */
    public interface C2909a {
        void mo6097a(long j);

        void mo6105a(String str, String str2, Map<String, Object> map);

        void mo6109c(long j, InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    public be(C2909a c2909a) {
        this.f7252c = c2909a;
        this.f7253d = C3047d.m9733a();
    }

    @Nullable
    public String m9510a(C3056i c3056i) throws C3028a {
        this.f7251b = false;
        this.f7254e = c3056i;
        int a = this.f7253d.m9735a(this.f7254e.m9837i(), this.f7254e.m9835g().m9627e());
        if (a > 0) {
            Map hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(a));
            this.f7252c.mo6105a("ads", "AdCacheAdExpired", hashMap);
        }
        List c = this.f7253d.m9741c(this.f7254e.m9830d(), this.f7254e.m9840l(), this.f7254e.m9842n());
        a = c.size();
        if (a == 0) {
            this.f7251b = false;
            if (m9507a(this.f7254e.m9824b())) {
                throw new C3028a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f7250a, "No ad available in cache; fetching an ad from the network");
            return m9505a(this.f7254e, true);
        } else if (a - 1 < this.f7254e.m9835g().m9626d()) {
            this.f7251b = true;
            this.f7252c.mo6097a(this.f7254e.m9830d());
            m9506a((C2968a) c.get(0));
            if (m9507a(this.f7254e.m9824b())) {
                throw new C3028a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f7250a, "Cache occupancy below threshold; fetching an ad from the network");
            return m9505a(this.f7254e, true);
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7250a, "Ad available in cache; signaling ad availability as true");
            this.f7251b = true;
            String b = ((C2968a) c.get(0)).m9122b();
            this.f7252c.mo6097a(this.f7254e.m9830d());
            m9506a((C2968a) c.get(0));
            return b;
        }
    }

    public boolean m9512a() {
        return this.f7251b;
    }

    @NonNull
    private String m9505a(C3056i c3056i, boolean z) {
        m9508b(c3056i, z);
        C3050f c3050f = new C3050f(c3056i.m9816a(), c3056i.m9830d(), c3056i.m9836h());
        c3050f.m9784d(c3056i.m9832e());
        c3050f.mo6240a(c3056i.m9834f());
        c3050f.mo6239a(c3056i.m9837i());
        c3050f.m9779b(c3056i.m9838j());
        c3050f.m9774a(c3056i.m9835g().m9625c());
        c3050f.m9780b(c3056i.m9839k());
        c3050f.m9779b(c3056i.m9838j());
        c3050f.m9782c(c3056i.m9840l());
        c3050f.m9753b(c3056i.m9828c() * 1000);
        c3050f.m9755c(c3056i.m9828c() * 1000);
        c3050f.m9775a(c3056i.m9842n());
        this.f7255f = SystemClock.elapsedRealtime();
        new C3049e(c3050f, this).m9744a();
        Map hashMap = new HashMap();
        hashMap.put("isPreloaded", Boolean.valueOf(true));
        hashMap.put("clientRequestId", c3050f.m9786f());
        this.f7252c.mo6105a("ads", "ServerCallInitiated", hashMap);
        return c3050f.m9786f();
    }

    private void m9508b(C3056i c3056i, boolean z) {
        if (c3056i != null) {
            Map k = c3056i.m9839k();
            if (k == null) {
                k = new HashMap();
            }
            if (!k.containsKey("preload-request")) {
                k.put("preload-request", String.valueOf(z ? 1 : 0));
                c3056i.m9827b(k);
            }
        }
    }

    private boolean m9507a(int i) {
        return SystemClock.elapsedRealtime() - this.f7255f < ((long) (i * 1000));
    }

    public void mo6213a(C3052g c3052g) {
        List c = m9509c(c3052g);
        if (c == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7250a, "Could not parse ad response:" + c3052g.m9790c());
            if (!this.f7251b) {
                this.f7252c.mo6109c(this.f7254e.m9830d(), new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        } else if (c.size() == 0) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7250a, "Ad response received but no ad available:" + c3052g.m9790c());
            Map hashMap = new HashMap();
            hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7255f));
            hashMap.put("isPreloaded", Boolean.valueOf(true));
            this.f7252c.mo6105a("ads", "ServerNoFill", hashMap);
            if (!this.f7251b) {
                this.f7252c.mo6109c(this.f7254e.m9830d(), new InMobiAdRequestStatus(StatusCode.NO_FILL));
            }
        } else {
            Map hashMap2 = new HashMap();
            hashMap2.put("numberOfAdsReturned", Integer.valueOf(c.size()));
            hashMap2.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7255f));
            hashMap2.put("isPreloaded", Boolean.valueOf(true));
            this.f7252c.mo6105a("ads", "ServerFill", hashMap2);
            this.f7253d.m9738a(c, this.f7254e.m9835g().m9624b(), this.f7254e.m9837i());
            m9506a((C2968a) c.get(0));
            if (!this.f7251b) {
                this.f7252c.mo6097a(this.f7254e.m9830d());
            }
        }
    }

    public void mo6214b(C3052g c3052g) {
        if (!this.f7251b) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(c3052g.m9791d().m10332a().getValue()));
            hashMap.put("reason", c3052g.m9791d().m10333b());
            hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7255f));
            hashMap.put("isPreloaded", Boolean.valueOf(true));
            this.f7252c.mo6105a("ads", "ServerError", hashMap);
            this.f7252c.mo6109c(this.f7254e.m9830d(), c3052g.m9788a());
        }
    }

    private List<C2968a> m9509c(C3052g c3052g) {
        List<C2968a> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(c3052g.m9790c());
            String trim = jSONObject.getString("requestId").trim();
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray != null) {
                int min = Math.min(c3052g.m9789b().m9783d(), jSONArray.length());
                for (int i = 0; i < min; i++) {
                    C2968a a = C2965a.m9111a(jSONArray.getJSONObject(i), c3052g.m9789b().m9785e(), c3052g.m9789b().m9778b(), c3052g.m9789b().m9781c(), trim + BridgeUtil.UNDERLINE_STR + i, c3052g.m9789b().m9786f(), this.f7254e.m9842n());
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
                if (min > 0 && arrayList.isEmpty()) {
                    return null;
                }
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7250a, "Error while parsing ad response.", e);
            Map hashMap = new HashMap();
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e.getLocalizedMessage());
            hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7255f));
            this.f7252c.mo6105a("ads", "ServerError", hashMap);
            arrayList = null;
        }
        return arrayList;
    }

    private void m9506a(C2968a c2968a) {
        if (c2968a != null && c2968a.m9128h().equalsIgnoreCase("inmobiJson")) {
            for (String a : c2968a.m9127g()) {
                C3072m.m9903a().m9914a(a, c2968a.m9122b());
            }
        }
    }
}

package com.inmobi.ads;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.inmobi.ads.C2968a.C2965a;
import com.inmobi.ads.C3049e.C3030a;
import com.inmobi.ads.C3072m.C3053b;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.p111b.C3028a;
import com.inmobi.commons.core.utilities.C3154c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AdStore */
class C3055h implements C3030a {
    private static final String f7433a = C3055h.class.getSimpleName();
    @NonNull
    private final C2910a f7434b;
    @NonNull
    private final C3047d f7435c;
    @Nullable
    private C3056i f7436d;
    private boolean f7437e = false;
    private long f7438f = 0;
    private final C3053b f7439g = new C30541(this);

    /* compiled from: AdStore */
    public interface C2910a {
        void mo6098a(long j, InMobiAdRequestStatus inMobiAdRequestStatus);

        void mo6099a(long j, C2968a c2968a);

        void mo6100a(long j, boolean z);

        void mo6101a(long j, boolean z, C2968a c2968a, long j2);

        void mo6105a(String str, String str2, Map<String, Object> map);
    }

    /* compiled from: AdStore */
    class C30541 implements C3053b {
        final /* synthetic */ C3055h f7432a;

        C30541(C3055h c3055h) {
            this.f7432a = c3055h;
        }

        public void mo6241a(C3029b c3029b, String str) {
            C3072m.m9903a().m9916b(c3029b.m9467b(), this.f7432a.f7439g);
            Map hashMap = new HashMap();
            hashMap.put("url", c3029b.m9467b());
            hashMap.put("latency", Long.valueOf(c3029b.m9470e()));
            hashMap.put("size", Long.valueOf(C3154c.m10399a(c3029b.m9465a())));
            this.f7432a.f7434b.mo6105a("ads", "VideoAssetDownloadFailed", hashMap);
            List<Long> arrayList = new ArrayList();
            for (C2968a c2968a : this.f7432a.f7435c.m9740b(c3029b.m9467b(), this.f7432a.f7436d == null ? null : this.f7432a.f7436d.m9840l())) {
                if (!arrayList.contains(Long.valueOf(c2968a.m9124d()))) {
                    arrayList.add(Long.valueOf(c2968a.m9124d()));
                }
            }
            if (!arrayList.contains(Long.valueOf(this.f7432a.f7436d.m9830d()))) {
                arrayList.add(Long.valueOf(this.f7432a.f7436d.m9830d()));
            }
            for (Long longValue : arrayList) {
                this.f7432a.f7434b.mo6100a(longValue.longValue(), false);
            }
        }

        public void mo6242a(C3029b c3029b, boolean z, String str) {
            C3072m.m9903a().m9916b(c3029b.m9467b(), this.f7432a.f7439g);
            Map hashMap = new HashMap();
            hashMap.put("url", c3029b.m9467b());
            hashMap.put("latency", Long.valueOf(c3029b.m9470e()));
            hashMap.put("size", Long.valueOf(C3154c.m10399a(c3029b.m9465a())));
            hashMap.put("clientRequestId", str);
            if (z) {
                this.f7432a.f7434b.mo6105a("ads", "GotCachedVideoAsset", hashMap);
            } else {
                this.f7432a.f7434b.mo6105a("ads", "VideoAssetDownloaded", hashMap);
            }
            List<Long> arrayList = new ArrayList();
            List<C2968a> a = this.f7432a.f7435c.m9736a(c3029b.m9467b(), this.f7432a.f7436d == null ? null : this.f7432a.f7436d.m9840l());
            Logger.m10359a(InternalLogLevel.INTERNAL, C3055h.f7433a, "Found " + a.size() + " ads mapping to this asset");
            for (C2968a c2968a : a) {
                if (!arrayList.contains(Long.valueOf(c2968a.m9124d()))) {
                    arrayList.add(Long.valueOf(c2968a.m9124d()));
                }
            }
            if (!arrayList.contains(Long.valueOf(this.f7432a.f7436d.m9830d()))) {
                arrayList.add(Long.valueOf(this.f7432a.f7436d.m9830d()));
            }
            for (Long longValue : arrayList) {
                long longValue2 = longValue.longValue();
                Logger.m10359a(InternalLogLevel.INTERNAL, C3055h.f7433a, "Notifying ad unit with placement ID (" + longValue2 + ")");
                this.f7432a.f7434b.mo6100a(longValue2, true);
            }
        }
    }

    public C3055h(C2910a c2910a) {
        this.f7434b = c2910a;
        this.f7435c = C3047d.m9733a();
    }

    private void m9804c() {
        this.f7436d = null;
    }

    public boolean m9812a() {
        return this.f7437e;
    }

    @Nullable
    public String m9808a(C3056i c3056i) throws C3028a {
        m9804c();
        this.f7437e = false;
        this.f7436d = c3056i;
        if (!SchemaSymbols.ATTVAL_INT.equals(this.f7436d.m9837i())) {
            return m9809a(false);
        }
        int a = this.f7435c.m9735a(this.f7436d.m9837i(), this.f7436d.m9835g().m9627e());
        if (a > 0) {
            Map hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(a));
            this.f7434b.mo6105a("ads", "AdCacheAdExpired", hashMap);
        }
        List c = this.f7435c.m9741c(this.f7436d.m9830d(), this.f7436d.m9840l(), this.f7436d.m9842n());
        a = c.size();
        if (a == 0) {
            this.f7437e = false;
            if (m9798a(this.f7436d.m9824b())) {
                throw new C3028a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "No ad available in cache; fetching an ad from the network");
            return m9801b(this.f7436d, false);
        } else if (a - 1 < this.f7436d.m9835g().m9626d()) {
            this.f7437e = true;
            if (!"INMOBIJSON".equalsIgnoreCase(((C2968a) c.get(0)).m9128h())) {
                return m9809a(true);
            }
            this.f7434b.mo6101a(this.f7436d.m9830d(), true, (C2968a) c.get(0), ((C2968a) c.get(0)).m9126f());
            m9802b((C2968a) c.get(0));
            if (m9798a(this.f7436d.m9824b())) {
                throw new C3028a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "Cache occupancy below threshold; fetching an ad from the network");
            return m9801b(this.f7436d, true);
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "Ad available in cache; signaling ad availability as true");
            this.f7437e = true;
            String b = ((C2968a) c.get(0)).m9122b();
            if (!"INMOBIJSON".equalsIgnoreCase(((C2968a) c.get(0)).m9128h())) {
                return m9809a(false);
            }
            this.f7434b.mo6101a(this.f7436d.m9830d(), true, (C2968a) c.get(0), ((C2968a) c.get(0)).m9126f());
            m9802b((C2968a) c.get(0));
            return b;
        }
    }

    String m9809a(boolean z) {
        C2968a a = m9806a(this.f7436d.m9830d(), this.f7436d.m9837i(), this.f7436d.m9840l(), this.f7436d.m9835g().m9626d(), this.f7436d.m9835g().m9627e(), this.f7436d.m9842n());
        if (a == null) {
            return m9801b(this.f7436d, z);
        }
        String b = a.m9122b();
        this.f7434b.mo6099a(this.f7436d.m9830d(), a);
        if (!"INMOBIJSON".equalsIgnoreCase(a.m9128h())) {
            return b;
        }
        m9802b(a);
        return b;
    }

    private void m9797a(C3056i c3056i, boolean z) {
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

    private boolean m9798a(int i) {
        return SystemClock.elapsedRealtime() - this.f7438f < ((long) (i * 1000));
    }

    @Nullable
    C2968a m9806a(long j, String str, String str2, int i, long j2, MonetizationContext monetizationContext) {
        int a = this.f7435c.m9735a(str, j2);
        if (a > 0) {
            Map hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(a));
            this.f7434b.mo6105a("ads", "AdCacheAdExpired", hashMap);
        }
        int a2 = this.f7435c.m9734a(j, str2, monetizationContext);
        if (a2 == 0) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "No stored ads!");
            return null;
        }
        C2968a b = this.f7435c.m9739b(j, str2, monetizationContext);
        if (b == null) {
            return null;
        }
        this.f7437e = true;
        int i2 = a2 - 1;
        hashMap = new HashMap();
        hashMap.put("clientRequestId", b.m9122b());
        this.f7434b.mo6105a("ads", "AdCacheHit", hashMap);
        if (i2 < i && this.f7436d != null) {
            m9801b(this.f7436d, true);
        }
        return b;
    }

    @Nullable
    C2968a m9807a(long j, String str, String str2, long j2, MonetizationContext monetizationContext) {
        int a = this.f7435c.m9735a(str, j2);
        if (a > 0) {
            Map hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(a));
            this.f7434b.mo6105a("ads", "AdCacheAdExpired", hashMap);
        }
        List c = this.f7435c.m9741c(j, str2, monetizationContext);
        if (c == null || c.size() <= 0) {
            return null;
        }
        return (C2968a) c.get(0);
    }

    boolean m9813a(long j, @Nullable String str, MonetizationContext monetizationContext) {
        return this.f7435c.m9734a(j, str, monetizationContext) != 0;
    }

    void m9810a(C2968a c2968a) {
        this.f7435c.m9737a(c2968a);
    }

    @NonNull
    private String m9801b(C3056i c3056i, boolean z) {
        m9797a(c3056i, z);
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
        this.f7438f = SystemClock.elapsedRealtime();
        new C3049e(c3050f, this).m9744a();
        Map hashMap = new HashMap();
        hashMap.put("isPreloaded", c3056i.m9841m());
        hashMap.put("clientRequestId", c3050f.m9786f());
        this.f7434b.mo6105a("ads", "ServerCallInitiated", hashMap);
        return c3050f.m9786f();
    }

    public void mo6213a(C3052g c3052g) {
        int i = 1;
        List c = m9815c(c3052g);
        if (c == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "Could not parse ad response:" + c3052g.m9790c());
            if (!this.f7437e) {
                this.f7434b.mo6098a(this.f7436d.m9830d(), new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        } else if (c.size() == 0) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "Ad response received but no ad available:" + c3052g.m9790c());
            r0 = new HashMap();
            r0.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7438f));
            this.f7434b.mo6105a("ads", "ServerNoFill", r0);
            if (!this.f7437e) {
                this.f7434b.mo6098a(this.f7436d.m9830d(), new InMobiAdRequestStatus(StatusCode.NO_FILL));
            }
        } else {
            r0 = new HashMap();
            r0.put("numberOfAdsReturned", Integer.valueOf(c.size()));
            r0.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7438f));
            this.f7434b.mo6105a("ads", "ServerFill", r0);
            String h = ((C2968a) c.get(0)).m9128h();
            C2968a c2968a = (C2968a) c.get(0);
            if (h != null) {
                String toUpperCase = h.toUpperCase(Locale.ENGLISH);
                int i2 = -1;
                switch (toUpperCase.hashCode()) {
                    case -598127114:
                        if (toUpperCase.equals("INMOBIJSON")) {
                            i2 = 2;
                            break;
                        }
                        break;
                    case 2228139:
                        if (toUpperCase.equals("HTML")) {
                            i2 = 0;
                            break;
                        }
                        break;
                    case 482567493:
                        if (toUpperCase.equals("PUBJSON")) {
                            i2 = 1;
                            break;
                        }
                        break;
                }
                switch (i2) {
                    case 0:
                    case 1:
                        if (this.f7437e) {
                            i = 0;
                        }
                        this.f7435c.m9738a(c.subList(i, c.size()), this.f7436d.m9835g().m9624b(), this.f7436d.m9837i());
                        if (!this.f7437e) {
                            this.f7434b.mo6099a(this.f7436d.m9830d(), c2968a);
                            return;
                        }
                        return;
                    case 2:
                        if (SchemaSymbols.ATTVAL_INT.equals(this.f7436d.m9837i())) {
                            this.f7435c.m9738a(c.subList(0, c.size()), this.f7436d.m9835g().m9624b(), this.f7436d.m9837i());
                            if (!this.f7437e) {
                                this.f7434b.mo6101a(this.f7436d.m9830d(), true, c2968a, c2968a.m9126f());
                            }
                        } else if ("inlban".equals(this.f7436d.m9837i())) {
                            this.f7435c.m9738a(c, this.f7436d.m9835g().m9624b(), this.f7436d.m9837i());
                            if (!this.f7437e) {
                                C2968a b = this.f7435c.m9739b(this.f7436d.m9830d(), this.f7436d.m9840l(), this.f7436d.m9842n());
                                if (b != null) {
                                    c2968a = b;
                                }
                                this.f7434b.mo6099a(this.f7436d.m9830d(), c2968a);
                            }
                        }
                        m9802b(c2968a);
                        return;
                    default:
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7433a, "Unknown markup type = " + h);
                        return;
                }
            }
        }
    }

    private void m9802b(C2968a c2968a) {
        if (c2968a != null && c2968a.m9128h().equalsIgnoreCase("inmobiJson")) {
            boolean z = false;
            for (String str : c2968a.m9127g()) {
                if (!z) {
                    C3072m.m9903a().m9913a(str, this.f7439g);
                    z = true;
                }
                C3072m.m9903a().m9914a(str, c2968a.m9122b());
            }
            if (!z) {
                this.f7434b.mo6100a(this.f7436d.m9830d(), true);
            }
        }
    }

    protected List<C2968a> m9815c(C3052g c3052g) {
        List<C2968a> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(c3052g.m9790c());
            String trim = jSONObject.getString("requestId").trim();
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray != null) {
                int min = Math.min(c3052g.m9789b().m9783d(), jSONArray.length());
                for (int i = 0; i < min; i++) {
                    C2968a a = C2965a.m9111a(jSONArray.getJSONObject(i), c3052g.m9789b().m9785e(), c3052g.m9789b().m9778b(), c3052g.m9789b().m9781c(), trim + BridgeUtil.UNDERLINE_STR + i, c3052g.m9789b().m9786f(), this.f7436d.m9842n());
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
                if (min > 0 && arrayList.isEmpty()) {
                    return null;
                }
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7433a, "Error while parsing ad response.", e);
            Map hashMap = new HashMap();
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e.getLocalizedMessage());
            hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7438f));
            this.f7434b.mo6105a("ads", "ServerError", hashMap);
            arrayList = null;
        }
        return arrayList;
    }

    public void mo6214b(C3052g c3052g) {
        if (!this.f7437e) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(c3052g.m9791d().m10332a().getValue()));
            hashMap.put("reason", c3052g.m9791d().m10333b());
            hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7438f));
            this.f7434b.mo6105a("ads", "ServerError", hashMap);
            this.f7434b.mo6098a(this.f7436d.m9830d(), c3052g.m9788a());
        }
    }
}

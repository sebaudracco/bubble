package com.inmobi.signals;

import android.os.SystemClock;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3160b;
import com.inmobi.commons.core.utilities.info.C3164f;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: SessionManager */
public class C3276n {
    private static final String f8243a = C3276n.class.getSimpleName();
    private static C3276n f8244b;
    private static Object f8245c = new Object();
    private long f8246d = 0;
    private long f8247e = 0;
    private long f8248f = 0;
    private long f8249g = 0;
    private long f8250h = 0;
    private long f8251i = 0;

    public static C3276n m10977a() {
        C3276n c3276n = f8244b;
        if (c3276n == null) {
            synchronized (f8245c) {
                c3276n = f8244b;
                if (c3276n == null) {
                    f8244b = new C3276n();
                    c3276n = f8244b;
                }
            }
        }
        return c3276n;
    }

    private C3276n() {
    }

    void m10980b() {
        if (C3277o.m10989a().m10994e().m11069i()) {
            String uuid = UUID.randomUUID().toString();
            C3164f.m10487a().m10490a(uuid);
            C3164f.m10487a().m10489a(System.currentTimeMillis());
            C3164f.m10487a().m10493b(0);
            Logger.m10359a(InternalLogLevel.INTERNAL, f8243a, "Session tracking started.");
            this.f8251i = SystemClock.elapsedRealtime();
            this.f8246d = 0;
            this.f8247e = 0;
            this.f8248f = 0;
            this.f8249g = 0;
            this.f8250h = 0;
            this.f8251i = 0;
            Map hashMap = new HashMap();
            hashMap.put("sessionId", uuid);
            try {
                C3135c.m10255a().m10280a("signals", "SDKSessionStarted", hashMap);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8243a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
            }
        }
    }

    void m10982c() {
        if (C3277o.m10989a().m10994e().m11069i()) {
            C3164f.m10487a().m10493b(System.currentTimeMillis());
            Logger.m10359a(InternalLogLevel.INTERNAL, f8243a, "Session tracking stopped.");
            try {
                Map hashMap = new HashMap();
                hashMap.put("sessionId", C3164f.m10487a().m10494c());
                hashMap.put("totalNetworkTime", Long.valueOf(this.f8250h));
                hashMap.put("sessionDuration", Long.valueOf(SystemClock.elapsedRealtime() - this.f8251i));
                C3135c.m10255a().m10280a("signals", "SDKSessionEnded", hashMap);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8243a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
            }
        }
    }

    C3164f m10984d() {
        if (C3277o.m10989a().m10994e().m11069i()) {
            return C3164f.m10487a();
        }
        return null;
    }

    public void m10979a(long j) {
        switch (C3160b.m10434b()) {
            case 0:
                m10986e(j);
                return;
            case 1:
                m10983c(j);
                return;
            default:
                return;
        }
    }

    public void m10981b(long j) {
        switch (C3160b.m10434b()) {
            case 0:
                m10987f(j);
                return;
            case 1:
                m10985d(j);
                return;
            default:
                return;
        }
    }

    public void m10983c(long j) {
        this.f8246d += j;
    }

    public void m10985d(long j) {
        this.f8247e += j;
    }

    public void m10986e(long j) {
        this.f8248f += j;
    }

    public void m10987f(long j) {
        this.f8249g += j;
    }

    public void m10988g(long j) {
        this.f8250h += j;
    }

    public Map<String, Object> m10978a(Map<String, Object> map) {
        map.put("totalWifiSentBytes", Long.valueOf(this.f8246d));
        map.put("totalWifiReceivedBytes", Long.valueOf(this.f8247e));
        map.put("totalCarrierSentBytes", Long.valueOf(this.f8248f));
        map.put("totalCarrierReceivedBytes", Long.valueOf(this.f8249g));
        map.put("totalNetworkTime", Long.valueOf(this.f8250h));
        return map;
    }
}

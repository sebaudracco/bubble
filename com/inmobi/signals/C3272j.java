package com.inmobi.signals;

import android.os.SystemClock;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3164f;
import java.util.HashMap;
import java.util.Map;

/* compiled from: IceNetworkClient */
public class C3272j {
    private static final String f8226a = C3272j.class.getSimpleName();
    private C3273k f8227b;

    /* compiled from: IceNetworkClient */
    class C32711 implements Runnable {
        final /* synthetic */ C3272j f8225a;

        C32711(C3272j c3272j) {
            this.f8225a = c3272j;
        }

        public void run() {
            int i = 0;
            while (i <= this.f8225a.f8227b.m10963b()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3272j.f8226a, "Attempting to send samples to server.");
                long elapsedRealtime = SystemClock.elapsedRealtime();
                C3143c a = new C3144d(this.f8225a.f8227b).m10357a();
                try {
                    C3276n.m10977a().m10979a(this.f8225a.f8227b.m9772t());
                    C3276n.m10977a().m10981b(a.m10356f());
                    C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - elapsedRealtime);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3272j.f8226a, "Error in setting request-response data size. " + e.getMessage());
                }
                if (a.m10351a()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3272j.f8226a, "Sending samples to server failed.");
                    i++;
                    if (i > this.f8225a.f8227b.m10963b()) {
                        try {
                            C3135c.m10255a().m10279a(new C3110g("signals", "RetryCountExceeded"));
                            return;
                        } catch (Exception e2) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C3272j.f8226a, "Error in submitting telemetry event : (" + e2.getMessage() + ")");
                            return;
                        }
                    }
                    try {
                        Thread.sleep((long) (this.f8225a.f8227b.m10964c() * 1000));
                    } catch (Throwable e3) {
                        Logger.m10360a(InternalLogLevel.INTERNAL, C3272j.f8226a, "User data network client interrupted while sleeping.", e3);
                    }
                } else {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3272j.f8226a, "Sending samples to server succeeded.");
                    try {
                        Map hashMap = new HashMap();
                        hashMap.put("url", this.f8225a.f8227b.m9761i());
                        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - 0));
                        hashMap.put("payloadSize", Long.valueOf(this.f8225a.f8227b.m9772t() + a.m10356f()));
                        C3135c.m10255a().m10280a("signals", "NICElatency", hashMap);
                        hashMap = new HashMap();
                        hashMap.put("sessionId", C3164f.m10487a().m10494c());
                        C3276n.m10977a().m10978a(hashMap);
                        C3135c.m10255a().m10280a("signals", "SDKNetworkStats", hashMap);
                        return;
                    } catch (Exception e22) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3272j.f8226a, "Error in submitting telemetry event : (" + e22.getMessage() + ")");
                        return;
                    }
                }
            }
        }
    }

    public C3272j(C3273k c3273k) {
        this.f8227b = c3273k;
    }

    public void m10962a() {
        new Thread(new C32711(this)).start();
    }
}

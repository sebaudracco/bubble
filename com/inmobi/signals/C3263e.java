package com.inmobi.signals;

import android.os.SystemClock;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: CarbNetworkClient */
public class C3263e {
    private static final String f8207a = C3263e.class.getSimpleName();

    public C3261c m10924a(C3260b c3260b) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        C3143c a = new C3144d(c3260b).m10357a();
        C3261c c3261c = new C3261c(a);
        try {
            C3276n.m10977a().m10979a(c3260b.m9772t());
            C3276n.m10977a().m10981b(a.m10356f());
            C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - elapsedRealtime);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Error in setting request-response data size. " + e.getMessage());
        }
        if (c3261c.m10917a()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Getting Carb list from server failed.");
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Getting Carb list from server succeeded.");
        }
        return c3261c;
    }

    public boolean m10925a(C3264f c3264f) {
        boolean z = false;
        int i = 0;
        while (i <= c3264f.m10927b()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Attempting to send pruned Carb list to server.");
            long elapsedRealtime = SystemClock.elapsedRealtime();
            C3143c a = new C3144d(c3264f).m10357a();
            try {
                C3276n.m10977a().m10979a(c3264f.m9772t());
                C3276n.m10977a().m10981b(a.m10356f());
                C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - elapsedRealtime);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Error in setting request-response data size. " + e.getMessage());
            }
            if (!a.m10351a()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Sending pruned Carb list to server succeeded.");
                z = true;
                break;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Sending pruned Carb list to server failed.");
            i++;
            if (i > c3264f.m10927b()) {
                break;
            }
            try {
                Thread.sleep((long) (c3264f.m10928c() * 1000));
            } catch (Throwable e2) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8207a, "User data network client interrupted while sleeping.", e2);
            }
        }
        if (!z) {
            try {
                C3135c.m10255a().m10279a(new C3110g("signals", "CarbUploadDiscarded"));
            } catch (Exception e3) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8207a, "Error in submitting telemetry event : (" + e3.getMessage() + ")");
            }
        }
        return z;
    }
}

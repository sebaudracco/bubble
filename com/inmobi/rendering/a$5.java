package com.inmobi.rendering;

import android.os.SystemClock;
import com.inmobi.commons.core.network.C3141a.C3048a;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.C3276n;

/* compiled from: JavaScriptBridge */
class a$5 implements C3048a {
    final /* synthetic */ NetworkRequest f7982a;
    final /* synthetic */ long f7983b;
    final /* synthetic */ a f7984c;

    a$5(a aVar, NetworkRequest networkRequest, long j) {
        this.f7984c = aVar;
        this.f7982a = networkRequest;
        this.f7983b = j;
    }

    public void mo6236a(C3143c c3143c) {
        Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "asyncPing Successful");
        try {
            C3276n.m10977a().m10979a(this.f7982a.m9772t());
            C3276n.m10977a().m10981b(c3143c.m10356f());
            C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - this.f7983b);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "Error in setting request-response data size. " + e.getMessage());
        }
    }

    public void mo6237b(C3143c c3143c) {
        Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "asyncPing Failed");
    }
}

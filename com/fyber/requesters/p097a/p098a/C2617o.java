package com.fyber.requesters.p097a.p098a;

import com.fyber.mediation.p108b.C2580a;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.FyberLogger;

/* compiled from: RequestTimeoutValidator */
public final class C2617o implements C2596p<C2580a> {
    public final /* synthetic */ boolean mo3996a(C2579k c2579k, C2579k c2579k2) {
        if (System.currentTimeMillis() - ((Long) ((C2580a) c2579k).mo3971a("requested_at", Long.class, Long.valueOf(-1))).longValue() < 60000) {
            FyberLogger.m8448d("RequestTimeoutValidator", "There is a request currently ongoing. Not forwarding the new one.");
            return false;
        }
        FyberLogger.m8448d("RequestTimeoutValidator", "The old request has expired. Forwarding the new one...");
        return true;
    }
}

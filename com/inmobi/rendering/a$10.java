package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$10 implements Runnable {
    final /* synthetic */ String f7949a;
    final /* synthetic */ String f7950b;
    final /* synthetic */ a f7951c;

    a$10(a aVar, String str, String str2) {
        this.f7951c = aVar;
        this.f7949a = str;
        this.f7950b = str2;
    }

    public void run() {
        try {
            a.a(this.f7951c).m10642c(this.f7949a, this.f7950b);
        } catch (Exception e) {
            a.a(this.f7951c).m10631a(this.f7949a, "Unexpected error", "expand");
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to expand ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling expand() request; " + e.getMessage());
        }
    }
}

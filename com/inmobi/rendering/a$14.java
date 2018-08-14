package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$14 implements Runnable {
    final /* synthetic */ String f7960a;
    final /* synthetic */ a f7961b;

    a$14(a aVar, String str) {
        this.f7961b = aVar;
        this.f7960a = str;
    }

    public void run() {
        try {
            a.a(this.f7961b).mo6175b();
        } catch (Exception e) {
            a.a(this.f7961b).m10631a(this.f7960a, "Unexpected error", "close");
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to close ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered an expected error in handling the close() request from creative; " + e.getMessage());
        }
    }
}

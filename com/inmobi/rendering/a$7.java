package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$7 implements Runnable {
    final /* synthetic */ String f7988a;
    final /* synthetic */ String f7989b;
    final /* synthetic */ a f7990c;

    a$7(a aVar, String str, String str2) {
        this.f7990c = aVar;
        this.f7988a = str;
        this.f7989b = str2;
    }

    public void run() {
        try {
            a.a(this.f7990c).m10639b("openEmbedded", this.f7988a, this.f7989b);
        } catch (Exception e) {
            a.a(this.f7990c).m10631a(this.f7988a, "Unexpected error", "openEmbedded");
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling openEmbedded() request from creative; " + e.getMessage());
        }
    }
}

package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$1 implements Runnable {
    final /* synthetic */ String f7962a;
    final /* synthetic */ String f7963b;
    final /* synthetic */ a f7964c;

    a$1(a aVar, String str, String str2) {
        this.f7964c = aVar;
        this.f7962a = str;
        this.f7963b = str2;
    }

    public void run() {
        try {
            a.a(this.f7964c).m10639b("open", this.f7962a, this.f7963b);
        } catch (Exception e) {
            a.a(this.f7964c).m10631a(this.f7962a, "Unexpected error", "open");
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling open() request from creative; " + e.getMessage());
        }
    }
}

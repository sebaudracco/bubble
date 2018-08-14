package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$11 implements Runnable {
    final /* synthetic */ String f7952a;
    final /* synthetic */ a f7953b;

    a$11(a aVar, String str) {
        this.f7953b = aVar;
        this.f7952a = str;
    }

    public void run() {
        try {
            a.a(this.f7953b).m10646d(this.f7952a);
        } catch (Exception e) {
            a.a(this.f7953b).m10631a(this.f7952a, "Unexpected error", "resize");
            Logger.m10359a(InternalLogLevel.ERROR, a.a(), "Could not resize ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered an unexpected error in handling resize() request; " + e.getMessage());
        }
    }
}

package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$9 implements Runnable {
    final /* synthetic */ a f7992a;

    a$9(a aVar) {
        this.f7992a = aVar;
    }

    public void run() {
        try {
            a.a(this.f7992a).setCurrentPosition();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in getting/setting current position; " + e.getMessage());
        }
    }
}

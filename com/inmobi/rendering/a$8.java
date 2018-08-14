package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$8 implements Runnable {
    final /* synthetic */ a f7991a;

    a$8(a aVar) {
        this.f7991a = aVar;
    }

    public void run() {
        try {
            a.a(this.f7991a).setDefaultPosition();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in getting/setting default position; " + e.getMessage());
        }
    }
}

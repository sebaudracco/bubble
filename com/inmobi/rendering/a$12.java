package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$12 implements Runnable {
    final /* synthetic */ boolean f7954a;
    final /* synthetic */ String f7955b;
    final /* synthetic */ a f7956c;

    a$12(a aVar, boolean z, String str) {
        this.f7956c = aVar;
        this.f7954a = z;
        this.f7955b = str;
    }

    public void run() {
        try {
            a.a(this.f7956c).m10644c(this.f7954a);
        } catch (Exception e) {
            a.a(this.f7956c).m10631a(this.f7955b, "Unexpected error", "useCustomClose");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered internal error in handling useCustomClose() request from creative; " + e.getMessage());
        }
    }
}

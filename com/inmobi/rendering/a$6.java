package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$6 implements Runnable {
    final /* synthetic */ boolean f7985a;
    final /* synthetic */ String f7986b;
    final /* synthetic */ a f7987c;

    a$6(a aVar, boolean z, String str) {
        this.f7987c = aVar;
        this.f7985a = z;
        this.f7986b = str;
    }

    public void run() {
        try {
            a.a(this.f7987c).m10640b(this.f7985a);
        } catch (Exception e) {
            a.a(this.f7987c).m10631a(this.f7986b, "Unexpected error", "disableCloseRegion");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling disableCloseRegion() request from creative; " + e.getMessage());
        }
    }
}

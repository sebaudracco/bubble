package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$13 implements Runnable {
    final /* synthetic */ String f7957a;
    final /* synthetic */ String f7958b;
    final /* synthetic */ a f7959c;

    a$13(a aVar, String str, String str2) {
        this.f7959c = aVar;
        this.f7957a = str;
        this.f7958b = str2;
    }

    public void run() {
        try {
            a.a(this.f7959c).m10638b(this.f7957a, this.f7958b.trim());
        } catch (Exception e) {
            a.a(this.f7959c).m10631a(this.f7957a, "Unexpected error", "playVideo");
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Error playing video; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling playVideo() request from creative; " + e.getMessage());
        }
    }
}

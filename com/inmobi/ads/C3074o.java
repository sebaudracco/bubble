package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;

/* compiled from: BannerRefreshHandler */
final class C3074o extends Handler {
    private InMobiBanner f7512a;

    public C3074o(InMobiBanner inMobiBanner) {
        this.f7512a = inMobiBanner;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.f7512a.load(true);
                return;
            default:
                return;
        }
    }
}

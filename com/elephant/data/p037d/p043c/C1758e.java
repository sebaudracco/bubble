package com.elephant.data.p037d.p043c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class C1758e extends BroadcastReceiver {
    private /* synthetic */ C1757d f3650a;

    C1758e(C1757d c1757d) {
        this.f3650a = c1757d;
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                this.f3650a.m5068b();
            } else if ("android.intent.action.USER_PRESENT".equals(action) || "android.intent.action.SCREEN_ON".equals(action)) {
                this.f3650a.m5067a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.elephant.data.p046f.p047a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class C1799f extends BroadcastReceiver {
    private /* synthetic */ C1794b f3786a;

    C1799f(C1794b c1794b) {
        this.f3786a = c1794b;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.TIME_TICK".equals(intent.getAction())) {
            this.f3786a.mo3540a();
        }
    }
}

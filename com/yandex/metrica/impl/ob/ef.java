package com.yandex.metrica.impl.ob;

import android.content.Context;

public class ef extends dy {
    private static final Object f12322a = new Object();
    private static volatile ef f12323b;
    private dy f12324c;

    public static ef m15896a(Context context) {
        if (f12323b == null) {
            synchronized (f12322a) {
                if (f12323b == null) {
                    f12323b = new ef(context.getApplicationContext());
                }
            }
        }
        return f12323b;
    }

    ef(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            this.f12324c = new eb(context);
        } else {
            this.f12324c = new ec();
        }
    }

    public synchronized void mo7084a() {
        this.f12324c.mo7084a();
    }

    public synchronized void mo7087b() {
        this.f12324c.mo7087b();
    }

    public synchronized void mo7086a(eh ehVar) {
        this.f12324c.mo7086a(ehVar);
    }

    public synchronized void mo7085a(ea eaVar) {
        this.f12324c.mo7085a(eaVar);
    }
}

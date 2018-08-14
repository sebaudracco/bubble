package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dr {
    private static final Object f12257a = new Object();
    private static volatile dr f12258b;
    private dp f12259c;

    public static dr m15783a(Context context) {
        if (f12258b == null) {
            synchronized (f12257a) {
                if (f12258b == null) {
                    f12258b = new dr(context);
                }
            }
        }
        return f12258b;
    }

    private dr(Context context) {
        this.f12259c = new dp(context);
    }

    public void m15784a() {
        this.f12259c.m15772b();
    }

    public void m15785b() {
        this.f12259c.m15770a();
    }
}

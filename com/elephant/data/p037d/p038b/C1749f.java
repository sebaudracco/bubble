package com.elephant.data.p037d.p038b;

import android.os.Message;
import java.util.TimerTask;

final class C1749f extends TimerTask {
    private boolean f3625a;
    private String f3626b;
    private /* synthetic */ C1748e f3627c;

    public C1749f(C1748e c1748e, String str) {
        this.f3627c = c1748e;
        this.f3626b = str;
    }

    public final void run() {
        long currentTimeMillis = System.currentTimeMillis();
        if (C1748e.f3613n != 0 && currentTimeMillis - C1748e.f3613n >= 5000 && this.f3627c.f3617f.containsKey(this.f3626b)) {
            Message obtain = Message.obtain();
            obtain.obj = this.f3626b;
            obtain.what = 273;
            this.f3627c.f3624m.sendMessage(obtain);
            this.f3627c.m5041c();
        }
    }
}

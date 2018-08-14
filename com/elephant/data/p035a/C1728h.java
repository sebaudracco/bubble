package com.elephant.data.p035a;

import android.content.Context;
import com.elephant.data.p044e.p045a.C1780d;
import com.elephant.data.p044e.p045a.C1781e;
import com.elephant.data.p048g.C1814b;

final class C1728h implements Runnable {
    private /* synthetic */ Context f3545a;
    private /* synthetic */ String f3546b;

    C1728h(C1727g c1727g, Context context, String str) {
        this.f3545a = context;
        this.f3546b = str;
    }

    public final void run() {
        if (C1727g.f3544d) {
            C1727g.f3544d = false;
            C1781e.m5131a(this.f3545a.getApplicationContext(), "", C1780d.UNINSTALLAPP.m5130a(), C1814b.km, this.f3546b);
        }
    }
}

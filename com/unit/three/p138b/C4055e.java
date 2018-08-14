package com.unit.three.p138b;

import android.content.Context;
import com.unit.three.p140d.C4083a;
import com.unit.three.p141c.C4078f;

final class C4055e implements Runnable {
    private /* synthetic */ Context f9369a;
    private /* synthetic */ C4053c f9370b;

    C4055e(C4053c c4053c, Context context) {
        this.f9370b = c4053c;
        this.f9369a = context;
    }

    public final void run() {
        try {
            C4053c.m12503a().m12516c(this.f9369a);
            String h = C4078f.m12572h();
            int i = C4078f.m12573i();
            if (C4053c.m12506a(this.f9370b, h, i)) {
                long c = C4053c.m12510c();
                C4083a.m12599a(this.f9369a);
                Thread.sleep(c + C4083a.m12597a().m12583g());
                C4053c.m12509b(this.f9370b, h, i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

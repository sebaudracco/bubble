package com.elephant.data.p037d.p038b;

import android.os.Handler;
import android.os.Message;
import java.util.Iterator;

final class C1750g extends Handler {
    private Object f3628a = new Object();
    private /* synthetic */ C1748e f3629b;

    C1750g(C1748e c1748e) {
        this.f3629b = c1748e;
    }

    public final void handleMessage(Message message) {
        if (message.what == 273) {
            synchronized (this.f3628a) {
                if (this.f3629b.f3618g != null && this.f3629b.f3618g.size() > 0) {
                    Iterator it = this.f3629b.f3618g.iterator();
                    String str = (String) message.obj;
                    while (it.hasNext()) {
                        C1725i c1725i = (C1725i) it.next();
                        if (this.f3629b.f3617f.get(str) != null) {
                            this.f3629b.f3617f.get(str);
                        }
                        c1725i.mo3533a((C1744a) this.f3629b.f3617f.get(str));
                        this.f3629b.f3617f.remove(str);
                        this.f3629b.f3615d.remove(str);
                        this.f3629b.f3616e.clear();
                    }
                }
            }
        }
    }
}

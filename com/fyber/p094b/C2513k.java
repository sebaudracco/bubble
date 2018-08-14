package com.fyber.p094b;

import android.support.annotation.NonNull;
import com.fyber.reporters.p109a.C2582c;
import com.fyber.utils.C2657h;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;

/* compiled from: ReporterOperation */
public final class C2513k implements Runnable {
    private C2672t f6240a;
    private C2582c f6241b;

    public C2513k(@NonNull C2672t c2672t, C2582c c2582c) {
        this.f6240a = c2672t;
        this.f6241b = c2582c;
    }

    public final void run() {
        try {
            String e = this.f6240a.m8547e();
            FyberLogger.m8448d("ReporterOperation", "event will be sent to " + e);
            int b = ((C2657h) C2657h.m8506b(e).mo4005a()).m8464b();
            FyberLogger.m8448d("ReporterOperation", "Server returned status code: " + b);
            if (b == 200) {
                this.f6241b.mo3983a();
            } else {
                this.f6241b.m8247a(b);
            }
        } catch (Exception e2) {
            FyberLogger.m8450e("ReporterOperation", "An error occurred", e2);
        }
    }
}

package com.unit.two.p147c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.unit.two.p148d.C4124a;
import com.unit.two.p148d.C4125b;
import com.unit.two.p151f.C4144k;

final class C4118u extends BroadcastReceiver {
    private /* synthetic */ C4117t f9590a;

    C4118u(C4117t c4117t) {
        this.f9590a = c4117t;
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            if ("android.intent.action.TIME_TICK".equals(intent.getAction())) {
                if (this.f9590a.f9587c != null) {
                    this.f9590a.f9587c.mo6922a();
                }
                if (System.currentTimeMillis() - C4144k.m12808d(context, C4144k.f9690d) >= 86400000) {
                    if (this.f9590a.f9588d != null) {
                        this.f9590a.f9588d.mo6922a();
                    }
                    C4144k.m12798a(context, C4144k.f9690d, System.currentTimeMillis());
                }
                long d = C4144k.m12808d(context, C4144k.f9691e);
                C4125b.m12740a(context);
                C4124a a = C4125b.m12738a();
                if (a != null && System.currentTimeMillis() - d >= ((long) a.m12735b())) {
                    if (this.f9590a.f9589e != null) {
                        this.f9590a.f9589e.mo6922a();
                    }
                    C4144k.m12798a(context, C4144k.f9691e, System.currentTimeMillis());
                }
            }
        } catch (Throwable th) {
        }
    }
}

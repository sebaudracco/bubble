package com.unit.two.p147c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.unit.two.p148d.C4124a;
import com.unit.two.p148d.C4125b;
import com.unit.two.p150e.C4128a;
import com.unit.two.p151f.C4144k;
import com.unit.two.p151f.C4145l;

public final class C4101f extends BroadcastReceiver {
    private static final String f9555a = C4096a.dM;
    private static C4101f f9556b;

    static {
        String str = C4096a.dL;
    }

    public static void m12685a(Context context) {
        if (f9556b == null) {
            f9556b = new C4101f();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(f9555a);
            context.registerReceiver(f9556b, intentFilter);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(f9555a) && C4145l.f9704a.equals(C4145l.m12860q(context))) {
            int h = C4144k.m12814h(context);
            long j = C4144k.m12816j(context);
            C4125b.m12740a(context);
            C4124a a = C4125b.m12738a();
            if (a != null && a.m12736c() && a.m12737d() && h < a.m12734a() && System.currentTimeMillis() - j > ((long) ((a.m12735b() * 60) * 1000))) {
                C4144k.m12806c(context, System.currentTimeMillis());
                C4144k.m12795a(context, h + 1);
                C4128a.m12748a(context);
            }
        }
    }
}

package com.unit.two.p147c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

public class C4117t {
    private static C4117t f9585b;
    private Context f9586a;
    private C4109v f9587c;
    private C4109v f9588d;
    private C4109v f9589e;

    static {
        String str = C4096a.dN;
        str = C4096a.dO;
    }

    private C4117t(Context context) {
        this.f9586a = context.getApplicationContext();
        BroadcastReceiver c4118u = new C4118u(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        this.f9586a.registerReceiver(c4118u, intentFilter);
    }

    public static C4117t m12706a(Context context) {
        if (f9585b == null) {
            synchronized (C4117t.class) {
                if (f9585b == null) {
                    f9585b = new C4117t(context);
                }
            }
        }
        return f9585b;
    }

    public static void m12708a() {
    }

    public final void m12711a(C4109v c4109v) {
        this.f9587c = c4109v;
    }

    public final void m12712b(C4109v c4109v) {
        this.f9588d = c4109v;
    }

    public final void m12713c(C4109v c4109v) {
        this.f9589e = c4109v;
    }
}

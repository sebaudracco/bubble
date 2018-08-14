package com.elephant.data.p046f.p047a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1817e;
import com.elephant.data.p048g.C1821i;
import com.elephant.data.p048g.p050b.C1813b;

public final class C1794b implements C1793a {
    private static C1794b f3779a;
    private Context f3780b;
    private boolean f3781c = false;
    private BroadcastReceiver f3782d = new C1799f(this);

    static {
        String str = C1814b.cB;
    }

    private C1794b(Context context) {
        this.f3780b = context.getApplicationContext();
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            this.f3780b.registerReceiver(this.f3782d, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static C1794b m5169a(Context context) {
        if (f3779a == null) {
            f3779a = new C1794b(context);
        }
        return f3779a;
    }

    public final void mo3540a() {
        C1821i.m5346a(new C1795c(this));
    }

    public final boolean m5172b() {
        long d = C1813b.m5264d(this.f3780b);
        long j = 28800000;
        if (C1806f.m5221a(this.f3780b).m5223a().m5193a()) {
            j = (long) (((C1806f.m5221a(this.f3780b).m5223a().m5212r() * 60) * 60) * 1000);
        }
        return (System.currentTimeMillis() - d < j || this.f3781c) ? false : !C1817e.m5333a(this.f3780b).m5335a();
    }
}

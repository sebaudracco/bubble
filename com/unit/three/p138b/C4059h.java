package com.unit.three.p138b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.unit.three.p143e.C4090d;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class C4059h extends BroadcastReceiver {
    private static C4059h f9374a;
    private List f9375b = new CopyOnWriteArrayList();

    public interface C4033a {
        void reconnected();
    }

    private C4059h() {
    }

    public static C4059h m12523a() {
        if (f9374a == null) {
            synchronized (C4059h.class) {
                if (f9374a == null) {
                    f9374a = new C4059h();
                }
            }
        }
        return f9374a;
    }

    public static void m12524a(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(f9374a, intentFilter);
        } catch (Throwable th) {
        }
    }

    public final void m12525a(C4033a c4033a) {
        try {
            this.f9375b.add(c4033a);
        } catch (Throwable th) {
        }
    }

    public final void m12526b(C4033a c4033a) {
        try {
            this.f9375b.remove(c4033a);
        } catch (Throwable th) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") && C4090d.m12625a(context)) {
            try {
                Object obj = (this.f9375b == null || this.f9375b.size() == 0) ? 1 : null;
                if (obj == null) {
                    for (C4033a c4033a : this.f9375b) {
                        if (c4033a != null) {
                            c4033a.reconnected();
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }
}

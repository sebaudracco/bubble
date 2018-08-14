package com.elephant.data.p035a.p036a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.appsgeyser.sdk.configuration.Constants;
import com.elephant.data.p037d.C1743a;
import com.elephant.data.p037d.C1752b;
import com.elephant.data.p037d.C1767d;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1821i;
import com.stepleaderdigital.reveal.Reveal;

public final class C1719h implements Runnable {
    private Context f3521a;
    private String f3522b;
    private String f3523c;
    private int f3524d = 1;
    private long f3525e = Constants.PULL_ALARM_PERIOD;
    private long f3526f = Reveal.CHECK_DELAY;
    private long f3527g;
    private C1804d f3528h;
    private String f3529i;
    private int f3530j;
    private boolean f3531k = false;

    static {
        String str = C1814b.jU;
    }

    private C1719h(Context context, String str, String str2) {
        this.f3521a = context;
        this.f3522b = str;
        this.f3529i = str2;
        this.f3523c = C1767d.m5096b(str2);
        this.f3531k = true;
        this.f3528h = C1806f.m5221a(context).m5223a();
    }

    public C1719h(Context context, String str, String str2, int i) {
        this.f3521a = context;
        this.f3522b = str;
        this.f3523c = C1767d.m5096b(str2);
        this.f3530j = i;
        this.f3527g = System.currentTimeMillis();
        this.f3528h = C1806f.m5221a(this.f3521a).m5223a();
        if (this.f3528h.m5193a()) {
            this.f3525e = (this.f3528h.m5198d() * 3600) * 1000;
            this.f3526f = this.f3528h.m5197c();
            if (this.f3526f < 0) {
                this.f3526f = Reveal.CHECK_DELAY;
            }
        }
        this.f3529i = str2;
    }

    private void m4962a() {
        if (this.f3528h.m5199e() == 3 || !TextUtils.isEmpty(this.f3523c)) {
            System.currentTimeMillis();
            if (!this.f3531k && this.f3528h.m5193a() && this.f3528h.m5201g() > 0) {
                try {
                    Thread.sleep(this.f3528h.m5201g());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.currentTimeMillis();
            switch (this.f3528h.m5199e()) {
                case 1:
                    m4966c();
                    return;
                case 2:
                    m4965b();
                    return;
                case 3:
                    if (TextUtils.isEmpty(this.f3523c)) {
                        m4966c();
                        return;
                    } else {
                        m4965b();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public static synchronized void m4963a(Context context, String str, int i) {
        synchronized (C1719h.class) {
            if (C1806f.m5221a(context).m5223a().m5193a()) {
                C1743a c = C1767d.m5097c(str);
                if (!(c == null || c.m5025f() || TextUtils.isEmpty(C1767d.m5096b(c.m5022c())))) {
                    new C1715d(context, str, 0).mo3530a(c);
                }
            }
            C1821i.m5346a(new C1752b(context, str, 0));
        }
    }

    public static void m4964a(Context context, String str, String str2) {
        C1821i.m5346a(new C1719h(context, str, str2));
    }

    private void m4965b() {
        C1816d.m5303e(this.f3521a, this.f3522b);
    }

    private void m4966c() {
        C1816d.m5287a(this.f3521a, this.f3529i, false);
    }

    private void m4967d() {
        while (System.currentTimeMillis() - this.f3527g < this.f3525e) {
            Intent intent = new Intent(C1814b.jV);
            intent.setPackage(this.f3522b);
            intent.setFlags(32);
            intent.putExtra(C1814b.jW, this.f3523c);
            this.f3521a.sendBroadcast(intent);
            try {
                if (this.f3526f > 0) {
                    Thread.sleep(this.f3526f);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public final void run() {
        if (this.f3521a != null && !TextUtils.isEmpty(this.f3522b)) {
            if (this.f3531k) {
                m4962a();
                return;
            }
            if (this.f3530j == 0) {
                m4962a();
            }
            if (!TextUtils.isEmpty(this.f3523c)) {
                switch (this.f3524d) {
                    case 1:
                        m4967d();
                        return;
                    case 2:
                        return;
                    default:
                        m4967d();
                        return;
                }
            }
        }
    }
}

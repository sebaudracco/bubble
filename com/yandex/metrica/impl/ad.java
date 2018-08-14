package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.IMetricaService.Stub;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ad {
    public static final long f11577a = TimeUnit.SECONDS.toMillis(10);
    private final Context f11578b;
    private final Handler f11579c;
    private final List<C4309a> f11580d = new CopyOnWriteArrayList();
    private volatile IMetricaService f11581e = null;
    private final Runnable f11582f = new C43071(this);
    private final ServiceConnection f11583g = new C43082(this);

    class C43071 implements Runnable {
        final /* synthetic */ ad f11575a;

        C43071(ad adVar) {
            this.f11575a = adVar;
        }

        public void run() {
            this.f11575a.m14491f();
        }
    }

    class C43082 implements ServiceConnection {
        final /* synthetic */ ad f11576a;

        C43082(ad adVar) {
            this.f11576a = adVar;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            this.f11576a.f11581e = Stub.asInterface(service);
            ad.m14489b(this.f11576a);
        }

        public void onServiceDisconnected(ComponentName name) {
            this.f11576a.f11581e = null;
            this.f11576a.m14492g();
        }
    }

    interface C4309a {
        void mo7020a();
    }

    public ad(Context context, Handler handler) {
        this.f11578b = context.getApplicationContext();
        this.f11579c = handler;
    }

    public synchronized void m14493a() {
        if (this.f11581e == null) {
            try {
                this.f11578b.bindService(be.m14892c(this.f11578b), this.f11583g, 1);
            } catch (Exception e) {
            }
        }
    }

    public void m14495b() {
        this.f11579c.removeCallbacks(this.f11582f);
        this.f11579c.postDelayed(this.f11582f, f11577a);
    }

    void m14496c() {
        this.f11579c.removeCallbacks(this.f11582f);
    }

    public boolean m14497d() {
        return this.f11581e != null;
    }

    public IMetricaService m14498e() {
        return this.f11581e;
    }

    private synchronized void m14491f() {
        if (this.f11578b != null && m14497d()) {
            try {
                this.f11578b.unbindService(this.f11583g);
                this.f11581e = null;
            } catch (Exception e) {
            }
        }
        this.f11581e = null;
        m14492g();
    }

    private void m14492g() {
        Iterator it = this.f11580d.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public void m14494a(C4309a c4309a) {
        this.f11580d.add(c4309a);
    }

    static /* synthetic */ void m14489b(ad adVar) {
        for (C4309a a : adVar.f11580d) {
            a.mo7020a();
        }
    }
}

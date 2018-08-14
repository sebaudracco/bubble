package com.yandex.metrica.impl.ob;

import android.util.Log;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class eu {
    private static final String f12349a = eu.class.getSimpleName();
    private ArrayList<Object> f12350b = new ArrayList();
    private ek f12351c;
    private final Lock f12352d;
    private final Lock f12353e;
    private final Condition f12354f;
    private ff f12355g;

    eu(fb fbVar) {
        this.f12351c = new eo(fbVar);
        this.f12352d = new ReentrantLock();
        this.f12353e = new ReentrantLock();
        this.f12354f = this.f12352d.newCondition();
    }

    void m15959a(X509Certificate[] x509CertificateArr) {
        this.f12353e.lock();
        try {
            if (this.f12351c.mo7100a(x509CertificateArr)) {
                this.f12353e.unlock();
                return;
            }
            this.f12355g = new ff(x509CertificateArr);
            Object obj = null;
            Iterator it = this.f12350b.iterator();
            while (it.hasNext()) {
                it.next();
                obj = 1;
            }
            if (obj != null) {
                Log.i(f12349a, "waiting for trust issue resolve");
                this.f12352d.lock();
                while (!this.f12355g.m16004b()) {
                    try {
                        this.f12354f.await(30000, TimeUnit.MILLISECONDS);
                        this.f12355g.m16005c();
                    } catch (InterruptedException e) {
                    }
                }
                this.f12352d.unlock();
            }
            this.f12353e.unlock();
        } catch (Throwable th) {
            this.f12353e.unlock();
        }
    }
}

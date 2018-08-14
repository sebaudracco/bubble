package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.at.C4336a;
import com.yandex.metrica.impl.ob.C4503t;
import java.io.Closeable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class bj implements Closeable {
    private C4503t f11808a;
    private final ba f11809b;
    private final Object f11810c = new Object();
    private final aj f11811d;
    private bh f11812e;
    private boolean f11813f = false;
    private Runnable f11814g = new C43621(this);

    class C43621 implements Runnable {
        final /* synthetic */ bj f11807a;

        C43621(bj bjVar) {
            this.f11807a = bjVar;
        }

        public void run() {
            this.f11807a.m14969a();
        }
    }

    public bj(C4503t c4503t, Executor executor) {
        this.f11808a = c4503t;
        this.f11809b = c4503t.mo7111h();
        this.f11811d = m14967a(c4503t, executor);
        this.f11811d.start();
        this.f11812e = m14968a(this.f11808a);
    }

    bh m14968a(C4503t c4503t) {
        return new bh(c4503t);
    }

    aj m14967a(C4503t c4503t, Executor executor) {
        aj ajVar = new aj(executor, c4503t.mo7113l());
        ajVar.setName("NetworkCore [" + c4503t.mo7113l() + "]");
        return ajVar;
    }

    public void close() {
        synchronized (this.f11810c) {
            if (!this.f11813f) {
                m14966d();
                if (this.f11811d.isAlive()) {
                    this.f11811d.m14562a();
                }
                this.f11813f = true;
            }
        }
    }

    public void m14969a() {
        synchronized (this.f11810c) {
            if (!this.f11813f) {
                synchronized (this.f11810c) {
                    if (!this.f11813f) {
                        if (this.f11812e.m14955s()) {
                            this.f11812e = new bh(this.f11808a);
                            this.f11811d.m14563a(this.f11812e);
                        }
                        if (bk.m14992b(this.f11809b.m14807a())) {
                            m14965a(as.m14693u(), Long.valueOf(-2));
                            m14965a(at.m14665A(), null);
                        }
                    }
                }
                m14966d();
            }
        }
    }

    private void m14965a(C4336a c4336a, Long l) {
        List<ContentValues> a = this.f11808a.m16165i().m15349a(l);
        if (a.isEmpty()) {
            a.add(C4338l.f11672a);
        }
        for (ContentValues contentValues : a) {
            try {
                this.f11811d.m14563a(c4336a.mo6994a(this.f11808a).m14662a(contentValues));
            } catch (Exception e) {
                return;
            }
        }
    }

    private void m14966d() {
        this.f11808a.m16170n().removeCallbacks(this.f11814g);
    }

    public void m14970b() {
        synchronized (this.f11810c) {
            if (!this.f11813f) {
                m14966d();
                if (this.f11808a.mo7112j().m14252b() > 0) {
                    this.f11808a.m16170n().postDelayed(this.f11814g, TimeUnit.SECONDS.toMillis((long) this.f11808a.mo7112j().m14252b()));
                }
            }
        }
    }

    public void m14971c() {
        synchronized (this.f11810c) {
            if (!(this.f11813f || this.f11811d.m14564b(this.f11812e))) {
                this.f11812e.m14945a(true);
                this.f11812e.m14942a(0);
                this.f11812e = new bh(this.f11808a);
                this.f11811d.m14563a(this.f11812e);
            }
        }
    }
}

package com.moat.analytics.mobile.inm;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

class ar implements ao {
    private static final AtomicReference<ExecutorService> f8547a = new AtomicReference();
    private static final Queue<ap> f8548b = new ConcurrentLinkedQueue();
    private static volatile aq f8549c = aq.OFF;
    private static volatile boolean f8550d = false;
    private static volatile int f8551e = 200;

    ar(aa aaVar) {
        if (f8547a.get() == null) {
            if (f8547a.compareAndSet(null, Executors.newSingleThreadExecutor(new as(this)))) {
                String str = "INM";
                ((ExecutorService) f8547a.get()).submit(new au("INM", aaVar, new at(this), null));
            }
        }
    }

    private void m11530g() {
        synchronized (f8548b) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f8548b.iterator();
            while (it.hasNext()) {
                ap apVar = (ap) it.next();
                if (apVar.mo6488c()) {
                    it.remove();
                } else if (currentTimeMillis - apVar.mo6489d() >= 300000) {
                    it.remove();
                }
            }
            if (f8548b.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f8548b.remove();
                }
            }
        }
    }

    public aq mo6480a() {
        return f8549c;
    }

    public void mo6481a(ap apVar) {
        m11530g();
        f8548b.add(apVar);
    }

    public boolean mo6482b() {
        return f8550d;
    }

    public int mo6483c() {
        return f8551e;
    }
}

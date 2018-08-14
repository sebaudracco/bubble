package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.C4484g;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class ah implements UncaughtExceptionHandler {
    private final CopyOnWriteArrayList<C4342i> f11620a = new CopyOnWriteArrayList();
    private final UncaughtExceptionHandler f11621b;

    public ah(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f11621b = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            C4484g.m16084a().m16092b(new ao());
            Iterator it = this.f11620a.iterator();
            while (it.hasNext()) {
                ((C4342i) it.next()).m14705a(ex);
            }
        } finally {
            if (this.f11621b != null) {
                this.f11621b.uncaughtException(thread, ex);
            }
        }
    }

    public void m14552a(C4342i c4342i) {
        this.f11620a.add(c4342i);
    }

    public void m14553b(C4342i c4342i) {
        this.f11620a.remove(c4342i);
    }
}

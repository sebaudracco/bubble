package com.yandex.metrica.impl.ob;

import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class C4484g {
    private final Thread f12443a = new Thread(new C44801(this), "Bus Dispatcher");
    private volatile boolean f12444b = true;
    private final BlockingQueue<C4481a> f12445c = new LinkedBlockingQueue();
    private ConcurrentHashMap<Class, CopyOnWriteArrayList<C4486k<? extends C4325i>>> f12446d = new ConcurrentHashMap();
    private WeakHashMap<Object, CopyOnWriteArrayList<C4483c>> f12447e = new WeakHashMap();
    private ConcurrentHashMap<Class, C4325i> f12448f = new ConcurrentHashMap();

    class C44801 implements Runnable {
        final /* synthetic */ C4484g f12437a;

        C44801(C4484g c4484g) {
            this.f12437a = c4484g;
        }

        public void run() {
            while (this.f12437a.f12444b) {
                try {
                    ((C4481a) this.f12437a.f12445c.take()).m16081a();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private static class C4481a {
        private final C4325i f12438a;
        private final C4486k<? extends C4325i> f12439b;

        private C4481a(C4325i c4325i, C4486k<? extends C4325i> c4486k) {
            this.f12438a = c4325i;
            this.f12439b = c4486k;
        }

        void m16081a() {
            try {
                if (!this.f12439b.m16098b(this.f12438a)) {
                    this.f12439b.m16097a(this.f12438a);
                }
            } catch (Throwable th) {
            }
        }
    }

    private static final class C4482b {
        private static final C4484g f12440a = new C4484g();
    }

    private static class C4483c {
        final CopyOnWriteArrayList<C4486k<? extends C4325i>> f12441a;
        final C4486k<? extends C4325i> f12442b;

        private C4483c(CopyOnWriteArrayList<C4486k<? extends C4325i>> copyOnWriteArrayList, C4486k<? extends C4325i> c4486k) {
            this.f12441a = copyOnWriteArrayList;
            this.f12442b = c4486k;
        }

        protected void m16083a() {
            this.f12441a.remove(this.f12442b);
        }

        protected void finalize() throws Throwable {
            super.finalize();
            m16083a();
        }
    }

    public static final C4484g m16084a() {
        return C4482b.f12440a;
    }

    C4484g() {
        this.f12443a.start();
    }

    public synchronized void m16087a(C4325i c4325i) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.f12446d.get(c4325i.getClass());
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                m16088a(c4325i, (C4486k) it.next());
            }
        }
    }

    void m16088a(C4325i c4325i, C4486k<? extends C4325i> c4486k) {
        this.f12445c.add(new C4481a(c4325i, c4486k));
    }

    public synchronized void m16092b(C4325i c4325i) {
        m16087a(c4325i);
        this.f12448f.put(c4325i.getClass(), c4325i);
    }

    public synchronized void m16089a(Class<? extends C4325i> cls) {
        this.f12448f.remove(cls);
    }

    public synchronized void m16091a(Object obj, Class cls, C4486k<? extends C4325i> c4486k) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.f12446d.get(cls);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.f12446d.put(cls, copyOnWriteArrayList2);
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        copyOnWriteArrayList.add(c4486k);
        copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.f12447e.get(obj);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.f12447e.put(obj, copyOnWriteArrayList2);
        }
        copyOnWriteArrayList2.add(new C4483c(copyOnWriteArrayList, c4486k));
        C4325i c4325i = (C4325i) this.f12448f.get(cls);
        if (c4325i != null) {
            m16088a(c4325i, c4486k);
        }
    }

    public synchronized void m16090a(Object obj) {
        List<C4483c> list = (List) this.f12447e.remove(obj);
        if (list != null) {
            for (C4483c a : list) {
                a.m16083a();
            }
        }
    }
}

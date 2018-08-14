package com.fyber.requesters.p097a.p098a;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: NetworkAgentCallable */
public final class C2611k<R, E extends Exception> implements Callable<Boolean> {
    private CountDownLatch f6514a = new CountDownLatch(1);
    private int f6515b = 60000;
    private E f6516c;
    private R f6517d;
    private C2607a<R, E> f6518e;

    /* compiled from: NetworkAgentCallable */
    public interface C2607a<R, E extends Exception> {
        void mo3997a();

        void mo3998a(R r);
    }

    public final void m8379a(R r) {
        this.f6517d = r;
        this.f6514a.countDown();
    }

    public final C2611k<R, E> m8377a(C2607a<R, E> c2607a) {
        this.f6518e = c2607a;
        return this;
    }

    public final void m8378a(E e) {
        this.f6516c = e;
        m8379a(null);
    }

    public final /* synthetic */ Object call() throws Exception {
        this.f6514a.await((long) this.f6515b, TimeUnit.MILLISECONDS);
        if (this.f6516c != null) {
            if (this.f6518e != null) {
                this.f6518e.mo3997a();
            }
            throw this.f6516c;
        }
        boolean z;
        if (this.f6518e != null) {
            this.f6518e.mo3998a(this.f6517d);
        }
        if (this.f6517d != null) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}

package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.fu.C4471b;
import com.yandex.metrica.impl.ob.fu.C4473a;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class fv<T> implements C4473a, C4471b<T>, Future<T> {
    private boolean f12430a = false;
    private T f12431b;
    private fr f12432c;

    public static <E> fv<E> m16074a() {
        return new fv();
    }

    private fv() {
    }

    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return m16075a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return m16075a(Long.valueOf(TimeUnit.MILLISECONDS.convert(timeout, unit)));
    }

    private synchronized T m16075a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        T t;
        if (this.f12432c != null) {
            throw new ExecutionException(this.f12432c);
        } else if (this.f12430a) {
            t = this.f12431b;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.f12432c != null) {
                throw new ExecutionException(this.f12432c);
            } else if (this.f12430a) {
                t = this.f12431b;
            } else {
                throw new TimeoutException();
            }
        }
        return t;
    }

    public boolean isCancelled() {
        return false;
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.f12430a || this.f12432c != null || isCancelled();
        return z;
    }

    public synchronized void mo7101a(T t) {
        this.f12430a = true;
        this.f12431b = t;
        notifyAll();
    }

    public synchronized void mo7102a(fr frVar) {
        this.f12432c = frVar;
        notifyAll();
    }
}

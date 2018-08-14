package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: vungle */
abstract class my$d<V> implements Runnable {
    final V f10771b;
    final int f10772c;
    final /* synthetic */ my f10773d;

    abstract AtomicInteger mo6953a();

    abstract void mo6954a(V v);

    public my$d(my myVar, V v, int i) {
        this.f10773d = myVar;
        this.f10772c = i;
        this.f10771b = v;
    }

    public void run() {
        try {
            AtomicInteger a = mo6953a();
            int i;
            do {
                i = a.get();
                if (this.f10772c < i) {
                    return;
                }
            } while (!a.compareAndSet(i, this.f10772c));
            mo6954a(this.f10771b);
        } catch (Throwable e) {
            Logger.m13649w(Logger.AD_TAG, e);
        }
    }
}

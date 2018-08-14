package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
/* compiled from: vungle */
public class yd {
    Scheduler f3472a = Schedulers.io();

    @Inject
    yd() {
    }

    public Func1<Observable<? extends Throwable>, Observable<?>> m4898a(String str) {
        return m4897a(100, str);
    }

    public Func1<Observable<? extends Throwable>, Observable<?>> m4897a(int i, String str) {
        return ye.m4899a(this, i + 1, str);
    }

    /* synthetic */ Observable m4896a(int i, String str, Observable observable) {
        return observable.zipWith(Observable.range(1, i), new 2(this, i)).flatMap(new 1(this, str));
    }
}

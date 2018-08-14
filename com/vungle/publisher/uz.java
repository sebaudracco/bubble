package com.vungle.publisher;

import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

/* compiled from: vungle */
public class uz implements Func1<tr, Observable<tw>> {
    @Inject
    ue f3388a;

    public /* synthetic */ Object call(Object obj) {
        return m4722a((tr) obj);
    }

    public Observable<tw> m4722a(tr trVar) {
        return Observable.just(m4723b(trVar));
    }

    protected tw m4723b(tr trVar) {
        return this.f3388a.m4702a(trVar);
    }
}

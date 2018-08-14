package com.vungle.publisher;

import javax.inject.Inject;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* compiled from: vungle */
public class uw implements Func1<tw, Observable<String>> {
    @Inject
    uj f3387a;

    public /* synthetic */ Object call(Object obj) {
        return m4721a((tw) obj);
    }

    public Observable<String> m4721a(tw twVar) {
        try {
            return Observable.just(this.f3387a.m4713a(twVar.m4686a()));
        } catch (Throwable e) {
            throw Exceptions.propagate(e);
        }
    }
}

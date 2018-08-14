package com.vungle.publisher;

import com.vungle.publisher.ei.a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

@Singleton
/* compiled from: vungle */
public class xy implements Func1<gd<?>, Observable<? extends gd<?>>> {
    @Inject
    th$a f3466a;
    @Inject
    uu f3467b;
    @Inject
    uz f3468c;
    @Inject
    yd f3469d;

    public /* synthetic */ Object call(Object obj) {
        return m4891a((gd) obj);
    }

    @Inject
    xy() {
    }

    public Observable<? extends gd<?>> m4891a(gd<?> gdVar) {
        b o = gdVar.o();
        a j = gdVar.j();
        String f = gdVar.f();
        Logger.d(Logger.PREPARE_TAG, "preparing viewable: " + gdVar);
        Observable just = Observable.just(gdVar);
        switch (1.a[j.ordinal()]) {
            case 1:
                return just;
            case 2:
                break;
            case 3:
            case 4:
                Logger.d(Logger.PREPARE_TAG, o + " will begin downloading for " + "ad_id" + " " + f);
                just = just.flatMap(this.f3466a).flatMap(this.f3468c).zipWith(Observable.just(gdVar), this.f3467b);
                break;
            default:
                throw new IllegalStateException("unexpected " + o + " status: " + j);
        }
        return just.flatMap(m4892a()).doOnError(m4893b(gdVar)).retryWhen(this.f3469d.m4897a(3, "viewable prep failed"));
    }

    Action1<Throwable> m4893b(gd<?> gdVar) {
        return xz.m4894a(gdVar);
    }

    static /* synthetic */ void m4889a(gd gdVar, Throwable th) {
        Logger.i(Logger.PREPARE_TAG, "viewable prep error, update status to failed for " + gdVar);
        gdVar.b(a.d);
    }

    Func1<gd<?>, Observable<? extends gd<?>>> m4892a() {
        return ya.m4895a();
    }

    static /* synthetic */ Observable m4890c(gd gdVar) {
        if (gdVar.m()) {
            return Observable.just(gdVar);
        }
        throw new RuntimeException(gdVar.o() + " post processing failed for " + "ad_id" + " " + gdVar.f());
    }
}

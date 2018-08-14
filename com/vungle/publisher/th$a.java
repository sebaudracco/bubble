package com.vungle.publisher;

import com.vungle.publisher.tr.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

@Singleton
/* compiled from: vungle */
public class th$a extends a<th> implements Func1<gd<?>, Observable<th>> {
    protected /* synthetic */ tr m4669b() {
        return m4667a();
    }

    public /* synthetic */ Object call(Object obj) {
        return m4668a((gd) obj);
    }

    @Inject
    th$a() {
    }

    protected th m4667a() {
        return new th();
    }

    public Observable<th> m4668a(gd<?> gdVar) {
        th thVar = (th) c();
        thVar.a(gdVar.e());
        return Observable.just(thVar);
    }
}

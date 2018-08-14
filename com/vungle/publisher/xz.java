package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class xz implements Action1 {
    private final gd f3470a;

    private xz(gd gdVar) {
        this.f3470a = gdVar;
    }

    public static Action1 m4894a(gd gdVar) {
        return new xz(gdVar);
    }

    @Hidden
    public void call(Object obj) {
        xy.m4889a(this.f3470a, (Throwable) obj);
    }
}

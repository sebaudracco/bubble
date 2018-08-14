package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vn implements Action1 {
    private final vc f3415a;

    private vn(vc vcVar) {
        this.f3415a = vcVar;
    }

    public static Action1 m4753a(vc vcVar) {
        return new vn(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3415a.m4740c((Throwable) obj);
    }
}

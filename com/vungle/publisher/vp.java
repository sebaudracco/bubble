package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vp implements Action1 {
    private final vc f3417a;

    private vp(vc vcVar) {
        this.f3417a = vcVar;
    }

    public static Action1 m4755a(vc vcVar) {
        return new vp(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3417a.m4737b((Throwable) obj);
    }
}

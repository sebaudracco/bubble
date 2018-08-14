package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vf implements Action1 {
    private final vc f3404a;

    private vf(vc vcVar) {
        this.f3404a = vcVar;
    }

    public static Action1 m4745a(vc vcVar) {
        return new vf(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3404a.m4733a((Throwable) obj);
    }
}

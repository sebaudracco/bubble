package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vi implements Action1 {
    private final vc f3407a;

    private vi(vc vcVar) {
        this.f3407a = vcVar;
    }

    public static Action1 m4748a(vc vcVar) {
        return new vi(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3407a.m4742d((Throwable) obj);
    }
}

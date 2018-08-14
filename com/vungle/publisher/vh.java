package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vh implements Action1 {
    private final vc f3406a;

    private vh(vc vcVar) {
        this.f3406a = vcVar;
    }

    public static Action1 m4747a(vc vcVar) {
        return new vh(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3406a.m4729a((Long) obj);
    }
}

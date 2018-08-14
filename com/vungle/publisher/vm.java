package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vm implements Action1 {
    private final vc f3414a;

    private vm(vc vcVar) {
        this.f3414a = vcVar;
    }

    public static Action1 m4752a(vc vcVar) {
        return new vm(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3414a.m4739c((JsonObject) obj);
    }
}

package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vo implements Action1 {
    private final vc f3416a;

    private vo(vc vcVar) {
        this.f3416a = vcVar;
    }

    public static Action1 m4754a(vc vcVar) {
        return new vo(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3416a.m4735b((JsonObject) obj);
    }
}

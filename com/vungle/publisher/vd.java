package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vd implements Action1 {
    private final vc f3401a;
    private final cz f3402b;

    private vd(vc vcVar, cz czVar) {
        this.f3401a = vcVar;
        this.f3402b = czVar;
    }

    public static Action1 m4743a(vc vcVar, cz czVar) {
        return new vd(vcVar, czVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3401a.m4728a(this.f3402b, (JsonObject) obj);
    }
}

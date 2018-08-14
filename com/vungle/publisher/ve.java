package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class ve implements Action1 {
    private final vc f3403a;

    private ve(vc vcVar) {
        this.f3403a = vcVar;
    }

    public static Action1 m4744a(vc vcVar) {
        return new ve(vcVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3403a.m4726a((JsonObject) obj);
    }
}

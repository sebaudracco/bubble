package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class xt implements Action1 {
    private final xq f3461a;
    private final dr f3462b;

    private xt(xq xqVar, dr drVar) {
        this.f3461a = xqVar;
        this.f3462b = drVar;
    }

    public static Action1 m4886a(xq xqVar, dr drVar) {
        return new xt(xqVar, drVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3461a.m4869a(this.f3462b, (Throwable) obj);
    }
}

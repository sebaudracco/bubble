package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class xm implements Action1 {
    private final xg f3453a;

    private xm(xg xgVar) {
        this.f3453a = xgVar;
    }

    public static Action1 m4861a(xg xgVar) {
        return new xm(xgVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3453a.m4854a((Throwable) obj);
    }
}

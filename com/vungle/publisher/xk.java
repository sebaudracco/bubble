package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Func1;

final /* synthetic */ class xk implements Func1 {
    private final xg f3450a;

    private xk(xg xgVar) {
        this.f3450a = xgVar;
    }

    public static Func1 m4859a(xg xgVar) {
        return new xk(xgVar);
    }

    @Hidden
    public Object call(Object obj) {
        return this.f3450a.m4849a((wc) obj);
    }
}

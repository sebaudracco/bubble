package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class xv implements Action1 {
    private final xq f3464a;
    private final dr f3465b;

    private xv(xq xqVar, dr drVar) {
        this.f3464a = xqVar;
        this.f3465b = drVar;
    }

    public static Action1 m4888a(xq xqVar, dr drVar) {
        return new xv(xqVar, drVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3464a.m4870a(this.f3465b, (List) obj);
    }
}

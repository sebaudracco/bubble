package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Func1;

final /* synthetic */ class xh implements Func1 {
    private final xg f3446a;

    private xh(xg xgVar) {
        this.f3446a = xgVar;
    }

    public static Func1 m4856a(xg xgVar) {
        return new xh(xgVar);
    }

    @Hidden
    public Object call(Object obj) {
        return this.f3446a.m4855b((String) obj);
    }
}

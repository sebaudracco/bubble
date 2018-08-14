package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class xr implements Func1 {
    private final dr f3459a;

    private xr(dr drVar) {
        this.f3459a = drVar;
    }

    public static Func1 m4884a(dr drVar) {
        return new xr(drVar);
    }

    @Hidden
    public Object call(Object obj) {
        return xq.m4864b(this.f3459a, (List) obj);
    }
}

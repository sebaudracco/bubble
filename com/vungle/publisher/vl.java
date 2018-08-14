package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Func1;

final /* synthetic */ class vl implements Func1 {
    private final vc f3412a;
    private final String f3413b;

    private vl(vc vcVar, String str) {
        this.f3412a = vcVar;
        this.f3413b = str;
    }

    public static Func1 m4751a(vc vcVar, String str) {
        return new vl(vcVar, str);
    }

    @Hidden
    public Object call(Object obj) {
        return this.f3412a.m4724a(this.f3413b, (Boolean) obj);
    }
}

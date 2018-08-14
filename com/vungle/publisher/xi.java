package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class xi implements Action1 {
    private final xg f3447a;
    private final String f3448b;

    private xi(xg xgVar, String str) {
        this.f3447a = xgVar;
        this.f3448b = str;
    }

    public static Action1 m4857a(xg xgVar, String str) {
        return new xi(xgVar, str);
    }

    @Hidden
    public void call(Object obj) {
        this.f3447a.m4853a(this.f3448b, (wd) obj);
    }
}

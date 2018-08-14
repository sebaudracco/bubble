package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.AbstractMap.SimpleEntry;
import rx.functions.Func1;

final /* synthetic */ class xl implements Func1 {
    private final xg f3451a;
    private final String f3452b;

    private xl(xg xgVar, String str) {
        this.f3451a = xgVar;
        this.f3452b = str;
    }

    public static Func1 m4860a(xg xgVar, String str) {
        return new xl(xgVar, str);
    }

    @Hidden
    public Object call(Object obj) {
        return this.f3451a.m4851a(this.f3452b, (SimpleEntry) obj);
    }
}

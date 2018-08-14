package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.Observable;
import rx.functions.Func1;

final /* synthetic */ class ye implements Func1 {
    private final yd f3473a;
    private final int f3474b;
    private final String f3475c;

    private ye(yd ydVar, int i, String str) {
        this.f3473a = ydVar;
        this.f3474b = i;
        this.f3475c = str;
    }

    public static Func1 m4899a(yd ydVar, int i, String str) {
        return new ye(ydVar, i, str);
    }

    @Hidden
    public Object call(Object obj) {
        return this.f3473a.m4896a(this.f3474b, this.f3475c, (Observable) obj);
    }
}

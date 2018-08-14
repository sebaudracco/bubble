package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1619f implements Runnable {
    private final C1591c f2948a;

    private C1619f(C1591c c1591c) {
        this.f2948a = c1591c;
    }

    public static Runnable m3975a(C1591c c1591c) {
        return new C1619f(c1591c);
    }

    @Hidden
    public void run() {
        this.f2948a.m3496c();
    }
}

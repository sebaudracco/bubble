package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1597d implements Runnable {
    private final C1591c f2810a;

    private C1597d(C1591c c1591c) {
        this.f2810a = c1591c;
    }

    public static Runnable m3668a(C1591c c1591c) {
        return new C1597d(c1591c);
    }

    @Hidden
    public void run() {
        this.f2810a.m3499e();
    }
}

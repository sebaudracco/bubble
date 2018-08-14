package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1602e implements Runnable {
    private final C1591c f2830a;

    private C1602e(C1591c c1591c) {
        this.f2830a = c1591c;
    }

    public static Runnable m3736a(C1591c c1591c) {
        return new C1602e(c1591c);
    }

    @Hidden
    public void run() {
        this.f2830a.m3498d();
    }
}

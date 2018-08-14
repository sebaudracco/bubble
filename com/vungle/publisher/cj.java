package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class cj implements Runnable {
    private final ci f2764a;

    private cj(ci ciVar) {
        this.f2764a = ciVar;
    }

    public static Runnable m3518a(ci ciVar) {
        return new cj(ciVar);
    }

    @Hidden
    public void run() {
        this.f2764a.m3516b();
    }
}

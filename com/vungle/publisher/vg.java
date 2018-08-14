package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class vg implements Runnable {
    private final vc f3405a;

    private vg(vc vcVar) {
        this.f3405a = vcVar;
    }

    public static Runnable m4746a(vc vcVar) {
        return new vg(vcVar);
    }

    @Hidden
    public void run() {
        this.f3405a.m4741d();
    }
}

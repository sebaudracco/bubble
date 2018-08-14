package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class po implements Runnable {
    private final pj f3250a;
    private final av f3251b;

    private po(pj pjVar, av avVar) {
        this.f3250a = pjVar;
        this.f3251b = avVar;
    }

    public static Runnable m4556a(pj pjVar, av avVar) {
        return new po(pjVar, avVar);
    }

    @Hidden
    public void run() {
        this.f3250a.m4541a(this.f3251b);
    }
}

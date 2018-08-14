package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pt implements Runnable {
    private final pj f3261a;
    private final bp f3262b;

    private pt(pj pjVar, bp bpVar) {
        this.f3261a = pjVar;
        this.f3262b = bpVar;
    }

    public static Runnable m4561a(pj pjVar, bp bpVar) {
        return new pt(pjVar, bpVar);
    }

    @Hidden
    public void run() {
        this.f3261a.m4545a(this.f3262b);
    }
}

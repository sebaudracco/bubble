package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ps implements Runnable {
    private final pj f3259a;
    private final bo f3260b;

    private ps(pj pjVar, bo boVar) {
        this.f3259a = pjVar;
        this.f3260b = boVar;
    }

    public static Runnable m4560a(pj pjVar, bo boVar) {
        return new ps(pjVar, boVar);
    }

    @Hidden
    public void run() {
        this.f3259a.m4544a(this.f3260b);
    }
}

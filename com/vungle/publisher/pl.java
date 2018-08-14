package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pl implements Runnable {
    private final pj f3244a;
    private final bq f3245b;

    private pl(pj pjVar, bq bqVar) {
        this.f3244a = pjVar;
        this.f3245b = bqVar;
    }

    public static Runnable m4553a(pj pjVar, bq bqVar) {
        return new pl(pjVar, bqVar);
    }

    @Hidden
    public void run() {
        this.f3244a.m4546a(this.f3245b);
    }
}

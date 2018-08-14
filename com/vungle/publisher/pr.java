package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pr implements Runnable {
    private final pj f3257a;
    private final bj f3258b;

    private pr(pj pjVar, bj bjVar) {
        this.f3257a = pjVar;
        this.f3258b = bjVar;
    }

    public static Runnable m4559a(pj pjVar, bj bjVar) {
        return new pr(pjVar, bjVar);
    }

    @Hidden
    public void run() {
        this.f3257a.m4542a(this.f3258b);
    }
}

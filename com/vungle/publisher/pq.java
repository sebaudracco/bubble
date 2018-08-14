package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pq implements Runnable {
    private final pj f3255a;
    private final bm f3256b;

    private pq(pj pjVar, bm bmVar) {
        this.f3255a = pjVar;
        this.f3256b = bmVar;
    }

    public static Runnable m4558a(pj pjVar, bm bmVar) {
        return new pq(pjVar, bmVar);
    }

    @Hidden
    public void run() {
        this.f3255a.m4543a(this.f3256b);
    }
}

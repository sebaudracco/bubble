package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pp implements Runnable {
    private final pj f3252a;
    private final String f3253b;
    private final boolean f3254c;

    private pp(pj pjVar, String str, boolean z) {
        this.f3252a = pjVar;
        this.f3253b = str;
        this.f3254c = z;
    }

    public static Runnable m4557a(pj pjVar, String str, boolean z) {
        return new pp(pjVar, str, z);
    }

    @Hidden
    public void run() {
        this.f3252a.m4550b(this.f3253b, this.f3254c);
    }
}

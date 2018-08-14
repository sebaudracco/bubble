package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pn implements Runnable {
    private final pj f3248a;
    private final String f3249b;

    private pn(pj pjVar, String str) {
        this.f3248a = pjVar;
        this.f3249b = str;
    }

    public static Runnable m4555a(pj pjVar, String str) {
        return new pn(pjVar, str);
    }

    @Hidden
    public void run() {
        this.f3248a.m4547a(this.f3249b);
    }
}

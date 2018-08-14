package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pm implements Runnable {
    private final pj f3246a;
    private final String f3247b;

    private pm(pj pjVar, String str) {
        this.f3246a = pjVar;
        this.f3247b = str;
    }

    public static Runnable m4554a(pj pjVar, String str) {
        return new pm(pjVar, str);
    }

    @Hidden
    public void run() {
        this.f3246a.m4549b(this.f3247b);
    }
}

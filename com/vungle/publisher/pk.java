package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pk implements Runnable {
    private final pj f3241a;
    private final String f3242b;
    private final boolean f3243c;

    private pk(pj pjVar, String str, boolean z) {
        this.f3241a = pjVar;
        this.f3242b = str;
        this.f3243c = z;
    }

    public static Runnable m4552a(pj pjVar, String str, boolean z) {
        return new pk(pjVar, str, z);
    }

    @Hidden
    public void run() {
        this.f3241a.m4551c(this.f3242b, this.f3243c);
    }
}

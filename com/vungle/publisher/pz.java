package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class pz implements Runnable {
    private final py f3266a;

    private pz(py pyVar) {
        this.f3266a = pyVar;
    }

    public static Runnable m4566a(py pyVar) {
        return new pz(pyVar);
    }

    @Hidden
    public void run() {
        this.f3266a.m4564a();
    }
}

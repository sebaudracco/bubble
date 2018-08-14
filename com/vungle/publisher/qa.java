package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class qa implements Runnable {
    private final py f3267a;
    private final qj f3268b;

    private qa(py pyVar, qj qjVar) {
        this.f3267a = pyVar;
        this.f3268b = qjVar;
    }

    public static Runnable m4567a(py pyVar, qj qjVar) {
        return new qa(pyVar, qjVar);
    }

    @Hidden
    public void run() {
        this.f3267a.m4565a(this.f3268b);
    }
}

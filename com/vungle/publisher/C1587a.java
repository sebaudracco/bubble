package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1587a implements Runnable {
    private final InitializationEventListener f2714a;

    private C1587a(InitializationEventListener initializationEventListener) {
        this.f2714a = initializationEventListener;
    }

    public static Runnable m3465a(InitializationEventListener initializationEventListener) {
        return new C1587a(initializationEventListener);
    }

    @Hidden
    public void run() {
        this.f2714a.m3460a();
    }
}

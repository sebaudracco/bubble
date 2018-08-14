package com.vungle.publisher;

import com.vungle.publisher.InitializationEventListener.C1586a;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1588b implements Runnable {
    private final C1586a f2715a;

    private C1588b(C1586a c1586a) {
        this.f2715a = c1586a;
    }

    public static Runnable m3466a(C1586a c1586a) {
        return new C1588b(c1586a);
    }

    @Hidden
    public void run() {
        this.f2715a.m3458a();
    }
}

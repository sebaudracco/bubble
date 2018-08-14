package com.vungle.publisher.env;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1611e implements Runnable {
    private final C1609d f2900a;
    private final AndroidDevice f2901b;

    private C1611e(C1609d c1609d, AndroidDevice androidDevice) {
        this.f2900a = c1609d;
        this.f2901b = androidDevice;
    }

    public static Runnable m3903a(C1609d c1609d, AndroidDevice androidDevice) {
        return new C1611e(c1609d, androidDevice);
    }

    @Hidden
    public void run() {
        this.f2900a.m3899f(this.f2901b);
    }
}

package com.vungle.publisher.env;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1616t implements Runnable {
    private final C1614r f2943a;
    private final String f2944b;

    private C1616t(C1614r c1614r, String str) {
        this.f2943a = c1614r;
        this.f2944b = str;
    }

    public static Runnable m3970a(C1614r c1614r, String str) {
        return new C1616t(c1614r, str);
    }

    @Hidden
    public void run() {
        this.f2943a.m3953d(this.f2944b);
    }
}

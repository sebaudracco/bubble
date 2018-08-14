package com.vungle.publisher.env;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class C1615s implements Runnable {
    private final C1614r f2941a;
    private final String f2942b;

    private C1615s(C1614r c1614r, String str) {
        this.f2941a = c1614r;
        this.f2942b = str;
    }

    public static Runnable m3969a(C1614r c1614r, String str) {
        return new C1615s(c1614r, str);
    }

    @Hidden
    public void run() {
        this.f2941a.m3955e(this.f2942b);
    }
}

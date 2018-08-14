package com.vungle.publisher;

import android.widget.ImageView;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class os implements Runnable {
    private final op f3221a;
    private final Float f3222b;
    private final ImageView f3223c;

    private os(op opVar, Float f, ImageView imageView) {
        this.f3221a = opVar;
        this.f3222b = f;
        this.f3223c = imageView;
    }

    public static Runnable m4528a(op opVar, Float f, ImageView imageView) {
        return new os(opVar, f, imageView);
    }

    @Hidden
    public void run() {
        this.f3221a.m4502a(this.f3222b, this.f3223c);
    }
}

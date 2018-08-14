package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vk implements Action1 {
    private final vc f3410a;
    private final String f3411b;

    private vk(vc vcVar, String str) {
        this.f3410a = vcVar;
        this.f3411b = str;
    }

    public static Action1 m4750a(vc vcVar, String str) {
        return new vk(vcVar, str);
    }

    @Hidden
    public void call(Object obj) {
        this.f3410a.m4732a(this.f3411b, (Throwable) obj);
    }
}

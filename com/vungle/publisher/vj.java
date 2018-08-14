package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class vj implements Action1 {
    private final vc f3408a;
    private final String f3409b;

    private vj(vc vcVar, String str) {
        this.f3408a = vcVar;
        this.f3409b = str;
    }

    public static Action1 m4749a(vc vcVar, String str) {
        return new vj(vcVar, str);
    }

    @Hidden
    public void call(Object obj) {
        this.f3408a.m4736b(this.f3409b, (JsonObject) obj);
    }
}

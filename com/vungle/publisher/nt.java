package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class nt implements Action1 {
    private final ns f3170a;

    private nt(ns nsVar) {
        this.f3170a = nsVar;
    }

    public static Action1 m4449a(ns nsVar) {
        return new nt(nsVar);
    }

    @Hidden
    public void call(Object obj) {
        this.f3170a.m4448a((Long) obj);
    }
}

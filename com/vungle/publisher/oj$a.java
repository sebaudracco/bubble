package com.vungle.publisher;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class oj$a {
    @Inject
    Context f3176a;
    @Inject
    mv f3177b;

    @Inject
    oj$a() {
    }

    public oj m4472a(int i) {
        oj ojVar = new oj(this.f3176a, null);
        oj.a(ojVar, i);
        oj.b(ojVar, (int) this.f3177b.m4396a(2));
        return ojVar;
    }
}

package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.Map;

final /* synthetic */ class tk implements Runnable {
    private final tj f3356a;
    private final cn f3357b;
    private final jf f3358c;
    private final String f3359d;
    private final Map f3360e;

    private tk(tj tjVar, cn cnVar, jf jfVar, String str, Map map) {
        this.f3356a = tjVar;
        this.f3357b = cnVar;
        this.f3358c = jfVar;
        this.f3359d = str;
        this.f3360e = map;
    }

    public static Runnable m4675a(tj tjVar, cn cnVar, jf jfVar, String str, Map map) {
        return new tk(tjVar, cnVar, jfVar, str, map);
    }

    @Hidden
    public void run() {
        this.f3356a.m4671a(this.f3357b, this.f3358c, this.f3359d, this.f3360e);
    }
}

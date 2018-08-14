package com.vungle.publisher;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.AbstractMap.SimpleEntry;
import rx.functions.Func2;

final /* synthetic */ class xn implements Func2 {
    private static final xn f3454a = new xn();

    private xn() {
    }

    public static Func2 m4862a() {
        return f3454a;
    }

    @Hidden
    public Object call(Object obj, Object obj2) {
        return new SimpleEntry((dr) obj, (String) obj2);
    }
}

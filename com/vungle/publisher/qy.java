package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Func1;

final /* synthetic */ class qy implements Func1 {
    private static final qy f3291a = new qy();

    private qy() {
    }

    public static Func1 m4594a() {
        return f3291a;
    }

    @Hidden
    public Object call(Object obj) {
        return ((JsonObject) obj).toString();
    }
}

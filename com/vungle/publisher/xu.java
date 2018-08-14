package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class xu implements Action1 {
    private static final xu f3463a = new xu();

    private xu() {
    }

    public static Action1 m4887a() {
        return f3463a;
    }

    @Hidden
    public void call(Object obj) {
        Logger.d(Logger.PREPARE_TAG, "viewable prepared: " + ((gd) obj).o() + ", has status: " + ((gd) obj).j());
    }
}

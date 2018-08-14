package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.lang.invoke.LambdaForm.Hidden;
import rx.functions.Action1;

final /* synthetic */ class xs implements Action1 {
    private static final xs f3460a = new xs();

    private xs() {
    }

    public static Action1 m4885a() {
        return f3460a;
    }

    @Hidden
    public void call(Object obj) {
        Logger.w(Logger.PREPARE_TAG, "could not prepare viewable after multiple retries");
    }
}

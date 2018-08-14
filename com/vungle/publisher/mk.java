package com.vungle.publisher;

import android.content.Context;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class mk implements Runnable {
    private final mj$a f3122a;
    private final Context f3123b;

    private mk(mj$a com_vungle_publisher_mj_a, Context context) {
        this.f3122a = com_vungle_publisher_mj_a;
        this.f3123b = context;
    }

    public static Runnable m4386a(mj$a com_vungle_publisher_mj_a, Context context) {
        return new mk(com_vungle_publisher_mj_a, context);
    }

    @Hidden
    public void run() {
        this.f3122a.m4380b(this.f3123b);
    }
}

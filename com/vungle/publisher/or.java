package com.vungle.publisher;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class or implements OnTouchListener {
    private final op f3220a;

    private or(op opVar) {
        this.f3220a = opVar;
    }

    public static OnTouchListener m4527a(op opVar) {
        return new or(opVar);
    }

    @Hidden
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f3220a.m4505a(view, motionEvent);
    }
}

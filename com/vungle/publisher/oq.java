package com.vungle.publisher;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class oq implements OnClickListener {
    private final op f3219a;

    private oq(op opVar) {
        this.f3219a = opVar;
    }

    public static OnClickListener m4526a(op opVar) {
        return new oq(opVar);
    }

    @Hidden
    public void onClick(View view) {
        this.f3219a.m4500a(view);
    }
}

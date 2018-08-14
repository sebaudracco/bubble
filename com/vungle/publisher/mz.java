package com.vungle.publisher;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class mz implements OnClickListener {
    private final my f3152a;

    private mz(my myVar) {
        this.f3152a = myVar;
    }

    public static OnClickListener m4420a(my myVar) {
        return new mz(myVar);
    }

    @Hidden
    public void onClick(View view) {
        this.f3152a.m4412a(view);
    }
}

package com.vungle.publisher;

import android.view.View.OnSystemUiVisibilityChangeListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class nj implements OnSystemUiVisibilityChangeListener {
    private final ni f3159a;
    private final p f3160b;

    private nj(ni niVar, p pVar) {
        this.f3159a = niVar;
        this.f3160b = pVar;
    }

    public static OnSystemUiVisibilityChangeListener m4428a(ni niVar, p pVar) {
        return new nj(niVar, pVar);
    }

    @Hidden
    public void onSystemUiVisibilityChange(int i) {
        this.f3159a.m4425a(this.f3160b, i);
    }
}

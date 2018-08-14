package com.vungle.publisher;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class nl implements OnApplyWindowInsetsListener {
    private final nk f3164a;
    private final View f3165b;

    private nl(nk nkVar, View view) {
        this.f3164a = nkVar;
        this.f3165b = view;
    }

    public static OnApplyWindowInsetsListener m4438a(nk nkVar, View view) {
        return new nl(nkVar, view);
    }

    @Hidden
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return this.f3164a.m4433a(this.f3165b, view, windowInsets);
    }
}

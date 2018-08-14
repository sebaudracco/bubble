package com.vungle.publisher;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ot implements OnClickListener {
    private final op f3224a;
    private final ImageView f3225b;

    private ot(op opVar, ImageView imageView) {
        this.f3224a = opVar;
        this.f3225b = imageView;
    }

    public static OnClickListener m4529a(op opVar, ImageView imageView) {
        return new ot(opVar, imageView);
    }

    @Hidden
    public void onClick(View view) {
        this.f3224a.m4501a(this.f3225b, view);
    }
}

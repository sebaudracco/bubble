package com.vungle.publisher;

import android.view.View;
import android.view.View.OnClickListener;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
class op$b implements OnClickListener {
    final /* synthetic */ op f10879a;

    op$b(op opVar) {
        this.f10879a = opVar;
    }

    public void onClick(View view) {
        Logger.m13644v(Logger.AD_TAG, "close clicked");
        op.a(this.f10879a, false);
    }
}

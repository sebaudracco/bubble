package com.vungle.publisher;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.vungle.publisher.mq.a;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class mt implements OnCancelListener {
    private final a f3135a;

    private mt(a aVar) {
        this.f3135a = aVar;
    }

    public static OnCancelListener m4394a(a aVar) {
        return new mt(aVar);
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        mq.m4388a(this.f3135a, dialogInterface);
    }
}

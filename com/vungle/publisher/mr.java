package com.vungle.publisher;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.vungle.publisher.mq.a;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class mr implements OnClickListener {
    private final a f3133a;

    private mr(a aVar) {
        this.f3133a = aVar;
    }

    public static OnClickListener m4392a(a aVar) {
        return new mr(aVar);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        mq.m4390b(this.f3133a, dialogInterface, i);
    }
}

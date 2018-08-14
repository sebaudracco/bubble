package com.vungle.publisher;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.vungle.publisher.mq.a;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ms implements OnClickListener {
    private final a f3134a;

    private ms(a aVar) {
        this.f3134a = aVar;
    }

    public static OnClickListener m4393a(a aVar) {
        return new ms(aVar);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        mq.m4389a(this.f3134a, dialogInterface, i);
    }
}

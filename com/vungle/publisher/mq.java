package com.vungle.publisher;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;
import com.vungle.publisher.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class mq {
    public AlertDialog m4391a(Context context, p pVar, a aVar) {
        Builder builder = new Builder(new ContextThemeWrapper(context, context.getApplicationInfo().theme));
        builder.setTitle(pVar.getIncentivizedCancelDialogTitle());
        builder.setMessage(pVar.getIncentivizedCancelDialogBodyText());
        builder.setPositiveButton(pVar.getIncentivizedCancelDialogKeepWatchingButtonText(), mr.m4392a(aVar));
        builder.setNegativeButton(pVar.getIncentivizedCancelDialogCloseButtonText(), ms.m4393a(aVar));
        builder.setOnCancelListener(mt.m4394a(aVar));
        return builder.create();
    }

    static /* synthetic */ void m4390b(a aVar, DialogInterface dialogInterface, int i) {
        Logger.d(Logger.AD_TAG, "positive click");
        aVar.a();
    }

    static /* synthetic */ void m4389a(a aVar, DialogInterface dialogInterface, int i) {
        Logger.d(Logger.AD_TAG, "negative click");
        aVar.b();
    }

    static /* synthetic */ void m4388a(a aVar, DialogInterface dialogInterface) {
        Logger.d(Logger.AD_TAG, "cancel click");
        aVar.c();
    }
}

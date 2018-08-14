package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

final class zzaqp implements OnCancelListener {
    private final /* synthetic */ JsResult zzdbk;

    zzaqp(JsResult jsResult) {
        this.zzdbk = jsResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.zzdbk.cancel();
    }
}

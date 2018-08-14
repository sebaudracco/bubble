package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class zzaqr implements OnClickListener {
    private final /* synthetic */ JsResult zzdbk;

    zzaqr(JsResult jsResult) {
        this.zzdbk = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdbk.confirm();
    }
}

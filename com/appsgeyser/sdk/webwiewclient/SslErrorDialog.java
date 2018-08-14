package com.appsgeyser.sdk.webwiewclient;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.appsgeyser.sdk.configuration.Constants;

class SslErrorDialog {
    private final Context activity;

    SslErrorDialog(Context activity) {
        this.activity = activity;
    }

    @TargetApi(14)
    void execute(WebView view, final SslErrorHandler handler, SslError error) {
        if (VERSION.SDK_INT < 14 || error.getUrl().equals(view.getUrl())) {
            Builder builder = new Builder(this.activity);
            builder.setMessage(Constants.SSL_ERROR_DIALOG_MESSAGE).setTitle(Constants.SSL_ERROR_DIALOG_TITLE).setPositiveButton(Constants.SSL_ERROR_DIALOG_BUTTON_POSITIVE, new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    handler.proceed();
                }
            }).setNegativeButton(Constants.SSL_ERROR_DIALOG_BUTTON_NEGATIVE, new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    handler.cancel();
                }
            });
            builder.create().show();
            return;
        }
        handler.proceed();
    }
}

package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.wBubble_7453037.R;
import cz.msebera.android.httpclient.protocol.HTTP;

public class ShareBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String url = intent.getDataString();
        if (url != null) {
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.setType(HTTP.PLAIN_TEXT_TYPE);
            shareIntent.putExtra("android.intent.extra.TEXT", url);
            Intent chooserIntent = Intent.createChooser(shareIntent, LocaleController.getString("ShareLink", R.string.ShareLink));
            chooserIntent.setFlags(ErrorDialogData.BINDER_CRASH);
            context.startActivity(chooserIntent);
        }
    }
}

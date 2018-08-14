package com.fyber.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* compiled from: IntentHelper */
public final class C2663k {
    public static boolean m8519a(Context context, String str, Uri uri) {
        Intent intent = new Intent(str);
        if (uri != null) {
            intent.setData(uri);
        }
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}

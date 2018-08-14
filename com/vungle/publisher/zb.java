package com.vungle.publisher;

import android.text.TextUtils;

/* compiled from: vungle */
public class zb {
    public static String m14191a(Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(th.getClass().getName());
        Object message = th.getMessage();
        if (!TextUtils.isEmpty(message)) {
            stringBuilder.append(':').append(' ').append(message);
        }
        return stringBuilder.toString();
    }
}

package com.integralads.avid.library.inmobi.p126f;

import android.text.TextUtils;
import android.util.Log;
import com.mopub.mobileads.VastExtensionXmlManager;

/* compiled from: AvidLogs */
public class C3312a {
    public static void m11278a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.e(VastExtensionXmlManager.AVID, str);
        }
    }

    public static void m11279a(String str, Exception exception) {
        if (!TextUtils.isEmpty(str) || exception != null) {
            Log.e(VastExtensionXmlManager.AVID, str, exception);
        }
    }
}

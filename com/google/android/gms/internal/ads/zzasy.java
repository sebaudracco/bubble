package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@zzadh
final class zzasy {
    @GuardedBy("InvokeJavascriptWorkaround.class")
    @VisibleForTesting
    private static Boolean zzdfk;

    private zzasy() {
    }

    @TargetApi(19)
    static void zza(WebView webView, String str) {
        if (PlatformVersion.isAtLeastKitKat() && zzb(webView)) {
            webView.evaluateJavascript(str, null);
            return;
        }
        String str2 = BridgeUtil.JAVASCRIPT_STR;
        String valueOf = String.valueOf(str);
        webView.loadUrl(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    @TargetApi(19)
    private static boolean zzb(WebView webView) {
        boolean booleanValue;
        synchronized (zzasy.class) {
            if (zzdfk == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", null);
                    zzdfk = Boolean.valueOf(true);
                } catch (IllegalStateException e) {
                    zzdfk = Boolean.valueOf(false);
                }
            }
            booleanValue = zzdfk.booleanValue();
        }
        return booleanValue;
    }
}

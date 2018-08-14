package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbv;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@zzadh
@TargetApi(11)
public class zzaru extends zzaqx {
    public zzaru(zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z) {
        super(com_google_android_gms_internal_ads_zzaqw, z);
    }

    @Nullable
    protected final WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        if (webView instanceof zzaqw) {
            zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) webView;
            if (this.zzxd != null) {
                this.zzxd.zza(str, map, 1);
            }
            if ("mraid.js".equalsIgnoreCase(new File(str).getName())) {
                String str2;
                if (com_google_android_gms_internal_ads_zzaqw.zzuf() != null) {
                    com_google_android_gms_internal_ads_zzaqw.zzuf().zznk();
                }
                if (com_google_android_gms_internal_ads_zzaqw.zzud().zzvs()) {
                    str2 = (String) zzkb.zzik().zzd(zznk.zzawe);
                } else if (com_google_android_gms_internal_ads_zzaqw.zzuj()) {
                    str2 = (String) zzkb.zzik().zzd(zznk.zzawd);
                } else {
                    str2 = (String) zzkb.zzik().zzd(zznk.zzawc);
                }
                zzbv.zzek();
                return zzakk.zzf(com_google_android_gms_internal_ads_zzaqw.getContext(), com_google_android_gms_internal_ads_zzaqw.zztq().zzcw, str2);
            }
            Map emptyMap;
            if (map == null) {
                emptyMap = Collections.emptyMap();
            }
            return super.zzd(str, emptyMap);
        }
        zzane.zzdk("Tried to intercept request from a WebView that wasn't an AdWebView.");
        return null;
    }
}

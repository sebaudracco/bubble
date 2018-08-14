package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import java.util.Set;

@TargetApi(11)
public class zzakt extends zzaks {
    public zzaqx zza(zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z) {
        return new zzart(com_google_android_gms_internal_ads_zzaqw, z);
    }

    public final boolean zza(Request request) {
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        return true;
    }

    public boolean zza(Context context, WebSettings webSettings) {
        super.zza(context, webSettings);
        return ((Boolean) zzaml.zza(context, new zzaku(this, context, webSettings))).booleanValue();
    }

    public final boolean zza(Window window) {
        window.setFlags(16777216, 16777216);
        return true;
    }

    public final Set<String> zzh(Uri uri) {
        return uri.getQueryParameterNames();
    }

    public final boolean zzy(View view) {
        view.setLayerType(0, null);
        return true;
    }

    public final boolean zzz(View view) {
        view.setLayerType(1, null);
        return true;
    }
}

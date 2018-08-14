package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzpg implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    final /* synthetic */ zzpf zzbjj;

    zzpg(zzpf com_google_android_gms_internal_ads_zzpf, zzacm com_google_android_gms_internal_ads_zzacm) {
        this.zzbjj = com_google_android_gms_internal_ads_zzpf;
        this.zzbji = com_google_android_gms_internal_ads_zzacm;
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) this.zzbjj.zzbjg.get();
        if (com_google_android_gms_internal_ads_zzaqw == null) {
            this.zzbji.zzb("/loadHtml", this);
            return;
        }
        com_google_android_gms_internal_ads_zzaqw.zzuf().zza(new zzph(this, map, this.zzbji));
        String str = (String) map.get("overlayHtml");
        String str2 = (String) map.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
            com_google_android_gms_internal_ads_zzaqw.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
        } else {
            com_google_android_gms_internal_ads_zzaqw.loadDataWithBaseURL(str2, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        }
    }
}

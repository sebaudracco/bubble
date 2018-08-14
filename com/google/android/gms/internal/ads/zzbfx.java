package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public final class zzbfx extends CustomTabsServiceConnection {
    private WeakReference<zzbfy> zzedz;

    public zzbfx(zzbfy com_google_android_gms_internal_ads_zzbfy) {
        this.zzedz = new WeakReference(com_google_android_gms_internal_ads_zzbfy);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbfy com_google_android_gms_internal_ads_zzbfy = (zzbfy) this.zzedz.get();
        if (com_google_android_gms_internal_ads_zzbfy != null) {
            com_google_android_gms_internal_ads_zzbfy.zza(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbfy com_google_android_gms_internal_ads_zzbfy = (zzbfy) this.zzedz.get();
        if (com_google_android_gms_internal_ads_zzbfy != null) {
            com_google_android_gms_internal_ads_zzbfy.zzjo();
        }
    }
}

package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzajf implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzaoj zzcoa;

    zzajf(zzaje com_google_android_gms_internal_ads_zzaje, Context context, zzaoj com_google_android_gms_internal_ads_zzaoj) {
        this.val$context = context;
        this.zzcoa = com_google_android_gms_internal_ads_zzaoj;
    }

    public final void run() {
        Throwable e;
        try {
            this.zzcoa.set(AdvertisingIdClient.getAdvertisingIdInfo(this.val$context));
            return;
        } catch (IOException e2) {
            e = e2;
        } catch (IllegalStateException e3) {
            e = e3;
        } catch (GooglePlayServicesNotAvailableException e4) {
            e = e4;
        } catch (GooglePlayServicesRepairableException e5) {
            e = e5;
        }
        this.zzcoa.setException(e);
        zzane.zzb("Exception while getting advertising Id info", e);
    }
}

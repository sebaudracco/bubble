package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzjw extends zzjr$zza<zzlj> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjr zzart;

    zzjw(zzjr com_google_android_gms_internal_ads_zzjr, Context context) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.val$context = context;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.getMobileAdsSettingsManagerWithClientJarVersion(ObjectWrapper.wrap(this.val$context), 12451000);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzlj zzg = zzjr.zzd(this.zzart).zzg(this.val$context);
        if (zzg != null) {
            return zzg;
        }
        zzjr.zza(this.zzart, this.val$context, "mobile_ads_settings");
        return new zzml();
    }
}

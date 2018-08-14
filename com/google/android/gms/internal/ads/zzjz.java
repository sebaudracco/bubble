package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.mopub.common.AdType;

final class zzjz extends zzjr$zza<zzagz> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzxn zzars;
    private final /* synthetic */ zzjr zzart;

    zzjz(zzjr com_google_android_gms_internal_ads_zzjr, Context context, zzxn com_google_android_gms_internal_ads_zzxn) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.val$context = context;
        this.zzars = com_google_android_gms_internal_ads_zzxn;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.createRewardedVideoAd(ObjectWrapper.wrap(this.val$context), this.zzars, 12451000);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzagz zza = zzjr.zzg(this.zzart).zza(this.val$context, this.zzars);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.zzart, this.val$context, AdType.REWARDED_VIDEO);
        return new zzmo();
    }
}

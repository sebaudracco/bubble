package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzjv extends zzjr$zza<zzkn> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzxn zzars;
    private final /* synthetic */ zzjr zzart;

    zzjv(zzjr com_google_android_gms_internal_ads_zzjr, Context context, String str, zzxn com_google_android_gms_internal_ads_zzxn) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.val$context = context;
        this.zzarr = str;
        this.zzars = com_google_android_gms_internal_ads_zzxn;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.createAdLoaderBuilder(ObjectWrapper.wrap(this.val$context), this.zzarr, this.zzars, 12451000);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzkn zza = zzjr.zzc(this.zzart).zza(this.val$context, this.zzarr, this.zzars);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.zzart, this.val$context, "native_ad");
        return new zzmf();
    }
}

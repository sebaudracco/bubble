package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzka extends zzjr$zza<zzaap> {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzjr zzart;

    zzka(zzjr com_google_android_gms_internal_ads_zzjr, Activity activity) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.val$activity = activity;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.createAdOverlay(ObjectWrapper.wrap(this.val$activity));
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzaap zze = zzjr.zzh(this.zzart).zze(this.val$activity);
        if (zze != null) {
            return zze;
        }
        zzjr.zza(this.zzart, this.val$activity, "ad_overlay");
        return null;
    }
}

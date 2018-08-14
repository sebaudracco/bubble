package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;

final class zzjy extends zzjr$zza<zzqf> {
    private final /* synthetic */ zzjr zzart;
    private final /* synthetic */ View zzarw;
    private final /* synthetic */ HashMap zzarx;
    private final /* synthetic */ HashMap zzary;

    zzjy(zzjr com_google_android_gms_internal_ads_zzjr, View view, HashMap hashMap, HashMap hashMap2) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.zzarw = view;
        this.zzarx = hashMap;
        this.zzary = hashMap2;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.createNativeAdViewHolderDelegate(ObjectWrapper.wrap(this.zzarw), ObjectWrapper.wrap(this.zzarx), ObjectWrapper.wrap(this.zzary));
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzqf zzb = zzjr.zzf(this.zzart).zzb(this.zzarw, this.zzarx, this.zzary);
        if (zzb != null) {
            return zzb;
        }
        zzjr.zza(this.zzart, this.zzarw.getContext(), "native_ad_view_holder_delegate");
        return new zzmn();
    }
}

package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzjx extends zzjr$zza<zzqa> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjr zzart;
    private final /* synthetic */ FrameLayout zzaru;
    private final /* synthetic */ FrameLayout zzarv;

    zzjx(zzjr com_google_android_gms_internal_ads_zzjr, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.zzaru = frameLayout;
        this.zzarv = frameLayout2;
        this.val$context = context;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.createNativeAdViewDelegate(ObjectWrapper.wrap(this.zzaru), ObjectWrapper.wrap(this.zzarv));
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzqa zzb = zzjr.zze(this.zzart).zzb(this.val$context, this.zzaru, this.zzarv);
        if (zzb != null) {
            return zzb;
        }
        zzjr.zza(this.zzart, this.val$context, "native_ad_view_delegate");
        return new zzmm();
    }
}

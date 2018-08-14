package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.analytics.FirebaseAnalytics.Event;

final class zzjt extends zzjr$zza<zzks> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjn zzarq;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzjr zzart;

    zzjt(zzjr com_google_android_gms_internal_ads_zzjr, Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str) {
        this.zzart = com_google_android_gms_internal_ads_zzjr;
        this.val$context = context;
        this.zzarq = com_google_android_gms_internal_ads_zzjn;
        this.zzarr = str;
        super(com_google_android_gms_internal_ads_zzjr);
    }

    public final /* synthetic */ Object zza(zzld com_google_android_gms_internal_ads_zzld) throws RemoteException {
        return com_google_android_gms_internal_ads_zzld.createSearchAdManager(ObjectWrapper.wrap(this.val$context), this.zzarq, this.zzarr, 12451000);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzks zza = zzjr.zzb(this.zzart).zza(this.val$context, this.zzarq, this.zzarr, null, 3);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.zzart, this.val$context, Event.SEARCH);
        return new zzmj();
    }
}

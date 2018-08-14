package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;

final class zzahp implements Runnable {
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzahv zzclw;
    private final /* synthetic */ zzjj zzyh;

    zzahp(zzahn com_google_android_gms_internal_ads_zzahn, zzxq com_google_android_gms_internal_ads_zzxq, zzjj com_google_android_gms_internal_ads_zzjj, zzahv com_google_android_gms_internal_ads_zzahv) {
        this.zzclv = com_google_android_gms_internal_ads_zzahn;
        this.zzclu = com_google_android_gms_internal_ads_zzxq;
        this.zzyh = com_google_android_gms_internal_ads_zzjj;
        this.zzclw = com_google_android_gms_internal_ads_zzahv;
    }

    public final void run() {
        try {
            this.zzclu.zza(ObjectWrapper.wrap(zzahn.zza(this.zzclv)), this.zzyh, null, this.zzclw, zzahn.zzb(this.zzclv));
        } catch (Throwable e) {
            Throwable th = e;
            String str = "Fail to initialize adapter ";
            String valueOf = String.valueOf(this.zzclv.zzbth);
            zzakb.zzc(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            this.zzclv.zza(this.zzclv.zzbth, 0);
        }
    }
}

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;

final class zzbl implements BaseGmsClient$SignOutCallbacks {
    final /* synthetic */ zza zzkk;

    zzbl(zza com_google_android_gms_common_api_internal_GoogleApiManager_zza) {
        this.zzkk = com_google_android_gms_common_api_internal_GoogleApiManager_zza;
    }

    public final void onSignOutComplete() {
        GoogleApiManager.zza(this.zzkk.zzjy).post(new zzbm(this));
    }
}

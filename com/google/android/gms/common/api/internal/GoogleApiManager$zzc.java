package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

class GoogleApiManager$zzc implements zzcb, BaseGmsClient$ConnectionProgressReportCallbacks {
    private final zzh<?> zzhc;
    final /* synthetic */ GoogleApiManager zzjy;
    private final Client zzka;
    private IAccountAccessor zzko = null;
    private Set<Scope> zzkp = null;
    private boolean zzkq = false;

    public GoogleApiManager$zzc(GoogleApiManager googleApiManager, Client client, zzh<?> com_google_android_gms_common_api_internal_zzh_) {
        this.zzjy = googleApiManager;
        this.zzka = client;
        this.zzhc = com_google_android_gms_common_api_internal_zzh_;
    }

    @WorkerThread
    private final void zzbu() {
        if (this.zzkq && this.zzko != null) {
            this.zzka.getRemoteService(this.zzko, this.zzkp);
        }
    }

    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        GoogleApiManager.zza(this.zzjy).post(new zzbn(this, connectionResult));
    }

    @WorkerThread
    public final void zza(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        if (iAccountAccessor == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzg(new ConnectionResult(4));
            return;
        }
        this.zzko = iAccountAccessor;
        this.zzkp = set;
        zzbu();
    }

    @WorkerThread
    public final void zzg(ConnectionResult connectionResult) {
        ((zza) GoogleApiManager.zzj(this.zzjy).get(this.zzhc)).zzg(connectionResult);
    }
}

package com.google.android.gms.internal.clearcut;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

final class zzh extends ApiMethodImpl<Status, zzj> {
    private final zze zzao;

    zzh(zze com_google_android_gms_clearcut_zze, GoogleApiClient googleApiClient) {
        super(ClearcutLogger.API, googleApiClient);
        this.zzao = com_google_android_gms_clearcut_zze;
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzj com_google_android_gms_internal_clearcut_zzj = (zzj) anyClient;
        zzl com_google_android_gms_internal_clearcut_zzi = new zzi(this);
        try {
            zze com_google_android_gms_clearcut_zze = this.zzao;
            if (com_google_android_gms_clearcut_zze.zzt != null && com_google_android_gms_clearcut_zze.zzaa.zzbjp.length == 0) {
                com_google_android_gms_clearcut_zze.zzaa.zzbjp = com_google_android_gms_clearcut_zze.zzt.zza();
            }
            if (com_google_android_gms_clearcut_zze.zzan != null && com_google_android_gms_clearcut_zze.zzaa.zzbjw.length == 0) {
                com_google_android_gms_clearcut_zze.zzaa.zzbjw = com_google_android_gms_clearcut_zze.zzan.zza();
            }
            zzfz com_google_android_gms_internal_clearcut_zzfz = com_google_android_gms_clearcut_zze.zzaa;
            byte[] bArr = new byte[com_google_android_gms_internal_clearcut_zzfz.zzas()];
            zzfz.zza(com_google_android_gms_internal_clearcut_zzfz, bArr, 0, bArr.length);
            com_google_android_gms_clearcut_zze.zzah = bArr;
            ((zzn) com_google_android_gms_internal_clearcut_zzj.getService()).zza(com_google_android_gms_internal_clearcut_zzi, this.zzao);
        } catch (Throwable e) {
            Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
            setFailedResult(new Status(10, "MessageProducer"));
        }
    }
}

package com.google.android.gms.clearcut;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.clearcut.zzaa;
import com.google.android.gms.internal.clearcut.zzge.zzv.zzb;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import java.util.TimeZone;

public class ClearcutLogger$LogEventBuilder {
    private final zzha zzaa;
    private boolean zzab;
    private final /* synthetic */ ClearcutLogger zzac;
    private String zzj;
    private int zzk;
    private String zzl;
    private String zzm;
    private zzb zzo;
    private final ClearcutLogger$zzb zzt;
    private ArrayList<Integer> zzu;
    private ArrayList<String> zzv;
    private ArrayList<Integer> zzw;
    private ArrayList<ExperimentTokens> zzx;
    private ArrayList<byte[]> zzy;
    private boolean zzz;

    private ClearcutLogger$LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr) {
        this(clearcutLogger, bArr, null);
    }

    private ClearcutLogger$LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr, ClearcutLogger$zzb clearcutLogger$zzb) {
        this.zzac = clearcutLogger;
        this.zzk = ClearcutLogger.zza(this.zzac);
        this.zzj = ClearcutLogger.zzb(this.zzac);
        this.zzl = ClearcutLogger.zzc(this.zzac);
        ClearcutLogger clearcutLogger2 = this.zzac;
        this.zzm = null;
        this.zzo = ClearcutLogger.zzd(this.zzac);
        this.zzu = null;
        this.zzv = null;
        this.zzw = null;
        this.zzx = null;
        this.zzy = null;
        this.zzz = true;
        this.zzaa = new zzha();
        this.zzab = false;
        this.zzl = ClearcutLogger.zzc(clearcutLogger);
        this.zzm = null;
        this.zzaa.zzbkc = zzaa.zze(ClearcutLogger.zze(clearcutLogger));
        this.zzaa.zzbjf = ClearcutLogger.zzf(clearcutLogger).currentTimeMillis();
        this.zzaa.zzbjg = ClearcutLogger.zzf(clearcutLogger).elapsedRealtime();
        zzha com_google_android_gms_internal_clearcut_zzha = this.zzaa;
        ClearcutLogger.zzg(clearcutLogger);
        com_google_android_gms_internal_clearcut_zzha.zzbju = (long) (TimeZone.getDefault().getOffset(this.zzaa.zzbjf) / 1000);
        if (bArr != null) {
            this.zzaa.zzbjp = bArr;
        }
        this.zzt = null;
    }

    @KeepForSdk
    public void log() {
        if (this.zzab) {
            throw new IllegalStateException("do not reuse LogEventBuilder");
        }
        this.zzab = true;
        zze com_google_android_gms_clearcut_zze = new zze(new zzr(ClearcutLogger.zzi(this.zzac), ClearcutLogger.zzj(this.zzac), this.zzk, this.zzj, this.zzl, this.zzm, ClearcutLogger.zzh(this.zzac), this.zzo), this.zzaa, null, null, ClearcutLogger.zzb(null), null, ClearcutLogger.zzb(null), null, null, this.zzz);
        if (ClearcutLogger.zzk(this.zzac).zza(com_google_android_gms_clearcut_zze)) {
            ClearcutLogger.zzl(this.zzac).zzb(com_google_android_gms_clearcut_zze);
        } else {
            PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, null);
        }
    }

    @KeepForSdk
    public ClearcutLogger$LogEventBuilder setEventCode(int i) {
        this.zzaa.zzbji = i;
        return this;
    }
}

package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzr<T> implements Comparable<zzr<T>> {
    private final Object mLock;
    private final zza zzae;
    private final int zzaf;
    private final String zzag;
    private final int zzah;
    private zzy zzai;
    private Integer zzaj;
    private zzv zzak;
    private boolean zzal;
    private boolean zzam;
    private boolean zzan;
    private boolean zzao;
    private zzab zzap;
    private zzc zzaq;
    private zzt zzar;

    public zzr(int i, String str, zzy com_google_android_gms_internal_ads_zzy) {
        int hashCode;
        this.zzae = zza.zzbk ? new zza() : null;
        this.mLock = new Object();
        this.zzal = true;
        this.zzam = false;
        this.zzan = false;
        this.zzao = false;
        this.zzaq = null;
        this.zzaf = i;
        this.zzag = str;
        this.zzai = com_google_android_gms_internal_ads_zzy;
        this.zzap = new zzh();
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    hashCode = host.hashCode();
                    this.zzah = hashCode;
                }
            }
        }
        hashCode = 0;
        this.zzah = hashCode;
    }

    public /* synthetic */ int compareTo(Object obj) {
        zzr com_google_android_gms_internal_ads_zzr = (zzr) obj;
        zzu com_google_android_gms_internal_ads_zzu = zzu.NORMAL;
        zzu com_google_android_gms_internal_ads_zzu2 = zzu.NORMAL;
        return com_google_android_gms_internal_ads_zzu == com_google_android_gms_internal_ads_zzu2 ? this.zzaj.intValue() - com_google_android_gms_internal_ads_zzr.zzaj.intValue() : com_google_android_gms_internal_ads_zzu2.ordinal() - com_google_android_gms_internal_ads_zzu.ordinal();
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public final int getMethod() {
        return this.zzaf;
    }

    public final String getUrl() {
        return this.zzag;
    }

    public final boolean isCanceled() {
        synchronized (this.mLock) {
        }
        return false;
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(this.zzah));
        valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        str = "[ ] ";
        String str2 = this.zzag;
        String valueOf2 = String.valueOf(zzu.NORMAL);
        String valueOf3 = String.valueOf(this.zzaj);
        return new StringBuilder(((((String.valueOf(str).length() + 3) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append(str).append(str2).append(" ").append(valueOf).append(" ").append(valueOf2).append(" ").append(valueOf3).toString();
    }

    public final zzr<?> zza(int i) {
        this.zzaj = Integer.valueOf(i);
        return this;
    }

    public final zzr<?> zza(zzc com_google_android_gms_internal_ads_zzc) {
        this.zzaq = com_google_android_gms_internal_ads_zzc;
        return this;
    }

    public final zzr<?> zza(zzv com_google_android_gms_internal_ads_zzv) {
        this.zzak = com_google_android_gms_internal_ads_zzv;
        return this;
    }

    protected abstract zzx<T> zza(zzp com_google_android_gms_internal_ads_zzp);

    final void zza(zzt com_google_android_gms_internal_ads_zzt) {
        synchronized (this.mLock) {
            this.zzar = com_google_android_gms_internal_ads_zzt;
        }
    }

    final void zza(zzx<?> com_google_android_gms_internal_ads_zzx_) {
        synchronized (this.mLock) {
            zzt com_google_android_gms_internal_ads_zzt = this.zzar;
        }
        if (com_google_android_gms_internal_ads_zzt != null) {
            com_google_android_gms_internal_ads_zzt.zza(this, com_google_android_gms_internal_ads_zzx_);
        }
    }

    protected abstract void zza(T t);

    public final void zzb(zzae com_google_android_gms_internal_ads_zzae) {
        synchronized (this.mLock) {
            zzy com_google_android_gms_internal_ads_zzy = this.zzai;
        }
        if (com_google_android_gms_internal_ads_zzy != null) {
            com_google_android_gms_internal_ads_zzy.zzd(com_google_android_gms_internal_ads_zzae);
        }
    }

    public final void zzb(String str) {
        if (zza.zzbk) {
            this.zzae.zza(str, Thread.currentThread().getId());
        }
    }

    final void zzc(String str) {
        if (this.zzak != null) {
            this.zzak.zzf(this);
        }
        if (zza.zzbk) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzs(this, str, id));
                return;
            }
            this.zzae.zza(str, id);
            this.zzae.zzc(toString());
        }
    }

    public final int zze() {
        return this.zzah;
    }

    public final zzc zzf() {
        return this.zzaq;
    }

    public byte[] zzg() throws zza {
        return null;
    }

    public final boolean zzh() {
        return this.zzal;
    }

    public final int zzi() {
        return this.zzap.zzc();
    }

    public final zzab zzj() {
        return this.zzap;
    }

    public final void zzk() {
        synchronized (this.mLock) {
            this.zzan = true;
        }
    }

    public final boolean zzl() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzan;
        }
        return z;
    }

    final void zzm() {
        synchronized (this.mLock) {
            zzt com_google_android_gms_internal_ads_zzt = this.zzar;
        }
        if (com_google_android_gms_internal_ads_zzt != null) {
            com_google_android_gms_internal_ads_zzt.zza(this);
        }
    }
}

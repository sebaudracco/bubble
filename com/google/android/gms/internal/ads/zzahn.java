package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Future;

@zzadh
public final class zzahn extends zzajx implements zzaht, zzahw, zzaia {
    private final Context mContext;
    private int mErrorCode = 3;
    private final Object mLock;
    public final String zzbth;
    private final zzaji zzbze;
    private final zzaib zzcll;
    private final zzahw zzclm;
    private final String zzcln;
    private final zzwx zzclo;
    private final long zzclp;
    private int zzclq = 0;
    private zzahq zzclr;
    private Future zzcls;
    private volatile zzb zzclt;

    public zzahn(Context context, String str, String str2, zzwx com_google_android_gms_internal_ads_zzwx, zzaji com_google_android_gms_internal_ads_zzaji, zzaib com_google_android_gms_internal_ads_zzaib, zzahw com_google_android_gms_internal_ads_zzahw, long j) {
        this.mContext = context;
        this.zzbth = str;
        this.zzcln = str2;
        this.zzclo = com_google_android_gms_internal_ads_zzwx;
        this.zzbze = com_google_android_gms_internal_ads_zzaji;
        this.zzcll = com_google_android_gms_internal_ads_zzaib;
        this.mLock = new Object();
        this.zzclm = com_google_android_gms_internal_ads_zzahw;
        this.zzclp = j;
    }

    private final void zza(zzjj com_google_android_gms_internal_ads_zzjj, zzxq com_google_android_gms_internal_ads_zzxq) {
        this.zzcll.zzpf().zza((zzahw) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                com_google_android_gms_internal_ads_zzxq.zza(com_google_android_gms_internal_ads_zzjj, this.zzcln, this.zzclo.zzbrr);
            } else {
                com_google_android_gms_internal_ads_zzxq.zzc(com_google_android_gms_internal_ads_zzjj, this.zzcln);
            }
        } catch (Throwable e) {
            zzane.zzc("Fail to load ad from adapter.", e);
            zza(this.zzbth, 0);
        }
    }

    private final boolean zzf(long j) {
        long elapsedRealtime = this.zzclp - (zzbv.zzer().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.mErrorCode = 4;
            return false;
        }
        try {
            this.mLock.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.mErrorCode = 5;
            return false;
        }
    }

    public final void onStop() {
    }

    public final void zza(zzb com_google_android_gms_ads_internal_gmsg_zzb) {
        this.zzclt = com_google_android_gms_ads_internal_gmsg_zzb;
    }

    public final void zza(String str, int i) {
        synchronized (this.mLock) {
            this.zzclq = 2;
            this.mErrorCode = i;
            this.mLock.notify();
        }
    }

    public final void zzac(int i) {
        zza(this.zzbth, 0);
    }

    public final void zzc(Bundle bundle) {
        zzb com_google_android_gms_ads_internal_gmsg_zzb = this.zzclt;
        if (com_google_android_gms_ads_internal_gmsg_zzb != null) {
            com_google_android_gms_ads_internal_gmsg_zzb.zza("", bundle);
        }
    }

    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzclq = 1;
            this.mLock.notify();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdn() {
        /*
        r10 = this;
        r9 = 1;
        r8 = 0;
        r0 = r10.zzcll;
        if (r0 == 0) goto L_0x0016;
    L_0x0006:
        r0 = r10.zzcll;
        r0 = r0.zzpf();
        if (r0 == 0) goto L_0x0016;
    L_0x000e:
        r0 = r10.zzcll;
        r0 = r0.zzpe();
        if (r0 != 0) goto L_0x0017;
    L_0x0016:
        return;
    L_0x0017:
        r0 = r10.zzcll;
        r1 = r0.zzpf();
        r1.zza(r8);
        r1.zza(r10);
        r1.zza(r10);
        r0 = r10.zzbze;
        r0 = r0.zzcgs;
        r0 = r0.zzccv;
        r2 = r10.zzcll;
        r2 = r2.zzpe();
        r3 = r2.isInitialized();	 Catch:{ RemoteException -> 0x009f }
        if (r3 == 0) goto L_0x0094;
    L_0x0038:
        r3 = com.google.android.gms.internal.ads.zzamu.zzsy;	 Catch:{ RemoteException -> 0x009f }
        r4 = new com.google.android.gms.internal.ads.zzaho;	 Catch:{ RemoteException -> 0x009f }
        r4.<init>(r10, r0, r2);	 Catch:{ RemoteException -> 0x009f }
        r3.post(r4);	 Catch:{ RemoteException -> 0x009f }
    L_0x0042:
        r0 = com.google.android.gms.ads.internal.zzbv.zzer();
        r2 = r0.elapsedRealtime();
    L_0x004a:
        r4 = r10.mLock;
        monitor-enter(r4);
        r0 = r10.zzclq;	 Catch:{ all -> 0x00e5 }
        if (r0 == 0) goto L_0x00b0;
    L_0x0051:
        r0 = new com.google.android.gms.internal.ads.zzahs;	 Catch:{ all -> 0x00e5 }
        r0.<init>();	 Catch:{ all -> 0x00e5 }
        r5 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ all -> 0x00e5 }
        r6 = r5.elapsedRealtime();	 Catch:{ all -> 0x00e5 }
        r2 = r6 - r2;
        r2 = r0.zzg(r2);	 Catch:{ all -> 0x00e5 }
        r0 = r10.zzclq;	 Catch:{ all -> 0x00e5 }
        if (r9 != r0) goto L_0x00ad;
    L_0x0068:
        r0 = 6;
    L_0x0069:
        r0 = r2.zzad(r0);	 Catch:{ all -> 0x00e5 }
        r2 = r10.zzbth;	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzcc(r2);	 Catch:{ all -> 0x00e5 }
        r2 = r10.zzclo;	 Catch:{ all -> 0x00e5 }
        r2 = r2.zzbru;	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzcd(r2);	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzpd();	 Catch:{ all -> 0x00e5 }
        r10.zzclr = r0;	 Catch:{ all -> 0x00e5 }
        monitor-exit(r4);	 Catch:{ all -> 0x00e5 }
    L_0x0082:
        r1.zza(r8);
        r1.zza(r8);
        r0 = r10.zzclq;
        if (r0 != r9) goto L_0x00eb;
    L_0x008c:
        r0 = r10.zzclm;
        r1 = r10.zzbth;
        r0.zzcb(r1);
        goto L_0x0016;
    L_0x0094:
        r3 = com.google.android.gms.internal.ads.zzamu.zzsy;	 Catch:{ RemoteException -> 0x009f }
        r4 = new com.google.android.gms.internal.ads.zzahp;	 Catch:{ RemoteException -> 0x009f }
        r4.<init>(r10, r2, r0, r1);	 Catch:{ RemoteException -> 0x009f }
        r3.post(r4);	 Catch:{ RemoteException -> 0x009f }
        goto L_0x0042;
    L_0x009f:
        r0 = move-exception;
        r2 = "Fail to check if adapter is initialized.";
        com.google.android.gms.internal.ads.zzane.zzc(r2, r0);
        r0 = r10.zzbth;
        r2 = 0;
        r10.zza(r0, r2);
        goto L_0x0042;
    L_0x00ad:
        r0 = r10.mErrorCode;	 Catch:{ all -> 0x00e5 }
        goto L_0x0069;
    L_0x00b0:
        r0 = r10.zzf(r2);	 Catch:{ all -> 0x00e5 }
        if (r0 != 0) goto L_0x00e8;
    L_0x00b6:
        r0 = new com.google.android.gms.internal.ads.zzahs;	 Catch:{ all -> 0x00e5 }
        r0.<init>();	 Catch:{ all -> 0x00e5 }
        r5 = r10.mErrorCode;	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzad(r5);	 Catch:{ all -> 0x00e5 }
        r5 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ all -> 0x00e5 }
        r6 = r5.elapsedRealtime();	 Catch:{ all -> 0x00e5 }
        r2 = r6 - r2;
        r0 = r0.zzg(r2);	 Catch:{ all -> 0x00e5 }
        r2 = r10.zzbth;	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzcc(r2);	 Catch:{ all -> 0x00e5 }
        r2 = r10.zzclo;	 Catch:{ all -> 0x00e5 }
        r2 = r2.zzbru;	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzcd(r2);	 Catch:{ all -> 0x00e5 }
        r0 = r0.zzpd();	 Catch:{ all -> 0x00e5 }
        r10.zzclr = r0;	 Catch:{ all -> 0x00e5 }
        monitor-exit(r4);	 Catch:{ all -> 0x00e5 }
        goto L_0x0082;
    L_0x00e5:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x00e5 }
        throw r0;
    L_0x00e8:
        monitor-exit(r4);	 Catch:{ all -> 0x00e5 }
        goto L_0x004a;
    L_0x00eb:
        r0 = r10.zzclm;
        r1 = r10.zzbth;
        r2 = r10.mErrorCode;
        r0.zza(r1, r2);
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahn.zzdn():void");
    }

    public final Future zzoz() {
        if (this.zzcls != null) {
            return this.zzcls;
        }
        zzanz com_google_android_gms_internal_ads_zzanz = (zzanz) zznt();
        this.zzcls = com_google_android_gms_internal_ads_zzanz;
        return com_google_android_gms_internal_ads_zzanz;
    }

    public final zzahq zzpa() {
        zzahq com_google_android_gms_internal_ads_zzahq;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzahq = this.zzclr;
        }
        return com_google_android_gms_internal_ads_zzahq;
    }

    public final zzwx zzpb() {
        return this.zzclo;
    }

    public final void zzpc() {
        zza(this.zzbze.zzcgs.zzccv, this.zzcll.zzpe());
    }
}

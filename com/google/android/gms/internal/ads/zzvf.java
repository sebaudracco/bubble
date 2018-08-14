package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzvf {
    private final Context mContext;
    private final Object mLock;
    private final String zzbpx;
    private zzalo<zzuu> zzbpy;
    private zzalo<zzuu> zzbpz;
    @Nullable
    private zzvw zzbqa;
    private int zzbqb;
    private final zzang zzyf;

    public zzvf(Context context, zzang com_google_android_gms_internal_ads_zzang, String str) {
        this.mLock = new Object();
        this.zzbqb = 1;
        this.zzbpx = str;
        this.mContext = context.getApplicationContext();
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzbpy = new zzvr();
        this.zzbpz = new zzvr();
    }

    public zzvf(Context context, zzang com_google_android_gms_internal_ads_zzang, String str, zzalo<zzuu> com_google_android_gms_internal_ads_zzalo_com_google_android_gms_internal_ads_zzuu, zzalo<zzuu> com_google_android_gms_internal_ads_zzalo_com_google_android_gms_internal_ads_zzuu2) {
        this(context, com_google_android_gms_internal_ads_zzang, str);
        this.zzbpy = com_google_android_gms_internal_ads_zzalo_com_google_android_gms_internal_ads_zzuu;
        this.zzbpz = com_google_android_gms_internal_ads_zzalo_com_google_android_gms_internal_ads_zzuu2;
    }

    protected final zzvw zza(@Nullable zzci com_google_android_gms_internal_ads_zzci) {
        zzaop com_google_android_gms_internal_ads_zzvw = new zzvw(this.zzbpz);
        zzaoe.zzcvy.execute(new zzvg(this, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzvw));
        com_google_android_gms_internal_ads_zzvw.zza(new zzvo(this, com_google_android_gms_internal_ads_zzvw), new zzvp(this, com_google_android_gms_internal_ads_zzvw));
        return com_google_android_gms_internal_ads_zzvw;
    }

    final /* synthetic */ void zza(zzci com_google_android_gms_internal_ads_zzci, zzvw com_google_android_gms_internal_ads_zzvw) {
        try {
            Context context = this.mContext;
            zzang com_google_android_gms_internal_ads_zzang = this.zzyf;
            zzuu com_google_android_gms_internal_ads_zzuf = ((Boolean) zzkb.zzik().zzd(zznk.zzaxz)).booleanValue() ? new zzuf(context, com_google_android_gms_internal_ads_zzang) : new zzuw(context, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zzci, null);
            com_google_android_gms_internal_ads_zzuf.zza(new zzvh(this, com_google_android_gms_internal_ads_zzvw, com_google_android_gms_internal_ads_zzuf));
            com_google_android_gms_internal_ads_zzuf.zza("/jsLoaded", new zzvk(this, com_google_android_gms_internal_ads_zzvw, com_google_android_gms_internal_ads_zzuf));
            zzamk com_google_android_gms_internal_ads_zzamk = new zzamk();
            zzv com_google_android_gms_internal_ads_zzvl = new zzvl(this, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzuf, com_google_android_gms_internal_ads_zzamk);
            com_google_android_gms_internal_ads_zzamk.set(com_google_android_gms_internal_ads_zzvl);
            com_google_android_gms_internal_ads_zzuf.zza("/requestReload", com_google_android_gms_internal_ads_zzvl);
            if (this.zzbpx.endsWith(".js")) {
                com_google_android_gms_internal_ads_zzuf.zzbb(this.zzbpx);
            } else if (this.zzbpx.startsWith("<html>")) {
                com_google_android_gms_internal_ads_zzuf.zzbc(this.zzbpx);
            } else {
                com_google_android_gms_internal_ads_zzuf.zzbd(this.zzbpx);
            }
            zzakk.zzcrm.postDelayed(new zzvm(this, com_google_android_gms_internal_ads_zzvw, com_google_android_gms_internal_ads_zzuf), (long) zzvq.zzbqo);
        } catch (Throwable th) {
            zzane.zzb("Error creating webview.", th);
            zzbv.zzeo().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            com_google_android_gms_internal_ads_zzvw.reject();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzvw r4, com.google.android.gms.internal.ads.zzuu r5) {
        /*
        r3 = this;
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r4.getStatus();	 Catch:{ all -> 0x002a }
        r2 = -1;
        if (r0 == r2) goto L_0x0011;
    L_0x000a:
        r0 = r4.getStatus();	 Catch:{ all -> 0x002a }
        r2 = 1;
        if (r0 != r2) goto L_0x0013;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
    L_0x0012:
        return;
    L_0x0013:
        r4.reject();	 Catch:{ all -> 0x002a }
        r0 = com.google.android.gms.internal.ads.zzaoe.zzcvy;	 Catch:{ all -> 0x002a }
        r5.getClass();	 Catch:{ all -> 0x002a }
        r2 = com.google.android.gms.internal.ads.zzvj.zza(r5);	 Catch:{ all -> 0x002a }
        r0.execute(r2);	 Catch:{ all -> 0x002a }
        r0 = "Could not receive loaded message in a timely manner. Rejecting.";
        com.google.android.gms.internal.ads.zzakb.m3428v(r0);	 Catch:{ all -> 0x002a }
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
        goto L_0x0012;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvf.zza(com.google.android.gms.internal.ads.zzvw, com.google.android.gms.internal.ads.zzuu):void");
    }

    public final zzvs zzb(@Nullable zzci com_google_android_gms_internal_ads_zzci) {
        zzvs zzlz;
        synchronized (this.mLock) {
            if (this.zzbqa == null || this.zzbqa.getStatus() == -1) {
                this.zzbqb = 2;
                this.zzbqa = zza(null);
                zzlz = this.zzbqa.zzlz();
            } else if (this.zzbqb == 0) {
                zzlz = this.zzbqa.zzlz();
            } else if (this.zzbqb == 1) {
                this.zzbqb = 2;
                zza(null);
                zzlz = this.zzbqa.zzlz();
            } else if (this.zzbqb == 2) {
                zzlz = this.zzbqa.zzlz();
            } else {
                zzlz = this.zzbqa.zzlz();
            }
        }
        return zzlz;
    }
}

package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkl;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzxn;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
@ParametersAreNonnullByDefault
public final class zzah extends zzkl {
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzw zzwc;
    private final zzxn zzwh;
    private final zzkh zzxs;
    @Nullable
    private final zzqw zzxt;
    @Nullable
    private final zzrl zzxu;
    @Nullable
    private final zzqz zzxv;
    @Nullable
    private final zzri zzxw;
    @Nullable
    private final zzjn zzxx;
    @Nullable
    private final PublisherAdViewOptions zzxy;
    private final SimpleArrayMap<String, zzrf> zzxz;
    private final SimpleArrayMap<String, zzrc> zzya;
    private final zzpl zzyb;
    private final List<String> zzyc;
    private final zzlg zzyd;
    private final String zzye;
    private final zzang zzyf;
    @Nullable
    private WeakReference<zzd> zzyg;

    zzah(Context context, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzkh com_google_android_gms_internal_ads_zzkh, zzqw com_google_android_gms_internal_ads_zzqw, zzrl com_google_android_gms_internal_ads_zzrl, zzqz com_google_android_gms_internal_ads_zzqz, SimpleArrayMap<String, zzrf> simpleArrayMap, SimpleArrayMap<String, zzrc> simpleArrayMap2, zzpl com_google_android_gms_internal_ads_zzpl, zzlg com_google_android_gms_internal_ads_zzlg, zzw com_google_android_gms_ads_internal_zzw, zzri com_google_android_gms_internal_ads_zzri, zzjn com_google_android_gms_internal_ads_zzjn, PublisherAdViewOptions publisherAdViewOptions) {
        this.mContext = context;
        this.zzye = str;
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzxs = com_google_android_gms_internal_ads_zzkh;
        this.zzxv = com_google_android_gms_internal_ads_zzqz;
        this.zzxt = com_google_android_gms_internal_ads_zzqw;
        this.zzxu = com_google_android_gms_internal_ads_zzrl;
        this.zzxz = simpleArrayMap;
        this.zzya = simpleArrayMap2;
        this.zzyb = com_google_android_gms_internal_ads_zzpl;
        this.zzyc = zzdg();
        this.zzyd = com_google_android_gms_internal_ads_zzlg;
        this.zzwc = com_google_android_gms_ads_internal_zzw;
        this.zzxw = com_google_android_gms_internal_ads_zzri;
        this.zzxx = com_google_android_gms_internal_ads_zzjn;
        this.zzxy = publisherAdViewOptions;
        zznk.initialize(this.mContext);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzakk.zzcrm.post(runnable);
    }

    private final void zzb(zzjj com_google_android_gms_internal_ads_zzjj, int i) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcg)).booleanValue() || this.zzxu == null) {
            zza com_google_android_gms_ads_internal_zzbc = new zzbc(this.mContext, this.zzwc, zzjn.zzf(this.mContext), this.zzye, this.zzwh, this.zzyf);
            this.zzyg = new WeakReference(com_google_android_gms_ads_internal_zzbc);
            zzqw com_google_android_gms_internal_ads_zzqw = this.zzxt;
            Preconditions.checkMainThread("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzbc.zzvw.zzade = com_google_android_gms_internal_ads_zzqw;
            zzrl com_google_android_gms_internal_ads_zzrl = this.zzxu;
            Preconditions.checkMainThread("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzbc.zzvw.zzadg = com_google_android_gms_internal_ads_zzrl;
            zzqz com_google_android_gms_internal_ads_zzqz = this.zzxv;
            Preconditions.checkMainThread("setOnContentAdLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzbc.zzvw.zzadf = com_google_android_gms_internal_ads_zzqz;
            SimpleArrayMap simpleArrayMap = this.zzxz;
            Preconditions.checkMainThread("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzbc.zzvw.zzadi = simpleArrayMap;
            com_google_android_gms_ads_internal_zzbc.zza(this.zzxs);
            simpleArrayMap = this.zzya;
            Preconditions.checkMainThread("setOnCustomClickListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzbc.zzvw.zzadh = simpleArrayMap;
            com_google_android_gms_ads_internal_zzbc.zzd(zzdg());
            zzpl com_google_android_gms_internal_ads_zzpl = this.zzyb;
            Preconditions.checkMainThread("setNativeAdOptions must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzbc.zzvw.zzadj = com_google_android_gms_internal_ads_zzpl;
            com_google_android_gms_ads_internal_zzbc.zza(this.zzyd);
            com_google_android_gms_ads_internal_zzbc.zzj(i);
            com_google_android_gms_ads_internal_zzbc.zzb(com_google_android_gms_internal_ads_zzjj);
            return;
        }
        zzi(0);
    }

    private final boolean zzde() {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaym)).booleanValue() && this.zzxw != null;
    }

    private final boolean zzdf() {
        return (this.zzxt == null && this.zzxv == null && this.zzxu == null && (this.zzxz == null || this.zzxz.size() <= 0)) ? false : true;
    }

    private final List<String> zzdg() {
        List<String> arrayList = new ArrayList();
        if (this.zzxv != null) {
            arrayList.add(SchemaSymbols.ATTVAL_TRUE_1);
        }
        if (this.zzxt != null) {
            arrayList.add("2");
        }
        if (this.zzxu != null) {
            arrayList.add("6");
        }
        if (this.zzxz.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    private final void zze(zzjj com_google_android_gms_internal_ads_zzjj) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcg)).booleanValue() || this.zzxu == null) {
            zza com_google_android_gms_ads_internal_zzq = new zzq(this.mContext, this.zzwc, this.zzxx, this.zzye, this.zzwh, this.zzyf);
            this.zzyg = new WeakReference(com_google_android_gms_ads_internal_zzq);
            zzri com_google_android_gms_internal_ads_zzri = this.zzxw;
            Preconditions.checkMainThread("setOnPublisherAdViewLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzadm = com_google_android_gms_internal_ads_zzri;
            if (this.zzxy != null) {
                if (this.zzxy.zzbg() != null) {
                    com_google_android_gms_ads_internal_zzq.zza(this.zzxy.zzbg());
                }
                com_google_android_gms_ads_internal_zzq.setManualImpressionsEnabled(this.zzxy.getManualImpressionsEnabled());
            }
            zzqw com_google_android_gms_internal_ads_zzqw = this.zzxt;
            Preconditions.checkMainThread("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzade = com_google_android_gms_internal_ads_zzqw;
            zzrl com_google_android_gms_internal_ads_zzrl = this.zzxu;
            Preconditions.checkMainThread("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzadg = com_google_android_gms_internal_ads_zzrl;
            zzqz com_google_android_gms_internal_ads_zzqz = this.zzxv;
            Preconditions.checkMainThread("setOnContentAdLoadedListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzadf = com_google_android_gms_internal_ads_zzqz;
            SimpleArrayMap simpleArrayMap = this.zzxz;
            Preconditions.checkMainThread("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzadi = simpleArrayMap;
            simpleArrayMap = this.zzya;
            Preconditions.checkMainThread("setOnCustomClickListener must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzadh = simpleArrayMap;
            zzpl com_google_android_gms_internal_ads_zzpl = this.zzyb;
            Preconditions.checkMainThread("setNativeAdOptions must be called on the main UI thread.");
            com_google_android_gms_ads_internal_zzq.zzvw.zzadj = com_google_android_gms_internal_ads_zzpl;
            com_google_android_gms_ads_internal_zzq.zzd(zzdg());
            com_google_android_gms_ads_internal_zzq.zza(this.zzxs);
            com_google_android_gms_ads_internal_zzq.zza(this.zzyd);
            List arrayList = new ArrayList();
            if (zzdf()) {
                arrayList.add(Integer.valueOf(1));
            }
            if (this.zzxw != null) {
                arrayList.add(Integer.valueOf(2));
            }
            com_google_android_gms_ads_internal_zzq.zze(arrayList);
            if (zzdf()) {
                com_google_android_gms_internal_ads_zzjj.extras.putBoolean("ina", true);
            }
            if (this.zzxw != null) {
                com_google_android_gms_internal_ads_zzjj.extras.putBoolean("iba", true);
            }
            com_google_android_gms_ads_internal_zzq.zzb(com_google_android_gms_internal_ads_zzjj);
            return;
        }
        zzi(0);
    }

    private final void zzi(int i) {
        if (this.zzxs != null) {
            try {
                this.zzxs.onAdFailedToLoad(0);
            } catch (Throwable e) {
                zzane.zzc("Failed calling onAdFailedToLoad.", e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public final java.lang.String getMediationAdapterClassName() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.mLock;
        monitor-enter(r2);
        r0 = r3.zzyg;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzyg;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzd) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.getMediationAdapterClassName();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzah.getMediationAdapterClassName():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isLoading() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.mLock;
        monitor-enter(r2);
        r0 = r3.zzyg;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzyg;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzd) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.isLoading();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzah.isLoading():boolean");
    }

    public final void zza(zzjj com_google_android_gms_internal_ads_zzjj, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Number of ads has to be more than 0");
        }
        runOnUiThread(new zzaj(this, com_google_android_gms_internal_ads_zzjj, i));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public final java.lang.String zzck() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.mLock;
        monitor-enter(r2);
        r0 = r3.zzyg;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzyg;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzd) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.zzck();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzah.zzck():java.lang.String");
    }

    public final void zzd(zzjj com_google_android_gms_internal_ads_zzjj) {
        runOnUiThread(new zzai(this, com_google_android_gms_internal_ads_zzjj));
    }
}

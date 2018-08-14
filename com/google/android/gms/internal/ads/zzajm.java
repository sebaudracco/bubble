package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzajm implements zzakh {
    private Context mContext;
    private final Object mLock = new Object();
    @Nullable
    private zzgf zzahz = null;
    private final zzajt zzcpl = new zzajt();
    private final zzakd zzcpm = new zzakd();
    @Nullable
    private zznn zzcpn = null;
    @Nullable
    private zzgk zzcpo = null;
    @Nullable
    private Boolean zzcpp = null;
    private String zzcpq;
    private final AtomicInteger zzcpr = new AtomicInteger(0);
    private final zzajp zzcps = new zzajp(null);
    private final Object zzcpt = new Object();
    @GuardedBy("mGrantedPermissionLock")
    private zzanz<ArrayList<String>> zzcpu;
    private zzes zzvy;
    private zzang zzyf;
    private boolean zzzv = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @javax.annotation.Nullable
    private final com.google.android.gms.internal.ads.zzgk zza(@javax.annotation.Nullable android.content.Context r5, boolean r6, boolean r7) {
        /*
        r4 = this;
        r1 = 0;
        r0 = com.google.android.gms.internal.ads.zznk.zzawk;
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();
        r0 = r2.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        r0 = r1;
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();
        if (r0 != 0) goto L_0x001d;
    L_0x001b:
        r0 = r1;
        goto L_0x0014;
    L_0x001d:
        r0 = com.google.android.gms.internal.ads.zznk.zzaws;
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();
        r0 = r2.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0043;
    L_0x002f:
        r0 = com.google.android.gms.internal.ads.zznk.zzawq;
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();
        r0 = r2.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0043;
    L_0x0041:
        r0 = r1;
        goto L_0x0014;
    L_0x0043:
        if (r6 == 0) goto L_0x0049;
    L_0x0045:
        if (r7 == 0) goto L_0x0049;
    L_0x0047:
        r0 = r1;
        goto L_0x0014;
    L_0x0049:
        r2 = r4.mLock;
        monitor-enter(r2);
        r0 = android.os.Looper.getMainLooper();	 Catch:{ all -> 0x0084 }
        if (r0 == 0) goto L_0x0054;
    L_0x0052:
        if (r5 != 0) goto L_0x0057;
    L_0x0054:
        monitor-exit(r2);	 Catch:{ all -> 0x0084 }
        r0 = r1;
        goto L_0x0014;
    L_0x0057:
        r0 = r4.zzahz;	 Catch:{ all -> 0x0084 }
        if (r0 != 0) goto L_0x0062;
    L_0x005b:
        r0 = new com.google.android.gms.internal.ads.zzgf;	 Catch:{ all -> 0x0084 }
        r0.<init>();	 Catch:{ all -> 0x0084 }
        r4.zzahz = r0;	 Catch:{ all -> 0x0084 }
    L_0x0062:
        r0 = r4.zzcpo;	 Catch:{ all -> 0x0084 }
        if (r0 != 0) goto L_0x0075;
    L_0x0066:
        r0 = new com.google.android.gms.internal.ads.zzgk;	 Catch:{ all -> 0x0084 }
        r1 = r4.zzahz;	 Catch:{ all -> 0x0084 }
        r3 = r4.zzyf;	 Catch:{ all -> 0x0084 }
        r3 = com.google.android.gms.internal.ads.zzadb.zzc(r5, r3);	 Catch:{ all -> 0x0084 }
        r0.<init>(r1, r3);	 Catch:{ all -> 0x0084 }
        r4.zzcpo = r0;	 Catch:{ all -> 0x0084 }
    L_0x0075:
        r0 = r4.zzcpo;	 Catch:{ all -> 0x0084 }
        r0.zzgw();	 Catch:{ all -> 0x0084 }
        r0 = "start fetching content...";
        com.google.android.gms.internal.ads.zzane.zzdj(r0);	 Catch:{ all -> 0x0084 }
        r0 = r4.zzcpo;	 Catch:{ all -> 0x0084 }
        monitor-exit(r2);	 Catch:{ all -> 0x0084 }
        goto L_0x0014;
    L_0x0084:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0084 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajm.zza(android.content.Context, boolean, boolean):com.google.android.gms.internal.ads.zzgk");
    }

    @TargetApi(16)
    private static ArrayList<String> zzag(Context context) {
        ArrayList<String> arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null) {
                return arrayList;
            }
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                    arrayList.add(packageInfo.requestedPermissions[i]);
                }
            }
            return arrayList;
        } catch (NameNotFoundException e) {
            return arrayList;
        }
    }

    @Nullable
    public final Context getApplicationContext() {
        return this.mContext;
    }

    @Nullable
    public final Resources getResources() {
        if (this.zzyf.zzcvg) {
            return this.mContext.getResources();
        }
        try {
            DynamiteModule load = DynamiteModule.load(this.mContext, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
            return load != null ? load.getModuleContext().getResources() : null;
        } catch (Throwable e) {
            zzane.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Boolean bool) {
        synchronized (this.mLock) {
            this.zzcpp = bool;
        }
    }

    public final void zza(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str);
    }

    public final void zzaa(boolean z) {
        this.zzcps.zzaa(z);
    }

    @Nullable
    public final zzgk zzaf(@Nullable Context context) {
        return zza(context, this.zzcpm.zzqu(), this.zzcpm.zzqw());
    }

    public final void zzb(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str, ((Float) zzkb.zzik().zzd(zznk.zzaul)).floatValue());
    }

    @TargetApi(23)
    public final void zzd(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        synchronized (this.mLock) {
            if (!this.zzzv) {
                zznn com_google_android_gms_internal_ads_zznn;
                this.mContext = context.getApplicationContext();
                this.zzyf = com_google_android_gms_internal_ads_zzang;
                zzbv.zzen().zza(zzbv.zzep());
                this.zzcpm.initialize(this.mContext);
                this.zzcpm.zza((zzakh) this);
                zzadb.zzc(this.mContext, this.zzyf);
                this.zzcpq = zzbv.zzek().zzm(context, com_google_android_gms_internal_ads_zzang.zzcw);
                this.zzvy = new zzes(context.getApplicationContext(), this.zzyf);
                zzbv.zzet();
                if (((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue()) {
                    com_google_android_gms_internal_ads_zznn = new zznn();
                } else {
                    zzakb.m3428v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    com_google_android_gms_internal_ads_zznn = null;
                }
                this.zzcpn = com_google_android_gms_internal_ads_zznn;
                zzanm.zza((zzanz) new zzajo(this).zznt(), "AppState.registerCsiReporter");
                this.zzzv = true;
                zzqi();
            }
        }
    }

    public final void zzd(Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            zza(this.mContext, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }

    public final zzajt zzpx() {
        return this.zzcpl;
    }

    @Nullable
    public final zznn zzpy() {
        zznn com_google_android_gms_internal_ads_zznn;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zznn = this.zzcpn;
        }
        return com_google_android_gms_internal_ads_zznn;
    }

    public final Boolean zzpz() {
        Boolean bool;
        synchronized (this.mLock) {
            bool = this.zzcpp;
        }
        return bool;
    }

    public final boolean zzqa() {
        return this.zzcps.zzqa();
    }

    public final boolean zzqb() {
        return this.zzcps.zzqb();
    }

    public final void zzqc() {
        this.zzcps.zzqc();
    }

    public final zzes zzqd() {
        return this.zzvy;
    }

    public final void zzqe() {
        this.zzcpr.incrementAndGet();
    }

    public final void zzqf() {
        this.zzcpr.decrementAndGet();
    }

    public final int zzqg() {
        return this.zzcpr.get();
    }

    public final zzakd zzqh() {
        zzakd com_google_android_gms_internal_ads_zzakd;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzakd = this.zzcpm;
        }
        return com_google_android_gms_internal_ads_zzakd;
    }

    public final zzanz<ArrayList<String>> zzqi() {
        if (this.mContext != null && PlatformVersion.isAtLeastJellyBean()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbau)).booleanValue()) {
                synchronized (this.zzcpt) {
                    zzanz<ArrayList<String>> com_google_android_gms_internal_ads_zzanz_java_util_ArrayList_java_lang_String;
                    if (this.zzcpu != null) {
                        com_google_android_gms_internal_ads_zzanz_java_util_ArrayList_java_lang_String = this.zzcpu;
                        return com_google_android_gms_internal_ads_zzanz_java_util_ArrayList_java_lang_String;
                    }
                    com_google_android_gms_internal_ads_zzanz_java_util_ArrayList_java_lang_String = zzaki.zza(new zzajn(this));
                    this.zzcpu = com_google_android_gms_internal_ads_zzanz_java_util_ArrayList_java_lang_String;
                    return com_google_android_gms_internal_ads_zzanz_java_util_ArrayList_java_lang_String;
                }
            }
        }
        return zzano.zzi(new ArrayList());
    }

    final /* synthetic */ ArrayList zzqj() throws Exception {
        return zzag(this.mContext);
    }
}

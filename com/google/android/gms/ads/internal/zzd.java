package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzadk;
import com.google.android.gms.internal.ads.zzaeg;
import com.google.android.gms.internal.ads.zzafa;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzajl;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzaoe;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzgk;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import com.google.android.gms.internal.ads.zzhx;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzua;
import com.google.android.gms.internal.ads.zzwz;
import com.google.android.gms.internal.ads.zzxg;
import com.google.android.gms.internal.ads.zzxn;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzd extends zza implements zzn, zzbo, zzwz {
    protected final zzxn zzwh;
    private transient boolean zzwi;

    public zzd(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw) {
        this(new zzbw(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzang), com_google_android_gms_internal_ads_zzxn, null, com_google_android_gms_ads_internal_zzw);
    }

    @VisibleForTesting
    private zzd(zzbw com_google_android_gms_ads_internal_zzbw, zzxn com_google_android_gms_internal_ads_zzxn, @Nullable zzbl com_google_android_gms_ads_internal_zzbl, zzw com_google_android_gms_ads_internal_zzw) {
        super(com_google_android_gms_ads_internal_zzbw, null, com_google_android_gms_ads_internal_zzw);
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzwi = false;
    }

    private final zzaeg zza(zzjj com_google_android_gms_internal_ads_zzjj, Bundle bundle, zzajl com_google_android_gms_internal_ads_zzajl, int i) {
        PackageInfo packageInfo;
        int i2;
        ApplicationInfo applicationInfo = this.zzvw.zzrt.getApplicationInfo();
        try {
            packageInfo = Wrappers.packageManager(this.zzvw.zzrt).getPackageInfo(applicationInfo.packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzvw.zzrt.getResources().getDisplayMetrics();
        Bundle bundle2 = null;
        if (!(this.zzvw.zzacs == null || this.zzvw.zzacs.getParent() == null)) {
            int[] iArr = new int[2];
            this.zzvw.zzacs.getLocationOnScreen(iArr);
            int i3 = iArr[0];
            int i4 = iArr[1];
            int width = this.zzvw.zzacs.getWidth();
            int height = this.zzvw.zzacs.getHeight();
            i2 = 0;
            if (this.zzvw.zzacs.isShown() && i3 + width > 0 && i4 + height > 0 && i3 <= displayMetrics.widthPixels && i4 <= displayMetrics.heightPixels) {
                i2 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i3);
            bundle2.putInt("y", i4);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt("visible", i2);
        }
        String zzql = zzbv.zzeo().zzpx().zzql();
        this.zzvw.zzacy = new zzajj(zzql, this.zzvw.zzacp);
        this.zzvw.zzacy.zzn(com_google_android_gms_internal_ads_zzjj);
        zzbv.zzek();
        String zza = zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacs, this.zzvw.zzacv);
        long j = 0;
        if (this.zzvw.zzadd != null) {
            try {
                j = this.zzvw.zzadd.getValue();
            } catch (RemoteException e2) {
                zzane.zzdk("Cannot get correlation id, default to 0.");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Bundle zza2 = zzbv.zzep().zza(this.zzvw.zzrt, this, zzql);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (i3 = 0; i3 < this.zzvw.zzadi.size(); i3++) {
            String str = (String) this.zzvw.zzadi.keyAt(i3);
            arrayList.add(str);
            if (this.zzvw.zzadh.containsKey(str) && this.zzvw.zzadh.get(str) != null) {
                arrayList2.add(str);
            }
        }
        Future zza3 = zzaki.zza(new zzg(this));
        Future zza4 = zzaki.zza(new zzh(this));
        String str2 = null;
        if (com_google_android_gms_internal_ads_zzajl != null) {
            str2 = com_google_android_gms_internal_ads_zzajl.zzpu();
        }
        String str3 = null;
        if (this.zzvw.zzads != null && this.zzvw.zzads.size() > 0) {
            i2 = 0;
            if (packageInfo != null) {
                i2 = packageInfo.versionCode;
            }
            if (i2 > zzbv.zzeo().zzqh().zzqz()) {
                zzbv.zzeo().zzqh().zzrf();
                zzbv.zzeo().zzqh().zzae(i2);
            } else {
                JSONObject zzre = zzbv.zzeo().zzqh().zzre();
                if (zzre != null) {
                    JSONArray optJSONArray = zzre.optJSONArray(this.zzvw.zzacp);
                    if (optJSONArray != null) {
                        str3 = optJSONArray.toString();
                    }
                }
            }
        }
        zzjn com_google_android_gms_internal_ads_zzjn = this.zzvw.zzacv;
        String str4 = this.zzvw.zzacp;
        String zzih = zzkb.zzih();
        zzang com_google_android_gms_internal_ads_zzang = this.zzvw.zzacr;
        List list = this.zzvw.zzads;
        boolean zzqt = zzbv.zzeo().zzqh().zzqt();
        int i5 = displayMetrics.widthPixels;
        int i6 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        List zzjb = zznk.zzjb();
        String str5 = this.zzvw.zzaco;
        zzpl com_google_android_gms_internal_ads_zzpl = this.zzvw.zzadj;
        String zzfq = this.zzvw.zzfq();
        float zzdo = zzbv.zzfj().zzdo();
        boolean zzdp = zzbv.zzfj().zzdp();
        zzbv.zzek();
        int zzas = zzakk.zzas(this.zzvw.zzrt);
        zzbv.zzek();
        int zzx = zzakk.zzx(this.zzvw.zzacs);
        boolean z = this.zzvw.zzrt instanceof Activity;
        boolean zzqy = zzbv.zzeo().zzqh().zzqy();
        boolean zzqa = zzbv.zzeo().zzqa();
        int zztx = zzbv.zzff().zztx();
        zzbv.zzek();
        Bundle zzrk = zzakk.zzrk();
        String zzrw = zzbv.zzeu().zzrw();
        zzlu com_google_android_gms_internal_ads_zzlu = this.zzvw.zzadl;
        boolean zzrx = zzbv.zzeu().zzrx();
        Bundle zzlt = zzua.zzlk().zzlt();
        boolean zzcr = zzbv.zzeo().zzqh().zzcr(this.zzvw.zzacp);
        List list2 = this.zzvw.zzadn;
        boolean isCallerInstantApp = Wrappers.packageManager(this.zzvw.zzrt).isCallerInstantApp();
        boolean zzqb = zzbv.zzeo().zzqb();
        zzbv.zzem();
        return new zzaeg(bundle2, com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zzjn, str4, applicationInfo, packageInfo, zzql, zzih, com_google_android_gms_internal_ads_zzang, zza2, list, arrayList, bundle, zzqt, i5, i6, f, zza, j, uuid, zzjb, str5, com_google_android_gms_internal_ads_zzpl, zzfq, zzdo, zzdp, zzas, zzx, z, zzqy, zza3, str2, zzqa, zztx, zzrk, zzrw, com_google_android_gms_internal_ads_zzlu, zzrx, zzlt, zzcr, zza4, list2, str3, arrayList2, i, isCallerInstantApp, zzqb, zzakq.zzrp(), (ArrayList) zzano.zza(zzbv.zzeo().zzqi(), null, 1000, TimeUnit.MILLISECONDS));
    }

    @Nullable
    static String zzc(zzajh com_google_android_gms_internal_ads_zzajh) {
        if (com_google_android_gms_internal_ads_zzajh == null) {
            return null;
        }
        String str = com_google_android_gms_internal_ads_zzajh.zzbty;
        Object obj = ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) ? 1 : null;
        if (obj == null || com_google_android_gms_internal_ads_zzajh.zzbtw == null) {
            return str;
        }
        try {
            return new JSONObject(com_google_android_gms_internal_ads_zzajh.zzbtw.zzbsb).getString("class_name");
        } catch (JSONException e) {
            return str;
        } catch (NullPointerException e2) {
            return str;
        }
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzvw.zzacw == null ? null : this.zzvw.zzacw.zzbty;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            zzane.zzdk("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzvw.zzacw.zzcod == null || this.zzvw.zzacw.zzcod.zzbsn == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, zzc(this.zzvw.zzacw.zzcod.zzbsn));
        }
        if (!(this.zzvw.zzacw.zzbtw == null || this.zzvw.zzacw.zzbtw.zzbrw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, this.zzvw.zzacw.zzbtw.zzbrw);
        }
        super.onAdClicked();
    }

    public final void onPause() {
        this.zzvy.zzj(this.zzvw.zzacw);
    }

    public final void onResume() {
        this.zzvy.zzk(this.zzvw.zzacw);
    }

    public void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null || !this.zzvw.zzfo())) {
            zzbv.zzem();
            zzakq.zzi(this.zzvw.zzacw.zzbyo);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbtx == null)) {
            try {
                this.zzvw.zzacw.zzbtx.pause();
            } catch (RemoteException e) {
                zzane.zzdk("Could not pause mediation adapter.");
            }
        }
        this.zzvy.zzj(this.zzvw.zzacw);
        this.zzvv.pause();
    }

    public final void recordImpression() {
        zza(this.zzvw.zzacw, false);
    }

    public void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        zzaqw com_google_android_gms_internal_ads_zzaqw = null;
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            com_google_android_gms_internal_ads_zzaqw = this.zzvw.zzacw.zzbyo;
        }
        if (com_google_android_gms_internal_ads_zzaqw != null && this.zzvw.zzfo()) {
            zzbv.zzem();
            zzakq.zzj(this.zzvw.zzacw.zzbyo);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbtx == null)) {
            try {
                this.zzvw.zzacw.zzbtx.resume();
            } catch (RemoteException e) {
                zzane.zzdk("Could not resume mediation adapter.");
            }
        }
        if (com_google_android_gms_internal_ads_zzaqw == null || !com_google_android_gms_internal_ads_zzaqw.zzum()) {
            this.zzvv.resume();
        }
        this.zzvy.zzk(this.zzvw.zzacw);
    }

    public void showInterstitial() {
        zzane.zzdk("showInterstitial is not supported for current ad type");
    }

    protected void zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        if (com_google_android_gms_internal_ads_zzajh == null) {
            zzane.zzdk("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (com_google_android_gms_internal_ads_zzajh == null) {
            zzane.zzdk("Ad state was null when trying to ping impression URLs.");
        } else {
            zzane.zzck("Pinging Impression URLs.");
            if (this.zzvw.zzacy != null) {
                this.zzvw.zzacy.zzpm();
            }
            com_google_android_gms_internal_ads_zzajh.zzcoq.zza(zzb.zzakn);
            if (!(com_google_android_gms_internal_ads_zzajh.zzbso == null || com_google_android_gms_internal_ads_zzajh.zzcok)) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(com_google_android_gms_internal_ads_zzajh.zzbso));
                com_google_android_gms_internal_ads_zzajh.zzcok = true;
            }
        }
        if (!com_google_android_gms_internal_ads_zzajh.zzcom || z) {
            if (!(com_google_android_gms_internal_ads_zzajh.zzcod == null || com_google_android_gms_internal_ads_zzajh.zzcod.zzbso == null)) {
                zzbv.zzfd();
                zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh, this.zzvw.zzacp, z, zzc(com_google_android_gms_internal_ads_zzajh.zzcod.zzbso));
            }
            if (!(com_google_android_gms_internal_ads_zzajh.zzbtw == null || com_google_android_gms_internal_ads_zzajh.zzbtw.zzbrx == null)) {
                zzbv.zzfd();
                zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh, this.zzvw.zzacp, z, com_google_android_gms_internal_ads_zzajh.zzbtw.zzbrx);
            }
            com_google_android_gms_internal_ads_zzajh.zzcom = true;
        }
    }

    public final void zza(zzqs com_google_android_gms_internal_ads_zzqs, String str) {
        zzrc com_google_android_gms_internal_ads_zzrc = null;
        if (com_google_android_gms_internal_ads_zzqs != null) {
            try {
                Object customTemplateId = com_google_android_gms_internal_ads_zzqs.getCustomTemplateId();
            } catch (Throwable e) {
                zzane.zzc("Unable to call onCustomClick.", e);
                return;
            }
        }
        customTemplateId = null;
        if (!(this.zzvw.zzadh == null || customTemplateId == null)) {
            com_google_android_gms_internal_ads_zzrc = (zzrc) this.zzvw.zzadh.get(customTemplateId);
        }
        if (com_google_android_gms_internal_ads_zzrc == null) {
            zzane.zzdk("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            com_google_android_gms_internal_ads_zzrc.zzb(com_google_android_gms_internal_ads_zzqs, str);
        }
    }

    public final boolean zza(zzaeg com_google_android_gms_internal_ads_zzaeg, zznx com_google_android_gms_internal_ads_zznx) {
        this.zzvr = com_google_android_gms_internal_ads_zznx;
        com_google_android_gms_internal_ads_zznx.zze("seq_num", com_google_android_gms_internal_ads_zzaeg.zzccy);
        com_google_android_gms_internal_ads_zznx.zze("request_id", com_google_android_gms_internal_ads_zzaeg.zzcdi);
        com_google_android_gms_internal_ads_zznx.zze("session_id", com_google_android_gms_internal_ads_zzaeg.zzccz);
        if (com_google_android_gms_internal_ads_zzaeg.zzccw != null) {
            com_google_android_gms_internal_ads_zznx.zze("app_version", String.valueOf(com_google_android_gms_internal_ads_zzaeg.zzccw.versionCode));
        }
        zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
        zzbv.zzeg();
        Context context = this.zzvw.zzrt;
        zzhx com_google_android_gms_internal_ads_zzhx = this.zzwc.zzxb;
        zzajx com_google_android_gms_internal_ads_zzafa = com_google_android_gms_internal_ads_zzaeg.zzccv.extras.getBundle("sdk_less_server_data") != null ? new zzafa(context, com_google_android_gms_internal_ads_zzaeg, this, com_google_android_gms_internal_ads_zzhx) : new zzadk(context, com_google_android_gms_internal_ads_zzaeg, this, com_google_android_gms_internal_ads_zzhx);
        com_google_android_gms_internal_ads_zzafa.zzqo();
        com_google_android_gms_ads_internal_zzbw.zzact = com_google_android_gms_internal_ads_zzafa;
        return true;
    }

    final boolean zza(zzajh com_google_android_gms_internal_ads_zzajh) {
        zzjj com_google_android_gms_internal_ads_zzjj;
        boolean z = false;
        if (this.zzvx != null) {
            com_google_android_gms_internal_ads_zzjj = this.zzvx;
            this.zzvx = null;
        } else {
            com_google_android_gms_internal_ads_zzjj = com_google_android_gms_internal_ads_zzajh.zzccv;
            if (com_google_android_gms_internal_ads_zzjj.extras != null) {
                z = com_google_android_gms_internal_ads_zzjj.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zzajh, z);
    }

    protected boolean zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        int i;
        int i2 = 0;
        if (!(com_google_android_gms_internal_ads_zzajh == null || com_google_android_gms_internal_ads_zzajh.zzbtz == null)) {
            com_google_android_gms_internal_ads_zzajh.zzbtz.zza(null);
        }
        if (com_google_android_gms_internal_ads_zzajh2.zzbtz != null) {
            com_google_android_gms_internal_ads_zzajh2.zzbtz.zza((zzwz) this);
        }
        if (com_google_android_gms_internal_ads_zzajh2.zzcod != null) {
            i = com_google_android_gms_internal_ads_zzajh2.zzcod.zzbtc;
            i2 = com_google_android_gms_internal_ads_zzajh2.zzcod.zzbtd;
        } else {
            i = 0;
        }
        this.zzvw.zzadt.zze(i, i2);
        return true;
    }

    protected boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        if (!z && this.zzvw.zzfo()) {
            if (com_google_android_gms_internal_ads_zzajh.zzbsu > 0) {
                this.zzvv.zza(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zzajh.zzbsu);
            } else if (com_google_android_gms_internal_ads_zzajh.zzcod != null && com_google_android_gms_internal_ads_zzajh.zzcod.zzbsu > 0) {
                this.zzvv.zza(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zzajh.zzcod.zzbsu);
            } else if (!com_google_android_gms_internal_ads_zzajh.zzceq && com_google_android_gms_internal_ads_zzajh.errorCode == 2) {
                this.zzvv.zzg(com_google_android_gms_internal_ads_zzjj);
            }
        }
        return this.zzvv.zzdz();
    }

    public boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zznx com_google_android_gms_internal_ads_zznx) {
        return zza(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zznx, 1);
    }

    public final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zznx com_google_android_gms_internal_ads_zznx, int i) {
        if (!zzca()) {
            return false;
        }
        zzajl zzra;
        zzbv.zzek();
        zzgk zzaf = zzbv.zzeo().zzaf(this.zzvw.zzrt);
        Bundle zza = zzaf == null ? null : zzakk.zza(zzaf);
        this.zzvv.cancel();
        this.zzvw.zzadv = 0;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcs)).booleanValue()) {
            zzra = zzbv.zzeo().zzqh().zzra();
            zzbv.zzes().zza(this.zzvw.zzrt, this.zzvw.zzacr, false, zzra, zzra != null ? zzra.zzpv() : null, this.zzvw.zzacp, null);
        } else {
            zzra = null;
        }
        return zza(zza(com_google_android_gms_internal_ads_zzjj, zza, zzra, i), com_google_android_gms_internal_ads_zznx);
    }

    public final void zzb(zzajh com_google_android_gms_internal_ads_zzajh) {
        super.zzb(com_google_android_gms_internal_ads_zzajh);
        if (com_google_android_gms_internal_ads_zzajh.zzbtw != null) {
            zzane.zzck("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzfu();
            }
            zzane.zzck("Pinging network fill URLs.");
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh, this.zzvw.zzacp, false, com_google_android_gms_internal_ads_zzajh.zzbtw.zzbsa);
            if (!(com_google_android_gms_internal_ads_zzajh.zzcod == null || com_google_android_gms_internal_ads_zzajh.zzcod.zzbsr == null || com_google_android_gms_internal_ads_zzajh.zzcod.zzbsr.size() <= 0)) {
                zzane.zzck("Pinging urls remotely");
                zzbv.zzek().zza(this.zzvw.zzrt, com_google_android_gms_internal_ads_zzajh.zzcod.zzbsr);
            }
        } else {
            zzane.zzck("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzft();
            }
        }
        if (com_google_android_gms_internal_ads_zzajh.errorCode == 3 && com_google_android_gms_internal_ads_zzajh.zzcod != null && com_google_android_gms_internal_ads_zzajh.zzcod.zzbsq != null) {
            zzane.zzck("Pinging no fill URLs.");
            zzbv.zzfd();
            zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh, this.zzvw.zzacp, false, com_google_android_gms_internal_ads_zzajh.zzcod.zzbsq);
        }
    }

    protected final void zzb(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        if (com_google_android_gms_internal_ads_zzajh != null) {
            if (!(com_google_android_gms_internal_ads_zzajh == null || com_google_android_gms_internal_ads_zzajh.zzbsp == null || com_google_android_gms_internal_ads_zzajh.zzcol)) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzb(com_google_android_gms_internal_ads_zzajh.zzbsp));
                com_google_android_gms_internal_ads_zzajh.zzcol = true;
            }
            if (!com_google_android_gms_internal_ads_zzajh.zzcon || z) {
                if (!(com_google_android_gms_internal_ads_zzajh.zzcod == null || com_google_android_gms_internal_ads_zzajh.zzcod.zzbsp == null)) {
                    zzbv.zzfd();
                    zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh, this.zzvw.zzacp, z, zzb(com_google_android_gms_internal_ads_zzajh.zzcod.zzbsp));
                }
                if (!(com_google_android_gms_internal_ads_zzajh.zzbtw == null || com_google_android_gms_internal_ads_zzajh.zzbtw.zzbry == null)) {
                    zzbv.zzfd();
                    zzxg.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh, this.zzvw.zzacp, z, com_google_android_gms_internal_ads_zzajh.zzbtw.zzbry);
                }
                com_google_android_gms_internal_ads_zzajh.zzcon = true;
            }
        }
    }

    public final void zzb(String str, String str2) {
        onAppEvent(str, str2);
    }

    protected final boolean zzc(zzjj com_google_android_gms_internal_ads_zzjj) {
        return super.zzc(com_google_android_gms_internal_ads_zzjj) && !this.zzwi;
    }

    protected boolean zzca() {
        zzbv.zzek();
        if (zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzbv.zzek();
            if (zzakk.zzaj(this.zzvw.zzrt)) {
                return true;
            }
        }
        return false;
    }

    public void zzcb() {
        this.zzwi = false;
        zzbn();
        this.zzvw.zzacy.zzpo();
    }

    public void zzcc() {
        this.zzwi = true;
        zzbp();
    }

    public void zzcd() {
        zzane.zzdk("Mediated ad does not support onVideoEnd callback");
    }

    public void zzce() {
        onAdClicked();
    }

    public final void zzcf() {
        zzcb();
    }

    public final void zzcg() {
        zzbo();
    }

    public final void zzch() {
        zzcc();
    }

    public final void zzci() {
        if (this.zzvw.zzacw != null) {
            String str = this.zzvw.zzacw.zzbty;
            zzane.zzdk(new StringBuilder(String.valueOf(str).length() + 74).append("Mediation adapter ").append(str).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        zza(this.zzvw.zzacw, true);
        zzb(this.zzvw.zzacw, true);
        zzbq();
    }

    public void zzcj() {
        recordImpression();
    }

    @Nullable
    public final String zzck() {
        return this.zzvw.zzacw == null ? null : zzc(this.zzvw.zzacw);
    }

    public final void zzcl() {
        Executor executor = zzaoe.zzcvy;
        zzbl com_google_android_gms_ads_internal_zzbl = this.zzvv;
        com_google_android_gms_ads_internal_zzbl.getClass();
        executor.execute(zze.zza(com_google_android_gms_ads_internal_zzbl));
    }

    public final void zzcm() {
        Executor executor = zzaoe.zzcvy;
        zzbl com_google_android_gms_ads_internal_zzbl = this.zzvv;
        com_google_android_gms_ads_internal_zzbl.getClass();
        executor.execute(zzf.zza(com_google_android_gms_ads_internal_zzbl));
    }
}

package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzadj;
import com.google.android.gms.internal.ads.zzagp;
import com.google.android.gms.internal.ads.zzagu;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzajs;
import com.google.android.gms.internal.ads.zzaju;
import com.google.android.gms.internal.ads.zzajz;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzes;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjk;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkt;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzms;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznv;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zza extends zzkt implements zzb, zzd, zzt, zzabm, zzadj, zzajs, zzjd {
    protected zznx zzvr;
    protected zznv zzvs;
    private zznv zzvt;
    protected boolean zzvu = false;
    protected final zzbl zzvv;
    protected final zzbw zzvw;
    @Nullable
    protected transient zzjj zzvx;
    protected final zzes zzvy;
    private final Bundle zzvz = new Bundle();
    private boolean zzwa = false;
    @Nullable
    protected IObjectWrapper zzwb;
    protected final zzw zzwc;

    @VisibleForTesting
    zza(zzbw com_google_android_gms_ads_internal_zzbw, @Nullable zzbl com_google_android_gms_ads_internal_zzbl, zzw com_google_android_gms_ads_internal_zzw) {
        this.zzvw = com_google_android_gms_ads_internal_zzbw;
        this.zzvv = new zzbl(this);
        this.zzwc = com_google_android_gms_ads_internal_zzw;
        zzbv.zzek().zzak(this.zzvw.zzrt);
        zzbv.zzek().zzal(this.zzvw.zzrt);
        zzajz.zzai(this.zzvw.zzrt);
        zzbv.zzfi().initialize(this.zzvw.zzrt);
        zzbv.zzeo().zzd(this.zzvw.zzrt, this.zzvw.zzacr);
        zzbv.zzeq().initialize(this.zzvw.zzrt);
        this.zzvy = zzbv.zzeo().zzqd();
        zzbv.zzen().initialize(this.zzvw.zzrt);
        zzbv.zzfk().initialize(this.zzvw.zzrt);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbci)).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(new zzb(this, new CountDownLatch(((Integer) zzkb.zzik().zzd(zznk.zzbck)).intValue()), timer), 0, ((Long) zzkb.zzik().zzd(zznk.zzbcj)).longValue());
        }
    }

    protected static boolean zza(zzjj com_google_android_gms_internal_ads_zzjj) {
        Bundle bundle = com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }

    @VisibleForTesting
    private static long zzq(String str) {
        Throwable e;
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException e2) {
            e = e2;
            zzane.zzb("", e);
            return -1;
        } catch (NumberFormatException e3) {
            e = e3;
            zzane.zzb("", e);
            return -1;
        }
    }

    public void destroy() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: destroy");
        this.zzvv.cancel();
        this.zzvy.zzi(this.zzvw.zzacw);
        zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
        if (com_google_android_gms_ads_internal_zzbw.zzacs != null) {
            com_google_android_gms_ads_internal_zzbw.zzacs.zzfs();
        }
        com_google_android_gms_ads_internal_zzbw.zzada = null;
        com_google_android_gms_ads_internal_zzbw.zzadc = null;
        com_google_android_gms_ads_internal_zzbw.zzadb = null;
        com_google_android_gms_ads_internal_zzbw.zzado = null;
        com_google_android_gms_ads_internal_zzbw.zzadd = null;
        com_google_android_gms_ads_internal_zzbw.zzg(false);
        if (com_google_android_gms_ads_internal_zzbw.zzacs != null) {
            com_google_android_gms_ads_internal_zzbw.zzacs.removeAllViews();
        }
        com_google_android_gms_ads_internal_zzbw.zzfm();
        com_google_android_gms_ads_internal_zzbw.zzfn();
        com_google_android_gms_ads_internal_zzbw.zzacw = null;
    }

    public String getAdUnitId() {
        return this.zzvw.zzacp;
    }

    public zzlo getVideoController() {
        return null;
    }

    public final boolean isLoading() {
        return this.zzvu;
    }

    public final boolean isReady() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: isLoaded");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            zzane.zzdk("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzane.zzck("Pinging click URLs.");
        if (this.zzvw.zzacy != null) {
            this.zzvw.zzacy.zzpn();
        }
        if (this.zzvw.zzacw.zzbsn != null) {
            zzbv.zzek();
            zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(this.zzvw.zzacw.zzbsn));
        }
        if (this.zzvw.zzacz != null) {
            try {
                this.zzvw.zzacz.onAdClicked();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onAppEvent(String str, @Nullable String str2) {
        if (this.zzvw.zzadb != null) {
            try {
                this.zzvw.zzadb.onAppEvent(str, str2);
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public void pause() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: pause");
    }

    public void resume() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: resume");
    }

    public void setImmersiveMode(boolean z) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzane.zzdk("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public final void setUserId(String str) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setUserId");
        this.zzvw.zzadr = str;
    }

    public final void stopLoading() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: stopLoading");
        this.zzvu = false;
        this.zzvw.zzg(true);
    }

    public void zza(zzaaw com_google_android_gms_internal_ads_zzaaw) {
        zzane.zzdk("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzabc com_google_android_gms_internal_ads_zzabc, String str) {
        zzane.zzdk("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzagx com_google_android_gms_internal_ads_zzagx) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzvw.zzadq = com_google_android_gms_internal_ads_zzagx;
    }

    public final void zza(zzahe com_google_android_gms_internal_ads_zzahe) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedVideoAdListener");
        this.zzvw.zzadp = com_google_android_gms_internal_ads_zzahe;
    }

    protected final void zza(@Nullable zzaig com_google_android_gms_internal_ads_zzaig) {
        if (this.zzvw.zzadp != null) {
            try {
                String str = "";
                int i = 1;
                if (com_google_android_gms_internal_ads_zzaig != null) {
                    str = com_google_android_gms_internal_ads_zzaig.type;
                    i = com_google_android_gms_internal_ads_zzaig.zzcmk;
                }
                zzagu com_google_android_gms_internal_ads_zzagp = new zzagp(str, i);
                this.zzvw.zzadp.zza(com_google_android_gms_internal_ads_zzagp);
                if (this.zzvw.zzadq != null) {
                    this.zzvw.zzadq.zza(com_google_android_gms_internal_ads_zzagp, this.zzvw.zzacx.zzcgs.zzcdi);
                }
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zza(zzaji com_google_android_gms_internal_ads_zzaji) {
        if (!(com_google_android_gms_internal_ads_zzaji.zzcos.zzceu == -1 || TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaji.zzcos.zzcfd))) {
            long zzq = zzq(com_google_android_gms_internal_ads_zzaji.zzcos.zzcfd);
            if (zzq != -1) {
                zznv zzd = this.zzvr.zzd(zzq + com_google_android_gms_internal_ads_zzaji.zzcos.zzceu);
                this.zzvr.zza(zzd, "stc");
            }
        }
        this.zzvr.zzan(com_google_android_gms_internal_ads_zzaji.zzcos.zzcfd);
        this.zzvr.zza(this.zzvs, "arf");
        this.zzvt = this.zzvr.zzjj();
        this.zzvr.zze("gqi", com_google_android_gms_internal_ads_zzaji.zzcos.zzamj);
        this.zzvw.zzact = null;
        this.zzvw.zzacx = com_google_android_gms_internal_ads_zzaji;
        com_google_android_gms_internal_ads_zzaji.zzcoq.zza(new zzc(this, com_google_android_gms_internal_ads_zzaji));
        com_google_android_gms_internal_ads_zzaji.zzcoq.zza(com.google.android.gms.internal.ads.zzhu.zza.zzb.zzakk);
        zza(com_google_android_gms_internal_ads_zzaji, this.zzvr);
    }

    protected abstract void zza(zzaji com_google_android_gms_internal_ads_zzaji, zznx com_google_android_gms_internal_ads_zznx);

    public final void zza(zzjn com_google_android_gms_internal_ads_zzjn) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdSize");
        this.zzvw.zzacv = com_google_android_gms_internal_ads_zzjn;
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null || this.zzvw.zzadv != 0)) {
            this.zzvw.zzacw.zzbyo.zza(zzasi.zzb(com_google_android_gms_internal_ads_zzjn));
        }
        if (this.zzvw.zzacs != null) {
            if (this.zzvw.zzacs.getChildCount() > 1) {
                this.zzvw.zzacs.removeView(this.zzvw.zzacs.getNextView());
            }
            this.zzvw.zzacs.setMinimumWidth(com_google_android_gms_internal_ads_zzjn.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(com_google_android_gms_internal_ads_zzjn.heightPixels);
            this.zzvw.zzacs.requestLayout();
        }
    }

    public final void zza(zzke com_google_android_gms_internal_ads_zzke) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdClickListener");
        this.zzvw.zzacz = com_google_android_gms_internal_ads_zzke;
    }

    public final void zza(zzkh com_google_android_gms_internal_ads_zzkh) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdListener");
        this.zzvw.zzada = com_google_android_gms_internal_ads_zzkh;
    }

    public final void zza(zzkx com_google_android_gms_internal_ads_zzkx) {
        this.zzvw.zzadc = com_google_android_gms_internal_ads_zzkx;
    }

    public final void zza(zzla com_google_android_gms_internal_ads_zzla) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAppEventListener");
        this.zzvw.zzadb = com_google_android_gms_internal_ads_zzla;
    }

    public final void zza(zzlg com_google_android_gms_internal_ads_zzlg) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCorrelationIdProvider");
        this.zzvw.zzadd = com_google_android_gms_internal_ads_zzlg;
    }

    public final void zza(@Nullable zzlu com_google_android_gms_internal_ads_zzlu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setIconAdOptions");
        this.zzvw.zzadl = com_google_android_gms_internal_ads_zzlu;
    }

    public final void zza(@Nullable zzmu com_google_android_gms_internal_ads_zzmu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setVideoOptions");
        this.zzvw.zzadk = com_google_android_gms_internal_ads_zzmu;
    }

    public final void zza(zznv com_google_android_gms_internal_ads_zznv) {
        this.zzvr = new zznx(((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue(), "load_ad", this.zzvw.zzacv.zzarb);
        this.zzvt = new zznv(-1, null, null);
        if (com_google_android_gms_internal_ads_zznv == null) {
            this.zzvs = new zznv(-1, null, null);
        } else {
            this.zzvs = new zznv(com_google_android_gms_internal_ads_zznv.getTime(), com_google_android_gms_internal_ads_zznv.zzjg(), com_google_android_gms_internal_ads_zznv.zzjh());
        }
    }

    public void zza(zzod com_google_android_gms_internal_ads_zzod) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public final void zza(String str, Bundle bundle) {
        this.zzvz.putAll(bundle);
        if (this.zzwa && this.zzvw.zzadc != null) {
            try {
                this.zzvw.zzadc.zzt();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zza(HashSet<zzajj> hashSet) {
        this.zzvw.zza(hashSet);
    }

    boolean zza(zzajh com_google_android_gms_internal_ads_zzajh) {
        return false;
    }

    protected abstract boolean zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2);

    protected abstract boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zznx com_google_android_gms_internal_ads_zznx);

    protected final List<String> zzb(List<String> list) {
        List arrayList = new ArrayList(list.size());
        for (String zzc : list) {
            arrayList.add(zzajb.zzc(zzc, this.zzvw.zzrt));
        }
        return arrayList;
    }

    public void zzb(zzajh com_google_android_gms_internal_ads_zzajh) {
        this.zzvr.zza(this.zzvt, "awr");
        this.zzvw.zzacu = null;
        if (!(com_google_android_gms_internal_ads_zzajh.errorCode == -2 || com_google_android_gms_internal_ads_zzajh.errorCode == 3 || this.zzvw.zzfl() == null)) {
            zzbv.zzep().zzb(this.zzvw.zzfl());
        }
        if (com_google_android_gms_internal_ads_zzajh.errorCode == -1) {
            this.zzvu = false;
            return;
        }
        if (zza(com_google_android_gms_internal_ads_zzajh)) {
            zzane.zzck("Ad refresh scheduled.");
        }
        if (com_google_android_gms_internal_ads_zzajh.errorCode != -2) {
            if (com_google_android_gms_internal_ads_zzajh.errorCode == 3) {
                com_google_android_gms_internal_ads_zzajh.zzcoq.zza(com.google.android.gms.internal.ads.zzhu.zza.zzb.zzakm);
            } else {
                com_google_android_gms_internal_ads_zzajh.zzcoq.zza(com.google.android.gms.internal.ads.zzhu.zza.zzb.zzakl);
            }
            zzi(com_google_android_gms_internal_ads_zzajh.errorCode);
            return;
        }
        if (this.zzvw.zzadt == null) {
            this.zzvw.zzadt = new zzaju(this.zzvw.zzacp);
        }
        if (this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().zzdc(com_google_android_gms_internal_ads_zzajh.zzcfl);
        }
        this.zzvy.zzh(this.zzvw.zzacw);
        if (zza(this.zzvw.zzacw, com_google_android_gms_internal_ads_zzajh)) {
            this.zzvw.zzacw = com_google_android_gms_internal_ads_zzajh;
            zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
            if (com_google_android_gms_ads_internal_zzbw.zzacy != null) {
                if (com_google_android_gms_ads_internal_zzbw.zzacw != null) {
                    com_google_android_gms_ads_internal_zzbw.zzacy.zzh(com_google_android_gms_ads_internal_zzbw.zzacw.zzcoh);
                    com_google_android_gms_ads_internal_zzbw.zzacy.zzi(com_google_android_gms_ads_internal_zzbw.zzacw.zzcoi);
                    com_google_android_gms_ads_internal_zzbw.zzacy.zzz(com_google_android_gms_ads_internal_zzbw.zzacw.zzceq);
                }
                com_google_android_gms_ads_internal_zzbw.zzacy.zzy(com_google_android_gms_ads_internal_zzbw.zzacv.zzarc);
            }
            this.zzvr.zze("is_mraid", this.zzvw.zzacw.zzfz() ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
            this.zzvr.zze("is_mediation", this.zzvw.zzacw.zzceq ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
            if (!(this.zzvw.zzacw.zzbyo == null || this.zzvw.zzacw.zzbyo.zzuf() == null)) {
                this.zzvr.zze("is_delay_pl", this.zzvw.zzacw.zzbyo.zzuf().zzux() ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
            }
            this.zzvr.zza(this.zzvs, "ttc");
            if (zzbv.zzeo().zzpy() != null) {
                zzbv.zzeo().zzpy().zza(this.zzvr);
            }
            zzbv();
            if (this.zzvw.zzfo()) {
                zzbq();
            }
        }
        if (com_google_android_gms_internal_ads_zzajh.zzbsr != null) {
            zzbv.zzek().zza(this.zzvw.zzrt, com_google_android_gms_internal_ads_zzajh.zzbsr);
        }
    }

    protected void zzb(boolean z) {
        zzakb.m3428v("Ad finished loading.");
        this.zzvu = z;
        this.zzwa = true;
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdLoaded();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdLoaded();
            } catch (Throwable e2) {
                zzane.zzd("#007 Could not call remote method.", e2);
            }
        }
        if (this.zzvw.zzadc != null) {
            try {
                this.zzvw.zzadc.zzt();
            } catch (Throwable e22) {
                zzane.zzd("#007 Could not call remote method.", e22);
            }
        }
    }

    public boolean zzb(zzjj com_google_android_gms_internal_ads_zzjj) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: loadAd");
        zzbv.zzeq().zzhh();
        this.zzvz.clear();
        this.zzwa = false;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayo)).booleanValue()) {
            com_google_android_gms_internal_ads_zzjj = com_google_android_gms_internal_ads_zzjj.zzhv();
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayp)).booleanValue()) {
                com_google_android_gms_internal_ads_zzjj.extras.putBoolean(AdMobAdapter.NEW_BUNDLE, true);
            }
        }
        if (DeviceProperties.isSidewinder(this.zzvw.zzrt) && com_google_android_gms_internal_ads_zzjj.zzaqe != null) {
            com_google_android_gms_internal_ads_zzjj = new zzjk(com_google_android_gms_internal_ads_zzjj).zza(null).zzhw();
        }
        if (this.zzvw.zzact == null && this.zzvw.zzacu == null) {
            zzane.zzdj("Starting ad request.");
            zza(null);
            this.zzvs = this.zzvr.zzjj();
            if (com_google_android_gms_internal_ads_zzjj.zzapz) {
                zzane.zzdj("This request is sent from a test device.");
            } else {
                zzkb.zzif();
                String zzbc = zzamu.zzbc(this.zzvw.zzrt);
                zzane.zzdj(new StringBuilder(String.valueOf(zzbc).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(zzbc).append("\") to get test ads on this device.").toString());
            }
            this.zzvv.zzf(com_google_android_gms_internal_ads_zzjj);
            this.zzvu = zza(com_google_android_gms_internal_ads_zzjj, this.zzvr);
            return this.zzvu;
        }
        if (this.zzvx != null) {
            zzane.zzdk("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            zzane.zzdk("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzvx = com_google_android_gms_internal_ads_zzjj;
        return false;
    }

    public final Bundle zzba() {
        return this.zzwa ? this.zzvz : new Bundle();
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final IObjectWrapper zzbj() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdFrame");
        return ObjectWrapper.wrap(this.zzvw.zzacs);
    }

    @Nullable
    public final zzjn zzbk() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdSize");
        return this.zzvw.zzacv == null ? null : new zzms(this.zzvw.zzacv);
    }

    public final void zzbl() {
        zzbo();
    }

    public final void zzbm() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: recordManualImpression");
        if (this.zzvw.zzacw == null) {
            zzane.zzdk("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzane.zzck("Pinging manual tracking URLs.");
        if (!this.zzvw.zzacw.zzcoo) {
            List arrayList = new ArrayList();
            if (this.zzvw.zzacw.zzces != null) {
                arrayList.addAll(this.zzvw.zzacw.zzces);
            }
            if (!(this.zzvw.zzacw.zzbtw == null || this.zzvw.zzacw.zzbtw.zzbrz == null)) {
                arrayList.addAll(this.zzvw.zzacw.zzbtw.zzbrz);
            }
            if (!arrayList.isEmpty()) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, arrayList);
                this.zzvw.zzacw.zzcoo = true;
            }
        }
    }

    protected void zzbn() {
        zzakb.m3428v("Ad closing.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdClosed();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdClosed();
            } catch (Throwable e2) {
                zzane.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected final void zzbo() {
        zzakb.m3428v("Ad leaving application.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdLeftApplication();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdLeftApplication();
            } catch (Throwable e2) {
                zzane.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected final void zzbp() {
        zzakb.m3428v("Ad opening.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdOpened();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdOpened();
            } catch (Throwable e2) {
                zzane.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected void zzbq() {
        zzb(false);
    }

    public final void zzbr() {
        zzane.zzdj("Ad impression.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdImpression();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzbs() {
        zzane.zzdj("Ad clicked.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdClicked();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    protected final void zzbt() {
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoStarted();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    protected final void zzbu() {
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoCompleted();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzbv() {
        zzajh com_google_android_gms_internal_ads_zzajh = this.zzvw.zzacw;
        if (com_google_android_gms_internal_ads_zzajh != null && !TextUtils.isEmpty(com_google_android_gms_internal_ads_zzajh.zzcfl) && !com_google_android_gms_internal_ads_zzajh.zzcop && zzbv.zzeu().zzrx()) {
            zzane.zzck("Sending troubleshooting signals to the server.");
            zzbv.zzeu().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, com_google_android_gms_internal_ads_zzajh.zzcfl, this.zzvw.zzacp);
            com_google_android_gms_internal_ads_zzajh.zzcop = true;
        }
    }

    public final zzla zzbw() {
        return this.zzvw.zzadb;
    }

    public final zzkh zzbx() {
        return this.zzvw.zzada;
    }

    protected final void zzby() {
        if (this.zzwb != null) {
            zzbv.zzfa().zzn(this.zzwb);
            this.zzwb = null;
        }
    }

    @Nullable
    protected final String zzbz() {
        zzaji com_google_android_gms_internal_ads_zzaji = this.zzvw.zzacx;
        if (com_google_android_gms_internal_ads_zzaji == null) {
            return "javascript";
        }
        if (com_google_android_gms_internal_ads_zzaji.zzcos == null) {
            return "javascript";
        }
        Object obj = com_google_android_gms_internal_ads_zzaji.zzcos.zzcfq;
        if (TextUtils.isEmpty(obj)) {
            return "javascript";
        }
        try {
            return new JSONObject(obj).optInt("media_type", -1) == 0 ? null : "javascript";
        } catch (Throwable e) {
            zzane.zzc("", e);
            return "javascript";
        }
    }

    protected final List<String> zzc(List<String> list) {
        List arrayList = new ArrayList(list.size());
        for (String zzb : list) {
            arrayList.add(zzajb.zzb(zzb, this.zzvw.zzrt));
        }
        return arrayList;
    }

    protected void zzc(int i, boolean z) {
        zzane.zzdk("Failed to load ad: " + i);
        this.zzvu = z;
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdFailedToLoad(i);
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdFailedToLoad(i);
            } catch (Throwable e2) {
                zzane.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected boolean zzc(zzjj com_google_android_gms_internal_ads_zzjj) {
        if (this.zzvw.zzacs == null) {
            return false;
        }
        ViewParent parent = this.zzvw.zzacs.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzbv.zzek().zza(view, view.getContext());
    }

    protected final void zzg(View view) {
        zzbx com_google_android_gms_ads_internal_zzbx = this.zzvw.zzacs;
        if (com_google_android_gms_ads_internal_zzbx != null) {
            com_google_android_gms_ads_internal_zzbx.addView(view, zzbv.zzem().zzro());
        }
    }

    protected void zzi(int i) {
        zzc(i, false);
    }
}

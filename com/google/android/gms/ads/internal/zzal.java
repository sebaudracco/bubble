package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import com.appnext.base.p023b.C1042c;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.gmsg.zzah;
import com.google.android.gms.ads.internal.gmsg.zzai;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaej;
import com.google.android.gms.internal.ads.zzafs;
import com.google.android.gms.internal.ads.zzago;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxn;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzal extends zzi implements zzai, zzz {
    private transient boolean zzyq = false;
    private int zzyr = -1;
    private boolean zzys;
    private float zzyt;
    private boolean zzyu;
    private zzaix zzyv;
    private String zzyw;
    private final String zzyx;
    private final zzago zzyy;

    public zzal(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw) {
        super(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw);
        boolean z = com_google_android_gms_internal_ads_zzjn != null && "reward_mb".equals(com_google_android_gms_internal_ads_zzjn.zzarb);
        this.zzyx = z ? "/Rewarded" : "/Interstitial";
        this.zzyy = z ? new zzago(this.zzvw, this.zzwh, new zzan(this), this, this) : null;
    }

    @VisibleForTesting
    private static zzaji zzb(zzaji com_google_android_gms_internal_ads_zzaji) {
        try {
            String jSONObject = zzafs.zzb(com_google_android_gms_internal_ads_zzaji.zzcos).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, com_google_android_gms_internal_ads_zzaji.zzcgs.zzacp);
            zzwx com_google_android_gms_internal_ads_zzwx = new zzwx(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            zzaej com_google_android_gms_internal_ads_zzaej = com_google_android_gms_internal_ads_zzaji.zzcos;
            zzwy com_google_android_gms_internal_ads_zzwy = new zzwy(Collections.singletonList(com_google_android_gms_internal_ads_zzwx), ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), com_google_android_gms_internal_ads_zzaej.zzbsr, com_google_android_gms_internal_ads_zzaej.zzbss, "", -1, 0, 1, null, 0, -1, -1, false);
            return new zzaji(com_google_android_gms_internal_ads_zzaji.zzcgs, new zzaej(com_google_android_gms_internal_ads_zzaji.zzcgs, com_google_android_gms_internal_ads_zzaej.zzbyq, com_google_android_gms_internal_ads_zzaej.zzceo, Collections.emptyList(), Collections.emptyList(), com_google_android_gms_internal_ads_zzaej.zzcep, true, com_google_android_gms_internal_ads_zzaej.zzcer, Collections.emptyList(), com_google_android_gms_internal_ads_zzaej.zzbsu, com_google_android_gms_internal_ads_zzaej.orientation, com_google_android_gms_internal_ads_zzaej.zzcet, com_google_android_gms_internal_ads_zzaej.zzceu, com_google_android_gms_internal_ads_zzaej.zzcev, com_google_android_gms_internal_ads_zzaej.zzcew, com_google_android_gms_internal_ads_zzaej.zzcex, null, com_google_android_gms_internal_ads_zzaej.zzcez, com_google_android_gms_internal_ads_zzaej.zzare, com_google_android_gms_internal_ads_zzaej.zzcdd, com_google_android_gms_internal_ads_zzaej.zzcfa, com_google_android_gms_internal_ads_zzaej.zzcfb, com_google_android_gms_internal_ads_zzaej.zzamj, com_google_android_gms_internal_ads_zzaej.zzarf, com_google_android_gms_internal_ads_zzaej.zzarg, null, Collections.emptyList(), Collections.emptyList(), com_google_android_gms_internal_ads_zzaej.zzcfh, com_google_android_gms_internal_ads_zzaej.zzcfi, com_google_android_gms_internal_ads_zzaej.zzcdr, com_google_android_gms_internal_ads_zzaej.zzcds, com_google_android_gms_internal_ads_zzaej.zzbsr, com_google_android_gms_internal_ads_zzaej.zzbss, com_google_android_gms_internal_ads_zzaej.zzcfj, null, com_google_android_gms_internal_ads_zzaej.zzcfl, com_google_android_gms_internal_ads_zzaej.zzcfm, com_google_android_gms_internal_ads_zzaej.zzced, com_google_android_gms_internal_ads_zzaej.zzzl, 0, com_google_android_gms_internal_ads_zzaej.zzcfp, Collections.emptyList(), com_google_android_gms_internal_ads_zzaej.zzzm, com_google_android_gms_internal_ads_zzaej.zzcfq), com_google_android_gms_internal_ads_zzwy, com_google_android_gms_internal_ads_zzaji.zzacv, com_google_android_gms_internal_ads_zzaji.errorCode, com_google_android_gms_internal_ads_zzaji.zzcoh, com_google_android_gms_internal_ads_zzaji.zzcoi, null, com_google_android_gms_internal_ads_zzaji.zzcoq, null);
        } catch (Throwable e) {
            zzane.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return com_google_android_gms_internal_ads_zzaji;
        }
    }

    private final void zzb(Bundle bundle) {
        zzbv.zzek().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, "gmob-apps", bundle, false);
    }

    private final boolean zzc(boolean z) {
        return this.zzyy != null && z;
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void showInterstitial() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            this.zzyy.zzw(this.zzyu);
            return;
        }
        if (zzbv.zzfh().zzv(this.zzvw.zzrt)) {
            this.zzyw = zzbv.zzfh().zzy(this.zzvw.zzrt);
            String valueOf = String.valueOf(this.zzyw);
            String valueOf2 = String.valueOf(this.zzyx);
            this.zzyw = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzvw.zzacw == null) {
            zzane.zzdk("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazx)).booleanValue()) {
            Bundle bundle;
            valueOf2 = this.zzvw.zzrt.getApplicationContext() != null ? this.zzvw.zzrt.getApplicationContext().getPackageName() : this.zzvw.zzrt.getPackageName();
            if (!this.zzyq) {
                zzane.zzdk("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString(C1404b.f2147y, valueOf2);
                bundle.putString(C1042c.jL, "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            zzbv.zzek();
            if (!zzakk.zzaq(this.zzvw.zzrt)) {
                zzane.zzdk("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString(C1404b.f2147y, valueOf2);
                bundle.putString(C1042c.jL, "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzvw.zzfp()) {
            if (this.zzvw.zzacw.zzceq && this.zzvw.zzacw.zzbtx != null) {
                try {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzayr)).booleanValue()) {
                        this.zzvw.zzacw.zzbtx.setImmersiveMode(this.zzyu);
                    }
                    this.zzvw.zzacw.zzbtx.showInterstitial();
                } catch (Throwable e) {
                    zzane.zzc("Could not show interstitial.", e);
                    zzdj();
                }
            } else if (this.zzvw.zzacw.zzbyo == null) {
                zzane.zzdk("The interstitial failed to load.");
            } else if (this.zzvw.zzacw.zzbyo.zzuj()) {
                zzane.zzdk("The interstitial is already showing.");
            } else {
                Bitmap zzar;
                this.zzvw.zzacw.zzbyo.zzai(true);
                this.zzvw.zzj(this.zzvw.zzacw.zzbyo.getView());
                if (this.zzvw.zzacw.zzcob != null) {
                    this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw);
                }
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    zzajh com_google_android_gms_internal_ads_zzajh = this.zzvw.zzacw;
                    if (com_google_android_gms_internal_ads_zzajh.zzfz()) {
                        new zzfp(this.zzvw.zzrt, com_google_android_gms_internal_ads_zzajh.zzbyo.getView()).zza(com_google_android_gms_internal_ads_zzajh.zzbyo);
                    } else {
                        com_google_android_gms_internal_ads_zzajh.zzbyo.zzuf().zza(new zzam(this, com_google_android_gms_internal_ads_zzajh));
                    }
                }
                if (this.zzvw.zzze) {
                    zzbv.zzek();
                    zzar = zzakk.zzar(this.zzvw.zzrt);
                } else {
                    zzar = null;
                }
                this.zzyr = zzbv.zzfe().zzb(zzar);
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzbbg)).booleanValue() || zzar == null) {
                    zzaq com_google_android_gms_ads_internal_zzaq = new zzaq(this.zzvw.zzze, zzdi(), false, 0.0f, -1, this.zzyu, this.zzvw.zzacw.zzzl, this.zzvw.zzacw.zzzm);
                    int requestedOrientation = this.zzvw.zzacw.zzbyo.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzvw.zzacw.orientation;
                    }
                    AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzvw.zzacw.zzbyo, requestedOrientation, this.zzvw.zzacr, this.zzvw.zzacw.zzcev, com_google_android_gms_ads_internal_zzaq);
                    zzbv.zzei();
                    zzl.zza(this.zzvw.zzrt, adOverlayInfoParcel, true);
                    return;
                }
                new zzao(this, this.zzyr).zzqo();
            }
        }
    }

    protected final zzaqw zza(zzaji com_google_android_gms_internal_ads_zzaji, @Nullable zzx com_google_android_gms_ads_internal_zzx, @Nullable zzait com_google_android_gms_internal_ads_zzait) throws zzarg {
        zzbv.zzel();
        zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, com_google_android_gms_internal_ads_zzaji.zzcoq);
        zza.zzuf().zza(this, this, null, this, this, ((Boolean) zzkb.zzik().zzd(zznk.zzaxe)).booleanValue(), this, com_google_android_gms_ads_internal_zzx, this, com_google_android_gms_internal_ads_zzait);
        zza(zza);
        zza.zzdr(com_google_android_gms_internal_ads_zzaji.zzcgs.zzcdi);
        zza.zza("/reward", new zzah(this));
        return zza;
    }

    public final void zza(zzaji com_google_android_gms_internal_ads_zzaji, zznx com_google_android_gms_internal_ads_zznx) {
        Object obj = 1;
        if (com_google_android_gms_internal_ads_zzaji.errorCode != -2) {
            super.zza(com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zznx);
            return;
        }
        if (zzc(com_google_android_gms_internal_ads_zzaji.zzcod != null)) {
            this.zzyy.zzou();
            return;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
            if (com_google_android_gms_internal_ads_zzaji.zzcos.zzceq) {
                obj = null;
            }
            if (zza.zza(com_google_android_gms_internal_ads_zzaji.zzcgs.zzccv) && r1 != null) {
                this.zzvw.zzacx = zzb(com_google_android_gms_internal_ads_zzaji);
            }
            super.zza(this.zzvw.zzacx, com_google_android_gms_internal_ads_zznx);
            return;
        }
        super.zza(com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zznx);
    }

    public final void zza(boolean z, float f) {
        this.zzys = z;
        this.zzyt = f;
    }

    public final boolean zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        if (zzc(com_google_android_gms_internal_ads_zzajh2.zzceq)) {
            return zzago.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2);
        }
        if (!super.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2)) {
            return false;
        }
        if (!(this.zzvw.zzfo() || this.zzvw.zzadu == null || com_google_android_gms_internal_ads_zzajh2.zzcob == null)) {
            this.zzvy.zza(this.zzvw.zzacv, com_google_android_gms_internal_ads_zzajh2, this.zzvw.zzadu);
        }
        zzb(com_google_android_gms_internal_ads_zzajh2, false);
        return true;
    }

    protected final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        if (this.zzvw.zzfo() && com_google_android_gms_internal_ads_zzajh.zzbyo != null) {
            zzbv.zzem();
            zzakq.zzi(com_google_android_gms_internal_ads_zzajh.zzbyo);
        }
        return this.zzvv.zzdz();
    }

    public final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zznx com_google_android_gms_internal_ads_zznx) {
        if (this.zzvw.zzacw != null) {
            zzane.zzdk("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzyv == null && zza.zza(com_google_android_gms_internal_ads_zzjj) && zzbv.zzfh().zzv(this.zzvw.zzrt) && !TextUtils.isEmpty(this.zzvw.zzacp)) {
            this.zzyv = new zzaix(this.zzvw.zzrt, this.zzvw.zzacp);
        }
        return super.zza(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zznx);
    }

    public final void zzb(zzaig com_google_android_gms_internal_ads_zzaig) {
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            zza(this.zzyy.zzd(com_google_android_gms_internal_ads_zzaig));
            return;
        }
        if (this.zzvw.zzacw != null) {
            if (this.zzvw.zzacw.zzcfg != null) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcfg);
            }
            if (this.zzvw.zzacw.zzcfe != null) {
                com_google_android_gms_internal_ads_zzaig = this.zzvw.zzacw.zzcfe;
            }
        }
        zza(com_google_android_gms_internal_ads_zzaig);
    }

    protected final void zzbn() {
        zzdj();
        super.zzbn();
    }

    protected final void zzbq() {
        zzaqw com_google_android_gms_internal_ads_zzaqw = this.zzvw.zzacw != null ? this.zzvw.zzacw.zzbyo : null;
        zzaji com_google_android_gms_internal_ads_zzaji = this.zzvw.zzacx;
        if (!(com_google_android_gms_internal_ads_zzaji == null || com_google_android_gms_internal_ads_zzaji.zzcos == null || !com_google_android_gms_internal_ads_zzaji.zzcos.zzcfp || com_google_android_gms_internal_ads_zzaqw == null || !zzbv.zzfa().zzi(this.zzvw.zzrt))) {
            this.zzwb = zzbv.zzfa().zza(this.zzvw.zzacr.zzcve + "." + this.zzvw.zzacr.zzcvf, com_google_android_gms_internal_ads_zzaqw.getWebView(), "", "javascript", zzbz());
            if (!(this.zzwb == null || com_google_android_gms_internal_ads_zzaqw.getView() == null)) {
                zzbv.zzfa().zza(this.zzwb, com_google_android_gms_internal_ads_zzaqw.getView());
                zzbv.zzfa().zzm(this.zzwb);
            }
        }
        super.zzbq();
        this.zzyq = true;
    }

    public final void zzcb() {
        super.zzcb();
        this.zzvy.zzh(this.zzvw.zzacw);
        if (this.zzyv != null) {
            this.zzyv.zzx(false);
        }
        zzby();
    }

    public final void zzcc() {
        recordImpression();
        super.zzcc();
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            zzasc zzuf = this.zzvw.zzacw.zzbyo.zzuf();
            if (zzuf != null) {
                zzuf.zzuz();
            }
        }
        if (!(!zzbv.zzfh().zzv(this.zzvw.zzrt) || this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            zzbv.zzfh().zzd(this.zzvw.zzacw.zzbyo.getContext(), this.zzyw);
        }
        if (this.zzyv != null) {
            this.zzyv.zzx(true);
        }
        if (this.zzwb != null && this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            this.zzvw.zzacw.zzbyo.zza("onSdkImpression", new HashMap());
        }
    }

    public final void zzcz() {
        zzd zzub = this.zzvw.zzacw.zzbyo.zzub();
        if (zzub != null) {
            zzub.close();
        }
    }

    public final void zzd(boolean z) {
        this.zzvw.zzze = z;
    }

    protected final boolean zzdi() {
        if (!(this.zzvw.zzrt instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzvw.zzrt).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        return (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
    }

    public final void zzdj() {
        zzbv.zzfe().zzb(Integer.valueOf(this.zzyr));
        if (this.zzvw.zzfo()) {
            this.zzvw.zzfm();
            this.zzvw.zzacw = null;
            this.zzvw.zzze = false;
            this.zzyq = false;
        }
    }

    public final void zzdk() {
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            this.zzyy.zzov();
            zzbt();
            return;
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzcog == null)) {
            zzbv.zzek();
            zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcog);
        }
        zzbt();
    }

    public final void zzdl() {
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            this.zzyy.zzow();
        }
        zzbu();
    }
}

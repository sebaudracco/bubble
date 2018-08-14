package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzox;
import com.google.android.gms.internal.ads.zzoy;
import com.google.android.gms.internal.ads.zzoz;
import com.google.android.gms.internal.ads.zzpa;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzyf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzq extends zzd implements zzpa {
    private boolean zzvm;
    private zzajh zzwr;
    private boolean zzws = false;

    public zzq(Context context, zzw com_google_android_gms_ads_internal_zzw, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang) {
        super(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw);
    }

    private static zzajh zza(zzaji com_google_android_gms_internal_ads_zzaji, int i) {
        return new zzajh(com_google_android_gms_internal_ads_zzaji.zzcgs.zzccv, null, com_google_android_gms_internal_ads_zzaji.zzcos.zzbsn, i, com_google_android_gms_internal_ads_zzaji.zzcos.zzbso, com_google_android_gms_internal_ads_zzaji.zzcos.zzces, com_google_android_gms_internal_ads_zzaji.zzcos.orientation, com_google_android_gms_internal_ads_zzaji.zzcos.zzbsu, com_google_android_gms_internal_ads_zzaji.zzcgs.zzccy, com_google_android_gms_internal_ads_zzaji.zzcos.zzceq, null, null, null, com_google_android_gms_internal_ads_zzaji.zzcod, null, com_google_android_gms_internal_ads_zzaji.zzcos.zzcer, com_google_android_gms_internal_ads_zzaji.zzacv, com_google_android_gms_internal_ads_zzaji.zzcos.zzcep, com_google_android_gms_internal_ads_zzaji.zzcoh, com_google_android_gms_internal_ads_zzaji.zzcoi, com_google_android_gms_internal_ads_zzaji.zzcos.zzcev, com_google_android_gms_internal_ads_zzaji.zzcob, null, com_google_android_gms_internal_ads_zzaji.zzcos.zzcfe, com_google_android_gms_internal_ads_zzaji.zzcos.zzcff, com_google_android_gms_internal_ads_zzaji.zzcos.zzcff, com_google_android_gms_internal_ads_zzaji.zzcos.zzcfh, com_google_android_gms_internal_ads_zzaji.zzcos.zzcfi, null, com_google_android_gms_internal_ads_zzaji.zzcos.zzbsr, com_google_android_gms_internal_ads_zzaji.zzcos.zzcfl, com_google_android_gms_internal_ads_zzaji.zzcoq, com_google_android_gms_internal_ads_zzaji.zzcos.zzzl, com_google_android_gms_internal_ads_zzaji.zzcor, com_google_android_gms_internal_ads_zzaji.zzcos.zzcfp, com_google_android_gms_internal_ads_zzaji.zzcos.zzbsp, com_google_android_gms_internal_ads_zzaji.zzcos.zzzm, com_google_android_gms_internal_ads_zzaji.zzcos.zzcfq);
    }

    private final void zza(zzov com_google_android_gms_internal_ads_zzov) {
        zzakk.zzcrm.post(new zzs(this, com_google_android_gms_internal_ads_zzov));
    }

    private final boolean zzb(zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        zzd(null);
        if (this.zzvw.zzfo()) {
            try {
                zzyf zzmu = com_google_android_gms_internal_ads_zzajh2.zzbtx != null ? com_google_android_gms_internal_ads_zzajh2.zzbtx.zzmu() : null;
                zzxz zzmo = com_google_android_gms_internal_ads_zzajh2.zzbtx != null ? com_google_android_gms_internal_ads_zzajh2.zzbtx.zzmo() : null;
                zzyc zzmp = com_google_android_gms_internal_ads_zzajh2.zzbtx != null ? com_google_android_gms_internal_ads_zzajh2.zzbtx.zzmp() : null;
                zzqs zzmt = com_google_android_gms_internal_ads_zzajh2.zzbtx != null ? com_google_android_gms_internal_ads_zzajh2.zzbtx.zzmt() : null;
                String zzc = zzd.zzc(com_google_android_gms_internal_ads_zzajh2);
                zzov com_google_android_gms_internal_ads_zzov;
                if (zzmu == null || this.zzvw.zzadg == null) {
                    if (zzmo != null) {
                        if (this.zzvw.zzadg != null) {
                            com_google_android_gms_internal_ads_zzov = new zzov(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz() != null ? zzmo.zzjz() : null, zzmo.getCallToAction(), null, zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), null, zzmo.getVideoController(), zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), zzc, zzmo.getExtras());
                            com_google_android_gms_internal_ads_zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmo, (zzpb) com_google_android_gms_internal_ads_zzov));
                            zza(com_google_android_gms_internal_ads_zzov);
                        }
                    }
                    if (zzmo != null) {
                        if (this.zzvw.zzade != null) {
                            zzoo com_google_android_gms_internal_ads_zzoo = new zzoo(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz() != null ? zzmo.zzjz() : null, zzmo.getCallToAction(), zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), null, zzmo.getExtras(), zzmo.getVideoController(), zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), zzc);
                            com_google_android_gms_internal_ads_zzoo.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmo, (zzpb) com_google_android_gms_internal_ads_zzoo));
                            zzakk.zzcrm.post(new zzt(this, com_google_android_gms_internal_ads_zzoo));
                        }
                    }
                    if (zzmp != null && this.zzvw.zzadg != null) {
                        com_google_android_gms_internal_ads_zzov = new zzov(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), -1.0d, null, null, null, zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc, zzmp.getExtras());
                        com_google_android_gms_internal_ads_zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) com_google_android_gms_internal_ads_zzov));
                        zza(com_google_android_gms_internal_ads_zzov);
                    } else if (zzmp != null && this.zzvw.zzadf != null) {
                        zzoq com_google_android_gms_internal_ads_zzoq = new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), null, zzmp.getExtras(), zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc);
                        com_google_android_gms_internal_ads_zzoq.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) com_google_android_gms_internal_ads_zzoq));
                        zzakk.zzcrm.post(new zzu(this, com_google_android_gms_internal_ads_zzoq));
                    } else if (zzmt == null || this.zzvw.zzadi == null || this.zzvw.zzadi.get(zzmt.getCustomTemplateId()) == null) {
                        zzane.zzdk("No matching mapper/listener for retrieved native ad template.");
                        zzi(0);
                        return false;
                    } else {
                        zzakk.zzcrm.post(new zzv(this, zzmt));
                    }
                } else {
                    com_google_android_gms_internal_ads_zzov = new zzov(zzmu.getHeadline(), zzmu.getImages(), zzmu.getBody(), zzmu.zzjz() != null ? zzmu.zzjz() : null, zzmu.getCallToAction(), zzmu.getAdvertiser(), zzmu.getStarRating(), zzmu.getStore(), zzmu.getPrice(), null, zzmu.getVideoController(), zzmu.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmu.zzmw()) : null, zzmu.zzke(), zzc, zzmu.getExtras());
                    com_google_android_gms_internal_ads_zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmu, (zzpb) com_google_android_gms_internal_ads_zzov));
                    zza(com_google_android_gms_internal_ads_zzov);
                }
                return super.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2);
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                zzi(0);
                return false;
            }
        }
        zzane.zzdk("Native ad does not have custom rendering mode.");
        zzi(0);
        return false;
    }

    private final boolean zzc(zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        View zze = zzas.zze(com_google_android_gms_internal_ads_zzajh2);
        if (zze == null) {
            return false;
        }
        View nextView = this.zzvw.zzacs.getNextView();
        if (nextView != null) {
            if (nextView instanceof zzaqw) {
                ((zzaqw) nextView).destroy();
            }
            this.zzvw.zzacs.removeView(nextView);
        }
        if (!zzas.zzf(com_google_android_gms_internal_ads_zzajh2)) {
            try {
                zzg(zze);
            } catch (Throwable th) {
                zzbv.zzeo().zza(th, "AdLoaderManager.swapBannerViews");
                zzane.zzc("Could not add mediation view to view hierarchy.", th);
                return false;
            }
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (com_google_android_gms_internal_ads_zzajh != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setMinimumWidth(zzbk().widthPixels);
        this.zzvw.zzacs.setMinimumHeight(zzbk().heightPixels);
        this.zzvw.zzacs.requestLayout();
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    @Nullable
    private final zzwy zzcw() {
        return (this.zzvw.zzacw == null || !this.zzvw.zzacw.zzceq) ? null : this.zzvw.zzacw.zzcod;
    }

    @Nullable
    public final zzlo getVideoController() {
        return null;
    }

    public final void pause() {
        if (this.zzws) {
            super.pause();
            return;
        }
        throw new IllegalStateException("Native Ad does not support pause().");
    }

    public final void resume() {
        if (this.zzws) {
            super.resume();
            return;
        }
        throw new IllegalStateException("Native Ad does not support resume().");
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }

    public final void zza(zzaji com_google_android_gms_internal_ads_zzaji, zznx com_google_android_gms_internal_ads_zznx) {
        this.zzwr = null;
        if (com_google_android_gms_internal_ads_zzaji.errorCode != -2) {
            this.zzwr = zza(com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzaji.errorCode);
        } else if (!com_google_android_gms_internal_ads_zzaji.zzcos.zzceq) {
            zzane.zzdk("partialAdState is not mediation");
            this.zzwr = zza(com_google_android_gms_internal_ads_zzaji, 0);
        }
        if (this.zzwr != null) {
            zzakk.zzcrm.post(new zzr(this));
            return;
        }
        if (com_google_android_gms_internal_ads_zzaji.zzacv != null) {
            this.zzvw.zzacv = com_google_android_gms_internal_ads_zzaji.zzacv;
        }
        this.zzvw.zzadv = 0;
        zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
        zzbv.zzej();
        com_google_android_gms_ads_internal_zzbw.zzacu = zzabl.zza(this.zzvw.zzrt, this, com_google_android_gms_internal_ads_zzaji, this.zzvw.zzacq, null, this.zzwh, this, com_google_android_gms_internal_ads_zznx);
    }

    public final void zza(zzod com_google_android_gms_internal_ads_zzod) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }

    public final void zza(zzox com_google_android_gms_internal_ads_zzox) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zza(zzoz com_google_android_gms_internal_ads_zzoz) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    protected final boolean zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        if (!this.zzvw.zzfo()) {
            throw new IllegalStateException("AdLoader API does not support custom rendering.");
        } else if (com_google_android_gms_internal_ads_zzajh2.zzceq) {
            if (com_google_android_gms_internal_ads_zzajh2.zzbtw != null && com_google_android_gms_internal_ads_zzajh2.zzbtw.zzmf()) {
                boolean z;
                if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
                    this.zzvw.zzacs.zzfr().zzdb(com_google_android_gms_internal_ads_zzajh2.zzcev);
                }
                if (!super.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2)) {
                    z = false;
                } else if (!this.zzvw.zzfo() || zzc(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2)) {
                    if (!this.zzvw.zzfp()) {
                        super.zza(com_google_android_gms_internal_ads_zzajh2, false);
                    }
                    z = true;
                } else {
                    zzi(0);
                    z = false;
                }
                if (!z) {
                    return false;
                }
                this.zzws = true;
            } else if (com_google_android_gms_internal_ads_zzajh2.zzbtw == null || !com_google_android_gms_internal_ads_zzajh2.zzbtw.zzmg()) {
                zzi(0);
                zzane.zzdk("Response is neither banner nor native.");
                return false;
            } else if (!zzb(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2)) {
                return false;
            }
            zze(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2)})));
            return true;
        } else {
            zzi(0);
            zzane.zzdk("newState is not mediation.");
            return false;
        }
    }

    protected final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        return false;
    }

    protected final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof zzoz) {
            ((zzoz) unwrap).zzkl();
        }
        super.zzb(this.zzvw.zzacw, false);
    }

    public final boolean zzb(zzjj com_google_android_gms_internal_ads_zzjj) {
        Object obj = (this.zzvw.zzadn != null && this.zzvw.zzadn.size() == 1 && ((Integer) this.zzvw.zzadn.get(0)).intValue() == 2) ? 1 : null;
        if (obj != null) {
            zzane.m3427e("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            zzi(0);
            return false;
        } else if (this.zzvw.zzadm == null) {
            return super.zzb(com_google_android_gms_internal_ads_zzjj);
        } else {
            if (com_google_android_gms_internal_ads_zzjj.zzaqb != this.zzvm) {
                int i = com_google_android_gms_internal_ads_zzjj.versionCode;
                long j = com_google_android_gms_internal_ads_zzjj.zzapw;
                Bundle bundle = com_google_android_gms_internal_ads_zzjj.extras;
                int i2 = com_google_android_gms_internal_ads_zzjj.zzapx;
                List list = com_google_android_gms_internal_ads_zzjj.zzapy;
                boolean z = com_google_android_gms_internal_ads_zzjj.zzapz;
                int i3 = com_google_android_gms_internal_ads_zzjj.zzaqa;
                boolean z2 = com_google_android_gms_internal_ads_zzjj.zzaqb || this.zzvm;
                com_google_android_gms_internal_ads_zzjj = new zzjj(i, j, bundle, i2, list, z, i3, z2, com_google_android_gms_internal_ads_zzjj.zzaqc, com_google_android_gms_internal_ads_zzjj.zzaqd, com_google_android_gms_internal_ads_zzjj.zzaqe, com_google_android_gms_internal_ads_zzjj.zzaqf, com_google_android_gms_internal_ads_zzjj.zzaqg, com_google_android_gms_internal_ads_zzjj.zzaqh, com_google_android_gms_internal_ads_zzjj.zzaqi, com_google_android_gms_internal_ads_zzjj.zzaqj, com_google_android_gms_internal_ads_zzjj.zzaqk, com_google_android_gms_internal_ads_zzjj.zzaql);
            }
            return super.zzb(com_google_android_gms_internal_ads_zzjj);
        }
    }

    protected final void zzbq() {
        super.zzbq();
        zzajh com_google_android_gms_internal_ads_zzajh = this.zzvw.zzacw;
        if (com_google_android_gms_internal_ads_zzajh != null && com_google_android_gms_internal_ads_zzajh.zzbtw != null && com_google_android_gms_internal_ads_zzajh.zzbtw.zzmf() && this.zzvw.zzadm != null) {
            try {
                this.zzvw.zzadm.zza(this, ObjectWrapper.wrap(this.zzvw.zzrt));
                super.zzb(this.zzvw.zzacw, false);
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzce() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty) || this.zzvw.zzacw.zzbtw == null || !this.zzvw.zzacw.zzbtw.zzmg()) {
            super.zzce();
        } else {
            zzbs();
        }
    }

    public final void zzcj() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty) || this.zzvw.zzacw.zzbtw == null || !this.zzvw.zzacw.zzbtw.zzmg()) {
            super.zzcj();
        } else {
            zzbr();
        }
    }

    public final void zzcr() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzcs() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzct() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final boolean zzcu() {
        return zzcw() != null ? zzcw().zzbta : false;
    }

    public final boolean zzcv() {
        return zzcw() != null ? zzcw().zzbtb : false;
    }

    public final void zzd(@Nullable List<String> list) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzvw.zzads = list;
    }

    public final void zze(List<Integer> list) {
        Preconditions.checkMainThread("setAllowedAdTypes must be called on the main UI thread.");
        this.zzvw.zzadn = list;
    }

    public final void zzi(View view) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    @Nullable
    public final zzrc zzr(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return (zzrc) this.zzvw.zzadh.get(str);
    }
}

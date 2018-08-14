package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzacm;
import com.google.android.gms.internal.ads.zzacq;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzaoj;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzev;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlr;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzos;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzox;
import com.google.android.gms.internal.ads.zzoy;
import com.google.android.gms.internal.ads.zzoz;
import com.google.android.gms.internal.ads.zzpa;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzpd;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzyf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbc extends zzd implements zzpa {
    private final Object mLock;
    private zzaqw zzaaa;
    @Nullable
    private zzaqw zzaab;
    private int zzaac;
    @GuardedBy("mLock")
    private zzacm zzaad;
    private final String zzaae;
    private boolean zzwl;
    @VisibleForTesting
    private boolean zzzy;
    private zzaoj<zzpb> zzzz;

    public zzbc(Context context, zzw com_google_android_gms_ads_internal_zzw, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang) {
        this(context, com_google_android_gms_ads_internal_zzw, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, false);
    }

    public zzbc(Context context, zzw com_google_android_gms_ads_internal_zzw, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, boolean z) {
        super(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw);
        this.mLock = new Object();
        this.zzzz = new zzaoj();
        this.zzaac = 1;
        this.zzaae = UUID.randomUUID().toString();
        this.zzzy = z;
    }

    private static zzov zza(zzpb com_google_android_gms_internal_ads_zzpb) {
        zzov com_google_android_gms_internal_ads_zzov = null;
        Object obj = null;
        if (com_google_android_gms_internal_ads_zzpb instanceof zzoq) {
            zzoq com_google_android_gms_internal_ads_zzoq = (zzoq) com_google_android_gms_internal_ads_zzpb;
            com_google_android_gms_internal_ads_zzov = new zzov(com_google_android_gms_internal_ads_zzoq.getHeadline(), com_google_android_gms_internal_ads_zzoq.getImages(), com_google_android_gms_internal_ads_zzoq.getBody(), com_google_android_gms_internal_ads_zzoq.zzkg(), com_google_android_gms_internal_ads_zzoq.getCallToAction(), com_google_android_gms_internal_ads_zzoq.getAdvertiser(), -1.0d, null, null, com_google_android_gms_internal_ads_zzoq.zzkc(), com_google_android_gms_internal_ads_zzoq.getVideoController(), com_google_android_gms_internal_ads_zzoq.zzkd(), com_google_android_gms_internal_ads_zzoq.zzke(), com_google_android_gms_internal_ads_zzoq.getMediationAdapterClassName(), com_google_android_gms_internal_ads_zzoq.getExtras());
            obj = com_google_android_gms_internal_ads_zzoq.zzka() != null ? ObjectWrapper.unwrap(com_google_android_gms_internal_ads_zzoq.zzka()) : null;
        } else if (com_google_android_gms_internal_ads_zzpb instanceof zzoo) {
            zzoo com_google_android_gms_internal_ads_zzoo = (zzoo) com_google_android_gms_internal_ads_zzpb;
            com_google_android_gms_internal_ads_zzov = new zzov(com_google_android_gms_internal_ads_zzoo.getHeadline(), com_google_android_gms_internal_ads_zzoo.getImages(), com_google_android_gms_internal_ads_zzoo.getBody(), com_google_android_gms_internal_ads_zzoo.zzjz(), com_google_android_gms_internal_ads_zzoo.getCallToAction(), null, com_google_android_gms_internal_ads_zzoo.getStarRating(), com_google_android_gms_internal_ads_zzoo.getStore(), com_google_android_gms_internal_ads_zzoo.getPrice(), com_google_android_gms_internal_ads_zzoo.zzkc(), com_google_android_gms_internal_ads_zzoo.getVideoController(), com_google_android_gms_internal_ads_zzoo.zzkd(), com_google_android_gms_internal_ads_zzoo.zzke(), com_google_android_gms_internal_ads_zzoo.getMediationAdapterClassName(), com_google_android_gms_internal_ads_zzoo.getExtras());
            obj = com_google_android_gms_internal_ads_zzoo.zzka() != null ? ObjectWrapper.unwrap(com_google_android_gms_internal_ads_zzoo.zzka()) : null;
        }
        if (obj instanceof zzpd) {
            com_google_android_gms_internal_ads_zzov.zzb((zzpd) obj);
        }
        return com_google_android_gms_internal_ads_zzov;
    }

    private static void zza(zzbw com_google_android_gms_ads_internal_zzbw, zzbw com_google_android_gms_ads_internal_zzbw2) {
        if (com_google_android_gms_ads_internal_zzbw2.zzade == null) {
            com_google_android_gms_ads_internal_zzbw2.zzade = com_google_android_gms_ads_internal_zzbw.zzade;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzadf == null) {
            com_google_android_gms_ads_internal_zzbw2.zzadf = com_google_android_gms_ads_internal_zzbw.zzadf;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzadh == null) {
            com_google_android_gms_ads_internal_zzbw2.zzadh = com_google_android_gms_ads_internal_zzbw.zzadh;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzadi == null) {
            com_google_android_gms_ads_internal_zzbw2.zzadi = com_google_android_gms_ads_internal_zzbw.zzadi;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzadk == null) {
            com_google_android_gms_ads_internal_zzbw2.zzadk = com_google_android_gms_ads_internal_zzbw.zzadk;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzadj == null) {
            com_google_android_gms_ads_internal_zzbw2.zzadj = com_google_android_gms_ads_internal_zzbw.zzadj;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzads == null) {
            com_google_android_gms_ads_internal_zzbw2.zzads = com_google_android_gms_ads_internal_zzbw.zzads;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzacy == null) {
            com_google_android_gms_ads_internal_zzbw2.zzacy = com_google_android_gms_ads_internal_zzbw.zzacy;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzadt == null) {
            com_google_android_gms_ads_internal_zzbw2.zzadt = com_google_android_gms_ads_internal_zzbw.zzadt;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzacz == null) {
            com_google_android_gms_ads_internal_zzbw2.zzacz = com_google_android_gms_ads_internal_zzbw.zzacz;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzada == null) {
            com_google_android_gms_ads_internal_zzbw2.zzada = com_google_android_gms_ads_internal_zzbw.zzada;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzacv == null) {
            com_google_android_gms_ads_internal_zzbw2.zzacv = com_google_android_gms_ads_internal_zzbw.zzacv;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzacw == null) {
            com_google_android_gms_ads_internal_zzbw2.zzacw = com_google_android_gms_ads_internal_zzbw.zzacw;
        }
        if (com_google_android_gms_ads_internal_zzbw2.zzacx == null) {
            com_google_android_gms_ads_internal_zzbw2.zzacx = com_google_android_gms_ads_internal_zzbw.zzacx;
        }
    }

    private final void zza(zzoo com_google_android_gms_internal_ads_zzoo) {
        zzakk.zzcrm.post(new zzbg(this, com_google_android_gms_internal_ads_zzoo));
    }

    private final void zza(zzoq com_google_android_gms_internal_ads_zzoq) {
        zzakk.zzcrm.post(new zzbi(this, com_google_android_gms_internal_ads_zzoq));
    }

    private final void zza(zzov com_google_android_gms_internal_ads_zzov) {
        zzakk.zzcrm.post(new zzbh(this, com_google_android_gms_internal_ads_zzov));
    }

    private final boolean zzcp() {
        return this.zzvw.zzacw != null && this.zzvw.zzacw.zzcfp;
    }

    @Nullable
    private final zzwy zzcw() {
        return (this.zzvw.zzacw == null || !this.zzvw.zzacw.zzceq) ? null : this.zzvw.zzacw.zzcod;
    }

    private final void zzdx() {
        zzacm zzdr = zzdr();
        if (zzdr != null) {
            zzdr.zzmc();
        }
    }

    public final String getAdUnitId() {
        return this.zzvw.zzacp;
    }

    public final String getUuid() {
        return this.zzaae;
    }

    public final void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public final void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public final void zza(zzaaw com_google_android_gms_internal_ads_zzaaw) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public final void zza(zzaji com_google_android_gms_internal_ads_zzaji, zznx com_google_android_gms_internal_ads_zznx) {
        Throwable e;
        if (com_google_android_gms_internal_ads_zzaji.zzacv != null) {
            this.zzvw.zzacv = com_google_android_gms_internal_ads_zzaji.zzacv;
        }
        if (com_google_android_gms_internal_ads_zzaji.errorCode != -2) {
            zzakk.zzcrm.post(new zzbd(this, com_google_android_gms_internal_ads_zzaji));
            return;
        }
        int i = com_google_android_gms_internal_ads_zzaji.zzcgs.zzceg;
        if (i == 1) {
            this.zzvw.zzadv = 0;
            zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
            zzbv.zzej();
            com_google_android_gms_ads_internal_zzbw.zzacu = zzabl.zza(this.zzvw.zzrt, this, com_google_android_gms_internal_ads_zzaji, this.zzvw.zzacq, null, this.zzwh, this, com_google_android_gms_internal_ads_zznx);
            String str = "AdRenderer: ";
            String valueOf = String.valueOf(this.zzvw.zzacu.getClass().getName());
            zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            int i2;
            JSONArray jSONArray2 = new JSONObject(com_google_android_gms_internal_ads_zzaji.zzcos.zzceo).getJSONArray("slots");
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                JSONArray jSONArray3 = jSONArray2.getJSONObject(i3).getJSONArray("ads");
                for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                    jSONArray.put(jSONArray3.get(i4));
                }
            }
            zzdx();
            List arrayList = new ArrayList();
            for (i2 = 0; i2 < i; i2++) {
                arrayList.add(zzaki.zza(new zzbe(this, i2, jSONArray, i, com_google_android_gms_internal_ads_zzaji)));
            }
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                try {
                    zzakk.zzcrm.post(new zzbf(this, (zzpb) ((zzanz) arrayList.get(i2)).get(((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), TimeUnit.MILLISECONDS), i2, arrayList));
                } catch (Throwable e2) {
                    zzane.zzc("", e2);
                    Thread.currentThread().interrupt();
                } catch (CancellationException e3) {
                    e2 = e3;
                    zzane.zzc("", e2);
                } catch (ExecutionException e4) {
                    e2 = e4;
                    zzane.zzc("", e2);
                } catch (TimeoutException e5) {
                    e2 = e5;
                    zzane.zzc("", e2);
                }
            }
        } catch (Throwable e22) {
            zzane.zzc("Malformed native ad response", e22);
            zzi(0);
        }
    }

    public final void zza(zzod com_google_android_gms_internal_ads_zzod) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public final void zza(zzox com_google_android_gms_internal_ads_zzox) {
        if (this.zzaaa != null) {
            this.zzaaa.zzb(com_google_android_gms_internal_ads_zzox);
        }
    }

    public final void zza(zzoz com_google_android_gms_internal_ads_zzoz) {
        if (this.zzvw.zzacw.zzcob != null) {
            zzbv.zzeo().zzqd().zza(this.zzvw.zzacv, this.zzvw.zzacw, new zzev(com_google_android_gms_internal_ads_zzoz), null);
        }
    }

    protected final boolean zza(zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        zzd(null);
        if (this.zzvw.zzfo()) {
            if (com_google_android_gms_internal_ads_zzajh2.zzceq) {
                zzdx();
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
                                zza(com_google_android_gms_internal_ads_zzoo);
                            }
                        }
                        if (zzmp != null && this.zzvw.zzadg != null) {
                            com_google_android_gms_internal_ads_zzov = new zzov(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), -1.0d, null, null, null, zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc, zzmp.getExtras());
                            com_google_android_gms_internal_ads_zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) com_google_android_gms_internal_ads_zzov));
                            zza(com_google_android_gms_internal_ads_zzov);
                        } else if (zzmp != null && this.zzvw.zzadf != null) {
                            zzoq com_google_android_gms_internal_ads_zzoq = new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), null, zzmp.getExtras(), zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc);
                            com_google_android_gms_internal_ads_zzoq.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) com_google_android_gms_internal_ads_zzoq));
                            zza(com_google_android_gms_internal_ads_zzoq);
                        } else if (zzmt == null || this.zzvw.zzadi == null || this.zzvw.zzadi.get(zzmt.getCustomTemplateId()) == null) {
                            zzane.zzdk("No matching mapper/listener for retrieved native ad template.");
                            zzi(0);
                            return false;
                        } else {
                            zzakk.zzcrm.post(new zzbk(this, zzmt));
                        }
                    } else {
                        com_google_android_gms_internal_ads_zzov = new zzov(zzmu.getHeadline(), zzmu.getImages(), zzmu.getBody(), zzmu.zzjz() != null ? zzmu.zzjz() : null, zzmu.getCallToAction(), zzmu.getAdvertiser(), zzmu.getStarRating(), zzmu.getStore(), zzmu.getPrice(), null, zzmu.getVideoController(), zzmu.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmu.zzmw()) : null, zzmu.zzke(), zzc, zzmu.getExtras());
                        com_google_android_gms_internal_ads_zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmu, (zzpb) com_google_android_gms_internal_ads_zzov));
                        zza(com_google_android_gms_internal_ads_zzov);
                    }
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            } else {
                zzpb com_google_android_gms_internal_ads_zzpb = com_google_android_gms_internal_ads_zzajh2.zzcoj;
                if (this.zzzy) {
                    this.zzzz.set(com_google_android_gms_internal_ads_zzpb);
                } else if ((com_google_android_gms_internal_ads_zzpb instanceof zzoq) && this.zzvw.zzadg != null) {
                    zza(zza(com_google_android_gms_internal_ads_zzajh2.zzcoj));
                } else if ((com_google_android_gms_internal_ads_zzpb instanceof zzoq) && this.zzvw.zzadf != null) {
                    zza((zzoq) com_google_android_gms_internal_ads_zzajh2.zzcoj);
                } else if ((com_google_android_gms_internal_ads_zzpb instanceof zzoo) && this.zzvw.zzadg != null) {
                    zza(zza(com_google_android_gms_internal_ads_zzajh2.zzcoj));
                } else if ((com_google_android_gms_internal_ads_zzpb instanceof zzoo) && this.zzvw.zzade != null) {
                    zza((zzoo) com_google_android_gms_internal_ads_zzajh2.zzcoj);
                } else if (!(com_google_android_gms_internal_ads_zzpb instanceof zzos) || this.zzvw.zzadi == null || this.zzvw.zzadi.get(((zzos) com_google_android_gms_internal_ads_zzpb).getCustomTemplateId()) == null) {
                    zzane.zzdk("No matching listener for retrieved native ad template.");
                    zzi(0);
                    return false;
                } else {
                    zzakk.zzcrm.post(new zzbj(this, ((zzos) com_google_android_gms_internal_ads_zzpb).getCustomTemplateId(), com_google_android_gms_internal_ads_zzajh2));
                }
            }
            return super.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    protected final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        return this.zzvv.zzdz();
    }

    public final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zznx com_google_android_gms_internal_ads_zznx) {
        try {
            zzdq();
            return super.zza(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zznx, this.zzaac);
        } catch (Throwable e) {
            String str = "Error initializing webview.";
            if (zzane.isLoggable(4)) {
                Log.i(AdRequest.LOGTAG, str, e);
            }
            return false;
        }
    }

    protected final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof zzoz) {
            ((zzoz) unwrap).zzkl();
        }
        super.zzb(this.zzvw.zzacw, false);
    }

    protected final void zzb(boolean z) {
        String str = null;
        super.zzb(z);
        if (this.zzwl) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcb)).booleanValue()) {
                zzdt();
            }
        }
        if (!zzcp()) {
            return;
        }
        if (this.zzaab != null || this.zzaaa != null) {
            zzaqw com_google_android_gms_internal_ads_zzaqw;
            if (this.zzaab != null) {
                com_google_android_gms_internal_ads_zzaqw = this.zzaab;
            } else if (this.zzaaa != null) {
                str = "javascript";
                com_google_android_gms_internal_ads_zzaqw = this.zzaaa;
            } else {
                com_google_android_gms_internal_ads_zzaqw = null;
            }
            if (com_google_android_gms_internal_ads_zzaqw.getWebView() != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
                int i = this.zzvw.zzacr.zzcve;
                this.zzwb = zzbv.zzfa().zza(i + "." + this.zzvw.zzacr.zzcvf, com_google_android_gms_internal_ads_zzaqw.getWebView(), "", "javascript", str);
                if (this.zzwb != null) {
                    zzbv.zzfa().zzm(this.zzwb);
                }
            }
        }
    }

    protected final void zzbq() {
        zzb(false);
    }

    protected final void zzc(int i, boolean z) {
        zzdx();
        super.zzc(i, z);
    }

    public final void zzcd() {
        zzajh com_google_android_gms_internal_ads_zzajh = this.zzvw.zzacw;
        if (com_google_android_gms_internal_ads_zzajh.zzbtx == null) {
            super.zzcd();
            return;
        }
        try {
            zzxq com_google_android_gms_internal_ads_zzxq = com_google_android_gms_internal_ads_zzajh.zzbtx;
            zzlo com_google_android_gms_internal_ads_zzlo = null;
            zzxz zzmo = com_google_android_gms_internal_ads_zzxq.zzmo();
            if (zzmo != null) {
                com_google_android_gms_internal_ads_zzlo = zzmo.getVideoController();
            } else {
                zzyc zzmp = com_google_android_gms_internal_ads_zzxq.zzmp();
                if (zzmp != null) {
                    com_google_android_gms_internal_ads_zzlo = zzmp.getVideoController();
                } else {
                    zzqs zzmt = com_google_android_gms_internal_ads_zzxq.zzmt();
                    if (zzmt != null) {
                        com_google_android_gms_internal_ads_zzlo = zzmt.getVideoController();
                    }
                }
            }
            if (com_google_android_gms_internal_ads_zzlo != null) {
                zzlr zzio = com_google_android_gms_internal_ads_zzlo.zzio();
                if (zzio != null) {
                    zzio.onVideoEnd();
                }
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zzce() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty)) {
            super.zzce();
        } else {
            zzbs();
        }
    }

    public final void zzcj() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty)) {
            super.zzcj();
        } else {
            zzbr();
        }
    }

    public final void zzcr() {
        if (zzcp() && this.zzwb != null) {
            zzaqw com_google_android_gms_internal_ads_zzaqw = null;
            if (this.zzaab != null) {
                com_google_android_gms_internal_ads_zzaqw = this.zzaab;
            } else if (this.zzaaa != null) {
                com_google_android_gms_internal_ads_zzaqw = this.zzaaa;
            }
            if (com_google_android_gms_internal_ads_zzaqw != null) {
                com_google_android_gms_internal_ads_zzaqw.zza("onSdkImpression", new HashMap());
            }
        }
    }

    public final void zzcs() {
        super.zzby();
        if (this.zzaab != null) {
            this.zzaab.destroy();
            this.zzaab = null;
        }
    }

    public final void zzct() {
        if (this.zzaaa != null) {
            this.zzaaa.destroy();
            this.zzaaa = null;
        }
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

    final void zzdq() throws zzarg {
        synchronized (this.mLock) {
            zzakb.m3428v("Initializing webview native ads utills");
            this.zzaad = new zzacq(this.zzvw.zzrt, this, this.zzaae, this.zzvw.zzacq, this.zzvw.zzacr);
        }
    }

    @Nullable
    public final zzacm zzdr() {
        zzacm com_google_android_gms_internal_ads_zzacm;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzacm = this.zzaad;
        }
        return com_google_android_gms_internal_ads_zzacm;
    }

    protected final Future<zzpb> zzds() {
        return this.zzzz;
    }

    public final void zzdt() {
        if (this.zzvw.zzacw == null || this.zzaaa == null) {
            this.zzwl = true;
            zzane.zzdk("Request to enable ActiveView before adState is available.");
            return;
        }
        zzbv.zzeo().zzqd().zza(this.zzvw.zzacv, this.zzvw.zzacw, this.zzaaa.getView(), this.zzaaa);
        this.zzwl = false;
    }

    public final void zzdu() {
        this.zzwl = false;
        if (this.zzvw.zzacw == null || this.zzaaa == null) {
            zzane.zzdk("Request to enable ActiveView before adState is available.");
        } else {
            zzbv.zzeo().zzqd().zzh(this.zzvw.zzacw);
        }
    }

    public final SimpleArrayMap<String, zzrf> zzdv() {
        Preconditions.checkMainThread("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzvw.zzadi;
    }

    public final void zzdw() {
        if (this.zzaaa != null && this.zzaaa.zztm() != null && this.zzvw.zzadj != null && this.zzvw.zzadj.zzbjr != null) {
            this.zzaaa.zztm().zzb(this.zzvw.zzadj.zzbjr);
        }
    }

    public final void zzf(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        this.zzaaa = com_google_android_gms_internal_ads_zzaqw;
    }

    public final void zzg(@Nullable zzaqw com_google_android_gms_internal_ads_zzaqw) {
        this.zzaab = com_google_android_gms_internal_ads_zzaqw;
    }

    protected final void zzi(int i) {
        zzc(i, false);
    }

    public final void zzi(View view) {
        if (this.zzwb != null) {
            zzbv.zzfa().zza(this.zzwb, view);
        }
    }

    public final void zzj(int i) {
        Preconditions.checkMainThread("setMaxNumberOfAds must be called on the main UI thread.");
        this.zzaac = i;
    }

    @Nullable
    public final zzrc zzr(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzvw.zzadh == null ? null : (zzrc) this.zzvw.zzadh.get(str);
    }
}

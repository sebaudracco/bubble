package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

@zzadh
public final class zzadk extends zzajx implements zzadx {
    private final Context mContext;
    @VisibleForTesting
    private zzwy zzbtj;
    @VisibleForTesting
    private zzaef zzbuc;
    @VisibleForTesting
    private zzaej zzbzf;
    private Runnable zzbzg;
    private final Object zzbzh = new Object();
    private final zzadj zzccf;
    private final zzaeg zzccg;
    private final zzhs zzcch;
    private final zzhx zzcci;
    @GuardedBy("mCancelLock")
    @VisibleForTesting
    zzalc zzccj;

    public zzadk(Context context, zzaeg com_google_android_gms_internal_ads_zzaeg, zzadj com_google_android_gms_internal_ads_zzadj, zzhx com_google_android_gms_internal_ads_zzhx) {
        this.zzccf = com_google_android_gms_internal_ads_zzadj;
        this.mContext = context;
        this.zzccg = com_google_android_gms_internal_ads_zzaeg;
        this.zzcci = com_google_android_gms_internal_ads_zzhx;
        this.zzcch = new zzhs(this.zzcci);
        this.zzcch.zza(new zzadl(this));
        zzit com_google_android_gms_internal_ads_zzit = new zzit();
        com_google_android_gms_internal_ads_zzit.zzaot = Integer.valueOf(this.zzccg.zzacr.zzcve);
        com_google_android_gms_internal_ads_zzit.zzaou = Integer.valueOf(this.zzccg.zzacr.zzcvf);
        com_google_android_gms_internal_ads_zzit.zzaov = Integer.valueOf(this.zzccg.zzacr.zzcvg ? 0 : 2);
        this.zzcch.zza(new zzadm(com_google_android_gms_internal_ads_zzit));
        if (this.zzccg.zzccw != null) {
            this.zzcch.zza(new zzadn(this));
        }
        zzjn com_google_android_gms_internal_ads_zzjn = this.zzccg.zzacv;
        if (com_google_android_gms_internal_ads_zzjn.zzarc && "interstitial_mb".equals(com_google_android_gms_internal_ads_zzjn.zzarb)) {
            this.zzcch.zza(zzado.zzccm);
        } else if (com_google_android_gms_internal_ads_zzjn.zzarc && "reward_mb".equals(com_google_android_gms_internal_ads_zzjn.zzarb)) {
            this.zzcch.zza(zzadp.zzccm);
        } else if (com_google_android_gms_internal_ads_zzjn.zzare || com_google_android_gms_internal_ads_zzjn.zzarc) {
            this.zzcch.zza(zzadr.zzccm);
        } else {
            this.zzcch.zza(zzadq.zzccm);
        }
        this.zzcch.zza(zzb.zzakj);
    }

    @VisibleForTesting
    private final zzjn zza(zzaef com_google_android_gms_internal_ads_zzaef) throws zzadu {
        int i = 1;
        if (this.zzbuc == null || this.zzbuc.zzadn == null || this.zzbuc.zzadn.size() <= 1) {
            i = 0;
        }
        if (i != 0 && this.zzbtj != null && !this.zzbtj.zzbte) {
            return null;
        }
        if (this.zzbzf.zzarf) {
            for (zzjn com_google_android_gms_internal_ads_zzjn : com_google_android_gms_internal_ads_zzaef.zzacv.zzard) {
                if (com_google_android_gms_internal_ads_zzjn.zzarf) {
                    return new zzjn(com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzaef.zzacv.zzard);
                }
            }
        }
        if (this.zzbzf.zzcet == null) {
            throw new zzadu("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzbzf.zzcet.split("x");
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.zzbzf.zzcet);
            throw new zzadu(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (zzjn com_google_android_gms_internal_ads_zzjn2 : com_google_android_gms_internal_ads_zzaef.zzacv.zzard) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                i = com_google_android_gms_internal_ads_zzjn2.width == -1 ? (int) (((float) com_google_android_gms_internal_ads_zzjn2.widthPixels) / f) : com_google_android_gms_internal_ads_zzjn2.width;
                int i2 = com_google_android_gms_internal_ads_zzjn2.height == -2 ? (int) (((float) com_google_android_gms_internal_ads_zzjn2.heightPixels) / f) : com_google_android_gms_internal_ads_zzjn2.height;
                if (parseInt == i && parseInt2 == i2 && !com_google_android_gms_internal_ads_zzjn2.zzarf) {
                    return new zzjn(com_google_android_gms_internal_ads_zzjn2, com_google_android_gms_internal_ads_zzaef.zzacv.zzard);
                }
            }
            str = "The ad size from the ad response was not one of the requested sizes: ";
            valueOf = String.valueOf(this.zzbzf.zzcet);
            throw new zzadu(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (NumberFormatException e) {
            str = "Invalid ad size number from the ad response: ";
            valueOf = String.valueOf(this.zzbzf.zzcet);
            throw new zzadu(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
    }

    private final void zzc(int i, String str) {
        if (i == 3 || i == -1) {
            zzane.zzdj(str);
        } else {
            zzane.zzdk(str);
        }
        if (this.zzbzf == null) {
            this.zzbzf = new zzaej(i);
        } else {
            this.zzbzf = new zzaej(i, this.zzbzf.zzbsu);
        }
        this.zzccf.zza(new zzaji(this.zzbuc != null ? this.zzbuc : new zzaef(this.zzccg, -1, null, null, null), this.zzbzf, this.zzbtj, null, i, -1, this.zzbzf.zzceu, null, this.zzcch, null));
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            if (this.zzccj != null) {
                this.zzccj.cancel();
            }
        }
    }

    @VisibleForTesting
    final zzalc zza(zzang com_google_android_gms_internal_ads_zzang, zzaol<zzaef> com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef) {
        Context context = this.mContext;
        if (new zzadw(context).zza(com_google_android_gms_internal_ads_zzang)) {
            zzane.zzck("Fetching ad response from local ad request service.");
            zzalc com_google_android_gms_internal_ads_zzaec = new zzaec(context, com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef, this);
            com_google_android_gms_internal_ads_zzaec.zznt();
            return com_google_android_gms_internal_ads_zzaec;
        }
        zzane.zzck("Fetching ad response from remote ad request service.");
        zzkb.zzif();
        if (zzamu.zzbe(context)) {
            return new zzaed(context, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef, this);
        }
        zzane.zzdk("Failed to connect to remote ad request service.");
        return null;
    }

    public final void zza(@NonNull zzaej com_google_android_gms_internal_ads_zzaej) {
        Boolean bool = null;
        zzane.zzck("Received ad response.");
        this.zzbzf = com_google_android_gms_internal_ads_zzaej;
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        synchronized (this.zzbzh) {
            this.zzccj = null;
        }
        zzbv.zzeo().zzqh().zzae(this.zzbzf.zzcdr);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
            if (this.zzbzf.zzced) {
                zzbv.zzeo().zzqh().zzcp(this.zzbuc.zzacp);
            } else {
                zzbv.zzeo().zzqh().zzcq(this.zzbuc.zzacp);
            }
        }
        try {
            if (this.zzbzf.errorCode == -2 || this.zzbzf.errorCode == -3) {
                JSONObject jSONObject;
                Boolean valueOf;
                zzjj com_google_android_gms_internal_ads_zzjj;
                Bundle bundle;
                Bundle bundle2;
                if (this.zzbzf.errorCode != -3) {
                    if (TextUtils.isEmpty(this.zzbzf.zzceo)) {
                        throw new zzadu("No fill from ad server.", 3);
                    }
                    zzbv.zzeo().zzqh().zzab(this.zzbzf.zzcdd);
                    if (this.zzbzf.zzceq) {
                        this.zzbtj = new zzwy(this.zzbzf.zzceo);
                        zzbv.zzeo().zzaa(this.zzbtj.zzbss);
                    } else {
                        zzbv.zzeo().zzaa(this.zzbzf.zzbss);
                    }
                    if (!TextUtils.isEmpty(this.zzbzf.zzcds)) {
                        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdj)).booleanValue()) {
                            zzane.zzck("Received cookie from server. Setting webview cookie in CookieManager.");
                            CookieManager zzax = zzbv.zzem().zzax(this.mContext);
                            if (zzax != null) {
                                zzax.setCookie("googleads.g.doubleclick.net", this.zzbzf.zzcds);
                            }
                        }
                    }
                }
                zzjn zza = this.zzbuc.zzacv.zzard != null ? zza(this.zzbuc) : null;
                zzbv.zzeo().zzqh().zzac(this.zzbzf.zzcfa);
                zzbv.zzeo().zzqh().zzad(this.zzbzf.zzcfm);
                if (!TextUtils.isEmpty(this.zzbzf.zzcey)) {
                    try {
                        jSONObject = new JSONObject(this.zzbzf.zzcey);
                    } catch (Throwable e) {
                        zzane.zzb("Error parsing the JSON for Active View.", e);
                    }
                    if (this.zzbzf.zzcfo == 2) {
                        valueOf = Boolean.valueOf(true);
                        com_google_android_gms_internal_ads_zzjj = this.zzbuc.zzccv;
                        bundle = com_google_android_gms_internal_ads_zzjj.zzaqg == null ? com_google_android_gms_internal_ads_zzjj.zzaqg : new Bundle();
                        if (bundle.getBundle(AdMobAdapter.class.getName()) == null) {
                            bundle = bundle.getBundle(AdMobAdapter.class.getName());
                        } else {
                            bundle2 = new Bundle();
                            bundle.putBundle(AdMobAdapter.class.getName(), bundle2);
                            bundle = bundle2;
                        }
                        bundle.putBoolean("render_test_label", true);
                        bool = valueOf;
                    }
                    if (this.zzbzf.zzcfo == 1) {
                        bool = Boolean.valueOf(false);
                    }
                    this.zzccf.zza(new zzaji(this.zzbuc, this.zzbzf, this.zzbtj, zza, -2, elapsedRealtime, this.zzbzf.zzceu, jSONObject, this.zzcch, this.zzbzf.zzcfo != 0 ? Boolean.valueOf(zzamm.zzo(this.zzbuc.zzccv)) : bool));
                    zzakk.zzcrm.removeCallbacks(this.zzbzg);
                    return;
                }
                jSONObject = null;
                if (this.zzbzf.zzcfo == 2) {
                    valueOf = Boolean.valueOf(true);
                    com_google_android_gms_internal_ads_zzjj = this.zzbuc.zzccv;
                    if (com_google_android_gms_internal_ads_zzjj.zzaqg == null) {
                    }
                    if (bundle.getBundle(AdMobAdapter.class.getName()) == null) {
                        bundle2 = new Bundle();
                        bundle.putBundle(AdMobAdapter.class.getName(), bundle2);
                        bundle = bundle2;
                    } else {
                        bundle = bundle.getBundle(AdMobAdapter.class.getName());
                    }
                    bundle.putBoolean("render_test_label", true);
                    bool = valueOf;
                }
                if (this.zzbzf.zzcfo == 1) {
                    bool = Boolean.valueOf(false);
                }
                if (this.zzbzf.zzcfo != 0) {
                }
                this.zzccf.zza(new zzaji(this.zzbuc, this.zzbzf, this.zzbtj, zza, -2, elapsedRealtime, this.zzbzf.zzceu, jSONObject, this.zzcch, this.zzbzf.zzcfo != 0 ? Boolean.valueOf(zzamm.zzo(this.zzbuc.zzccv)) : bool));
                zzakk.zzcrm.removeCallbacks(this.zzbzg);
                return;
            }
            throw new zzadu("There was a problem getting an ad response. ErrorCode: " + this.zzbzf.errorCode, this.zzbzf.errorCode);
        } catch (Throwable e2) {
            zzane.zzb("Could not parse mediation config.", e2);
            String str = "Could not parse mediation config: ";
            String valueOf2 = String.valueOf(this.zzbzf.zzceo);
            throw new zzadu(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str), 0);
        } catch (zzadu e3) {
            zzc(e3.getErrorCode(), e3.getMessage());
            zzakk.zzcrm.removeCallbacks(this.zzbzg);
        }
    }

    final /* synthetic */ void zzb(zzii com_google_android_gms_internal_ads_zzii) {
        com_google_android_gms_internal_ads_zzii.zzanm.zzamu = this.zzccg.zzccw.packageName;
    }

    final /* synthetic */ void zzc(zzii com_google_android_gms_internal_ads_zzii) {
        com_google_android_gms_internal_ads_zzii.zzanh = this.zzccg.zzcdi;
    }

    public final void zzdn() {
        zzane.zzck("AdLoaderBackgroundTask started.");
        this.zzbzg = new zzads(this);
        zzakk.zzcrm.postDelayed(this.zzbzg, ((Long) zzkb.zzik().zzd(zznk.zzban)).longValue());
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbak)).booleanValue() && this.zzccg.zzccv.extras != null) {
            String string = this.zzccg.zzccv.extras.getString("_ad");
            if (string != null) {
                this.zzbuc = new zzaef(this.zzccg, elapsedRealtime, null, null, null);
                zza(zzafs.zza(this.mContext, this.zzbuc, string));
                return;
            }
        }
        zzaol com_google_android_gms_internal_ads_zzaop = new zzaop();
        zzaki.zzb(new zzadt(this, com_google_android_gms_internal_ads_zzaop));
        String zzz = zzbv.zzfh().zzz(this.mContext);
        String zzaa = zzbv.zzfh().zzaa(this.mContext);
        String zzab = zzbv.zzfh().zzab(this.mContext);
        zzbv.zzfh().zzg(this.mContext, zzab);
        this.zzbuc = new zzaef(this.zzccg, elapsedRealtime, zzz, zzaa, zzab);
        com_google_android_gms_internal_ads_zzaop.zzk(this.zzbuc);
    }
}

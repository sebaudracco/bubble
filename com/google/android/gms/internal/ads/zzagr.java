package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONObject;

@zzadh
public final class zzagr extends zzd implements zzahu {
    private static zzagr zzcle;
    private boolean zzclf;
    private final zzago zzclg = new zzago(this.zzvw, this.zzwh, this, this, this);
    private boolean zzyu;
    @VisibleForTesting
    private final zzaix zzyv;

    public zzagr(Context context, zzw com_google_android_gms_ads_internal_zzw, zzjn com_google_android_gms_internal_ads_zzjn, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang) {
        super(context, com_google_android_gms_internal_ads_zzjn, null, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw);
        zzcle = this;
        this.zzyv = new zzaix(context, null);
    }

    private static zzaji zzc(zzaji com_google_android_gms_internal_ads_zzaji) {
        zzakb.m3428v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            JSONObject zzb = zzafs.zzb(com_google_android_gms_internal_ads_zzaji.zzcos);
            zzb.remove("impression_urls");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, com_google_android_gms_internal_ads_zzaji.zzcgs.zzacp);
            String jSONObject2 = jSONObject.toString();
            zzwx com_google_android_gms_internal_ads_zzwx = new zzwx(zzb.toString(), null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            return new zzaji(com_google_android_gms_internal_ads_zzaji.zzcgs, com_google_android_gms_internal_ads_zzaji.zzcos, new zzwy(Arrays.asList(new zzwx[]{com_google_android_gms_internal_ads_zzwx}), ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false), com_google_android_gms_internal_ads_zzaji.zzacv, com_google_android_gms_internal_ads_zzaji.errorCode, com_google_android_gms_internal_ads_zzaji.zzcoh, com_google_android_gms_internal_ads_zzaji.zzcoi, com_google_android_gms_internal_ads_zzaji.zzcob, com_google_android_gms_internal_ads_zzaji.zzcoq, null);
        } catch (Throwable e) {
            zzane.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return new zzaji(com_google_android_gms_internal_ads_zzaji.zzcgs, com_google_android_gms_internal_ads_zzaji.zzcos, null, com_google_android_gms_internal_ads_zzaji.zzacv, 0, com_google_android_gms_internal_ads_zzaji.zzcoh, com_google_android_gms_internal_ads_zzaji.zzcoi, com_google_android_gms_internal_ads_zzaji.zzcob, com_google_android_gms_internal_ads_zzaji.zzcoq, null);
        }
    }

    public static zzagr zzox() {
        return zzcle;
    }

    public final void destroy() {
        this.zzclg.destroy();
        super.destroy();
    }

    public final boolean isLoaded() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }

    public final void onContextChanged(Context context) {
        this.zzclg.onContextChanged(context);
    }

    public final void onRewardedVideoAdClosed() {
        if (zzbv.zzfh().zzw(this.zzvw.zzrt)) {
            this.zzyv.zzx(false);
        }
        zzbn();
    }

    public final void onRewardedVideoAdLeftApplication() {
        zzbo();
    }

    public final void onRewardedVideoAdOpened() {
        if (zzbv.zzfh().zzw(this.zzvw.zzrt)) {
            this.zzyv.zzx(true);
        }
        zza(this.zzvw.zzacw, false);
        zzbp();
    }

    public final void onRewardedVideoCompleted() {
        this.zzclg.zzow();
        zzbu();
    }

    public final void onRewardedVideoStarted() {
        this.zzclg.zzov();
        zzbt();
    }

    public final void pause() {
        this.zzclg.pause();
    }

    public final void resume() {
        this.zzclg.resume();
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void zza(zzahk com_google_android_gms_internal_ads_zzahk) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(com_google_android_gms_internal_ads_zzahk.zzacp)) {
            zzane.zzdk("Invalid ad unit id. Aborting.");
            zzakk.zzcrm.post(new zzags(this));
            return;
        }
        this.zzclf = false;
        this.zzvw.zzacp = com_google_android_gms_internal_ads_zzahk.zzacp;
        this.zzyv.setAdUnitId(com_google_android_gms_internal_ads_zzahk.zzacp);
        super.zzb(com_google_android_gms_internal_ads_zzahk.zzccv);
    }

    public final void zza(zzaji com_google_android_gms_internal_ads_zzaji, zznx com_google_android_gms_internal_ads_zznx) {
        if (com_google_android_gms_internal_ads_zzaji.errorCode != -2) {
            zzakk.zzcrm.post(new zzagt(this, com_google_android_gms_internal_ads_zzaji));
            return;
        }
        this.zzvw.zzacx = com_google_android_gms_internal_ads_zzaji;
        if (com_google_android_gms_internal_ads_zzaji.zzcod == null) {
            this.zzvw.zzacx = zzc(com_google_android_gms_internal_ads_zzaji);
        }
        this.zzclg.zzou();
    }

    public final boolean zza(zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        zzb(com_google_android_gms_internal_ads_zzajh2, false);
        return zzago.zza(com_google_android_gms_internal_ads_zzajh, com_google_android_gms_internal_ads_zzajh2);
    }

    protected final boolean zza(zzjj com_google_android_gms_internal_ads_zzjj, zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        return false;
    }

    protected final void zzbn() {
        this.zzvw.zzacw = null;
        super.zzbn();
    }

    public final void zzc(@Nullable zzaig com_google_android_gms_internal_ads_zzaig) {
        zzaig zzd = this.zzclg.zzd(com_google_android_gms_internal_ads_zzaig);
        if (zzbv.zzfh().zzw(this.zzvw.zzrt) && zzd != null) {
            zzbv.zzfh().zza(this.zzvw.zzrt, zzbv.zzfh().zzab(this.zzvw.zzrt), this.zzvw.zzacp, zzd.type, zzd.zzcmk);
        }
        zza(zzd);
    }

    @Nullable
    public final zzaib zzca(String str) {
        return this.zzclg.zzca(str);
    }

    public final void zzdm() {
        onAdClicked();
    }

    public final void zzoy() {
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (isLoaded()) {
            this.zzclg.zzw(this.zzyu);
        } else {
            zzane.zzdk("The reward video has not loaded.");
        }
    }
}

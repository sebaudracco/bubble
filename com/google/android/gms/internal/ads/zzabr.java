package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@zzadh
public final class zzabr extends zzabh {
    private final zzaqw zzbnd;
    private zzwy zzbtj;
    @VisibleForTesting
    private zzww zzbzq;
    protected zzxe zzbzr;
    private boolean zzbzs;
    private final zznx zzvr;
    private zzxn zzwh;

    zzabr(Context context, zzaji com_google_android_gms_internal_ads_zzaji, zzxn com_google_android_gms_internal_ads_zzxn, zzabm com_google_android_gms_internal_ads_zzabm, zznx com_google_android_gms_internal_ads_zznx, zzaqw com_google_android_gms_internal_ads_zzaqw) {
        super(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzabm);
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzbtj = com_google_android_gms_internal_ads_zzaji.zzcod;
        this.zzvr = com_google_android_gms_internal_ads_zznx;
        this.zzbnd = com_google_android_gms_internal_ads_zzaqw;
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            super.onStop();
            if (this.zzbzq != null) {
                this.zzbzq.cancel();
            }
        }
    }

    protected final zzajh zzaa(int i) {
        String str;
        zzaef com_google_android_gms_internal_ads_zzaef = this.zzbze.zzcgs;
        zzjj com_google_android_gms_internal_ads_zzjj = com_google_android_gms_internal_ads_zzaef.zzccv;
        zzaqw com_google_android_gms_internal_ads_zzaqw = this.zzbnd;
        List list = this.zzbzf.zzbsn;
        List list2 = this.zzbzf.zzbso;
        List list3 = this.zzbzf.zzces;
        int i2 = this.zzbzf.orientation;
        long j = this.zzbzf.zzbsu;
        String str2 = com_google_android_gms_internal_ads_zzaef.zzccy;
        boolean z = this.zzbzf.zzceq;
        zzwx com_google_android_gms_internal_ads_zzwx = this.zzbzr != null ? this.zzbzr.zzbtw : null;
        zzxq com_google_android_gms_internal_ads_zzxq = this.zzbzr != null ? this.zzbzr.zzbtx : null;
        String name = this.zzbzr != null ? this.zzbzr.zzbty : AdMobAdapter.class.getName();
        zzwy com_google_android_gms_internal_ads_zzwy = this.zzbtj;
        zzxa com_google_android_gms_internal_ads_zzxa = this.zzbzr != null ? this.zzbzr.zzbtz : null;
        long j2 = this.zzbzf.zzcer;
        zzjn com_google_android_gms_internal_ads_zzjn = this.zzbze.zzacv;
        long j3 = this.zzbzf.zzcep;
        long j4 = this.zzbze.zzcoh;
        long j5 = this.zzbzf.zzceu;
        String str3 = this.zzbzf.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig com_google_android_gms_internal_ads_zzaig = this.zzbzf.zzcfe;
        List list4 = this.zzbzf.zzcff;
        List list5 = this.zzbzf.zzcfg;
        boolean z2 = this.zzbtj != null ? this.zzbtj.zzbsz : false;
        zzael com_google_android_gms_internal_ads_zzael = this.zzbzf.zzcfi;
        if (this.zzbzq != null) {
            List<zzxe> zzme = this.zzbzq.zzme();
            String str4 = "";
            if (zzme == null) {
                str = str4.toString();
            } else {
                str = str4;
                for (zzxe com_google_android_gms_internal_ads_zzxe : zzme) {
                    if (!(com_google_android_gms_internal_ads_zzxe == null || com_google_android_gms_internal_ads_zzxe.zzbtw == null || TextUtils.isEmpty(com_google_android_gms_internal_ads_zzxe.zzbtw.zzbru))) {
                        int i3;
                        String valueOf = String.valueOf(str);
                        String str5 = com_google_android_gms_internal_ads_zzxe.zzbtw.zzbru;
                        switch (com_google_android_gms_internal_ads_zzxe.zzbtv) {
                            case -1:
                                i3 = 4;
                                break;
                            case 0:
                                i3 = 0;
                                break;
                            case 1:
                                i3 = 1;
                                break;
                            case 3:
                                i3 = 2;
                                break;
                            case 4:
                                i3 = 3;
                                break;
                            case 5:
                                i3 = 5;
                                break;
                            default:
                                i3 = 6;
                                break;
                        }
                        str4 = new StringBuilder(String.valueOf(str5).length() + 33).append(str5).append(".").append(i3).append(".").append(com_google_android_gms_internal_ads_zzxe.zzbub).toString();
                        str = new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str4).length()).append(valueOf).append(str4).append(BridgeUtil.UNDERLINE_STR).toString();
                    }
                }
                str = str.substring(0, Math.max(0, str.length() - 1));
            }
        } else {
            str = null;
        }
        return new zzajh(com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zzaqw, list, i, list2, list3, i2, j, str2, z, com_google_android_gms_internal_ads_zzwx, com_google_android_gms_internal_ads_zzxq, name, com_google_android_gms_internal_ads_zzwy, com_google_android_gms_internal_ads_zzxa, j2, com_google_android_gms_internal_ads_zzjn, j3, j4, j5, str3, jSONObject, null, com_google_android_gms_internal_ads_zzaig, list4, list5, z2, com_google_android_gms_internal_ads_zzael, str, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbzf.zzzl, this.zzbze.zzcor, this.zzbzf.zzcfp, this.zzbzf.zzbsp, this.zzbzf.zzzm, this.zzbzf.zzcfq);
    }

    protected final void zze(long j) throws zzabk {
        synchronized (this.zzbzh) {
            zzww com_google_android_gms_internal_ads_zzxh;
            if (this.zzbtj.zzbsx != -1) {
                com_google_android_gms_internal_ads_zzxh = new zzxh(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, j, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), 2, this.zzbze.zzcor);
            } else {
                com_google_android_gms_internal_ads_zzxh = new zzxk(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, j, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), this.zzvr, this.zzbze.zzcor);
            }
            this.zzbzq = com_google_android_gms_internal_ads_zzxh;
        }
        List arrayList = new ArrayList(this.zzbtj.zzbsm);
        boolean z = false;
        Bundle bundle = this.zzbze.zzcgs.zzccv.zzaqg;
        String str = "com.google.ads.mediation.admob.AdMobAdapter";
        if (bundle != null) {
            bundle = bundle.getBundle(str);
            if (bundle != null) {
                z = bundle.getBoolean("_skipMediation");
            }
        }
        if (z) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((zzwx) listIterator.next()).zzbrt.contains(str)) {
                    listIterator.remove();
                }
            }
        }
        this.zzbzr = this.zzbzq.zzh(arrayList);
        switch (this.zzbzr.zzbtv) {
            case 0:
                if (this.zzbzr.zzbtw != null && this.zzbzr.zzbtw.zzbsf != null) {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    zzakk.zzcrm.post(new zzabs(this, countDownLatch));
                    try {
                        countDownLatch.await(10, TimeUnit.SECONDS);
                        synchronized (this.zzbzh) {
                            if (!this.zzbzs) {
                                throw new zzabk("View could not be prepared", 0);
                            } else if (this.zzbnd.isDestroyed()) {
                                throw new zzabk("Assets not loaded, web view is destroyed", 0);
                            }
                        }
                        return;
                    } catch (InterruptedException e) {
                        String valueOf = String.valueOf(e);
                        throw new zzabk(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
                    }
                }
                return;
            case 1:
                throw new zzabk("No fill from any mediation ad networks.", 3);
            default:
                throw new zzabk("Unexpected mediation result: " + this.zzbzr.zzbtv, 0);
        }
    }
}

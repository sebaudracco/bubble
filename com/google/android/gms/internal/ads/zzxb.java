package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.appnext.core.Ad;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzxb implements zzxf {
    private final Context mContext;
    private final Object mLock = new Object();
    private zzjj zzaao;
    private final zzjn zzaau;
    private final boolean zzael;
    private final String zzbth;
    private final long zzbti;
    private final zzwy zzbtj;
    private final zzwx zzbtk;
    private final List<String> zzbtl;
    private final List<String> zzbtm;
    private final boolean zzbtn;
    private final boolean zzbto;
    @GuardedBy("mLock")
    private zzxq zzbtp;
    @GuardedBy("mLock")
    private int zzbtq = -2;
    private zzxw zzbtr;
    private final zzxn zzwh;
    private final zzpl zzyb;
    private final List<String> zzyc;
    private final zzang zzyf;

    public zzxb(Context context, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzwy com_google_android_gms_internal_ads_zzwy, zzwx com_google_android_gms_internal_ads_zzwx, zzjj com_google_android_gms_internal_ads_zzjj, zzjn com_google_android_gms_internal_ads_zzjn, zzang com_google_android_gms_internal_ads_zzang, boolean z, boolean z2, zzpl com_google_android_gms_internal_ads_zzpl, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        this.mContext = context;
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzbtk = com_google_android_gms_internal_ads_zzwx;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzbth = zzmh();
        } else {
            this.zzbth = str;
        }
        this.zzbtj = com_google_android_gms_internal_ads_zzwy;
        if (com_google_android_gms_internal_ads_zzwx.zzbsl != -1) {
            this.zzbti = com_google_android_gms_internal_ads_zzwx.zzbsl;
        } else if (com_google_android_gms_internal_ads_zzwy.zzbsl != -1) {
            this.zzbti = com_google_android_gms_internal_ads_zzwy.zzbsl;
        } else {
            this.zzbti = 10000;
        }
        this.zzaao = com_google_android_gms_internal_ads_zzjj;
        this.zzaau = com_google_android_gms_internal_ads_zzjn;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzyb = com_google_android_gms_internal_ads_zzpl;
        this.zzyc = list;
        this.zzbtl = list2;
        this.zzbtm = list3;
        this.zzbto = z3;
    }

    @VisibleForTesting
    private static zzxq zza(MediationAdapter mediationAdapter) {
        return new zzyk(mediationAdapter);
    }

    @GuardedBy("mLock")
    private final void zza(zzxa com_google_android_gms_internal_ads_zzxa) {
        String zzbk = zzbk(this.zzbtk.zzbsb);
        try {
            if (this.zzyf.zzcvf < GmsVersion.VERSION_HALLOUMI) {
                if (this.zzaau.zzarc) {
                    this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, com_google_android_gms_internal_ads_zzxa);
                } else {
                    this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaau, this.zzaao, zzbk, com_google_android_gms_internal_ads_zzxa);
                }
            } else if (this.zzael || this.zzbtk.zzmg()) {
                List arrayList = new ArrayList(this.zzyc);
                if (this.zzbtl != null) {
                    for (String str : this.zzbtl) {
                        String str2 = ":false";
                        if (this.zzbtm != null && this.zzbtm.contains(str)) {
                            str2 = ":true";
                        }
                        arrayList.add(new StringBuilder((String.valueOf(str).length() + 7) + String.valueOf(str2).length()).append("custom:").append(str).append(str2).toString());
                    }
                }
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, com_google_android_gms_internal_ads_zzxa, this.zzyb, arrayList);
            } else if (this.zzaau.zzarc) {
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, com_google_android_gms_internal_ads_zzxa);
            } else if (!this.zzbtn) {
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaau, this.zzaao, zzbk, this.zzbtk.zzbrr, com_google_android_gms_internal_ads_zzxa);
            } else if (this.zzbtk.zzbsf != null) {
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaao, zzbk, this.zzbtk.zzbrr, com_google_android_gms_internal_ads_zzxa, new zzpl(zzbl(this.zzbtk.zzbsj)), this.zzbtk.zzbsi);
            } else {
                this.zzbtp.zza(ObjectWrapper.wrap(this.mContext), this.zzaau, this.zzaao, zzbk, this.zzbtk.zzbrr, com_google_android_gms_internal_ads_zzxa);
            }
        } catch (Throwable e) {
            zzane.zzc("Could not request ad from mediation adapter.", e);
            zzx(5);
        }
    }

    @GuardedBy("mLock")
    private final String zzbk(String str) {
        if (!(str == null || !zzmk() || zzy(2))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                str = jSONObject.toString();
            } catch (JSONException e) {
                zzane.zzdk("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private static NativeAdOptions zzbl(String str) {
        int i = 0;
        Builder builder = new Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            String optString = jSONObject.optString("native_image_orientation", "any");
            if (Ad.ORIENTATION_LANDSCAPE.equals(optString)) {
                i = 2;
            } else if (Ad.ORIENTATION_PORTRAIT.equals(optString)) {
                i = 1;
            } else if (!"any".equals(optString)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (Throwable e) {
            zzane.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private final String zzmh() {
        try {
            if (!TextUtils.isEmpty(this.zzbtk.zzbrv)) {
                return this.zzwh.zzbn(this.zzbtk.zzbrv) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            zzane.zzdk("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    @GuardedBy("mLock")
    private final zzxw zzmi() {
        if (this.zzbtq != 0 || !zzmk()) {
            return null;
        }
        try {
            if (!(!zzy(4) || this.zzbtr == null || this.zzbtr.zzmm() == 0)) {
                return this.zzbtr;
            }
        } catch (RemoteException e) {
            zzane.zzdk("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzxd(zzml());
    }

    @GuardedBy("mLock")
    private final zzxq zzmj() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.zzbth);
        zzane.zzdj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!(this.zzael || this.zzbtk.zzmg())) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbai)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                return zza(new AdMobAdapter());
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbaj)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzbth)) {
                return zza(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzbth)) {
                return new zzyk(new zzzv());
            }
        }
        try {
            return this.zzwh.zzbm(this.zzbth);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Could not instantiate mediation adapter: ";
            valueOf = String.valueOf(this.zzbth);
            zzane.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    private final boolean zzmk() {
        return this.zzbtj.zzbsx != -1;
    }

    @GuardedBy("mLock")
    private final int zzml() {
        if (this.zzbtk.zzbsb == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzbtk.zzbsb);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzy(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException e) {
            zzane.zzdk("Could not convert to json. Returning 0");
            return 0;
        }
    }

    @GuardedBy("mLock")
    private final boolean zzy(int i) {
        try {
            Bundle zzmr = this.zzael ? this.zzbtp.zzmr() : this.zzaau.zzarc ? this.zzbtp.getInterstitialAdapterInfo() : this.zzbtp.zzmq();
            return zzmr != null && (zzmr.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException e) {
            zzane.zzdk("Could not get adapter info. Returning false");
            return false;
        }
    }

    public final void cancel() {
        synchronized (this.mLock) {
            try {
                if (this.zzbtp != null) {
                    this.zzbtp.destroy();
                }
            } catch (Throwable e) {
                zzane.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzbtq = -1;
            this.mLock.notify();
        }
    }

    public final zzxe zza(long j, long j2) {
        zzxe com_google_android_gms_internal_ads_zzxe;
        synchronized (this.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzxa com_google_android_gms_internal_ads_zzxa = new zzxa();
            zzakk.zzcrm.post(new zzxc(this, com_google_android_gms_internal_ads_zzxa));
            long j3 = this.zzbti;
            while (this.zzbtq == -2) {
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                long j4 = j3 - (elapsedRealtime2 - elapsedRealtime);
                elapsedRealtime2 = j2 - (elapsedRealtime2 - j);
                if (j4 <= 0 || elapsedRealtime2 <= 0) {
                    zzane.zzdj("Timed out waiting for adapter.");
                    this.zzbtq = 3;
                } else {
                    try {
                        this.mLock.wait(Math.min(j4, elapsedRealtime2));
                    } catch (InterruptedException e) {
                        this.zzbtq = 5;
                    }
                }
            }
            com_google_android_gms_internal_ads_zzxe = new zzxe(this.zzbtk, this.zzbtp, this.zzbth, com_google_android_gms_internal_ads_zzxa, this.zzbtq, zzmi(), zzbv.zzer().elapsedRealtime() - elapsedRealtime);
        }
        return com_google_android_gms_internal_ads_zzxe;
    }

    public final void zza(int i, zzxw com_google_android_gms_internal_ads_zzxw) {
        synchronized (this.mLock) {
            this.zzbtq = 0;
            this.zzbtr = com_google_android_gms_internal_ads_zzxw;
            this.mLock.notify();
        }
    }

    public final void zzx(int i) {
        synchronized (this.mLock) {
            this.zzbtq = i;
            this.mLock.notify();
        }
    }
}

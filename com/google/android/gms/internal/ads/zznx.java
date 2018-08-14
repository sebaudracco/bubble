package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznx {
    private final Object mLock = new Object();
    @VisibleForTesting
    private boolean zzbgn;
    private final List<zznv> zzbgo = new LinkedList();
    private final Map<String, String> zzbgp = new LinkedHashMap();
    private String zzbgq;
    @Nullable
    private zznx zzbgr;

    public zznx(boolean z, String str, String str2) {
        this.zzbgn = z;
        this.zzbgp.put(C1042c.jL, str);
        this.zzbgp.put("ad_format", str2);
    }

    public final boolean zza(zznv com_google_android_gms_internal_ads_zznv, long j, String... strArr) {
        synchronized (this.mLock) {
            for (String com_google_android_gms_internal_ads_zznv2 : strArr) {
                this.zzbgo.add(new zznv(j, com_google_android_gms_internal_ads_zznv2, com_google_android_gms_internal_ads_zznv));
            }
        }
        return true;
    }

    public final boolean zza(@Nullable zznv com_google_android_gms_internal_ads_zznv, String... strArr) {
        return (!this.zzbgn || com_google_android_gms_internal_ads_zznv == null) ? false : zza(com_google_android_gms_internal_ads_zznv, zzbv.zzer().elapsedRealtime(), strArr);
    }

    public final void zzan(String str) {
        if (this.zzbgn) {
            synchronized (this.mLock) {
                this.zzbgq = str;
            }
        }
    }

    public final void zzc(@Nullable zznx com_google_android_gms_internal_ads_zznx) {
        synchronized (this.mLock) {
            this.zzbgr = com_google_android_gms_internal_ads_zznx;
        }
    }

    @Nullable
    public final zznv zzd(long j) {
        return !this.zzbgn ? null : new zznv(j, null, null);
    }

    public final void zze(String str, String str2) {
        if (this.zzbgn && !TextUtils.isEmpty(str2)) {
            zznn zzpy = zzbv.zzeo().zzpy();
            if (zzpy != null) {
                synchronized (this.mLock) {
                    zznr zzal = zzpy.zzal(str);
                    Map map = this.zzbgp;
                    map.put(str, zzal.zzd((String) map.get(str), str2));
                }
            }
        }
    }

    public final zznv zzjj() {
        return zzd(zzbv.zzer().elapsedRealtime());
    }

    public final String zzjk() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.mLock) {
            for (zznv com_google_android_gms_internal_ads_zznv : this.zzbgo) {
                long time = com_google_android_gms_internal_ads_zznv.getTime();
                String zzjg = com_google_android_gms_internal_ads_zznv.zzjg();
                zznv com_google_android_gms_internal_ads_zznv2 = com_google_android_gms_internal_ads_zznv2.zzjh();
                if (com_google_android_gms_internal_ads_zznv2 != null && time > 0) {
                    stringBuilder2.append(zzjg).append('.').append(time - com_google_android_gms_internal_ads_zznv2.getTime()).append(',');
                }
            }
            this.zzbgo.clear();
            if (!TextUtils.isEmpty(this.zzbgq)) {
                stringBuilder2.append(this.zzbgq);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    @VisibleForTesting
    final Map<String, String> zzjl() {
        Map<String, String> map;
        synchronized (this.mLock) {
            zznn zzpy = zzbv.zzeo().zzpy();
            if (zzpy == null || this.zzbgr == null) {
                map = this.zzbgp;
            } else {
                map = zzpy.zza(this.zzbgp, this.zzbgr.zzjl());
            }
        }
        return map;
    }

    public final zznv zzjm() {
        synchronized (this.mLock) {
        }
        return null;
    }
}

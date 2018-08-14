package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzsm implements zzm {
    private final Context mContext;
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    private zzsf zzbnl;
    @GuardedBy("mLock")
    private boolean zzbnm;

    public zzsm(Context context) {
        this.mContext = context;
    }

    private final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbnl == null) {
                return;
            }
            this.zzbnl.disconnect();
            this.zzbnl = null;
            Binder.flushPendingCommands();
        }
    }

    private final Future<ParcelFileDescriptor> zzb(zzsg com_google_android_gms_internal_ads_zzsg) {
        Future com_google_android_gms_internal_ads_zzsn = new zzsn(this);
        BaseConnectionCallbacks com_google_android_gms_internal_ads_zzso = new zzso(this, com_google_android_gms_internal_ads_zzsn, com_google_android_gms_internal_ads_zzsg);
        BaseOnConnectionFailedListener com_google_android_gms_internal_ads_zzsr = new zzsr(this, com_google_android_gms_internal_ads_zzsn);
        synchronized (this.mLock) {
            this.zzbnl = new zzsf(this.mContext, zzbv.zzez().zzsa(), com_google_android_gms_internal_ads_zzso, com_google_android_gms_internal_ads_zzsr);
            this.zzbnl.checkAvailabilityAndConnect();
        }
        return com_google_android_gms_internal_ads_zzsn;
    }

    public final zzp zzc(zzr<?> com_google_android_gms_internal_ads_zzr_) throws zzae {
        zzsg zzh = zzsg.zzh(com_google_android_gms_internal_ads_zzr_);
        long intValue = (long) ((Integer) zzkb.zzik().zzd(zznk.zzbdx)).intValue();
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        try {
            zzsi com_google_android_gms_internal_ads_zzsi = (zzsi) new zzaev((ParcelFileDescriptor) zzb(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzsi.CREATOR);
            if (com_google_android_gms_internal_ads_zzsi.zzbnj) {
                throw new zzae(com_google_android_gms_internal_ads_zzsi.zzbnk);
            }
            zzp com_google_android_gms_internal_ads_zzp;
            if (com_google_android_gms_internal_ads_zzsi.zzbnh.length != com_google_android_gms_internal_ads_zzsi.zzbni.length) {
                com_google_android_gms_internal_ads_zzp = null;
            } else {
                Map hashMap = new HashMap();
                for (int i = 0; i < com_google_android_gms_internal_ads_zzsi.zzbnh.length; i++) {
                    hashMap.put(com_google_android_gms_internal_ads_zzsi.zzbnh[i], com_google_android_gms_internal_ads_zzsi.zzbni[i]);
                }
                com_google_android_gms_internal_ads_zzp = new zzp(com_google_android_gms_internal_ads_zzsi.statusCode, com_google_android_gms_internal_ads_zzsi.data, hashMap, com_google_android_gms_internal_ads_zzsi.zzac, com_google_android_gms_internal_ads_zzsi.zzad);
            }
            zzakb.m3428v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return com_google_android_gms_internal_ads_zzp;
        } catch (InterruptedException e) {
            zzakb.m3428v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return null;
        } catch (ExecutionException e2) {
            zzakb.m3428v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return null;
        } catch (TimeoutException e3) {
            zzakb.m3428v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return null;
        } catch (Throwable th) {
            zzakb.m3428v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
        }
    }
}

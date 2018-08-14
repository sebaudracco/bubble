package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzce;
import com.google.android.gms.internal.ads.zzch;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzadh
public final class zzag implements zzce, Runnable {
    private Context zzrt;
    private final List<Object[]> zzxo;
    private final AtomicReference<zzce> zzxp;
    private zzang zzxq;
    private CountDownLatch zzxr;

    private zzag(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzxo = new Vector();
        this.zzxp = new AtomicReference();
        this.zzxr = new CountDownLatch(1);
        this.zzrt = context;
        this.zzxq = com_google_android_gms_internal_ads_zzang;
        zzkb.zzif();
        if (zzamu.zzsh()) {
            zzaki.zzb(this);
        } else {
            run();
        }
    }

    public zzag(zzbw com_google_android_gms_ads_internal_zzbw) {
        this(com_google_android_gms_ads_internal_zzbw.zzrt, com_google_android_gms_ads_internal_zzbw.zzacr);
    }

    private static Context zzd(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    private final boolean zzdc() {
        try {
            this.zzxr.await();
            return true;
        } catch (Throwable e) {
            zzane.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private final void zzdd() {
        if (!this.zzxo.isEmpty()) {
            for (Object[] objArr : this.zzxo) {
                if (objArr.length == 1) {
                    ((zzce) this.zzxp.get()).zza((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((zzce) this.zzxp.get()).zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.zzxo.clear();
        }
    }

    public final void run() {
        try {
            boolean z = (((Boolean) zzkb.zzik().zzd(zznk.zzayl)).booleanValue() || (this.zzxq.zzcvg ? 1 : null) == null) ? false : true;
            this.zzxp.set(zzch.zza(this.zzxq.zzcw, zzd(this.zzrt), z));
        } finally {
            this.zzxr.countDown();
            this.zzrt = null;
            this.zzxq = null;
        }
    }

    public final String zza(Context context) {
        if (zzdc()) {
            zzce com_google_android_gms_internal_ads_zzce = (zzce) this.zzxp.get();
            if (com_google_android_gms_internal_ads_zzce != null) {
                zzdd();
                return com_google_android_gms_internal_ads_zzce.zza(zzd(context));
            }
        }
        return "";
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        if (zzdc()) {
            zzce com_google_android_gms_internal_ads_zzce = (zzce) this.zzxp.get();
            if (com_google_android_gms_internal_ads_zzce != null) {
                zzdd();
                return com_google_android_gms_internal_ads_zzce.zza(zzd(context), str, view, activity);
            }
        }
        return "";
    }

    public final void zza(int i, int i2, int i3) {
        zzce com_google_android_gms_internal_ads_zzce = (zzce) this.zzxp.get();
        if (com_google_android_gms_internal_ads_zzce != null) {
            zzdd();
            com_google_android_gms_internal_ads_zzce.zza(i, i2, i3);
            return;
        }
        this.zzxo.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void zza(MotionEvent motionEvent) {
        zzce com_google_android_gms_internal_ads_zzce = (zzce) this.zzxp.get();
        if (com_google_android_gms_internal_ads_zzce != null) {
            zzdd();
            com_google_android_gms_internal_ads_zzce.zza(motionEvent);
            return;
        }
        this.zzxo.add(new Object[]{motionEvent});
    }

    public final void zzb(View view) {
        zzce com_google_android_gms_internal_ads_zzce = (zzce) this.zzxp.get();
        if (com_google_android_gms_internal_ads_zzce != null) {
            com_google_android_gms_internal_ads_zzce.zzb(view);
        }
    }
}

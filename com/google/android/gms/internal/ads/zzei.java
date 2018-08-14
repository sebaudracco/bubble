package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzei implements Callable {
    private final String TAG = getClass().getSimpleName();
    private final String className;
    protected final zzcz zzps;
    protected final zzba zztq;
    private final String zztx;
    protected Method zztz;
    private final int zzud;
    private final int zzue;

    public zzei(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, zzba com_google_android_gms_internal_ads_zzba, int i, int i2) {
        this.zzps = com_google_android_gms_internal_ads_zzcz;
        this.className = str;
        this.zztx = str2;
        this.zztq = com_google_android_gms_internal_ads_zzba;
        this.zzud = i;
        this.zzue = i2;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzat();
    }

    protected abstract void zzar() throws IllegalAccessException, InvocationTargetException;

    public Void zzat() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            this.zztz = this.zzps.zza(this.className, this.zztx);
            if (this.zztz != null) {
                zzar();
                zzcc zzag = this.zzps.zzag();
                if (!(zzag == null || this.zzud == Integer.MIN_VALUE)) {
                    zzag.zza(this.zzue, this.zzud, (System.nanoTime() - nanoTime) / 1000);
                }
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return null;
    }
}

package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzeg {
    private static final String TAG = zzeg.class.getSimpleName();
    private final String className;
    private final zzcz zzps;
    private final String zztx;
    private final int zzty = 2;
    private volatile Method zztz = null;
    private final Class<?>[] zzua;
    private CountDownLatch zzub = new CountDownLatch(1);

    public zzeg(zzcz com_google_android_gms_internal_ads_zzcz, String str, String str2, Class<?>... clsArr) {
        this.zzps = com_google_android_gms_internal_ads_zzcz;
        this.className = str;
        this.zztx = str2;
        this.zzua = clsArr;
        this.zzps.zzab().submit(new zzeh(this));
    }

    private final void zzav() {
        try {
            Class loadClass = this.zzps.zzac().loadClass(zzb(this.zzps.zzae(), this.className));
            if (loadClass != null) {
                this.zztz = loadClass.getMethod(zzb(this.zzps.zzae(), this.zztx), this.zzua);
                if (this.zztz == null) {
                    this.zzub.countDown();
                } else {
                    this.zzub.countDown();
                }
            }
        } catch (zzcl e) {
        } catch (UnsupportedEncodingException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (NullPointerException e5) {
        } finally {
            this.zzub.countDown();
        }
    }

    private final String zzb(byte[] bArr, String str) throws zzcl, UnsupportedEncodingException {
        return new String(this.zzps.zzad().zza(bArr, str), "UTF-8");
    }

    public final Method zzaw() {
        if (this.zztz != null) {
            return this.zztz;
        }
        try {
            return this.zzub.await(2, TimeUnit.SECONDS) ? this.zztz : null;
        } catch (InterruptedException e) {
            return null;
        }
    }
}

package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

final class zzail implements Runnable {
    private final /* synthetic */ Bitmap val$bitmap;
    private final /* synthetic */ zzaii zzcna;

    zzail(zzaii com_google_android_gms_internal_ads_zzaii, Bitmap bitmap) {
        this.zzcna = com_google_android_gms_internal_ads_zzaii;
        this.val$bitmap = bitmap;
    }

    public final void run() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (zzaii.zza(this.zzcna)) {
            zzaii.zzb(this.zzcna).zzecm = new zzbft();
            zzaii.zzb(this.zzcna).zzecm.zzedl = byteArrayOutputStream.toByteArray();
            zzaii.zzb(this.zzcna).zzecm.mimeType = "image/png";
            zzaii.zzb(this.zzcna).zzecm.zzamf = Integer.valueOf(1);
        }
    }
}

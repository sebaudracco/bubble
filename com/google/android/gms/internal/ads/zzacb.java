package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.PlatformVersion;
import java.io.InputStream;

final class zzacb implements zzalz<zzon> {
    private final /* synthetic */ String zzbwo;
    private final /* synthetic */ zzabv zzcal;
    private final /* synthetic */ boolean zzcav;
    private final /* synthetic */ double zzcaw;
    private final /* synthetic */ boolean zzcax;

    zzacb(zzabv com_google_android_gms_internal_ads_zzabv, boolean z, double d, boolean z2, String str) {
        this.zzcal = com_google_android_gms_internal_ads_zzabv;
        this.zzcav = z;
        this.zzcaw = d;
        this.zzcax = z2;
        this.zzbwo = str;
    }

    @TargetApi(19)
    private final zzon zzd(InputStream inputStream) {
        Bitmap decodeStream;
        Options options = new Options();
        options.inDensity = (int) (160.0d * this.zzcaw);
        if (!this.zzcax) {
            options.inPreferredConfig = Config.RGB_565;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Throwable e) {
            zzakb.zzb("Error grabbing image.", e);
            decodeStream = null;
        }
        if (decodeStream == null) {
            this.zzcal.zzd(2, this.zzcav);
            return null;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (PlatformVersion.isAtLeastKitKat() && zzakb.zzqp()) {
            int width = decodeStream.getWidth();
            int height = decodeStream.getHeight();
            zzakb.v("Decoded image w: " + width + " h:" + height + " bytes: " + decodeStream.getAllocationByteCount() + " time: " + (uptimeMillis2 - uptimeMillis) + " on ui thread: " + (Looper.getMainLooper().getThread() == Thread.currentThread()));
        }
        return new zzon(new BitmapDrawable(Resources.getSystem(), decodeStream), Uri.parse(this.zzbwo), this.zzcaw);
    }

    @TargetApi(19)
    public final /* synthetic */ Object zze(InputStream inputStream) {
        return zzd(inputStream);
    }

    public final /* synthetic */ Object zzny() {
        this.zzcal.zzd(2, this.zzcav);
        return null;
    }
}

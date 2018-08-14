package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzapx {
    private final Context mContext;
    private final String zzchp;
    @Nullable
    private final zznx zzcxo;
    private boolean zzcxs;
    @Nullable
    private final zznv zzdad;
    private final zzalp zzdae = new zzals().zza("min_1", Double.MIN_VALUE, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE).zza("1_5", MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzrz();
    private final long[] zzdaf;
    private final String[] zzdag;
    private boolean zzdah = false;
    private boolean zzdai = false;
    private boolean zzdaj = false;
    private boolean zzdak = false;
    private zzapg zzdal;
    private boolean zzdam;
    private boolean zzdan;
    private long zzdao = -1;
    private final zzang zzzw;

    public zzapx(Context context, zzang com_google_android_gms_internal_ads_zzang, String str, @Nullable zznx com_google_android_gms_internal_ads_zznx, @Nullable zznv com_google_android_gms_internal_ads_zznv) {
        this.mContext = context;
        this.zzzw = com_google_android_gms_internal_ads_zzang;
        this.zzchp = str;
        this.zzcxo = com_google_android_gms_internal_ads_zznx;
        this.zzdad = com_google_android_gms_internal_ads_zznv;
        String str2 = (String) zzkb.zzik().zzd(zznk.zzave);
        if (str2 == null) {
            this.zzdag = new String[0];
            this.zzdaf = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzdag = new String[split.length];
        this.zzdaf = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzdaf[i] = Long.parseLong(split[i]);
            } catch (Throwable e) {
                zzane.zzc("Unable to parse frame hash target time number.", e);
                this.zzdaf[i] = -1;
            }
        }
    }

    public final void onStop() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzavd)).booleanValue() && !this.zzdam) {
            String valueOf;
            Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString("request", this.zzchp);
            bundle.putString("player", this.zzdal.zzsp());
            for (zzalr com_google_android_gms_internal_ads_zzalr : this.zzdae.zzry()) {
                String valueOf2 = String.valueOf("fps_c_");
                valueOf = String.valueOf(com_google_android_gms_internal_ads_zzalr.name);
                bundle.putString(valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2), Integer.toString(com_google_android_gms_internal_ads_zzalr.count));
                valueOf2 = String.valueOf("fps_p_");
                valueOf = String.valueOf(com_google_android_gms_internal_ads_zzalr.name);
                bundle.putString(valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2), Double.toString(com_google_android_gms_internal_ads_zzalr.zzctb));
            }
            for (int i = 0; i < this.zzdaf.length; i++) {
                valueOf = this.zzdag[i];
                if (valueOf != null) {
                    String valueOf3 = String.valueOf(Long.valueOf(this.zzdaf[i]));
                    bundle.putString(new StringBuilder(String.valueOf(valueOf3).length() + 3).append("fh_").append(valueOf3).toString(), valueOf);
                }
            }
            zzbv.zzek().zza(this.mContext, this.zzzw.zzcw, "gmob-apps", bundle, true);
            this.zzdam = true;
        }
    }

    public final void zzb(zzapg com_google_android_gms_internal_ads_zzapg) {
        zznq.zza(this.zzcxo, this.zzdad, "vpc2");
        this.zzdah = true;
        if (this.zzcxo != null) {
            this.zzcxo.zze("vpn", com_google_android_gms_internal_ads_zzapg.zzsp());
        }
        this.zzdal = com_google_android_gms_internal_ads_zzapg;
    }

    public final void zzc(zzapg com_google_android_gms_internal_ads_zzapg) {
        if (this.zzdaj && !this.zzdak) {
            if (zzakb.zzqp() && !this.zzdak) {
                zzakb.m3428v("VideoMetricsMixin first frame");
            }
            zznq.zza(this.zzcxo, this.zzdad, "vff2");
            this.zzdak = true;
        }
        long nanoTime = zzbv.zzer().nanoTime();
        if (this.zzcxs && this.zzdan && this.zzdao != -1) {
            this.zzdae.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzdao)));
        }
        this.zzdan = this.zzcxs;
        this.zzdao = nanoTime;
        long longValue = ((Long) zzkb.zzik().zzd(zznk.zzavf)).longValue();
        long currentPosition = (long) com_google_android_gms_internal_ads_zzapg.getCurrentPosition();
        int i = 0;
        while (i < this.zzdag.length) {
            if (this.zzdag[i] != null || longValue <= Math.abs(currentPosition - this.zzdaf[i])) {
                i++;
            } else {
                String[] strArr = this.zzdag;
                Bitmap bitmap = com_google_android_gms_internal_ads_zzapg.getBitmap(8, 8);
                currentPosition = 0;
                longValue = 63;
                int i2 = 0;
                while (i2 < 8) {
                    int i3 = 0;
                    long j = currentPosition;
                    currentPosition = longValue;
                    while (i3 < 8) {
                        int pixel = bitmap.getPixel(i3, i2);
                        j |= (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > 128 ? 1 : 0) << ((int) currentPosition);
                        i3++;
                        currentPosition--;
                    }
                    i2++;
                    longValue = currentPosition;
                    currentPosition = j;
                }
                strArr[i] = String.format("%016X", new Object[]{Long.valueOf(currentPosition)});
                return;
            }
        }
    }

    public final void zzsv() {
        if (this.zzdah && !this.zzdai) {
            zznq.zza(this.zzcxo, this.zzdad, "vfr2");
            this.zzdai = true;
        }
    }

    public final void zztt() {
        this.zzcxs = true;
        if (this.zzdai && !this.zzdaj) {
            zznq.zza(this.zzcxo, this.zzdad, "vfp2");
            this.zzdaj = true;
        }
    }

    public final void zztu() {
        this.zzcxs = false;
    }
}

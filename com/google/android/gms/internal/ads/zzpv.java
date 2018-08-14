package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzpv extends AdChoicesInfo {
    private final List<Image> zzbhf = new ArrayList();
    private final zzps zzbkk;
    private String zzbkl;

    public zzpv(zzps com_google_android_gms_internal_ads_zzps) {
        this.zzbkk = com_google_android_gms_internal_ads_zzps;
        try {
            this.zzbkl = this.zzbkk.getText();
        } catch (Throwable e) {
            zzane.zzb("", e);
            this.zzbkl = "";
        }
        try {
            for (Object next : com_google_android_gms_internal_ads_zzps.zzjr()) {
                zzpw com_google_android_gms_internal_ads_zzpw;
                Object next2;
                if (next2 instanceof IBinder) {
                    IBinder iBinder = (IBinder) next2;
                    if (iBinder != null) {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                        if (queryLocalInterface instanceof zzpw) {
                            com_google_android_gms_internal_ads_zzpw = (zzpw) queryLocalInterface;
                        } else {
                            next2 = new zzpy(iBinder);
                        }
                        if (com_google_android_gms_internal_ads_zzpw != null) {
                            this.zzbhf.add(new zzpz(com_google_android_gms_internal_ads_zzpw));
                        }
                    }
                }
                com_google_android_gms_internal_ads_zzpw = null;
                if (com_google_android_gms_internal_ads_zzpw != null) {
                    this.zzbhf.add(new zzpz(com_google_android_gms_internal_ads_zzpw));
                }
            }
        } catch (Throwable e2) {
            zzane.zzb("", e2);
        }
    }

    public final List<Image> getImages() {
        return this.zzbhf;
    }

    public final CharSequence getText() {
        return this.zzbkl;
    }
}

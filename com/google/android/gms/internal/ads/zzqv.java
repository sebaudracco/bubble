package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import java.util.WeakHashMap;

@zzadh
public final class zzqv implements NativeCustomTemplateAd {
    private static WeakHashMap<IBinder, zzqv> zzbkt = new WeakHashMap();
    private final VideoController zzasv = new VideoController();
    private final zzqs zzbku;
    private final MediaView zzbkv;

    @VisibleForTesting
    private zzqv(zzqs com_google_android_gms_internal_ads_zzqs) {
        Context context;
        Throwable e;
        MediaView mediaView;
        MediaView mediaView2 = null;
        this.zzbku = com_google_android_gms_internal_ads_zzqs;
        try {
            context = (Context) ObjectWrapper.unwrap(com_google_android_gms_internal_ads_zzqs.zzkh());
        } catch (NullPointerException e2) {
            e = e2;
            zzane.zzb("", e);
            context = null;
            if (context != null) {
                mediaView = new MediaView(context);
                try {
                    if (!this.zzbku.zzh(ObjectWrapper.wrap(mediaView))) {
                        mediaView = null;
                    }
                    mediaView2 = mediaView;
                } catch (Throwable e3) {
                    zzane.zzb("", e3);
                }
            }
            this.zzbkv = mediaView2;
        } catch (RemoteException e4) {
            e3 = e4;
            zzane.zzb("", e3);
            context = null;
            if (context != null) {
                mediaView = new MediaView(context);
                if (this.zzbku.zzh(ObjectWrapper.wrap(mediaView))) {
                    mediaView = null;
                }
                mediaView2 = mediaView;
            }
            this.zzbkv = mediaView2;
        }
        if (context != null) {
            mediaView = new MediaView(context);
            if (this.zzbku.zzh(ObjectWrapper.wrap(mediaView))) {
                mediaView = null;
            }
            mediaView2 = mediaView;
        }
        this.zzbkv = mediaView2;
    }

    public static zzqv zza(zzqs com_google_android_gms_internal_ads_zzqs) {
        zzqv com_google_android_gms_internal_ads_zzqv;
        synchronized (zzbkt) {
            com_google_android_gms_internal_ads_zzqv = (zzqv) zzbkt.get(com_google_android_gms_internal_ads_zzqs.asBinder());
            if (com_google_android_gms_internal_ads_zzqv != null) {
            } else {
                com_google_android_gms_internal_ads_zzqv = new zzqv(com_google_android_gms_internal_ads_zzqs);
                zzbkt.put(com_google_android_gms_internal_ads_zzqs.asBinder(), com_google_android_gms_internal_ads_zzqv);
            }
        }
        return com_google_android_gms_internal_ads_zzqv;
    }

    public final void destroy() {
        try {
            this.zzbku.destroy();
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzbku.getAvailableAssetNames();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getCustomTemplateId() {
        try {
            return this.zzbku.getCustomTemplateId();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Image getImage(String str) {
        try {
            zzpw zzap = this.zzbku.zzap(str);
            if (zzap != null) {
                return new zzpz(zzap);
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        return null;
    }

    public final CharSequence getText(String str) {
        try {
            return this.zzbku.zzao(str);
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            zzlo videoController = this.zzbku.getVideoController();
            if (videoController != null) {
                this.zzasv.zza(videoController);
            }
        } catch (Throwable e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzasv;
    }

    public final MediaView getVideoMediaView() {
        return this.zzbkv;
    }

    public final void performClick(String str) {
        try {
            this.zzbku.performClick(str);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzbku.recordImpression();
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final zzqs zzku() {
        return this.zzbku;
    }
}

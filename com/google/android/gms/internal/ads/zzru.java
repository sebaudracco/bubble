package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.UnconfirmedClickListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzru extends UnifiedNativeAd {
    private final zzrr zzbkw;
    private final List<Image> zzbkx = new ArrayList();
    private final zzpz zzbky;
    private final VideoController zzbkz = new VideoController();
    private final AdChoicesInfo zzbla;

    public zzru(zzrr com_google_android_gms_internal_ads_zzrr) {
        zzpz com_google_android_gms_internal_ads_zzpz;
        AdChoicesInfo adChoicesInfo = null;
        this.zzbkw = com_google_android_gms_internal_ads_zzrr;
        try {
            List images = this.zzbkw.getImages();
            if (images != null) {
                for (Object next : images) {
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
                                this.zzbkx.add(new zzpz(com_google_android_gms_internal_ads_zzpw));
                            }
                        }
                    }
                    com_google_android_gms_internal_ads_zzpw = null;
                    if (com_google_android_gms_internal_ads_zzpw != null) {
                        this.zzbkx.add(new zzpz(com_google_android_gms_internal_ads_zzpw));
                    }
                }
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        try {
            zzpw zzjz = this.zzbkw.zzjz();
            com_google_android_gms_internal_ads_zzpz = zzjz != null ? new zzpz(zzjz) : null;
        } catch (Throwable e2) {
            zzane.zzb("", e2);
            com_google_android_gms_internal_ads_zzpz = null;
        }
        this.zzbky = com_google_android_gms_internal_ads_zzpz;
        try {
            if (this.zzbkw.zzkf() != null) {
                adChoicesInfo = new zzpv(this.zzbkw.zzkf());
            }
        } catch (Throwable e22) {
            zzane.zzb("", e22);
        }
        this.zzbla = adChoicesInfo;
    }

    private final IObjectWrapper zzka() {
        try {
            return this.zzbkw.zzka();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final void cancelUnconfirmedClick() {
        try {
            this.zzbkw.cancelUnconfirmedClick();
        } catch (Throwable e) {
            zzane.zzb("Failed to cancelUnconfirmedClick", e);
        }
    }

    public final void destroy() {
        try {
            this.zzbkw.destroy();
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzbla;
    }

    public final String getAdvertiser() {
        try {
            return this.zzbkw.getAdvertiser();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getBody() {
        try {
            return this.zzbkw.getBody();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getCallToAction() {
        try {
            return this.zzbkw.getCallToAction();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            Bundle extras = this.zzbkw.getExtras();
            if (extras != null) {
                return extras;
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        return new Bundle();
    }

    public final String getHeadline() {
        try {
            return this.zzbkw.getHeadline();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Image getIcon() {
        return this.zzbky;
    }

    public final List<Image> getImages() {
        return this.zzbkx;
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzbkw.getMediationAdapterClassName();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getPrice() {
        try {
            return this.zzbkw.getPrice();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        Double d = null;
        try {
            double starRating = this.zzbkw.getStarRating();
            if (starRating != -1.0d) {
                d = Double.valueOf(starRating);
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        return d;
    }

    public final String getStore() {
        try {
            return this.zzbkw.getStore();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzbkw.getVideoController() != null) {
                this.zzbkz.zza(this.zzbkw.getVideoController());
            }
        } catch (Throwable e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzbkz;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzbkw.performClick(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzbkw.recordImpression(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzbkw.reportTouchEvent(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final void setUnconfirmedClickListener(UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzbkw.zza(new zzse(unconfirmedClickListener));
        } catch (Throwable e) {
            zzane.zzb("Failed to setUnconfirmedClickListener", e);
        }
    }

    protected final /* synthetic */ Object zzbe() {
        return zzka();
    }

    public final Object zzbh() {
        try {
            IObjectWrapper zzke = this.zzbkw.zzke();
            if (zzke != null) {
                return ObjectWrapper.unwrap(zzke);
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        return null;
    }
}

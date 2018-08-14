package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzqn extends NativeAppInstallAd {
    private final VideoController zzasv = new VideoController();
    private final zzqk zzbkn;
    private final List<Image> zzbko = new ArrayList();
    private final zzpz zzbkp;
    private final AdChoicesInfo zzbkq;

    public zzqn(zzqk com_google_android_gms_internal_ads_zzqk) {
        zzpz com_google_android_gms_internal_ads_zzpz;
        AdChoicesInfo adChoicesInfo = null;
        this.zzbkn = com_google_android_gms_internal_ads_zzqk;
        try {
            List images = this.zzbkn.getImages();
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
                                this.zzbko.add(new zzpz(com_google_android_gms_internal_ads_zzpw));
                            }
                        }
                    }
                    com_google_android_gms_internal_ads_zzpw = null;
                    if (com_google_android_gms_internal_ads_zzpw != null) {
                        this.zzbko.add(new zzpz(com_google_android_gms_internal_ads_zzpw));
                    }
                }
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        try {
            zzpw zzjz = this.zzbkn.zzjz();
            com_google_android_gms_internal_ads_zzpz = zzjz != null ? new zzpz(zzjz) : null;
        } catch (Throwable e2) {
            zzane.zzb("", e2);
            com_google_android_gms_internal_ads_zzpz = null;
        }
        this.zzbkp = com_google_android_gms_internal_ads_zzpz;
        try {
            if (this.zzbkn.zzkf() != null) {
                adChoicesInfo = new zzpv(this.zzbkn.zzkf());
            }
        } catch (Throwable e22) {
            zzane.zzb("", e22);
        }
        this.zzbkq = adChoicesInfo;
    }

    private final IObjectWrapper zzka() {
        try {
            return this.zzbkn.zzka();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzbkn.destroy();
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzbkq;
    }

    public final CharSequence getBody() {
        try {
            return this.zzbkn.getBody();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzbkn.getCallToAction();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzbkn.getExtras();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzbkn.getHeadline();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Image getIcon() {
        return this.zzbkp;
    }

    public final List<Image> getImages() {
        return this.zzbko;
    }

    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzbkn.getMediationAdapterClassName();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getPrice() {
        try {
            return this.zzbkn.getPrice();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        Double d = null;
        try {
            double starRating = this.zzbkn.getStarRating();
            if (starRating != -1.0d) {
                d = Double.valueOf(starRating);
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        return d;
    }

    public final CharSequence getStore() {
        try {
            return this.zzbkn.getStore();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzbkn.getVideoController() != null) {
                this.zzasv.zza(this.zzbkn.getVideoController());
            }
        } catch (Throwable e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzasv;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzbkn.performClick(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzbkn.recordImpression(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzbkn.reportTouchEvent(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    protected final /* synthetic */ Object zzbe() {
        return zzka();
    }
}

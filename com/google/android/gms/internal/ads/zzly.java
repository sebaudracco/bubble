package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

@zzadh
public final class zzly {
    private zzjd zzapt;
    private AdListener zzapu;
    private AdSize[] zzarh;
    private final zzxm zzast;
    private final AtomicBoolean zzasu;
    private final VideoController zzasv;
    @VisibleForTesting
    private final zzkd zzasw;
    private Correlator zzasx;
    private zzks zzasy;
    private OnCustomRenderedAdLoadedListener zzasz;
    private ViewGroup zzata;
    private int zzatb;
    private final zzjm zzuk;
    private VideoOptions zzvg;
    private boolean zzvm;
    private AppEventListener zzvo;
    private String zzye;

    public zzly(ViewGroup viewGroup) {
        this(viewGroup, null, false, zzjm.zzara, 0);
    }

    public zzly(ViewGroup viewGroup, int i) {
        this(viewGroup, null, false, zzjm.zzara, i);
    }

    public zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzjm.zzara, 0);
    }

    public zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zzjm.zzara, i);
    }

    @VisibleForTesting
    private zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzjm com_google_android_gms_internal_ads_zzjm, int i) {
        this(viewGroup, attributeSet, z, com_google_android_gms_internal_ads_zzjm, null, i);
    }

    @VisibleForTesting
    private zzly(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzjm com_google_android_gms_internal_ads_zzjm, zzks com_google_android_gms_internal_ads_zzks, int i) {
        this.zzast = new zzxm();
        this.zzasv = new VideoController();
        this.zzasw = new zzlz(this);
        this.zzata = viewGroup;
        this.zzuk = com_google_android_gms_internal_ads_zzjm;
        this.zzasy = null;
        this.zzasu = new AtomicBoolean(false);
        this.zzatb = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzjq com_google_android_gms_internal_ads_zzjq = new zzjq(context, attributeSet);
                this.zzarh = com_google_android_gms_internal_ads_zzjq.zzi(z);
                this.zzye = com_google_android_gms_internal_ads_zzjq.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzamu zzif = zzkb.zzif();
                    AdSize adSize = this.zzarh[0];
                    int i2 = this.zzatb;
                    zzjn com_google_android_gms_internal_ads_zzjn = new zzjn(context, adSize);
                    com_google_android_gms_internal_ads_zzjn.zzarg = zzu(i2);
                    zzif.zza(viewGroup, com_google_android_gms_internal_ads_zzjn, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzkb.zzif().zza(viewGroup, new zzjn(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    private static zzjn zza(Context context, AdSize[] adSizeArr, int i) {
        zzjn com_google_android_gms_internal_ads_zzjn = new zzjn(context, adSizeArr);
        com_google_android_gms_internal_ads_zzjn.zzarg = zzu(i);
        return com_google_android_gms_internal_ads_zzjn;
    }

    private static boolean zzu(int i) {
        return i == 1;
    }

    public final void destroy() {
        try {
            if (this.zzasy != null) {
                this.zzasy.destroy();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final AdListener getAdListener() {
        return this.zzapu;
    }

    public final AdSize getAdSize() {
        try {
            if (this.zzasy != null) {
                zzjn zzbk = this.zzasy.zzbk();
                if (zzbk != null) {
                    return zzbk.zzhy();
                }
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        return this.zzarh != null ? this.zzarh[0] : null;
    }

    public final AdSize[] getAdSizes() {
        return this.zzarh;
    }

    public final String getAdUnitId() {
        if (this.zzye == null && this.zzasy != null) {
            try {
                this.zzye = this.zzasy.getAdUnitId();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        return this.zzye;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzck();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        return null;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzasz;
    }

    public final VideoController getVideoController() {
        return this.zzasv;
    }

    public final VideoOptions getVideoOptions() {
        return this.zzvg;
    }

    public final boolean isLoading() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.isLoading();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        return false;
    }

    public final void pause() {
        try {
            if (this.zzasy != null) {
                this.zzasy.pause();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void recordManualImpression() {
        if (!this.zzasu.getAndSet(true)) {
            try {
                if (this.zzasy != null) {
                    this.zzasy.zzbm();
                }
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        try {
            if (this.zzasy != null) {
                this.zzasy.resume();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        this.zzapu = adListener;
        this.zzasw.zza(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (this.zzarh != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        zza(adSizeArr);
    }

    public final void setAdUnitId(String str) {
        if (this.zzye != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzye = str;
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzvo = appEventListener;
            if (this.zzasy != null) {
                this.zzasy.zza(appEventListener != null ? new zzjp(appEventListener) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzasx = correlator;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(this.zzasx == null ? null : this.zzasx.zzaz());
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzvm = z;
        try {
            if (this.zzasy != null) {
                this.zzasy.setManualImpressionsEnabled(this.zzvm);
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzasz = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(onCustomRenderedAdLoadedListener != null ? new zzog(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzvg = videoOptions;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(videoOptions == null ? null : new zzmu(videoOptions));
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzjd com_google_android_gms_internal_ads_zzjd) {
        try {
            this.zzapt = com_google_android_gms_internal_ads_zzjd;
            if (this.zzasy != null) {
                this.zzasy.zza(com_google_android_gms_internal_ads_zzjd != null ? new zzje(com_google_android_gms_internal_ads_zzjd) : null);
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzlw com_google_android_gms_internal_ads_zzlw) {
        try {
            if (this.zzasy == null) {
                if ((this.zzarh == null || this.zzye == null) && this.zzasy == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzata.getContext();
                zzjn zza = zza(context, this.zzarh, this.zzatb);
                this.zzasy = "search_v2".equals(zza.zzarb) ? (zzks) zzjr.zza(context, false, new zzjt(zzkb.zzig(), context, zza, this.zzye)) : (zzks) zzjr.zza(context, false, new zzjs(zzkb.zzig(), context, zza, this.zzye, this.zzast));
                this.zzasy.zza(new zzjf(this.zzasw));
                if (this.zzapt != null) {
                    this.zzasy.zza(new zzje(this.zzapt));
                }
                if (this.zzvo != null) {
                    this.zzasy.zza(new zzjp(this.zzvo));
                }
                if (this.zzasz != null) {
                    this.zzasy.zza(new zzog(this.zzasz));
                }
                if (this.zzasx != null) {
                    this.zzasy.zza(this.zzasx.zzaz());
                }
                if (this.zzvg != null) {
                    this.zzasy.zza(new zzmu(this.zzvg));
                }
                this.zzasy.setManualImpressionsEnabled(this.zzvm);
                try {
                    IObjectWrapper zzbj = this.zzasy.zzbj();
                    if (zzbj != null) {
                        this.zzata.addView((View) ObjectWrapper.unwrap(zzbj));
                    }
                } catch (Throwable e) {
                    zzane.zzd("#007 Could not call remote method.", e);
                }
            }
            if (this.zzasy.zzb(zzjm.zza(this.zzata.getContext(), com_google_android_gms_internal_ads_zzlw))) {
                this.zzast.zzj(com_google_android_gms_internal_ads_zzlw.zzir());
            }
        } catch (Throwable e2) {
            zzane.zzd("#007 Could not call remote method.", e2);
        }
    }

    public final void zza(AdSize... adSizeArr) {
        this.zzarh = adSizeArr;
        try {
            if (this.zzasy != null) {
                this.zzasy.zza(zza(this.zzata.getContext(), this.zzarh, this.zzatb));
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        this.zzata.requestLayout();
    }

    public final boolean zza(zzks com_google_android_gms_internal_ads_zzks) {
        if (com_google_android_gms_internal_ads_zzks == null) {
            return false;
        }
        try {
            IObjectWrapper zzbj = com_google_android_gms_internal_ads_zzks.zzbj();
            if (zzbj == null) {
                return false;
            }
            if (((View) ObjectWrapper.unwrap(zzbj)).getParent() != null) {
                return false;
            }
            this.zzata.addView((View) ObjectWrapper.unwrap(zzbj));
            this.zzasy = com_google_android_gms_internal_ads_zzks;
            return true;
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final zzlo zzbc() {
        zzlo com_google_android_gms_internal_ads_zzlo = null;
        if (this.zzasy != null) {
            try {
                com_google_android_gms_internal_ads_zzlo = this.zzasy.getVideoController();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
        return com_google_android_gms_internal_ads_zzlo;
    }
}

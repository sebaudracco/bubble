package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoy extends zzpd {
    private Object mLock;
    @Nullable
    private zzxz zzbit;
    @Nullable
    private zzyc zzbiu;
    @Nullable
    private zzyf zzbiv;
    private final zzpa zzbiw;
    @Nullable
    private zzoz zzbix;
    private boolean zzbiy;

    private zzoy(Context context, zzpa com_google_android_gms_internal_ads_zzpa, zzci com_google_android_gms_internal_ads_zzci, zzpb com_google_android_gms_internal_ads_zzpb) {
        super(context, com_google_android_gms_internal_ads_zzpa, null, com_google_android_gms_internal_ads_zzci, null, com_google_android_gms_internal_ads_zzpb, null, null);
        this.zzbiy = false;
        this.mLock = new Object();
        this.zzbiw = com_google_android_gms_internal_ads_zzpa;
    }

    public zzoy(Context context, zzpa com_google_android_gms_internal_ads_zzpa, zzci com_google_android_gms_internal_ads_zzci, zzxz com_google_android_gms_internal_ads_zzxz, zzpb com_google_android_gms_internal_ads_zzpb) {
        this(context, com_google_android_gms_internal_ads_zzpa, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzpb);
        this.zzbit = com_google_android_gms_internal_ads_zzxz;
    }

    public zzoy(Context context, zzpa com_google_android_gms_internal_ads_zzpa, zzci com_google_android_gms_internal_ads_zzci, zzyc com_google_android_gms_internal_ads_zzyc, zzpb com_google_android_gms_internal_ads_zzpb) {
        this(context, com_google_android_gms_internal_ads_zzpa, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzpb);
        this.zzbiu = com_google_android_gms_internal_ads_zzyc;
    }

    public zzoy(Context context, zzpa com_google_android_gms_internal_ads_zzpa, zzci com_google_android_gms_internal_ads_zzci, zzyf com_google_android_gms_internal_ads_zzyf, zzpb com_google_android_gms_internal_ads_zzpb) {
        this(context, com_google_android_gms_internal_ads_zzpa, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzpb);
        this.zzbiv = com_google_android_gms_internal_ads_zzyf;
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view = (View) ((WeakReference) entry.getValue()).get();
                if (view != null) {
                    hashMap.put((String) entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.cancelUnconfirmedClick();
            }
        }
    }

    public final void setClickConfirmingView(View view) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.setClickConfirmingView(view);
            }
        }
    }

    @Nullable
    public final View zza(OnClickListener onClickListener, boolean z) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                View zza = this.zzbix.zza(onClickListener, z);
                return zza;
            }
            IObjectWrapper zzmv;
            try {
                if (this.zzbiv != null) {
                    zzmv = this.zzbiv.zzmv();
                } else if (this.zzbit != null) {
                    zzmv = this.zzbit.zzmv();
                } else {
                    if (this.zzbiu != null) {
                        zzmv = this.zzbiu.zzmv();
                    }
                    zzmv = null;
                }
            } catch (Throwable e) {
                zzane.zzc("Failed to call getAdChoicesContent", e);
            }
            if (zzmv != null) {
                zza = (View) ObjectWrapper.unwrap(zzmv);
                return zza;
            }
            return null;
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzbjd = true;
            if (this.zzbix != null) {
                this.zzbix.zza(view, map);
                this.zzbiw.recordImpression();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideImpressionRecording()) {
                        this.zzbiv.recordImpression();
                        this.zzbiw.recordImpression();
                    } else if (this.zzbit != null && !this.zzbit.getOverrideImpressionRecording()) {
                        this.zzbit.recordImpression();
                        this.zzbiw.recordImpression();
                    } else if (!(this.zzbiu == null || this.zzbiu.getOverrideImpressionRecording())) {
                        this.zzbiu.recordImpression();
                        this.zzbiw.recordImpression();
                    }
                } catch (Throwable e) {
                    zzane.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(view, map, bundle, view2);
                this.zzbiw.onAdClicked();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideClickHandling()) {
                        this.zzbiv.zzj(ObjectWrapper.wrap(view));
                        this.zzbiw.onAdClicked();
                    } else if (this.zzbit != null && !this.zzbit.getOverrideClickHandling()) {
                        this.zzbit.zzj(ObjectWrapper.wrap(view));
                        this.zzbiw.onAdClicked();
                    } else if (!(this.zzbiu == null || this.zzbiu.getOverrideClickHandling())) {
                        this.zzbiu.zzj(ObjectWrapper.wrap(view));
                        this.zzbiw.onAdClicked();
                    }
                } catch (Throwable e) {
                    zzane.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzbiy = true;
            HashMap zzb = zzb(map);
            HashMap zzb2 = zzb(map2);
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                } else if (this.zzbit != null) {
                    this.zzbit.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbit.zzk(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbiu.zzk(ObjectWrapper.wrap(view));
                }
            } catch (Throwable e) {
                zzane.zzc("Failed to call prepareAd", e);
            }
            this.zzbiy = false;
        }
    }

    public final void zza(zzro com_google_android_gms_internal_ads_zzro) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(com_google_android_gms_internal_ads_zzro);
            }
        }
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbit != null) {
                    this.zzbit.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzl(ObjectWrapper.wrap(view));
                }
            } catch (Throwable e) {
                zzane.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final void zzc(@Nullable zzoz com_google_android_gms_internal_ads_zzoz) {
        synchronized (this.mLock) {
            this.zzbix = com_google_android_gms_internal_ads_zzoz;
        }
    }

    public final void zzcr() {
        if (this.zzbix != null) {
            this.zzbix.zzcr();
        }
    }

    public final void zzcs() {
        if (this.zzbix != null) {
            this.zzbix.zzcs();
        }
    }

    public final boolean zzkj() {
        boolean zzkj;
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                zzkj = this.zzbix.zzkj();
            } else {
                zzkj = this.zzbiw.zzcu();
            }
        }
        return zzkj;
    }

    public final boolean zzkk() {
        boolean zzkk;
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                zzkk = this.zzbix.zzkk();
            } else {
                zzkk = this.zzbiw.zzcv();
            }
        }
        return zzkk;
    }

    public final void zzkl() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzbje = true;
            if (this.zzbix != null) {
                this.zzbix.zzkl();
            }
        }
    }

    public final boolean zzkm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbiy;
        }
        return z;
    }

    public final zzoz zzkn() {
        zzoz com_google_android_gms_internal_ads_zzoz;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzoz = this.zzbix;
        }
        return com_google_android_gms_internal_ads_zzoz;
    }

    @Nullable
    public final zzaqw zzko() {
        return null;
    }

    public final void zzkp() {
    }

    public final void zzkq() {
    }
}

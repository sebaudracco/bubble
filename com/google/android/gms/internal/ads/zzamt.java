package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzamt {
    private final View mView;
    private Activity zzcuj;
    private boolean zzcuk;
    private boolean zzcul;
    private boolean zzcum;
    private OnGlobalLayoutListener zzcun;
    private OnScrollChangedListener zzcuo;

    public zzamt(Activity activity, View view, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        this.zzcuj = activity;
        this.mView = view;
        this.zzcun = onGlobalLayoutListener;
        this.zzcuo = onScrollChangedListener;
    }

    private static ViewTreeObserver zzj(Activity activity) {
        if (activity == null) {
            return null;
        }
        Window window = activity.getWindow();
        if (window == null) {
            return null;
        }
        View decorView = window.getDecorView();
        return decorView != null ? decorView.getViewTreeObserver() : null;
    }

    private final void zzse() {
        if (!this.zzcuk) {
            Activity activity;
            ViewTreeObserver zzj;
            if (this.zzcun != null) {
                if (this.zzcuj != null) {
                    activity = this.zzcuj;
                    OnGlobalLayoutListener onGlobalLayoutListener = this.zzcun;
                    zzj = zzj(activity);
                    if (zzj != null) {
                        zzj.addOnGlobalLayoutListener(onGlobalLayoutListener);
                    }
                }
                zzbv.zzfg();
                zzaor.zza(this.mView, this.zzcun);
            }
            if (this.zzcuo != null) {
                if (this.zzcuj != null) {
                    activity = this.zzcuj;
                    OnScrollChangedListener onScrollChangedListener = this.zzcuo;
                    zzj = zzj(activity);
                    if (zzj != null) {
                        zzj.addOnScrollChangedListener(onScrollChangedListener);
                    }
                }
                zzbv.zzfg();
                zzaor.zza(this.mView, this.zzcuo);
            }
            this.zzcuk = true;
        }
    }

    private final void zzsf() {
        if (this.zzcuj != null && this.zzcuk) {
            Activity activity;
            ViewTreeObserver zzj;
            if (this.zzcun != null) {
                activity = this.zzcuj;
                OnGlobalLayoutListener onGlobalLayoutListener = this.zzcun;
                zzj = zzj(activity);
                if (zzj != null) {
                    zzbv.zzem().zza(zzj, onGlobalLayoutListener);
                }
            }
            if (this.zzcuo != null) {
                activity = this.zzcuj;
                OnScrollChangedListener onScrollChangedListener = this.zzcuo;
                zzj = zzj(activity);
                if (zzj != null) {
                    zzj.removeOnScrollChangedListener(onScrollChangedListener);
                }
            }
            this.zzcuk = false;
        }
    }

    public final void onAttachedToWindow() {
        this.zzcul = true;
        if (this.zzcum) {
            zzse();
        }
    }

    public final void onDetachedFromWindow() {
        this.zzcul = false;
        zzsf();
    }

    public final void zzi(Activity activity) {
        this.zzcuj = activity;
    }

    public final void zzsc() {
        this.zzcum = true;
        if (this.zzcul) {
            zzse();
        }
    }

    public final void zzsd() {
        this.zzcum = false;
        zzsf();
    }
}

package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzbv;
import java.lang.ref.WeakReference;

@zzadh
final class zzaos extends zzaou implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> zzcwm;

    public zzaos(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.zzcwm = new WeakReference(onGlobalLayoutListener);
    }

    public final void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.zzcwm.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            detach();
        }
    }

    protected final void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    protected final void zzb(ViewTreeObserver viewTreeObserver) {
        zzbv.zzem().zza(viewTreeObserver, (OnGlobalLayoutListener) this);
    }
}

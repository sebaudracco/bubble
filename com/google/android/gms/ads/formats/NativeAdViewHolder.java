package com.google.android.gms.ads.formats;

import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzqf;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class NativeAdViewHolder {
    public static WeakHashMap<View, NativeAdViewHolder> zzvk = new WeakHashMap();
    private zzqf zzvj;
    private WeakReference<View> zzvl;

    public NativeAdViewHolder(View view, Map<String, View> map, Map<String, View> map2) {
        Preconditions.checkNotNull(view, "ContainerView must not be null");
        if ((view instanceof NativeAdView) || (view instanceof UnifiedNativeAdView)) {
            zzane.e("The provided containerView is of type of NativeAdView, which cannot be usedwith NativeAdViewHolder.");
        } else if (zzvk.get(view) != null) {
            zzane.e("The provided containerView is already in use with another NativeAdViewHolder.");
        } else {
            zzvk.put(view, this);
            this.zzvl = new WeakReference(view);
            this.zzvj = zzkb.zzig().zza(view, zzb(map), zzb(map2));
        }
    }

    private final void zza(IObjectWrapper iObjectWrapper) {
        Object obj = this.zzvl != null ? (View) this.zzvl.get() : null;
        if (obj == null) {
            zzane.zzdk("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        if (!zzvk.containsKey(obj)) {
            zzvk.put(obj, this);
        }
        if (this.zzvj != null) {
            try {
                this.zzvj.zza(iObjectWrapper);
            } catch (Throwable e) {
                zzane.zzb("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    private static HashMap<String, View> zzb(Map<String, View> map) {
        return map == null ? new HashMap() : new HashMap(map);
    }

    public final void setClickConfirmingView(View view) {
        try {
            this.zzvj.zzc(ObjectWrapper.wrap(view));
        } catch (Throwable e) {
            zzane.zzb("Unable to call setClickConfirmingView on delegate", e);
        }
    }

    public final void setNativeAd(NativeAd nativeAd) {
        zza((IObjectWrapper) nativeAd.zzbe());
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd) {
        zza((IObjectWrapper) unifiedNativeAd.zzbe());
    }

    public final void unregisterNativeAd() {
        if (this.zzvj != null) {
            try {
                this.zzvj.unregisterNativeAd();
            } catch (Throwable e) {
                zzane.zzb("Unable to call unregisterNativeAd on delegate", e);
            }
        }
        Object obj = this.zzvl != null ? (View) this.zzvl.get() : null;
        if (obj != null) {
            zzvk.remove(obj);
        }
    }
}

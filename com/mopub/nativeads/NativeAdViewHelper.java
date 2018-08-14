package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.logging.MoPubLog;
import java.util.WeakHashMap;

@Deprecated
class NativeAdViewHelper {
    private static final WeakHashMap<View, NativeAd> sNativeAdMap = new WeakHashMap();

    private NativeAdViewHelper() {
    }

    @Deprecated
    @NonNull
    static View getAdView(@Nullable View convertView, @Nullable ViewGroup parent, @NonNull Context context, @Nullable NativeAd nativeAd) {
        if (convertView != null) {
            clearNativeAd(convertView);
        }
        if (nativeAd == null || nativeAd.isDestroyed()) {
            MoPubLog.d("NativeAd null or invalid. Returning empty view");
            if (convertView != null && ViewType.EMPTY.equals(convertView.getTag())) {
                return convertView;
            }
            convertView = new View(context);
            convertView.setTag(ViewType.EMPTY);
            convertView.setVisibility(8);
            return convertView;
        }
        if (convertView == null || !ViewType.AD.equals(convertView.getTag())) {
            convertView = nativeAd.createAdView(context, parent);
            convertView.setTag(ViewType.AD);
        }
        prepareNativeAd(convertView, nativeAd);
        nativeAd.renderAdView(convertView);
        return convertView;
    }

    private static void clearNativeAd(@NonNull View view) {
        NativeAd nativeAd = (NativeAd) sNativeAdMap.get(view);
        if (nativeAd != null) {
            nativeAd.clear(view);
        }
    }

    private static void prepareNativeAd(@NonNull View view, @NonNull NativeAd nativeAd) {
        sNativeAdMap.put(view, nativeAd);
        nativeAd.prepare(view);
    }
}

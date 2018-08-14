package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.DataKeys;
import com.mopub.common.event.EventDetails;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.factories.CustomEventNativeFactory;
import com.mopub.network.AdResponse;
import java.util.Map;

final class CustomEventNativeAdapter {
    private CustomEventNativeAdapter() {
    }

    public static void loadNativeAd(@NonNull Context context, @NonNull Map<String, Object> localExtras, @NonNull AdResponse adResponse, @NonNull CustomEventNativeListener customEventNativeListener) {
        String customEventNativeClassName = adResponse.getCustomEventClassName();
        MoPubLog.m12061d("Attempting to invoke custom event: " + customEventNativeClassName);
        try {
            CustomEventNative customEventNative = CustomEventNativeFactory.create(customEventNativeClassName);
            if (adResponse.hasJson()) {
                localExtras.put(DataKeys.JSON_BODY_KEY, adResponse.getJsonBody());
            }
            EventDetails eventDetails = adResponse.getEventDetails();
            if (eventDetails != null) {
                localExtras.put(DataKeys.EVENT_DETAILS, eventDetails);
            }
            localExtras.put(DataKeys.CLICK_TRACKING_URL_KEY, adResponse.getClickTrackingUrl());
            try {
                customEventNative.loadNativeAd(context, customEventNativeListener, localExtras, adResponse.getServerExtras());
            } catch (Exception e) {
                MoPubLog.m12070w("Loading custom event native threw an error.", e);
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND);
            }
        } catch (Exception e2) {
            MoPubLog.m12069w("Failed to load Custom Event Native class: " + customEventNativeClassName);
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND);
        }
    }
}

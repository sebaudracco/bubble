package com.mopub.mobileads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WebViewCacheService {
    @VisibleForTesting
    static final int MAX_SIZE = 50;
    @VisibleForTesting
    static final long TRIM_CACHE_FREQUENCY_MILLIS = 900000;
    @NonNull
    private static Handler sHandler = new Handler();
    @NonNull
    @VisibleForTesting
    static final TrimCacheRunnable sTrimCacheRunnable = new TrimCacheRunnable(null);
    @SuppressLint({"UseSparseArrays"})
    @NonNull
    private static final Map<Long, Config> sWebViewConfigs = Collections.synchronizedMap(new HashMap());

    private WebViewCacheService() {
    }

    @VisibleForTesting
    public static void storeWebViewConfig(@NonNull Long broadcastIdentifier, @NonNull Interstitial baseInterstitial, @NonNull BaseWebView baseWebView, @NonNull ExternalViewabilitySessionManager viewabilityManager) {
        Preconditions.checkNotNull(broadcastIdentifier);
        Preconditions.checkNotNull(baseInterstitial);
        Preconditions.checkNotNull(baseWebView);
        trimCache();
        if (sWebViewConfigs.size() >= 50) {
            MoPubLog.w("Unable to cache web view. Please destroy some via MoPubInterstitial#destroy() and try again.");
        } else {
            sWebViewConfigs.put(broadcastIdentifier, new Config(baseWebView, baseInterstitial, viewabilityManager));
        }
    }

    @Nullable
    public static Config popWebViewConfig(@NonNull Long broadcastIdentifier) {
        Preconditions.checkNotNull(broadcastIdentifier);
        return (Config) sWebViewConfigs.remove(broadcastIdentifier);
    }

    @VisibleForTesting
    static synchronized void trimCache() {
        synchronized (WebViewCacheService.class) {
            Iterator<Entry<Long, Config>> iterator = sWebViewConfigs.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<Long, Config> entry = (Entry) iterator.next();
                if (((Config) entry.getValue()).getWeakInterstitial().get() == null) {
                    ((Config) entry.getValue()).getViewabilityManager().endDisplaySession();
                    iterator.remove();
                }
            }
            if (!sWebViewConfigs.isEmpty()) {
                sHandler.removeCallbacks(sTrimCacheRunnable);
                sHandler.postDelayed(sTrimCacheRunnable, TRIM_CACHE_FREQUENCY_MILLIS);
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    public static void clearAll() {
        sWebViewConfigs.clear();
        sHandler.removeCallbacks(sTrimCacheRunnable);
    }

    @NonNull
    @Deprecated
    @VisibleForTesting
    static Map<Long, Config> getWebViewConfigs() {
        return sWebViewConfigs;
    }

    @Deprecated
    @VisibleForTesting
    static void setHandler(@NonNull Handler handler) {
        sHandler = handler;
    }
}

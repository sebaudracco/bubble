package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubLifecycleManager;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public abstract class CustomEventRewardedAd {
    protected abstract boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception;

    @NonNull
    protected abstract String getAdNetworkId();

    @Nullable
    @VisibleForTesting
    protected abstract LifecycleListener getLifecycleListener();

    protected abstract boolean isReady();

    protected abstract void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception;

    protected abstract void onInvalidate();

    protected abstract void show();

    final void loadCustomEvent(@NonNull Activity launcherActivity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) {
        try {
            if (checkAndInitializeSdk(launcherActivity, localExtras, serverExtras)) {
                MoPubLifecycleManager.getInstance(launcherActivity).addLifecycleListener(getLifecycleListener());
            }
            loadWithSdkInitialized(launcherActivity, localExtras, serverExtras);
        } catch (Exception e) {
            MoPubLog.m12063e(e.getMessage());
        }
    }
}

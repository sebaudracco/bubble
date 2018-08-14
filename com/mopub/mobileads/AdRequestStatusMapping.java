package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.VisibleForTesting;
import java.util.Map;
import java.util.TreeMap;

public class AdRequestStatusMapping {
    @NonNull
    private final Map<String, AdRequestStatus> mAdUnitToAdRequestStatus = new TreeMap();

    void markFail(@NonNull String adUnitId) {
        this.mAdUnitToAdRequestStatus.remove(adUnitId);
    }

    void markLoading(@NonNull String adUnitId) {
        this.mAdUnitToAdRequestStatus.put(adUnitId, new AdRequestStatus(LoadingStatus.LOADING));
    }

    void markLoaded(@NonNull String adUnitId, @Nullable String failUrlString, @Nullable String impressionTrackerUrlString, @Nullable String clickTrackerUrlString) {
        this.mAdUnitToAdRequestStatus.put(adUnitId, new AdRequestStatus(LoadingStatus.LOADED, failUrlString, impressionTrackerUrlString, clickTrackerUrlString));
    }

    void markPlayed(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            AdRequestStatus.access$000((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId), LoadingStatus.PLAYED);
        } else {
            this.mAdUnitToAdRequestStatus.put(adUnitId, new AdRequestStatus(LoadingStatus.PLAYED));
        }
    }

    boolean canPlay(@NonNull String adUnitId) {
        AdRequestStatus adRequestStatus = (AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId);
        return adRequestStatus != null && LoadingStatus.LOADED.equals(AdRequestStatus.access$100(adRequestStatus));
    }

    boolean isLoading(@NonNull String adUnitId) {
        if (!this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return false;
        }
        return AdRequestStatus.access$100((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId)) == LoadingStatus.LOADING;
    }

    @Nullable
    String getFailoverUrl(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return AdRequestStatus.access$200((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId));
        }
        return null;
    }

    @Nullable
    String getImpressionTrackerUrlString(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return AdRequestStatus.access$300((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId));
        }
        return null;
    }

    @Nullable
    String getClickTrackerUrlString(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            return AdRequestStatus.access$400((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId));
        }
        return null;
    }

    void clearImpressionUrl(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            AdRequestStatus.access$500((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId), null);
        }
    }

    void clearClickUrl(@NonNull String adUnitId) {
        if (this.mAdUnitToAdRequestStatus.containsKey(adUnitId)) {
            AdRequestStatus.access$600((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(adUnitId), null);
        }
    }

    @Deprecated
    @VisibleForTesting
    void clearMapping() {
        this.mAdUnitToAdRequestStatus.clear();
    }
}

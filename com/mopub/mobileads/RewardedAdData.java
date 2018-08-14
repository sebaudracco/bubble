package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class RewardedAdData {
    @NonNull
    private final Map<String, Set<MoPubReward>> mAdUnitToAvailableRewardsMap = new TreeMap();
    @NonNull
    private final Map<String, String> mAdUnitToCustomDataMap = new TreeMap();
    @NonNull
    private final Map<String, CustomEventRewardedAd> mAdUnitToCustomEventMap = new TreeMap();
    @NonNull
    private final Map<String, MoPubReward> mAdUnitToRewardMap = new TreeMap();
    @NonNull
    private final Map<String, String> mAdUnitToServerCompletionUrlMap = new TreeMap();
    @Nullable
    private String mCurrentlyShowingAdUnitId;
    @NonNull
    private final Map<TwoPartKey, Set<String>> mCustomEventToMoPubIdMap = new HashMap();
    @NonNull
    private final Map<Class<? extends CustomEventRewardedAd>, MoPubReward> mCustomEventToRewardMap = new HashMap();
    @Nullable
    private String mCustomerId;

    RewardedAdData() {
    }

    @Nullable
    CustomEventRewardedAd getCustomEvent(@Nullable String moPubId) {
        return (CustomEventRewardedAd) this.mAdUnitToCustomEventMap.get(moPubId);
    }

    @Nullable
    MoPubReward getMoPubReward(@Nullable String moPubId) {
        return (MoPubReward) this.mAdUnitToRewardMap.get(moPubId);
    }

    @Nullable
    String getCustomData(@Nullable String moPubId) {
        return (String) this.mAdUnitToCustomDataMap.get(moPubId);
    }

    void addAvailableReward(@NonNull String moPubId, @Nullable String currencyName, @Nullable String currencyAmount) {
        Preconditions.checkNotNull(moPubId);
        if (currencyName == null || currencyAmount == null) {
            MoPubLog.e(String.format(Locale.US, "Currency name and amount cannot be null: name = %s, amount = %s", new Object[]{currencyName, currencyAmount}));
            return;
        }
        try {
            int intCurrencyAmount = Integer.parseInt(currencyAmount);
            if (intCurrencyAmount < 0) {
                MoPubLog.e(String.format(Locale.US, "Currency amount cannot be negative: %s", new Object[]{currencyAmount}));
            } else if (this.mAdUnitToAvailableRewardsMap.containsKey(moPubId)) {
                ((Set) this.mAdUnitToAvailableRewardsMap.get(moPubId)).add(MoPubReward.success(currencyName, intCurrencyAmount));
            } else {
                HashSet<MoPubReward> availableRewards = new HashSet();
                availableRewards.add(MoPubReward.success(currencyName, intCurrencyAmount));
                this.mAdUnitToAvailableRewardsMap.put(moPubId, availableRewards);
            }
        } catch (NumberFormatException e) {
            MoPubLog.e(String.format(Locale.US, "Currency amount must be an integer: %s", new Object[]{currencyAmount}));
        }
    }

    @NonNull
    Set<MoPubReward> getAvailableRewards(@NonNull String moPubId) {
        Preconditions.checkNotNull(moPubId);
        Set<MoPubReward> availableRewards = (Set) this.mAdUnitToAvailableRewardsMap.get(moPubId);
        return availableRewards == null ? Collections.emptySet() : availableRewards;
    }

    void selectReward(@NonNull String moPubId, @NonNull MoPubReward selectedReward) {
        Preconditions.checkNotNull(moPubId);
        Preconditions.checkNotNull(selectedReward);
        Set<MoPubReward> availableRewards = (Set) this.mAdUnitToAvailableRewardsMap.get(moPubId);
        if (availableRewards == null || availableRewards.isEmpty()) {
            MoPubLog.e(String.format(Locale.US, "AdUnit %s does not have any rewards.", new Object[]{moPubId}));
        } else if (availableRewards.contains(selectedReward)) {
            updateAdUnitRewardMapping(moPubId, selectedReward.getLabel(), Integer.toString(selectedReward.getAmount()));
        } else {
            MoPubLog.e(String.format(Locale.US, "Selected reward is invalid for AdUnit %s.", new Object[]{moPubId}));
        }
    }

    void resetAvailableRewards(@NonNull String moPubId) {
        Preconditions.checkNotNull(moPubId);
        Set<MoPubReward> availableRewards = (Set) this.mAdUnitToAvailableRewardsMap.get(moPubId);
        if (availableRewards != null && !availableRewards.isEmpty()) {
            availableRewards.clear();
        }
    }

    void resetSelectedReward(@NonNull String moPubId) {
        Preconditions.checkNotNull(moPubId);
        updateAdUnitRewardMapping(moPubId, null, null);
    }

    @Nullable
    String getServerCompletionUrl(@Nullable String moPubId) {
        if (TextUtils.isEmpty(moPubId)) {
            return null;
        }
        return (String) this.mAdUnitToServerCompletionUrlMap.get(moPubId);
    }

    @Nullable
    MoPubReward getLastShownMoPubReward(@NonNull Class<? extends CustomEventRewardedAd> customEventClass) {
        return (MoPubReward) this.mCustomEventToRewardMap.get(customEventClass);
    }

    @NonNull
    Set<String> getMoPubIdsForAdNetwork(@NonNull Class<? extends CustomEventRewardedAd> customEventClass, @Nullable String adNetworkId) {
        if (adNetworkId == null) {
            Set<String> hashSet = new HashSet();
            for (Entry<TwoPartKey, Set<String>> entry : this.mCustomEventToMoPubIdMap.entrySet()) {
                if (customEventClass == ((TwoPartKey) entry.getKey()).customEventClass) {
                    hashSet.addAll((Collection) entry.getValue());
                }
            }
            return hashSet;
        }
        Set<String> set;
        TwoPartKey key = new TwoPartKey(customEventClass, adNetworkId);
        if (this.mCustomEventToMoPubIdMap.containsKey(key)) {
            set = (Set) this.mCustomEventToMoPubIdMap.get(key);
        } else {
            set = Collections.emptySet();
        }
        return set;
    }

    void updateAdUnitCustomEventMapping(@NonNull String moPubId, @NonNull CustomEventRewardedAd customEvent, @NonNull String adNetworkId) {
        this.mAdUnitToCustomEventMap.put(moPubId, customEvent);
        associateCustomEventWithMoPubId(customEvent.getClass(), adNetworkId, moPubId);
    }

    void updateAdUnitRewardMapping(@NonNull String moPubId, @Nullable String currencyName, @Nullable String currencyAmount) {
        Preconditions.checkNotNull(moPubId);
        if (currencyName == null || currencyAmount == null) {
            this.mAdUnitToRewardMap.remove(moPubId);
            return;
        }
        try {
            int intCurrencyAmount = Integer.parseInt(currencyAmount);
            if (intCurrencyAmount < 0) {
                MoPubLog.e(String.format(Locale.US, "Currency amount cannot be negative: %s", new Object[]{currencyAmount}));
                return;
            }
            this.mAdUnitToRewardMap.put(moPubId, MoPubReward.success(currencyName, intCurrencyAmount));
        } catch (NumberFormatException e) {
            MoPubLog.e(String.format(Locale.US, "Currency amount must be an integer: %s", new Object[]{currencyAmount}));
        }
    }

    void updateAdUnitToServerCompletionUrlMapping(@NonNull String moPubId, @Nullable String serverCompletionUrl) {
        Preconditions.checkNotNull(moPubId);
        this.mAdUnitToServerCompletionUrlMap.put(moPubId, serverCompletionUrl);
    }

    void updateCustomEventLastShownRewardMapping(@NonNull Class<? extends CustomEventRewardedAd> customEventClass, @Nullable MoPubReward moPubReward) {
        Preconditions.checkNotNull(customEventClass);
        this.mCustomEventToRewardMap.put(customEventClass, moPubReward);
    }

    void associateCustomEventWithMoPubId(@NonNull Class<? extends CustomEventRewardedAd> customEventClass, @NonNull String adNetworkId, @NonNull String moPubId) {
        Set<String> moPubIds;
        TwoPartKey newCustomEventMapping = new TwoPartKey(customEventClass, adNetworkId);
        Iterator<Entry<TwoPartKey, Set<String>>> entryIterator = this.mCustomEventToMoPubIdMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<TwoPartKey, Set<String>> entry = (Entry) entryIterator.next();
            if (!((TwoPartKey) entry.getKey()).equals(newCustomEventMapping) && ((Set) entry.getValue()).contains(moPubId)) {
                ((Set) entry.getValue()).remove(moPubId);
                if (((Set) entry.getValue()).isEmpty()) {
                    entryIterator.remove();
                }
                moPubIds = (Set) this.mCustomEventToMoPubIdMap.get(newCustomEventMapping);
                if (moPubIds == null) {
                    moPubIds = new HashSet();
                    this.mCustomEventToMoPubIdMap.put(newCustomEventMapping, moPubIds);
                }
                moPubIds.add(moPubId);
            }
        }
        moPubIds = (Set) this.mCustomEventToMoPubIdMap.get(newCustomEventMapping);
        if (moPubIds == null) {
            moPubIds = new HashSet();
            this.mCustomEventToMoPubIdMap.put(newCustomEventMapping, moPubIds);
        }
        moPubIds.add(moPubId);
    }

    void setCurrentlyShowingAdUnitId(@Nullable String currentAdUnitId) {
        this.mCurrentlyShowingAdUnitId = currentAdUnitId;
    }

    void updateAdUnitToCustomDataMapping(@NonNull String moPubId, @Nullable String customData) {
        NoThrow.checkNotNull(moPubId);
        this.mAdUnitToCustomDataMap.put(moPubId, customData);
    }

    @Nullable
    String getCurrentlyShowingAdUnitId() {
        return this.mCurrentlyShowingAdUnitId;
    }

    void setCustomerId(@Nullable String customerId) {
        this.mCustomerId = customerId;
    }

    @Nullable
    String getCustomerId() {
        return this.mCustomerId;
    }

    @Deprecated
    @VisibleForTesting
    void clear() {
        this.mAdUnitToCustomEventMap.clear();
        this.mAdUnitToRewardMap.clear();
        this.mAdUnitToAvailableRewardsMap.clear();
        this.mAdUnitToServerCompletionUrlMap.clear();
        this.mAdUnitToCustomDataMap.clear();
        this.mCustomEventToRewardMap.clear();
        this.mCustomEventToMoPubIdMap.clear();
        this.mCurrentlyShowingAdUnitId = null;
        this.mCustomerId = null;
    }

    @Deprecated
    @VisibleForTesting
    boolean existsInAvailableRewards(@NonNull String moPubId, @NonNull String currencyName, int currencyAmount) {
        Preconditions.checkNotNull(moPubId);
        Preconditions.checkNotNull(currencyName);
        for (MoPubReward reward : getAvailableRewards(moPubId)) {
            if (reward.getLabel().equals(currencyName) && reward.getAmount() == currencyAmount) {
                return true;
            }
        }
        return false;
    }
}

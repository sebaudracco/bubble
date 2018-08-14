package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.AdReport;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.common.util.MoPubCollections;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.ReflectionTarget;
import com.mopub.common.util.Utils;
import com.mopub.network.AdRequest;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NoConnectionError;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubRewardedVideoManager {
    public static final int API_VERSION = 1;
    private static final String CURRENCIES_JSON_REWARDS_MAP_KEY = "rewards";
    private static final String CURRENCIES_JSON_REWARD_AMOUNT_KEY = "amount";
    private static final String CURRENCIES_JSON_REWARD_NAME_KEY = "name";
    @VisibleForTesting
    static final int CUSTOM_DATA_MAX_LENGTH_BYTES = 8192;
    private static final String CUSTOM_EVENT_PREF_NAME = "mopubCustomEventSettings";
    private static final int DEFAULT_LOAD_TIMEOUT = 30000;
    @NonNull
    private static SharedPreferences sCustomEventSharedPrefs;
    private static MoPubRewardedVideoManager sInstance;
    @NonNull
    private final AdRequestStatusMapping mAdRequestStatus;
    @NonNull
    private final Handler mCallbackHandler = new Handler(Looper.getMainLooper());
    @NonNull
    private final Context mContext;
    @NonNull
    private final Handler mCustomEventTimeoutHandler;
    @NonNull
    private final Set<MediationSettings> mGlobalMediationSettings = new HashSet();
    @NonNull
    private final Map<String, Set<MediationSettings>> mInstanceMediationSettings;
    @NonNull
    private WeakReference<Activity> mMainActivity;
    @NonNull
    private final RewardedAdData mRewardedAdData = new RewardedAdData();
    @NonNull
    private final Map<String, Runnable> mTimeoutMap;
    @Nullable
    private MoPubRewardedVideoListener mVideoListener;

    private MoPubRewardedVideoManager(@NonNull Activity mainActivity, MediationSettings... mediationSettings) {
        this.mMainActivity = new WeakReference(mainActivity);
        this.mContext = mainActivity.getApplicationContext();
        MoPubCollections.addAllNonNull(this.mGlobalMediationSettings, mediationSettings);
        this.mInstanceMediationSettings = new HashMap();
        this.mCustomEventTimeoutHandler = new Handler();
        this.mTimeoutMap = new HashMap();
        this.mAdRequestStatus = new AdRequestStatusMapping();
        sCustomEventSharedPrefs = SharedPreferencesHelper.getSharedPreferences(this.mContext, CUSTOM_EVENT_PREF_NAME);
    }

    @NonNull
    public static synchronized List<CustomEventRewardedVideo> initNetworks(@NonNull Activity mainActivity, @NonNull List<Class<? extends CustomEventRewardedVideo>> networksToInit) {
        List<CustomEventRewardedVideo> emptyList;
        synchronized (MoPubRewardedVideoManager.class) {
            Preconditions.checkNotNull(mainActivity);
            Preconditions.checkNotNull(networksToInit);
            if (sInstance == null) {
                logErrorNotInitialized();
                emptyList = Collections.emptyList();
            } else {
                emptyList = new LinkedList();
                Map<String, ?> networkInitSettings = sCustomEventSharedPrefs.getAll();
                MoPubLog.d(String.format(Locale.US, "fetched init settings for %s networks: %s", new Object[]{Integer.valueOf(networkInitSettings.size()), networkInitSettings.keySet()}));
                Iterator it = new LinkedHashSet(networksToInit).iterator();
                while (it.hasNext()) {
                    String networkClassName = ((Class) it.next()).getName();
                    if (networkInitSettings.containsKey(networkClassName)) {
                        try {
                            CustomEventRewardedVideo customEvent = (CustomEventRewardedVideo) Reflection.instantiateClassWithEmptyConstructor(networkClassName, CustomEventRewardedVideo.class);
                            MoPubLog.d(String.format(Locale.US, "Initializing %s with params %s", new Object[]{networkClassName, Json.jsonStringToMap((String) networkInitSettings.get(networkClassName))}));
                            customEvent.checkAndInitializeSdk(mainActivity, Collections.emptyMap(), networkInitParamsMap);
                            emptyList.add(customEvent);
                        } catch (Exception e) {
                            MoPubLog.e("Error fetching init settings for network " + networkClassName);
                        }
                    } else {
                        MoPubLog.d("Init settings not found for " + networkClassName);
                    }
                }
            }
        }
        return emptyList;
    }

    public static synchronized void init(@NonNull Activity mainActivity, MediationSettings... mediationSettings) {
        synchronized (MoPubRewardedVideoManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubRewardedVideoManager(mainActivity, mediationSettings);
            } else {
                MoPubLog.e("Tried to call initializeRewardedVideo more than once. Only the first initialization call has any effect.");
            }
        }
    }

    @ReflectionTarget
    public static void updateActivity(@NonNull Activity activity) {
        if (sInstance != null) {
            sInstance.mMainActivity = new WeakReference(activity);
            return;
        }
        logErrorNotInitialized();
    }

    @Nullable
    public static <T extends MediationSettings> T getGlobalMediationSettings(@NonNull Class<T> clazz) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return null;
        }
        for (MediationSettings mediationSettings : sInstance.mGlobalMediationSettings) {
            if (clazz.equals(mediationSettings.getClass())) {
                return (MediationSettings) clazz.cast(mediationSettings);
            }
        }
        return null;
    }

    @Nullable
    public static <T extends MediationSettings> T getInstanceMediationSettings(@NonNull Class<T> clazz, @NonNull String adUnitId) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return null;
        }
        Set<MediationSettings> instanceMediationSettings = (Set) sInstance.mInstanceMediationSettings.get(adUnitId);
        if (instanceMediationSettings == null) {
            return null;
        }
        for (MediationSettings mediationSettings : instanceMediationSettings) {
            if (clazz.equals(mediationSettings.getClass())) {
                return (MediationSettings) clazz.cast(mediationSettings);
            }
        }
        return null;
    }

    public static void setVideoListener(@Nullable MoPubRewardedVideoListener listener) {
        if (sInstance != null) {
            sInstance.mVideoListener = listener;
        } else {
            logErrorNotInitialized();
        }
    }

    public static void loadVideo(@NonNull String adUnitId, @Nullable RequestParameters requestParameters, @Nullable MediationSettings... mediationSettings) {
        Location location = null;
        Preconditions.checkNotNull(adUnitId);
        if (sInstance == null) {
            logErrorNotInitialized();
        } else if (adUnitId.equals(sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId())) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. The ad is already showing.", new Object[]{adUnitId}));
        } else if (sInstance.mAdRequestStatus.canPlay(adUnitId)) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. This ad unit already finished loading and is ready to show.", new Object[]{adUnitId}));
            postToInstance(new 1(adUnitId));
        } else {
            Set<MediationSettings> newInstanceMediationSettings = new HashSet();
            MoPubCollections.addAllNonNull(newInstanceMediationSettings, mediationSettings);
            sInstance.mInstanceMediationSettings.put(adUnitId, newInstanceMediationSettings);
            String customerId = requestParameters == null ? null : requestParameters.mCustomerId;
            if (!TextUtils.isEmpty(customerId)) {
                sInstance.mRewardedAdData.setCustomerId(customerId);
            }
            AdUrlGenerator withKeywords = new WebViewAdUrlGenerator(sInstance.mContext, false).withAdUnitId(adUnitId).withKeywords(requestParameters == null ? null : requestParameters.mKeywords);
            if (requestParameters != null) {
                location = requestParameters.mLocation;
            }
            loadVideo(adUnitId, withKeywords.withLocation(location).generateUrlString(Constants.HOST));
        }
    }

    private static void loadVideo(@NonNull String adUnitId, @NonNull String adUrlString) {
        if (sInstance == null) {
            logErrorNotInitialized();
        } else if (sInstance.mAdRequestStatus.isLoading(adUnitId)) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. A request is already pending.", new Object[]{adUnitId}));
        } else {
            Networking.getRequestQueue(sInstance.mContext).add(new AdRequest(adUrlString, AdFormat.REWARDED_VIDEO, adUnitId, sInstance.mContext, new RewardedVideoRequestListener(sInstance, adUnitId)));
            sInstance.mAdRequestStatus.markLoading(adUnitId);
            MoPubLog.d(String.format(Locale.US, "Loading rewarded ad request for ad unit %s with URL %s", new Object[]{adUnitId, adUrlString}));
        }
    }

    public static boolean hasVideo(@NonNull String adUnitId) {
        if (sInstance != null) {
            return isPlayable(adUnitId, sInstance.mRewardedAdData.getCustomEvent(adUnitId));
        }
        logErrorNotInitialized();
        return false;
    }

    public static void showVideo(@NonNull String adUnitId) {
        showVideo(adUnitId, null);
    }

    public static void showVideo(@NonNull String adUnitId, @Nullable String customData) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return;
        }
        if (customData != null && customData.length() > 8192) {
            MoPubLog.w(String.format(Locale.US, "Provided rewarded ad custom data parameter longer than supported(%d bytes, %d maximum)", new Object[]{Integer.valueOf(customData.length()), Integer.valueOf(8192)}));
        }
        CustomEventRewardedAd customEvent = sInstance.mRewardedAdData.getCustomEvent(adUnitId);
        if (!isPlayable(adUnitId, customEvent)) {
            if (sInstance.mAdRequestStatus.isLoading(adUnitId)) {
                MoPubLog.d("Rewarded ad is not ready to be shown yet.");
            } else {
                MoPubLog.d("No rewarded ad loading or loaded.");
            }
            sInstance.failover(adUnitId, MoPubErrorCode.VIDEO_NOT_AVAILABLE);
        } else if (sInstance.mRewardedAdData.getAvailableRewards(adUnitId).isEmpty() || sInstance.mRewardedAdData.getMoPubReward(adUnitId) != null) {
            sInstance.mRewardedAdData.updateCustomEventLastShownRewardMapping(customEvent.getClass(), sInstance.mRewardedAdData.getMoPubReward(adUnitId));
            sInstance.mRewardedAdData.updateAdUnitToCustomDataMapping(adUnitId, customData);
            sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId(adUnitId);
            sInstance.mAdRequestStatus.markPlayed(adUnitId);
            customEvent.show();
        } else {
            sInstance.failover(adUnitId, MoPubErrorCode.REWARD_NOT_SELECTED);
        }
    }

    private static boolean isPlayable(String adUnitId, @Nullable CustomEventRewardedAd customEvent) {
        return sInstance != null && sInstance.mAdRequestStatus.canPlay(adUnitId) && customEvent != null && customEvent.isReady();
    }

    @NonNull
    public static Set<MoPubReward> getAvailableRewards(@NonNull String adUnitId) {
        if (sInstance != null) {
            return sInstance.mRewardedAdData.getAvailableRewards(adUnitId);
        }
        logErrorNotInitialized();
        return Collections.emptySet();
    }

    public static void selectReward(@NonNull String adUnitId, @NonNull MoPubReward selectedReward) {
        if (sInstance != null) {
            sInstance.mRewardedAdData.selectReward(adUnitId, selectedReward);
        } else {
            logErrorNotInitialized();
        }
    }

    private void onAdSuccess(AdResponse adResponse, String adUnitId) {
        this.mAdRequestStatus.markLoaded(adUnitId, adResponse.getFailoverUrl(), adResponse.getImpressionTrackingUrl(), adResponse.getClickTrackingUrl());
        Integer timeoutMillis = adResponse.getAdTimeoutMillis();
        if (timeoutMillis == null || timeoutMillis.intValue() <= 0) {
            timeoutMillis = Integer.valueOf(30000);
        }
        String customEventClassName = adResponse.getCustomEventClassName();
        if (customEventClassName == null) {
            MoPubLog.e("Couldn't create custom event, class name was null.");
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        CustomEventRewardedAd oldRewardedVideo = this.mRewardedAdData.getCustomEvent(adUnitId);
        if (oldRewardedVideo != null) {
            oldRewardedVideo.onInvalidate();
        }
        try {
            CustomEventRewardedAd customEvent = (CustomEventRewardedAd) Reflection.instantiateClassWithEmptyConstructor(customEventClassName, CustomEventRewardedAd.class);
            Map<String, Object> localExtras = new TreeMap();
            localExtras.put(DataKeys.AD_UNIT_ID_KEY, adUnitId);
            localExtras.put(DataKeys.REWARDED_AD_CURRENCY_NAME_KEY, adResponse.getRewardedVideoCurrencyName());
            localExtras.put(DataKeys.REWARDED_AD_CURRENCY_AMOUNT_STRING_KEY, adResponse.getRewardedVideoCurrencyAmount());
            localExtras.put(DataKeys.REWARDED_AD_DURATION_KEY, adResponse.getRewardedDuration());
            localExtras.put(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, Boolean.valueOf(adResponse.shouldRewardOnClick()));
            localExtras.put(DataKeys.AD_REPORT_KEY, new AdReport(adUnitId, ClientMetadata.getInstance(this.mContext), adResponse));
            localExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(Utils.generateUniqueId()));
            localExtras.put("Rewarded-Ad-Customer-Id", this.mRewardedAdData.getCustomerId());
            String rewardedCurrencies = adResponse.getRewardedCurrencies();
            this.mRewardedAdData.resetAvailableRewards(adUnitId);
            this.mRewardedAdData.resetSelectedReward(adUnitId);
            if (TextUtils.isEmpty(rewardedCurrencies)) {
                this.mRewardedAdData.updateAdUnitRewardMapping(adUnitId, adResponse.getRewardedVideoCurrencyName(), adResponse.getRewardedVideoCurrencyAmount());
            } else {
                try {
                    parseMultiCurrencyJson(adUnitId, rewardedCurrencies);
                } catch (Exception e) {
                    MoPubLog.e("Error parsing rewarded currencies JSON header: " + rewardedCurrencies);
                    failover(adUnitId, MoPubErrorCode.REWARDED_CURRENCIES_PARSING_ERROR);
                    return;
                }
            }
            this.mRewardedAdData.updateAdUnitToServerCompletionUrlMapping(adUnitId, adResponse.getRewardedVideoCompletionUrl());
            Activity mainActivity = (Activity) this.mMainActivity.get();
            if (mainActivity == null) {
                MoPubLog.d("Could not load custom event because Activity reference was null. Call MoPub#updateActivity before requesting more rewarded ads.");
                this.mAdRequestStatus.markFail(adUnitId);
                return;
            }
            Runnable 2 = new 2(this, customEvent);
            this.mCustomEventTimeoutHandler.postDelayed(2, (long) timeoutMillis.intValue());
            this.mTimeoutMap.put(adUnitId, 2);
            Map<String, String> serverExtras = adResponse.getServerExtras();
            if (customEvent instanceof CustomEventRewardedVideo) {
                MoPubLog.d(String.format(Locale.US, "Updating init settings for custom event %s with params %s", new Object[]{customEventClassName, new JSONObject(serverExtras).toString()}));
                sCustomEventSharedPrefs.edit().putString(customEventClassName, serverExtrasJsonString).commit();
            }
            MoPubLog.d(String.format(Locale.US, "Loading custom event with class name %s", new Object[]{customEventClassName}));
            customEvent.loadCustomEvent(mainActivity, localExtras, serverExtras);
            this.mRewardedAdData.updateAdUnitCustomEventMapping(adUnitId, customEvent, customEvent.getAdNetworkId());
        } catch (Exception e2) {
            MoPubLog.e(String.format(Locale.US, "Couldn't create custom event with class name %s", new Object[]{customEventClassName}));
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    private void onAdError(@NonNull VolleyError volleyError, @NonNull String adUnitId) {
        MoPubErrorCode errorCode = MoPubErrorCode.INTERNAL_ERROR;
        if (volleyError instanceof MoPubNetworkError) {
            switch (15.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case 1:
                case 2:
                    errorCode = MoPubErrorCode.NO_FILL;
                    break;
                default:
                    errorCode = MoPubErrorCode.INTERNAL_ERROR;
                    break;
            }
        }
        if (volleyError instanceof NoConnectionError) {
            errorCode = MoPubErrorCode.NO_CONNECTION;
        }
        failover(adUnitId, errorCode);
    }

    private void parseMultiCurrencyJson(@NonNull String adUnitId, @NonNull String rewardedCurrencies) throws JSONException {
        int i = 0;
        String[] rewardsArray = Json.jsonArrayToStringArray((String) Json.jsonStringToMap(rewardedCurrencies).get(CURRENCIES_JSON_REWARDS_MAP_KEY));
        if (rewardsArray.length == 1) {
            Map<String, String> rewardData = Json.jsonStringToMap(rewardsArray[0]);
            this.mRewardedAdData.updateAdUnitRewardMapping(adUnitId, (String) rewardData.get("name"), (String) rewardData.get(CURRENCIES_JSON_REWARD_AMOUNT_KEY));
        }
        int length = rewardsArray.length;
        while (i < length) {
            rewardData = Json.jsonStringToMap(rewardsArray[i]);
            this.mRewardedAdData.addAvailableReward(adUnitId, (String) rewardData.get("name"), (String) rewardData.get(CURRENCIES_JSON_REWARD_AMOUNT_KEY));
            i++;
        }
    }

    private void failover(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        Preconditions.checkNotNull(adUnitId);
        Preconditions.checkNotNull(errorCode);
        String failoverUrl = this.mAdRequestStatus.getFailoverUrl(adUnitId);
        this.mAdRequestStatus.markFail(adUnitId);
        if (failoverUrl != null && !errorCode.equals(MoPubErrorCode.EXPIRED)) {
            loadVideo(adUnitId, failoverUrl);
        } else if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoLoadFailure(adUnitId, errorCode);
        }
    }

    private void cancelTimeouts(@NonNull String moPubId) {
        Runnable runnable = (Runnable) this.mTimeoutMap.remove(moPubId);
        if (runnable != null) {
            this.mCustomEventTimeoutHandler.removeCallbacks(runnable);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoLoadSuccess(@NonNull Class<T> customEventClass, @NonNull String thirdPartyId) {
        postToInstance(new 3(customEventClass, thirdPartyId));
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoLoadFailure(@NonNull Class<T> customEventClass, String thirdPartyId, MoPubErrorCode errorCode) {
        postToInstance(new 4(customEventClass, thirdPartyId, errorCode));
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoStarted(@NonNull Class<T> customEventClass, String thirdPartyId) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new 5(customEventClass, thirdPartyId));
        } else {
            postToInstance(new 6(currentlyShowingAdUnitId));
        }
    }

    private static void onRewardedVideoStartedAction(@NonNull String adUnitId) {
        Preconditions.checkNotNull(adUnitId);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoStarted(adUnitId);
        }
        TrackingRequest.makeTrackingHttpRequest(sInstance.mAdRequestStatus.getImpressionTrackerUrlString(adUnitId), sInstance.mContext);
        sInstance.mAdRequestStatus.clearImpressionUrl(adUnitId);
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoPlaybackError(@NonNull Class<T> customEventClass, String thirdPartyId, MoPubErrorCode errorCode) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new 7(customEventClass, thirdPartyId, errorCode));
        } else {
            postToInstance(new 8(currentlyShowingAdUnitId, errorCode));
        }
    }

    private static void onRewardedVideoPlaybackErrorAction(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        Preconditions.checkNotNull(adUnitId);
        Preconditions.checkNotNull(errorCode);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoPlaybackError(adUnitId, errorCode);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoClicked(@NonNull Class<T> customEventClass, String thirdPartyId) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new 9(customEventClass, thirdPartyId));
        } else {
            postToInstance(new 10(currentlyShowingAdUnitId));
        }
    }

    private static void onRewardedVideoClickedAction(@NonNull String adUnitId) {
        Preconditions.checkNotNull(adUnitId);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoClicked(adUnitId);
        }
        TrackingRequest.makeTrackingHttpRequest(sInstance.mAdRequestStatus.getClickTrackerUrlString(adUnitId), sInstance.mContext);
        sInstance.mAdRequestStatus.clearClickUrl(adUnitId);
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoClosed(@NonNull Class<T> customEventClass, String thirdPartyId) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new 11(customEventClass, thirdPartyId));
        } else {
            postToInstance(new 12(currentlyShowingAdUnitId));
        }
        sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId(null);
    }

    private static void onRewardedVideoClosedAction(@NonNull String adUnitId) {
        Preconditions.checkNotNull(adUnitId);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoClosed(adUnitId);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoCompleted(@NonNull Class<T> customEventClass, String thirdPartyId, @NonNull MoPubReward moPubReward) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        rewardOnClient(customEventClass, thirdPartyId, moPubReward, currentlyShowingAdUnitId);
        rewardOnServer(currentlyShowingAdUnitId);
    }

    private static void rewardOnServer(@Nullable String currentlyShowingAdUnitId) {
        String serverCompletionUrl = sInstance.mRewardedAdData.getServerCompletionUrl(currentlyShowingAdUnitId);
        if (!TextUtils.isEmpty(serverCompletionUrl)) {
            postToInstance(new 13(currentlyShowingAdUnitId, serverCompletionUrl));
        }
    }

    private static <T extends CustomEventRewardedAd> void rewardOnClient(@NonNull Class<T> customEventClass, @Nullable String thirdPartyId, @NonNull MoPubReward moPubReward, @Nullable String currentlyShowingAdUnitId) {
        postToInstance(new 14(customEventClass, moPubReward, currentlyShowingAdUnitId, thirdPartyId));
    }

    @VisibleForTesting
    static MoPubReward chooseReward(@Nullable MoPubReward moPubReward, @NonNull MoPubReward networkReward) {
        if (!networkReward.isSuccessful()) {
            return networkReward;
        }
        if (moPubReward == null) {
            moPubReward = networkReward;
        }
        return moPubReward;
    }

    private static void postToInstance(@NonNull Runnable runnable) {
        if (sInstance != null) {
            sInstance.mCallbackHandler.post(runnable);
        }
    }

    private static void logErrorNotInitialized() {
        MoPubLog.e("MoPub rewarded ad was not initialized. You must call MoPub.initializeRewardedVideo() before loading or attempting to play rewarded ads.");
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    static RewardedAdData getRewardedAdData() {
        if (sInstance != null) {
            return sInstance.mRewardedAdData;
        }
        return null;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    static AdRequestStatusMapping getAdRequestStatusMapping() {
        if (sInstance != null) {
            return sInstance.mAdRequestStatus;
        }
        return null;
    }

    @Deprecated
    @VisibleForTesting
    static void setCustomEventSharedPrefs(@NonNull SharedPreferences sharedPrefs) {
        Preconditions.checkNotNull(sharedPrefs);
        sCustomEventSharedPrefs = sharedPrefs;
    }
}

package com.mopub.mobileads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RewardedVideoCompletionRequest.RewardedVideoCompletionRequestListener;
import com.mopub.network.Networking;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.VolleyError;
import cz.msebera.android.httpclient.HttpStatus;

public class RewardedVideoCompletionRequestHandler implements RewardedVideoCompletionRequestListener {
    private static final String API_VERSION_KEY = "&v=";
    private static final String CUSTOMER_ID_KEY = "&customer_id=";
    private static final String CUSTOM_DATA_KEY = "&rcd=";
    private static final String CUSTOM_EVENT_CLASS_NAME_KEY = "&cec=";
    static final int MAX_RETRIES = 17;
    static final int REQUEST_TIMEOUT_DELAY = 1000;
    static final int[] RETRY_TIMES = new int[]{5000, 10000, 20000, 40000, 60000};
    private static final String REWARD_AMOUNT_KEY = "&rca=";
    private static final String REWARD_NAME_KEY = "&rcn=";
    private static final String SDK_VERSION_KEY = "&nv=";
    @NonNull
    private final Handler mHandler;
    @NonNull
    private final RequestQueue mRequestQueue;
    private int mRetryCount;
    private volatile boolean mShouldStop;
    @NonNull
    private final String mUrl;

    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String url, @Nullable String customerId, @NonNull String rewardName, @NonNull String rewardAmount, @Nullable String className, @Nullable String customData) {
        this(context, url, customerId, rewardName, rewardAmount, className, customData, new Handler());
    }

    @VisibleForTesting
    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String url, @Nullable String customerId, @NonNull String rewardName, @NonNull String rewardAmount, @Nullable String className, @Nullable String customData, @NonNull Handler handler) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(rewardName);
        Preconditions.checkNotNull(rewardAmount);
        Preconditions.checkNotNull(handler);
        this.mUrl = appendParameters(url, customerId, rewardName, rewardAmount, className, customData);
        this.mRetryCount = 0;
        this.mHandler = handler;
        this.mRequestQueue = Networking.getRequestQueue(context);
    }

    void makeRewardedVideoCompletionRequest() {
        if (this.mShouldStop) {
            this.mRequestQueue.cancelAll(this.mUrl);
            return;
        }
        RewardedVideoCompletionRequest rewardedVideoCompletionRequest = new RewardedVideoCompletionRequest(this.mUrl, new DefaultRetryPolicy(getTimeout(this.mRetryCount) - 1000, 0, 0.0f), this);
        rewardedVideoCompletionRequest.setTag(this.mUrl);
        this.mRequestQueue.add(rewardedVideoCompletionRequest);
        if (this.mRetryCount >= 17) {
            MoPubLog.d("Exceeded number of retries for rewarded video completion request.");
            return;
        }
        this.mHandler.postDelayed(new 1(this), (long) getTimeout(this.mRetryCount));
        this.mRetryCount++;
    }

    public void onResponse(Integer response) {
        if (response == null) {
            return;
        }
        if (response.intValue() < HttpStatus.SC_INTERNAL_SERVER_ERROR || response.intValue() >= Settings.MAX_DYNAMIC_ACQUISITION) {
            this.mShouldStop = true;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null && volleyError.networkResponse != null) {
            if (volleyError.networkResponse.statusCode < HttpStatus.SC_INTERNAL_SERVER_ERROR || volleyError.networkResponse.statusCode >= Settings.MAX_DYNAMIC_ACQUISITION) {
                this.mShouldStop = true;
            }
        }
    }

    public static void makeRewardedVideoCompletionRequest(@Nullable Context context, @Nullable String url, @Nullable String customerId, @NonNull String rewardName, @NonNull String rewardAmount, @Nullable String rewardedAd, @Nullable String customData) {
        if (context != null && !TextUtils.isEmpty(url) && rewardName != null && rewardAmount != null) {
            new RewardedVideoCompletionRequestHandler(context, url, customerId, rewardName, rewardAmount, rewardedAd, customData).makeRewardedVideoCompletionRequest();
        }
    }

    static int getTimeout(int retryCount) {
        if (retryCount < 0 || retryCount >= RETRY_TIMES.length) {
            return RETRY_TIMES[RETRY_TIMES.length - 1];
        }
        return RETRY_TIMES[retryCount];
    }

    private static String appendParameters(@NonNull String url, @Nullable String customerId, @NonNull String rewardName, @NonNull String rewardAmount, @Nullable String className, @Nullable String customData) {
        String str;
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(rewardName);
        Preconditions.checkNotNull(rewardAmount);
        StringBuilder stringBuilder = new StringBuilder(url);
        StringBuilder append = stringBuilder.append(CUSTOMER_ID_KEY).append(customerId == null ? "" : Uri.encode(customerId)).append(REWARD_NAME_KEY).append(Uri.encode(rewardName)).append(REWARD_AMOUNT_KEY).append(Uri.encode(rewardAmount)).append(SDK_VERSION_KEY).append(Uri.encode("4.20.0")).append(API_VERSION_KEY).append(1).append(CUSTOM_EVENT_CLASS_NAME_KEY);
        if (className == null) {
            str = "";
        } else {
            str = Uri.encode(className);
        }
        append.append(str);
        if (!TextUtils.isEmpty(customData)) {
            stringBuilder.append(CUSTOM_DATA_KEY).append(Uri.encode(customData));
        }
        return stringBuilder.toString();
    }

    @Deprecated
    @VisibleForTesting
    boolean getShouldStop() {
        return this.mShouldStop;
    }

    @Deprecated
    @VisibleForTesting
    int getRetryCount() {
        return this.mRetryCount;
    }

    @Deprecated
    @VisibleForTesting
    void setRetryCount(int retryCount) {
        this.mRetryCount = retryCount;
    }
}

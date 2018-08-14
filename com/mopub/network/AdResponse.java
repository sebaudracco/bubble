package com.mopub.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.MoPub$BrowserAgent;
import com.mopub.common.event.EventDetails;
import com.mopub.common.util.DateAndTime;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class AdResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @Nullable
    private final Integer mAdTimeoutDelayMillis;
    @Nullable
    private final String mAdType;
    @Nullable
    private final String mAdUnitId;
    @Nullable
    private final MoPub$BrowserAgent mBrowserAgent;
    @Nullable
    private final String mClickTrackingUrl;
    @Nullable
    private final String mCustomEventClassName;
    @Nullable
    private final String mDspCreativeId;
    @Nullable
    private final EventDetails mEventDetails;
    @Nullable
    private final String mFailoverUrl;
    @Nullable
    private final String mFullAdType;
    @Nullable
    private final Integer mHeight;
    @Nullable
    private final String mImpressionTrackingUrl;
    @Nullable
    private final JSONObject mJsonBody;
    @Nullable
    private final String mNetworkType;
    @Nullable
    private final String mRedirectUrl;
    @Nullable
    private final Integer mRefreshTimeMillis;
    @Nullable
    private final String mRequestId;
    @Nullable
    private final String mResponseBody;
    @Nullable
    private final String mRewardedCurrencies;
    @Nullable
    private final Integer mRewardedDuration;
    @Nullable
    private final String mRewardedVideoCompletionUrl;
    @Nullable
    private final String mRewardedVideoCurrencyAmount;
    @Nullable
    private final String mRewardedVideoCurrencyName;
    private final boolean mScrollable;
    @NonNull
    private final Map<String, String> mServerExtras;
    private final boolean mShouldRewardOnClick;
    private final long mTimestamp;
    @Nullable
    private final Integer mWidth;

    public static class Builder {
        private Integer adTimeoutDelayMillis;
        private String adType;
        private String adUnitId;
        private String clickTrackingUrl;
        private String customEventClassName;
        private String dspCreativeId;
        private EventDetails eventDetails;
        private String failoverUrl;
        private String fullAdType;
        private Integer height;
        private String impressionTrackingUrl;
        private JSONObject jsonBody;
        private MoPub$BrowserAgent mBrowserAgent;
        private String networkType;
        private String redirectUrl;
        private Integer refreshTimeMillis;
        private String requestId;
        private String responseBody;
        private String rewardedCurrencies;
        private Integer rewardedDuration;
        private String rewardedVideoCompletionUrl;
        private String rewardedVideoCurrencyAmount;
        private String rewardedVideoCurrencyName;
        private boolean scrollable = false;
        private Map<String, String> serverExtras = new TreeMap();
        private boolean shouldRewardOnClick;
        private Integer width;

        public Builder setAdType(@Nullable String adType) {
            this.adType = adType;
            return this;
        }

        public Builder setAdUnitId(@Nullable String adUnitId) {
            this.adUnitId = adUnitId;
            return this;
        }

        public Builder setFullAdType(@Nullable String fullAdType) {
            this.fullAdType = fullAdType;
            return this;
        }

        public Builder setNetworkType(@Nullable String networkType) {
            this.networkType = networkType;
            return this;
        }

        public Builder setRewardedVideoCurrencyName(@Nullable String rewardedVideoCurrencyName) {
            this.rewardedVideoCurrencyName = rewardedVideoCurrencyName;
            return this;
        }

        public Builder setRewardedVideoCurrencyAmount(@Nullable String rewardedVideoCurrencyAmount) {
            this.rewardedVideoCurrencyAmount = rewardedVideoCurrencyAmount;
            return this;
        }

        public Builder setRewardedCurrencies(@Nullable String rewardedCurrencies) {
            this.rewardedCurrencies = rewardedCurrencies;
            return this;
        }

        public Builder setRewardedVideoCompletionUrl(@Nullable String rewardedVideoCompletionUrl) {
            this.rewardedVideoCompletionUrl = rewardedVideoCompletionUrl;
            return this;
        }

        public Builder setRewardedDuration(@Nullable Integer rewardedDuration) {
            this.rewardedDuration = rewardedDuration;
            return this;
        }

        public Builder setShouldRewardOnClick(boolean shouldRewardOnClick) {
            this.shouldRewardOnClick = shouldRewardOnClick;
            return this;
        }

        public Builder setRedirectUrl(@Nullable String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder setClickTrackingUrl(@Nullable String clickTrackingUrl) {
            this.clickTrackingUrl = clickTrackingUrl;
            return this;
        }

        public Builder setImpressionTrackingUrl(@Nullable String impressionTrackingUrl) {
            this.impressionTrackingUrl = impressionTrackingUrl;
            return this;
        }

        public Builder setFailoverUrl(@Nullable String failoverUrl) {
            this.failoverUrl = failoverUrl;
            return this;
        }

        public Builder setRequestId(@Nullable String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder setDimensions(@Nullable Integer width, @Nullable Integer height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder setAdTimeoutDelayMilliseconds(@Nullable Integer adTimeoutDelayMilliseconds) {
            this.adTimeoutDelayMillis = adTimeoutDelayMilliseconds;
            return this;
        }

        public Builder setRefreshTimeMilliseconds(@Nullable Integer refreshTimeMilliseconds) {
            this.refreshTimeMillis = refreshTimeMilliseconds;
            return this;
        }

        public Builder setScrollable(@Nullable Boolean scrollable) {
            this.scrollable = scrollable == null ? this.scrollable : scrollable.booleanValue();
            return this;
        }

        public Builder setDspCreativeId(@Nullable String dspCreativeId) {
            this.dspCreativeId = dspCreativeId;
            return this;
        }

        public Builder setResponseBody(@Nullable String responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public Builder setJsonBody(@Nullable JSONObject jsonBody) {
            this.jsonBody = jsonBody;
            return this;
        }

        public Builder setEventDetails(@Nullable EventDetails eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public Builder setCustomEventClassName(@Nullable String customEventClassName) {
            this.customEventClassName = customEventClassName;
            return this;
        }

        public Builder setBrowserAgent(@Nullable MoPub$BrowserAgent browserAgent) {
            this.mBrowserAgent = browserAgent;
            return this;
        }

        public Builder setServerExtras(@Nullable Map<String, String> serverExtras) {
            if (serverExtras == null) {
                this.serverExtras = new TreeMap();
            } else {
                this.serverExtras = new TreeMap(serverExtras);
            }
            return this;
        }

        public AdResponse build() {
            return new AdResponse();
        }
    }

    private AdResponse(@NonNull Builder builder) {
        this.mAdType = builder.adType;
        this.mAdUnitId = builder.adUnitId;
        this.mFullAdType = builder.fullAdType;
        this.mNetworkType = builder.networkType;
        this.mRewardedVideoCurrencyName = builder.rewardedVideoCurrencyName;
        this.mRewardedVideoCurrencyAmount = builder.rewardedVideoCurrencyAmount;
        this.mRewardedCurrencies = builder.rewardedCurrencies;
        this.mRewardedVideoCompletionUrl = builder.rewardedVideoCompletionUrl;
        this.mRewardedDuration = builder.rewardedDuration;
        this.mShouldRewardOnClick = builder.shouldRewardOnClick;
        this.mRedirectUrl = builder.redirectUrl;
        this.mClickTrackingUrl = builder.clickTrackingUrl;
        this.mImpressionTrackingUrl = builder.impressionTrackingUrl;
        this.mFailoverUrl = builder.failoverUrl;
        this.mRequestId = builder.requestId;
        this.mWidth = builder.width;
        this.mHeight = builder.height;
        this.mAdTimeoutDelayMillis = builder.adTimeoutDelayMillis;
        this.mRefreshTimeMillis = builder.refreshTimeMillis;
        this.mDspCreativeId = builder.dspCreativeId;
        this.mScrollable = builder.scrollable;
        this.mResponseBody = builder.responseBody;
        this.mJsonBody = builder.jsonBody;
        this.mEventDetails = builder.eventDetails;
        this.mCustomEventClassName = builder.customEventClassName;
        this.mBrowserAgent = builder.mBrowserAgent;
        this.mServerExtras = builder.serverExtras;
        this.mTimestamp = DateAndTime.now().getTime();
    }

    public boolean hasJson() {
        return this.mJsonBody != null;
    }

    @Nullable
    public JSONObject getJsonBody() {
        return this.mJsonBody;
    }

    @Nullable
    public EventDetails getEventDetails() {
        return this.mEventDetails;
    }

    @Nullable
    public String getStringBody() {
        return this.mResponseBody;
    }

    @Nullable
    public String getAdType() {
        return this.mAdType;
    }

    @Nullable
    public String getFullAdType() {
        return this.mFullAdType;
    }

    @Nullable
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    @Nullable
    public String getNetworkType() {
        return this.mNetworkType;
    }

    @Nullable
    public String getRewardedVideoCurrencyName() {
        return this.mRewardedVideoCurrencyName;
    }

    @Nullable
    public String getRewardedVideoCurrencyAmount() {
        return this.mRewardedVideoCurrencyAmount;
    }

    @Nullable
    public String getRewardedCurrencies() {
        return this.mRewardedCurrencies;
    }

    @Nullable
    public String getRewardedVideoCompletionUrl() {
        return this.mRewardedVideoCompletionUrl;
    }

    @Nullable
    public Integer getRewardedDuration() {
        return this.mRewardedDuration;
    }

    public boolean shouldRewardOnClick() {
        return this.mShouldRewardOnClick;
    }

    @Nullable
    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    @Nullable
    public String getClickTrackingUrl() {
        return this.mClickTrackingUrl;
    }

    @Nullable
    public String getImpressionTrackingUrl() {
        return this.mImpressionTrackingUrl;
    }

    @Nullable
    public String getFailoverUrl() {
        return this.mFailoverUrl;
    }

    @Nullable
    public String getRequestId() {
        return this.mRequestId;
    }

    public boolean isScrollable() {
        return this.mScrollable;
    }

    @Nullable
    public Integer getWidth() {
        return this.mWidth;
    }

    @Nullable
    public Integer getHeight() {
        return this.mHeight;
    }

    @Nullable
    public Integer getAdTimeoutMillis() {
        return this.mAdTimeoutDelayMillis;
    }

    @Nullable
    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    @Nullable
    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    @Nullable
    public String getCustomEventClassName() {
        return this.mCustomEventClassName;
    }

    @Nullable
    public MoPub$BrowserAgent getBrowserAgent() {
        return this.mBrowserAgent;
    }

    @NonNull
    public Map<String, String> getServerExtras() {
        return new TreeMap(this.mServerExtras);
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public Builder toBuilder() {
        return new Builder().setAdType(this.mAdType).setNetworkType(this.mNetworkType).setRewardedVideoCurrencyName(this.mRewardedVideoCurrencyName).setRewardedVideoCurrencyAmount(this.mRewardedVideoCurrencyAmount).setRewardedCurrencies(this.mRewardedCurrencies).setRewardedVideoCompletionUrl(this.mRewardedVideoCompletionUrl).setRewardedDuration(this.mRewardedDuration).setShouldRewardOnClick(this.mShouldRewardOnClick).setRedirectUrl(this.mRedirectUrl).setClickTrackingUrl(this.mClickTrackingUrl).setImpressionTrackingUrl(this.mImpressionTrackingUrl).setFailoverUrl(this.mFailoverUrl).setDimensions(this.mWidth, this.mHeight).setAdTimeoutDelayMilliseconds(this.mAdTimeoutDelayMillis).setRefreshTimeMilliseconds(this.mRefreshTimeMillis).setDspCreativeId(this.mDspCreativeId).setScrollable(Boolean.valueOf(this.mScrollable)).setResponseBody(this.mResponseBody).setJsonBody(this.mJsonBody).setEventDetails(this.mEventDetails).setCustomEventClassName(this.mCustomEventClassName).setBrowserAgent(this.mBrowserAgent).setServerExtras(this.mServerExtras);
    }
}

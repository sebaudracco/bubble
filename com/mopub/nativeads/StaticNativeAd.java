package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Map;

public abstract class StaticNativeAd extends BaseNativeAd implements ImpressionInterface, ClickInterface {
    private static final int DEFAULT_IMPRESSION_MIN_PERCENTAGE_VIEWED = 50;
    private static final int DEFAULT_IMPRESSION_MIN_TIME_VIEWED_MS = 1000;
    static final double MAX_STAR_RATING = 5.0d;
    static final double MIN_STAR_RATING = 0.0d;
    @Nullable
    private String mCallToAction;
    @Nullable
    private String mClickDestinationUrl;
    @NonNull
    private final Map<String, Object> mExtras = new HashMap();
    @Nullable
    private String mIconImageUrl;
    private int mImpressionMinPercentageViewed = 50;
    private int mImpressionMinTimeViewed = 1000;
    private Integer mImpressionMinVisiblePx = null;
    private boolean mImpressionRecorded;
    @Nullable
    private String mMainImageUrl;
    @Nullable
    private String mPrivacyInformationIconClickThroughUrl;
    @Nullable
    private String mPrivacyInformationIconImageUrl;
    @Nullable
    private Double mStarRating;
    @Nullable
    private String mText;
    @Nullable
    private String mTitle;

    @Nullable
    public final String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public final String getText() {
        return this.mText;
    }

    @Nullable
    public final String getMainImageUrl() {
        return this.mMainImageUrl;
    }

    @Nullable
    public final String getIconImageUrl() {
        return this.mIconImageUrl;
    }

    @Nullable
    public final String getCallToAction() {
        return this.mCallToAction;
    }

    @Nullable
    public final Double getStarRating() {
        return this.mStarRating;
    }

    @Nullable
    public final String getPrivacyInformationIconClickThroughUrl() {
        return this.mPrivacyInformationIconClickThroughUrl;
    }

    @Nullable
    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    @Nullable
    public final Object getExtra(@NonNull String key) {
        if (NoThrow.checkNotNull(key, "getExtra key is not allowed to be null")) {
            return this.mExtras.get(key);
        }
        return null;
    }

    @NonNull
    public final Map<String, Object> getExtras() {
        return new HashMap(this.mExtras);
    }

    @Nullable
    public final String getClickDestinationUrl() {
        return this.mClickDestinationUrl;
    }

    public final void setMainImageUrl(@Nullable String mainImageUrl) {
        this.mMainImageUrl = mainImageUrl;
    }

    public final void setIconImageUrl(@Nullable String iconImageUrl) {
        this.mIconImageUrl = iconImageUrl;
    }

    public final void setClickDestinationUrl(@Nullable String clickDestinationUrl) {
        this.mClickDestinationUrl = clickDestinationUrl;
    }

    public final void setCallToAction(@Nullable String callToAction) {
        this.mCallToAction = callToAction;
    }

    public final void setTitle(@Nullable String title) {
        this.mTitle = title;
    }

    public final void setText(@Nullable String text) {
        this.mText = text;
    }

    public final void setStarRating(@Nullable Double starRating) {
        if (starRating == null) {
            this.mStarRating = null;
        } else if (starRating.doubleValue() < 0.0d || starRating.doubleValue() > MAX_STAR_RATING) {
            MoPubLog.m12061d("Ignoring attempt to set invalid star rating (" + starRating + "). Must be between " + 0.0d + " and " + MAX_STAR_RATING + ".");
        } else {
            this.mStarRating = starRating;
        }
    }

    public final void setPrivacyInformationIconClickThroughUrl(@Nullable String privacyInformationIconClickThroughUrl) {
        this.mPrivacyInformationIconClickThroughUrl = privacyInformationIconClickThroughUrl;
    }

    public final void setPrivacyInformationIconImageUrl(@Nullable String privacyInformationIconImageUrl) {
        this.mPrivacyInformationIconImageUrl = privacyInformationIconImageUrl;
    }

    public final void addExtra(@NonNull String key, @Nullable Object value) {
        if (NoThrow.checkNotNull(key, "addExtra key is not allowed to be null")) {
            this.mExtras.put(key, value);
        }
    }

    public final void setImpressionMinTimeViewed(int impressionMinTimeViewed) {
        if (impressionMinTimeViewed > 0) {
            this.mImpressionMinTimeViewed = impressionMinTimeViewed;
        } else {
            MoPubLog.m12061d("Ignoring non-positive impressionMinTimeViewed: " + impressionMinTimeViewed);
        }
    }

    public final void setImpressionMinPercentageViewed(int impressionMinPercentageViewed) {
        if (impressionMinPercentageViewed < 0 || impressionMinPercentageViewed > 100) {
            MoPubLog.m12061d("Ignoring impressionMinTimeViewed that's not a percent [0, 100]: " + impressionMinPercentageViewed);
        } else {
            this.mImpressionMinPercentageViewed = impressionMinPercentageViewed;
        }
    }

    public final void setImpressionMinVisiblePx(@Nullable Integer impressionMinVisiblePx) {
        if (impressionMinVisiblePx == null || impressionMinVisiblePx.intValue() <= 0) {
            MoPubLog.m12061d("Ignoring null or non-positive impressionMinVisiblePx: " + impressionMinVisiblePx);
        } else {
            this.mImpressionMinVisiblePx = impressionMinVisiblePx;
        }
    }

    public void prepare(@NonNull View view) {
    }

    public void clear(@NonNull View view) {
    }

    public void destroy() {
    }

    public void recordImpression(@NonNull View view) {
    }

    public final int getImpressionMinPercentageViewed() {
        return this.mImpressionMinPercentageViewed;
    }

    public final int getImpressionMinTimeViewed() {
        return this.mImpressionMinTimeViewed;
    }

    public final Integer getImpressionMinVisiblePx() {
        return this.mImpressionMinVisiblePx;
    }

    public final boolean isImpressionRecorded() {
        return this.mImpressionRecorded;
    }

    public final void setImpressionRecorded() {
        this.mImpressionRecorded = true;
    }

    public void handleClick(@NonNull View view) {
    }
}

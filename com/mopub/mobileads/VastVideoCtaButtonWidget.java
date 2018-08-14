package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.CtaButtonDrawable;

public class VastVideoCtaButtonWidget extends ImageView {
    @NonNull
    private CtaButtonDrawable mCtaButtonDrawable;
    private boolean mHasClickthroughUrl;
    private boolean mHasCompanionAd;
    private boolean mHasSocialActions = false;
    private boolean mIsVideoComplete;
    private boolean mIsVideoSkippable;
    @NonNull
    private final LayoutParams mLandscapeLayoutParams;
    @NonNull
    private final LayoutParams mPortraitLayoutParams;

    public VastVideoCtaButtonWidget(@NonNull Context context, int videoViewId, boolean hasCompanionAd, boolean hasClickthroughUrl) {
        super(context);
        this.mHasCompanionAd = hasCompanionAd;
        this.mHasClickthroughUrl = hasClickthroughUrl;
        setId((int) Utils.generateUniqueId());
        int width = Dips.dipsToIntPixels(150.0f, context);
        int height = Dips.dipsToIntPixels(38.0f, context);
        int margin = Dips.dipsToIntPixels(16.0f, context);
        this.mCtaButtonDrawable = new CtaButtonDrawable(context);
        setImageDrawable(this.mCtaButtonDrawable);
        this.mLandscapeLayoutParams = new LayoutParams(width, height);
        this.mLandscapeLayoutParams.setMargins(margin, margin, margin, margin);
        this.mLandscapeLayoutParams.addRule(8, videoViewId);
        this.mLandscapeLayoutParams.addRule(7, videoViewId);
        this.mPortraitLayoutParams = new LayoutParams(width, height);
        this.mPortraitLayoutParams.setMargins(margin, margin, margin, margin);
        this.mPortraitLayoutParams.addRule(12);
        this.mPortraitLayoutParams.addRule(11);
        updateLayoutAndVisibility();
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayoutAndVisibility();
    }

    void updateCtaText(@NonNull String customCtaText) {
        this.mCtaButtonDrawable.setCtaText(customCtaText);
    }

    void setHasSocialActions(boolean hasSocialActions) {
        this.mHasSocialActions = hasSocialActions;
    }

    boolean getHasSocialActions() {
        return this.mHasSocialActions;
    }

    void notifyVideoSkippable() {
        this.mIsVideoSkippable = true;
        updateLayoutAndVisibility();
    }

    void notifyVideoComplete() {
        this.mIsVideoSkippable = true;
        this.mIsVideoComplete = true;
        updateLayoutAndVisibility();
    }

    private void updateLayoutAndVisibility() {
        if (!this.mHasClickthroughUrl) {
            setVisibility(8);
        } else if (!this.mIsVideoSkippable) {
            setVisibility(4);
        } else if (this.mIsVideoComplete && this.mHasCompanionAd && !this.mHasSocialActions) {
            setVisibility(8);
        } else {
            switch (getResources().getConfiguration().orientation) {
                case 0:
                    MoPubLog.d("Screen orientation undefined: CTA button widget defaulting to portrait layout");
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
                case 1:
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
                case 2:
                    setLayoutParams(this.mLandscapeLayoutParams);
                    break;
                case 3:
                    MoPubLog.d("Screen orientation is deprecated ORIENTATION_SQUARE: CTA button widget defaulting to portrait layout");
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
                default:
                    MoPubLog.d("Unrecognized screen orientation: CTA button widget defaulting to portrait layout");
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
            }
            setVisibility(0);
        }
    }

    @Deprecated
    @VisibleForTesting
    String getCtaText() {
        return this.mCtaButtonDrawable.getCtaText();
    }

    @Deprecated
    @VisibleForTesting
    boolean hasPortraitLayoutParams() {
        return getLayoutParams().equals(this.mPortraitLayoutParams);
    }

    @Deprecated
    @VisibleForTesting
    boolean hasLandscapeLayoutParams() {
        return getLayoutParams().equals(this.mLandscapeLayoutParams);
    }
}

package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzks;
import com.google.android.gms.internal.ads.zzly;

public final class PublisherAdView extends ViewGroup {
    private final zzly zzut;

    public PublisherAdView(Context context) {
        super(context);
        this.zzut = new zzly(this);
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzut = new zzly(this, attributeSet, true);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzut = new zzly(this, attributeSet, true);
    }

    public final void destroy() {
        this.zzut.destroy();
    }

    public final AdListener getAdListener() {
        return this.zzut.getAdListener();
    }

    public final AdSize getAdSize() {
        return this.zzut.getAdSize();
    }

    public final AdSize[] getAdSizes() {
        return this.zzut.getAdSizes();
    }

    public final String getAdUnitId() {
        return this.zzut.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzut.getAppEventListener();
    }

    public final String getMediationAdapterClassName() {
        return this.zzut.getMediationAdapterClassName();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzut.getOnCustomRenderedAdLoadedListener();
    }

    public final VideoController getVideoController() {
        return this.zzut.getVideoController();
    }

    public final VideoOptions getVideoOptions() {
        return this.zzut.getVideoOptions();
    }

    public final boolean isLoading() {
        return this.zzut.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzut.zza(publisherAdRequest.zzay());
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    protected final void onMeasure(int i, int i2) {
        int widthInPixels;
        int i3 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize;
            AdSize adSize2 = null;
            try {
                adSize = getAdSize();
            } catch (Throwable e) {
                zzane.zzb("Unable to retrieve ad size.", e);
                adSize = adSize2;
            }
            if (adSize != null) {
                Context context = getContext();
                widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
            } else {
                widthInPixels = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            widthInPixels = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(widthInPixels, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public final void pause() {
        this.zzut.pause();
    }

    public final void recordManualImpression() {
        this.zzut.recordManualImpression();
    }

    public final void resume() {
        this.zzut.resume();
    }

    public final void setAdListener(AdListener adListener) {
        this.zzut.setAdListener(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (adSizeArr == null || adSizeArr.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzut.zza(adSizeArr);
    }

    public final void setAdUnitId(String str) {
        this.zzut.setAdUnitId(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zzut.setAppEventListener(appEventListener);
    }

    public final void setCorrelator(Correlator correlator) {
        this.zzut.setCorrelator(correlator);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzut.setManualImpressionsEnabled(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzut.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzut.setVideoOptions(videoOptions);
    }

    public final boolean zza(zzks com_google_android_gms_internal_ads_zzks) {
        return this.zzut.zza(com_google_android_gms_internal_ads_zzks);
    }
}

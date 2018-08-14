package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.CacheService;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.mobileads.VastXmlManagerAggregator.VastXmlManagerAggregatorListener;

public class VastManager implements VastXmlManagerAggregatorListener {
    @Nullable
    private String mDspCreativeId;
    private int mScreenAreaDp;
    private double mScreenAspectRatio;
    private final boolean mShouldPreCacheVideo;
    @Nullable
    private VastManagerListener mVastManagerListener;
    @Nullable
    private VastXmlManagerAggregator mVastXmlManagerAggregator;

    public VastManager(@NonNull Context context, boolean shouldPreCacheVideo) {
        initializeScreenDimensions(context);
        this.mShouldPreCacheVideo = shouldPreCacheVideo;
    }

    public void prepareVastVideoConfiguration(@Nullable String vastXml, @NonNull VastManagerListener vastManagerListener, @Nullable String dspCreativeId, @NonNull Context context) {
        Preconditions.checkNotNull(vastManagerListener, "vastManagerListener cannot be null");
        Preconditions.checkNotNull(context, "context cannot be null");
        if (this.mVastXmlManagerAggregator == null) {
            this.mVastManagerListener = vastManagerListener;
            this.mVastXmlManagerAggregator = new VastXmlManagerAggregator(this, this.mScreenAspectRatio, this.mScreenAreaDp, context.getApplicationContext());
            this.mDspCreativeId = dspCreativeId;
            try {
                AsyncTasks.safeExecuteOnExecutor(this.mVastXmlManagerAggregator, new String[]{vastXml});
            } catch (Exception e) {
                MoPubLog.d("Failed to aggregate vast xml", e);
                this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
            }
        }
    }

    public void cancel() {
        if (this.mVastXmlManagerAggregator != null) {
            this.mVastXmlManagerAggregator.cancel(true);
            this.mVastXmlManagerAggregator = null;
        }
    }

    public void onAggregationComplete(@Nullable VastVideoConfig vastVideoConfig) {
        if (this.mVastManagerListener == null) {
            throw new IllegalStateException("mVastManagerListener cannot be null here. Did you call prepareVastVideoConfiguration()?");
        } else if (vastVideoConfig == null) {
            this.mVastManagerListener.onVastVideoConfigurationPrepared(null);
        } else {
            if (!TextUtils.isEmpty(this.mDspCreativeId)) {
                vastVideoConfig.setDspCreativeId(this.mDspCreativeId);
            }
            if (!this.mShouldPreCacheVideo || updateDiskMediaFileUrl(vastVideoConfig)) {
                this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfig);
                return;
            }
            VideoDownloader.cache(vastVideoConfig.getNetworkMediaFileUrl(), new 1(this, vastVideoConfig));
        }
    }

    private boolean updateDiskMediaFileUrl(@NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        String networkMediaFileUrl = vastVideoConfig.getNetworkMediaFileUrl();
        if (!CacheService.containsKeyDiskCache(networkMediaFileUrl)) {
            return false;
        }
        vastVideoConfig.setDiskMediaFileUrl(CacheService.getFilePathDiskCache(networkMediaFileUrl));
        return true;
    }

    private void initializeScreenDimensions(@NonNull Context context) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int xPx = display.getWidth();
        int yPx = display.getHeight();
        float density = context.getResources().getDisplayMetrics().density;
        if (density <= 0.0f) {
            density = 1.0f;
        }
        int screenWidth = Math.max(xPx, yPx);
        int screenHeight = Math.min(xPx, yPx);
        this.mScreenAspectRatio = ((double) screenWidth) / ((double) screenHeight);
        this.mScreenAreaDp = (int) ((((float) screenWidth) / density) * (((float) screenHeight) / density));
    }

    @Deprecated
    @VisibleForTesting
    int getScreenAreaDp() {
        return this.mScreenAreaDp;
    }

    @Deprecated
    @VisibleForTesting
    double getScreenAspectRatio() {
        return this.mScreenAspectRatio;
    }
}

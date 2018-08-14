package com.mopub.mobileads;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.mopub.common.AdFormat;
import com.mopub.common.AdReport;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ManifestUtils;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import com.mopub.common.util.Visibility;
import com.mopub.mobileads.factories.AdViewControllerFactory;
import java.util.Map;
import java.util.TreeMap;

public class MoPubView extends FrameLayout {
    private static final String CUSTOM_EVENT_BANNER_ADAPTER_FACTORY = "com.mopub.mobileads.factories.CustomEventBannerAdapterFactory";
    @Nullable
    protected AdViewController mAdViewController;
    private BannerAdListener mBannerAdListener;
    private Context mContext;
    protected Object mCustomEventBannerAdapter;
    private BroadcastReceiver mScreenStateReceiver;
    private int mScreenVisibility;

    public MoPubView(Context context) {
        this(context, null);
    }

    public MoPubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ManifestUtils.checkWebViewActivitiesDeclared(context);
        this.mContext = context;
        this.mScreenVisibility = getVisibility();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        this.mAdViewController = AdViewControllerFactory.create(context, this);
        registerScreenStateBroadcastReceiver();
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new 1(this);
        IntentFilter filter = new IntentFilter("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(this.mScreenStateReceiver, filter);
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception e) {
            MoPubLog.d("Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public void loadAd() {
        if (this.mAdViewController != null) {
            this.mAdViewController.loadAd();
        }
    }

    public void destroy() {
        unregisterScreenStateBroadcastReceiver();
        removeAllViews();
        if (this.mAdViewController != null) {
            this.mAdViewController.cleanup();
            this.mAdViewController = null;
        }
        if (this.mCustomEventBannerAdapter != null) {
            invalidateAdapter();
            this.mCustomEventBannerAdapter = null;
        }
    }

    private void invalidateAdapter() {
        if (this.mCustomEventBannerAdapter != null) {
            try {
                new MethodBuilder(this.mCustomEventBannerAdapter, "invalidate").setAccessible().execute();
            } catch (Exception e) {
                MoPubLog.e("Error invalidating adapter", e);
            }
        }
    }

    Integer getAdTimeoutDelay() {
        return this.mAdViewController != null ? this.mAdViewController.getAdTimeoutDelay() : null;
    }

    protected boolean loadFailUrl(@NonNull MoPubErrorCode errorCode) {
        if (this.mAdViewController == null) {
            return false;
        }
        return this.mAdViewController.loadFailUrl(errorCode);
    }

    protected void loadCustomEvent(String customEventClassName, Map<String, String> serverExtras) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(customEventClassName)) {
                MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (this.mCustomEventBannerAdapter != null) {
                invalidateAdapter();
            }
            MoPubLog.d("Loading custom event adapter.");
            if (Reflection.classFound(CUSTOM_EVENT_BANNER_ADAPTER_FACTORY)) {
                try {
                    this.mCustomEventBannerAdapter = new MethodBuilder(null, "create").setStatic(Class.forName(CUSTOM_EVENT_BANNER_ADAPTER_FACTORY)).addParam(MoPubView.class, this).addParam(String.class, customEventClassName).addParam(Map.class, serverExtras).addParam(Long.TYPE, Long.valueOf(this.mAdViewController.getBroadcastIdentifier())).addParam(AdReport.class, this.mAdViewController.getAdReport()).execute();
                    new MethodBuilder(this.mCustomEventBannerAdapter, "loadAd").setAccessible().execute();
                    return;
                } catch (Exception e) {
                    MoPubLog.e("Error loading custom event", e);
                    return;
                }
            }
            MoPubLog.e("Could not load custom event -- missing banner module");
        }
    }

    protected void registerClick() {
        if (this.mAdViewController != null) {
            this.mAdViewController.registerClick();
            adClicked();
        }
    }

    protected void trackNativeImpression() {
        MoPubLog.d("Tracking impression for native adapter.");
        if (this.mAdViewController != null) {
            this.mAdViewController.trackImpression();
        }
    }

    protected void onWindowVisibilityChanged(int visibility) {
        if (Visibility.hasScreenVisibilityChanged(this.mScreenVisibility, visibility)) {
            this.mScreenVisibility = visibility;
            setAdVisibility(this.mScreenVisibility);
        }
    }

    private void setAdVisibility(int visibility) {
        if (this.mAdViewController != null) {
            if (Visibility.isScreenVisible(visibility)) {
                this.mAdViewController.resumeRefresh();
            } else {
                this.mAdViewController.pauseRefresh();
            }
        }
    }

    protected void adLoaded() {
        MoPubLog.d("adLoaded");
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerLoaded(this);
        }
    }

    protected void adFailed(MoPubErrorCode errorCode) {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerFailed(this, errorCode);
        }
    }

    protected void adPresentedOverlay() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerExpanded(this);
        }
    }

    protected void adClosed() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerCollapsed(this);
        }
    }

    protected void adClicked() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerClicked(this);
        }
    }

    protected void nativeAdLoaded() {
        if (this.mAdViewController != null) {
            this.mAdViewController.scheduleRefreshTimerIfEnabled();
        }
        adLoaded();
    }

    public void setAdUnitId(String adUnitId) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setAdUnitId(adUnitId);
        }
    }

    public String getAdUnitId() {
        return this.mAdViewController != null ? this.mAdViewController.getAdUnitId() : null;
    }

    public void setKeywords(String keywords) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setKeywords(keywords);
        }
    }

    public String getKeywords() {
        return this.mAdViewController != null ? this.mAdViewController.getKeywords() : null;
    }

    public void setLocation(Location location) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setLocation(location);
        }
    }

    public Location getLocation() {
        return this.mAdViewController != null ? this.mAdViewController.getLocation() : null;
    }

    public int getAdWidth() {
        return this.mAdViewController != null ? this.mAdViewController.getAdWidth() : 0;
    }

    public int getAdHeight() {
        return this.mAdViewController != null ? this.mAdViewController.getAdHeight() : 0;
    }

    public Activity getActivity() {
        return (Activity) this.mContext;
    }

    public void setBannerAdListener(BannerAdListener listener) {
        this.mBannerAdListener = listener;
    }

    public BannerAdListener getBannerAdListener() {
        return this.mBannerAdListener;
    }

    public void setLocalExtras(Map<String, Object> localExtras) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setLocalExtras(localExtras);
        }
    }

    public Map<String, Object> getLocalExtras() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getLocalExtras();
        }
        return new TreeMap();
    }

    public void setAutorefreshEnabled(boolean enabled) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setShouldAllowAutoRefresh(enabled);
        }
    }

    public boolean getAutorefreshEnabled() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getCurrentAutoRefreshStatus();
        }
        MoPubLog.d("Can't get autorefresh status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void setAdContentView(View view) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setAdContentView(view);
        }
    }

    public void setTesting(boolean testing) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setTesting(testing);
        }
    }

    public boolean getTesting() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getTesting();
        }
        MoPubLog.d("Can't get testing status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void forceRefresh() {
        if (this.mCustomEventBannerAdapter != null) {
            invalidateAdapter();
            this.mCustomEventBannerAdapter = null;
        }
        if (this.mAdViewController != null) {
            this.mAdViewController.forceRefresh();
        }
    }

    AdViewController getAdViewController() {
        return this.mAdViewController;
    }

    public AdFormat getAdFormat() {
        return AdFormat.BANNER;
    }

    @Deprecated
    public void setTimeout(int milliseconds) {
    }

    @Deprecated
    public String getResponseString() {
        return null;
    }

    @Deprecated
    public String getClickTrackingUrl() {
        return null;
    }
}

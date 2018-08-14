package com.mopub.mobileads;

import android.app.Activity;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appsgeyser.sdk.configuration.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.AdTypeTranslator.CustomEventType;
import com.mopub.mobileads.CustomEventInterstitialAdapter.CustomEventInterstitialAdapterListener;
import java.util.Map;

public class MoPubInterstitial implements CustomEventInterstitialAdapterListener {
    @NonNull
    private Activity mActivity;
    @NonNull
    private final Runnable mAdExpiration;
    @NonNull
    private volatile InterstitialState mCurrentInterstitialState;
    @Nullable
    private CustomEventInterstitialAdapter mCustomEventInterstitialAdapter;
    @NonNull
    private Handler mHandler;
    @Nullable
    private InterstitialAdListener mInterstitialAdListener;
    @NonNull
    private MoPubInterstitialView mInterstitialView = new MoPubInterstitialView(this, this.mActivity);

    public MoPubInterstitial(@NonNull Activity activity, @NonNull String adUnitId) {
        this.mActivity = activity;
        this.mInterstitialView.setAdUnitId(adUnitId);
        this.mCurrentInterstitialState = InterstitialState.IDLE;
        this.mHandler = new Handler();
        this.mAdExpiration = new 1(this);
    }

    private boolean attemptStateTransition(@NonNull InterstitialState endState) {
        return attemptStateTransition(endState, false);
    }

    @VisibleForTesting
    synchronized boolean attemptStateTransition(@NonNull InterstitialState endState, boolean force) {
        boolean z = true;
        synchronized (this) {
            Preconditions.checkNotNull(endState);
            switch (2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[this.mCurrentInterstitialState.ordinal()]) {
                case 1:
                    switch (2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[endState.ordinal()]) {
                        case 1:
                            if (!force) {
                                MoPubLog.d("Already loading an interstitial.");
                            }
                            z = false;
                            break;
                        case 2:
                            MoPubLog.d("Interstitial is not ready to be shown yet.");
                            z = false;
                            break;
                        case 3:
                            setInterstitialStateDestroyed();
                            break;
                        case 4:
                            invalidateInterstitialAdapter();
                            this.mCurrentInterstitialState = InterstitialState.IDLE;
                            break;
                        case 5:
                            this.mCurrentInterstitialState = InterstitialState.READY;
                            if (CustomEventType.isMoPubSpecific(this.mInterstitialView.getCustomEventClassName())) {
                                this.mHandler.postDelayed(this.mAdExpiration, Constants.PULL_ALARM_PERIOD);
                                break;
                            }
                            break;
                        default:
                            z = false;
                            break;
                    }
                case 2:
                    switch (2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[endState.ordinal()]) {
                        case 1:
                            if (!force) {
                                MoPubLog.d("Interstitial already showing. Not loading another.");
                            }
                            z = false;
                            break;
                        case 2:
                            MoPubLog.d("Already showing an interstitial. Cannot show it again.");
                            z = false;
                            break;
                        case 3:
                            setInterstitialStateDestroyed();
                            break;
                        case 4:
                            if (!force) {
                                invalidateInterstitialAdapter();
                                this.mCurrentInterstitialState = InterstitialState.IDLE;
                                break;
                            }
                            MoPubLog.d("Cannot force refresh while showing an interstitial.");
                            z = false;
                            break;
                        default:
                            z = false;
                            break;
                    }
                case 3:
                    MoPubLog.d("MoPubInterstitial destroyed. Ignoring all requests.");
                    z = false;
                    break;
                case 4:
                    switch (2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[endState.ordinal()]) {
                        case 1:
                            invalidateInterstitialAdapter();
                            this.mCurrentInterstitialState = InterstitialState.LOADING;
                            if (!force) {
                                this.mInterstitialView.loadAd();
                                break;
                            }
                            this.mInterstitialView.forceRefresh();
                            break;
                        case 2:
                            MoPubLog.d("No interstitial loading or loaded.");
                            z = false;
                            break;
                        case 3:
                            setInterstitialStateDestroyed();
                            break;
                        default:
                            z = false;
                            break;
                    }
                case 5:
                    switch (2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[endState.ordinal()]) {
                        case 1:
                            MoPubLog.d("Interstitial already loaded. Not loading another.");
                            if (this.mInterstitialAdListener != null) {
                                this.mInterstitialAdListener.onInterstitialLoaded(this);
                            }
                            z = false;
                            break;
                        case 2:
                            showCustomEventInterstitial();
                            this.mCurrentInterstitialState = InterstitialState.SHOWING;
                            this.mHandler.removeCallbacks(this.mAdExpiration);
                            break;
                        case 3:
                            setInterstitialStateDestroyed();
                            break;
                        case 4:
                            if (!force) {
                                z = false;
                                break;
                            }
                            invalidateInterstitialAdapter();
                            this.mCurrentInterstitialState = InterstitialState.IDLE;
                            break;
                        default:
                            z = false;
                            break;
                    }
                default:
                    z = false;
                    break;
            }
        }
        return z;
    }

    private void setInterstitialStateDestroyed() {
        invalidateInterstitialAdapter();
        this.mInterstitialView.setBannerAdListener(null);
        this.mInterstitialView.destroy();
        this.mHandler.removeCallbacks(this.mAdExpiration);
        this.mCurrentInterstitialState = InterstitialState.DESTROYED;
    }

    public void load() {
        attemptStateTransition(InterstitialState.LOADING);
    }

    public boolean show() {
        return attemptStateTransition(InterstitialState.SHOWING);
    }

    public void forceRefresh() {
        attemptStateTransition(InterstitialState.IDLE, true);
        attemptStateTransition(InterstitialState.LOADING, true);
    }

    public boolean isReady() {
        return this.mCurrentInterstitialState == InterstitialState.READY;
    }

    boolean isDestroyed() {
        return this.mCurrentInterstitialState == InterstitialState.DESTROYED;
    }

    Integer getAdTimeoutDelay() {
        return this.mInterstitialView.getAdTimeoutDelay();
    }

    @NonNull
    MoPubInterstitialView getMoPubInterstitialView() {
        return this.mInterstitialView;
    }

    private void showCustomEventInterstitial() {
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.showInterstitial();
        }
    }

    private void invalidateInterstitialAdapter() {
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
    }

    public void setKeywords(@Nullable String keywords) {
        this.mInterstitialView.setKeywords(keywords);
    }

    @Nullable
    public String getKeywords() {
        return this.mInterstitialView.getKeywords();
    }

    @NonNull
    public Activity getActivity() {
        return this.mActivity;
    }

    @Nullable
    public Location getLocation() {
        return this.mInterstitialView.getLocation();
    }

    public void destroy() {
        attemptStateTransition(InterstitialState.DESTROYED);
    }

    public void setInterstitialAdListener(@Nullable InterstitialAdListener listener) {
        this.mInterstitialAdListener = listener;
    }

    @Nullable
    public InterstitialAdListener getInterstitialAdListener() {
        return this.mInterstitialAdListener;
    }

    public void setTesting(boolean testing) {
        this.mInterstitialView.setTesting(testing);
    }

    public boolean getTesting() {
        return this.mInterstitialView.getTesting();
    }

    public void setLocalExtras(Map<String, Object> extras) {
        this.mInterstitialView.setLocalExtras(extras);
    }

    @NonNull
    public Map<String, Object> getLocalExtras() {
        return this.mInterstitialView.getLocalExtras();
    }

    public void onCustomEventInterstitialLoaded() {
        if (!isDestroyed()) {
            attemptStateTransition(InterstitialState.READY);
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialLoaded(this);
            }
        }
    }

    public void onCustomEventInterstitialFailed(@NonNull MoPubErrorCode errorCode) {
        if (!isDestroyed() && !this.mInterstitialView.loadFailUrl(errorCode)) {
            attemptStateTransition(InterstitialState.IDLE);
        }
    }

    public void onCustomEventInterstitialShown() {
        if (!isDestroyed()) {
            this.mInterstitialView.trackImpression();
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialShown(this);
            }
        }
    }

    public void onCustomEventInterstitialClicked() {
        if (!isDestroyed()) {
            this.mInterstitialView.registerClick();
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialClicked(this);
            }
        }
    }

    public void onCustomEventInterstitialDismissed() {
        if (!isDestroyed()) {
            attemptStateTransition(InterstitialState.IDLE);
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialDismissed(this);
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    void setHandler(@NonNull Handler handler) {
        this.mHandler = handler;
    }

    @Deprecated
    @VisibleForTesting
    void setInterstitialView(@NonNull MoPubInterstitialView interstitialView) {
        this.mInterstitialView = interstitialView;
    }

    @Deprecated
    @VisibleForTesting
    void setCurrentInterstitialState(@NonNull InterstitialState interstitialState) {
        this.mCurrentInterstitialState = interstitialState;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    InterstitialState getCurrentInterstitialState() {
        return this.mCurrentInterstitialState;
    }

    @Deprecated
    @VisibleForTesting
    void setCustomEventInterstitialAdapter(@NonNull CustomEventInterstitialAdapter customEventInterstitialAdapter) {
        this.mCustomEventInterstitialAdapter = customEventInterstitialAdapter;
    }
}

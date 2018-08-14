package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.AdUnit.C2908e;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMobiBanner extends RelativeLayout {
    private static final String DEBUG_LOG_TAG = "InMobi";
    private static final String TAG = InMobiBanner.class.getSimpleName();
    private static ConcurrentHashMap<C3073n, WeakReference<BannerAdRequestListener>> prefetchAdUnitMap = new ConcurrentHashMap(5, 0.9f, 3);
    private WeakReference<Activity> mActivityRef;
    private long mAdLoadCalledTimestamp = 0;
    private AnimationType mAnimationType = AnimationType.ROTATE_HORIZONTAL_AXIS;
    private C3073n mBackgroundBannerAdUnit;
    private final C2905b mBannerAdListener = new C29204(this);
    private C3073n mBannerAdUnit1;
    private C3073n mBannerAdUnit2;
    private int mBannerHeightInDp = 0;
    private int mBannerWidthInDp = 0;
    private C2921b mClientCallbackHandler;
    private BannerAdListener mClientListener;
    private C3073n mForegroundBannerAdUnit;
    private boolean mIsAutoRefreshEnabled = true;
    private boolean mIsInitialized = false;
    private C3074o mRefreshHandler;
    private int mRefreshInterval;

    static class C29162 implements C2908e {
        C29162() {
        }

        public void mo6116a(@NonNull AdUnit adUnit) {
            if (adUnit != null) {
                try {
                    WeakReference weakReference = (WeakReference) InMobiBanner.prefetchAdUnitMap.get(adUnit);
                    if (weakReference != null) {
                        InMobiBanner.prefetchAdUnitMap.remove(adUnit);
                        BannerAdRequestListener bannerAdRequestListener = (BannerAdRequestListener) weakReference.get();
                        if (bannerAdRequestListener != null) {
                            InMobiBanner inMobiBanner = new InMobiBanner(adUnit.m8711a(), adUnit.m8736b());
                            inMobiBanner.setExtras(adUnit.m8785z());
                            inMobiBanner.setKeywords(adUnit.m8784y());
                            inMobiBanner.setMonetizationContext(adUnit.mo6259x());
                            bannerAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiBanner);
                        }
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "SDK encountered unexpected error in onAdPrefetchSucceeded " + e.getMessage());
                }
            }
        }

        public void mo6117a(@NonNull AdUnit adUnit, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
            if (adUnit != null) {
                try {
                    WeakReference weakReference = (WeakReference) InMobiBanner.prefetchAdUnitMap.get(adUnit);
                    if (weakReference != null) {
                        InMobiBanner.prefetchAdUnitMap.remove(adUnit);
                        BannerAdRequestListener bannerAdRequestListener = (BannerAdRequestListener) weakReference.get();
                        if (bannerAdRequestListener != null) {
                            bannerAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                        }
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "SDK encountered unexpected error in onAdPrefetchFailed " + e.getMessage());
                }
            }
        }
    }

    class C29173 implements OnGlobalLayoutListener {
        final /* synthetic */ InMobiBanner f6745a;

        C29173(InMobiBanner inMobiBanner) {
            this.f6745a = inMobiBanner;
        }

        public void onGlobalLayout() {
            try {
                this.f6745a.mBannerWidthInDp = DisplayInfo.m10422b(this.f6745a.getMeasuredWidth());
                this.f6745a.mBannerHeightInDp = DisplayInfo.m10422b(this.f6745a.getMeasuredHeight());
                if (!this.f6745a.hasValidSize()) {
                    return;
                }
                if (VERSION.SDK_INT >= 16) {
                    this.f6745a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    this.f6745a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "InMobiBanner$1.onGlobalLayout() handler threw unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "InMobiBanner$1.onGlobalLayout() handler threw unexpected error: " + e.getMessage());
            }
        }
    }

    private interface C2918a {
        void mo6118a();
    }

    class C29204 implements C2905b {
        final /* synthetic */ InMobiBanner f6747a;

        class C29191 implements C2918a {
            final /* synthetic */ C29204 f6746a;

            C29191(C29204 c29204) {
                this.f6746a = c29204;
            }

            public void mo6118a() {
                try {
                    this.f6746a.f6747a.mClientCallbackHandler.sendEmptyMessage(1);
                    this.f6746a.f6747a.scheduleRefresh();
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Encountered unexpected error in scheduling refresh for banner ad");
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "InMobiBanner$5.onSuccess() handler threw unexpected error: " + e.getMessage());
                }
            }
        }

        C29204(InMobiBanner inMobiBanner) {
            this.f6747a = inMobiBanner;
        }

        public void mo6122a(AdUnit adUnit, boolean z) {
        }

        public void mo6120a(AdUnit adUnit) {
        }

        public void mo6119a() {
            try {
                if (this.f6747a.mForegroundBannerAdUnit == null || !this.f6747a.mForegroundBannerAdUnit.mo6144U()) {
                    this.f6747a.swapAdUnitsAndDisplayAd(new C29191(this));
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Encountered unexpected error in loading banner ad");
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "InMobiBanner$2.onAdLoadSucceeded() handler threw unexpected error: " + e.getMessage());
            }
        }

        public void mo6121a(AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
            try {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = inMobiAdRequestStatus;
                this.f6747a.mClientCallbackHandler.sendMessage(obtain);
                this.f6747a.scheduleRefresh();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Encountered unexpected error in loading banner ad");
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "InMobiBanner$2.onAdLoadFailed() handler threw unexpected error: " + e.getMessage());
            }
        }

        public void mo6124b() {
        }

        public void mo6126c() {
        }

        public void mo6127d() {
            this.f6747a.mClientCallbackHandler.sendEmptyMessage(3);
        }

        public void mo6128e() {
            try {
                this.f6747a.scheduleRefresh();
                this.f6747a.mClientCallbackHandler.sendEmptyMessage(4);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Encountered unexpected error in closing banner ad");
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "InMobiBanner$2.onAdDismissed() handler threw unexpected error: " + e.getMessage());
            }
        }

        public void mo6123a(@NonNull Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = map;
            this.f6747a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void mo6129f() {
            this.f6747a.mClientCallbackHandler.sendEmptyMessage(6);
        }

        public void mo6125b(@NonNull Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = map;
            this.f6747a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void mo6130g() {
        }
    }

    public enum AnimationType {
        ANIMATION_OFF,
        ROTATE_HORIZONTAL_AXIS,
        ANIMATION_ALPHA,
        ROTATE_VERTICAL_AXIS
    }

    public interface BannerAdListener {
        void onAdDismissed(InMobiBanner inMobiBanner);

        void onAdDisplayed(InMobiBanner inMobiBanner);

        void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiBanner inMobiBanner);

        void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onUserLeftApplication(InMobiBanner inMobiBanner);
    }

    public interface BannerAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiBanner inMobiBanner);
    }

    private static final class C2921b extends Handler {
        private WeakReference<BannerAdListener> f6748a = new WeakReference(null);
        private WeakReference<InMobiBanner> f6749b;

        public C2921b(InMobiBanner inMobiBanner) {
            super(Looper.getMainLooper());
            this.f6749b = new WeakReference(inMobiBanner);
        }

        public void m8804a(BannerAdListener bannerAdListener) {
            this.f6748a = new WeakReference(bannerAdListener);
        }

        public void handleMessage(Message message) {
            Map map = null;
            InMobiBanner inMobiBanner = (InMobiBanner) this.f6749b.get();
            BannerAdListener bannerAdListener = (BannerAdListener) this.f6748a.get();
            if (inMobiBanner != null && bannerAdListener != null) {
                switch (message.what) {
                    case 1:
                        try {
                            bannerAdListener.onAdLoadSucceeded(inMobiBanner);
                            return;
                        } catch (Exception e) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onAdLoadSucceeded callback threw unexpected error: " + e.getMessage());
                            return;
                        }
                    case 2:
                        try {
                            bannerAdListener.onAdLoadFailed(inMobiBanner, (InMobiAdRequestStatus) message.obj);
                            return;
                        } catch (Exception e2) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onAdLoadFailed callback threw unexpected error: " + e2.getMessage());
                            return;
                        }
                    case 3:
                        try {
                            bannerAdListener.onAdDisplayed(inMobiBanner);
                            return;
                        } catch (Exception e22) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onAdDisplayed callback threw unexpected error: " + e22.getMessage());
                            return;
                        }
                    case 4:
                        try {
                            bannerAdListener.onAdDismissed(inMobiBanner);
                            return;
                        } catch (Exception e222) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onAdDismissed callback threw unexpected error: " + e222.getMessage());
                            return;
                        }
                    case 5:
                        try {
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            bannerAdListener.onAdInteraction(inMobiBanner, map);
                            return;
                        } catch (Exception e2222) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onAdInteraction callback threw unexpected error: " + e2222.getMessage());
                            return;
                        }
                    case 6:
                        try {
                            bannerAdListener.onUserLeftApplication(inMobiBanner);
                            return;
                        } catch (Exception e22222) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onUserLeftApplication callback threw unexpected error: " + e22222.getMessage());
                            return;
                        }
                    case 7:
                        try {
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            bannerAdListener.onAdRewardActionCompleted(inMobiBanner, map);
                            return;
                        } catch (Exception e222222) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "onUserRewardActionCompleted callback threw unexpected error: " + e222222.getMessage());
                            return;
                        }
                    default:
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                        return;
                }
            }
        }
    }

    public InMobiBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (C3105a.m10076a()) {
            String str = "http://schemas.android.com/apk/lib/com.inmobi.ads";
            this.mClientCallbackHandler = new C2921b(this);
            str = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "placementId");
            String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
            if (str != null) {
                try {
                    initializeAdUnit(context, Long.parseLong(str.trim()));
                } catch (NumberFormatException e) {
                    Logger.m10359a(InternalLogLevel.ERROR, TAG, "Placement id value supplied in XML layout is not valid. Banner creation failed.");
                }
            } else {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Placement id value is not supplied in XML layout. Banner creation failed.");
            }
            if (attributeValue != null) {
                try {
                    setRefreshInterval(Integer.parseInt(attributeValue.trim()));
                    return;
                } catch (NumberFormatException e2) {
                    Logger.m10359a(InternalLogLevel.ERROR, TAG, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.");
                    return;
                }
            }
            return;
        }
        Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Banner ad");
    }

    public InMobiBanner(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Banner ad");
        } else if (activity == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to create Banner ad with null Activity object.");
        } else {
            this.mActivityRef = new WeakReference(activity);
            String str = "http://schemas.android.com/apk/lib/com.inmobi.ads";
            this.mClientCallbackHandler = new C2921b(this);
            str = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "placementId");
            String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
            if (str != null) {
                try {
                    initializeAdUnit(activity, Long.parseLong(str.trim()));
                } catch (NumberFormatException e) {
                    Logger.m10359a(InternalLogLevel.ERROR, TAG, "Placement id value supplied in XML layout is not valid. Banner creation failed.");
                }
            } else {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Placement id value is not supplied in XML layout. Banner creation failed.");
            }
            if (attributeValue != null) {
                try {
                    setRefreshInterval(Integer.parseInt(attributeValue.trim()));
                } catch (NumberFormatException e2) {
                    Logger.m10359a(InternalLogLevel.ERROR, TAG, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.");
                }
            }
        }
    }

    public InMobiBanner(Context context, long j) {
        super(context);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Banner ad");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to create InMobiBanner ad with null context object.");
        } else {
            this.mClientCallbackHandler = new C2921b(this);
            initializeAdUnit(context, j);
        }
    }

    public InMobiBanner(Activity activity, long j) {
        super(activity);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Banner ad");
        } else if (activity == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to create Banner ad with null Activity object.");
        } else {
            this.mActivityRef = new WeakReference(activity);
            this.mClientCallbackHandler = new C2921b(this);
            initializeAdUnit(activity, j);
        }
    }

    public void load() {
        load(false);
    }

    void load(final boolean z) {
        try {
            if (!C3105a.m10076a()) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
            } else if (!this.mIsInitialized) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
            } else if (this.mForegroundBannerAdUnit == null || !this.mForegroundBannerAdUnit.mo6144U()) {
                if (!hasValidSize()) {
                    if (getLayoutParams() == null) {
                        Logger.m10359a(InternalLogLevel.ERROR, TAG, "The layout params of the banner must be set before calling load");
                        Logger.m10359a(InternalLogLevel.ERROR, TAG, "or call setBannerSize(int widthInDp, int heightInDp) before load");
                        this.mBannerAdListener.mo6121a(this.mBackgroundBannerAdUnit, new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
                        return;
                    } else if (getLayoutParams().width == -2 || getLayoutParams().height == -2) {
                        Logger.m10359a(InternalLogLevel.ERROR, TAG, "The height or width of a Banner ad can't be WRAP_CONTENT");
                        Logger.m10359a(InternalLogLevel.ERROR, TAG, "or call setBannerSize(int widthInDp, int heightInDp) before load");
                        this.mBannerAdListener.mo6121a(this.mBackgroundBannerAdUnit, new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
                        return;
                    } else {
                        setSizeFromLayoutParams();
                    }
                }
                if (hasValidSize()) {
                    cancelScheduledRefresh();
                    if (checkForRefreshRate()) {
                        this.mBackgroundBannerAdUnit.mo6165g(getFrameSizeString());
                        this.mBackgroundBannerAdUnit.m9931b(z);
                        return;
                    }
                    return;
                }
                new Handler().postDelayed(new Runnable(this) {
                    final /* synthetic */ InMobiBanner f6744b;

                    public void run() {
                        try {
                            if (this.f6744b.hasValidSize()) {
                                this.f6744b.cancelScheduledRefresh();
                                if (this.f6744b.checkForRefreshRate()) {
                                    this.f6744b.mBackgroundBannerAdUnit.mo6165g(this.f6744b.getFrameSizeString());
                                    this.f6744b.mBackgroundBannerAdUnit.m9931b(z);
                                    return;
                                }
                                return;
                            }
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "The height or width of the banner can not be determined");
                            this.f6744b.mBannerAdListener.mo6121a(this.f6744b.mBackgroundBannerAdUnit, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                        } catch (Exception e) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiBanner.TAG, "SDK encountered unexpected error while loading an ad");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiBanner.TAG, "InMobiBanner$4.run() threw unexpected error: " + e.getMessage());
                        }
                    }
                }, 200);
            } else {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
                this.mClientCallbackHandler.sendMessage(obtain);
                this.mForegroundBannerAdUnit.m8752c("AdActive");
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to load ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Load failed with unexpected error: " + e.getMessage());
        }
    }

    public void load(@NonNull Context context) {
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Context is null, InMobiBanner cannot be loaded.");
        } else if (this.mIsInitialized) {
            this.mBannerAdUnit1.mo6146a(context);
            this.mBannerAdUnit2.mo6146a(context);
            if (context instanceof Activity) {
                this.mActivityRef = new WeakReference((Activity) context);
                setMonetizationContext(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
            } else {
                setMonetizationContext(MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            }
            load();
        } else {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
        }
    }

    private void setMonetizationContext(MonetizationContext monetizationContext) {
        if (this.mBannerAdUnit1 != null && this.mBannerAdUnit2 != null) {
            this.mBannerAdUnit1.mo6255a(monetizationContext);
            this.mBannerAdUnit2.mo6255a(monetizationContext);
        }
    }

    public static void requestAd(@NonNull Context context, @NonNull InMobiAdRequest inMobiAdRequest, @NonNull BannerAdRequestListener bannerAdRequestListener) {
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null Context. Aborting request");
        } else if (inMobiAdRequest == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non  null InMobiAdRequest. Ignoring request");
        } else if (bannerAdRequestListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null BannerAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest.getWidth() <= 0 && inMobiAdRequest.getHeight() <= 0) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please provide positive width and height for banner. Ignoring request");
        } else if (inMobiAdRequest.getMonetizationContext() == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a MonetizationContext type. Ignoring request");
        } else {
            C2908e c29162 = new C29162();
            try {
                C3073n c3073n = new C3073n(context.getApplicationContext(), inMobiAdRequest.getPlacementId(), null);
                c3073n.m8733a(inMobiAdRequest.getExtras());
                c3073n.mo6255a(inMobiAdRequest.getMonetizationContext());
                c3073n.m8730a(inMobiAdRequest.getKeywords());
                c3073n.mo6165g(inMobiAdRequest.getWidth() + "x" + inMobiAdRequest.getHeight());
                c3073n.m8722a(c29162);
                prefetchAdUnitMap.put(c3073n, new WeakReference(bannerAdRequestListener));
                c3073n.mo6250B();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in requestAd" + e.getMessage());
            }
        }
    }

    private final boolean checkForRefreshRate() {
        if (this.mAdLoadCalledTimestamp != 0) {
            int g = this.mBackgroundBannerAdUnit.m8775p().m9722g();
            if (SystemClock.elapsedRealtime() - this.mAdLoadCalledTimestamp < ((long) (g * 1000))) {
                this.mBackgroundBannerAdUnit.m8724a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage("Ad cannot be refreshed before " + g + " seconds"), false);
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Ad cannot be refreshed before " + g + " seconds (Placement Id = " + this.mBackgroundBannerAdUnit.m8736b() + ")");
                return false;
            }
        }
        this.mAdLoadCalledTimestamp = SystemClock.elapsedRealtime();
        return true;
    }

    public void setExtras(Map<String, String> map) {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.m8733a((Map) map);
            this.mBannerAdUnit2.m8733a((Map) map);
        }
    }

    public void setKeywords(String str) {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.m8730a(str);
            this.mBannerAdUnit2.m8730a(str);
        }
    }

    public void setListener(BannerAdListener bannerAdListener) {
        if (bannerAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please pass a non-null listener to the banner.");
            return;
        }
        this.mClientListener = bannerAdListener;
        if (this.mClientCallbackHandler != null) {
            this.mClientCallbackHandler.m8804a(bannerAdListener);
        }
    }

    public void setEnableAutoRefresh(boolean z) {
        try {
            if (this.mIsInitialized && this.mIsAutoRefreshEnabled != z) {
                this.mIsAutoRefreshEnabled = z;
                if (this.mIsAutoRefreshEnabled) {
                    scheduleRefresh();
                } else {
                    cancelScheduledRefresh();
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to setup auto-refresh on the ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Setting up auto-refresh failed with unexpected error: " + e.getMessage());
        }
    }

    public void setRefreshInterval(int i) {
        try {
            if (this.mIsInitialized) {
                if (i < this.mBackgroundBannerAdUnit.m8775p().m9722g()) {
                    i = this.mBackgroundBannerAdUnit.m8775p().m9722g();
                }
                this.mRefreshInterval = i;
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to set refresh interval for the ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Setting refresh interval failed with unexpected error: " + e.getMessage());
        }
    }

    public void setAnimationType(AnimationType animationType) {
        if (this.mIsInitialized) {
            this.mAnimationType = animationType;
        }
    }

    public void disableHardwareAcceleration() {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.mo6142S();
            this.mBannerAdUnit2.mo6142S();
        }
    }

    protected void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            if (this.mIsInitialized) {
                setSizeFromLayoutParams();
                if (!hasValidSize()) {
                    setupBannerSizeObserver();
                }
                scheduleRefresh();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner#onAttachedToWindow() handler threw unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "InMobiBanner#onAttachedToWindow() handler threw unexpected error: " + e.getMessage());
        }
    }

    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            if (this.mIsInitialized) {
                cancelScheduledRefresh();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner.onDetachedFromWindow() handler threw unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "InMobiBanner.onDetachedFromWindow() handler threw unexpected error: " + e.getMessage());
        }
    }

    private void setSizeFromLayoutParams() {
        if (getLayoutParams() != null) {
            this.mBannerWidthInDp = DisplayInfo.m10422b(getLayoutParams().width);
            this.mBannerHeightInDp = DisplayInfo.m10422b(getLayoutParams().height);
        }
    }

    public void setBannerSize(int i, int i2) {
        if (this.mIsInitialized) {
            this.mBannerWidthInDp = i;
            this.mBannerHeightInDp = i2;
        }
    }

    @TargetApi(16)
    void setupBannerSizeObserver() {
        getViewTreeObserver().addOnGlobalLayoutListener(new C29173(this));
    }

    boolean hasValidSize() {
        return this.mBannerWidthInDp > 0 && this.mBannerHeightInDp > 0;
    }

    String getFrameSizeString() {
        return this.mBannerWidthInDp + "x" + this.mBannerHeightInDp;
    }

    protected void onVisibilityChanged(View view, int i) {
        try {
            super.onVisibilityChanged(view, i);
            if (!this.mIsInitialized) {
                return;
            }
            if (i == 0) {
                scheduleRefresh();
            } else {
                cancelScheduledRefresh();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner$1.onVisibilityChanged() handler threw unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "InMobiBanner$1.onVisibilityChanged() handler threw unexpected error: " + e.getMessage());
        }
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
            if (!this.mIsInitialized) {
                return;
            }
            if (z) {
                scheduleRefresh();
            } else {
                cancelScheduledRefresh();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error: " + e.getMessage());
        }
    }

    private void initializeAdUnit(Context context, long j) {
        this.mBannerAdUnit1 = new C3073n(context, j, this.mBannerAdListener);
        this.mBannerAdUnit2 = new C3073n(context, j, this.mBannerAdListener);
        this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        this.mRefreshInterval = this.mBackgroundBannerAdUnit.m8775p().m9723h();
        this.mRefreshHandler = new C3074o(this);
        setMonetizationContext(MonetizationContext.MONETIZATION_CONTEXT_OTHER);
        this.mIsInitialized = true;
    }

    private void initializeAdUnit(Activity activity, long j) {
        this.mBannerAdUnit1 = new C3073n(activity, j, this.mBannerAdListener);
        this.mBannerAdUnit2 = new C3073n(activity, j, this.mBannerAdListener);
        this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        this.mRefreshInterval = this.mBackgroundBannerAdUnit.m8775p().m9723h();
        this.mRefreshHandler = new C3074o(this);
        setMonetizationContext(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        this.mIsInitialized = true;
    }

    private void scheduleRefresh() {
        if (isShown() && hasWindowFocus()) {
            this.mRefreshHandler.removeMessages(1);
            if (this.mBackgroundBannerAdUnit.m8748c() == AdState.STATE_LOADING || this.mBackgroundBannerAdUnit.m8748c() == AdState.STATE_AVAILABLE || (this.mForegroundBannerAdUnit != null && this.mForegroundBannerAdUnit.m8748c() == AdState.STATE_ACTIVE)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Ignoring an attempt to schedule refresh when an ad is already loading or active.");
            } else if (this.mIsAutoRefreshEnabled) {
                this.mRefreshHandler.sendEmptyMessageDelayed(1, (long) (this.mRefreshInterval * 1000));
            }
        }
    }

    private void cancelScheduledRefresh() {
        this.mRefreshHandler.removeMessages(1);
    }

    private void displayAd() {
        if (getContext() != null) {
            RenderView renderView = (RenderView) this.mForegroundBannerAdUnit.mo6167t();
            if (renderView != null) {
                ViewableAd viewableAd = renderView.getViewableAd();
                if (this.mForegroundBannerAdUnit.mo6143T()) {
                    renderView.mo6173a();
                }
                ViewGroup viewGroup = (ViewGroup) renderView.getParent();
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                View a = viewableAd.mo6225a();
                viewableAd.mo6228a(this.mForegroundBannerAdUnit.m8775p().m9728m(), new View[0]);
                if (this.mBackgroundBannerAdUnit != null) {
                    this.mBackgroundBannerAdUnit.mo6145V();
                }
                if (viewGroup == null) {
                    addView(a, layoutParams);
                } else {
                    viewGroup.removeAllViews();
                    viewGroup.addView(a, layoutParams);
                }
                this.mBackgroundBannerAdUnit.mo6141J();
            }
        }
    }

    private void swapAdUnitsAndDisplayAd(C2918a c2918a) {
        if (this.mForegroundBannerAdUnit == null) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit1;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit2;
        } else if (this.mForegroundBannerAdUnit.equals(this.mBannerAdUnit1)) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit2;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        } else if (this.mForegroundBannerAdUnit.equals(this.mBannerAdUnit2)) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit1;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit2;
        }
        try {
            Animation a = C3066k.m9878a(this.mAnimationType, (float) getWidth(), (float) getHeight());
            displayAd();
            if (a != null) {
                startAnimation(a);
            }
            c2918a.mo6118a();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unexpected error while displaying Banner Ad.");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Unexpected error while displaying Banner Ad : " + e.getMessage());
        }
    }

    public final void resume() {
        try {
            if (this.mForegroundBannerAdUnit != null && this.mActivityRef == null) {
                this.mForegroundBannerAdUnit.m9927W();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not resume ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in resuming ad; " + e.getMessage());
        }
    }

    public final void pause() {
        try {
            if (this.mForegroundBannerAdUnit != null && this.mActivityRef == null) {
                this.mForegroundBannerAdUnit.mo6145V();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not pause ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in pausing ad; " + e.getMessage());
        }
    }
}

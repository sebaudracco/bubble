package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.AdUnit.C2908e;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.ads.ab.C2927a;
import com.inmobi.ads.ab.C2974b;
import com.inmobi.ads.ai.C2898b;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMobiNativeStrand {
    private static final String DEBUG_LOG_TAG = "InMobi";
    private static final String TAG = InMobiNativeStrand.class.getSimpleName();
    private static ConcurrentHashMap<AdUnit, WeakReference<NativeStrandAdRequestListener>> prefetchAdUnitMap = new ConcurrentHashMap(8, 0.9f, 3);
    private WeakReference<Activity> mActivityRef;
    private C2931a mClientCallbackHandler;
    private C2898b mInteractionCallback;
    private C2927a mListener;
    private ab mNativeStrandAdCache;
    private NativeStrandAdListener mNativeStrandAdListener;
    @Nullable
    private ai mNativeV2AdContainer;
    private C2974b mToken;

    class C29281 implements C2927a {
        final /* synthetic */ InMobiNativeStrand f6761a;

        C29281(InMobiNativeStrand inMobiNativeStrand) {
            this.f6761a = inMobiNativeStrand;
        }

        public void mo6132a(boolean z, InMobiAdRequestStatus inMobiAdRequestStatus) {
            if (z) {
                this.f6761a.mClientCallbackHandler.sendEmptyMessage(1);
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = inMobiAdRequestStatus;
            this.f6761a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void mo6131a() {
            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "Ad markup successfully loaded in container");
            this.f6761a.mClientCallbackHandler.sendEmptyMessage(1);
        }
    }

    class C29292 implements C2898b {
        final /* synthetic */ InMobiNativeStrand f6762a;

        C29292(InMobiNativeStrand inMobiNativeStrand) {
            this.f6762a = inMobiNativeStrand;
        }

        public void mo6089a() {
        }

        public void mo6092b() {
        }

        public void mo6093c() {
            Message obtain = Message.obtain();
            obtain.what = 7;
            this.f6762a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void mo6094d() {
            Message obtain = Message.obtain();
            obtain.what = 8;
            this.f6762a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void mo6095e() {
        }

        public void mo6091a(Map<String, String> map) {
        }

        public void mo6096f() {
        }

        public void mo6090a(String str, String str2, Map<String, Object> map) {
        }
    }

    static class C29303 implements C2908e {
        C29303() {
        }

        public void mo6116a(@NonNull AdUnit adUnit) {
            if (adUnit != null) {
                try {
                    WeakReference weakReference = (WeakReference) InMobiNativeStrand.prefetchAdUnitMap.get(adUnit);
                    if (weakReference != null) {
                        InMobiNativeStrand.prefetchAdUnitMap.remove(adUnit);
                        NativeStrandAdRequestListener nativeStrandAdRequestListener = (NativeStrandAdRequestListener) weakReference.get();
                        if (nativeStrandAdRequestListener != null) {
                            InMobiNativeStrand inMobiNativeStrand = new InMobiNativeStrand(adUnit.m8711a(), adUnit.m8736b(), adUnit.mo6259x());
                            inMobiNativeStrand.setKeywords(adUnit.m8784y());
                            inMobiNativeStrand.setExtras(adUnit.m8785z());
                            nativeStrandAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiNativeStrand);
                        }
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "SDK encountered unexpected error in onAdPrefetchSucceeded " + e.getMessage());
                }
            }
        }

        public void mo6117a(@NonNull AdUnit adUnit, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
            if (adUnit != null) {
                try {
                    WeakReference weakReference = (WeakReference) InMobiNativeStrand.prefetchAdUnitMap.get(adUnit);
                    if (weakReference != null) {
                        InMobiNativeStrand.prefetchAdUnitMap.remove(adUnit);
                        NativeStrandAdRequestListener nativeStrandAdRequestListener = (NativeStrandAdRequestListener) weakReference.get();
                        if (nativeStrandAdRequestListener != null) {
                            nativeStrandAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                        }
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "SDK encountered unexpected error in onAdPrefetchFailed " + e.getMessage());
                }
            }
        }
    }

    public interface NativeStrandAdListener {
        void onAdClicked(@NonNull InMobiNativeStrand inMobiNativeStrand);

        void onAdImpressed(@NonNull InMobiNativeStrand inMobiNativeStrand);

        void onAdLoadFailed(@NonNull InMobiNativeStrand inMobiNativeStrand, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(@NonNull InMobiNativeStrand inMobiNativeStrand);
    }

    public interface NativeStrandAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiNativeStrand inMobiNativeStrand);
    }

    private static final class C2931a extends Handler {
        private WeakReference<InMobiNativeStrand> f6763a;
        private boolean f6764b = true;

        public C2931a(InMobiNativeStrand inMobiNativeStrand) {
            super(Looper.getMainLooper());
            this.f6763a = new WeakReference(inMobiNativeStrand);
        }

        public void m8848a() {
            this.f6764b = false;
        }

        public void handleMessage(Message message) {
            InMobiNativeStrand inMobiNativeStrand = (InMobiNativeStrand) this.f6763a.get();
            if (inMobiNativeStrand == null) {
                Logger.m10359a(InternalLogLevel.ERROR, InMobiNativeStrand.TAG, "Lost reference to InMobiNativeStrand! callback cannot be given");
                return;
            }
            NativeStrandAdListener access$400 = inMobiNativeStrand.mNativeStrandAdListener;
            if (access$400 == null) {
                Logger.m10359a(InternalLogLevel.ERROR, InMobiNativeStrand.TAG, "InMobiNative Strand is already destroyed! Callback cannot be given");
                return;
            }
            switch (message.what) {
                case 1:
                    try {
                        if (!this.f6764b) {
                            this.f6764b = true;
                            inMobiNativeStrand.mNativeStrandAdListener.onAdLoadSucceeded(inMobiNativeStrand);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiNativeStrand.TAG, "Publisher handler caused unexpected error");
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "onAdLoadSucceeded callback threw unexpected error: " + e.getMessage());
                        return;
                    }
                case 2:
                    try {
                        if (!this.f6764b) {
                            this.f6764b = true;
                            inMobiNativeStrand.mNativeStrandAdListener.onAdLoadFailed(inMobiNativeStrand, (InMobiAdRequestStatus) message.obj);
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiNativeStrand.TAG, "Publisher handler caused unexpected error");
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "onAdLoadFailed callback threw unexpected error: " + e2.getMessage());
                        return;
                    }
                case 3:
                case 4:
                case 5:
                case 6:
                    return;
                case 7:
                    try {
                        access$400.onAdImpressed(inMobiNativeStrand);
                        return;
                    } catch (Exception e22) {
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiNativeStrand.TAG, "Publisher handler caused unexpected error");
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "onAdImpressed callback threw unexpected error: " + e22.getMessage());
                        return;
                    }
                case 8:
                    try {
                        access$400.onAdClicked(inMobiNativeStrand);
                        return;
                    } catch (Exception e222) {
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiNativeStrand.TAG, "Publisher handler caused unexpected error");
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "onAdClicked callback threw unexpected error: " + e222.getMessage());
                        return;
                    }
                default:
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNativeStrand.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                    return;
            }
        }
    }

    public InMobiNativeStrand(Context context, long j, NativeStrandAdListener nativeStrandAdListener) {
        this.mListener = new C29281(this);
        this.mInteractionCallback = new C29292(this);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Native Strands ad");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Context is null, Native Strand cannot be created.");
        } else if (nativeStrandAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the Native Strand cannot be created.");
        } else {
            this.mNativeStrandAdCache = ab.m9182a(context);
            this.mToken = this.mNativeStrandAdCache.m9197a(hashCode(), j, new InMobiClientPositioning(), this.mListener);
            this.mNativeStrandAdCache.m9204a(this.mToken, MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            this.mNativeStrandAdListener = nativeStrandAdListener;
            this.mClientCallbackHandler = new C2931a(this);
        }
    }

    private InMobiNativeStrand(Context context, long j, MonetizationContext monetizationContext) {
        this.mListener = new C29281(this);
        this.mInteractionCallback = new C29292(this);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Native Strands ad");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Context is null, Native Strand cannot be created.");
        } else {
            this.mNativeStrandAdCache = ab.m9182a(context);
            this.mToken = this.mNativeStrandAdCache.m9197a(hashCode(), j, new InMobiClientPositioning(), this.mListener);
            this.mNativeStrandAdCache.m9204a(this.mToken, monetizationContext);
            this.mClientCallbackHandler = new C2931a(this);
        }
    }

    public InMobiNativeStrand(Activity activity, long j, NativeStrandAdListener nativeStrandAdListener) {
        this.mListener = new C29281(this);
        this.mInteractionCallback = new C29292(this);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Native Strands ad");
        } else if (activity == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to create InmobiNativeStrand ad with null Activity object.");
        } else if (nativeStrandAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the InmobiNativeStrand cannot be created.");
        } else {
            this.mNativeStrandAdCache = ab.m9182a((Context) activity);
            this.mToken = this.mNativeStrandAdCache.m9197a(hashCode(), j, new InMobiClientPositioning(), this.mListener);
            this.mNativeStrandAdCache.m9204a(this.mToken, MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
            this.mNativeStrandAdListener = nativeStrandAdListener;
            this.mClientCallbackHandler = new C2931a(this);
            this.mActivityRef = new WeakReference(activity);
        }
    }

    public void load() {
        try {
            if (!C3105a.m10076a()) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized. Ignoring InMobiNativeStrand.load()");
            } else if (this.mNativeStrandAdListener == null) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the InMobiNativeStrand cannot be loaded.");
            } else {
                this.mClientCallbackHandler.m8848a();
                if (this.mNativeV2AdContainer != null) {
                    this.mClientCallbackHandler.sendEmptyMessage(1);
                    return;
                }
                clear();
                this.mNativeStrandAdCache.m9202a(this.mToken);
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to load ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Unable to load ad; SDK encountered an unexpected error");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    public void load(Context context) {
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized. Ignoring InMobiNativeStrand.load()");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Context is null, InMobiNativeStrand cannot be loaded.");
        } else if (this.mNativeStrandAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, InMobiNativeStrand cannot be loaded.");
        } else if (this.mNativeStrandAdCache == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand not initialized properly, InMobiNativeStrand cannot be loaded.");
        } else {
            this.mNativeStrandAdCache.m9203a(this.mToken, context);
            if (context instanceof Activity) {
                this.mNativeStrandAdCache.m9204a(this.mToken, MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
            } else {
                this.mNativeStrandAdCache.m9204a(this.mToken, MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            }
            load();
        }
    }

    public void setNativeStrandAdListener(NativeStrandAdListener nativeStrandAdListener) {
        this.mNativeStrandAdListener = nativeStrandAdListener;
    }

    public final void resume() {
        try {
            if (this.mNativeStrandAdCache != null && this.mActivityRef == null) {
                this.mNativeStrandAdCache.m9220f(this.mToken);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not resume ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in resuming ad; " + e.getMessage());
        }
    }

    public final void pause() {
        try {
            if (this.mNativeStrandAdCache != null && this.mActivityRef == null) {
                this.mNativeStrandAdCache.m9218e(this.mToken);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not pause ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in pausing ad; " + e.getMessage());
        }
    }

    public static void requestAd(@NonNull Context context, @NonNull InMobiAdRequest inMobiAdRequest, @NonNull NativeStrandAdRequestListener nativeStrandAdRequestListener) {
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (nativeStrandAdRequestListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null NativeStrandAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (inMobiAdRequest.getMonetizationContext() == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a MonetizationContext type. Ignoring request");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null Context. Ignoring request");
        } else {
            try {
                RecyclerView.class.getName();
                Picasso.class.getName();
                C2908e c29303 = new C29303();
                try {
                    ac acVar = new ac(context.getApplicationContext(), inMobiAdRequest.getPlacementId(), new Integer[0], null);
                    acVar.m8733a(inMobiAdRequest.getExtras());
                    acVar.m8730a(inMobiAdRequest.getKeywords());
                    acVar.mo6255a(inMobiAdRequest.getMonetizationContext());
                    acVar.m8722a(c29303);
                    prefetchAdUnitMap.put(acVar, new WeakReference(nativeStrandAdRequestListener));
                    acVar.mo6250B();
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in requestAd" + e.getMessage());
                }
            } catch (NoClassDefFoundError e2) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Some of the dependency libraries for InMobiNativeStrand not found. Ignoring request");
                nativeStrandAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
            }
        }
    }

    public View getStrandView(View view, ViewGroup viewGroup) {
        try {
            if (C3105a.m10076a()) {
                if (this.mNativeV2AdContainer == null) {
                    this.mNativeV2AdContainer = this.mNativeStrandAdCache.m9209b(this.mToken);
                }
                if (this.mNativeV2AdContainer == null) {
                    return null;
                }
                ViewableAd viewableAd = this.mNativeV2AdContainer.getViewableAd();
                this.mNativeV2AdContainer.m9319a(this.mInteractionCallback);
                View a = viewableAd.mo6226a(view, viewGroup, true);
                viewableAd.mo6228a(this.mNativeStrandAdCache.m9215d(this.mToken), new View[0]);
                if (a != null) {
                    return a;
                }
                C3135c.m10255a().m10280a("ads", "StrandInflationFailed", new HashMap());
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "Error inflating view even with a valid data model!");
                return null;
            }
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized. Ignoring InMobiNativeStrand.getStrandView()");
            return null;
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Could not inflate ad view; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in inflating ad view; " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            return null;
        }
    }

    public void setExtras(Map<String, String> map) {
        if (C3105a.m10076a()) {
            try {
                this.mNativeStrandAdCache.m9206a(this.mToken, (Map) map);
                return;
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Could not set extras on Native Strand ad; SDK encountered unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in setting ad request extras; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
                return;
            }
        }
        Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized.Ignoring InMobiNativeStrand.setExtras()");
    }

    public void setKeywords(String str) {
        if (C3105a.m10076a()) {
            try {
                this.mNativeStrandAdCache.m9205a(this.mToken, str);
                return;
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Could not set keywords on Native Strand ad; SDK encountered unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in setting ad request keywords; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
                return;
            }
        }
        Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized.Ignoring InMobiNativeStrand.setKeywords()");
    }

    public void destroy() {
        try {
            if (!C3105a.m10076a()) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized. Ignoring InMobiNativeStrand.destroy()");
            }
            if (this.mClientCallbackHandler != null) {
                this.mClientCallbackHandler.removeMessages(0);
            }
            this.mNativeStrandAdCache.m9214c(this.mToken);
            this.mNativeStrandAdListener = null;
            this.mListener = null;
            clear();
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Failed to destroy ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error destroying ad; " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    private void clear() {
        if (this.mNativeV2AdContainer != null) {
            this.mNativeV2AdContainer.destroy();
            this.mNativeV2AdContainer = null;
        }
    }
}

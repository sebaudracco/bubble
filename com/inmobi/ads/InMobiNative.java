package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.AdUnit.C2908e;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class InMobiNative {
    private static final String DEBUG_LOG_TAG = "InMobi";
    private static final String TAG = InMobiNative.class.getSimpleName();
    private static ConcurrentHashMap<aa, WeakReference<NativeAdRequestListener>> prefetchAdUnitMap = new ConcurrentHashMap(5, 0.9f, 3);
    protected static WeakHashMap<View, aa> sMappedAdUnits = new WeakHashMap();
    private WeakReference<Activity> mActivityRef;
    private C2926a mClientCallbackHandler;
    private final C2905b mListener;
    private NativeAdEventsListener mNativeAdEventListener;
    private NativeAdListener mNativeAdListener;
    protected aa mNativeAdUnit;

    public interface NativeAdListener {
        void onAdDismissed(InMobiNative inMobiNative);

        void onAdDisplayed(InMobiNative inMobiNative);

        void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiNative inMobiNative);

        void onUserLeftApplication(InMobiNative inMobiNative);
    }

    static class C29241 implements C2908e {
        C29241() {
        }

        public void mo6116a(@NonNull AdUnit adUnit) {
            if (adUnit != null) {
                try {
                    WeakReference weakReference = (WeakReference) InMobiNative.prefetchAdUnitMap.get(adUnit);
                    if (weakReference != null) {
                        InMobiNative.prefetchAdUnitMap.remove(adUnit);
                        NativeAdRequestListener nativeAdRequestListener = (NativeAdRequestListener) weakReference.get();
                        if (nativeAdRequestListener != null) {
                            InMobiNative inMobiNative = new InMobiNative(adUnit.m8736b(), adUnit.mo6259x());
                            inMobiNative.setExtras(adUnit.m8785z());
                            inMobiNative.setKeywords(adUnit.m8784y());
                            nativeAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiNative);
                        }
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "SDK encountered unexpected error in onAdPrefetchSucceeded " + e.getMessage());
                }
            }
        }

        public void mo6117a(@NonNull AdUnit adUnit, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
            if (adUnit != null) {
                try {
                    WeakReference weakReference = (WeakReference) InMobiNative.prefetchAdUnitMap.get(adUnit);
                    if (weakReference != null) {
                        InMobiNative.prefetchAdUnitMap.remove(adUnit);
                        NativeAdRequestListener nativeAdRequestListener = (NativeAdRequestListener) weakReference.get();
                        if (nativeAdRequestListener != null) {
                            nativeAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                        }
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "SDK encountered unexpected error in onAdPrefetchFailed " + e.getMessage());
                }
            }
        }
    }

    class C29252 implements C2905b {
        final /* synthetic */ InMobiNative f6757a;

        C29252(InMobiNative inMobiNative) {
            this.f6757a = inMobiNative;
        }

        public void mo6122a(AdUnit adUnit, boolean z) {
        }

        public void mo6120a(AdUnit adUnit) {
        }

        public void mo6119a() {
            this.f6757a.mClientCallbackHandler.sendEmptyMessage(1);
        }

        public void mo6121a(AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = inMobiAdRequestStatus;
            this.f6757a.mClientCallbackHandler.sendMessage(obtain);
        }

        public void mo6124b() {
        }

        public void mo6126c() {
        }

        public void mo6127d() {
            this.f6757a.mClientCallbackHandler.sendEmptyMessage(3);
        }

        public void mo6128e() {
            this.f6757a.mClientCallbackHandler.sendEmptyMessage(4);
        }

        public void mo6123a(@NonNull Map<Object, Object> map) {
        }

        public void mo6129f() {
            this.f6757a.mClientCallbackHandler.sendEmptyMessage(5);
        }

        public void mo6125b(@NonNull Map<Object, Object> map) {
        }

        public void mo6130g() {
            this.f6757a.mClientCallbackHandler.sendEmptyMessage(6);
        }
    }

    public interface NativeAdEventsListener {
        void onAdImpressed(InMobiNative inMobiNative);
    }

    public interface NativeAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiNative inMobiNative);
    }

    private static final class C2926a extends Handler {
        private WeakReference<NativeAdListener> f6758a;
        private WeakReference<NativeAdEventsListener> f6759b;
        private WeakReference<InMobiNative> f6760c;

        public C2926a(InMobiNative inMobiNative, NativeAdListener nativeAdListener) {
            super(Looper.getMainLooper());
            this.f6760c = new WeakReference(inMobiNative);
            this.f6758a = new WeakReference(nativeAdListener);
        }

        public void m8833a(NativeAdEventsListener nativeAdEventsListener) {
            this.f6759b = new WeakReference(nativeAdEventsListener);
        }

        public void handleMessage(Message message) {
            InMobiNative inMobiNative = (InMobiNative) this.f6760c.get();
            NativeAdListener nativeAdListener = (NativeAdListener) this.f6758a.get();
            if (!(inMobiNative == null || nativeAdListener == null)) {
                switch (message.what) {
                    case 1:
                        try {
                            nativeAdListener.onAdLoadSucceeded(inMobiNative);
                            break;
                        } catch (Exception e) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiNative.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "onAdLoadSucceeded callback threw unexpected error: " + e.getMessage());
                            break;
                        }
                    case 2:
                        try {
                            nativeAdListener.onAdLoadFailed(inMobiNative, (InMobiAdRequestStatus) message.obj);
                            break;
                        } catch (Exception e2) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiNative.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "onAdLoadFailed callback threw unexpected error: " + e2.getMessage());
                            break;
                        }
                    case 3:
                        try {
                            nativeAdListener.onAdDisplayed(inMobiNative);
                            break;
                        } catch (Exception e22) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiNative.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "onAdDisplayed callback threw unexpected error: " + e22.getMessage());
                            break;
                        }
                    case 4:
                        try {
                            nativeAdListener.onAdDismissed(inMobiNative);
                            break;
                        } catch (Exception e222) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiNative.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "onAdDismissed callback threw unexpected error: " + e222.getMessage());
                            break;
                        }
                    case 5:
                        try {
                            nativeAdListener.onUserLeftApplication(inMobiNative);
                            break;
                        } catch (Exception e2222) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiNative.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "onUserLeftApplication callback threw unexpected error: " + e2222.getMessage());
                            break;
                        }
                    default:
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                        break;
                }
            }
            if (inMobiNative != null && this.f6759b != null && this.f6759b.get() != null) {
                switch (message.what) {
                    case 6:
                        try {
                            ((NativeAdEventsListener) this.f6759b.get()).onAdImpressed(inMobiNative);
                            return;
                        } catch (Exception e3) {
                            Logger.m10359a(InternalLogLevel.ERROR, InMobiNative.TAG, "Publisher handler caused unexpected error");
                            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "onAdImpressed callback threw unexpected error: " + e3.getMessage());
                            return;
                        }
                    default:
                        Logger.m10359a(InternalLogLevel.INTERNAL, InMobiNative.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                        return;
                }
            }
        }
    }

    public InMobiNative(long j, NativeAdListener nativeAdListener) {
        this.mListener = new C29252(this);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Native ad");
        } else if (nativeAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "The Native ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else {
            this.mNativeAdListener = nativeAdListener;
            this.mNativeAdUnit = new aa(j, this.mListener);
            this.mNativeAdUnit.mo6255a(MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            this.mClientCallbackHandler = new C2926a(this, nativeAdListener);
        }
    }

    private InMobiNative(long j, MonetizationContext monetizationContext) {
        this.mListener = new C29252(this);
        if (C3105a.m10076a()) {
            this.mNativeAdUnit = new aa(j, this.mListener);
            this.mNativeAdUnit.mo6255a(monetizationContext);
            return;
        }
        Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Native ad");
    }

    private void setMonetizationContext(MonetizationContext monetizationContext) {
        this.mNativeAdUnit.mo6255a(monetizationContext);
    }

    public void setNativeAdListener(NativeAdListener nativeAdListener) {
        this.mNativeAdListener = nativeAdListener;
        this.mClientCallbackHandler = new C2926a(this, nativeAdListener);
    }

    private InMobiNative(aa aaVar) {
        this.mListener = new C29252(this);
        this.mNativeAdUnit = aaVar;
    }

    public InMobiNative(Activity activity, long j, NativeAdListener nativeAdListener) {
        this.mListener = new C29252(this);
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating a Native ad");
        } else if (activity == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Unable to create InmobiNative ad with null Activity object.");
        } else if (nativeAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "The Native ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else {
            this.mNativeAdListener = nativeAdListener;
            this.mNativeAdUnit = new aa(activity, j, this.mListener);
            this.mNativeAdUnit.mo6255a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
            this.mClientCallbackHandler = new C2926a(this, nativeAdListener);
            this.mActivityRef = new WeakReference(activity);
        }
    }

    public final void load() {
        try {
            if (!C3105a.m10076a()) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNative is not initialized. Ignoring InMobiNative.load()");
            } else if (this.mNativeAdListener == null) {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the InMobiNative cannot be loaded.");
            } else if (this.mNativeAdUnit != null) {
                this.mNativeAdUnit.mo6139A();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not load ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in loading ad; " + e.getMessage());
        }
    }

    public void load(Context context) {
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "InMobiNative is not initialized. Ignoring InMobiNative.load()");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Context is null, InMobiNative cannot be loaded.");
        } else if (this.mNativeAdListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the InMobiNative cannot be loaded.");
        } else {
            this.mNativeAdUnit.mo6146a(context);
            if (context instanceof Activity) {
                this.mNativeAdUnit.mo6255a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
            } else {
                this.mNativeAdUnit.mo6255a(MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            }
            load();
        }
    }

    public static void requestAd(@NonNull Context context, @NonNull InMobiAdRequest inMobiAdRequest, @NonNull NativeAdRequestListener nativeAdRequestListener) {
        if (!C3105a.m10076a()) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (nativeAdRequestListener == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null NativeAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (inMobiAdRequest.getMonetizationContext() == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a MonetizationContext type. Ignoring request");
        } else if (context == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please supply a non null Context. Ignoring request");
        } else {
            C2908e c29241 = new C29241();
            try {
                aa aaVar = new aa(inMobiAdRequest.getPlacementId(), null);
                aaVar.m8733a(inMobiAdRequest.getExtras());
                aaVar.m8730a(inMobiAdRequest.getKeywords());
                aaVar.mo6255a(inMobiAdRequest.getMonetizationContext());
                aaVar.m8722a(c29241);
                prefetchAdUnitMap.put(aaVar, new WeakReference(nativeAdRequestListener));
                aaVar.mo6250B();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in requestAd" + e.getMessage());
            }
        }
    }

    public final void resume() {
        try {
            if (this.mNativeAdUnit != null && this.mActivityRef == null) {
                this.mNativeAdUnit.mo6142S();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not resume ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in resuming ad; " + e.getMessage());
        }
    }

    public final void pause() {
        try {
            if (this.mNativeAdUnit != null && this.mActivityRef == null) {
                this.mNativeAdUnit.mo6143T();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not pause ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in pausing ad; " + e.getMessage());
        }
    }

    public final Object getAdContent() {
        if (this.mNativeAdUnit == null) {
            return null;
        }
        return this.mNativeAdUnit.mo6144U();
    }

    public static void bind(View view, InMobiNative inMobiNative) {
        aa aaVar;
        if (view == null) {
            try {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please pass non-null instances of a view to bind.");
                if (inMobiNative != null) {
                    aaVar = inMobiNative.mNativeAdUnit;
                    if (aaVar != null) {
                        aaVar.mo6105a("ads", "InvalidTrackerView", new HashMap());
                    }
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not bind ad; SDK encountered an unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in binding ad; " + e.getMessage());
            }
        } else if (inMobiNative == null) {
            Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please pass non-null instances of InMobiNative to bind.");
        } else {
            if (((aa) sMappedAdUnits.get(view)) != null) {
                unbind(view);
            }
            aaVar = inMobiNative.mNativeAdUnit;
            if (aaVar != null) {
                sMappedAdUnits.remove(view);
                sMappedAdUnits.put(view, aaVar);
                aaVar.m9142a(view, null, null);
            }
        }
    }

    public static void unbind(View view) {
        if (view == null) {
            try {
                Logger.m10359a(InternalLogLevel.ERROR, TAG, "Please pass a non-null view object to bind.");
                return;
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Could not unbind ad; SDK encountered an unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in unbinding ad; " + e.getMessage());
                return;
            }
        }
        aa aaVar = (aa) sMappedAdUnits.remove(view);
        if (aaVar != null) {
            aaVar.m9140a(view);
        }
    }

    public final void reportAdClickAndOpenLandingPage(Map<String, String> map) {
        try {
            if (this.mNativeAdUnit != null) {
                this.mNativeAdUnit.m9143a((Map) map, null, null);
                this.mNativeAdUnit.m9138W();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Reporting ad click and opening landing page failed; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in reporting click and opening landing page for ad; " + e.getMessage());
        }
    }

    public final void reportAdClick(Map<String, String> map) {
        try {
            if (this.mNativeAdUnit != null) {
                this.mNativeAdUnit.m9143a((Map) map, null, null);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, DEBUG_LOG_TAG, "Reporting ad click failed; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in reporting click for ad; " + e.getMessage());
        }
    }

    public final void setExtras(Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.m8733a((Map) map);
        }
    }

    public final void setKeywords(String str) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.m8730a(str);
        }
    }

    public final void setNativeAdEventListener(NativeAdEventsListener nativeAdEventsListener) {
        this.mNativeAdEventListener = nativeAdEventsListener;
        if (this.mClientCallbackHandler != null) {
            this.mClientCallbackHandler.m8833a(this.mNativeAdEventListener);
        }
    }
}

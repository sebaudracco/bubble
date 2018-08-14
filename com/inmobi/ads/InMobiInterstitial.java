package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.inmobi.ads.AdUnit.b;
import com.inmobi.ads.AdUnit.e;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public final class InMobiInterstitial {
    private static final String TAG = InMobiInterstitial.class.getSimpleName();
    private static ConcurrentHashMap<w, ArrayList<WeakReference<InterstitialAdRequestListener>>> prefetchAdUnitMap = new ConcurrentHashMap(2, 0.9f, 3);
    private C1581a mClientCallbackHandler;
    private WeakReference<Context> mContextRef;
    private boolean mDidPubCalledLoad;
    private Map<String, String> mExtras;
    private final b mInterstitialAdListener;
    private w mInterstitialAdUnit;
    private boolean mIsHardwareAccelerationDisabled;
    private boolean mIsInitialized;
    private boolean mIsTrueRequestBeaconFired;
    private String mKeywords;
    private InterstitialAdListener mListener;
    private InterstitialAdListener2 mListener2;
    private long mPlacementId;

    @Deprecated
    public interface InterstitialAdListener {
        void onAdDismissed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

        void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

        void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
    }

    private static final class C1581a extends Handler {
        private WeakReference<InterstitialAdListener2> f2642a;
        private WeakReference<InterstitialAdListener> f2643b;
        private WeakReference<InMobiInterstitial> f2644c;
        private boolean f2645d = true;
        private boolean f2646e = true;

        @Deprecated
        public C1581a(InMobiInterstitial inMobiInterstitial, InterstitialAdListener interstitialAdListener) {
            super(Looper.getMainLooper());
            this.f2644c = new WeakReference(inMobiInterstitial);
            this.f2643b = new WeakReference(interstitialAdListener);
        }

        public C1581a(InMobiInterstitial inMobiInterstitial, InterstitialAdListener2 interstitialAdListener2) {
            super(Looper.getMainLooper());
            this.f2644c = new WeakReference(inMobiInterstitial);
            this.f2642a = new WeakReference(interstitialAdListener2);
        }

        public void m3429a() {
            this.f2645d = false;
        }

        public void m3430b() {
            this.f2646e = false;
        }

        public void handleMessage(Message message) {
            Map map = null;
            InMobiInterstitial inMobiInterstitial = (InMobiInterstitial) this.f2644c.get();
            if (this.f2642a != null) {
                InterstitialAdListener2 interstitialAdListener2 = (InterstitialAdListener2) this.f2642a.get();
                if (inMobiInterstitial != null && interstitialAdListener2 != null) {
                    switch (message.what) {
                        case 10:
                            if (!this.f2645d) {
                                this.f2645d = true;
                                try {
                                    interstitialAdListener2.onAdLoadFailed(inMobiInterstitial, (InMobiAdRequestStatus) message.obj);
                                    return;
                                } catch (Exception e) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdLoadFailed callback threw unexpected error: " + e.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 11:
                            boolean z = message.getData().getBoolean("available");
                            if (!this.f2645d && z) {
                                try {
                                    interstitialAdListener2.onAdReceived(inMobiInterstitial);
                                    return;
                                } catch (Exception e2) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdReceived callback threw unexpected error: " + e2.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 12:
                            if (!this.f2645d) {
                                this.f2645d = true;
                                try {
                                    interstitialAdListener2.onAdLoadSucceeded(inMobiInterstitial);
                                    return;
                                } catch (Exception e22) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdLoadSucceeded callback threw unexpected error: " + e22.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 13:
                            if (!this.f2646e) {
                                if (message.obj != null) {
                                    map = (Map) message.obj;
                                }
                                try {
                                    interstitialAdListener2.onAdRewardActionCompleted(inMobiInterstitial, map);
                                    return;
                                } catch (Exception e222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdRewardActionCompleted callback threw unexpected error: " + e222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 14:
                            if (!this.f2646e) {
                                this.f2646e = true;
                                try {
                                    interstitialAdListener2.onAdDisplayFailed(inMobiInterstitial);
                                    return;
                                } catch (Exception e2222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdDisplayFailed callback threw unexpected error: " + e2222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 15:
                            if (!this.f2646e) {
                                try {
                                    interstitialAdListener2.onAdWillDisplay(inMobiInterstitial);
                                    return;
                                } catch (Exception e22222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdWillDisplay callback threw unexpected error: " + e22222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 16:
                            if (!this.f2646e) {
                                try {
                                    interstitialAdListener2.onAdDisplayed(inMobiInterstitial);
                                    return;
                                } catch (Exception e222222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdDisplayed callback threw unexpected error: " + e222222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 17:
                            return;
                        case 18:
                            if (!this.f2646e) {
                                if (message.obj != null) {
                                    map = (Map) message.obj;
                                }
                                try {
                                    interstitialAdListener2.onAdInteraction(inMobiInterstitial, map);
                                    return;
                                } catch (Exception e2222222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdInteraction callback threw unexpected error: " + e2222222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 19:
                            if (!this.f2646e) {
                                this.f2646e = true;
                                try {
                                    interstitialAdListener2.onAdDismissed(inMobiInterstitial);
                                    return;
                                } catch (Exception e22222222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdDismissed callback threw unexpected error: " + e22222222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 20:
                            if (!this.f2646e) {
                                try {
                                    interstitialAdListener2.onUserLeftApplication(inMobiInterstitial);
                                    return;
                                } catch (Exception e222222222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onUserLeftApplication callback threw unexpected error: " + e222222222.getMessage());
                                    return;
                                }
                            }
                            return;
                        default:
                            Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                            return;
                    }
                }
            } else if (this.f2643b != null) {
                InterstitialAdListener interstitialAdListener = (InterstitialAdListener) this.f2643b.get();
                if (inMobiInterstitial != null && interstitialAdListener != null) {
                    switch (message.what) {
                        case 1:
                            if (!this.f2645d) {
                                this.f2645d = true;
                                try {
                                    interstitialAdListener.onAdLoadSucceeded(inMobiInterstitial);
                                    return;
                                } catch (Exception e2222222222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdLoadSucceeded callback threw unexpected error: " + e2222222222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 2:
                            if (!this.f2645d) {
                                this.f2645d = true;
                                try {
                                    interstitialAdListener.onAdLoadFailed(inMobiInterstitial, (InMobiAdRequestStatus) message.obj);
                                    return;
                                } catch (Exception e22222222222) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.TAG, "Publisher handler caused unexpected error");
                                    Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "onAdLoadFailed callback threw unexpected error: " + e22222222222.getMessage());
                                    return;
                                }
                            }
                            return;
                        case 3:
                            if (!this.f2646e) {
                                interstitialAdListener.onAdDisplayed(inMobiInterstitial);
                                return;
                            }
                            return;
                        case 4:
                            if (!this.f2646e) {
                                this.f2646e = true;
                                interstitialAdListener.onAdDismissed(inMobiInterstitial);
                                return;
                            }
                            return;
                        case 5:
                            if (!this.f2646e) {
                                if (message.obj != null) {
                                    map = (Map) message.obj;
                                }
                                interstitialAdListener.onAdInteraction(inMobiInterstitial, map);
                                return;
                            }
                            return;
                        case 6:
                            if (!this.f2646e) {
                                interstitialAdListener.onUserLeftApplication(inMobiInterstitial);
                                return;
                            }
                            return;
                        case 7:
                            if (!this.f2646e) {
                                if (message.obj != null) {
                                    map = (Map) message.obj;
                                }
                                interstitialAdListener.onAdRewardActionCompleted(inMobiInterstitial, map);
                                return;
                            }
                            return;
                        default:
                            Logger.a(InternalLogLevel.INTERNAL, InMobiInterstitial.TAG, "Unhandled ad lifecycle event! Ignoring ...");
                            return;
                    }
                }
            }
        }
    }

    @Deprecated
    public InMobiInterstitial(Context context, long j, InterstitialAdListener interstitialAdListener) {
        this.mIsInitialized = false;
        this.mIsTrueRequestBeaconFired = false;
        this.mDidPubCalledLoad = false;
        this.mInterstitialAdListener = new 2(this);
        if (!a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to create Interstitial ad with null context object.");
        } else if (context instanceof Activity) {
            this.mIsInitialized = true;
            this.mContextRef = new WeakReference(context);
            this.mPlacementId = j;
            this.mListener = interstitialAdListener;
            this.mClientCallbackHandler = new C1581a(this, this.mListener);
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please supply an Activity context to create an Interstitial ad");
        }
    }

    public InMobiInterstitial(Context context, long j, InterstitialAdListener2 interstitialAdListener2) {
        this.mIsInitialized = false;
        this.mIsTrueRequestBeaconFired = false;
        this.mDidPubCalledLoad = false;
        this.mInterstitialAdListener = new 2(this);
        if (!a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdListener2 == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to create Interstitial ad with null context object.");
        } else {
            this.mIsInitialized = true;
            this.mContextRef = new WeakReference(context);
            this.mPlacementId = j;
            this.mListener2 = interstitialAdListener2;
            this.mClientCallbackHandler = new C1581a(this, this.mListener2);
        }
    }

    public InMobiInterstitial(Activity activity, long j, InterstitialAdListener2 interstitialAdListener2) {
        this.mIsInitialized = false;
        this.mIsTrueRequestBeaconFired = false;
        this.mDidPubCalledLoad = false;
        this.mInterstitialAdListener = new 2(this);
        if (!a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdListener2 == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (activity == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to create Interstitial ad with null Activity object.");
        } else {
            this.mIsInitialized = true;
            this.mContextRef = new WeakReference(activity);
            this.mPlacementId = j;
            this.mListener2 = interstitialAdListener2;
            this.mClientCallbackHandler = new C1581a(this, this.mListener2);
        }
    }

    private InMobiInterstitial(Context context, long j) {
        this.mIsInitialized = false;
        this.mIsTrueRequestBeaconFired = false;
        this.mDidPubCalledLoad = false;
        this.mInterstitialAdListener = new 2(this);
        this.mIsInitialized = true;
        this.mContextRef = new WeakReference(context);
        this.mPlacementId = j;
    }

    public void setInterstitialAdListener(InterstitialAdListener2 interstitialAdListener2) {
        this.mListener2 = interstitialAdListener2;
        this.mClientCallbackHandler = new C1581a(this, interstitialAdListener2);
    }

    public void setKeywords(String str) {
        if (this.mIsInitialized) {
            this.mKeywords = str;
        }
    }

    private bb getPlacementObj() {
        bb bbVar = new bb(this.mPlacementId, j.a(this.mExtras));
        bbVar.a(this.mKeywords);
        bbVar.a(this.mExtras);
        return bbVar;
    }

    public void load() {
        try {
            if (!a.a()) {
                Logger.a(InternalLogLevel.ERROR, TAG, "InMobiInterstitial is not initialized. Ignoring InMobiInterstitial.load()");
            } else if (this.mListener2 == null && this.mListener == null) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the InMobiInterstitial cannot be loaded.");
            } else if (this.mIsInitialized && this.mContextRef.get() != null) {
                AdUnit a = j.a().a(getPlacementObj());
                this.mClientCallbackHandler.m3429a();
                this.mIsTrueRequestBeaconFired = false;
                this.mDidPubCalledLoad = true;
                if (a != null) {
                    this.mInterstitialAdUnit = (w) a;
                } else {
                    this.mInterstitialAdUnit = w.a.a(hashCode(), (Context) this.mContextRef.get(), this.mPlacementId, this.mInterstitialAdListener);
                }
                setupAdUnit();
                loadAdUnit();
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to load ad; SDK encountered an unexpected error");
            Logger.a(InternalLogLevel.INTERNAL, TAG, "Load failed with unexpected error: " + e.getMessage());
            c.a().a(new com.inmobi.commons.core.d.b(e));
        }
    }

    public static void requestAd(@NonNull Context context, @NonNull InMobiAdRequest inMobiAdRequest, @NonNull InterstitialAdRequestListener interstitialAdRequestListener) {
        if (!a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (interstitialAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please supply a non null InterstitialAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please supply a non null Context. Ignoring request");
        } else {
            try {
                RecyclerView.class.getName();
                Picasso.class.getName();
                e 1 = new 1();
                try {
                    Object obj;
                    for (Entry key : prefetchAdUnitMap.entrySet()) {
                        obj = (w) key.getKey();
                        if (obj != null && obj.b() == inMobiAdRequest.getPlacementId()) {
                            break;
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        ArrayList arrayList = (ArrayList) prefetchAdUnitMap.get(obj);
                        arrayList.add(new WeakReference(interstitialAdRequestListener));
                        w prefetchUnit = getPrefetchUnit(context, inMobiAdRequest, 1);
                        prefetchAdUnitMap.put(prefetchUnit, arrayList);
                        prefetchUnit.B();
                        return;
                    }
                    setupAndGetInterstitialAdUnit(context, inMobiAdRequest, interstitialAdRequestListener, 1).B();
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.INTERNAL, TAG, "SDK encountered unexpected error in requestAd" + e.getMessage());
                }
            } catch (NoClassDefFoundError e2) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Some of the dependency libraries for Interstitial not found. Ignoring request");
                interstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
            }
        }
    }

    static w setupAndGetInterstitialAdUnit(Context context, InMobiAdRequest inMobiAdRequest, InterstitialAdRequestListener interstitialAdRequestListener, e eVar) {
        w prefetchUnit = getPrefetchUnit(context, inMobiAdRequest, eVar);
        prefetchUnit.a(eVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WeakReference(interstitialAdRequestListener));
        prefetchAdUnitMap.put(prefetchUnit, arrayList);
        return prefetchUnit;
    }

    static w getPrefetchUnit(Context context, InMobiAdRequest inMobiAdRequest, e eVar) {
        w a = w.a.a(new Random().nextInt(), context.getApplicationContext(), inMobiAdRequest.getPlacementId(), null);
        a.a(inMobiAdRequest.getExtras());
        a.a(inMobiAdRequest.getKeywords());
        a.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        a.a(true);
        return a;
    }

    private void setupAdUnit() {
        this.mInterstitialAdUnit.a((Context) this.mContextRef.get());
        this.mInterstitialAdUnit.a(hashCode(), this.mInterstitialAdListener);
        this.mInterstitialAdUnit.a(this.mExtras);
        this.mInterstitialAdUnit.a(this.mKeywords);
        this.mInterstitialAdUnit.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        if (this.mIsHardwareAccelerationDisabled) {
            this.mInterstitialAdUnit.U();
        }
        this.mInterstitialAdUnit.a(false);
    }

    private void loadAdUnit() {
        Logger.a(InternalLogLevel.DEBUG, TAG, "Fetching an Interstitial ad for placement id: " + this.mInterstitialAdUnit.b());
        this.mInterstitialAdUnit.a(this.mInterstitialAdListener);
        this.mInterstitialAdUnit.A();
    }

    public void show() {
        try {
            if (!this.mDidPubCalledLoad) {
                Logger.a(InternalLogLevel.ERROR, TAG, "load() must be called before trying to show the ad");
            } else if (this.mIsInitialized && this.mInterstitialAdUnit != null) {
                this.mClientCallbackHandler.m3430b();
                this.mInterstitialAdUnit.c(hashCode());
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to show ad; SDK encountered an unexpected error");
            Logger.a(InternalLogLevel.INTERNAL, TAG, "Show failed with unexpected error: " + e.getMessage());
            c.a().a(new com.inmobi.commons.core.d.b(e));
        }
    }

    public void show(int i, int i2) {
        try {
            if (this.mIsInitialized && this.mInterstitialAdUnit != null) {
                this.mClientCallbackHandler.m3430b();
                this.mInterstitialAdUnit.a(hashCode(), i, i2);
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to show ad; SDK encountered an unexpected error");
            Logger.a(InternalLogLevel.INTERNAL, TAG, "Show failed with unexpected error: " + e.getMessage());
            c.a().a(new com.inmobi.commons.core.d.b(e));
        }
    }

    public boolean isReady() {
        if (!this.mIsInitialized || this.mInterstitialAdUnit == null) {
            return false;
        }
        boolean T = this.mInterstitialAdUnit.T();
        if (T) {
            return T;
        }
        tryFiringTrueRequestBeacon("isReadyFalse");
        return T;
    }

    public void setExtras(Map<String, String> map) {
        if (this.mIsInitialized) {
            this.mExtras = map;
        }
    }

    public void disableHardwareAcceleration() {
        if (this.mIsInitialized) {
            this.mIsHardwareAccelerationDisabled = true;
        }
    }

    private void tryFiringTrueRequestBeacon(String str) {
        if (!this.mIsTrueRequestBeaconFired) {
            this.mInterstitialAdUnit.f(str);
            this.mIsTrueRequestBeaconFired = true;
        }
    }
}

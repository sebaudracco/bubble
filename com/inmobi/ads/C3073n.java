package com.inmobi.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdCreativeType;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.AdTrackerType;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.C3323a;
import com.mopub.mobileads.VastResourceXmlManager;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BannerAdUnit */
class C3073n extends AdUnit implements ActivityLifecycleCallbacks {
    private static final String f7506b = C3073n.class.getSimpleName();
    private static final String f7507c = InMobiBanner.class.getSimpleName();
    private boolean f7508d = true;
    private boolean f7509e = false;
    private int f7510f = 0;
    private String f7511g;

    public C3073n(Context context, long j, C2905b c2905b) {
        super(context, j, c2905b);
    }

    public C3073n(Activity activity, long j, C2905b c2905b) {
        super(activity, j, c2905b);
        activity.getApplication().registerActivityLifecycleCallbacks(this);
    }

    public void mo6146a(Context context) {
        super.mo6146a(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    void mo6142S() {
        RenderView renderView = (RenderView) mo6167t();
        if (renderView != null) {
            this.f7509e = true;
            renderView.mo6173a();
        }
    }

    public void m9931b(boolean z) {
        if (z) {
            Logger.m10359a(InternalLogLevel.DEBUG, f7507c, "Initiating Banner refresh for placement id: " + m8736b());
        }
        Logger.m10359a(InternalLogLevel.DEBUG, f7507c, "Fetching a Banner ad for placement id: " + m8736b());
        this.f7508d = z;
        super.mo6139A();
    }

    protected boolean mo6140F() {
        if (AdState.STATE_LOADING == m8748c() || AdState.STATE_AVAILABLE == m8748c()) {
            m8724a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + m8736b());
            return true;
        } else if (m8748c() != AdState.STATE_ACTIVE) {
            return false;
        } else {
            m8724a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + m8736b());
            return true;
        }
    }

    protected void mo6150b(C2968a c2968a) {
    }

    boolean mo6143T() {
        return this.f7509e;
    }

    boolean mo6144U() {
        return m8748c() == AdState.STATE_ACTIVE;
    }

    @NonNull
    protected RenderView mo6245v() {
        RenderView v = super.mo6245v();
        if (this.f7509e) {
            v.mo6173a();
        }
        return v;
    }

    protected String mo6151d() {
        return "banner";
    }

    protected void mo6165g(String str) {
        this.f7511g = str;
    }

    protected String mo6152f() {
        return this.f7511g;
    }

    protected PlacementType mo6153h() {
        return PlacementType.PLACEMENT_TYPE_INLINE;
    }

    protected Map<String, String> mo6166i() {
        Map hashMap = new HashMap();
        hashMap.put("u-rt", this.f7508d ? String.valueOf(1) : String.valueOf(0));
        hashMap.put("mk-ad-slot", this.f7511g);
        return hashMap;
    }

    @UiThread
    public void mo6149b(long j, @NonNull C2968a c2968a) {
        try {
            super.mo6149b(j, c2968a);
            Logger.m10359a(InternalLogLevel.DEBUG, f7507c, "Banner ad fetch successful for placement id: " + m8736b());
            if (j == m8736b() && m8748c() == AdState.STATE_AVAILABLE) {
                boolean h = m8775p().m9728m().m9703h();
                for (bi biVar : m8764g()) {
                    if (h && AdTrackerType.AD_TRACKER_TYPE_IAS == biVar.f7267a) {
                        try {
                            C3323a a = C3078q.m9949a(m8711a(), false, (AdCreativeType) biVar.f7268b.get(VastResourceXmlManager.CREATIVE_TYPE), mo6245v());
                            if (a != null) {
                                biVar.f7268b.put("avidAdSession", a);
                                Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "AVID ad session created and WebView container registered with AVID");
                            } else {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "Ignoring AVID meta data for this ad markup");
                            }
                        } catch (Exception e) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "Setting up impression tracking for AVID encountered an unexpected error: " + e.getMessage());
                        }
                    }
                }
                try {
                    Logger.m10359a(InternalLogLevel.DEBUG, f7507c, "Started loading banner ad markup in WebView for placement id: " + m8736b());
                    m8713a(0, m8769j(), null, null);
                } catch (Exception e2) {
                    m8706M();
                    if (m8776q() != null) {
                        m8776q().mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                    }
                    Logger.m10359a(InternalLogLevel.ERROR, f7507c, "Unable to load ad; SDK encountered an internal error");
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "Loading ad markup into container encountered an unexpected error: " + e2.getMessage());
                }
            }
        } catch (Exception e22) {
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "Unable to load ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "Handling ad fetch successful encountered an unexpected error: " + e22.getMessage());
        }
    }

    public void mo6110c(RenderView renderView) {
        try {
            super.mo6110c(renderView);
            if (m8748c() == AdState.STATE_AVAILABLE) {
                m8706M();
                m8720a(AdState.STATE_LOADED);
                m8709P();
                Logger.m10359a(InternalLogLevel.DEBUG, f7507c, "Successfully loaded Banner ad markup in the WebView for placement id: " + m8736b());
                if (m8776q() != null) {
                    m8776q().mo6119a();
                }
                m8701H();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "Unable to load ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "Loading ad markup into container encountered an unexpected error: " + e.getMessage());
        }
    }

    public void mo6111d(RenderView renderView) {
        try {
            super.mo6111d(renderView);
            if (m8748c() == AdState.STATE_LOADED) {
                m8720a(AdState.STATE_RENDERED);
                m8731a("ads", "AdRendered");
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "Unable to load ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "BannerAdUnit.onRenderViewVisible threw unexpected error: " + e.getMessage());
        }
    }

    public synchronized void mo6113f(RenderView renderView) {
        try {
            super.mo6113f(renderView);
            if (m8748c() == AdState.STATE_RENDERED) {
                this.f7510f++;
                m8720a(AdState.STATE_ACTIVE);
                Logger.m10359a(InternalLogLevel.DEBUG, f7507c, "Successfully displayed banner ad for placement Id : " + m8736b());
                if (m8776q() != null) {
                    m8776q().mo6127d();
                }
            } else if (m8748c() == AdState.STATE_ACTIVE) {
                this.f7510f++;
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "Unable to display ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "BannerAdUnit.onAdScreenDisplayed threw unexpected error: " + e.getMessage());
        }
    }

    public synchronized void mo6114g(RenderView renderView) {
        try {
            super.mo6114g(renderView);
            if (m8748c() == AdState.STATE_ACTIVE) {
                int i = this.f7510f - 1;
                this.f7510f = i;
                if (i == 0) {
                    m8720a(AdState.STATE_RENDERED);
                    if (m8776q() != null) {
                        m8776q().mo6128e();
                    }
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, f7507c, "Unable to dismiss ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7506b, "BannerAdUnit.onAdScreenDismissed threw unexpected error: " + e.getMessage());
        }
    }

    public void mo6145V() {
        AdState c = m8748c();
        if (c == AdState.STATE_LOADED || c == AdState.STATE_RENDERED || c == AdState.STATE_ACTIVE) {
            AdContainer t = mo6167t();
            if (t != null) {
                ViewableAd viewableAd = t.getViewableAd();
                if (viewableAd != null) {
                    viewableAd.mo6229c();
                }
            }
        }
    }

    public void m9927W() {
        AdState c = m8748c();
        if (c == AdState.STATE_LOADED || c == AdState.STATE_RENDERED || c == AdState.STATE_ACTIVE) {
            AdContainer t = mo6167t();
            if (t != null) {
                ViewableAd viewableAd = t.getViewableAd();
                if (viewableAd != null) {
                    viewableAd.mo6228a(m8775p().m9728m(), new View[0]);
                }
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        Context a = m8711a();
        if (a != null && a.equals(activity)) {
            m9927W();
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        Context a = m8711a();
        if (a != null && a.equals(activity)) {
            mo6145V();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        Context a = m8711a();
        if (a != null && a.equals(activity)) {
            ((Activity) a).getApplication().unregisterActivityLifecycleCallbacks(this);
            mo6141J();
        }
    }
}

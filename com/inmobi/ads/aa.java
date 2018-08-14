package com.inmobi.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.C3087t.C2969a;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.p118a.C3213c;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

/* compiled from: NativeAdUnit */
class aa extends AdUnit implements ActivityLifecycleCallbacks, C2969a {
    private static final String f6964b = aa.class.getSimpleName();
    private static final String f6965c = InMobiNative.class.getSimpleName();
    private Map<aa, WeakReference<View>> f6966d = new HashMap();
    private WeakHashMap<View, aa> f6967e = new WeakHashMap();
    private String f6968f;
    private String f6969g;
    private C3087t f6970h;
    private URL f6971i;
    private String f6972j;
    private int f6973k = 0;
    private long f6974l = 0;

    public aa(long j, C2905b c2905b) {
        super(C3105a.m10078b(), j, c2905b);
    }

    public aa(Activity activity, long j, C2905b c2905b) {
        super(activity, j, c2905b);
        activity.getApplication().registerActivityLifecycleCallbacks(this);
    }

    public void mo6146a(Context context) {
        super.mo6146a(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    public void mo6139A() {
        Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Fetching a Native ad for placement id: " + m8736b());
        if (this.f6974l != 0) {
            int g = m8775p().m9722g();
            if (SystemClock.elapsedRealtime() - this.f6974l < ((long) (g * 1000))) {
                m8724a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage("Ad cannot be refreshed before " + g + " seconds"), false);
                Logger.m10359a(InternalLogLevel.ERROR, f6965c, "Ad cannot be refreshed before " + g + " seconds (Placement Id = " + m8736b() + ").");
                return;
            }
        }
        this.f6974l = SystemClock.elapsedRealtime();
        super.mo6139A();
        this.f6970h = new C3087t(m8775p().m9728m(), new bd(m8775p().m9728m()), this);
    }

    protected boolean mo6140F() {
        if (AdState.STATE_LOADING == m8748c() || AdState.STATE_AVAILABLE == m8748c()) {
            m8724a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            Logger.m10359a(InternalLogLevel.ERROR, f6965c, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
            return true;
        } else if (m8748c() != AdState.STATE_ACTIVE) {
            return false;
        } else {
            m8724a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.m10359a(InternalLogLevel.ERROR, f6965c, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad");
            return true;
        }
    }

    protected void mo6150b(C2968a c2968a) {
    }

    public void mo6142S() {
        if (AdState.STATE_ATTACHED == m8748c()) {
            WeakReference weakReference = (WeakReference) this.f6966d.get(this);
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (this.f6970h != null && view != null) {
                    this.f6970h.m9987a(view, this, m8775p().m9728m().m9695a(), m8775p().m9728m().m9697b());
                    this.f6970h.m9988b();
                }
            }
        }
    }

    public void mo6143T() {
        if (AdState.STATE_ATTACHED == m8748c()) {
            WeakReference weakReference = (WeakReference) this.f6966d.get(this);
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (this.f6970h != null && view != null) {
                    this.f6970h.m9986a(view);
                    this.f6970h.m9985a();
                }
            }
        }
    }

    public boolean mo6148a(C2968a c2968a) {
        if (!super.mo6148a(c2968a)) {
            return false;
        }
        String str = "contextCode";
        str = FavaDiagnosticsEntity.EXTRA_NAMESPACE;
        try {
            JSONObject jSONObject = new JSONObject(c2968a.m9123c());
            this.f6968f = jSONObject.getString("contextCode");
            this.f6969g = jSONObject.getString(FavaDiagnosticsEntity.EXTRA_NAMESPACE);
            if (this.f6968f == null || this.f6968f.trim().length() == 0 || this.f6969g == null || this.f6969g.trim().length() == 0) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f6964b, "Exception while parsing ad.", e);
            return false;
        }
    }

    public void mo6149b(long j, @NonNull C2968a c2968a) {
        super.mo6149b(j, c2968a);
        if (j == m8736b() && AdState.STATE_AVAILABLE == m8748c()) {
            Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Native ad successfully fetched for placement id: " + m8736b());
            Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Started loading Native ad markup in the WebViewfor placement id: " + m8736b());
            try {
                m8713a(0, this.f6968f, null, null);
            } catch (Exception e) {
                m8706M();
                if (m8776q() != null) {
                    m8776q().mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                }
                Logger.m10359a(InternalLogLevel.ERROR, f6965c, "Failed to load ad; SDK encountered an unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, f6964b, "SDK encountered an unexpected error loading an ad; " + e.getMessage());
            }
        }
    }

    public Object mo6144U() {
        return m8769j();
    }

    public void m9142a(View view, URL url, String str) {
        View view2;
        Map hashMap = new HashMap();
        hashMap.put("customScript", str != null ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("customUrl", url != null ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        m8753c("ads", "TrackImpression", hashMap);
        WeakReference weakReference = (WeakReference) this.f6966d.get(this);
        if (weakReference != null) {
            view2 = (View) weakReference.get();
        } else {
            view2 = null;
        }
        if (!view.equals(view2)) {
            if (AdState.STATE_LOADED == m8748c() || AdState.STATE_ATTACHED == m8748c()) {
                m9140a(view2);
                m9140a(view);
                this.f6966d.put(this, new WeakReference(view));
                this.f6967e.put(view, this);
                this.f6971i = url;
                this.f6972j = str;
                this.f6970h.m9987a(view, this, m8775p().m9728m().m9695a(), m8775p().m9728m().m9697b());
                m8720a(AdState.STATE_ATTACHED);
                Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Binding view :<" + view + "> to Native ad for placement id:" + m8736b());
            } else if (m8748c() != AdState.STATE_RENDERED && m8748c() != AdState.STATE_ACTIVE) {
                Logger.m10359a(InternalLogLevel.ERROR, f6965c, "Please wait for the ad to finish loading before making a call to bind.");
            }
        }
    }

    public void m9140a(View view) {
        if (view != null && AdState.STATE_ATTACHED == m8748c()) {
            m8720a(AdState.STATE_LOADED);
            InMobiNative.sMappedAdUnits.remove(view);
            this.f6970h.m9986a(view);
            this.f6966d.remove(this);
            aa aaVar = (aa) this.f6967e.remove(view);
            if (aaVar != null) {
                aaVar.m8720a(AdState.STATE_LOADED);
                this.f6966d.remove(aaVar);
            }
            Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Unbinding Native ad from view :<" + view + "> for placement id: " + m8736b());
        }
    }

    public void mo6147a(View view, Object obj) {
        try {
            mo6145V();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, f6965c, "Failed to record impression on ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f6964b, "SDK encountered an unexpected error in recording ad impression; " + e.getMessage());
        }
    }

    public void mo6107b(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6964b, "Ad interaction. Params:" + hashMap.toString());
    }

    void mo6145V() {
        Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Viewable impression was recorded for Native ad with placement id: " + m8736b());
        if (AdState.STATE_ATTACHED == m8748c()) {
            m8720a(AdState.STATE_RENDERED);
            RenderView renderView = (RenderView) mo6167t();
            if (renderView != null) {
                renderView.m10635a(true);
                renderView.m10637b(this.f6969g + "recordEvent(18)");
                if (this.f6972j != null) {
                    renderView.m10637b(this.f6972j);
                }
                if (this.f6971i != null) {
                    C3213c.m10698a().m10713a(this.f6971i.toExternalForm(), true);
                }
                m8731a("ads", "ViewableBeaconFired");
                C2905b q = m8776q();
                if (q != null) {
                    q.mo6130g();
                }
            }
        }
    }

    void m9143a(Map<String, String> map, URL url, String str) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6964b, "Click record requested");
        Map hashMap = new HashMap();
        hashMap.put("customScript", str != null ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("customUrl", url != null ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        m8753c("ads", "ReportClick", hashMap);
        if (AdState.STATE_ATTACHED == m8748c() || AdState.STATE_RENDERED == m8748c()) {
            RenderView renderView = (RenderView) mo6167t();
            if (renderView != null) {
                renderView.m10652g();
                renderView.m10637b(m9130b((Map) map));
                if (str != null) {
                    renderView.m10637b(str);
                }
                if (url != null) {
                    C3213c.m10698a().m10713a(url.toExternalForm(), true);
                    return;
                }
                return;
            }
            return;
        }
        m8753c("ads", "InvalidClickReport", hashMap);
        Logger.m10359a(InternalLogLevel.ERROR, f6964b, "reportAdClick call made in wrong state");
    }

    private String m9130b(Map<String, String> map) {
        C3155d.m10405a((Map) map);
        if (map == null || map.isEmpty()) {
            return this.f6969g + "recordEvent(8)";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f6969g + "recordEvent(8, ");
        stringBuilder.append(new JSONObject(map).toString());
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

    void m9138W() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6964b, "Open landing page requested");
        try {
            C3135c.m10255a().m10279a(new C3110g("ads", "OpenLandingPage"));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6964b, "Error in submitting telemetry event : (" + e.getMessage() + ")");
        }
        if (AdState.STATE_RENDERED == m8748c() || AdState.STATE_ATTACHED == m8748c()) {
            String str = this.f6969g + "openLandingPage()";
            RenderView renderView = (RenderView) mo6167t();
            if (renderView != null) {
                renderView.m10637b(str);
            }
        }
    }

    protected void mo6141J() {
        m8774o();
        this.f6966d.clear();
        this.f6967e.clear();
        if (this.f6970h != null) {
            this.f6970h.m9991e();
        }
        this.f6972j = null;
        this.f6971i = null;
        super.mo6141J();
    }

    protected String mo6151d() {
        return "native";
    }

    protected String mo6152f() {
        return null;
    }

    protected PlacementType mo6153h() {
        return PlacementType.PLACEMENT_TYPE_INLINE;
    }

    public void mo6110c(RenderView renderView) {
        super.mo6110c(renderView);
        if (AdState.STATE_AVAILABLE == m8748c()) {
            m8706M();
            m8720a(AdState.STATE_LOADED);
            m8709P();
            Logger.m10359a(InternalLogLevel.DEBUG, f6965c, "Successfully loaded Native ad markup in WebView for placement id: " + m8736b());
            if (m8776q() != null) {
                m8776q().mo6119a();
            }
            m8701H();
        }
    }

    public void mo6113f(RenderView renderView) {
        super.mo6113f(renderView);
        if (AdState.STATE_RENDERED == m8748c() || AdState.STATE_ATTACHED == m8748c()) {
            this.f6973k++;
            m8720a(AdState.STATE_ACTIVE);
            if (m8776q() != null) {
                m8776q().mo6127d();
            }
        } else if (m8748c() == AdState.STATE_ACTIVE) {
            this.f6973k++;
        }
    }

    public void mo6114g(RenderView renderView) {
        super.mo6114g(renderView);
        if (AdState.STATE_ACTIVE == m8748c()) {
            this.f6973k--;
            if (this.f6973k == 0) {
                m8720a(AdState.STATE_RENDERED);
                m8731a("ads", "AdRendered");
                if (m8776q() != null) {
                    m8776q().mo6128e();
                }
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        Context a = m8711a();
        if (a != null && a.equals(activity)) {
            mo6142S();
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        Context a = m8711a();
        if (a != null && a.equals(activity)) {
            mo6143T();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        Context a = m8711a();
        if (a != null && a.equals(activity)) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }
}

package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdCreativeType;
import com.inmobi.ads.AdUnit.AdMarkupType;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.AdTrackerType;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.C3323a;
import com.mopub.mobileads.VastResourceXmlManager;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: InterstitialAdUnit */
class C3097w extends AdUnit {
    private static final String f7570b = C3097w.class.getSimpleName();
    private static final String f7571c = InMobiInterstitial.class.getSimpleName();
    private int f7572d;
    private boolean f7573e;
    private int f7574f;

    /* compiled from: InterstitialAdUnit */
    class C30932 implements Runnable {
        final /* synthetic */ C3097w f7566a;

        C30932(C3097w c3097w) {
            this.f7566a = c3097w;
        }

        public void run() {
            try {
                if (this.f7566a.mo6145V()) {
                    this.f7566a.m8760e("IllegalState");
                    return;
                }
                this.f7566a.m8720a(AdState.STATE_LOADING);
                super.mo6250B();
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.ERROR, C3097w.f7571c, "Unable to Prefetch ad; SDK encountered an unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, C3097w.f7570b, "Prefetch failed with unexpected error: " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
    }

    /* compiled from: InterstitialAdUnit */
    static final class C3094a {
        static final Map<Long, C3097w> f7567a = new HashMap();

        @NonNull
        static C3097w m10005a(int i, Context context, long j, C2905b c2905b) {
            if (f7567a.containsKey(Long.valueOf(j))) {
                C3097w c3097w = (C3097w) f7567a.get(Long.valueOf(j));
                c3097w.mo6146a(context);
                if (c2905b == null) {
                    return c3097w;
                }
                c3097w.m8721a(c2905b);
                if (c3097w.m8777r().get(Integer.valueOf(i)) != null) {
                    return c3097w;
                }
                c3097w.m8777r().put(Integer.valueOf(i), new WeakReference(c2905b));
                return c3097w;
            }
            C3097w c3097w2 = new C3097w(i, context, j, c2905b);
            f7567a.put(Long.valueOf(j), c3097w2);
            return c3097w2;
        }
    }

    /* compiled from: InterstitialAdUnit */
    private final class C3095b extends Exception {
        final /* synthetic */ C3097w f7568a;

        public C3095b(C3097w c3097w, String str) {
            this.f7568a = c3097w;
            super(str);
        }
    }

    /* compiled from: InterstitialAdUnit */
    private final class C3096c extends Exception {
        final /* synthetic */ C3097w f7569a;

        public C3096c(C3097w c3097w, String str) {
            this.f7569a = c3097w;
            super(str);
        }
    }

    private C3097w(int i, Context context, long j, C2905b c2905b) {
        super(context, j, c2905b);
        this.f7572d = 0;
        this.f7573e = false;
        this.f7574f = -1;
        if (c2905b != null) {
            m8777r().put(Integer.valueOf(i), new WeakReference(c2905b));
        }
    }

    @NonNull
    protected RenderView mo6245v() {
        RenderView v = super.mo6245v();
        if (this.f7573e) {
            v.mo6173a();
        }
        return v;
    }

    public void mo6139A() {
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            super.mo6139A();
        } catch (NoClassDefFoundError e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Some of the dependency libraries for Interstitial not found");
            m8746b("MissingDependency");
            C2905b q = m8776q();
            if (q != null) {
                q.mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES));
            }
        }
    }

    protected boolean mo6140F() {
        if (AdState.STATE_LOADING == m8748c()) {
            m8724a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            Logger.m10359a(InternalLogLevel.ERROR, f7571c, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + m8736b());
            return true;
        } else if (AdState.STATE_ACTIVE == m8748c() || AdState.STATE_RENDERED == m8748c()) {
            m8724a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
            Logger.m10359a(InternalLogLevel.ERROR, f7571c, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + m8736b());
            return true;
        } else if (AdState.STATE_AVAILABLE != m8748c()) {
            return false;
        } else {
            if (AdMarkupType.AD_MARKUP_TYPE_INM_HTML == m8770k()) {
                m8724a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
                Logger.m10359a(InternalLogLevel.ERROR, f7571c, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + m8736b());
                return true;
            }
            C2905b q = m8776q();
            if (q == null) {
                return true;
            }
            q.mo6122a((AdUnit) this, true);
            return true;
        }
    }

    protected int mo6251E() {
        if (AdState.STATE_READY != m8748c()) {
            return super.mo6251E();
        }
        if (m10008Y()) {
            return super.mo6251E();
        }
        return 1;
    }

    private boolean m10008Y() {
        try {
            if (m8770k() == AdMarkupType.AD_MARKUP_TYPE_INM_HTML) {
                m10009Z();
                return false;
            } else if (!m10014b(true)) {
                return true;
            } else {
                m10009Z();
                return false;
            }
        } catch (C3095b e) {
            return true;
        } catch (C3096c e2) {
            return true;
        }
    }

    private void m10009Z() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "An ad is ready with the ad unit. Signaling ad load success ...");
        C2905b q = m8776q();
        if (q != null) {
            q.mo6122a((AdUnit) this, true);
            q.mo6120a((AdUnit) this);
        }
    }

    public void m10029a(int i, C2905b c2905b) {
        m8777r().put(Integer.valueOf(i), new WeakReference(c2905b));
    }

    protected Map<String, String> mo6166i() {
        Map hashMap = new HashMap();
        hashMap.put("preload-request", m8700G() ? String.valueOf(1) : String.valueOf(0));
        return hashMap;
    }

    public boolean mo6148a(C2968a c2968a) {
        if (super.mo6148a(c2968a)) {
            if (c2968a instanceof bq) {
                bq bqVar = (bq) c2968a;
                C3029b b = C3067l.m9879a().m9884b(bqVar.m9583j());
                if (b == null || !b.m9469d()) {
                    return false;
                }
                m8725a(new bo(b.m9465a(), bqVar.m9584k(), bqVar.m9585l(), bqVar.m9586m(), bqVar.m9587n(), m8775p().m9731p()));
            }
            return true;
        }
        mo6150b(c2968a);
        return false;
    }

    protected void mo6150b(C2968a c2968a) {
        m8778s().m9810a(c2968a);
    }

    protected void mo6141J() {
        super.mo6141J();
        this.f7574f = -1;
    }

    public void m10037c(final int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Please ensure that you call show() on the UI thread");
        } else if (!mo6143T()) {
            Logger.m10359a(InternalLogLevel.ERROR, f7570b, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling show.");
            m8757d("ShowIntBeforeReady");
            Map r = m8777r();
            for (Integer intValue : r.keySet()) {
                C2905b c2905b = (C2905b) ((WeakReference) r.get(Integer.valueOf(intValue.intValue()))).get();
                if (c2905b != null) {
                    c2905b.mo6124b();
                }
            }
        } else if (AdMarkupType.AD_MARKUP_TYPE_INM_HTML == m8770k()) {
            m10015d(i);
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Show requested by client ID (" + i + ")");
            new Thread(new Runnable(this) {
                final /* synthetic */ C3097w f7565b;

                /* compiled from: InterstitialAdUnit */
                class C30911 implements Runnable {
                    final /* synthetic */ C30921 f7563a;

                    /* compiled from: InterstitialAdUnit */
                    class C30901 implements Runnable {
                        final /* synthetic */ C30911 f7562a;

                        C30901(C30911 c30911) {
                            this.f7562a = c30911;
                        }

                        public void run() {
                            this.f7562a.f7563a.f7565b.m10015d(i);
                        }
                    }

                    C30911(C30921 c30921) {
                        this.f7563a = c30921;
                    }

                    public void run() {
                        this.f7563a.f7565b.m8713a(i, this.f7563a.f7565b.m8769j(), new C30901(this), Looper.getMainLooper());
                    }
                }

                public void run() {
                    try {
                        if (this.f7565b.m10014b(false)) {
                            new Handler(Looper.getMainLooper()).post(new C30911(this));
                            return;
                        }
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to Show Ad, canShowAd Failed");
                        this.f7565b.aa();
                    } catch (C3095b e) {
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e.getMessage());
                        this.f7565b.aa();
                    } catch (C3096c e2) {
                        Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e2.getMessage());
                        this.f7565b.aa();
                    }
                }
            }).start();
        }
    }

    private void m10015d(int i) {
        C2905b c2905b;
        m8731a("ads", "ShowInt");
        if (m8778s().m9813a(m8736b(), mo6152f(), mo6259x())) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Cache can still serve ads for placement ID (" + m8736b() + ")");
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "No more ads for placement ID (" + m8736b() + ") in cache");
            Iterator it = m8777r().keySet().iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                if (!(intValue == i || m8777r().get(Integer.valueOf(intValue)) == null)) {
                    c2905b = (C2905b) ((WeakReference) m8777r().get(Integer.valueOf(intValue))).get();
                    if (c2905b != null) {
                        c2905b.mo6122a((AdUnit) this, false);
                    }
                    it.remove();
                }
            }
        }
        if (mo6142S()) {
            m8720a(AdState.STATE_RENDERED);
            c2905b = m8712a(i);
            if (c2905b != null) {
                c2905b.mo6126c();
                return;
            }
            return;
        }
        m8720a(AdState.STATE_FAILED);
        c2905b = m8712a(i);
        if (c2905b != null) {
            c2905b.mo6124b();
        }
    }

    public void m10028a(int i, int i2, int i3) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            AdContainer t = mo6167t();
            if (t != null && m8711a() != null) {
                try {
                    m8711a().getResources().getAnimation(i2);
                    m8711a().getResources().getAnimation(i3);
                    this.f7574f = i2;
                    t.setExitAnimation(i3);
                } catch (Throwable e) {
                    Logger.m10360a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "The supplied resource ID with show for animations is invalid", e);
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
                m10037c(i);
                return;
            }
            return;
        }
        Logger.m10359a(InternalLogLevel.ERROR, f7571c, "Please ensure that you call show() on the UI thread");
    }

    boolean mo6142S() {
        try {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, ">>> Starting " + InMobiAdActivity.class.getSimpleName() + " to display interstitial ad ...");
            AdContainer t = mo6167t();
            if (t == null || AdMarkupType.AD_MARKUP_TYPE_UNKNOWN == t.getMarkupType()) {
                return false;
            }
            int a = InMobiAdActivity.m10547a(t);
            Intent intent = new Intent(m8711a(), InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", AdMarkupType.AD_MARKUP_TYPE_INM_HTML == m8770k() ? 200 : 201);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
            C3105a.m10072a(m8711a(), intent);
            if (!(m8711a() == null || !(m8711a() instanceof Activity) || this.f7574f == -1)) {
                ((Activity) m8711a()).overridePendingTransition(this.f7574f, 0);
            }
            return true;
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Cannot show ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Encountered unexpected error while showing ad: " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            return false;
        }
    }

    public boolean mo6143T() {
        return m8748c() == AdState.STATE_READY;
    }

    void mo6144U() {
        AdContainer t = mo6167t();
        if (t != null) {
            this.f7573e = true;
            t.mo6173a();
        }
    }

    protected String mo6151d() {
        return SchemaSymbols.ATTVAL_INT;
    }

    protected String mo6152f() {
        return null;
    }

    protected PlacementType mo6153h() {
        return PlacementType.PLACEMENT_TYPE_FULLSCREEN;
    }

    @UiThread
    public void mo6149b(long j, @NonNull C2968a c2968a) {
        try {
            super.mo6149b(j, c2968a);
            Logger.m10359a(InternalLogLevel.DEBUG, f7571c, "Interstitial ad successfully fetched for placement id: " + m8736b());
            if (j == m8736b() && m8748c() == AdState.STATE_AVAILABLE) {
                boolean h = m8775p().m9728m().m9703h();
                for (bi biVar : m8764g()) {
                    if (h && AdTrackerType.AD_TRACKER_TYPE_IAS == biVar.f7267a) {
                        try {
                            C3323a a = C3078q.m9949a(m8711a(), true, (AdCreativeType) biVar.f7268b.get(VastResourceXmlManager.CREATIVE_TYPE), mo6245v());
                            if (a != null) {
                                biVar.f7268b.put("avidAdSession", a);
                                biVar.f7268b.put("deferred", Boolean.valueOf(true));
                                Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "AVID ad session created and WebView container registered with AVID");
                            } else {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Ignoring IAS meta data for this ad markup");
                            }
                        } catch (Throwable e) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Setting up impression tracking for IAS encountered an unexpected error: " + e.getMessage());
                            C3135c.m10255a().m10279a(new C3132b(e));
                        }
                    }
                }
                try {
                    m8713a(0, m8769j(), null, null);
                } catch (Throwable e2) {
                    m8706M();
                    if (m8776q() != null) {
                        m8776q().mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                    }
                    Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Loading ad markup into container encountered an unexpected error: " + e2.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e2));
                }
            }
        } catch (Throwable e22) {
            Logger.m10359a(InternalLogLevel.ERROR, f7571c, "Unable to load ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Handling ad fetch successful encountered an unexpected error: " + e22.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e22));
        }
    }

    @UiThread
    public void mo6257b(long j, boolean z, C2968a c2968a, long j2) {
        try {
            super.mo6257b(j, z, c2968a, j2);
            if (j != m8736b()) {
                return;
            }
            C2905b q;
            if (z) {
                if (AdState.STATE_LOADING == m8748c()) {
                    m8720a(AdState.STATE_AVAILABLE);
                    q = m8776q();
                    if (super.mo6148a(c2968a)) {
                        m8750c(c2968a);
                        if (q != null) {
                            q.mo6122a((AdUnit) this, true);
                        }
                    } else if (q != null) {
                        q.mo6122a((AdUnit) this, false);
                    }
                }
            } else if (AdState.STATE_LOADED == m8748c() || AdState.STATE_READY == m8748c() || AdState.STATE_AVAILABLE == m8748c()) {
                m8720a(AdState.STATE_CREATED);
                q = m8776q();
                if (q != null) {
                    q.mo6122a((AdUnit) this, false);
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Handling ad availability change event encountered an unexpected error: " + e.getMessage());
        }
    }

    public void mo6164b(long j, boolean z) {
        super.mo6164b(j, z);
        if (z) {
            if (j == m8736b() && AdState.STATE_AVAILABLE == m8748c()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Setting state to READY");
                m8720a(AdState.STATE_READY);
                for (WeakReference weakReference : m8777r().values()) {
                    if (!(weakReference == null || weakReference.get() == null)) {
                        ((C2905b) weakReference.get()).mo6120a((AdUnit) this);
                    }
                }
            }
        } else if (j != m8736b()) {
        } else {
            if (AdState.STATE_AVAILABLE == m8748c() || AdState.STATE_READY == m8748c()) {
                m8720a(AdState.STATE_CREATED);
                for (WeakReference weakReference2 : m8777r().values()) {
                    if (!(weakReference2 == null || weakReference2.get() == null)) {
                        ((C2905b) weakReference2.get()).mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
                    }
                }
            }
        }
    }

    public void mo6252K() {
        super.mo6252K();
        if (m8748c() == AdState.STATE_LOADED) {
            m8706M();
            m8720a(AdState.STATE_READY);
            m8709P();
            Logger.m10359a(InternalLogLevel.DEBUG, f7571c, "Successfully loaded Interstitial ad markup in the WebView for placement id: " + m8736b());
            m8701H();
            C2905b q = m8776q();
            if (q != null) {
                q.mo6119a();
                q.mo6120a((AdUnit) this);
            }
        }
    }

    public void mo6110c(RenderView renderView) {
        super.mo6110c(renderView);
        if (m8748c() == AdState.STATE_AVAILABLE) {
            m8720a(AdState.STATE_LOADED);
            C2905b q = m8776q();
            if (q != null) {
                q.mo6122a((AdUnit) this, true);
            }
        }
    }

    public void mo6253L() {
        super.mo6253L();
        if (m8748c() == AdState.STATE_LOADED) {
            m8706M();
            m8720a(AdState.STATE_FAILED);
            Logger.m10359a(InternalLogLevel.DEBUG, f7571c, "Failed to load the Interstitial markup in the WebView for placement id: " + m8736b());
            C2905b q = m8776q();
            if (q != null) {
                q.mo6122a((AdUnit) this, false);
            }
        }
    }

    public synchronized void mo6113f(RenderView renderView) {
        super.mo6113f(renderView);
        if (m8748c() == AdState.STATE_RENDERED) {
            this.f7572d++;
            if (this.f7572d == 1) {
                m8731a("ads", "AdRendered");
                Logger.m10359a(InternalLogLevel.DEBUG, f7571c, "Successfully displayed Interstitial for placement id: " + m8736b());
                for (WeakReference weakReference : m8777r().values()) {
                    if (!(weakReference == null || weakReference.get() == null)) {
                        ((C2905b) weakReference.get()).mo6127d();
                    }
                }
            } else {
                m8720a(AdState.STATE_ACTIVE);
            }
        } else if (m8748c() == AdState.STATE_ACTIVE) {
            this.f7572d++;
        }
    }

    public synchronized void mo6114g(RenderView renderView) {
        super.mo6114g(renderView);
        if (m8748c() == AdState.STATE_ACTIVE) {
            this.f7572d--;
            if (this.f7572d == 1) {
                m8720a(AdState.STATE_RENDERED);
            }
        } else if (m8748c() == AdState.STATE_RENDERED) {
            this.f7572d--;
            m8731a("ads", "IntClosed");
            mo6141J();
            Logger.m10359a(InternalLogLevel.DEBUG, f7571c, "Interstitial ad dismissed for placement id: " + m8736b());
            for (WeakReference weakReference : m8777r().values()) {
                if (!(weakReference == null || weakReference.get() == null)) {
                    ((C2905b) weakReference.get()).mo6128e();
                }
            }
        }
    }

    public void mo6254O() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7570b, "Renderview timed out.");
        m8752c("RenderTimeOut");
        if (AdState.STATE_LOADED == m8748c() || AdState.STATE_AVAILABLE == m8748c()) {
            m8720a(AdState.STATE_FAILED);
            Logger.m10359a(InternalLogLevel.DEBUG, f7570b, "Failed to load the Interstitial markup in the webview due to time out for placement id: " + m8736b());
            C2905b q = m8776q();
            if (q != null) {
                q.mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        }
    }

    private boolean m10014b(boolean z) throws C3095b, C3096c {
        C2968a a;
        if (z) {
            a = m8778s().m9807a(m8736b(), mo6151d(), mo6152f(), m8775p().m9714a(mo6151d()).m9627e(), mo6259x());
        } else {
            a = m8778s().m9806a(m8736b(), mo6151d(), mo6152f(), m8775p().m9714a(mo6151d()).m9626d(), m8775p().m9714a(mo6151d()).m9627e(), mo6259x());
        }
        if (a == null) {
            throw new C3095b(this, "No Cached Ad found for AdUnit");
        } else if (mo6148a(a)) {
            return true;
        } else {
            throw new C3096c(this, "No Cached Asset for AdUnit");
        }
    }

    private void aa() {
        m8720a(AdState.STATE_CREATED);
        Map r = m8777r();
        for (Integer intValue : r.keySet()) {
            C2905b c2905b = (C2905b) ((WeakReference) r.get(Integer.valueOf(intValue.intValue()))).get();
            if (c2905b != null) {
                c2905b.mo6124b();
            }
        }
    }

    public void mo6255a(MonetizationContext monetizationContext) {
        super.mo6255a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    public MonetizationContext mo6259x() {
        return MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
    }

    protected boolean mo6145V() {
        if (AdState.STATE_LOADING == m8748c()) {
            mo6109c(m8736b(), new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING));
            Logger.m10359a(InternalLogLevel.ERROR, f7571c, "An ad prefetch is already in progress. Please wait for the prefetch to complete before requesting for another ad for placement id: " + m8736b());
            return true;
        } else if (AdState.STATE_ACTIVE == m8748c() || AdState.STATE_RENDERED == m8748c()) {
            mo6109c(m8736b(), new InMobiAdRequestStatus(StatusCode.AD_ACTIVE));
            Logger.m10359a(InternalLogLevel.ERROR, f7571c, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + m8736b());
            return true;
        } else {
            if (AdState.STATE_AVAILABLE == m8748c()) {
                if (AdMarkupType.AD_MARKUP_TYPE_INM_HTML == m8770k()) {
                    mo6109c(m8736b(), new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING));
                    Logger.m10359a(InternalLogLevel.ERROR, f7571c, "An ad load is already in progress. Please wait for the load to complete before requesting prefetch for another ad for placement id: " + m8736b());
                    return true;
                } else if (AdMarkupType.AD_MARKUP_TYPE_INM_JSON == m8770k()) {
                    mo6097a(m8736b());
                    return true;
                }
            }
            if (AdState.STATE_READY != m8748c() && AdState.STATE_PREFETCHED != m8748c()) {
                return false;
            }
            mo6097a(m8736b());
            return true;
        }
    }

    void mo6250B() {
        new Handler(Looper.getMainLooper()).post(new C30932(this));
    }

    public void mo6256b(long j) {
        m8720a(AdState.STATE_PREFETCHED);
        if (m8710Q() != null) {
            m8710Q().mo6116a(this);
        }
    }

    public void mo6258d(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
        m8720a(AdState.STATE_FAILED);
        if (m8710Q() != null) {
            m8710Q().mo6117a(this, inMobiAdRequestStatus);
        }
    }
}

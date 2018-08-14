package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.C3097w.C3094a;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: AdUnitCache */
public final class C3063j implements C2911b {
    private static final String f7461a = C3063j.class.getSimpleName();
    private static final Object f7462b = new Object();
    private static volatile C3063j f7463c;
    private static ConcurrentHashMap<bb, AdUnit> f7464d;
    private static C3046c f7465e;

    /* compiled from: AdUnitCache */
    class C30571 implements ComponentCallbacks2 {
        final /* synthetic */ C3063j f7453a;

        C30571(C3063j c3063j) {
            this.f7453a = c3063j;
        }

        public void onTrimMemory(int i) {
            if (i == 15) {
                this.f7453a.m9867h();
            }
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
        }
    }

    /* compiled from: AdUnitCache */
    class C30582 implements ComponentCallbacks {
        final /* synthetic */ C3063j f7454a;

        C30582(C3063j c3063j) {
            this.f7454a = c3063j;
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
            this.f7454a.m9867h();
        }
    }

    /* compiled from: AdUnitCache */
    class C30593 implements Runnable {
        final /* synthetic */ C3063j f7455a;

        C30593(C3063j c3063j) {
            this.f7455a = c3063j;
        }

        public void run() {
            try {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "Flushing ad unit cache due to low memory.");
                Iterator it = C3063j.f7464d.entrySet().iterator();
                while (it.hasNext()) {
                    ((AdUnit) ((Entry) it.next()).getValue()).mo6141J();
                    it.remove();
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "SDK encountered unexpected error in flushing ad unit cache; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
    }

    /* compiled from: AdUnitCache */
    private static class C3062a implements C2905b {
        private bb f7460a;

        C3062a(bb bbVar) {
            this.f7460a = bbVar;
        }

        public void mo6122a(AdUnit adUnit, boolean z) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "onAdReceived called with: " + z);
        }

        public void mo6120a(AdUnit adUnit) {
        }

        public void mo6119a() {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "onAdLoadSucceeded called");
        }

        public void mo6121a(AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "onAdLoadFailed called. Status:" + inMobiAdRequestStatus.getMessage());
            AdUnit adUnit2 = (AdUnit) C3063j.f7464d.get(this.f7460a);
            C3063j.f7464d.remove(this.f7460a);
            if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
                adUnit2.m8731a("ads", "PreLoadServerNoFill");
            }
        }

        public void mo6124b() {
        }

        public void mo6126c() {
        }

        public void mo6127d() {
        }

        public void mo6128e() {
        }

        public void mo6123a(@NonNull Map<Object, Object> map) {
        }

        public void mo6129f() {
        }

        public void mo6125b(@NonNull Map<Object, Object> map) {
        }

        public void mo6130g() {
        }
    }

    public static C3063j m9855a() {
        C3063j c3063j = f7463c;
        if (c3063j == null) {
            synchronized (f7462b) {
                c3063j = f7463c;
                if (c3063j == null) {
                    c3063j = new C3063j();
                    f7463c = c3063j;
                }
            }
        }
        return c3063j;
    }

    private C3063j() {
        f7464d = new ConcurrentHashMap(8, 0.9f, 3);
        f7465e = new C3046c();
        C3121b.m10178a().m10190a(f7465e, (C2911b) this);
        m9866g();
        C3135c.m10255a().m10281a(f7465e.mo6231a(), f7465e.m9729n());
    }

    @TargetApi(14)
    private void m9866g() {
        Application application = (Application) C3105a.m10078b();
        if (application != null) {
            if (VERSION.SDK_INT >= 14) {
                application.registerComponentCallbacks(new C30571(this));
            } else {
                application.registerComponentCallbacks(new C30582(this));
            }
        }
    }

    private void m9867h() {
        Context b = C3105a.m10078b();
        if (b != null) {
            new Handler(b.getMainLooper()).post(new C30593(this));
        }
    }

    public void mo6102a(C3045a c3045a) {
        f7465e = (C3046c) c3045a;
        C3135c.m10255a().m10281a(f7465e.mo6231a(), f7465e.m9729n());
    }

    public void m9875b() {
        m9868i();
        m9869j();
        m9870k();
    }

    public void m9877c() {
        m9868i();
        m9869j();
    }

    AdUnit m9873a(bb bbVar) {
        if (f7465e.m9730o().m9654b()) {
            m9862c(bbVar);
            AdUnit adUnit = (AdUnit) f7464d.get(bbVar);
            if (adUnit == null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "No cached ad unit found for pid:" + bbVar.m9475c() + " tp:" + bbVar.m9476d());
                return null;
            } else if (m9861b(adUnit)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "Expired cached ad unit found for pid:" + bbVar.m9475c() + " tp:" + bbVar.m9476d());
                adUnit.mo6141J();
                f7464d.remove(bbVar);
                m9859a("AdUnitExpired", adUnit);
                return null;
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "Cached ad unit found for pid:" + bbVar.m9475c() + " tp:" + bbVar.m9476d());
                AdUnit adUnit2 = (AdUnit) f7464d.get(bbVar);
                f7464d.remove(bbVar);
                m9857a(adUnit2);
                return adUnit;
            }
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "No cached ad unit found as config is disabled. pid:" + bbVar.m9475c() + " tp:" + bbVar.m9476d());
        return null;
    }

    private void m9857a(AdUnit adUnit) {
        Map hashMap = new HashMap();
        hashMap.put("type", adUnit.mo6151d());
        hashMap.put("plId", Long.valueOf(adUnit.m8736b()));
        hashMap.put("clientRequestId", adUnit.m8773n());
    }

    private void m9859a(String str, AdUnit adUnit) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        hashMap.put("type", adUnit.mo6151d());
        hashMap.put("plId", Long.valueOf(adUnit.m8736b()));
        hashMap.put("clientRequestId", adUnit.m8773n());
    }

    private void m9862c(bb bbVar) {
        List arrayList = new ArrayList();
        arrayList.add(bbVar);
        int a = bc.m9478a().m9480a(arrayList, f7465e.m9730o().m9655c());
        AdUnit adUnit = (AdUnit) f7464d.get(bbVar);
        if (a > 0) {
            Map hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(a));
            adUnit.m8753c("ads", "PreLoadPidOverflow", hashMap);
        }
    }

    private void m9868i() {
        if (f7465e.m9730o().m9654b()) {
            int a = bc.m9478a().m9479a(f7465e.m9730o().m9653a());
            if (a > 0) {
                try {
                    Map hashMap = new HashMap();
                    hashMap.put("type", SchemaSymbols.ATTVAL_INT);
                    hashMap.put("count", Integer.valueOf(a));
                    C3135c.m10255a().m10280a("ads", "PreLoadPidExpiry", hashMap);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                }
            }
        }
    }

    private void m9869j() {
        Iterator it = f7464d.entrySet().iterator();
        while (it.hasNext()) {
            try {
                Entry entry = (Entry) it.next();
                final AdUnit adUnit = (AdUnit) entry.getValue();
                if (m9861b(adUnit)) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "cleanUpExpiredCachedAdUnits. pid:" + ((bb) entry.getKey()).m9475c() + " tp:" + ((bb) entry.getKey()).m9476d());
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ C3063j f7457b;

                        public void run() {
                            try {
                                adUnit.mo6141J();
                            } catch (Throwable e) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "Encountered an unexpected error clearing the ad unit: " + e.getMessage());
                                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered an unexpected error clearing an old ad");
                                C3135c.m10255a().m10279a(new C3132b(e));
                            }
                        }
                    });
                    it.remove();
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "SDK encountered an unexpected error in expiring ad units; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
                return;
            }
        }
    }

    private boolean m9861b(AdUnit adUnit) {
        if (adUnit.m8748c() == AdState.STATE_LOADING) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "hasAdUnitExpired. Ad unit in loading state.");
            return false;
        } else if (System.nanoTime() - adUnit.m8771l() > ((f7465e.m9714a(adUnit.mo6151d()).m9627e() * 1000) * 1000) * 1000) {
            return true;
        } else {
            return false;
        }
    }

    private void m9870k() {
        if (f7465e.m9730o().m9654b()) {
            ArrayList arrayList = (ArrayList) m9872m();
            for (int i = 0; i < arrayList.size(); i++) {
                m9876b((bb) arrayList.get(i));
            }
        }
    }

    public void m9876b(final bb bbVar) {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ C3063j f7459b;

            public void run() {
                try {
                    this.f7459b.m9871l();
                    if (C3063j.f7465e.m9730o().m9654b() && !C3063j.f7464d.containsKey(bbVar)) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "preLoadAdUnit. pid:" + bbVar.m9475c() + " tp:" + bbVar.m9476d());
                        if (bbVar.m9471a() == null && bbVar.m9476d() != null) {
                            Map hashMap = new HashMap();
                            hashMap.put("tp", bbVar.m9476d());
                            bbVar.m9473a(hashMap);
                        }
                        C3097w a = C3094a.m10005a(hashCode(), C3105a.m10078b(), bbVar.m9475c(), new C3062a(bbVar));
                        a.m8730a(bbVar.m9474b());
                        a.m8733a(bbVar.m9471a());
                        a.m8734a(true);
                        a.mo6255a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
                        C3063j.f7464d.put(bbVar, a);
                        a.mo6139A();
                    }
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3063j.f7461a, "SDK encountered an unexpected error preloading ad units; " + e.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        });
    }

    private void m9871l() {
        if (f7464d.size() >= f7465e.m9730o().m9655c()) {
            ArrayList arrayList = (ArrayList) bc.m9478a().m9481b();
            Iterator it = f7464d.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (!arrayList.contains(entry.getKey())) {
                    ((AdUnit) entry.getValue()).mo6141J();
                    it.remove();
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7461a, "Removing extra ad unit from ad unit cache. Pid:" + ((bb) entry.getKey()).m9475c() + " tp:" + ((bb) entry.getKey()).m9476d());
                }
            }
        }
    }

    public static String m9856a(Map<String, String> map) {
        return map == null ? null : (String) map.get("tp");
    }

    private List<bb> m9872m() {
        return bc.m9478a().m9481b();
    }
}

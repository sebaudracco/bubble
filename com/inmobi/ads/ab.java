package com.inmobi.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.C2968a.C2965a;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.ai.C3003a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3152a;
import com.inmobi.commons.core.utilities.C3152a.C2982b;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.C3158f;
import com.inmobi.commons.core.utilities.C3158f.C2983b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* compiled from: NativeStrandAdCache */
final class ab implements ActivityLifecycleCallbacks, C2905b, C2982b, C2983b {
    private static final String f7004a = ab.class.getSimpleName();
    private static final Map<Context, ab> f7005i = new WeakHashMap();
    private static ExecutorService f7006j;
    @NonNull
    private final WeakReference<Context> f7007b;
    @NonNull
    private C2978c f7008c;
    private boolean f7009d;
    private boolean f7010e;
    @NonNull
    private final Handler f7011f;
    @NonNull
    private final Map<C2974b, List<NativeV2DataModel>> f7012g = Collections.synchronizedMap(new LinkedHashMap());
    @NonNull
    private final Map<C2974b, WeakReference<C2927a>> f7013h = new HashMap();

    /* compiled from: NativeStrandAdCache */
    interface C2927a {
        void mo6131a();

        void mo6132a(boolean z, InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    /* compiled from: NativeStrandAdCache */
    static final class C2974b {
        final int f6985a;
        final long f6986b;
        final ac f6987c;

        C2974b(int i, long j, ac acVar) {
            this.f6985a = i;
            this.f6986b = j;
            this.f6987c = acVar;
        }
    }

    /* compiled from: NativeStrandAdCache */
    private enum C2978c {
        STARTED("Started") {
            void mo6154a(final ab abVar, @Nullable final C2974b c2974b) {
                ab.f7006j.execute(new Runnable(this) {
                    final /* synthetic */ C29791 f6990c;

                    public void run() {
                        Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Entering state:STARTED");
                        abVar.f7008c = C2978c.STARTED;
                        if (c2974b != null) {
                            ac acVar = c2974b.f6987c;
                            try {
                                int a = C3047d.m9733a().m9735a(acVar.mo6151d(), acVar.m8775p().m9714a(acVar.mo6151d()).m9627e());
                                if (a > 0) {
                                    Map hashMap = new HashMap();
                                    hashMap.put("count", Integer.valueOf(a));
                                    acVar.mo6105a("ads", "AdCacheAdExpired", hashMap);
                                }
                                if (abVar.f7009d) {
                                    this.f6990c.m9163e(abVar, c2974b);
                                }
                            } catch (Throwable e) {
                                C3135c.m10255a().m10279a(new C3132b(e));
                                Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, e.getMessage());
                            }
                        }
                    }
                });
            }

            private void m9163e(final ab abVar, final C2974b c2974b) {
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ C29791 f6993c;

                    public void run() {
                        try {
                            this.f6993c.mo6160c(abVar, c2974b);
                        } catch (Throwable e) {
                            C3135c.m10255a().m10279a(new C3132b(e));
                            Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, e.getMessage());
                        }
                    }
                });
            }

            void mo6158b(ab abVar, C2974b c2974b) {
                a.mo6154a(abVar, c2974b);
            }

            void mo6160c(ab abVar, @Nullable C2974b c2974b) {
                if (c2974b != null) {
                    ac acVar = c2974b.f6987c;
                    if (abVar.f7012g.get(c2974b) != null) {
                        acVar.mo6139A();
                    }
                }
            }

            ai mo6161d(ab abVar, @Nullable C2974b c2974b) {
                if (c2974b == null) {
                    return null;
                }
                ai aiVar;
                ac acVar = c2974b.f6987c;
                if (acVar.m8711a() == null || ((List) abVar.f7012g.get(c2974b)).size() <= 0) {
                    aiVar = null;
                } else {
                    synchronized (abVar.f7012g) {
                        List list = (List) abVar.f7012g.get(c2974b);
                        if (list == null || list.isEmpty()) {
                            aiVar = null;
                        } else {
                            NativeV2DataModel nativeV2DataModel = (NativeV2DataModel) list.remove(0);
                            abVar.f7012g.put(c2974b, list);
                            list = nativeV2DataModel.m9072a(AssetType.ASSET_TYPE_VIDEO);
                            if (!(list == null || list.isEmpty())) {
                                C3029b c = C3067l.m9879a().m9888c(((aw) list.get(0)).m9441D().mo6219b());
                                if (c == null || !c.m9469d()) {
                                    acVar.mo6165g("NoCachedVideoAsset");
                                    return null;
                                }
                            }
                            ai a = C3003a.m9299a(acVar.m8711a(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), nativeV2DataModel, acVar.m8772m(), acVar.m8773n(), acVar.m8764g());
                            if (a == null) {
                                acVar.mo6165g("DataModelValidationFailed");
                                aiVar = a;
                            } else {
                                acVar.m9235a(a);
                                WeakReference weakReference = (WeakReference) abVar.f7013h.get(c2974b);
                                if (weakReference != null) {
                                    C2927a c2927a = (C2927a) weakReference.get();
                                    if (c2927a != null) {
                                        c2927a.mo6131a();
                                    }
                                }
                                aiVar = a;
                            }
                        }
                    }
                }
                return aiVar;
            }

            void mo6156a(ab abVar, C2974b c2974b, AdUnit adUnit, JSONObject jSONObject) {
                if (c2974b != null && adUnit != null && jSONObject != null) {
                    final JSONObject jSONObject2 = jSONObject;
                    final AdUnit adUnit2 = adUnit;
                    final ab abVar2 = abVar;
                    final C2974b c2974b2 = c2974b;
                    new Thread(new Runnable(this) {
                        final /* synthetic */ C29791 f6998e;

                        public void run() {
                            NativeV2DataModel nativeV2DataModel = new NativeV2DataModel(PlacementType.PLACEMENT_TYPE_INLINE, jSONObject2, adUnit2.m8775p().m9731p(), null);
                            try {
                                if (!nativeV2DataModel.m9094n()) {
                                    ab.m9189d(abVar2, c2974b2, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                                } else if (abVar2.f7012g.get(c2974b2) != null) {
                                    List list = (List) abVar2.f7012g.get(c2974b2);
                                    for (NativeV2Asset nativeV2Asset : nativeV2DataModel.m9072a(AssetType.ASSET_TYPE_VIDEO)) {
                                        aw awVar = (aw) nativeV2Asset;
                                        awVar.m9441D().mo6218a(C3067l.m9879a().m9884b(awVar.m9441D().mo6219b()).m9465a());
                                    }
                                    synchronized (list) {
                                        list.add(nativeV2DataModel);
                                    }
                                    ab.m9188c(abVar2, c2974b2, new InMobiAdRequestStatus(StatusCode.NO_ERROR));
                                }
                            } catch (Throwable e) {
                                C3135c.m10255a().m10279a(new C3132b(e));
                                ab.m9189d(abVar2, c2974b2, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                            }
                        }
                    }).start();
                }
            }

            void mo6155a(ab abVar, C2974b c2974b, AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
                if (c2974b != null && adUnit != null) {
                    WeakReference weakReference = (WeakReference) abVar.f7013h.get(c2974b);
                    if (weakReference != null) {
                        C2927a c2927a = (C2927a) weakReference.get();
                        if (c2927a != null) {
                            c2927a.mo6132a(false, inMobiAdRequestStatus);
                        }
                    }
                }
            }

            void mo6157a(ab abVar, boolean z) {
                if (!z) {
                    C2978c.PAUSED.mo6154a(abVar, null);
                    Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "App went to background; stopping cache replenish handler");
                }
            }

            void mo6159b(ab abVar, boolean z) {
                if (!z) {
                    C2978c.PAUSED.mo6154a(abVar, null);
                    Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Connectivity lost; stopping cache replenish handler");
                }
            }
        },
        PAUSED("Paused") {
            void mo6154a(ab abVar, C2974b c2974b) {
                Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Entering state:" + this);
                abVar.f7008c = C2978c.PAUSED;
            }

            void mo6158b(ab abVar, C2974b c2974b) {
                if (!abVar.f7009d) {
                    ab.m9189d(abVar, c2974b, new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE));
                }
            }

            ai mo6161d(ab abVar, @Nullable C2974b c2974b) {
                if (c2974b == null) {
                    return null;
                }
                ai aiVar;
                ac acVar = c2974b.f6987c;
                if (acVar.m8711a() == null || ((List) abVar.f7012g.get(c2974b)).size() <= 0) {
                    aiVar = null;
                } else {
                    synchronized (abVar.f7012g) {
                        List list = (List) abVar.f7012g.get(c2974b);
                        if (list == null || list.isEmpty()) {
                            aiVar = null;
                        } else {
                            NativeV2DataModel nativeV2DataModel = (NativeV2DataModel) list.remove(0);
                            abVar.f7012g.put(c2974b, list);
                            list = nativeV2DataModel.m9072a(AssetType.ASSET_TYPE_VIDEO);
                            if (!(list == null || list.isEmpty())) {
                                C3029b c = C3067l.m9879a().m9888c(((aw) list.get(0)).m9441D().mo6219b());
                                if (c == null || !c.m9469d()) {
                                    acVar.mo6165g("NoCachedVideoAsset");
                                    return null;
                                }
                            }
                            ai a = C3003a.m9299a(acVar.m8711a(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), nativeV2DataModel, acVar.m8772m(), acVar.m8773n(), acVar.m8764g());
                            if (a == null) {
                                acVar.mo6165g("DataModelValidationFailed");
                                aiVar = a;
                            } else {
                                acVar.m9235a(a);
                                WeakReference weakReference = (WeakReference) abVar.f7013h.get(c2974b);
                                if (weakReference != null) {
                                    C2927a c2927a = (C2927a) weakReference.get();
                                    if (c2927a != null) {
                                        c2927a.mo6131a();
                                    }
                                }
                                aiVar = a;
                            }
                        }
                    }
                }
                return aiVar;
            }

            void mo6157a(ab abVar, boolean z) {
                Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Activity in focus:" + z);
                if (z && abVar.f7009d) {
                    a.mo6154a(abVar, null);
                }
            }

            void mo6159b(ab abVar, boolean z) {
                Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Network available:" + z);
                if (z && abVar.f7010e) {
                    C2978c.STARTED.mo6154a(abVar, null);
                }
            }
        },
        STOPPED("Stopped") {
            void mo6154a(ab abVar, C2974b c2974b) {
                Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Entering state:" + this);
                abVar.f7008c = C2978c.STOPPED;
            }

            void mo6158b(ab abVar, C2974b c2974b) {
                if (abVar.f7010e && abVar.f7009d) {
                    a.mo6154a(abVar, c2974b);
                } else {
                    b.mo6154a(abVar, c2974b);
                }
            }
        };
        
        private String f7003d;

        abstract void mo6154a(ab abVar, C2974b c2974b);

        private C2978c(String str) {
            this.f7003d = str;
        }

        void mo6158b(ab abVar, C2974b c2974b) {
            Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Cannot be started from current state:" + abVar.f7008c.toString());
        }

        void mo6160c(ab abVar, C2974b c2974b) {
        }

        ai mo6161d(ab abVar, C2974b c2974b) {
            return null;
        }

        void mo6156a(ab abVar, C2974b c2974b, AdUnit adUnit, JSONObject jSONObject) {
        }

        void mo6155a(ab abVar, C2974b c2974b, AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
            Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "AdLoad failed not handled for state:" + abVar.f7008c.toString());
        }

        void mo6157a(ab abVar, boolean z) {
        }

        void mo6159b(ab abVar, boolean z) {
        }

        public String toString() {
            return this.f7003d;
        }
    }

    private ab(@NonNull Context context) {
        this.f7007b = new WeakReference(context);
        this.f7011f = new Handler(context.getMainLooper());
        this.f7009d = C3155d.m10406a();
        f7006j = Executors.newFixedThreadPool(1);
        this.f7010e = true;
        if (this.f7009d && this.f7010e) {
            this.f7008c = C2978c.STOPPED;
        } else {
            this.f7008c = C2978c.PAUSED;
        }
        this.f7008c.mo6154a(this, null);
        if (context instanceof Activity) {
            m9195j();
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    public static ab m9182a(@NonNull Context context) {
        ab abVar = (ab) f7005i.get(context);
        if (abVar != null) {
            return abVar;
        }
        abVar = new ab(context);
        f7005i.put(context, abVar);
        return abVar;
    }

    public C2974b m9197a(int i, long j, @NonNull InMobiClientPositioning inMobiClientPositioning, @Nullable C2927a c2927a) {
        Integer[] numArr;
        int size = inMobiClientPositioning.getFixedPositions().size();
        if (size == 0) {
            numArr = new Integer[0];
        } else {
            numArr = new Integer[size];
            inMobiClientPositioning.getFixedPositions().toArray(numArr);
        }
        C2974b c2974b = new C2974b(i, j, new ac((Context) this.f7007b.get(), j, numArr, this));
        if (c2927a != null) {
            this.f7013h.put(c2974b, new WeakReference(c2927a));
        }
        return c2974b;
    }

    public void m9202a(C2974b c2974b) {
        this.f7012g.put(c2974b, new LinkedList());
        this.f7008c.mo6158b(this, c2974b);
    }

    @Nullable
    public ai m9209b(C2974b c2974b) {
        return this.f7008c.mo6161d(this, c2974b);
    }

    public void m9214c(C2974b c2974b) {
        final ac acVar = c2974b.f6987c;
        List<NativeV2DataModel> list = (List) this.f7012g.remove(c2974b);
        this.f7013h.remove(c2974b);
        final List arrayList = new ArrayList();
        synchronized (list) {
            for (NativeV2DataModel nativeV2DataModel : list) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("markupType", "json");
                    jSONObject.put("pubContent", nativeV2DataModel.m9074a().toString());
                    C2968a a = C2965a.m9111a(jSONObject, acVar.m8736b(), acVar.mo6151d(), acVar.mo6152f(), acVar.m8772m(), acVar.m8773n(), acVar.mo6259x());
                    if (a != null) {
                        arrayList.add(a);
                    }
                } catch (Throwable e) {
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        }
        list.clear();
        new Thread(new Runnable(this) {
            final /* synthetic */ ab f6978c;

            /* compiled from: NativeStrandAdCache */
            class C29701 implements Runnable {
                final /* synthetic */ C29711 f6975a;

                C29701(C29711 c29711) {
                    this.f6975a = c29711;
                }

                public void run() {
                    acVar.mo6143T();
                }
            }

            public void run() {
                try {
                    C3047d.m9733a().m9738a(arrayList, acVar.m8775p().m9714a(acVar.mo6151d()).m9624b(), acVar.mo6151d());
                    if (arrayList.size() > 0) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, "Stored " + arrayList.size() + " data model(s) to disk");
                    }
                    arrayList.clear();
                    this.f6978c.f7011f.post(new C29701(this));
                } catch (Throwable e) {
                    C3135c.m10255a().m10279a(new C3132b(e));
                    Logger.m10359a(InternalLogLevel.INTERNAL, ab.f7004a, e.getMessage());
                }
            }
        }).start();
    }

    public void m9206a(C2974b c2974b, Map<String, String> map) {
        c2974b.f6987c.m8733a((Map) map);
    }

    public void m9205a(C2974b c2974b, String str) {
        c2974b.f6987c.m8730a(str);
    }

    public void m9204a(C2974b c2974b, MonetizationContext monetizationContext) {
        c2974b.f6987c.mo6255a(monetizationContext);
    }

    public void m9203a(C2974b c2974b, Context context) {
        c2974b.f6987c.mo6146a(context);
    }

    public C3044h m9215d(C2974b c2974b) {
        return c2974b.f6987c.m8775p().m9728m();
    }

    public void mo6122a(AdUnit adUnit, boolean z) {
    }

    public void mo6119a() {
    }

    public void mo6120a(AdUnit adUnit) {
        if ("inlban".equals(adUnit.mo6151d())) {
            try {
                if (!((ac) adUnit).mo6142S()) {
                    JSONObject jSONObject = new JSONObject(adUnit.m8769j());
                    for (C2974b c2974b : this.f7012g.keySet()) {
                        if (c2974b.f6987c.equals(adUnit)) {
                            break;
                        }
                    }
                    C2974b c2974b2 = null;
                    if (c2974b2 != null) {
                        this.f7008c.mo6156a(this, c2974b2, adUnit, jSONObject);
                        return;
                    }
                    return;
                }
                return;
            } catch (Throwable e) {
                this.f7008c.mo6155a(null, null, null, null);
                C3135c.m10255a().m10279a(new C3132b(e));
                return;
            }
        }
        this.f7008c.mo6155a(null, null, null, null);
    }

    public void mo6121a(AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if ("inlban".equals(adUnit.mo6151d())) {
            try {
                if (!((ac) adUnit).mo6142S()) {
                    for (C2974b c2974b : this.f7012g.keySet()) {
                        if (c2974b.f6987c.equals(adUnit)) {
                            break;
                        }
                    }
                    C2974b c2974b2 = null;
                    if (c2974b2 != null) {
                        this.f7008c.mo6155a(this, c2974b2, adUnit, inMobiAdRequestStatus);
                    }
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7004a, "SDK encountered unexpected error in handling the onAdLoadFailed event; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
            }
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

    public void mo6162a(boolean z) {
        this.f7010e = z;
        try {
            this.f7008c.mo6157a(this, z);
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7004a, "Encountered unexpected error in the onFocusChanged handler: " + e.getMessage());
            Logger.m10359a(InternalLogLevel.DEBUG, "InMobi Native Strands", "SDK encountered an unexpected error");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    public void m9218e(C2974b c2974b) {
        if (c2974b != null && c2974b.f6987c != null) {
            mo6162a(false);
            c2974b.f6987c.mo6144U();
        }
    }

    public void m9220f(C2974b c2974b) {
        if (c2974b != null && c2974b.f6987c != null) {
            mo6162a(true);
            c2974b.f6987c.mo6145V();
        }
    }

    public void mo6163b(boolean z) {
        this.f7009d = z;
        this.f7008c.mo6159b(this, z);
    }

    private static void m9188c(final ab abVar, final C2974b c2974b, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                WeakReference weakReference = (WeakReference) abVar.f7013h.get(c2974b);
                if (weakReference != null) {
                    C2927a c2927a = (C2927a) weakReference.get();
                    if (c2927a != null) {
                        c2927a.mo6132a(true, inMobiAdRequestStatus);
                    }
                }
            }
        });
    }

    private static void m9189d(final ab abVar, final C2974b c2974b, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                WeakReference weakReference = (WeakReference) abVar.f7013h.get(c2974b);
                if (weakReference != null) {
                    C2927a c2927a = (C2927a) weakReference.get();
                    if (c2927a != null) {
                        c2927a.mo6132a(false, inMobiAdRequestStatus);
                    }
                }
            }
        });
    }

    private void m9195j() {
        C3152a.m10381a().m10392a((C2982b) this);
        C3158f.m10412a().m10418a("android.net.conn.CONNECTIVITY_CHANGE", (C2983b) this);
    }

    private void m9196k() {
        C3152a.m10381a().m10393b((C2982b) this);
        C3158f.m10412a().m10417a((C2983b) this, "android.net.conn.CONNECTIVITY_CHANGE");
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        try {
            if (f7005i.containsKey(activity)) {
                f7005i.remove(activity);
                m9196k();
                activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7004a, "Error in onActivityDestroyed : (" + e.getMessage() + ")");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }
}

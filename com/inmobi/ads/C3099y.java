package com.inmobi.ads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.AdUnit.C2907d;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.moat.analytics.mobile.inm.MoatFactory;
import com.moat.analytics.mobile.inm.NativeDisplayTracker;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;

/* compiled from: MoatTrackedNativeV2DisplayAd */
class C3099y extends br {
    private final String f7580b = C3099y.class.getSimpleName();
    @NonNull
    private final WeakReference<Activity> f7581c;
    private NativeDisplayTracker f7582d;
    @NonNull
    private Map<String, Object> f7583e;
    @NonNull
    private ViewableAd f7584f;

    C3099y(@NonNull Activity activity, @NonNull ViewableAd viewableAd, @NonNull Map<String, Object> map) {
        this.f7581c = new WeakReference(activity);
        this.f7584f = viewableAd;
        this.f7583e = map;
    }

    @Nullable
    public View mo6225a() {
        return this.f7584f.mo6225a();
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7584f.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7584f.mo6249b();
    }

    public void mo6228a(@NonNull C3044h c3044h, @Nullable View... viewArr) {
        try {
            View b = this.f7584f.mo6249b();
            if (b != null) {
                Activity activity = (Activity) this.f7581c.get();
                if (c3044h.m9702g() && activity != null && ((Boolean) this.f7583e.get("enabled")).booleanValue()) {
                    if (this.f7582d == null) {
                        this.f7582d = MoatFactory.create(activity).createNativeDisplayTracker(b, (String) this.f7583e.get("partnerCode"));
                    }
                    Logger.m10359a(InternalLogLevel.INTERNAL, this.f7580b, "Moat init result for Native Display : " + this.f7582d.track(m10055f()) + ", for ID : " + this.f7583e.get("zMoatIID"));
                }
                this.f7584f.mo6228a(c3044h, viewArr);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, this.f7580b, "Exception in startTrackingForImpression with message : " + e.getMessage());
        } finally {
            this.f7584f.mo6228a(c3044h, viewArr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6229c() {
        /*
        r5 = this;
        r0 = r5.f7582d;	 Catch:{ Exception -> 0x0033 }
        if (r0 == 0) goto L_0x002d;
    L_0x0004:
        r0 = r5.f7582d;	 Catch:{ Exception -> 0x0033 }
        r0.stopTracking();	 Catch:{ Exception -> 0x0033 }
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x0033 }
        r1 = r5.f7580b;	 Catch:{ Exception -> 0x0033 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0033 }
        r2.<init>();	 Catch:{ Exception -> 0x0033 }
        r3 = "Moat stopped tracking for Native Display for ID : ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0033 }
        r3 = r5.f7583e;	 Catch:{ Exception -> 0x0033 }
        r4 = "zMoatIID";
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0033 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0033 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0033 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r1, r2);	 Catch:{ Exception -> 0x0033 }
    L_0x002d:
        r0 = r5.f7584f;
        r0.mo6229c();
    L_0x0032:
        return;
    L_0x0033:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x0059 }
        r2 = r5.f7580b;	 Catch:{ all -> 0x0059 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0059 }
        r3.<init>();	 Catch:{ all -> 0x0059 }
        r4 = "Exception in stopTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0059 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0059 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0059 }
        r0 = r0.toString();	 Catch:{ all -> 0x0059 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r0);	 Catch:{ all -> 0x0059 }
        r0 = r5.f7584f;
        r0.mo6229c();
        goto L_0x0032;
    L_0x0059:
        r0 = move-exception;
        r1 = r5.f7584f;
        r1.mo6229c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.y.c():void");
    }

    public void mo6227a(AdEvent adEvent) {
        this.f7584f.mo6227a(adEvent);
    }

    public void mo6230d() {
        this.f7582d = null;
        this.f7581c.clear();
        super.mo6230d();
        this.f7584f.mo6230d();
    }

    private Map<String, String> m10055f() {
        Map<String, String> a = C2907d.m8653a("moatClientLevel", "moatClientSlicer", (JSONArray) this.f7583e.get("clientLevels"), (JSONArray) this.f7583e.get("clientSlicers"));
        a.put("zMoatIID", (String) this.f7583e.get("zMoatIID"));
        return a;
    }
}

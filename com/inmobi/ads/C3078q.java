package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.inmobi.ads.AdUnit.AdCreativeType;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3106b;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.C3323a;
import com.integralads.avid.library.inmobi.session.C3325c;
import com.integralads.avid.library.inmobi.session.C3329g;
import java.lang.ref.WeakReference;

/* compiled from: IasTrackedHtmlAd */
public class C3078q extends br {
    private static final String f7518b = C3078q.class.getSimpleName();
    @NonNull
    private final WeakReference<Activity> f7519c;
    @NonNull
    private final ViewableAd f7520d;
    @NonNull
    private final C3323a<WebView> f7521e;
    private final boolean f7522f;

    @Nullable
    static C3323a<WebView> m9949a(@Nullable Context context, boolean z, @NonNull AdCreativeType adCreativeType, @NonNull RenderView renderView) {
        C3323a<WebView> a;
        C3329g c3329g = new C3329g(C3106b.m10098c(), z);
        switch (adCreativeType) {
            case AD_CREATIVE_TYPE_DISPLAY:
                a = C3325c.m11365a(context, c3329g);
                break;
            case AD_CREATIVE_TYPE_VIDEO:
                a = C3325c.m11366b(context, c3329g);
                break;
            default:
                Logger.m10359a(InternalLogLevel.INTERNAL, f7518b, "Unknown creative type; ignoring AVID meta-data");
                a = null;
                break;
        }
        if (a != null) {
            if (context instanceof Activity) {
                a.m11360a(renderView, (Activity) context);
            } else {
                a.m11360a(renderView, null);
            }
        }
        return a;
    }

    public C3078q(@NonNull Activity activity, @NonNull ViewableAd viewableAd, @NonNull C3323a<WebView> c3323a, boolean z) {
        this.f7519c = new WeakReference(activity);
        this.f7520d = viewableAd;
        this.f7521e = c3323a;
        this.f7522f = z;
    }

    @Nullable
    public View mo6225a() {
        return this.f7520d.mo6225a();
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7520d.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7520d.mo6249b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6228a(@android.support.annotation.NonNull com.inmobi.ads.C3046c.C3044h r7, @android.support.annotation.Nullable android.view.View... r8) {
        /*
        r6 = this;
        r1 = r6.mo6249b();	 Catch:{ Exception -> 0x0052 }
        r0 = r6.f7519c;	 Catch:{ Exception -> 0x0052 }
        r0 = r0.get();	 Catch:{ Exception -> 0x0052 }
        r0 = (android.app.Activity) r0;	 Catch:{ Exception -> 0x0052 }
        if (r0 == 0) goto L_0x004c;
    L_0x000e:
        r2 = r7.m9703h();	 Catch:{ Exception -> 0x0052 }
        if (r2 == 0) goto L_0x004c;
    L_0x0014:
        if (r1 == 0) goto L_0x004c;
    L_0x0016:
        if (r8 == 0) goto L_0x0026;
    L_0x0018:
        r3 = r8.length;	 Catch:{ Exception -> 0x0052 }
        r2 = 0;
    L_0x001a:
        if (r2 >= r3) goto L_0x0026;
    L_0x001c:
        r4 = r8[r2];	 Catch:{ Exception -> 0x0052 }
        r5 = r6.f7521e;	 Catch:{ Exception -> 0x0052 }
        r5.m11362b(r4);	 Catch:{ Exception -> 0x0052 }
        r2 = r2 + 1;
        goto L_0x001a;
    L_0x0026:
        r2 = r6.f7521e;	 Catch:{ Exception -> 0x0052 }
        r1 = (android.webkit.WebView) r1;	 Catch:{ Exception -> 0x0052 }
        r2.m11360a(r1, r0);	 Catch:{ Exception -> 0x0052 }
        r0 = r6.f7522f;	 Catch:{ Exception -> 0x0052 }
        if (r0 == 0) goto L_0x0042;
    L_0x0031:
        r0 = r6.f7521e;	 Catch:{ Exception -> 0x0052 }
        r0 = r0.m11363c();	 Catch:{ Exception -> 0x0052 }
        if (r0 == 0) goto L_0x0042;
    L_0x0039:
        r0 = r6.f7521e;	 Catch:{ Exception -> 0x0052 }
        r0 = r0.m11363c();	 Catch:{ Exception -> 0x0052 }
        r0.a_();	 Catch:{ Exception -> 0x0052 }
    L_0x0042:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x0052 }
        r1 = f7518b;	 Catch:{ Exception -> 0x0052 }
        r2 = "Registered ad view with AVID ad session";
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r1, r2);	 Catch:{ Exception -> 0x0052 }
    L_0x004c:
        r0 = r6.f7520d;
        r0.mo6228a(r7, r8);
    L_0x0051:
        return;
    L_0x0052:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x0078 }
        r2 = f7518b;	 Catch:{ all -> 0x0078 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0078 }
        r3.<init>();	 Catch:{ all -> 0x0078 }
        r4 = "Exception in startTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0078 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0078 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0078 }
        r0 = r0.toString();	 Catch:{ all -> 0x0078 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r0);	 Catch:{ all -> 0x0078 }
        r0 = r6.f7520d;
        r0.mo6228a(r7, r8);
        goto L_0x0051;
    L_0x0078:
        r0 = move-exception;
        r1 = r6.f7520d;
        r1.mo6228a(r7, r8);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.q.a(com.inmobi.ads.c$h, android.view.View[]):void");
    }

    public void mo6229c() {
        try {
            this.f7521e.m11359a((WebView) mo6249b());
            this.f7521e.m11361b();
            Logger.m10359a(InternalLogLevel.INTERNAL, f7518b, "Unregistered WebView to IAS AdSession.");
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7518b, "Exception in stopTrackingForImpression with message : " + e.getMessage());
        } finally {
            this.f7520d.mo6229c();
        }
    }

    public void mo6227a(AdEvent adEvent) {
        this.f7520d.mo6227a(adEvent);
    }

    public void mo6230d() {
        super.mo6230d();
        try {
            this.f7519c.clear();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7518b, "Exception in destroy with message : " + e.getMessage());
        } finally {
            this.f7520d.mo6230d();
        }
    }
}

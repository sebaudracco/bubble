package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.ViewableAd.AdEvent;
import java.lang.ref.WeakReference;

/* compiled from: InMobiTrackedNativeV2DisplayAd */
class C3088u extends br {
    private static final String f7554b = C3088u.class.getSimpleName();
    @NonNull
    private final WeakReference<Context> f7555c;
    @NonNull
    private final ViewableAd f7556d;
    @NonNull
    private final am f7557e = new am();

    C3088u(@NonNull Context context, @NonNull ai aiVar, @NonNull ViewableAd viewableAd) {
        super(aiVar);
        this.f7555c = new WeakReference(context);
        this.f7556d = viewableAd;
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7556d.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7556d.mo6249b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6228a(@android.support.annotation.NonNull com.inmobi.ads.C3046c.C3044h r6, @android.support.annotation.Nullable android.view.View... r7) {
        /*
        r5 = this;
        r0 = r5.f7555c;	 Catch:{ Exception -> 0x0027 }
        r0 = r0.get();	 Catch:{ Exception -> 0x0027 }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0027 }
        r2 = r5.mo6249b();	 Catch:{ Exception -> 0x0027 }
        r1 = r5.m9109e();	 Catch:{ Exception -> 0x0027 }
        r1 = (com.inmobi.ads.ai) r1;	 Catch:{ Exception -> 0x0027 }
        if (r0 == 0) goto L_0x0021;
    L_0x0014:
        if (r2 == 0) goto L_0x0021;
    L_0x0016:
        r3 = r1.mo6176c();	 Catch:{ Exception -> 0x0027 }
        if (r3 != 0) goto L_0x0021;
    L_0x001c:
        r3 = r5.f7557e;	 Catch:{ Exception -> 0x0027 }
        r3.m9372a(r0, r2, r1, r6);	 Catch:{ Exception -> 0x0027 }
    L_0x0021:
        r0 = r5.f7556d;
        r0.mo6228a(r6, r7);
    L_0x0026:
        return;
    L_0x0027:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x004d }
        r2 = f7554b;	 Catch:{ all -> 0x004d }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004d }
        r3.<init>();	 Catch:{ all -> 0x004d }
        r4 = "Exception in startTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x004d }
        r0 = r0.getMessage();	 Catch:{ all -> 0x004d }
        r0 = r3.append(r0);	 Catch:{ all -> 0x004d }
        r0 = r0.toString();	 Catch:{ all -> 0x004d }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r0);	 Catch:{ all -> 0x004d }
        r0 = r5.f7556d;
        r0.mo6228a(r6, r7);
        goto L_0x0026;
    L_0x004d:
        r0 = move-exception;
        r1 = r5.f7556d;
        r1.mo6228a(r6, r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.u.a(com.inmobi.ads.c$h, android.view.View[]):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6229c() {
        /*
        r5 = this;
        r0 = r5.m9109e();	 Catch:{ Exception -> 0x001f }
        r0 = (com.inmobi.ads.ai) r0;	 Catch:{ Exception -> 0x001f }
        r1 = r0.mo6176c();	 Catch:{ Exception -> 0x001f }
        if (r1 != 0) goto L_0x0019;
    L_0x000c:
        r2 = r5.f7557e;	 Catch:{ Exception -> 0x001f }
        r1 = r5.f7555c;	 Catch:{ Exception -> 0x001f }
        r1 = r1.get();	 Catch:{ Exception -> 0x001f }
        r1 = (android.content.Context) r1;	 Catch:{ Exception -> 0x001f }
        r2.m9353a(r1, r0);	 Catch:{ Exception -> 0x001f }
    L_0x0019:
        r0 = r5.f7556d;
        r0.mo6229c();
    L_0x001e:
        return;
    L_0x001f:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x0045 }
        r2 = f7554b;	 Catch:{ all -> 0x0045 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0045 }
        r3.<init>();	 Catch:{ all -> 0x0045 }
        r4 = "Exception in stopTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0045 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0045 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0045 }
        r0 = r0.toString();	 Catch:{ all -> 0x0045 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r0);	 Catch:{ all -> 0x0045 }
        r0 = r5.f7556d;
        r0.mo6229c();
        goto L_0x001e;
    L_0x0045:
        r0 = move-exception;
        r1 = r5.f7556d;
        r1.mo6229c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.u.c():void");
    }

    public void mo6227a(AdEvent adEvent) {
        this.f7556d.mo6227a(adEvent);
    }

    public void mo6230d() {
        super.mo6230d();
        this.f7555c.clear();
        this.f7556d.mo6230d();
    }
}

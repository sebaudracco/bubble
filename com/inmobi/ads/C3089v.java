package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;

/* compiled from: InMobiTrackedNativeV2VideoAd */
class C3089v extends br {
    private static final String f7558b = C3089v.class.getSimpleName();
    @NonNull
    private final WeakReference<Context> f7559c;
    @NonNull
    private final ViewableAd f7560d;
    @NonNull
    private final av f7561e = new av();

    C3089v(@NonNull Context context, @NonNull au auVar, @NonNull ViewableAd viewableAd) {
        super(auVar);
        this.f7559c = new WeakReference(context);
        this.f7560d = viewableAd;
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7560d.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7560d.mo6249b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6228a(@android.support.annotation.NonNull com.inmobi.ads.C3046c.C3044h r6, @android.support.annotation.Nullable android.view.View... r7) {
        /*
        r5 = this;
        r0 = r5.m9109e();	 Catch:{ Exception -> 0x002d }
        r0 = (com.inmobi.ads.au) r0;	 Catch:{ Exception -> 0x002d }
        r1 = r0.getVideoContainerView();	 Catch:{ Exception -> 0x002d }
        r1 = (com.inmobi.ads.NativeStrandVideoWrapper) r1;	 Catch:{ Exception -> 0x002d }
        r2 = r5.f7559c;	 Catch:{ Exception -> 0x002d }
        r2 = r2.get();	 Catch:{ Exception -> 0x002d }
        r2 = (android.content.Context) r2;	 Catch:{ Exception -> 0x002d }
        if (r2 == 0) goto L_0x0027;
    L_0x0016:
        if (r1 == 0) goto L_0x0027;
    L_0x0018:
        r3 = r0.mo6176c();	 Catch:{ Exception -> 0x002d }
        if (r3 != 0) goto L_0x0027;
    L_0x001e:
        r1 = r1.getVideoView();	 Catch:{ Exception -> 0x002d }
        r3 = r5.f7561e;	 Catch:{ Exception -> 0x002d }
        r3.m9437a(r2, r1, r0, r6);	 Catch:{ Exception -> 0x002d }
    L_0x0027:
        r0 = r5.f7560d;
        r0.mo6228a(r6, r7);
    L_0x002c:
        return;
    L_0x002d:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x005f }
        r2 = f7558b;	 Catch:{ all -> 0x005f }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005f }
        r3.<init>();	 Catch:{ all -> 0x005f }
        r4 = "Exception in startTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x005f }
        r4 = r0.getMessage();	 Catch:{ all -> 0x005f }
        r3 = r3.append(r4);	 Catch:{ all -> 0x005f }
        r3 = r3.toString();	 Catch:{ all -> 0x005f }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ all -> 0x005f }
        r1 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ all -> 0x005f }
        r2 = new com.inmobi.commons.core.d.b;	 Catch:{ all -> 0x005f }
        r2.<init>(r0);	 Catch:{ all -> 0x005f }
        r1.m10279a(r2);	 Catch:{ all -> 0x005f }
        r0 = r5.f7560d;
        r0.mo6228a(r6, r7);
        goto L_0x002c;
    L_0x005f:
        r0 = move-exception;
        r1 = r5.f7560d;
        r1.mo6228a(r6, r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.v.a(com.inmobi.ads.c$h, android.view.View[]):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6229c() {
        /*
        r5 = this;
        r0 = r5.f7559c;	 Catch:{ Exception -> 0x0021 }
        r0 = r0.get();	 Catch:{ Exception -> 0x0021 }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0021 }
        r1 = r5.m9109e();	 Catch:{ Exception -> 0x0021 }
        r1 = (com.inmobi.ads.au) r1;	 Catch:{ Exception -> 0x0021 }
        r2 = r1.mo6176c();	 Catch:{ Exception -> 0x0021 }
        if (r2 != 0) goto L_0x001b;
    L_0x0014:
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r2 = r5.f7561e;	 Catch:{ Exception -> 0x0021 }
        r2.m9353a(r0, r1);	 Catch:{ Exception -> 0x0021 }
    L_0x001b:
        r0 = r5.f7560d;
        r0.mo6229c();
    L_0x0020:
        return;
    L_0x0021:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x0053 }
        r2 = f7558b;	 Catch:{ all -> 0x0053 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0053 }
        r3.<init>();	 Catch:{ all -> 0x0053 }
        r4 = "Exception in stopTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0053 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x0053 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0053 }
        r3 = r3.toString();	 Catch:{ all -> 0x0053 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ all -> 0x0053 }
        r1 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ all -> 0x0053 }
        r2 = new com.inmobi.commons.core.d.b;	 Catch:{ all -> 0x0053 }
        r2.<init>(r0);	 Catch:{ all -> 0x0053 }
        r1.m10279a(r2);	 Catch:{ all -> 0x0053 }
        r0 = r5.f7560d;
        r0.mo6229c();
        goto L_0x0020;
    L_0x0053:
        r0 = move-exception;
        r1 = r5.f7560d;
        r1.mo6229c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.v.c():void");
    }

    public void mo6227a(AdEvent adEvent) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7558b, "Received event : " + adEvent.toString());
        this.f7560d.mo6227a(adEvent);
    }

    public void mo6230d() {
        super.mo6230d();
        this.f7559c.clear();
        this.f7560d.mo6230d();
    }
}

package com.inmobi.ads;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.inmobi.ads.AdUnit.C2907d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.moat.analytics.mobile.inm.NativeVideoTracker;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;

/* compiled from: MoatTrackedNativeV2VideoAd */
class C3101z extends br {
    private static final String f7586b = C3101z.class.getSimpleName();
    private static final boolean f7587c = false;
    @NonNull
    private final WeakReference<Activity> f7588d;
    private NativeVideoTracker f7589e;
    private MediaPlayer f7590f;
    @NonNull
    private Map<String, Object> f7591g;
    @NonNull
    private ViewableAd f7592h;

    static {
        if ("row".equalsIgnoreCase("staging")) {
        }
    }

    C3101z(@NonNull Activity activity, @NonNull ViewableAd viewableAd, @NonNull au auVar, @NonNull Map<String, Object> map) {
        super(auVar);
        this.f7588d = new WeakReference(activity);
        this.f7592h = viewableAd;
        this.f7591g = map;
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7592h.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7592h.mo6249b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6228a(@android.support.annotation.NonNull com.inmobi.ads.C3046c.C3044h r6, @android.support.annotation.Nullable android.view.View... r7) {
        /*
        r5 = this;
        r0 = r5.f7588d;	 Catch:{ Exception -> 0x004f }
        r0 = r0.get();	 Catch:{ Exception -> 0x004f }
        r0 = (android.app.Activity) r0;	 Catch:{ Exception -> 0x004f }
        if (r0 == 0) goto L_0x0049;
    L_0x000a:
        r1 = r5.m9109e();	 Catch:{ Exception -> 0x004f }
        r1 = r1 instanceof com.inmobi.ads.au;	 Catch:{ Exception -> 0x004f }
        if (r1 == 0) goto L_0x0049;
    L_0x0012:
        r1 = r6.m9702g();	 Catch:{ Exception -> 0x004f }
        if (r1 == 0) goto L_0x0049;
    L_0x0018:
        r1 = r5.f7591g;	 Catch:{ Exception -> 0x004f }
        r2 = "enabled";
        r1 = r1.get(r2);	 Catch:{ Exception -> 0x004f }
        r1 = (java.lang.Boolean) r1;	 Catch:{ Exception -> 0x004f }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x004f }
        if (r1 == 0) goto L_0x0049;
    L_0x0029:
        r1 = r5.f7589e;	 Catch:{ Exception -> 0x004f }
        if (r1 != 0) goto L_0x0049;
    L_0x002d:
        r1 = com.moat.analytics.mobile.inm.MoatFactory.create(r0);	 Catch:{ Exception -> 0x004f }
        r0 = r5.f7591g;	 Catch:{ Exception -> 0x004f }
        r2 = "partnerCode";
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x004f }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004f }
        r0 = r1.createNativeVideoTracker(r0);	 Catch:{ Exception -> 0x004f }
        r5.f7589e = r0;	 Catch:{ Exception -> 0x004f }
        r0 = r5.f7589e;	 Catch:{ Exception -> 0x004f }
        r1 = f7587c;	 Catch:{ Exception -> 0x004f }
        r0.setDebug(r1);	 Catch:{ Exception -> 0x004f }
    L_0x0049:
        r0 = r5.f7592h;
        r0.mo6228a(r6, r7);
    L_0x004e:
        return;
    L_0x004f:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x0081 }
        r2 = f7586b;	 Catch:{ all -> 0x0081 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r3.<init>();	 Catch:{ all -> 0x0081 }
        r4 = "Exception in startTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0081 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x0081 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0081 }
        r3 = r3.toString();	 Catch:{ all -> 0x0081 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ all -> 0x0081 }
        r1 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ all -> 0x0081 }
        r2 = new com.inmobi.commons.core.d.b;	 Catch:{ all -> 0x0081 }
        r2.<init>(r0);	 Catch:{ all -> 0x0081 }
        r1.m10279a(r2);	 Catch:{ all -> 0x0081 }
        r0 = r5.f7592h;
        r0.mo6228a(r6, r7);
        goto L_0x004e;
    L_0x0081:
        r0 = move-exception;
        r1 = r5.f7592h;
        r1.mo6228a(r6, r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.z.a(com.inmobi.ads.c$h, android.view.View[]):void");
    }

    private void m10063f() {
        NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) m9109e().getVideoContainerView();
        if (nativeStrandVideoWrapper != null && this.f7589e != null) {
            View videoView = nativeStrandVideoWrapper.getVideoView();
            this.f7590f = videoView.getMediaPlayer();
            Logger.m10359a(InternalLogLevel.INTERNAL, f7586b, "Moat init result for Video : " + this.f7589e.trackVideoAd(m10064g(), this.f7590f, videoView));
        }
    }

    public void mo6229c() {
        this.f7592h.mo6229c();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6227a(com.inmobi.ads.ViewableAd.AdEvent r7) {
        /*
        r6 = this;
        r0 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        if (r0 == 0) goto L_0x0046;
    L_0x0004:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x0050 }
        r1 = f7586b;	 Catch:{ Exception -> 0x0050 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0050 }
        r2.<init>();	 Catch:{ Exception -> 0x0050 }
        r3 = "Received event : ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0050 }
        r3 = r7.toString();	 Catch:{ Exception -> 0x0050 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0050 }
        r3 = " for VideoTracker(";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0050 }
        r3 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        r3 = r3.hashCode();	 Catch:{ Exception -> 0x0050 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0050 }
        r3 = ")";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0050 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0050 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r1, r2);	 Catch:{ Exception -> 0x0050 }
        r0 = com.inmobi.ads.C3101z.C31001.f7585a;	 Catch:{ Exception -> 0x0050 }
        r1 = r7.ordinal();	 Catch:{ Exception -> 0x0050 }
        r0 = r0[r1];	 Catch:{ Exception -> 0x0050 }
        switch(r0) {
            case 1: goto L_0x004c;
            case 2: goto L_0x0082;
            case 3: goto L_0x0082;
            case 4: goto L_0x00ad;
            case 5: goto L_0x00ba;
            case 6: goto L_0x00c7;
            case 7: goto L_0x00d5;
            default: goto L_0x0046;
        };
    L_0x0046:
        r0 = r6.f7592h;
        r0.mo6227a(r7);
    L_0x004b:
        return;
    L_0x004c:
        r6.m10063f();	 Catch:{ Exception -> 0x0050 }
        goto L_0x0046;
    L_0x0050:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x00a3 }
        r2 = f7586b;	 Catch:{ all -> 0x00a3 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a3 }
        r3.<init>();	 Catch:{ all -> 0x00a3 }
        r4 = "Exception in onAdEvent with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a3 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x00a3 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a3 }
        r3 = r3.toString();	 Catch:{ all -> 0x00a3 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ all -> 0x00a3 }
        r1 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ all -> 0x00a3 }
        r2 = new com.inmobi.commons.core.d.b;	 Catch:{ all -> 0x00a3 }
        r2.<init>(r0);	 Catch:{ all -> 0x00a3 }
        r1.m10279a(r2);	 Catch:{ all -> 0x00a3 }
        r0 = r6.f7592h;
        r0.mo6227a(r7);
        goto L_0x004b;
    L_0x0082:
        r2 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        r3 = new com.moat.analytics.mobile.inm.MoatAdEvent;	 Catch:{ Exception -> 0x0050 }
        r4 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_VOLUME_CHANGE;	 Catch:{ Exception -> 0x0050 }
        r0 = r6.f7590f;	 Catch:{ Exception -> 0x0050 }
        r0 = r0.getCurrentPosition();	 Catch:{ Exception -> 0x0050 }
        r5 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0050 }
        r0 = com.inmobi.ads.ViewableAd.AdEvent.AD_EVENT_VIDEO_UNMUTE;	 Catch:{ Exception -> 0x0050 }
        if (r7 != r0) goto L_0x00aa;
    L_0x0096:
        r0 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
    L_0x0098:
        r0 = java.lang.Double.valueOf(r0);	 Catch:{ Exception -> 0x0050 }
        r3.<init>(r4, r5, r0);	 Catch:{ Exception -> 0x0050 }
        r2.dispatchEvent(r3);	 Catch:{ Exception -> 0x0050 }
        goto L_0x0046;
    L_0x00a3:
        r0 = move-exception;
        r1 = r6.f7592h;
        r1.mo6227a(r7);
        throw r0;
    L_0x00aa:
        r0 = 0;
        goto L_0x0098;
    L_0x00ad:
        r0 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        r1 = new com.moat.analytics.mobile.inm.MoatAdEvent;	 Catch:{ Exception -> 0x0050 }
        r2 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_ENTER_FULLSCREEN;	 Catch:{ Exception -> 0x0050 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0050 }
        r0.dispatchEvent(r1);	 Catch:{ Exception -> 0x0050 }
        goto L_0x0046;
    L_0x00ba:
        r0 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        r1 = new com.moat.analytics.mobile.inm.MoatAdEvent;	 Catch:{ Exception -> 0x0050 }
        r2 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_EXIT_FULLSCREEN;	 Catch:{ Exception -> 0x0050 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0050 }
        r0.dispatchEvent(r1);	 Catch:{ Exception -> 0x0050 }
        goto L_0x0046;
    L_0x00c7:
        r0 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        r1 = new com.moat.analytics.mobile.inm.MoatAdEvent;	 Catch:{ Exception -> 0x0050 }
        r2 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_COMPLETE;	 Catch:{ Exception -> 0x0050 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0050 }
        r0.dispatchEvent(r1);	 Catch:{ Exception -> 0x0050 }
        goto L_0x0046;
    L_0x00d5:
        r0 = r6.f7589e;	 Catch:{ Exception -> 0x0050 }
        r1 = new com.moat.analytics.mobile.inm.MoatAdEvent;	 Catch:{ Exception -> 0x0050 }
        r2 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_COMPLETE;	 Catch:{ Exception -> 0x0050 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0050 }
        r0.dispatchEvent(r1);	 Catch:{ Exception -> 0x0050 }
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.z.a(com.inmobi.ads.ViewableAd$AdEvent):void");
    }

    public void mo6230d() {
        this.f7589e = null;
        this.f7588d.clear();
        super.mo6230d();
        this.f7592h.mo6230d();
    }

    private Map<String, String> m10064g() {
        return C2907d.m8653a(FirebaseAnalytics$Param.LEVEL, "slicer", (JSONArray) this.f7591g.get("clientLevels"), (JSONArray) this.f7591g.get("clientSlicers"));
    }
}

package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3160b;
import com.inmobi.commons.p112a.C3106b;
import com.integralads.avid.library.inmobi.session.C3325c;
import com.integralads.avid.library.inmobi.session.C3327e;
import com.integralads.avid.library.inmobi.session.C3329g;
import java.lang.ref.WeakReference;
import java.util.Set;

/* compiled from: IasTrackedNativeV2VideoAd */
class C3082r extends br {
    private static final String f7531b = C3082r.class.getSimpleName();
    @NonNull
    private final WeakReference<Activity> f7532c;
    @NonNull
    private final ViewableAd f7533d;
    @NonNull
    private final C3327e f7534e;
    @Nullable
    private C3081a f7535f;
    @Nullable
    private WeakReference<View> f7536g;

    /* compiled from: IasTrackedNativeV2VideoAd */
    private static final class C3081a extends ContentObserver {
        private Context f7527a;
        private int f7528b = -1;
        private WeakReference<C3082r> f7529c;
        private boolean f7530d = false;

        C3081a(Context context, C3082r c3082r) {
            super(new Handler());
            this.f7527a = context;
            this.f7529c = new WeakReference(c3082r);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            if (this.f7527a != null) {
                int a = C3160b.m10432a(this.f7527a);
                if (a != this.f7528b) {
                    this.f7528b = a;
                    C3082r c3082r = (C3082r) this.f7529c.get();
                    if (!this.f7530d && c3082r != null) {
                        c3082r.m9959a(a);
                    }
                }
            }
        }
    }

    @NonNull
    static C3327e m9958a(@NonNull Context context, @NonNull Set<String> set) {
        C3327e c = C3325c.m11367c(context, new C3329g(C3106b.m10098c(), true));
        if (context instanceof Activity) {
            c.m11360a(null, (Activity) context);
        } else {
            c.m11360a(null, null);
        }
        for (String a : set) {
            c.m11364a(a);
        }
        return c;
    }

    C3082r(@NonNull Activity activity, @NonNull ViewableAd viewableAd, @NonNull au auVar, @NonNull C3327e c3327e) {
        super(auVar);
        this.f7532c = new WeakReference(activity);
        this.f7533d = viewableAd;
        this.f7534e = c3327e;
    }

    @Nullable
    public View mo6225a() {
        return this.f7533d.mo6225a();
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7533d.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7533d.mo6249b();
    }

    private void m9965f() {
        try {
            if (this.f7534e.m11363c() != null) {
                this.f7534e.m11363c().a_();
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6228a(@android.support.annotation.NonNull com.inmobi.ads.C3046c.C3044h r6, @android.support.annotation.Nullable android.view.View... r7) {
        /*
        r5 = this;
        r0 = r6.m9703h();	 Catch:{ Exception -> 0x0023 }
        if (r0 == 0) goto L_0x001d;
    L_0x0006:
        r5.m9966g();	 Catch:{ Exception -> 0x0023 }
        r5.m9965f();	 Catch:{ Exception -> 0x0023 }
        r0 = r5.f7534e;	 Catch:{ Exception -> 0x0023 }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x0023 }
        if (r0 == 0) goto L_0x001d;
    L_0x0014:
        r0 = r5.f7534e;	 Catch:{ Exception -> 0x0023 }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x0023 }
        r0.d_();	 Catch:{ Exception -> 0x0023 }
    L_0x001d:
        r0 = r5.f7533d;
        r0.mo6228a(r6, r7);
    L_0x0022:
        return;
    L_0x0023:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x0055 }
        r2 = f7531b;	 Catch:{ all -> 0x0055 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r3.<init>();	 Catch:{ all -> 0x0055 }
        r4 = "Exception in startTrackingForImpression with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0055 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x0055 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0055 }
        r3 = r3.toString();	 Catch:{ all -> 0x0055 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ all -> 0x0055 }
        r1 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ all -> 0x0055 }
        r2 = new com.inmobi.commons.core.d.b;	 Catch:{ all -> 0x0055 }
        r2.<init>(r0);	 Catch:{ all -> 0x0055 }
        r1.m10279a(r2);	 Catch:{ all -> 0x0055 }
        r0 = r5.f7533d;
        r0.mo6228a(r6, r7);
        goto L_0x0022;
    L_0x0055:
        r0 = move-exception;
        r1 = r5.f7533d;
        r1.mo6228a(r6, r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.r.a(com.inmobi.ads.c$h, android.view.View[]):void");
    }

    public void mo6229c() {
        try {
            if (!((au) m9109e()).mo6208x()) {
                this.f7534e.m11359a(this.f7536g == null ? null : (View) this.f7536g.get());
                this.f7534e.m11361b();
                Logger.m10359a(InternalLogLevel.INTERNAL, f7531b, "Unregistered VideoView to IAS AdSession : " + this.f7534e.hashCode());
            }
            m9967h();
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7531b, "Exception in stopTrackingForImpression with message : " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        } finally {
            this.f7533d.mo6229c();
        }
    }

    private void m9962a(final NativeStrandVideoWrapper nativeStrandVideoWrapper) {
        final View b = mo6249b();
        if (nativeStrandVideoWrapper != null && b != null && (b instanceof ViewGroup)) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ C3082r f7525c;

                public void run() {
                    this.f7525c.m9961a((ViewGroup) b, nativeStrandVideoWrapper);
                }
            });
        }
    }

    private void m9961a(ViewGroup viewGroup, NativeStrandVideoWrapper nativeStrandVideoWrapper) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (!childAt.equals(nativeStrandVideoWrapper)) {
                this.f7534e.m11362b(childAt);
                if ((childAt instanceof ViewGroup) && ((ViewGroup) childAt).getChildCount() > 0) {
                    m9961a((ViewGroup) childAt, nativeStrandVideoWrapper);
                }
            }
        }
    }

    private void m9966g() {
        Activity activity = (Activity) this.f7532c.get();
        if (activity != null && (m9109e() instanceof au)) {
            NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) m9109e().getVideoContainerView();
            if (nativeStrandVideoWrapper != null) {
                this.f7536g = new WeakReference(nativeStrandVideoWrapper);
                m9962a(nativeStrandVideoWrapper);
                this.f7534e.m11360a((View) this.f7536g.get(), activity);
                m9960a(activity);
                Logger.m10359a(InternalLogLevel.INTERNAL, f7531b, "Registered ad view with AVID Video AdSession " + this.f7534e.hashCode());
            }
        }
    }

    private void m9960a(@NonNull Activity activity) {
        if (this.f7535f == null) {
            this.f7535f = new C3081a(activity.getApplicationContext(), this);
            activity.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.f7535f);
        }
    }

    private void m9967h() {
        Activity activity = (Activity) this.f7532c.get();
        if (activity != null && this.f7535f != null) {
            activity.getContentResolver().unregisterContentObserver(this.f7535f);
        }
    }

    private void m9959a(int i) {
        try {
            if (this.f7534e.m11368d() != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7531b, "Sending volumeChange to IAS AdSession(" + this.f7534e.hashCode() + ") with volume - " + i);
                this.f7534e.m11368d().mo6333a(Integer.valueOf(i));
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7531b, "Exception in onVolumeChange with message : " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6227a(com.inmobi.ads.ViewableAd.AdEvent r7) {
        /*
        r6 = this;
        r1 = 1;
        r2 = 0;
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        if (r0 == 0) goto L_0x0041;
    L_0x000a:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x005b }
        r3 = f7531b;	 Catch:{ Exception -> 0x005b }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x005b }
        r4.<init>();	 Catch:{ Exception -> 0x005b }
        r5 = "Sending event (";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x005b }
        r4 = r4.append(r7);	 Catch:{ Exception -> 0x005b }
        r5 = ") to IAS AdSession : ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x005b }
        r5 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r5 = r5.hashCode();	 Catch:{ Exception -> 0x005b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x005b }
        r4 = r4.toString();	 Catch:{ Exception -> 0x005b }
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r3, r4);	 Catch:{ Exception -> 0x005b }
        r0 = com.inmobi.ads.C3082r.C30802.f7526a;	 Catch:{ Exception -> 0x005b }
        r3 = r7.ordinal();	 Catch:{ Exception -> 0x005b }
        r0 = r0[r3];	 Catch:{ Exception -> 0x005b }
        switch(r0) {
            case 1: goto L_0x0047;
            case 2: goto L_0x0096;
            case 3: goto L_0x00b0;
            case 4: goto L_0x00ba;
            case 5: goto L_0x00c5;
            case 6: goto L_0x00d0;
            case 7: goto L_0x00db;
            case 8: goto L_0x00e6;
            case 9: goto L_0x00f1;
            case 10: goto L_0x0105;
            case 11: goto L_0x0110;
            case 12: goto L_0x0110;
            case 13: goto L_0x0147;
            case 14: goto L_0x015b;
            case 15: goto L_0x016f;
            case 16: goto L_0x017d;
            case 17: goto L_0x0188;
            default: goto L_0x0041;
        };
    L_0x0041:
        r0 = r6.f7533d;
        r0.mo6227a(r7);
    L_0x0046:
        return;
    L_0x0047:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6345k();	 Catch:{ Exception -> 0x005b }
        r1 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r6.f7536g;	 Catch:{ Exception -> 0x005b }
        if (r0 != 0) goto L_0x008d;
    L_0x0056:
        r0 = 0;
    L_0x0057:
        r1.m11359a(r0);	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x005b:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x00a9 }
        r2 = f7531b;	 Catch:{ all -> 0x00a9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a9 }
        r3.<init>();	 Catch:{ all -> 0x00a9 }
        r4 = "Exception in onAdEvent with message : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a9 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x00a9 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a9 }
        r3 = r3.toString();	 Catch:{ all -> 0x00a9 }
        com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ all -> 0x00a9 }
        r1 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ all -> 0x00a9 }
        r2 = new com.inmobi.commons.core.d.b;	 Catch:{ all -> 0x00a9 }
        r2.<init>(r0);	 Catch:{ all -> 0x00a9 }
        r1.m10279a(r2);	 Catch:{ all -> 0x00a9 }
        r0 = r6.f7533d;
        r0.mo6227a(r7);
        goto L_0x0046;
    L_0x008d:
        r0 = r6.f7536g;	 Catch:{ Exception -> 0x005b }
        r0 = r0.get();	 Catch:{ Exception -> 0x005b }
        r0 = (android.view.View) r0;	 Catch:{ Exception -> 0x005b }
        goto L_0x0057;
    L_0x0096:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.c_();	 Catch:{ Exception -> 0x005b }
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.e_();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00a9:
        r0 = move-exception;
        r1 = r6.f7533d;
        r1.mo6227a(r7);
        throw r0;
    L_0x00b0:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6346l();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00ba:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6350p();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00c5:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6342h();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00d0:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6343i();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00db:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6344j();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00e6:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.b_();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x00f1:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6340f();	 Catch:{ Exception -> 0x005b }
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6338e();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x0105:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6349o();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x0110:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r3 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0 = com.inmobi.ads.ViewableAd.AdEvent.AD_EVENT_VIDEO_MUTE;	 Catch:{ Exception -> 0x005b }
        if (r0 != r7) goto L_0x0132;
    L_0x011a:
        r0 = r2;
    L_0x011b:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x005b }
        r3.mo6333a(r0);	 Catch:{ Exception -> 0x005b }
        r0 = r6.f7535f;	 Catch:{ Exception -> 0x005b }
        if (r0 == 0) goto L_0x0041;
    L_0x0126:
        r3 = r6.f7535f;	 Catch:{ Exception -> 0x005b }
        r0 = com.inmobi.ads.ViewableAd.AdEvent.AD_EVENT_VIDEO_MUTE;	 Catch:{ Exception -> 0x005b }
        if (r0 != r7) goto L_0x0145;
    L_0x012c:
        r0 = r1;
    L_0x012d:
        r3.f7530d = r0;	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x0132:
        r0 = r6.f7535f;	 Catch:{ Exception -> 0x005b }
        if (r0 == 0) goto L_0x0143;
    L_0x0136:
        r0 = r6.f7532c;	 Catch:{ Exception -> 0x005b }
        r0 = r0.get();	 Catch:{ Exception -> 0x005b }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x005b }
        r0 = com.inmobi.commons.core.utilities.info.C3160b.m10432a(r0);	 Catch:{ Exception -> 0x005b }
        goto L_0x011b;
    L_0x0143:
        r0 = r1;
        goto L_0x011b;
    L_0x0145:
        r0 = r2;
        goto L_0x012d;
    L_0x0147:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6347m();	 Catch:{ Exception -> 0x005b }
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6351q();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x015b:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6352r();	 Catch:{ Exception -> 0x005b }
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6348n();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x016f:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r1 = "Unknown Player error";
        r0.mo6334a(r1);	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x017d:
        r0 = r6.f7534e;	 Catch:{ Exception -> 0x005b }
        r0 = r0.m11368d();	 Catch:{ Exception -> 0x005b }
        r0.mo6341g();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
    L_0x0188:
        r6.m9966g();	 Catch:{ Exception -> 0x005b }
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.r.a(com.inmobi.ads.ViewableAd$AdEvent):void");
    }

    public void mo6230d() {
        super.mo6230d();
        try {
            this.f7532c.clear();
            if (this.f7536g != null) {
                this.f7536g.clear();
            }
            this.f7535f = null;
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7531b, "Exception in destroy with message : " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        } finally {
            this.f7533d.mo6230d();
        }
    }
}

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.AutoScrollHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpn extends zzqb implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    @VisibleForTesting
    private static final String[] zzbjs = new String[]{NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @VisibleForTesting
    private zzoz zzbij;
    private final FrameLayout zzbjt;
    private View zzbju;
    private final boolean zzbjv;
    @VisibleForTesting
    private Map<String, WeakReference<View>> zzbjw = Collections.synchronizedMap(new HashMap());
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private boolean zzbjy = false;
    @VisibleForTesting
    private Point zzbjz = new Point();
    @VisibleForTesting
    private Point zzbka = new Point();
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb = new WeakReference(null);
    @Nullable
    @VisibleForTesting
    private FrameLayout zzvh;

    @TargetApi(21)
    public zzpn(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzbjt = frameLayout;
        this.zzvh = frameLayout2;
        zzbv.zzfg();
        zzaor.zza(this.zzbjt, (OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza(this.zzbjt, (OnScrollChangedListener) this);
        this.zzbjt.setOnTouchListener(this);
        this.zzbjt.setOnClickListener(this);
        if (frameLayout2 != null && PlatformVersion.isAtLeastLollipop()) {
            frameLayout2.setElevation(AutoScrollHelper.NO_MAX);
        }
        zznk.initialize(this.zzbjt.getContext());
        this.zzbjv = ((Boolean) zzkb.zzik().zzd(zznk.zzbcd)).booleanValue();
    }

    private final void zzkt() {
        synchronized (this.mLock) {
            if (!this.zzbjv && this.zzbjy) {
                int measuredWidth = this.zzbjt.getMeasuredWidth();
                int measuredHeight = this.zzbjt.getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzvh == null)) {
                    this.zzvh.setLayoutParams(new LayoutParams(measuredWidth, measuredHeight));
                    this.zzbjy = false;
                }
            }
        }
    }

    private final void zzl(@Nullable View view) {
        if (this.zzbij != null) {
            zzoz zzkn = this.zzbij instanceof zzoy ? ((zzoy) this.zzbij).zzkn() : this.zzbij;
            if (zzkn != null) {
                zzkn.zzl(view);
            }
        }
    }

    @VisibleForTesting
    private final int zzv(int i) {
        zzkb.zzif();
        return zzamu.zzb(this.zzbij.getContext(), i);
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzvh != null) {
                this.zzvh.removeAllViews();
            }
            this.zzvh = null;
            this.zzbjw = null;
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
            this.zzbkb = null;
            this.zzbju = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
        r7 = this;
        r6 = r7.mLock;
        monitor-enter(r6);
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);	 Catch:{ all -> 0x0080 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        r0.cancelUnconfirmedClick();	 Catch:{ all -> 0x0080 }
        r3 = new android.os.Bundle;	 Catch:{ all -> 0x0080 }
        r3.<init>();	 Catch:{ all -> 0x0080 }
        r0 = "x";
        r1 = r7.zzbjz;	 Catch:{ all -> 0x0080 }
        r1 = r1.x;	 Catch:{ all -> 0x0080 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0080 }
        r1 = (float) r1;	 Catch:{ all -> 0x0080 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0080 }
        r0 = "y";
        r1 = r7.zzbjz;	 Catch:{ all -> 0x0080 }
        r1 = r1.y;	 Catch:{ all -> 0x0080 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0080 }
        r1 = (float) r1;	 Catch:{ all -> 0x0080 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0080 }
        r0 = "start_x";
        r1 = r7.zzbka;	 Catch:{ all -> 0x0080 }
        r1 = r1.x;	 Catch:{ all -> 0x0080 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0080 }
        r1 = (float) r1;	 Catch:{ all -> 0x0080 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0080 }
        r0 = "start_y";
        r1 = r7.zzbka;	 Catch:{ all -> 0x0080 }
        r1 = r1.y;	 Catch:{ all -> 0x0080 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0080 }
        r1 = (float) r1;	 Catch:{ all -> 0x0080 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0080 }
        r0 = r7.zzbjx;	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x0091;
    L_0x0053:
        r0 = r7.zzbjx;	 Catch:{ all -> 0x0080 }
        r0 = r0.equals(r8);	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x0091;
    L_0x005b:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        r0 = r0 instanceof com.google.android.gms.internal.ads.zzoy;	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x0083;
    L_0x0061:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        r0 = (com.google.android.gms.internal.ads.zzoy) r0;	 Catch:{ all -> 0x0080 }
        r0 = r0.zzkn();	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x007e;
    L_0x006b:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        r0 = (com.google.android.gms.internal.ads.zzoy) r0;	 Catch:{ all -> 0x0080 }
        r0 = r0.zzkn();	 Catch:{ all -> 0x0080 }
        r2 = "1007";
        r4 = r7.zzbjw;	 Catch:{ all -> 0x0080 }
        r5 = r7.zzbjt;	 Catch:{ all -> 0x0080 }
        r1 = r8;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0080 }
    L_0x007e:
        monitor-exit(r6);	 Catch:{ all -> 0x0080 }
        goto L_0x0008;
    L_0x0080:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0080 }
        throw r0;
    L_0x0083:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        r2 = "1007";
        r4 = r7.zzbjw;	 Catch:{ all -> 0x0080 }
        r5 = r7.zzbjt;	 Catch:{ all -> 0x0080 }
        r1 = r8;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0080 }
        goto L_0x007e;
    L_0x0091:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0080 }
        r1 = r7.zzbjw;	 Catch:{ all -> 0x0080 }
        r2 = r7.zzbjt;	 Catch:{ all -> 0x0080 }
        r0.zza(r8, r1, r3, r2);	 Catch:{ all -> 0x0080 }
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            zzkt();
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
            zzkt();
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
            } else {
                int[] iArr = new int[2];
                this.zzbjt.getLocationOnScreen(iArr);
                Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
                this.zzbjz = point;
                if (motionEvent.getAction() == 0) {
                    this.zzbka = point;
                }
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) point.x, (float) point.y);
                this.zzbij.zzd(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r13) {
        /*
        r12 = this;
        r11 = 2;
        r3 = 1;
        r7 = 0;
        r8 = 0;
        r9 = r12.mLock;
        monitor-enter(r9);
        r1 = 0;
        r12.zzl(r1);	 Catch:{ all -> 0x00b1 }
        r1 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r13);	 Catch:{ all -> 0x00b1 }
        r2 = r1 instanceof com.google.android.gms.internal.ads.zzpd;	 Catch:{ all -> 0x00b1 }
        if (r2 != 0) goto L_0x001b;
    L_0x0013:
        r1 = "Not an instance of native engine. This is most likely a transient error";
        com.google.android.gms.internal.ads.zzane.zzdk(r1);	 Catch:{ all -> 0x00b1 }
        monitor-exit(r9);	 Catch:{ all -> 0x00b1 }
    L_0x001a:
        return;
    L_0x001b:
        r2 = r12.zzbjv;	 Catch:{ all -> 0x00b1 }
        if (r2 != 0) goto L_0x0034;
    L_0x001f:
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0034;
    L_0x0023:
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        r4 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x00b1 }
        r5 = 0;
        r6 = 0;
        r4.<init>(r5, r6);	 Catch:{ all -> 0x00b1 }
        r2.setLayoutParams(r4);	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r2.requestLayout();	 Catch:{ all -> 0x00b1 }
    L_0x0034:
        r2 = 1;
        r12.zzbjy = r2;	 Catch:{ all -> 0x00b1 }
        r1 = (com.google.android.gms.internal.ads.zzpd) r1;	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0058;
    L_0x003d:
        r2 = com.google.android.gms.internal.ads.zznk.zzbbu;	 Catch:{ all -> 0x00b1 }
        r4 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x00b1 }
        r2 = r4.zzd(r2);	 Catch:{ all -> 0x00b1 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ all -> 0x00b1 }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0058;
    L_0x004f:
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r4 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r5 = r12.zzbjw;	 Catch:{ all -> 0x00b1 }
        r2.zzb(r4, r5);	 Catch:{ all -> 0x00b1 }
    L_0x0058:
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r2 = r2 instanceof com.google.android.gms.internal.ads.zzpd;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0093;
    L_0x005e:
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r2 = (com.google.android.gms.internal.ads.zzpd) r2;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0093;
    L_0x0064:
        r4 = r2.getContext();	 Catch:{ all -> 0x00b1 }
        if (r4 == 0) goto L_0x0093;
    L_0x006a:
        r4 = com.google.android.gms.ads.internal.zzbv.zzfh();	 Catch:{ all -> 0x00b1 }
        r5 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r5 = r5.getContext();	 Catch:{ all -> 0x00b1 }
        r4 = r4.zzu(r5);	 Catch:{ all -> 0x00b1 }
        if (r4 == 0) goto L_0x0093;
    L_0x007a:
        r4 = r2.zzks();	 Catch:{ all -> 0x00b1 }
        if (r4 == 0) goto L_0x0084;
    L_0x0080:
        r2 = 0;
        r4.zzx(r2);	 Catch:{ all -> 0x00b1 }
    L_0x0084:
        r2 = r12.zzbkb;	 Catch:{ all -> 0x00b1 }
        r2 = r2.get();	 Catch:{ all -> 0x00b1 }
        r2 = (com.google.android.gms.internal.ads.zzfp) r2;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0093;
    L_0x008e:
        if (r4 == 0) goto L_0x0093;
    L_0x0090:
        r2.zzb(r4);	 Catch:{ all -> 0x00b1 }
    L_0x0093:
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r2 = r2 instanceof com.google.android.gms.internal.ads.zzoy;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x00b4;
    L_0x0099:
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r2 = (com.google.android.gms.internal.ads.zzoy) r2;	 Catch:{ all -> 0x00b1 }
        r2 = r2.zzkm();	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x00b4;
    L_0x00a3:
        r2 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r2 = (com.google.android.gms.internal.ads.zzoy) r2;	 Catch:{ all -> 0x00b1 }
        r2.zzc(r1);	 Catch:{ all -> 0x00b1 }
    L_0x00aa:
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        if (r2 != 0) goto L_0x00c3;
    L_0x00ae:
        monitor-exit(r9);	 Catch:{ all -> 0x00b1 }
        goto L_0x001a;
    L_0x00b1:
        r1 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x00b1 }
        throw r1;
    L_0x00b4:
        r12.zzbij = r1;	 Catch:{ all -> 0x00b1 }
        r2 = r1 instanceof com.google.android.gms.internal.ads.zzoy;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x00aa;
    L_0x00ba:
        r0 = r1;
        r0 = (com.google.android.gms.internal.ads.zzoy) r0;	 Catch:{ all -> 0x00b1 }
        r2 = r0;
        r4 = 0;
        r2.zzc(r4);	 Catch:{ all -> 0x00b1 }
        goto L_0x00aa;
    L_0x00c3:
        r2 = com.google.android.gms.internal.ads.zznk.zzbbu;	 Catch:{ all -> 0x00b1 }
        r4 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x00b1 }
        r2 = r4.zzd(r2);	 Catch:{ all -> 0x00b1 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ all -> 0x00b1 }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x00db;
    L_0x00d5:
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        r4 = 0;
        r2.setClickable(r4);	 Catch:{ all -> 0x00b1 }
    L_0x00db:
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        r2.removeAllViews();	 Catch:{ all -> 0x00b1 }
        r5 = r1.zzkj();	 Catch:{ all -> 0x00b1 }
        if (r5 == 0) goto L_0x027f;
    L_0x00e6:
        r2 = r12.zzbjw;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x021c;
    L_0x00ea:
        r2 = 2;
        r6 = new java.lang.String[r2];	 Catch:{ all -> 0x00b1 }
        r2 = 0;
        r4 = "1098";
        r6[r2] = r4;	 Catch:{ all -> 0x00b1 }
        r2 = 1;
        r4 = "3011";
        r6[r2] = r4;	 Catch:{ all -> 0x00b1 }
        r4 = r8;
    L_0x00fa:
        if (r4 >= r11) goto L_0x021c;
    L_0x00fc:
        r2 = r6[r4];	 Catch:{ all -> 0x00b1 }
        r10 = r12.zzbjw;	 Catch:{ all -> 0x00b1 }
        r2 = r10.get(r2);	 Catch:{ all -> 0x00b1 }
        r2 = (java.lang.ref.WeakReference) r2;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0217;
    L_0x0108:
        r2 = r2.get();	 Catch:{ all -> 0x00b1 }
        r2 = (android.view.View) r2;	 Catch:{ all -> 0x00b1 }
    L_0x010e:
        r4 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x00b1 }
        if (r4 == 0) goto L_0x027f;
    L_0x0112:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x00b1 }
        r4 = r2;
    L_0x0115:
        if (r5 == 0) goto L_0x021f;
    L_0x0117:
        if (r4 == 0) goto L_0x021f;
    L_0x0119:
        r2 = r3;
    L_0x011a:
        r3 = r1.zza(r12, r2);	 Catch:{ all -> 0x00b1 }
        r12.zzbjx = r3;	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbjx;	 Catch:{ all -> 0x00b1 }
        if (r3 == 0) goto L_0x0141;
    L_0x0124:
        r3 = r12.zzbjw;	 Catch:{ all -> 0x00b1 }
        if (r3 == 0) goto L_0x0137;
    L_0x0128:
        r3 = r12.zzbjw;	 Catch:{ all -> 0x00b1 }
        r5 = "1007";
        r6 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x00b1 }
        r10 = r12.zzbjx;	 Catch:{ all -> 0x00b1 }
        r6.<init>(r10);	 Catch:{ all -> 0x00b1 }
        r3.put(r5, r6);	 Catch:{ all -> 0x00b1 }
    L_0x0137:
        if (r2 == 0) goto L_0x0222;
    L_0x0139:
        r4.removeAllViews();	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbjx;	 Catch:{ all -> 0x00b1 }
        r4.addView(r2);	 Catch:{ all -> 0x00b1 }
    L_0x0141:
        r2 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbjw;	 Catch:{ all -> 0x00b1 }
        r4 = 0;
        r5 = r12;
        r6 = r12;
        r1.zza(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbjv;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x017d;
    L_0x014f:
        r2 = r12.zzbju;	 Catch:{ all -> 0x00b1 }
        if (r2 != 0) goto L_0x016c;
    L_0x0153:
        r2 = new android.view.View;	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r3 = r3.getContext();	 Catch:{ all -> 0x00b1 }
        r2.<init>(r3);	 Catch:{ all -> 0x00b1 }
        r12.zzbju = r2;	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbju;	 Catch:{ all -> 0x00b1 }
        r3 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x00b1 }
        r4 = -1;
        r5 = 0;
        r3.<init>(r4, r5);	 Catch:{ all -> 0x00b1 }
        r2.setLayoutParams(r3);	 Catch:{ all -> 0x00b1 }
    L_0x016c:
        r2 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbju;	 Catch:{ all -> 0x00b1 }
        r3 = r3.getParent();	 Catch:{ all -> 0x00b1 }
        if (r2 == r3) goto L_0x017d;
    L_0x0176:
        r2 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbju;	 Catch:{ all -> 0x00b1 }
        r2.addView(r3);	 Catch:{ all -> 0x00b1 }
    L_0x017d:
        r2 = r1.zzko();	 Catch:{ Exception -> 0x0245 }
    L_0x0181:
        if (r2 == 0) goto L_0x0190;
    L_0x0183:
        r3 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        if (r3 == 0) goto L_0x0190;
    L_0x0187:
        r3 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        r2 = r2.getView();	 Catch:{ all -> 0x00b1 }
        r3.addView(r2);	 Catch:{ all -> 0x00b1 }
    L_0x0190:
        r4 = r12.mLock;	 Catch:{ all -> 0x00b1 }
        monitor-enter(r4);	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbjw;	 Catch:{ all -> 0x0278 }
        r1.zzf(r2);	 Catch:{ all -> 0x0278 }
        r2 = r12.zzbjw;	 Catch:{ all -> 0x0278 }
        if (r2 == 0) goto L_0x0266;
    L_0x019c:
        r5 = zzbjs;	 Catch:{ all -> 0x0278 }
        r6 = r5.length;	 Catch:{ all -> 0x0278 }
        r3 = r8;
    L_0x01a0:
        if (r3 >= r6) goto L_0x0266;
    L_0x01a2:
        r2 = r5[r3];	 Catch:{ all -> 0x0278 }
        r8 = r12.zzbjw;	 Catch:{ all -> 0x0278 }
        r2 = r8.get(r2);	 Catch:{ all -> 0x0278 }
        r2 = (java.lang.ref.WeakReference) r2;	 Catch:{ all -> 0x0278 }
        if (r2 == 0) goto L_0x0261;
    L_0x01ae:
        r2 = r2.get();	 Catch:{ all -> 0x0278 }
        r2 = (android.view.View) r2;	 Catch:{ all -> 0x0278 }
    L_0x01b4:
        r3 = r2 instanceof android.widget.FrameLayout;	 Catch:{ all -> 0x0278 }
        if (r3 != 0) goto L_0x0269;
    L_0x01b8:
        r1.zzkq();	 Catch:{ all -> 0x0278 }
        monitor-exit(r4);	 Catch:{ all -> 0x0278 }
    L_0x01bc:
        r2 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r1.zzi(r2);	 Catch:{ all -> 0x00b1 }
        r1 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r12.zzl(r1);	 Catch:{ all -> 0x00b1 }
        r1 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r1.zzj(r2);	 Catch:{ all -> 0x00b1 }
        r1 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r1 = r1 instanceof com.google.android.gms.internal.ads.zzpd;	 Catch:{ all -> 0x00b1 }
        if (r1 == 0) goto L_0x0214;
    L_0x01d3:
        r1 = r12.zzbij;	 Catch:{ all -> 0x00b1 }
        r1 = (com.google.android.gms.internal.ads.zzpd) r1;	 Catch:{ all -> 0x00b1 }
        if (r1 == 0) goto L_0x0214;
    L_0x01d9:
        r2 = r1.getContext();	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0214;
    L_0x01df:
        r2 = com.google.android.gms.ads.internal.zzbv.zzfh();	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r3 = r3.getContext();	 Catch:{ all -> 0x00b1 }
        r2 = r2.zzu(r3);	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0214;
    L_0x01ef:
        r2 = r12.zzbkb;	 Catch:{ all -> 0x00b1 }
        r2 = r2.get();	 Catch:{ all -> 0x00b1 }
        r2 = (com.google.android.gms.internal.ads.zzfp) r2;	 Catch:{ all -> 0x00b1 }
        if (r2 != 0) goto L_0x020d;
    L_0x01f9:
        r2 = new com.google.android.gms.internal.ads.zzfp;	 Catch:{ all -> 0x00b1 }
        r3 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r3 = r3.getContext();	 Catch:{ all -> 0x00b1 }
        r4 = r12.zzbjt;	 Catch:{ all -> 0x00b1 }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x00b1 }
        r3 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x00b1 }
        r3.<init>(r2);	 Catch:{ all -> 0x00b1 }
        r12.zzbkb = r3;	 Catch:{ all -> 0x00b1 }
    L_0x020d:
        r1 = r1.zzks();	 Catch:{ all -> 0x00b1 }
        r2.zza(r1);	 Catch:{ all -> 0x00b1 }
    L_0x0214:
        monitor-exit(r9);	 Catch:{ all -> 0x00b1 }
        goto L_0x001a;
    L_0x0217:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x00fa;
    L_0x021c:
        r2 = r7;
        goto L_0x010e;
    L_0x021f:
        r2 = r8;
        goto L_0x011a;
    L_0x0222:
        r2 = r1.getContext();	 Catch:{ all -> 0x00b1 }
        r3 = new com.google.android.gms.ads.formats.AdChoicesView;	 Catch:{ all -> 0x00b1 }
        r3.<init>(r2);	 Catch:{ all -> 0x00b1 }
        r2 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x00b1 }
        r4 = -1;
        r5 = -1;
        r2.<init>(r4, r5);	 Catch:{ all -> 0x00b1 }
        r3.setLayoutParams(r2);	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzbjx;	 Catch:{ all -> 0x00b1 }
        r3.addView(r2);	 Catch:{ all -> 0x00b1 }
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x0141;
    L_0x023e:
        r2 = r12.zzvh;	 Catch:{ all -> 0x00b1 }
        r2.addView(r3);	 Catch:{ all -> 0x00b1 }
        goto L_0x0141;
    L_0x0245:
        r2 = move-exception;
        com.google.android.gms.ads.internal.zzbv.zzem();	 Catch:{ all -> 0x00b1 }
        r3 = com.google.android.gms.internal.ads.zzakq.zzrp();	 Catch:{ all -> 0x00b1 }
        if (r3 == 0) goto L_0x0258;
    L_0x024f:
        r2 = "Privileged processes cannot create HTML overlays.";
        com.google.android.gms.internal.ads.zzane.zzdk(r2);	 Catch:{ all -> 0x00b1 }
        r2 = r7;
        goto L_0x0181;
    L_0x0258:
        r3 = "Error obtaining overlay.";
        com.google.android.gms.internal.ads.zzane.zzb(r3, r2);	 Catch:{ all -> 0x00b1 }
        r2 = r7;
        goto L_0x0181;
    L_0x0261:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x01a0;
    L_0x0266:
        r2 = r7;
        goto L_0x01b4;
    L_0x0269:
        r3 = new com.google.android.gms.internal.ads.zzpo;	 Catch:{ all -> 0x0278 }
        r3.<init>(r12, r2);	 Catch:{ all -> 0x0278 }
        r5 = r1 instanceof com.google.android.gms.internal.ads.zzoy;	 Catch:{ all -> 0x0278 }
        if (r5 == 0) goto L_0x027b;
    L_0x0272:
        r1.zzb(r2, r3);	 Catch:{ all -> 0x0278 }
    L_0x0275:
        monitor-exit(r4);	 Catch:{ all -> 0x0278 }
        goto L_0x01bc;
    L_0x0278:
        r1 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0278 }
        throw r1;	 Catch:{ all -> 0x00b1 }
    L_0x027b:
        r1.zza(r2, r3);	 Catch:{ all -> 0x0278 }
        goto L_0x0275;
    L_0x027f:
        r4 = r7;
        goto L_0x0115;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final IObjectWrapper zzak(String str) {
        Object obj = null;
        synchronized (this.mLock) {
            if (this.zzbjw == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) this.zzbjw.get(str);
            if (weakReference != null) {
                View view = (View) weakReference.get();
            }
            IObjectWrapper wrap = ObjectWrapper.wrap(obj);
            return wrap;
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        if (zzbv.zzfh().zzu(this.zzbjt.getContext()) && this.zzbkb != null) {
            zzfp com_google_android_gms_internal_ads_zzfp = (zzfp) this.zzbkb.get();
            if (com_google_android_gms_internal_ads_zzfp != null) {
                com_google_android_gms_internal_ads_zzfp.zzgm();
            }
        }
        zzkt();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r5, com.google.android.gms.dynamic.IObjectWrapper r6) {
        /*
        r4 = this;
        r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r6);
        r0 = (android.view.View) r0;
        r1 = r4.mLock;
        monitor-enter(r1);
        r2 = r4.zzbjw;	 Catch:{ all -> 0x0018 }
        if (r2 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
    L_0x000e:
        return;
    L_0x000f:
        if (r0 != 0) goto L_0x001b;
    L_0x0011:
        r0 = r4.zzbjw;	 Catch:{ all -> 0x0018 }
        r0.remove(r5);	 Catch:{ all -> 0x0018 }
    L_0x0016:
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
        goto L_0x000e;
    L_0x0018:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
        throw r0;
    L_0x001b:
        r2 = r4.zzbjw;	 Catch:{ all -> 0x0018 }
        r3 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x0018 }
        r3.<init>(r0);	 Catch:{ all -> 0x0018 }
        r2.put(r5, r3);	 Catch:{ all -> 0x0018 }
        r2 = "1098";
        r2 = r2.equals(r5);	 Catch:{ all -> 0x0018 }
        if (r2 != 0) goto L_0x0037;
    L_0x002e:
        r2 = "3011";
        r2 = r2.equals(r5);	 Catch:{ all -> 0x0018 }
        if (r2 == 0) goto L_0x0039;
    L_0x0037:
        monitor-exit(r1);	 Catch:{ all -> 0x0018 }
        goto L_0x000e;
    L_0x0039:
        r0.setOnTouchListener(r4);	 Catch:{ all -> 0x0018 }
        r2 = 1;
        r0.setClickable(r2);	 Catch:{ all -> 0x0018 }
        r0.setOnClickListener(r4);	 Catch:{ all -> 0x0018 }
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zzb(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}

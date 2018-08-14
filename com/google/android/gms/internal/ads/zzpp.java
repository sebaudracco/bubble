package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpp extends zzqg implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    @VisibleForTesting
    static final String[] zzbjs = new String[]{NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    @VisibleForTesting
    private zzoz zzbij;
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private Point zzbjz = new Point();
    @VisibleForTesting
    private Point zzbka = new Point();
    @Nullable
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb = new WeakReference(null);
    private final WeakReference<View> zzbke;
    private final Map<String, WeakReference<View>> zzbkf = new HashMap();
    private final Map<String, WeakReference<View>> zzbkg = new HashMap();
    private final Map<String, WeakReference<View>> zzbkh = new HashMap();

    public zzpp(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        zzbv.zzfg();
        zzaor.zza(view, (OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza(view, (OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzbke = new WeakReference(view);
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            View view2 = (View) entry.getValue();
            if (view2 != null) {
                this.zzbkf.put(str, new WeakReference(view2));
                if (!(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) || UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str))) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzbkh.putAll(this.zzbkf);
        for (Entry entry2 : hashMap2.entrySet()) {
            View view3 = (View) entry2.getValue();
            if (view3 != null) {
                this.zzbkg.put((String) entry2.getKey(), new WeakReference(view3));
                view3.setOnTouchListener(this);
            }
        }
        this.zzbkh.putAll(this.zzbkg);
        zznk.initialize(view.getContext());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(com.google.android.gms.internal.ads.zzpd r7) {
        /*
        r6 = this;
        r2 = r6.mLock;
        monitor-enter(r2);
        r3 = zzbjs;	 Catch:{ all -> 0x0039 }
        r4 = r3.length;	 Catch:{ all -> 0x0039 }
        r0 = 0;
        r1 = r0;
    L_0x0008:
        if (r1 >= r4) goto L_0x0029;
    L_0x000a:
        r0 = r3[r1];	 Catch:{ all -> 0x0039 }
        r5 = r6.zzbkh;	 Catch:{ all -> 0x0039 }
        r0 = r5.get(r0);	 Catch:{ all -> 0x0039 }
        r0 = (java.lang.ref.WeakReference) r0;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0025;
    L_0x0016:
        r0 = r0.get();	 Catch:{ all -> 0x0039 }
        r0 = (android.view.View) r0;	 Catch:{ all -> 0x0039 }
    L_0x001c:
        r1 = r0 instanceof android.widget.FrameLayout;	 Catch:{ all -> 0x0039 }
        if (r1 != 0) goto L_0x002b;
    L_0x0020:
        r7.zzkq();	 Catch:{ all -> 0x0039 }
        monitor-exit(r2);	 Catch:{ all -> 0x0039 }
    L_0x0024:
        return;
    L_0x0025:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0008;
    L_0x0029:
        r0 = 0;
        goto L_0x001c;
    L_0x002b:
        r1 = new com.google.android.gms.internal.ads.zzpr;	 Catch:{ all -> 0x0039 }
        r1.<init>(r6, r0);	 Catch:{ all -> 0x0039 }
        r3 = r7 instanceof com.google.android.gms.internal.ads.zzoy;	 Catch:{ all -> 0x0039 }
        if (r3 == 0) goto L_0x003c;
    L_0x0034:
        r7.zzb(r0, r1);	 Catch:{ all -> 0x0039 }
    L_0x0037:
        monitor-exit(r2);	 Catch:{ all -> 0x0039 }
        goto L_0x0024;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0039 }
        throw r0;
    L_0x003c:
        r7.zza(r0, r1);	 Catch:{ all -> 0x0039 }
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.zza(com.google.android.gms.internal.ads.zzpd):void");
    }

    private final boolean zza(String[] strArr) {
        for (Object obj : strArr) {
            if (this.zzbkf.get(obj) != null) {
                return true;
            }
        }
        for (Object obj2 : strArr) {
            if (this.zzbkg.get(obj2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzl(@Nullable View view) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                zzoz zzkn = this.zzbij instanceof zzoy ? ((zzoy) this.zzbij).zzkn() : this.zzbij;
                if (zzkn != null) {
                    zzkn.zzl(view);
                }
            }
        }
    }

    @VisibleForTesting
    private final int zzv(int i) {
        int zzb;
        synchronized (this.mLock) {
            zzkb.zzif();
            zzb = zzamu.zzb(this.zzbij.getContext(), i);
        }
        return zzb;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        /*
        r7 = this;
        r6 = r7.mLock;
        monitor-enter(r6);
        r0 = r7.zzbij;	 Catch:{ all -> 0x0015 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);	 Catch:{ all -> 0x0015 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r7.zzbke;	 Catch:{ all -> 0x0015 }
        r5 = r0.get();	 Catch:{ all -> 0x0015 }
        r5 = (android.view.View) r5;	 Catch:{ all -> 0x0015 }
        if (r5 != 0) goto L_0x0018;
    L_0x0013:
        monitor-exit(r6);	 Catch:{ all -> 0x0015 }
        goto L_0x0008;
    L_0x0015:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0015 }
        throw r0;
    L_0x0018:
        r3 = new android.os.Bundle;	 Catch:{ all -> 0x0015 }
        r3.<init>();	 Catch:{ all -> 0x0015 }
        r0 = "x";
        r1 = r7.zzbjz;	 Catch:{ all -> 0x0015 }
        r1 = r1.x;	 Catch:{ all -> 0x0015 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0015 }
        r1 = (float) r1;	 Catch:{ all -> 0x0015 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0015 }
        r0 = "y";
        r1 = r7.zzbjz;	 Catch:{ all -> 0x0015 }
        r1 = r1.y;	 Catch:{ all -> 0x0015 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0015 }
        r1 = (float) r1;	 Catch:{ all -> 0x0015 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0015 }
        r0 = "start_x";
        r1 = r7.zzbka;	 Catch:{ all -> 0x0015 }
        r1 = r1.x;	 Catch:{ all -> 0x0015 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0015 }
        r1 = (float) r1;	 Catch:{ all -> 0x0015 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0015 }
        r0 = "start_y";
        r1 = r7.zzbka;	 Catch:{ all -> 0x0015 }
        r1 = r1.y;	 Catch:{ all -> 0x0015 }
        r1 = r7.zzv(r1);	 Catch:{ all -> 0x0015 }
        r1 = (float) r1;	 Catch:{ all -> 0x0015 }
        r3.putFloat(r0, r1);	 Catch:{ all -> 0x0015 }
        r0 = r7.zzbjx;	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x0094;
    L_0x005d:
        r0 = r7.zzbjx;	 Catch:{ all -> 0x0015 }
        r0 = r0.equals(r8);	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x0094;
    L_0x0065:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0015 }
        r0 = r0 instanceof com.google.android.gms.internal.ads.zzoy;	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x0088;
    L_0x006b:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0015 }
        r0 = (com.google.android.gms.internal.ads.zzoy) r0;	 Catch:{ all -> 0x0015 }
        r0 = r0.zzkn();	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x0086;
    L_0x0075:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0015 }
        r0 = (com.google.android.gms.internal.ads.zzoy) r0;	 Catch:{ all -> 0x0015 }
        r0 = r0.zzkn();	 Catch:{ all -> 0x0015 }
        r2 = "1007";
        r4 = r7.zzbkh;	 Catch:{ all -> 0x0015 }
        r1 = r8;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0015 }
    L_0x0086:
        monitor-exit(r6);	 Catch:{ all -> 0x0015 }
        goto L_0x0008;
    L_0x0088:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0015 }
        r2 = "1007";
        r4 = r7.zzbkh;	 Catch:{ all -> 0x0015 }
        r1 = r8;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0015 }
        goto L_0x0086;
    L_0x0094:
        r0 = r7.zzbij;	 Catch:{ all -> 0x0015 }
        r1 = r7.zzbkh;	 Catch:{ all -> 0x0015 }
        r0.zza(r8, r1, r3, r5);	 Catch:{ all -> 0x0015 }
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                View view = (View) this.zzbke.get();
                if (view != null) {
                    this.zzbij.zzc(view, this.zzbkh);
                }
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                View view = (View) this.zzbke.get();
                if (view != null) {
                    this.zzbij.zzc(view, this.zzbkh);
                }
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
            } else {
                View view2 = (View) this.zzbke.get();
                if (view2 == null) {
                } else {
                    int[] iArr = new int[2];
                    view2.getLocationOnScreen(iArr);
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
        }
        return false;
    }

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            zzl(null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (unwrap instanceof zzpd) {
                zzpd com_google_android_gms_internal_ads_zzpd = (zzpd) unwrap;
                if (com_google_android_gms_internal_ads_zzpd.zzkk()) {
                    zzfp com_google_android_gms_internal_ads_zzfp;
                    View view;
                    View view2 = (View) this.zzbke.get();
                    if (!(this.zzbij == null || view2 == null)) {
                        if (((Boolean) zzkb.zzik().zzd(zznk.zzbbu)).booleanValue()) {
                            this.zzbij.zzb(view2, this.zzbkh);
                        }
                    }
                    synchronized (this.mLock) {
                        if (this.zzbij instanceof zzpd) {
                            zzpd com_google_android_gms_internal_ads_zzpd2 = (zzpd) this.zzbij;
                            View view3 = (View) this.zzbke.get();
                            if (!(com_google_android_gms_internal_ads_zzpd2 == null || com_google_android_gms_internal_ads_zzpd2.getContext() == null || view3 == null || !zzbv.zzfh().zzu(view3.getContext()))) {
                                zzft zzks = com_google_android_gms_internal_ads_zzpd2.zzks();
                                if (zzks != null) {
                                    zzks.zzx(false);
                                }
                                com_google_android_gms_internal_ads_zzfp = (zzfp) this.zzbkb.get();
                                if (!(com_google_android_gms_internal_ads_zzfp == null || zzks == null)) {
                                    com_google_android_gms_internal_ads_zzfp.zzb(zzks);
                                }
                            }
                        }
                    }
                    if ((this.zzbij instanceof zzoy) && ((zzoy) this.zzbij).zzkm()) {
                        ((zzoy) this.zzbij).zzc(com_google_android_gms_internal_ads_zzpd);
                    } else {
                        this.zzbij = com_google_android_gms_internal_ads_zzpd;
                        if (com_google_android_gms_internal_ads_zzpd instanceof zzoy) {
                            ((zzoy) com_google_android_gms_internal_ads_zzpd).zzc(null);
                        }
                    }
                    String[] strArr = new String[]{NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
                    for (int i = 0; i < 2; i++) {
                        WeakReference weakReference = (WeakReference) this.zzbkh.get(strArr[i]);
                        if (weakReference != null) {
                            view = (View) weakReference.get();
                            break;
                        }
                    }
                    view = null;
                    if (view == null) {
                        zzane.zzdk("Ad choices asset view is not provided.");
                    } else {
                        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
                        if (viewGroup != null) {
                            this.zzbjx = com_google_android_gms_internal_ads_zzpd.zza((OnClickListener) this, true);
                            if (this.zzbjx != null) {
                                this.zzbkh.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzbjx));
                                this.zzbkf.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzbjx));
                                viewGroup.removeAllViews();
                                viewGroup.addView(this.zzbjx);
                            }
                        }
                    }
                    com_google_android_gms_internal_ads_zzpd.zza(view2, this.zzbkf, this.zzbkg, (OnTouchListener) this, (OnClickListener) this);
                    zzakk.zzcrm.post(new zzpq(this, com_google_android_gms_internal_ads_zzpd));
                    zzl(view2);
                    this.zzbij.zzj(view2);
                    synchronized (this.mLock) {
                        if (this.zzbij instanceof zzpd) {
                            com_google_android_gms_internal_ads_zzpd = (zzpd) this.zzbij;
                            view2 = (View) this.zzbke.get();
                            if (!(com_google_android_gms_internal_ads_zzpd == null || com_google_android_gms_internal_ads_zzpd.getContext() == null || view2 == null || !zzbv.zzfh().zzu(view2.getContext()))) {
                                com_google_android_gms_internal_ads_zzfp = (zzfp) this.zzbkb.get();
                                if (com_google_android_gms_internal_ads_zzfp == null) {
                                    com_google_android_gms_internal_ads_zzfp = new zzfp(view2.getContext(), view2);
                                    this.zzbkb = new WeakReference(com_google_android_gms_internal_ads_zzfp);
                                }
                                com_google_android_gms_internal_ads_zzfp.zza(com_google_android_gms_internal_ads_zzpd.zzks());
                            }
                        }
                    }
                    return;
                }
                zzane.m3427e("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            zzane.zzdk("Not an instance of native engine. This is most likely a transient error");
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }
}

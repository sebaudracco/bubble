package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@zzadh
@TargetApi(14)
public final class zzfp implements ActivityLifecycleCallbacks, OnAttachStateChangeListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private static final long zzagc = ((Long) zzkb.zzik().zzd(zznk.zzazt)).longValue();
    private zzamj zzadz = new zzamj(zzagc);
    private final Context zzaeo;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private boolean zzafd = false;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzafe;
    private final Rect zzafh;
    private Application zzagd;
    private WeakReference<ViewTreeObserver> zzage;
    private WeakReference<View> zzagf;
    private zzfu zzagg;
    private int zzagh = -1;
    private final HashSet<zzft> zzagi = new HashSet();
    private final DisplayMetrics zzagj;

    public zzfp(Context context, View view) {
        this.zzaeo = context.getApplicationContext();
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) this.zzaeo.getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        if (this.zzaeo instanceof Application) {
            this.zzagd = (Application) this.zzaeo;
            this.zzagg = new zzfu((Application) this.zzaeo, this);
        }
        this.zzagj = context.getResources().getDisplayMetrics();
        this.zzafh = new Rect();
        this.zzafh.right = this.zzaeu.getDefaultDisplay().getWidth();
        this.zzafh.bottom = this.zzaeu.getDefaultDisplay().getHeight();
        View view2 = this.zzagf != null ? (View) this.zzagf.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzagf = new WeakReference(view);
        if (view != null) {
            if (zzbv.zzem().isAttachedToWindow(view)) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzn(rect.left), zzn(rect.top), zzn(rect.right), zzn(rect.bottom));
    }

    private final void zza(Activity activity, int i) {
        if (this.zzagf != null) {
            Window window = activity.getWindow();
            if (window != null) {
                View peekDecorView = window.peekDecorView();
                View view = (View) this.zzagf.get();
                if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                    this.zzagh = i;
                }
            }
        }
    }

    private final void zzao() {
        zzbv.zzek();
        zzakk.zzcrm.post(new zzfq(this));
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzage = new WeakReference(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzafe == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzafe = new zzfr(this);
            zzbv.zzfk().zza(this.zzaeo, this.zzafe, intentFilter);
        }
        if (this.zzagd != null) {
            try {
                this.zzagd.registerActivityLifecycleCallbacks(this.zzagg);
            } catch (Throwable e) {
                zzane.zzb("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final void zzf(View view) {
        ViewTreeObserver viewTreeObserver;
        try {
            if (this.zzage != null) {
                viewTreeObserver = (ViewTreeObserver) this.zzage.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzage = null;
            }
        } catch (Throwable e) {
            zzane.zzb("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnScrollChangedListener(this);
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
        } catch (Throwable e2) {
            zzane.zzb("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzafe != null) {
            try {
                zzbv.zzfk().zza(this.zzaeo, this.zzafe);
            } catch (Throwable e22) {
                zzane.zzb("Failed trying to unregister the receiver", e22);
            } catch (Throwable e222) {
                zzbv.zzeo().zza(e222, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzafe = null;
        }
        if (this.zzagd != null) {
            try {
                this.zzagd.unregisterActivityLifecycleCallbacks(this.zzagg);
            } catch (Throwable e2222) {
                zzane.zzb("Error registering activity lifecycle callbacks.", e2222);
            }
        }
    }

    private final void zzm(int i) {
        if (this.zzagi.size() != 0 && this.zzagf != null) {
            View view = (View) this.zzagf.get();
            Object obj = i == 1 ? 1 : null;
            Object obj2 = view == null ? 1 : null;
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            boolean z = false;
            Rect rect3 = new Rect();
            boolean z2 = false;
            Rect rect4 = new Rect();
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            if (view != null) {
                z = view.getGlobalVisibleRect(rect2);
                z2 = view.getLocalVisibleRect(rect3);
                view.getHitRect(rect4);
                try {
                    view.getLocationOnScreen(iArr);
                    view.getLocationInWindow(iArr2);
                } catch (Throwable e) {
                    zzane.zzb("Failure getting view location.", e);
                }
                rect.left = iArr[0];
                rect.top = iArr[1];
                rect.right = rect.left + view.getWidth();
                rect.bottom = rect.top + view.getHeight();
            }
            int windowVisibility = view != null ? view.getWindowVisibility() : 8;
            if (this.zzagh != -1) {
                windowVisibility = this.zzagh;
            }
            boolean z3 = obj2 == null && zzbv.zzek().zza(view, this.zzaev, this.zzaew) && z && z2 && windowVisibility == 0;
            if (obj != null && !this.zzadz.tryAcquire() && z3 == this.zzafd) {
                return;
            }
            if (z3 || this.zzafd || i != 1) {
                zzfs com_google_android_gms_internal_ads_zzfs = new zzfs(zzbv.zzer().elapsedRealtime(), this.zzaev.isScreenOn(), view != null ? zzbv.zzem().isAttachedToWindow(view) : false, view != null ? view.getWindowVisibility() : 8, zza(this.zzafh), zza(rect), zza(rect2), z, zza(rect3), z2, zza(rect4), this.zzagj.density, z3);
                Iterator it = this.zzagi.iterator();
                while (it.hasNext()) {
                    ((zzft) it.next()).zza(com_google_android_gms_internal_ads_zzfs);
                }
                this.zzafd = z3;
            }
        }
    }

    private final int zzn(int i) {
        return (int) (((float) i) / this.zzagj.density);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzm(3);
        zzao();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzm(3);
        zzao();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzm(3);
        zzao();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivityStopped(Activity activity) {
        zzm(3);
        zzao();
    }

    public final void onGlobalLayout() {
        zzm(2);
        zzao();
    }

    public final void onScrollChanged() {
        zzm(1);
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzagh = -1;
        zze(view);
        zzm(3);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzagh = -1;
        zzm(3);
        zzao();
        zzf(view);
    }

    public final void zza(zzft com_google_android_gms_internal_ads_zzft) {
        this.zzagi.add(com_google_android_gms_internal_ads_zzft);
        zzm(3);
    }

    public final void zzb(zzft com_google_android_gms_internal_ads_zzft) {
        this.zzagi.remove(com_google_android_gms_internal_ads_zzft);
    }

    public final void zzgm() {
        zzm(4);
    }
}

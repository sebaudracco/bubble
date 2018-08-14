package com.vungle.publisher;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.env.i;
import com.vungle.publisher.env.w;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.log.C1654g;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.py.C1669a;
import java.util.Arrays;
import java.util.logging.Level;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class VunglePubBase {
    public static final String VERSION = (w.c + BuildConfig.VERSION_NAME);
    @Inject
    C1591c f2700a;
    @Inject
    InitializationEventListener f2701b;
    @Inject
    C1669a f2702c;
    @Inject
    qo f2703d;
    @Inject
    ci f2704e;
    @Inject
    protected i f2705f;
    @Inject
    qg f2706g;
    @Inject
    AdConfig f2707h;
    @Inject
    C1674u f2708i;
    @Inject
    C1613o f2709j;
    @Inject
    C1614r f2710k;
    @Inject
    mj$a f2711l;
    @Inject
    C1654g f2712m;
    private boolean f2713n;

    protected VunglePubBase() {
    }

    protected void init(@NonNull Context context, @NonNull String vungleAppId, @Size(min = 1) @NonNull String[] placementReferenceIds, @Nullable VungleInitListener initListener) {
        try {
            Logger.d("Vungle", "init SDK requested with placements: " + zk.a(placementReferenceIds));
            if (this.f2713n && this.f2710k.f2918a.get()) {
                Logger.d("Vungle", "already initialized");
                this.f2712m.f3083a.log(Level.FINE, "already initialized");
                setInitListener(initListener);
                this.f2706g.m4568a(new qk());
            } else if (this.f2713n && this.f2710k.f2919b.get()) {
                Logger.d("Vungle", "initialization already in progress, ignoring this request");
                this.f2712m.f3083a.log(Level.FINE, "initialization already in progress, ignoring this request");
            } else {
                m3463a(context, vungleAppId);
                this.f2712m.m4348a();
                setInitListener(initListener);
                if (!m3464a()) {
                    m3462a("device does not meet minimum Android API level for Vungle SDK", null);
                    this.f2712m.f3083a.log(Level.SEVERE, "device does not meet minimum Android API level for Vungle SDK");
                } else if (placementReferenceIds == null || placementReferenceIds.length < 1) {
                    m3462a("need one or more placement reference IDs for initialization, got none", null);
                    this.f2712m.f3083a.log(Level.SEVERE, "need one or more placement reference IDs for initialization, got none");
                } else if (zj.b(context)) {
                    this.f2712m.f3083a.log(Level.INFO, VERSION + " init(" + vungleAppId + ")");
                    this.f2703d.m4574b();
                    this.f2709j.m3926b(Arrays.asList(placementReferenceIds));
                    this.f2701b.register();
                    this.f2709j.m3926b(Arrays.asList(placementReferenceIds));
                    m3461a(context);
                } else {
                    m3462a("initialization failed due to required permissions missing", null);
                    this.f2712m.f3083a.log(Level.SEVERE, "initialization failed due to required permissions missing");
                }
            }
        } catch (Exception e) {
            m3462a("initialization failed with an exception", e);
        }
    }

    public void setInitListener(VungleInitListener initListener) {
        String str = Logger.EVENT_TAG;
        if (initListener == null) {
            Logger.d(Logger.EVENT_TAG, "ignoring set null init listener");
            return;
        }
        Logger.d(Logger.EVENT_TAG, "adding init listener " + initListener);
        this.f2702c.m4562a(initListener).register();
    }

    private void m3462a(String str, Exception exception) {
        Logger.e("Vungle", str, exception);
        if (this.f2713n) {
            this.f2706g.m4568a(new qj(new Throwable(str, exception)));
        }
    }

    private void m3461a(Context context) {
        this.f2704e.m3512a();
        this.f2705f.n();
        this.f2711l.m4379a(context);
    }

    protected boolean m3464a() {
        String str = Logger.DEVICE_TAG;
        if (VERSION.SDK_INT >= 14) {
            return true;
        }
        Logger.w(Logger.DEVICE_TAG, "Device Android API level " + VERSION.SDK_INT + " does not meet required minimum " + 14);
        return false;
    }

    protected void m3463a(Context context, String str) {
        if (Injector.getInstance().d()) {
            Logger.d("Vungle", "already injected");
            return;
        }
        Injector.getInstance().a(context, str);
        Injector.c().m4199a(this);
        Logger.d("Vungle", "injection successful");
        this.f2713n = true;
    }

    protected void addEventListeners(VungleAdEventListener... eventListeners) {
        try {
            if (this.f2713n) {
                this.f2709j.m3921a(eventListeners);
            } else {
                Logger.d("Vungle", "Error in addEventListeners(): VunglePub not injected/initialized");
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error adding eventListeners", e);
        }
    }

    protected void clearAndSetEventListeners(VungleAdEventListener... eventListeners) {
        try {
            if (this.f2713n) {
                this.f2709j.m3933c(eventListeners);
            } else {
                Logger.d("Vungle", "Error in clearAndSetEventListeners(): VunglePub not injected/initialized");
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error setting event listeners", e);
        }
    }

    protected void clearEventListeners() {
        try {
            if (this.f2713n) {
                this.f2709j.m3915a();
            } else {
                Logger.d("Vungle", "Error in clearEventListener(): VunglePub not injected/initialized");
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error clearing event listeners", e);
        }
    }

    protected void removeEventListeners(VungleAdEventListener... eventListeners) {
        try {
            if (this.f2713n) {
                this.f2709j.m3928b(eventListeners);
            } else {
                Logger.d("Vungle", "Error in removeEventListeners(): VunglePub not injected/initialized");
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error removing eventListeners", e);
        }
    }

    protected AdConfig getGlobalAdConfig() {
        try {
            if (this.f2713n) {
                return this.f2707h;
            }
            Logger.e("Vungle", "error in getGlobalAdConfig() - VunglePub not injected");
            return null;
        } catch (Throwable e) {
            Logger.e("Vungle", "error getting globalAdConfig", e);
            return null;
        }
    }

    public void onResume() {
        try {
            if (this.f2713n && this.f2710k.f2918a.get()) {
                this.f2710k.m3951c();
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error onResume()", e);
        }
    }

    public void onPause() {
        try {
            if (this.f2713n && this.f2710k.f2918a.get()) {
                this.f2710k.m3958h();
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error onPause()", e);
        }
    }

    public boolean isInitialized() {
        return this.f2713n && this.f2710k.f2918a.get();
    }

    public boolean isAdPlayable(@NonNull String placementReferenceId) {
        try {
            Logger.d("Vungle", "isAdPlayable called for placement: " + placementReferenceId);
            if (this.f2713n && this.f2710k.f2918a.get()) {
                return this.f2700a.m3494b(placementReferenceId);
            }
            Logger.w("Vungle", "Wait until successful initialization before calling isAdPlayable()");
            return false;
        } catch (Throwable e) {
            Logger.e("Vungle", "error returning ad playable", e);
            return false;
        }
    }

    protected void loadAd(@NonNull String placementReferenceId) {
        try {
            Logger.d(Logger.AD_TAG, "VunglePub.loadAd() called for placement: " + placementReferenceId);
            if (!this.f2713n) {
                Logger.e("Vungle", "Call init() before loadAd()");
            } else if (this.f2710k.f2918a.get()) {
                this.f2700a.m3500e(placementReferenceId);
            } else {
                Logger.w("Vungle", "Wait until successful initialization before calling loadAd()");
                this.f2706g.m4568a(new bn(placementReferenceId));
            }
        } catch (Throwable e) {
            Logger.e("Vungle", "error in loadAd", e);
            if (this.f2713n) {
                this.f2706g.m4568a(new bl(placementReferenceId));
            }
        }
    }

    protected void playAd(@NonNull String placementReferenceId, @Nullable AdConfig adConfig) {
        String str = Logger.AD_TAG;
        try {
            Logger.d(Logger.AD_TAG, "playAd() called for placement: " + placementReferenceId);
            if (!this.f2713n) {
                Logger.e(Logger.AD_TAG, "Call init() before playAd()");
            } else if (this.f2710k.f2918a.get()) {
                this.f2700a.m3489a(placementReferenceId, this.f2708i.m4690a(this.f2707h, adConfig));
            } else {
                this.f2706g.m4568a(new bo(null, placementReferenceId));
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            if (this.f2713n) {
                this.f2706g.m4568a(new bm(null, placementReferenceId));
            }
        }
    }

    protected boolean closeFlexViewAd(String placementReferenceId) {
        return this.f2700a.m3501f(placementReferenceId);
    }
}

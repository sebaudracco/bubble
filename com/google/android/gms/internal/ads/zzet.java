package com.google.android.gms.internal.ads;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.mobfox.sdk.networking.RequestParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzet implements OnGlobalLayoutListener, OnScrollChangedListener {
    private final Object mLock = new Object();
    private boolean zzaaq = false;
    private zzamj zzadz;
    private final Context zzaeo;
    private final WeakReference<zzajh> zzaeq;
    private WeakReference<ViewTreeObserver> zzaer;
    private final zzgd zzaes;
    protected final zzer zzaet;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private final DisplayMetrics zzaex;
    @Nullable
    private zzfa zzaey;
    private boolean zzaez;
    private boolean zzafa = false;
    private boolean zzafb;
    private boolean zzafc;
    private boolean zzafd;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzafe;
    private final HashSet<zzeq> zzaff = new HashSet();
    private final HashSet<zzfo> zzafg = new HashSet();
    private final Rect zzafh = new Rect();
    private final zzew zzafi;
    private float zzafj;

    public zzet(Context context, zzjn com_google_android_gms_internal_ads_zzjn, zzajh com_google_android_gms_internal_ads_zzajh, zzang com_google_android_gms_internal_ads_zzang, zzgd com_google_android_gms_internal_ads_zzgd) {
        this.zzaeq = new WeakReference(com_google_android_gms_internal_ads_zzajh);
        this.zzaes = com_google_android_gms_internal_ads_zzgd;
        this.zzaer = new WeakReference(null);
        this.zzafb = true;
        this.zzafd = false;
        this.zzadz = new zzamj(200);
        this.zzaet = new zzer(UUID.randomUUID().toString(), com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zzjn.zzarb, com_google_android_gms_internal_ads_zzajh.zzcob, com_google_android_gms_internal_ads_zzajh.zzfz(), com_google_android_gms_internal_ads_zzjn.zzare);
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        this.zzaeo = context;
        this.zzafi = new zzew(this, new Handler());
        this.zzaeo.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.zzafi);
        this.zzaex = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzaeu.getDefaultDisplay();
        this.zzafh.right = defaultDisplay.getWidth();
        this.zzafh.bottom = defaultDisplay.getHeight();
        zzgb();
    }

    @VisibleForTesting
    private final boolean isScreenOn() {
        return VERSION.SDK_INT >= 20 ? this.zzaev.isInteractive() : this.zzaev.isScreenOn();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    private final JSONObject zza(@Nullable View view, @Nullable Boolean bool) throws JSONException {
        if (view == null) {
            return zzgg().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbv.zzem().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Throwable e) {
            zzane.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject zzgg = zzgg();
        zzgg.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(this.zzafh.top, this.zzaex)).put("bottom", zza(this.zzafh.bottom, this.zzaex)).put("left", zza(this.zzafh.left, this.zzaex)).put("right", zza(this.zzafh.right, this.zzaex))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzaex)).put("bottom", zza(rect.bottom, this.zzaex)).put("left", zza(rect.left, this.zzaex)).put("right", zza(rect.right, this.zzaex))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzaex)).put("bottom", zza(rect2.bottom, this.zzaex)).put("left", zza(rect2.left, this.zzaex)).put("right", zza(rect2.right, this.zzaex))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzaex)).put("bottom", zza(rect3.bottom, this.zzaex)).put("left", zza(rect3.left, this.zzaex)).put("right", zza(rect3.right, this.zzaex))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzaex)).put("bottom", zza(rect4.bottom, this.zzaex)).put("left", zza(rect4.left, this.zzaex)).put("right", zza(rect4.right, this.zzaex))).put("screenDensity", (double) this.zzaex.density);
        if (bool == null) {
            bool = Boolean.valueOf(zzbv.zzek().zza(view, this.zzaev, this.zzaew));
        }
        zzgg.put("isVisible", bool.booleanValue());
        return zzgg;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzafg);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzfo) obj).zzb(zza, z);
            }
        } catch (Throwable th) {
            zzane.zzb("Skipping active view message.", th);
        }
    }

    private final void zzgd() {
        if (this.zzaey != null) {
            this.zzaey.zza(this);
        }
    }

    private final void zzgf() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzaer.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zzgg() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzaet.zzfw()).put("activeViewJSON", this.zzaet.zzfx()).put("timestamp", zzbv.zzer().elapsedRealtime()).put("adFormat", this.zzaet.zzfv()).put("hashCode", this.zzaet.zzfy()).put("isMraid", this.zzaet.zzfz()).put("isStopped", this.zzafa).put("isPaused", this.zzaaq).put("isNative", this.zzaet.zzga()).put("isScreenOn", isScreenOn()).put("appMuted", zzbv.zzfj().zzdp()).put("appVolume", (double) zzbv.zzfj().zzdo()).put("deviceVolume", (double) this.zzafj);
        return jSONObject;
    }

    public final void onGlobalLayout() {
        zzl(2);
    }

    public final void onScrollChanged() {
        zzl(1);
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzaaq = true;
            zzl(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzaaq = false;
            zzl(3);
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzafa = true;
            zzl(3);
        }
    }

    public final void zza(zzfa com_google_android_gms_internal_ads_zzfa) {
        synchronized (this.mLock) {
            this.zzaey = com_google_android_gms_internal_ads_zzfa;
        }
    }

    public final void zza(zzfo com_google_android_gms_internal_ads_zzfo) {
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzafe != null) {
                } else {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzafe = new zzeu(this);
                    zzbv.zzfk().zza(this.zzaeo, this.zzafe, intentFilter);
                }
            }
            zzl(3);
        }
        this.zzafg.add(com_google_android_gms_internal_ads_zzfo);
        try {
            com_google_android_gms_internal_ads_zzfo.zzb(zza(zza(this.zzaes.zzgh(), null)), false);
        } catch (Throwable e) {
            zzane.zzb("Skipping measurement update for new client.", e);
        }
    }

    final void zza(zzfo com_google_android_gms_internal_ads_zzfo, Map<String, String> map) {
        String str = "Received request to untrack: ";
        String valueOf = String.valueOf(this.zzaet.zzfy());
        zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzb(com_google_android_gms_internal_ads_zzfo);
    }

    public final void zzb(zzfo com_google_android_gms_internal_ads_zzfo) {
        this.zzafg.remove(com_google_android_gms_internal_ads_zzfo);
        com_google_android_gms_internal_ads_zzfo.zzgl();
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                zzgf();
                synchronized (this.mLock) {
                    if (this.zzafe != null) {
                        try {
                            zzbv.zzfk().zza(this.zzaeo, this.zzafe);
                        } catch (Throwable e) {
                            zzane.zzb("Failed trying to unregister the receiver", e);
                        } catch (Throwable e2) {
                            zzbv.zzeo().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzafe = null;
                    }
                }
                this.zzaeo.getContentResolver().unregisterContentObserver(this.zzafi);
                this.zzafb = false;
                zzgd();
                ArrayList arrayList = new ArrayList(this.zzafg);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzfo) obj);
                }
            }
        }
    }

    final boolean zzc(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzaet.zzfy());
    }

    final void zzd(Map<String, String> map) {
        zzl(3);
    }

    final void zze(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("isVisible")) || SchemaSymbols.ATTVAL_TRUE.equals(map.get("isVisible"));
            Iterator it = this.zzaff.iterator();
            while (it.hasNext()) {
                ((zzeq) it.next()).zza(this, z);
            }
        }
    }

    public final void zzgb() {
        this.zzafj = zzalb.zzay(this.zzaeo);
    }

    public final void zzgc() {
        synchronized (this.mLock) {
            if (this.zzafb) {
                this.zzafc = true;
                try {
                    JSONObject zzgg = zzgg();
                    zzgg.put("doneReasonCode", RequestParams.f9038U);
                    zza(zzgg, true);
                } catch (Throwable e) {
                    zzane.zzb("JSON failure while processing active view data.", e);
                } catch (Throwable e2) {
                    zzane.zzb("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zzaet.zzfy());
                zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    public final boolean zzge() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzafb;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void zzl(int r8) {
        /*
        r7 = this;
        r2 = 0;
        r1 = 1;
        r4 = r7.mLock;
        monitor-enter(r4);
        r0 = r7.zzafg;	 Catch:{ all -> 0x005d }
        r3 = r0.iterator();	 Catch:{ all -> 0x005d }
    L_0x000b:
        r0 = r3.hasNext();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0026;
    L_0x0011:
        r0 = r3.next();	 Catch:{ all -> 0x005d }
        r0 = (com.google.android.gms.internal.ads.zzfo) r0;	 Catch:{ all -> 0x005d }
        r0 = r0.zzgk();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x000b;
    L_0x001d:
        r0 = r1;
    L_0x001e:
        if (r0 == 0) goto L_0x0024;
    L_0x0020:
        r0 = r7.zzafb;	 Catch:{ all -> 0x005d }
        if (r0 != 0) goto L_0x0028;
    L_0x0024:
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
    L_0x0025:
        return;
    L_0x0026:
        r0 = r2;
        goto L_0x001e;
    L_0x0028:
        r0 = r7.zzaes;	 Catch:{ all -> 0x005d }
        r5 = r0.zzgh();	 Catch:{ all -> 0x005d }
        if (r5 == 0) goto L_0x0060;
    L_0x0030:
        r0 = com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x005d }
        r3 = r7.zzaev;	 Catch:{ all -> 0x005d }
        r6 = r7.zzaew;	 Catch:{ all -> 0x005d }
        r0 = r0.zza(r5, r3, r6);	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0060;
    L_0x003e:
        r3 = r1;
    L_0x003f:
        if (r5 == 0) goto L_0x0062;
    L_0x0041:
        if (r3 == 0) goto L_0x0062;
    L_0x0043:
        r0 = new android.graphics.Rect;	 Catch:{ all -> 0x005d }
        r0.<init>();	 Catch:{ all -> 0x005d }
        r6 = 0;
        r0 = r5.getGlobalVisibleRect(r0, r6);	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0062;
    L_0x004f:
        r0 = r1;
    L_0x0050:
        r2 = r7.zzaes;	 Catch:{ all -> 0x005d }
        r2 = r2.zzgi();	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x0064;
    L_0x0058:
        r7.zzgc();	 Catch:{ all -> 0x005d }
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x005d:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        throw r0;
    L_0x0060:
        r3 = r2;
        goto L_0x003f;
    L_0x0062:
        r0 = r2;
        goto L_0x0050;
    L_0x0064:
        if (r8 != r1) goto L_0x0074;
    L_0x0066:
        r2 = r7.zzadz;	 Catch:{ all -> 0x005d }
        r2 = r2.tryAcquire();	 Catch:{ all -> 0x005d }
        if (r2 != 0) goto L_0x0074;
    L_0x006e:
        r2 = r7.zzafd;	 Catch:{ all -> 0x005d }
        if (r0 != r2) goto L_0x0074;
    L_0x0072:
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x0074:
        if (r0 != 0) goto L_0x007e;
    L_0x0076:
        r2 = r7.zzafd;	 Catch:{ all -> 0x005d }
        if (r2 != 0) goto L_0x007e;
    L_0x007a:
        if (r8 != r1) goto L_0x007e;
    L_0x007c:
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x007e:
        r1 = java.lang.Boolean.valueOf(r3);	 Catch:{ JSONException -> 0x00d3, RuntimeException -> 0x00cb }
        r1 = r7.zza(r5, r1);	 Catch:{ JSONException -> 0x00d3, RuntimeException -> 0x00cb }
        r2 = 0;
        r7.zza(r1, r2);	 Catch:{ JSONException -> 0x00d3, RuntimeException -> 0x00cb }
        r7.zzafd = r0;	 Catch:{ JSONException -> 0x00d3, RuntimeException -> 0x00cb }
    L_0x008c:
        r0 = r7.zzaes;	 Catch:{ all -> 0x005d }
        r0 = r0.zzgj();	 Catch:{ all -> 0x005d }
        r1 = r0.zzgh();	 Catch:{ all -> 0x005d }
        if (r1 == 0) goto L_0x00c5;
    L_0x0098:
        r0 = r7.zzaer;	 Catch:{ all -> 0x005d }
        r0 = r0.get();	 Catch:{ all -> 0x005d }
        r0 = (android.view.ViewTreeObserver) r0;	 Catch:{ all -> 0x005d }
        r1 = r1.getViewTreeObserver();	 Catch:{ all -> 0x005d }
        if (r1 == r0) goto L_0x00c5;
    L_0x00a6:
        r7.zzgf();	 Catch:{ all -> 0x005d }
        r2 = r7.zzaez;	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x00b5;
    L_0x00ad:
        if (r0 == 0) goto L_0x00be;
    L_0x00af:
        r0 = r0.isAlive();	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x00be;
    L_0x00b5:
        r0 = 1;
        r7.zzaez = r0;	 Catch:{ all -> 0x005d }
        r1.addOnScrollChangedListener(r7);	 Catch:{ all -> 0x005d }
        r1.addOnGlobalLayoutListener(r7);	 Catch:{ all -> 0x005d }
    L_0x00be:
        r0 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x005d }
        r0.<init>(r1);	 Catch:{ all -> 0x005d }
        r7.zzaer = r0;	 Catch:{ all -> 0x005d }
    L_0x00c5:
        r7.zzgd();	 Catch:{ all -> 0x005d }
        monitor-exit(r4);	 Catch:{ all -> 0x005d }
        goto L_0x0025;
    L_0x00cb:
        r0 = move-exception;
    L_0x00cc:
        r1 = "Active view update failed.";
        com.google.android.gms.internal.ads.zzane.zza(r1, r0);	 Catch:{ all -> 0x005d }
        goto L_0x008c;
    L_0x00d3:
        r0 = move-exception;
        goto L_0x00cc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzet.zzl(int):void");
    }
}

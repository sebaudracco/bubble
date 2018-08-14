package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
final class zzasq extends zzasv implements OnGlobalLayoutListener, DownloadListener, zzaqw, zzuo {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    @Nullable
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    @GuardedBy("this")
    private String zzchp = "";
    private zznv zzdad;
    private final zzbo zzddb;
    @GuardedBy("this")
    private zzd zzddg;
    @GuardedBy("this")
    private zzasi zzddh;
    @GuardedBy("this")
    private boolean zzddi;
    @GuardedBy("this")
    private boolean zzddk;
    @GuardedBy("this")
    private boolean zzddl;
    @GuardedBy("this")
    private int zzddm;
    @GuardedBy("this")
    private boolean zzddn = true;
    @GuardedBy("this")
    private boolean zzddo = false;
    @GuardedBy("this")
    private zzarl zzddp;
    @GuardedBy("this")
    private boolean zzddq;
    @GuardedBy("this")
    private boolean zzddr;
    @GuardedBy("this")
    private zzox zzdds;
    @GuardedBy("this")
    private int zzddt;
    @GuardedBy("this")
    private int zzddu;
    private zznv zzddv;
    private zznv zzddw;
    private zznw zzddx;
    private WeakReference<OnClickListener> zzddy;
    @GuardedBy("this")
    private zzd zzddz;
    private Map<String, zzaqh> zzdeb;
    private zzasj zzdet;
    private float zzdeu;
    @GuardedBy("this")
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    @VisibleForTesting
    private zzasq(zzash com_google_android_gms_internal_ads_zzash, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) {
        super(com_google_android_gms_internal_ads_zzash);
        this.zzddh = com_google_android_gms_internal_ads_zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = com_google_android_gms_internal_ads_zzci;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzddb = com_google_android_gms_ads_internal_zzbo;
        this.zzwc = com_google_android_gms_ads_internal_zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        this.zzaee = new zzamt(zzvv().zzto(), this, this, null);
        zzbv.zzek().zza((Context) com_google_android_gms_internal_ads_zzash, com_google_android_gms_internal_ads_zzang.zzcw, getSettings());
        setDownloadListener(this);
        this.zzdeu = zzvv().getResources().getDisplayMetrics().density;
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
        zzvo();
        this.zzddx = new zznw(new zznx(true, "make_wv", this.zzus));
        this.zzddx.zzji().zzc(com_google_android_gms_internal_ads_zznx);
        this.zzdad = zznq.zzb(this.zzddx.zzji());
        this.zzddx.zza("native:view_create", this.zzdad);
        this.zzddw = null;
        this.zzddv = null;
        zzbv.zzem().zzaw(com_google_android_gms_internal_ads_zzash);
    }

    private final void zzal(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("isVisible", z ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        zzup.zza(this, "onAdVisibilityChanged", hashMap);
    }

    static zzasq zzc(Context context, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) {
        return new zzasq(new zzash(context), com_google_android_gms_internal_ads_zzasi, str, z, z2, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zznx, com_google_android_gms_ads_internal_zzbo, com_google_android_gms_ads_internal_zzw, com_google_android_gms_internal_ads_zzhs);
    }

    private final boolean zzvh() {
        if (!this.zzdet.zzfz() && !this.zzdet.zzuu()) {
            return false;
        }
        int i;
        int i2;
        zzbv.zzek();
        DisplayMetrics zza = zzakk.zza(this.zzaeu);
        zzkb.zzif();
        int zzb = zzamu.zzb(zza, zza.widthPixels);
        zzkb.zzif();
        int zzb2 = zzamu.zzb(zza, zza.heightPixels);
        Activity zzto = zzvv().zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i = zzb2;
            i2 = zzb;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            i2 = zzamu.zzb(zza, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(zza, zzf[1]);
        }
        if (this.zzbwy == zzb && this.zzbwz == zzb2 && this.zzbxb == i2 && this.zzbxc == i) {
            return false;
        }
        boolean z = (this.zzbwy == zzb && this.zzbwz == zzb2) ? false : true;
        this.zzbwy = zzb;
        this.zzbwz = zzb2;
        this.zzbxb = i2;
        this.zzbxc = i;
        new zzaal(this).zza(zzb, zzb2, i2, i, zza.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
    }

    private final void zzvj() {
        zznq.zza(this.zzddx.zzji(), this.zzdad, "aeh2");
    }

    private final synchronized void zzvk() {
        if (this.zzddk || this.zzddh.zzvs()) {
            zzane.zzck("Enabling hardware acceleration on an overlay.");
            zzvm();
        } else if (VERSION.SDK_INT < 18) {
            zzane.zzck("Disabling hardware acceleration on an AdView.");
            zzvl();
        } else {
            zzane.zzck("Enabling hardware acceleration on an AdView.");
            zzvm();
        }
    }

    private final synchronized void zzvl() {
        if (!this.zzddl) {
            zzbv.zzem().zzz(this);
        }
        this.zzddl = true;
    }

    private final synchronized void zzvm() {
        if (this.zzddl) {
            zzbv.zzem().zzy(this);
        }
        this.zzddl = false;
    }

    private final synchronized void zzvn() {
        this.zzdeb = null;
    }

    private final void zzvo() {
        if (this.zzddx != null) {
            zznx zzji = this.zzddx.zzji();
            if (zzji != null && zzbv.zzeo().zzpy() != null) {
                zzbv.zzeo().zzpy().zza(zzji);
            }
        }
    }

    public final OnClickListener getOnClickListener() {
        return (OnClickListener) this.zzddy.get();
    }

    public final synchronized int getRequestedOrientation() {
        return this.zzddm;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    protected final synchronized void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z2 = this.zzddq;
        if (this.zzdet == null || !this.zzdet.zzuu()) {
            z = z2;
        } else {
            if (!this.zzddr) {
                OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    if (this == null) {
                        throw null;
                    }
                    zzaor.zza((View) this, zzuv);
                }
                OnScrollChangedListener zzuw = this.zzdet.zzuw();
                if (zzuw != null) {
                    zzbv.zzfg();
                    if (this == null) {
                        throw null;
                    }
                    zzaor.zza((View) this, zzuw);
                }
                this.zzddr = true;
            }
            zzvh();
            z = true;
        }
        zzal(z);
    }

    protected final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzaee.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzddr && this.zzdet != null && this.zzdet.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                OnScrollChangedListener zzuw = this.zzdet.zzuw();
                if (zzuw != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzuw);
                }
                this.zzddr = false;
            }
        }
        zzal(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbv.zzek();
            zzakk.zza(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            zzane.zzck(new StringBuilder((String.valueOf(str).length() + 51) + String.valueOf(str4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(str).append(" / ").append(str4).toString());
        }
    }

    @TargetApi(21)
    protected final void onDraw(Canvas canvas) {
        if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
            super.onDraw(canvas);
            if (this.zzdet != null && this.zzdet.zzve() != null) {
                this.zzdet.zzve().zzda();
            }
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxx)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8 && ((axisValue > 0.0f && !canScrollVertically(-1)) || ((axisValue < 0.0f && !canScrollVertically(1)) || ((axisValue2 > 0.0f && !canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !canScrollHorizontally(1)))))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean zzvh = zzvh();
        zzd zzub = zzub();
        if (zzub != null && zzvh) {
            zzub.zznn();
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected final synchronized void onMeasure(int i, int i2) {
        if (isDestroyed()) {
            setMeasuredDimension(0, 0);
        } else if (isInEditMode() || this.zzddk || this.zzddh.zzvt()) {
            super.onMeasure(i, i2);
        } else if (this.zzddh.zzvu()) {
            zzarl zztm = zztm();
            float aspectRatio = zztm != null ? zztm.getAspectRatio() : 0.0f;
            if (aspectRatio == 0.0f) {
                super.onMeasure(i, i2);
            } else {
                int size = MeasureSpec.getSize(i);
                int size2 = MeasureSpec.getSize(i2);
                r1 = (int) (((float) size2) * aspectRatio);
                r0 = (int) (((float) size) / aspectRatio);
                if (size2 == 0 && r0 != 0) {
                    r1 = (int) (((float) r0) * aspectRatio);
                    size2 = r0;
                } else if (size == 0 && r1 != 0) {
                    r0 = (int) (((float) r1) / aspectRatio);
                    size = r1;
                }
                setMeasuredDimension(Math.min(r1, size), Math.min(r0, size2));
            }
        } else if (this.zzddh.isFluid()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbch)).booleanValue() || !PlatformVersion.isAtLeastJellyBeanMR1()) {
                super.onMeasure(i, i2);
            } else {
                zza("/contentHeight", new zzasr(this));
                zzbe("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                r1 = MeasureSpec.getSize(i);
                switch (this.zzddu) {
                    case -1:
                        r0 = MeasureSpec.getSize(i2);
                        break;
                    default:
                        r0 = (int) (((float) this.zzddu) * this.zzdeu);
                        break;
                }
                setMeasuredDimension(r1, r0);
            }
        } else if (this.zzddh.zzvs()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.zzaeu.getDefaultDisplay().getMetrics(displayMetrics);
            setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            Object obj;
            r1 = MeasureSpec.getMode(i);
            int size3 = MeasureSpec.getSize(i);
            int mode = MeasureSpec.getMode(i2);
            int size4 = MeasureSpec.getSize(i2);
            int i3 = (r1 == Integer.MIN_VALUE || r1 == 1073741824) ? size3 : Integer.MAX_VALUE;
            mode = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size4 : Integer.MAX_VALUE;
            Object obj2 = (this.zzddh.widthPixels > i3 || this.zzddh.heightPixels > mode) ? 1 : null;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfe)).booleanValue()) {
                obj = (((float) this.zzddh.widthPixels) / this.zzdeu > ((float) i3) / this.zzdeu || ((float) this.zzddh.heightPixels) / this.zzdeu > ((float) mode) / this.zzdeu) ? null : 1;
                if (obj2 == null) {
                    obj = obj2;
                }
            } else {
                obj = obj2;
            }
            if (obj != null) {
                zzane.zzdk("Not enough space to show ad. Needs " + ((int) (((float) this.zzddh.widthPixels) / this.zzdeu)) + "x" + ((int) (((float) this.zzddh.heightPixels) / this.zzdeu)) + " dp, but only has " + ((int) (((float) size3) / this.zzdeu)) + "x" + ((int) (((float) size4) / this.zzdeu)) + " dp.");
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
            }
        }
    }

    public final void onPause() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onPause();
            }
        } catch (Throwable e) {
            zzane.zzb("Could not pause webview.", e);
        }
    }

    public final void onResume() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onResume();
            }
        } catch (Throwable e) {
            zzane.zzb("Could not resume webview.", e);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzdet.zzuu()) {
            synchronized (this) {
                if (this.zzdds != null) {
                    this.zzdds.zzc(motionEvent);
                }
            }
        } else if (this.zzbjc != null) {
            this.zzbjc.zza(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.zzddy = new WeakReference(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void setRequestedOrientation(int i) {
        this.zzddm = i;
        if (this.zzddg != null) {
            this.zzddg.setRequestedOrientation(this.zzddm);
        }
    }

    public final void stopLoading() {
        try {
            super.stopLoading();
        } catch (Throwable e) {
            zzane.zzb("Could not stop loading webview.", e);
        }
    }

    public final void zza(zzc com_google_android_gms_ads_internal_overlay_zzc) {
        this.zzdet.zza(com_google_android_gms_ads_internal_overlay_zzc);
    }

    public final synchronized void zza(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzddg = com_google_android_gms_ads_internal_overlay_zzd;
    }

    public final synchronized void zza(zzarl com_google_android_gms_internal_ads_zzarl) {
        if (this.zzddp != null) {
            zzane.m3427e("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzddp = com_google_android_gms_internal_ads_zzarl;
        }
    }

    public final synchronized void zza(zzasi com_google_android_gms_internal_ads_zzasi) {
        this.zzddh = com_google_android_gms_internal_ads_zzasi;
        requestLayout();
    }

    public final void zza(zzasj com_google_android_gms_internal_ads_zzasj) {
        this.zzdet = com_google_android_gms_internal_ads_zzasj;
    }

    public final void zza(zzfs com_google_android_gms_internal_ads_zzfs) {
        synchronized (this) {
            this.zzddq = com_google_android_gms_internal_ads_zzfs.zztg;
        }
        zzal(com_google_android_gms_internal_ads_zzfs.zztg);
    }

    public final void zza(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        if (this.zzdet != null) {
            this.zzdet.zza(str, (zzv) com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        if (this.zzdet != null) {
            this.zzdet.zza(str, (Predicate) predicate);
        }
    }

    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zza(boolean z, int i) {
        this.zzdet.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzdet.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdet.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzdet.zzah(z);
    }

    public final void zzai(int i) {
        if (i == 0) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aebb2");
        }
        zzvj();
        if (this.zzddx.zzji() != null) {
            this.zzddx.zzji().zze("close_type", String.valueOf(i));
        }
        Map hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza(this, "onhide", hashMap);
    }

    public final synchronized void zzai(boolean z) {
        Object obj = z != this.zzddk ? 1 : null;
        this.zzddk = z;
        zzvk();
        if (obj != null) {
            new zzaal(this).zzby(z ? "expanded" : "default");
        }
    }

    public final synchronized void zzaj(boolean z) {
        this.zzddn = z;
    }

    public final synchronized void zzak(boolean z) {
        this.zzddt = (z ? 1 : -1) + this.zzddt;
        if (this.zzddt <= 0 && this.zzddg != null) {
            this.zzddg.zznq();
        }
    }

    protected final synchronized void zzam(boolean z) {
        if (!z) {
            zzvo();
            this.zzaee.zzsd();
            if (this.zzddg != null) {
                this.zzddg.close();
                this.zzddg.onDestroy();
                this.zzddg = null;
            }
        }
        this.zzdet.reset();
        zzbv.zzff();
        zzaqg.zzb((zzapw) this);
        zzvn();
    }

    public final synchronized void zzb(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzddz = com_google_android_gms_ads_internal_overlay_zzd;
    }

    public final synchronized void zzb(zzox com_google_android_gms_internal_ads_zzox) {
        this.zzdds = com_google_android_gms_internal_ads_zzox;
    }

    public final void zzb(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        if (this.zzdet != null) {
            this.zzdet.zzb(str, (zzv) com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    public final synchronized void zzbe(String str) {
        if (isDestroyed()) {
            zzane.zzdk("The webview is destroyed. Ignoring action.");
        } else {
            super.zzbe(str);
        }
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final void zzbm(Context context) {
        zzvv().setBaseContext(context);
        this.zzaee.zzi(zzvv().zzto());
    }

    public final synchronized void zzc(String str, String str2, @Nullable String str3) {
        String zzb;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
            zzb = zzarx.zzb(str2, zzarx.zzvp());
        } else {
            zzb = str2;
        }
        super.loadDataWithBaseURL(str, zzb, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", str3);
    }

    public final synchronized void zzcl() {
        this.zzddo = true;
        if (this.zzddb != null) {
            this.zzddb.zzcl();
        }
    }

    public final synchronized void zzcm() {
        this.zzddo = false;
        if (this.zzddb != null) {
            this.zzddb.zzcm();
        }
    }

    public final synchronized void zzdr(String str) {
        if (str == null) {
            str = "";
        }
        this.zzchp = str;
    }

    public final void zzf(String str, String str2) {
        zzup.zza(this, str, str2);
    }

    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            this.zzddv = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_show", this.zzddv);
        }
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza(this, "onshow", hashMap);
    }

    public final void zznp() {
        zzd zzub = zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }

    public final synchronized String zzol() {
        return this.zzchp;
    }

    public final zzapn zztl() {
        return null;
    }

    public final synchronized zzarl zztm() {
        return this.zzddp;
    }

    public final zznv zztn() {
        return this.zzdad;
    }

    public final Activity zzto() {
        return zzvv().zzto();
    }

    public final zznw zztp() {
        return this.zzddx;
    }

    public final zzang zztq() {
        return this.zzyf;
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        zzvj();
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza(this, "onhide", hashMap);
    }

    public final void zztz() {
        Map hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zzup.zza(this, "volume", hashMap);
    }

    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzdet.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    public final Context zzua() {
        return zzvv().zzua();
    }

    public final synchronized zzd zzub() {
        return this.zzddg;
    }

    public final synchronized zzd zzuc() {
        return this.zzddz;
    }

    public final synchronized zzasi zzud() {
        return this.zzddh;
    }

    public final synchronized String zzue() {
        return this.zzus;
    }

    public final /* synthetic */ zzasc zzuf() {
        return this.zzdet;
    }

    public final WebViewClient zzug() {
        return this.zzdfb;
    }

    public final synchronized boolean zzuh() {
        return this.zzddi;
    }

    public final zzci zzui() {
        return this.zzbjc;
    }

    public final synchronized boolean zzuj() {
        return this.zzddk;
    }

    public final synchronized boolean zzul() {
        return this.zzddn;
    }

    public final synchronized boolean zzum() {
        return this.zzddo;
    }

    public final synchronized boolean zzun() {
        return this.zzddt > 0;
    }

    public final void zzuo() {
        this.zzaee.zzsc();
    }

    public final void zzup() {
        if (this.zzddw == null) {
            this.zzddw = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_load", this.zzddw);
        }
    }

    public final synchronized zzox zzuq() {
        return this.zzdds;
    }

    public final void zzur() {
        setBackgroundColor(0);
    }

    public final void zzus() {
        zzakb.m3428v("Cannot add text view to inner AdWebView");
    }
}

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
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
final class zzari extends WebView implements OnGlobalLayoutListener, DownloadListener, zzaqw {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    private final DisplayMetrics zzagj;
    @Nullable
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    private final zzhs zzcch;
    @GuardedBy("this")
    private String zzchp = "";
    @GuardedBy("this")
    private Boolean zzcpp;
    private zznv zzdad;
    private final zzash zzdda;
    private final zzbo zzddb;
    private final float zzddc;
    private boolean zzddd = false;
    private boolean zzdde = false;
    private zzaqx zzddf;
    @GuardedBy("this")
    private zzd zzddg;
    @GuardedBy("this")
    private zzasi zzddh;
    @GuardedBy("this")
    private boolean zzddi;
    @GuardedBy("this")
    private boolean zzddj;
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
    @GuardedBy("this")
    private boolean zzdea;
    private Map<String, zzaqh> zzdeb;
    @GuardedBy("this")
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    @VisibleForTesting
    private zzari(zzash com_google_android_gms_internal_ads_zzash, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) {
        super(com_google_android_gms_internal_ads_zzash);
        this.zzdda = com_google_android_gms_internal_ads_zzash;
        this.zzddh = com_google_android_gms_internal_ads_zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = com_google_android_gms_internal_ads_zzci;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzddb = com_google_android_gms_ads_internal_zzbo;
        this.zzwc = com_google_android_gms_ads_internal_zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        zzbv.zzek();
        this.zzagj = zzakk.zza(this.zzaeu);
        this.zzddc = this.zzagj.density;
        this.zzcch = com_google_android_gms_internal_ads_zzhs;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (Throwable e) {
            zzane.zzb("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzek().zza((Context) com_google_android_gms_internal_ads_zzash, com_google_android_gms_internal_ads_zzang.zzcw, settings);
        zzbv.zzem().zza(getContext(), settings);
        setDownloadListener(this);
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
        if (PlatformVersion.isAtLeastHoneycomb()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzaee = new zzamt(this.zzdda.zzto(), this, this, null);
        zzvo();
        this.zzddx = new zznw(new zznx(true, "make_wv", this.zzus));
        this.zzddx.zzji().zzc(com_google_android_gms_internal_ads_zznx);
        this.zzdad = zznq.zzb(this.zzddx.zzji());
        this.zzddx.zza("native:view_create", this.zzdad);
        this.zzddw = null;
        this.zzddv = null;
        zzbv.zzem().zzaw(com_google_android_gms_internal_ads_zzash);
        zzbv.zzeo().zzqe();
    }

    @VisibleForTesting
    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzcpp = bool;
        }
        zzbv.zzeo().zza(bool);
    }

    @TargetApi(19)
    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            evaluateJavascript(str, null);
        }
    }

    private final void zzal(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("isVisible", z ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        zza("onAdVisibilityChanged", hashMap);
    }

    static zzari zzb(Context context, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) {
        return new zzari(new zzash(context), com_google_android_gms_internal_ads_zzasi, str, z, z2, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zznx, com_google_android_gms_ads_internal_zzbo, com_google_android_gms_ads_internal_zzw, com_google_android_gms_internal_ads_zzhs);
    }

    private final synchronized void zzds(String str) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            loadUrl(str);
        }
    }

    private final synchronized void zzdt(String str) {
        Throwable e;
        try {
            super.loadUrl(str);
        } catch (Exception e2) {
            e = e2;
            zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzane.zzc("Could not call loadUrl. ", e);
        } catch (NoClassDefFoundError e3) {
            e = e3;
            zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzane.zzc("Could not call loadUrl. ", e);
        } catch (IncompatibleClassChangeError e4) {
            e = e4;
            zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzane.zzc("Could not call loadUrl. ", e);
        } catch (UnsatisfiedLinkError e5) {
            e = e5;
            zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzane.zzc("Could not call loadUrl. ", e);
        }
    }

    private final void zzdu(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzpz() == null) {
                zzvi();
            }
            if (zzpz().booleanValue()) {
                zza(str, null);
                return;
            }
            String str2 = BridgeUtil.JAVASCRIPT_STR;
            String valueOf = String.valueOf(str);
            zzds(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        str2 = BridgeUtil.JAVASCRIPT_STR;
        valueOf = String.valueOf(str);
        zzds(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    @VisibleForTesting
    private final synchronized Boolean zzpz() {
        return this.zzcpp;
    }

    private final synchronized void zzqf() {
        if (!this.zzdea) {
            this.zzdea = true;
            zzbv.zzeo().zzqf();
        }
    }

    private final boolean zzvh() {
        if (!this.zzddf.zzfz() && !this.zzddf.zzuu()) {
            return false;
        }
        int i;
        int i2;
        zzkb.zzif();
        int zzb = zzamu.zzb(this.zzagj, this.zzagj.widthPixels);
        zzkb.zzif();
        int zzb2 = zzamu.zzb(this.zzagj, this.zzagj.heightPixels);
        Activity zzto = this.zzdda.zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i = zzb2;
            i2 = zzb;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            i2 = zzamu.zzb(this.zzagj, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(this.zzagj, zzf[1]);
        }
        if (this.zzbwy == zzb && this.zzbwz == zzb2 && this.zzbxb == i2 && this.zzbxc == i) {
            return false;
        }
        boolean z = (this.zzbwy == zzb && this.zzbwz == zzb2) ? false : true;
        this.zzbwy = zzb;
        this.zzbwz = zzb2;
        this.zzbxb = i2;
        this.zzbxc = i;
        new zzaal(this).zza(zzb, zzb2, i2, i, this.zzagj.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
    }

    private final synchronized void zzvi() {
        this.zzcpp = zzbv.zzeo().zzpz();
        if (this.zzcpp == null) {
            try {
                evaluateJavascript("(function(){})()", null);
                zza(Boolean.valueOf(true));
            } catch (IllegalStateException e) {
                zza(Boolean.valueOf(false));
            }
        }
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

    public final synchronized void destroy() {
        zzvo();
        this.zzaee.zzsd();
        if (this.zzddg != null) {
            this.zzddg.close();
            this.zzddg.onDestroy();
            this.zzddg = null;
        }
        this.zzddf.reset();
        if (!this.zzddj) {
            zzbv.zzff();
            zzaqg.zzb((zzapw) this);
            zzvn();
            this.zzddj = true;
            zzakb.m3428v("Initiating WebView self destruct sequence in 3...");
            zzakb.m3428v("Loading blank page in WebView, 2...");
            zzdt("about:blank");
        }
    }

    @TargetApi(19)
    public final synchronized void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (isDestroyed()) {
            zzane.zzdm("#004 The webview is destroyed. Ignoring action.");
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
        } else {
            super.evaluateJavascript(str, valueCallback);
        }
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzddj) {
                    this.zzddf.reset();
                    zzbv.zzff();
                    zzaqg.zzb((zzapw) this);
                    zzvn();
                    zzqf();
                }
            }
        } finally {
            super.finalize();
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

    public final synchronized boolean isDestroyed() {
        return this.zzddj;
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadData(str, str2, str3);
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    public final synchronized void loadUrl(String str) {
        Throwable e;
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            try {
                super.loadUrl(str);
            } catch (Exception e2) {
                e = e2;
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrl");
                zzane.zzc("Could not call loadUrl. ", e);
            } catch (NoClassDefFoundError e3) {
                e = e3;
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrl");
                zzane.zzc("Could not call loadUrl. ", e);
            } catch (IncompatibleClassChangeError e4) {
                e = e4;
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrl");
                zzane.zzc("Could not call loadUrl. ", e);
            }
        }
    }

    protected final synchronized void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z2 = this.zzddq;
        if (this.zzddf == null || !this.zzddf.zzuu()) {
            z = z2;
        } else {
            if (!this.zzddr) {
                OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    if (this == null) {
                        throw null;
                    }
                    zzaor.zza((View) this, zzuv);
                }
                OnScrollChangedListener zzuw = this.zzddf.zzuw();
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
            if (this.zzddr && this.zzddf != null && this.zzddf.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                OnScrollChangedListener zzuw = this.zzddf.zzuw();
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
        if (!isDestroyed()) {
            if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                if (this.zzddf != null && this.zzddf.zzve() != null) {
                    this.zzddf.zzve().zzda();
                }
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
                zza("/contentHeight", new zzarj(this));
                zzdu("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                float f = this.zzagj.density;
                r1 = MeasureSpec.getSize(i);
                switch (this.zzddu) {
                    case -1:
                        r0 = MeasureSpec.getSize(i2);
                        break;
                    default:
                        r0 = (int) (f * ((float) this.zzddu));
                        break;
                }
                setMeasuredDimension(r1, r0);
            }
        } else if (this.zzddh.zzvs()) {
            setMeasuredDimension(this.zzagj.widthPixels, this.zzagj.heightPixels);
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
                obj = (((float) this.zzddh.widthPixels) / this.zzddc > ((float) i3) / this.zzddc || ((float) this.zzddh.heightPixels) / this.zzddc > ((float) mode) / this.zzddc) ? null : 1;
                if (obj2 == null) {
                    obj = obj2;
                }
            } else {
                obj = obj2;
            }
            if (obj != null) {
                zzane.zzdk("Not enough space to show ad. Needs " + ((int) (((float) this.zzddh.widthPixels) / this.zzddc)) + "x" + ((int) (((float) this.zzddh.heightPixels) / this.zzddc)) + " dp, but only has " + ((int) (((float) size3) / this.zzddc)) + "x" + ((int) (((float) size4) / this.zzddc)) + " dp.");
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
                if (!this.zzddd) {
                    this.zzcch.zza(zzb.zzalj);
                    this.zzddd = true;
                }
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                if (!this.zzdde) {
                    this.zzcch.zza(zzb.zzalk);
                    this.zzdde = true;
                }
                setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
            }
        }
    }

    public final void onPause() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onPause();
                }
            } catch (Throwable e) {
                zzane.zzb("Could not pause webview.", e);
            }
        }
    }

    public final void onResume() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onResume();
                }
            } catch (Throwable e) {
                zzane.zzb("Could not resume webview.", e);
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzddf.zzuu()) {
            synchronized (this) {
                if (this.zzdds != null) {
                    this.zzdds.zzc(motionEvent);
                }
            }
        } else if (this.zzbjc != null) {
            this.zzbjc.zza(motionEvent);
        }
        return isDestroyed() ? false : super.onTouchEvent(motionEvent);
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

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaqx) {
            this.zzddf = (zzaqx) webViewClient;
        }
    }

    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Throwable e) {
                zzane.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public final void zza(zzc com_google_android_gms_ads_internal_overlay_zzc) {
        this.zzddf.zza(com_google_android_gms_ads_internal_overlay_zzc);
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

    public final void zza(zzfs com_google_android_gms_internal_ads_zzfs) {
        synchronized (this) {
            this.zzddq = com_google_android_gms_internal_ads_zzfs.zztg;
        }
        zzal(com_google_android_gms_internal_ads_zzfs.zztg);
    }

    public final void zza(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        if (this.zzddf != null) {
            this.zzddf.zza(str, (zzv) com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        if (this.zzddf != null) {
            this.zzddf.zza(str, (Predicate) predicate);
        }
    }

    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzbv.zzek().zzn(map));
        } catch (JSONException e) {
            zzane.zzdk("Could not convert parameters to JSON.");
        }
    }

    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(window.AFMA_ReceiveMessage || function() {})('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        String str2 = "Dispatching AFMA event: ";
        jSONObject2 = String.valueOf(stringBuilder.toString());
        zzane.zzck(jSONObject2.length() != 0 ? str2.concat(jSONObject2) : new String(str2));
        zzdu(stringBuilder.toString());
    }

    public final void zza(boolean z, int i) {
        this.zzddf.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzddf.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzddf.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzddf.zzah(z);
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
        zza("onhide", hashMap);
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

    public final synchronized void zzb(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzddz = com_google_android_gms_ads_internal_overlay_zzd;
    }

    public final synchronized void zzb(zzox com_google_android_gms_internal_ads_zzox) {
        this.zzdds = com_google_android_gms_internal_ads_zzox;
    }

    public final void zzb(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        if (this.zzddf != null) {
            this.zzddf.zzb(str, (zzv) com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        zzdu(new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(jSONObject2).length()).append(str).append("(").append(jSONObject2).append(");").toString());
    }

    public final void zzbe(String str) {
        zzdu(str);
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final void zzbm(Context context) {
        this.zzdda.setBaseContext(context);
        this.zzaee.zzi(this.zzdda.zzto());
    }

    public final synchronized void zzc(String str, String str2, @Nullable String str3) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            String zzb;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
                zzb = zzarx.zzb(str2, zzarx.zzvp());
            } else {
                zzb = str2;
            }
            super.loadDataWithBaseURL(str, zzb, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", str3);
        }
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

    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            this.zzddv = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_show", this.zzddv);
        }
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zza("onshow", hashMap);
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
        return this.zzdda.zzto();
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
        zza("onhide", hashMap);
    }

    public final void zztz() {
        Map hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zza("volume", hashMap);
    }

    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzddf.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    public final Context zzua() {
        return this.zzdda.zzua();
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
        return this.zzddf;
    }

    public final WebViewClient zzug() {
        return this.zzddf;
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

    public final synchronized void zzuk() {
        zzakb.m3428v("Destroying WebView!");
        zzqf();
        zzakk.zzcrm.post(new zzark(this));
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

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.webkit.ValueCallback;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public class zzasv extends zzass {
    @GuardedBy("this")
    private boolean zzdfh;
    @GuardedBy("this")
    private boolean zzdfi;

    public zzasv(zzash com_google_android_gms_internal_ads_zzash) {
        super(com_google_android_gms_internal_ads_zzash);
        zzbv.zzeo().zzqe();
    }

    private final synchronized void zzqf() {
        if (!this.zzdfi) {
            this.zzdfi = true;
            zzbv.zzeo().zzqf();
        }
    }

    public synchronized void destroy() {
        if (!this.zzdfh) {
            this.zzdfh = true;
            zzam(false);
            zzakb.m3428v("Initiating WebView self destruct sequence in 3...");
            zzakb.m3428v("Loading blank page in WebView, 2...");
            try {
                super.loadUrl("about:blank");
            } catch (Throwable e) {
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    @TargetApi(19)
    public synchronized void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
        } else {
            super.evaluateJavascript(str, valueCallback);
        }
    }

    protected void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!isDestroyed()) {
                    zzam(true);
                }
                zzqf();
            }
        } finally {
            super.finalize();
        }
    }

    public final synchronized boolean isDestroyed() {
        return this.zzdfh;
    }

    public synchronized void loadData(String str, String str2, String str3) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadData(str, str2, str3);
        }
    }

    public synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    public synchronized void loadUrl(String str) {
        if (isDestroyed()) {
            zzane.zzdk("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadUrl(str);
        }
    }

    @TargetApi(21)
    protected void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            super.onDraw(canvas);
        }
    }

    public void onPause() {
        if (!isDestroyed()) {
            super.onPause();
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            super.onResume();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !isDestroyed() && super.onTouchEvent(motionEvent);
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            super.stopLoading();
        }
    }

    @GuardedBy("this")
    protected void zzam(boolean z) {
    }

    public final synchronized void zzc(zzasu com_google_android_gms_internal_ads_zzasu) {
        if (isDestroyed()) {
            zzakb.m3428v("Blank page loaded, 1...");
            zzuk();
        } else {
            super.zzc(com_google_android_gms_internal_ads_zzasu);
        }
    }

    public final synchronized void zzuk() {
        zzakb.m3428v("Destroying WebView!");
        zzqf();
        zzaoe.zzcvy.execute(new zzasw(this));
    }

    final /* synthetic */ void zzvw() {
        super.destroy();
    }
}

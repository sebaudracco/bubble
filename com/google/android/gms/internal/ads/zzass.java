package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzbv;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public class zzass extends WebView implements zzasx, zzasz, zzata, zzatb {
    private final List<zzasx> zzdew = new CopyOnWriteArrayList();
    private final List<zzatb> zzdex = new CopyOnWriteArrayList();
    private final List<zzasz> zzdey = new CopyOnWriteArrayList();
    private final List<zzata> zzdez = new CopyOnWriteArrayList();
    private final zzash zzdfa;
    protected final WebViewClient zzdfb;

    public zzass(zzash com_google_android_gms_internal_ads_zzash) {
        super(com_google_android_gms_internal_ads_zzash);
        this.zzdfa = com_google_android_gms_internal_ads_zzash;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzem().zza(getContext(), settings);
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        try {
            getSettings().setJavaScriptEnabled(true);
        } catch (Throwable e) {
            zzane.zzb("Unable to enable Javascript.", e);
        }
        setLayerType(1, null);
        this.zzdfb = new zzast(this, this, this, this);
        super.setWebViewClient(this.zzdfb);
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (VERSION.SDK_INT >= 17) {
            super.addJavascriptInterface(obj, str);
        } else {
            zzakb.m3428v("Ignore addJavascriptInterface due to low Android version.");
        }
    }

    public void loadUrl(String str) {
        Throwable e;
        try {
            super.loadUrl(str);
            return;
        } catch (Exception e2) {
            e = e2;
        } catch (NoClassDefFoundError e3) {
            e = e3;
        } catch (IncompatibleClassChangeError e4) {
            e = e4;
        }
        zzbv.zzeo().zza(e, "CoreWebView.loadUrl");
        zzane.zzd("#007 Could not call remote method.", e);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
    }

    public final void zza(zzasx com_google_android_gms_internal_ads_zzasx) {
        this.zzdew.add(com_google_android_gms_internal_ads_zzasx);
    }

    public final void zza(zzasz com_google_android_gms_internal_ads_zzasz) {
        this.zzdey.add(com_google_android_gms_internal_ads_zzasz);
    }

    public final void zza(zzata com_google_android_gms_internal_ads_zzata) {
        this.zzdez.add(com_google_android_gms_internal_ads_zzata);
    }

    public final void zza(zzatb com_google_android_gms_internal_ads_zzatb) {
        this.zzdex.add(com_google_android_gms_internal_ads_zzatb);
    }

    public final boolean zza(zzasu com_google_android_gms_internal_ads_zzasu) {
        for (zzasx zza : this.zzdew) {
            if (zza.zza(com_google_android_gms_internal_ads_zzasu)) {
                return true;
            }
        }
        return false;
    }

    public final void zzb(zzasu com_google_android_gms_internal_ads_zzasu) {
        for (zzasz zzb : this.zzdey) {
            zzb.zzb(com_google_android_gms_internal_ads_zzasu);
        }
    }

    public void zzbe(String str) {
        zzasy.zza(this, str);
    }

    public void zzc(zzasu com_google_android_gms_internal_ads_zzasu) {
        for (zzata zzc : this.zzdez) {
            zzc.zzc(com_google_android_gms_internal_ads_zzasu);
        }
    }

    public final WebResourceResponse zzd(zzasu com_google_android_gms_internal_ads_zzasu) {
        for (zzatb zzd : this.zzdex) {
            WebResourceResponse zzd2 = zzd.zzd(com_google_android_gms_internal_ads_zzasu);
            if (zzd2 != null) {
                return zzd2;
            }
        }
        return null;
    }

    protected final zzash zzvv() {
        return this.zzdfa;
    }
}

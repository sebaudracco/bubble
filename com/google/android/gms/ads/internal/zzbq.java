package com.google.android.gms.ads.internal;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;

final class zzbq extends WebViewClient {
    private final /* synthetic */ zzbp zzaba;

    zzbq(zzbp com_google_android_gms_ads_internal_zzbp) {
        this.zzaba = com_google_android_gms_ads_internal_zzbp;
    }

    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (zzbp.zza(this.zzaba) != null) {
            try {
                zzbp.zza(this.zzaba).onAdFailedToLoad(0);
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.zzaba.zzeb())) {
            return false;
        }
        if (str.startsWith((String) zzkb.zzik().zzd(zznk.zzbcw))) {
            if (zzbp.zza(this.zzaba) != null) {
                try {
                    zzbp.zza(this.zzaba).onAdFailedToLoad(3);
                } catch (Throwable e) {
                    zzakb.zzd("#007 Could not call remote method.", e);
                }
            }
            this.zzaba.zzk(0);
            return true;
        }
        if (str.startsWith((String) zzkb.zzik().zzd(zznk.zzbcx))) {
            if (zzbp.zza(this.zzaba) != null) {
                try {
                    zzbp.zza(this.zzaba).onAdFailedToLoad(0);
                } catch (Throwable e2) {
                    zzakb.zzd("#007 Could not call remote method.", e2);
                }
            }
            this.zzaba.zzk(0);
            return true;
        }
        if (str.startsWith((String) zzkb.zzik().zzd(zznk.zzbcy))) {
            if (zzbp.zza(this.zzaba) != null) {
                try {
                    zzbp.zza(this.zzaba).onAdLoaded();
                } catch (Throwable e22) {
                    zzakb.zzd("#007 Could not call remote method.", e22);
                }
            }
            this.zzaba.zzk(this.zzaba.zzu(str));
            return true;
        } else if (str.startsWith("gmsg://")) {
            return true;
        } else {
            if (zzbp.zza(this.zzaba) != null) {
                try {
                    zzbp.zza(this.zzaba).onAdLeftApplication();
                } catch (Throwable e222) {
                    zzakb.zzd("#007 Could not call remote method.", e222);
                }
            }
            zzbp.zzb(this.zzaba, zzbp.zza(this.zzaba, str));
            return true;
        }
    }
}

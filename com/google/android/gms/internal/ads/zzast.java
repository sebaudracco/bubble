package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzast extends WebViewClient {
    private final zzasx zzdfc;
    private final zzatb zzdfd;
    private final zzasz zzdfe;
    private final zzata zzdff;
    private final zzatc zzdfg = new zzatc();

    zzast(zzasx com_google_android_gms_internal_ads_zzasx, zzatb com_google_android_gms_internal_ads_zzatb, zzasz com_google_android_gms_internal_ads_zzasz, zzata com_google_android_gms_internal_ads_zzata) {
        this.zzdfc = com_google_android_gms_internal_ads_zzasx;
        this.zzdfd = com_google_android_gms_internal_ads_zzatb;
        this.zzdfe = com_google_android_gms_internal_ads_zzasz;
        this.zzdff = com_google_android_gms_internal_ads_zzata;
    }

    private final boolean zzf(zzasu com_google_android_gms_internal_ads_zzasu) {
        return this.zzdfc.zza(com_google_android_gms_internal_ads_zzasu);
    }

    private final WebResourceResponse zzg(zzasu com_google_android_gms_internal_ads_zzasu) {
        return this.zzdfd.zzd(com_google_android_gms_internal_ads_zzasu);
    }

    public final void onLoadResource(WebView webView, String str) {
        if (str != null) {
            String str2 = "Loading resource: ";
            String valueOf = String.valueOf(str);
            zzakb.m3428v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            this.zzdfe.zzb(new zzasu(str));
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        if (str != null) {
            this.zzdff.zzc(new zzasu(str));
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.zzdfg.zze(i, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.zzdfg.zzb(sslError);
    }

    @TargetApi(24)
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : zzg(new zzasu(webResourceRequest));
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return str == null ? null : zzg(new zzasu(str));
    }

    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 222:
                return true;
            default:
                return false;
        }
    }

    @TargetApi(24)
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return (webResourceRequest == null || webResourceRequest.getUrl() == null) ? false : zzf(new zzasu(webResourceRequest));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return str == null ? false : zzf(new zzasu(str));
    }
}

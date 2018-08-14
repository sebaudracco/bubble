package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.PlatformVersion;

@zzadh
@TargetApi(11)
public final class zzaqo extends WebChromeClient {
    private final zzaqw zzbnd;

    public zzaqo(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        this.zzbnd = com_google_android_gms_internal_ads_zzaqw;
    }

    private static Context zza(WebView webView) {
        if (!(webView instanceof zzaqw)) {
            return webView.getContext();
        }
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) webView;
        Context zzto = com_google_android_gms_internal_ads_zzaqw.zzto();
        return zzto == null ? com_google_android_gms_internal_ads_zzaqw.getContext() : zzto;
    }

    private final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        try {
            if (!(this.zzbnd == null || this.zzbnd.zzuf() == null || this.zzbnd.zzuf().zzut() == null)) {
                zzx zzut = this.zzbnd.zzuf().zzut();
                if (!(zzut == null || zzut.zzcy())) {
                    zzut.zzs(new StringBuilder((String.valueOf(str).length() + 11) + String.valueOf(str3).length()).append("window.").append(str).append("('").append(str3).append("')").toString());
                    return false;
                }
            }
            Builder builder = new Builder(context);
            builder.setTitle(str2);
            if (z) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                View textView = new TextView(context);
                textView.setText(str3);
                View editText = new EditText(context);
                editText.setText(str4);
                linearLayout.addView(textView);
                linearLayout.addView(editText);
                builder.setView(linearLayout).setPositiveButton(17039370, new zzaqu(jsPromptResult, editText)).setNegativeButton(17039360, new zzaqt(jsPromptResult)).setOnCancelListener(new zzaqs(jsPromptResult)).create().show();
                return true;
            }
            builder.setMessage(str3).setPositiveButton(17039370, new zzaqr(jsResult)).setNegativeButton(17039360, new zzaqq(jsResult)).setOnCancelListener(new zzaqp(jsResult)).create().show();
            return true;
        } catch (Throwable e) {
            zzane.zzc("Fail to display Dialog.", e);
            return true;
        }
    }

    public final void onCloseWindow(WebView webView) {
        if (webView instanceof zzaqw) {
            zzd zzub = ((zzaqw) webView).zzub();
            if (zzub == null) {
                zzane.zzdk("Tried to close an AdWebView not associated with an overlay.");
                return;
            } else {
                zzub.close();
                return;
            }
        }
        zzane.zzdk("Tried to close a WebView that wasn't an AdWebView.");
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String message = consoleMessage.message();
        String sourceId = consoleMessage.sourceId();
        message = new StringBuilder((String.valueOf(message).length() + 19) + String.valueOf(sourceId).length()).append("JS: ").append(message).append(" (").append(sourceId).append(":").append(consoleMessage.lineNumber()).append(")").toString();
        if (message.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        switch (zzaqv.zzdbn[consoleMessage.messageLevel().ordinal()]) {
            case 1:
                zzane.m3427e(message);
                break;
            case 2:
                zzane.zzdk(message);
                break;
            case 3:
            case 4:
                zzane.zzdj(message);
                break;
            case 5:
                zzane.zzck(message);
                break;
            default:
                zzane.zzdj(message);
                break;
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebViewTransport webViewTransport = (WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zzbnd.zzug() != null) {
            webView2.setWebViewClient(this.zzbnd.zzug());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j != 0) {
            if (j2 == 0) {
                j = Math.min(Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4) + j, 1048576);
            } else if (j2 <= Math.min(1048576 - j, j4)) {
                j += j2;
            }
            j2 = j;
        } else if (j2 > j4 || j2 > 1048576) {
            j2 = 0;
        }
        quotaUpdater.updateQuota(j2);
    }

    public final void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        if (callback != null) {
            boolean z;
            zzbv.zzek();
            if (!zzakk.zzl(this.zzbnd.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                zzbv.zzek();
                if (!zzakk.zzl(this.zzbnd.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = false;
                    callback.invoke(str, z, true);
                }
            }
            z = true;
            callback.invoke(str, z, true);
        }
    }

    public final void onHideCustomView() {
        zzd zzub = this.zzbnd.zzub();
        if (zzub == null) {
            zzane.zzdk("Could not get ad overlay when hiding custom view.");
        } else {
            zzub.zznh();
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "alert", str, str2, null, jsResult, null, false);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "onBeforeUnload", str, str2, null, jsResult, null, false);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "confirm", str, str2, null, jsResult, null, false);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), "prompt", str, str2, str3, null, jsPromptResult, true);
    }

    @TargetApi(21)
    public final void onPermissionRequest(PermissionRequest permissionRequest) {
        if (PlatformVersion.isAtLeastLollipop()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzayb)).booleanValue()) {
                if (this.zzbnd == null || this.zzbnd.zzuf() == null || this.zzbnd.zzuf().zzvf() == null) {
                    super.onPermissionRequest(permissionRequest);
                    return;
                }
                String[] zzb = this.zzbnd.zzuf().zzvf().zzb(permissionRequest.getResources());
                if (zzb.length > 0) {
                    permissionRequest.grant(zzb);
                    return;
                } else {
                    permissionRequest.deny();
                    return;
                }
            }
        }
        super.onPermissionRequest(permissionRequest);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        long j3 = PlaybackStateCompat.ACTION_PREPARE_FROM_URI + j;
        if (5242880 - j2 < j3) {
            quotaUpdater.updateQuota(0);
        } else {
            quotaUpdater.updateQuota(j3);
        }
    }

    @Deprecated
    public final void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        zzd zzub = this.zzbnd.zzub();
        if (zzub == null) {
            zzane.zzdk("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        zzub.zza(view, customViewCallback);
        zzub.setRequestedOrientation(i);
    }

    public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        onShowCustomView(view, -1, customViewCallback);
    }
}

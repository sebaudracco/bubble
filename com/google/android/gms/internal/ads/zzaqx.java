package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzad;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.iid.InstanceID;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public class zzaqx extends WebViewClient implements zzasc {
    private static final String[] zzdbo = new String[]{C1404b.f2123a, "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", InstanceID.ERROR_TIMEOUT, "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdbp = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object mLock;
    @GuardedBy("mLock")
    private boolean zzaek;
    private zzjd zzapt;
    private zzb zzbll;
    private zzd zzblm;
    private zzz zzbmu;
    private zzx zzbmw;
    private zzaab zzbmx;
    private zzaam zzbmy;
    private zzt zzbnb;
    private zzn zzbnc;
    private zzaqw zzbnd;
    private final HashMap<String, List<zzv<? super zzaqw>>> zzdbq;
    private zzasd zzdbr;
    private zzase zzdbs;
    private zzasf zzdbt;
    private boolean zzdbu;
    @GuardedBy("mLock")
    private boolean zzdbv;
    @GuardedBy("mLock")
    private OnGlobalLayoutListener zzdbw;
    @GuardedBy("mLock")
    private OnScrollChangedListener zzdbx;
    @GuardedBy("mLock")
    private boolean zzdby;
    private final zzaak zzdbz;
    private zzasg zzdca;
    private boolean zzdcb;
    private boolean zzdcc;
    private int zzdcd;
    private OnAttachStateChangeListener zzdce;
    @Nullable
    protected zzait zzxd;

    public zzaqx(zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z) {
        this(com_google_android_gms_internal_ads_zzaqw, z, new zzaak(com_google_android_gms_internal_ads_zzaqw, com_google_android_gms_internal_ads_zzaqw.zzua(), new zzmw(com_google_android_gms_internal_ads_zzaqw.getContext())), null);
    }

    @VisibleForTesting
    private zzaqx(zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z, zzaak com_google_android_gms_internal_ads_zzaak, zzaab com_google_android_gms_internal_ads_zzaab) {
        this.zzdbq = new HashMap();
        this.mLock = new Object();
        this.zzdbu = false;
        this.zzbnd = com_google_android_gms_internal_ads_zzaqw;
        this.zzaek = z;
        this.zzdbz = com_google_android_gms_internal_ads_zzaak;
        this.zzbmx = null;
    }

    private final void zza(View view, zzait com_google_android_gms_internal_ads_zzait, int i) {
        if (com_google_android_gms_internal_ads_zzait.zzph() && i > 0) {
            com_google_android_gms_internal_ads_zzait.zzr(view);
            if (com_google_android_gms_internal_ads_zzait.zzph()) {
                zzakk.zzcrm.postDelayed(new zzaqz(this, view, com_google_android_gms_internal_ads_zzait, i), 100);
            }
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zznf = this.zzbmx != null ? this.zzbmx.zznf() : false;
        zzbv.zzei();
        Context context = this.zzbnd.getContext();
        if (!zznf) {
            z = true;
        }
        zzl.zza(context, adOverlayInfoParcel, z);
        if (this.zzxd != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbyl != null) {
                str = adOverlayInfoParcel.zzbyl.url;
            }
            this.zzxd.zzcf(str);
        }
    }

    private final void zzd(Context context, String str, String str2, String str3) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazy)).booleanValue()) {
            String host;
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            String str4 = "host";
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    host = parse.getHost();
                    bundle.putString(str4, host);
                    zzbv.zzek().zza(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
                }
            }
            host = "";
            bundle.putString(str4, host);
            zzbv.zzek().zza(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.webkit.WebResourceResponse zze(java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11) throws java.io.IOException {
        /*
        r9 = this;
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4 = 0;
        r3 = 0;
        r0 = new java.net.URL;
        r0.<init>(r10);
        r6 = r0;
        r0 = r4;
    L_0x000b:
        r5 = r0 + 1;
        r0 = 20;
        if (r5 > r0) goto L_0x00f7;
    L_0x0011:
        r2 = r6.openConnection();
        r2.setConnectTimeout(r8);
        r2.setReadTimeout(r8);
        r0 = r11.entrySet();
        r7 = r0.iterator();
    L_0x0023:
        r0 = r7.hasNext();
        if (r0 == 0) goto L_0x003f;
    L_0x0029:
        r0 = r7.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r2.addRequestProperty(r1, r0);
        goto L_0x0023;
    L_0x003f:
        r0 = r2 instanceof java.net.HttpURLConnection;
        if (r0 != 0) goto L_0x004c;
    L_0x0043:
        r0 = new java.io.IOException;
        r1 = "Invalid protocol.";
        r0.<init>(r1);
        throw r0;
    L_0x004c:
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;
        r1 = com.google.android.gms.ads.internal.zzbv.zzek();
        r2 = r9.zzbnd;
        r2 = r2.getContext();
        r7 = r9.zzbnd;
        r7 = r7.zztq();
        r7 = r7.zzcw;
        r1.zza(r2, r7, r4, r0);
        r1 = new com.google.android.gms.internal.ads.zzamy;
        r1.<init>();
        r1.zza(r0, r3);
        r2 = r0.getResponseCode();
        r1.zza(r0, r2);
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r2 < r1) goto L_0x00ef;
    L_0x0077:
        r1 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r2 >= r1) goto L_0x00ef;
    L_0x007b:
        r1 = "Location";
        r1 = r0.getHeaderField(r1);
        if (r1 != 0) goto L_0x008d;
    L_0x0084:
        r0 = new java.io.IOException;
        r1 = "Missing Location header in redirect";
        r0.<init>(r1);
        throw r0;
    L_0x008d:
        r2 = new java.net.URL;
        r2.<init>(r6, r1);
        r6 = r2.getProtocol();
        if (r6 != 0) goto L_0x00a0;
    L_0x0098:
        r0 = "Protocol is null";
        com.google.android.gms.internal.ads.zzane.zzdk(r0);
        r0 = r3;
    L_0x009f:
        return r0;
    L_0x00a0:
        r7 = "http";
        r7 = r6.equals(r7);
        if (r7 != 0) goto L_0x00ce;
    L_0x00a9:
        r7 = "https";
        r7 = r6.equals(r7);
        if (r7 != 0) goto L_0x00ce;
    L_0x00b2:
        r1 = "Unsupported scheme: ";
        r0 = java.lang.String.valueOf(r6);
        r2 = r0.length();
        if (r2 == 0) goto L_0x00c8;
    L_0x00bf:
        r0 = r1.concat(r0);
    L_0x00c3:
        com.google.android.gms.internal.ads.zzane.zzdk(r0);
        r0 = r3;
        goto L_0x009f;
    L_0x00c8:
        r0 = new java.lang.String;
        r0.<init>(r1);
        goto L_0x00c3;
    L_0x00ce:
        r6 = "Redirecting to ";
        r1 = java.lang.String.valueOf(r1);
        r7 = r1.length();
        if (r7 == 0) goto L_0x00e9;
    L_0x00db:
        r1 = r6.concat(r1);
    L_0x00df:
        com.google.android.gms.internal.ads.zzane.zzck(r1);
        r0.disconnect();
        r0 = r5;
        r6 = r2;
        goto L_0x000b;
    L_0x00e9:
        r1 = new java.lang.String;
        r1.<init>(r6);
        goto L_0x00df;
    L_0x00ef:
        com.google.android.gms.ads.internal.zzbv.zzek();
        r0 = com.google.android.gms.internal.ads.zzakk.zzb(r0);
        goto L_0x009f;
    L_0x00f7:
        r0 = new java.io.IOException;
        r1 = 32;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r1);
        r1 = "Too many redirects (20)";
        r1 = r2.append(r1);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqx.zze(java.lang.String, java.util.Map):android.webkit.WebResourceResponse");
    }

    private final void zzi(Uri uri) {
        String path = uri.getPath();
        List<zzv> list = (List) this.zzdbq.get(path);
        if (list != null) {
            zzbv.zzek();
            Map zzg = zzakk.zzg(uri);
            if (zzane.isLoggable(2)) {
                String str = "Received GMSG: ";
                path = String.valueOf(path);
                zzakb.m3428v(path.length() != 0 ? str.concat(path) : new String(str));
                for (String path2 : zzg.keySet()) {
                    str = (String) zzg.get(path2);
                    zzakb.m3428v(new StringBuilder((String.valueOf(path2).length() + 4) + String.valueOf(str).length()).append("  ").append(path2).append(": ").append(str).toString());
                }
            }
            for (zzv zza : list) {
                zza.zza(this.zzbnd, zzg);
            }
            return;
        }
        String valueOf = String.valueOf(uri);
        zzakb.m3428v(new StringBuilder(String.valueOf(valueOf).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf).toString());
    }

    private final void zzuy() {
        if (this.zzdce != null) {
            this.zzbnd.getView().removeOnAttachStateChangeListener(this.zzdce);
        }
    }

    private final void zzvd() {
        if (this.zzdbr != null && ((this.zzdcb && this.zzdcd <= 0) || this.zzdcc)) {
            this.zzdbr.zze(!this.zzdcc);
            this.zzdbr = null;
        }
        this.zzbnd.zzup();
    }

    public final void onLoadResource(WebView webView, String str) {
        String str2 = "Loading resource: ";
        String valueOf = String.valueOf(str);
        zzakb.m3428v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPageFinished(android.webkit.WebView r3, java.lang.String r4) {
        /*
        r2 = this;
        r1 = r2.mLock;
        monitor-enter(r1);
        r0 = r2.zzbnd;	 Catch:{ all -> 0x002c }
        r0 = r0.isDestroyed();	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x0018;
    L_0x000b:
        r0 = "Blank page loaded, 1...";
        com.google.android.gms.internal.ads.zzakb.m3428v(r0);	 Catch:{ all -> 0x002c }
        r0 = r2.zzbnd;	 Catch:{ all -> 0x002c }
        r0.zzuk();	 Catch:{ all -> 0x002c }
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
    L_0x0017:
        return;
    L_0x0018:
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        r0 = 1;
        r2.zzdcb = r0;
        r0 = r2.zzdbs;
        if (r0 == 0) goto L_0x0028;
    L_0x0020:
        r0 = r2.zzdbs;
        r0.zzly();
        r0 = 0;
        r2.zzdbs = r0;
    L_0x0028:
        r2.zzvd();
        goto L_0x0017;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqx.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String valueOf = (i >= 0 || (-i) - 1 >= zzdbo.length) ? String.valueOf(i) : zzdbo[(-i) - 1];
        zzd(this.zzbnd.getContext(), "http_err", valueOf, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            String valueOf = (primaryError < 0 || primaryError >= zzdbp.length) ? String.valueOf(primaryError) : zzdbp[primaryError];
            zzd(this.zzbnd.getContext(), "ssl_err", valueOf, zzbv.zzem().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzxd != null) {
            this.zzxd.zzpj();
            this.zzxd = null;
        }
        zzuy();
        synchronized (this.mLock) {
            this.zzdbq.clear();
            this.zzapt = null;
            this.zzbnc = null;
            this.zzdbr = null;
            this.zzdbs = null;
            this.zzbll = null;
            this.zzblm = null;
            this.zzdbu = false;
            this.zzaek = false;
            this.zzdbv = false;
            this.zzdby = false;
            this.zzbnb = null;
            this.zzdbt = null;
            if (this.zzbmx != null) {
                this.zzbmx.zzm(true);
                this.zzbmx = null;
            }
        }
    }

    @Nullable
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzd(str, Collections.emptyMap());
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
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

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        zzakb.m3428v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        } else {
            if (this.zzdbu && webView == this.zzbnd.getWebView()) {
                str2 = parse.getScheme();
                Object obj = ("http".equalsIgnoreCase(str2) || "https".equalsIgnoreCase(str2)) ? 1 : null;
                if (obj != null) {
                    if (this.zzapt != null) {
                        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxf)).booleanValue()) {
                            this.zzapt.onAdClicked();
                            if (this.zzxd != null) {
                                this.zzxd.zzcf(str);
                            }
                            this.zzapt = null;
                        }
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (this.zzbnd.getWebView().willNotDraw()) {
                str2 = "AdWebView unable to handle URL: ";
                valueOf = String.valueOf(str);
                zzane.zzdk(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            } else {
                Uri uri;
                try {
                    zzci zzui = this.zzbnd.zzui();
                    if (zzui != null && zzui.zzb(parse)) {
                        parse = zzui.zza(parse, this.zzbnd.getContext(), this.zzbnd.getView(), this.zzbnd.zzto());
                    }
                    uri = parse;
                } catch (zzcj e) {
                    String str3 = "Unable to append parameter to URL: ";
                    str2 = String.valueOf(str);
                    zzane.zzdk(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                    uri = parse;
                }
                if (this.zzbmw == null || this.zzbmw.zzcy()) {
                    zza(new zzc("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
                } else {
                    this.zzbmw.zzs(str);
                }
            }
        }
        return true;
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzdbz.zzc(i, i2);
        if (this.zzbmx != null) {
            this.zzbmx.zza(i, i2, z);
        }
    }

    public final void zza(OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.mLock) {
            this.zzdbv = true;
            this.zzbnd.zzuo();
            this.zzdbw = onGlobalLayoutListener;
            this.zzdbx = onScrollChangedListener;
        }
    }

    public final void zza(zzc com_google_android_gms_ads_internal_overlay_zzc) {
        zzn com_google_android_gms_ads_internal_overlay_zzn = null;
        boolean zzuj = this.zzbnd.zzuj();
        zzjd com_google_android_gms_internal_ads_zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        if (!zzuj) {
            com_google_android_gms_ads_internal_overlay_zzn = this.zzbnc;
        }
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_overlay_zzc, com_google_android_gms_internal_ads_zzjd, com_google_android_gms_ads_internal_overlay_zzn, this.zzbnb, this.zzbnd.zztq()));
    }

    public final void zza(zzasd com_google_android_gms_internal_ads_zzasd) {
        this.zzdbr = com_google_android_gms_internal_ads_zzasd;
    }

    public final void zza(zzase com_google_android_gms_internal_ads_zzase) {
        this.zzdbs = com_google_android_gms_internal_ads_zzase;
    }

    public final void zza(zzasf com_google_android_gms_internal_ads_zzasf) {
        this.zzdbt = com_google_android_gms_internal_ads_zzasf;
    }

    public final void zza(zzasg com_google_android_gms_internal_ads_zzasg) {
        this.zzdca = com_google_android_gms_internal_ads_zzasg;
    }

    public final void zza(zzjd com_google_android_gms_internal_ads_zzjd, zzb com_google_android_gms_ads_internal_gmsg_zzb, zzn com_google_android_gms_ads_internal_overlay_zzn, zzd com_google_android_gms_ads_internal_gmsg_zzd, zzt com_google_android_gms_ads_internal_overlay_zzt, boolean z, @Nullable zzz com_google_android_gms_ads_internal_gmsg_zzz, zzx com_google_android_gms_ads_internal_zzx, zzaam com_google_android_gms_internal_ads_zzaam, @Nullable zzait com_google_android_gms_internal_ads_zzait) {
        zzx com_google_android_gms_ads_internal_zzx2 = com_google_android_gms_ads_internal_zzx == null ? new zzx(this.zzbnd.getContext(), com_google_android_gms_internal_ads_zzait, null) : com_google_android_gms_ads_internal_zzx;
        this.zzbmx = new zzaab(this.zzbnd, com_google_android_gms_internal_ads_zzaam);
        this.zzxd = com_google_android_gms_internal_ads_zzait;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            zza("/adMetadata", new zza(com_google_android_gms_ads_internal_gmsg_zzb));
        }
        zza("/appEvent", new com.google.android.gms.ads.internal.gmsg.zzc(com_google_android_gms_ads_internal_gmsg_zzd));
        zza("/backButton", zzf.zzblx);
        zza("/refresh", zzf.zzbly);
        zza("/canOpenURLs", zzf.zzblo);
        zza("/canOpenIntents", zzf.zzblp);
        zza("/click", zzf.zzblq);
        zza("/close", zzf.zzblr);
        zza("/customClose", zzf.zzbls);
        zza("/instrument", zzf.zzbmb);
        zza("/delayPageLoaded", zzf.zzbmd);
        zza("/delayPageClosed", zzf.zzbme);
        zza("/getLocationInfo", zzf.zzbmf);
        zza("/httpTrack", zzf.zzblt);
        zza("/log", zzf.zzblu);
        zza("/mraid", new zzac(com_google_android_gms_ads_internal_zzx2, this.zzbmx, com_google_android_gms_internal_ads_zzaam));
        zza("/mraidLoaded", this.zzdbz);
        zza("/open", new zzad(this.zzbnd.getContext(), this.zzbnd.zztq(), this.zzbnd.zzui(), com_google_android_gms_ads_internal_overlay_zzt, com_google_android_gms_internal_ads_zzjd, com_google_android_gms_ads_internal_gmsg_zzb, com_google_android_gms_ads_internal_gmsg_zzd, com_google_android_gms_ads_internal_overlay_zzn, com_google_android_gms_ads_internal_zzx2, this.zzbmx));
        zza("/precache", new zzaql());
        zza("/touch", zzf.zzblw);
        zza("/video", zzf.zzblz);
        zza("/videoMeta", zzf.zzbma);
        if (zzbv.zzfh().zzs(this.zzbnd.getContext())) {
            zza("/logScionEvent", new zzab(this.zzbnd.getContext()));
        }
        if (com_google_android_gms_ads_internal_gmsg_zzz != null) {
            zza("/setInterstitialProperties", new zzy(com_google_android_gms_ads_internal_gmsg_zzz));
        }
        this.zzapt = com_google_android_gms_internal_ads_zzjd;
        this.zzbnc = com_google_android_gms_ads_internal_overlay_zzn;
        this.zzbll = com_google_android_gms_ads_internal_gmsg_zzb;
        this.zzblm = com_google_android_gms_ads_internal_gmsg_zzd;
        this.zzbnb = com_google_android_gms_ads_internal_overlay_zzt;
        this.zzbmw = com_google_android_gms_ads_internal_zzx2;
        this.zzbmy = com_google_android_gms_internal_ads_zzaam;
        this.zzbmu = com_google_android_gms_ads_internal_gmsg_zzz;
        this.zzdbu = z;
    }

    public final void zza(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        synchronized (this.mLock) {
            List list = (List) this.zzdbq.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzdbq.put(str, list);
            }
            list.add(com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        synchronized (this.mLock) {
            List<zzv> list = (List) this.zzdbq.get(str);
            if (list == null) {
                return;
            }
            Collection arrayList = new ArrayList();
            for (zzv com_google_android_gms_ads_internal_gmsg_zzv : list) {
                if (predicate.apply(com_google_android_gms_ads_internal_gmsg_zzv)) {
                    arrayList.add(com_google_android_gms_ads_internal_gmsg_zzv);
                }
            }
            list.removeAll(arrayList);
        }
    }

    public final void zza(boolean z, int i) {
        zzjd com_google_android_gms_internal_ads_zzjd = (!this.zzbnd.zzuj() || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        zza(new AdOverlayInfoParcel(com_google_android_gms_internal_ads_zzjd, this.zzbnc, this.zzbnb, this.zzbnd, z, i, this.zzbnd.zztq()));
    }

    public final void zza(boolean z, int i, String str) {
        zzn com_google_android_gms_ads_internal_overlay_zzn = null;
        boolean zzuj = this.zzbnd.zzuj();
        zzjd com_google_android_gms_internal_ads_zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        if (!zzuj) {
            com_google_android_gms_ads_internal_overlay_zzn = new zzarb(this.zzbnd, this.zzbnc);
        }
        zza(new AdOverlayInfoParcel(com_google_android_gms_internal_ads_zzjd, com_google_android_gms_ads_internal_overlay_zzn, this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, this.zzbnd.zztq()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzuj = this.zzbnd.zzuj();
        zzjd com_google_android_gms_internal_ads_zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        zza(new AdOverlayInfoParcel(com_google_android_gms_internal_ads_zzjd, zzuj ? null : new zzarb(this.zzbnd, this.zzbnc), this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, str2, this.zzbnd.zztq()));
    }

    public final void zzah(boolean z) {
        this.zzdbu = z;
    }

    public final void zzb(int i, int i2) {
        if (this.zzbmx != null) {
            this.zzbmx.zzb(i, i2);
        }
    }

    public final void zzb(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        synchronized (this.mLock) {
            List list = (List) this.zzdbq.get(str);
            if (list == null) {
                return;
            }
            list.remove(com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);
        }
    }

    @Nullable
    protected final WebResourceResponse zzd(String str, Map<String, String> map) {
        Throwable e;
        try {
            String zzb = zzajb.zzb(str, this.zzbnd.getContext());
            if (!zzb.equals(str)) {
                return zze(zzb, map);
            }
            zzhl zzaa = zzhl.zzaa(str);
            if (zzaa != null) {
                zzhi zza = zzbv.zzeq().zza(zzaa);
                if (zza != null && zza.zzhi()) {
                    return new WebResourceResponse("", "", zza.zzhj());
                }
            }
            if (zzamy.isEnabled()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzazn)).booleanValue()) {
                    return zze(str, map);
                }
            }
            return null;
        } catch (Exception e2) {
            e = e2;
            zzbv.zzeo().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        } catch (NoClassDefFoundError e3) {
            e = e3;
            zzbv.zzeo().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    public final boolean zzfz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzaek;
        }
        return z;
    }

    public final void zznk() {
        synchronized (this.mLock) {
            this.zzdbu = false;
            this.zzaek = true;
            zzaoe.zzcvy.execute(new zzaqy(this));
        }
    }

    public final zzx zzut() {
        return this.zzbmw;
    }

    public final boolean zzuu() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdbv;
        }
        return z;
    }

    public final OnGlobalLayoutListener zzuv() {
        OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.mLock) {
            onGlobalLayoutListener = this.zzdbw;
        }
        return onGlobalLayoutListener;
    }

    public final OnScrollChangedListener zzuw() {
        OnScrollChangedListener onScrollChangedListener;
        synchronized (this.mLock) {
            onScrollChangedListener = this.zzdbx;
        }
        return onScrollChangedListener;
    }

    public final boolean zzux() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdby;
        }
        return z;
    }

    public final void zzuz() {
        zzait com_google_android_gms_internal_ads_zzait = this.zzxd;
        if (com_google_android_gms_internal_ads_zzait != null) {
            View webView = this.zzbnd.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza(webView, com_google_android_gms_internal_ads_zzait, 10);
                return;
            }
            zzuy();
            this.zzdce = new zzara(this, com_google_android_gms_internal_ads_zzait);
            this.zzbnd.getView().addOnAttachStateChangeListener(this.zzdce);
        }
    }

    public final void zzva() {
        synchronized (this.mLock) {
            this.zzdby = true;
        }
        this.zzdcd++;
        zzvd();
    }

    public final void zzvb() {
        this.zzdcd--;
        zzvd();
    }

    public final void zzvc() {
        this.zzdcc = true;
        zzvd();
    }

    public final zzasg zzve() {
        return this.zzdca;
    }

    public final zzait zzvf() {
        return this.zzxd;
    }

    final /* synthetic */ void zzvg() {
        this.zzbnd.zzuo();
        com.google.android.gms.ads.internal.overlay.zzd zzub = this.zzbnd.zzub();
        if (zzub != null) {
            zzub.zznk();
        }
        if (this.zzdbt != null) {
            this.zzdbt.zzdb();
            this.zzdbt = null;
        }
    }
}

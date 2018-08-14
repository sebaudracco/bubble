package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public interface zzaqw extends zzbo, zzapw, zzarr, zzars, zzarw, zzarz, zzasa, zzasb, zzft, zzue, zzve {
    void destroy();

    Context getContext();

    int getHeight();

    LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    @Nullable
    OnClickListener getOnClickListener();

    ViewParent getParent();

    int getRequestedOrientation();

    View getView();

    WebView getWebView();

    int getWidth();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, @Nullable String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    void setBackgroundColor(int i);

    void setOnClickListener(OnClickListener onClickListener);

    void setOnTouchListener(OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    void zza(zzd com_google_android_gms_ads_internal_overlay_zzd);

    void zza(zzarl com_google_android_gms_internal_ads_zzarl);

    void zza(zzasi com_google_android_gms_internal_ads_zzasi);

    void zza(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);

    void zza(String str, Predicate<zzv<? super zzaqw>> predicate);

    void zzai(int i);

    void zzai(boolean z);

    void zzaj(boolean z);

    void zzak(boolean z);

    void zzb(zzd com_google_android_gms_ads_internal_overlay_zzd);

    void zzb(zzox com_google_android_gms_internal_ads_zzox);

    void zzb(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw);

    zzw zzbi();

    void zzbm(Context context);

    void zzc(String str, String str2, @Nullable String str3);

    void zzdr(String str);

    void zzno();

    @Nullable
    zzarl zztm();

    Activity zzto();

    zznw zztp();

    zzang zztq();

    void zzty();

    void zztz();

    void zzu(boolean z);

    Context zzua();

    zzd zzub();

    zzd zzuc();

    zzasi zzud();

    String zzue();

    @Nullable
    zzasc zzuf();

    WebViewClient zzug();

    boolean zzuh();

    zzci zzui();

    boolean zzuj();

    void zzuk();

    boolean zzul();

    boolean zzum();

    boolean zzun();

    void zzuo();

    void zzup();

    zzox zzuq();

    void zzur();

    void zzus();
}

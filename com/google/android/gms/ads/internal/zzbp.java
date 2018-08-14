package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkt;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzod;
import java.util.Map;
import java.util.concurrent.Future;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbp extends zzkt {
    private final Context mContext;
    private final zzjn zzaau;
    private final Future<zzci> zzaav = zzaki.zza(new zzbs(this));
    private final zzbu zzaaw;
    @Nullable
    private WebView zzaax = new WebView(this.mContext);
    @Nullable
    private zzci zzaay;
    private AsyncTask<Void, Void, String> zzaaz;
    @Nullable
    private zzkh zzxs;
    private final zzang zzyf;

    public zzbp(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzang com_google_android_gms_internal_ads_zzang) {
        this.mContext = context;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzaau = com_google_android_gms_internal_ads_zzjn;
        this.zzaaw = new zzbu(str);
        zzk(0);
        this.zzaax.setVerticalScrollBarEnabled(false);
        this.zzaax.getSettings().setJavaScriptEnabled(true);
        this.zzaax.setWebViewClient(new zzbq(this));
        this.zzaax.setOnTouchListener(new zzbr(this));
    }

    private final String zzv(String str) {
        if (this.zzaay == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzaay.zza(parse, this.mContext, null, null);
        } catch (Throwable e) {
            zzane.zzc("Unable to process ad data", e);
        }
        return parse.toString();
    }

    private final void zzw(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzaaz.cancel(true);
        this.zzaav.cancel(true);
        this.zzaax.destroy();
        this.zzaax = null;
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Nullable
    public final zzlo getVideoController() {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final boolean isReady() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void stopLoading() throws RemoteException {
    }

    public final void zza(zzaaw com_google_android_gms_internal_ads_zzaaw) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzabc com_google_android_gms_internal_ads_zzabc, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzahe com_google_android_gms_internal_ads_zzahe) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzjn com_google_android_gms_internal_ads_zzjn) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public final void zza(zzke com_google_android_gms_internal_ads_zzke) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzkh com_google_android_gms_internal_ads_zzkh) throws RemoteException {
        this.zzxs = com_google_android_gms_internal_ads_zzkh;
    }

    public final void zza(zzkx com_google_android_gms_internal_ads_zzkx) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzla com_google_android_gms_internal_ads_zzla) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzlg com_google_android_gms_internal_ads_zzlg) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzlu com_google_android_gms_internal_ads_zzlu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzmu com_google_android_gms_internal_ads_zzmu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzod com_google_android_gms_internal_ads_zzod) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final boolean zzb(zzjj com_google_android_gms_internal_ads_zzjj) throws RemoteException {
        Preconditions.checkNotNull(this.zzaax, "This Search Ad has already been torn down");
        this.zzaaw.zza(com_google_android_gms_internal_ads_zzjj, this.zzyf);
        this.zzaaz = new zzbt(this, null).execute(new Void[0]);
        return true;
    }

    public final Bundle zzba() {
        throw new IllegalStateException("Unused method");
    }

    public final IObjectWrapper zzbj() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzaax);
    }

    public final zzjn zzbk() throws RemoteException {
        return this.zzaau;
    }

    public final void zzbm() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    @Nullable
    public final String zzck() throws RemoteException {
        return null;
    }

    @VisibleForTesting
    final String zzea() {
        Builder builder = new Builder();
        builder.scheme("https://").appendEncodedPath((String) zzkb.zzik().zzd(zznk.zzbcz));
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzaaw.getQuery());
        builder.appendQueryParameter("pubId", this.zzaaw.zzed());
        Map zzee = this.zzaaw.zzee();
        for (String str : zzee.keySet()) {
            builder.appendQueryParameter(str, (String) zzee.get(str));
        }
        Uri build = builder.build();
        if (this.zzaay != null) {
            try {
                build = this.zzaay.zza(build, this.mContext);
            } catch (Throwable e) {
                zzane.zzc("Unable to process ad data", e);
            }
        }
        String zzeb = zzeb();
        String str2 = build.getEncodedQuery();
        return new StringBuilder((String.valueOf(zzeb).length() + 1) + String.valueOf(str2).length()).append(zzeb).append("#").append(str2).toString();
    }

    @VisibleForTesting
    final String zzeb() {
        String str;
        CharSequence zzec = this.zzaaw.zzec();
        if (TextUtils.isEmpty(zzec)) {
            str = "www.google.com";
        } else {
            CharSequence charSequence = zzec;
        }
        String str2 = (String) zzkb.zzik().zzd(zznk.zzbcz);
        return new StringBuilder((String.valueOf(str).length() + 8) + String.valueOf(str2).length()).append("https://").append(str).append(str2).toString();
    }

    @VisibleForTesting
    final void zzk(int i) {
        if (this.zzaax != null) {
            this.zzaax.setLayoutParams(new LayoutParams(-1, i));
        }
    }

    @VisibleForTesting
    final int zzu(String str) {
        int i = 0;
        Object queryParameter = Uri.parse(str).getQueryParameter("height");
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                zzkb.zzif();
                i = zzamu.zza(this.mContext, Integer.parseInt(queryParameter));
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }
}

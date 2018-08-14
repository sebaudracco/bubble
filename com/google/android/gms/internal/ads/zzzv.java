package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.PlatformVersion;

@zzadh
public final class zzzv implements MediationInterstitialAdapter {
    private Uri mUri;
    private Activity zzbvp;
    private MediationInterstitialListener zzbvq;

    public final void onDestroy() {
        zzane.zzck("Destroying AdMobCustomTabsAdapter adapter.");
    }

    public final void onPause() {
        zzane.zzck("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public final void onResume() {
        zzane.zzck("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzbvq = mediationInterstitialListener;
        if (this.zzbvq == null) {
            zzane.zzdk("Listener not set for mediation. Returning.");
        } else if (context instanceof Activity) {
            int i = (PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzoh.zzh(context)) ? 1 : 0;
            if (i == 0) {
                zzane.zzdk("Default browser does not support custom tabs. Bailing out.");
                this.zzbvq.onAdFailedToLoad(this, 0);
                return;
            }
            Object string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzane.zzdk("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzbvq.onAdFailedToLoad(this, 0);
                return;
            }
            this.zzbvp = (Activity) context;
            this.mUri = Uri.parse(string);
            this.zzbvq.onAdLoaded(this);
        } else {
            zzane.zzdk("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzbvq.onAdFailedToLoad(this, 0);
        }
    }

    public final void showInterstitial() {
        CustomTabsIntent build = new Builder().build();
        build.intent.setData(this.mUri);
        zzakk.zzcrm.post(new zzzx(this, new AdOverlayInfoParcel(new zzc(build.intent), null, new zzzw(this), null, new zzang(0, 0, false))));
        zzbv.zzeo().zzqc();
    }
}

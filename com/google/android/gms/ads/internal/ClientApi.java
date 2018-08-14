package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzx;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaap;
import com.google.android.gms.internal.ads.zzaaz;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzagq;
import com.google.android.gms.internal.ads.zzagz;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkn;
import com.google.android.gms.internal.ads.zzks;
import com.google.android.gms.internal.ads.zzle;
import com.google.android.gms.internal.ads.zzlj;
import com.google.android.gms.internal.ads.zzpn;
import com.google.android.gms.internal.ads.zzpp;
import com.google.android.gms.internal.ads.zzqa;
import com.google.android.gms.internal.ads.zzqf;
import com.google.android.gms.internal.ads.zzxn;
import java.util.HashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@Keep
@KeepForSdkWithMembers
@DynamiteApi
@RetainForClient
@zzadh
@ParametersAreNonnullByDefault
public class ClientApi extends zzle {
    public zzkn createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzxn com_google_android_gms_internal_ads_zzxn, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzek();
        return new zzak(context, str, com_google_android_gms_internal_ads_zzxn, new zzang(12451000, i, true, zzakk.zzav(context)), zzw.zzc(context));
    }

    public zzaap createAdOverlay(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(activity.getIntent());
        if (zzc == null) {
            return new zzr(activity);
        }
        switch (zzc.zzbyu) {
            case 1:
                return new zzq(activity);
            case 2:
                return new zzx(activity);
            case 3:
                return new zzy(activity);
            case 4:
                return new zzs(activity, zzc);
            default:
                return new zzr(activity);
        }
    }

    public zzks createBannerAdManager(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, int i) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzek();
        return new zzy(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, new zzang(12451000, i, true, zzakk.zzav(context)), zzw.zzc(context));
    }

    public zzaaz createInAppPurchaseManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zzks createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper r14, com.google.android.gms.internal.ads.zzjn r15, java.lang.String r16, com.google.android.gms.internal.ads.zzxn r17, int r18) throws android.os.RemoteException {
        /*
        r13 = this;
        r2 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r14);
        r2 = (android.content.Context) r2;
        com.google.android.gms.internal.ads.zznk.initialize(r2);
        r5 = new com.google.android.gms.internal.ads.zzang;
        r1 = 12451000; // 0xbdfcb8 float:1.7447567E-38 double:6.1516114E-317;
        r3 = 1;
        com.google.android.gms.ads.internal.zzbv.zzek();
        r4 = com.google.android.gms.internal.ads.zzakk.zzav(r2);
        r0 = r18;
        r5.<init>(r1, r0, r3, r4);
        r1 = "reward_mb";
        r3 = r15.zzarb;
        r3 = r1.equals(r3);
        if (r3 != 0) goto L_0x0038;
    L_0x0026:
        r1 = com.google.android.gms.internal.ads.zznk.zzayy;
        r4 = com.google.android.gms.internal.ads.zzkb.zzik();
        r1 = r4.zzd(r1);
        r1 = (java.lang.Boolean) r1;
        r1 = r1.booleanValue();
        if (r1 != 0) goto L_0x004c;
    L_0x0038:
        if (r3 == 0) goto L_0x005d;
    L_0x003a:
        r1 = com.google.android.gms.internal.ads.zznk.zzayz;
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();
        r1 = r3.zzd(r1);
        r1 = (java.lang.Boolean) r1;
        r1 = r1.booleanValue();
        if (r1 == 0) goto L_0x005d;
    L_0x004c:
        r1 = 1;
    L_0x004d:
        if (r1 == 0) goto L_0x005f;
    L_0x004f:
        r1 = new com.google.android.gms.internal.ads.zzub;
        r6 = com.google.android.gms.ads.internal.zzw.zzc(r2);
        r3 = r16;
        r4 = r17;
        r1.<init>(r2, r3, r4, r5, r6);
    L_0x005c:
        return r1;
    L_0x005d:
        r1 = 0;
        goto L_0x004d;
    L_0x005f:
        r6 = new com.google.android.gms.ads.internal.zzal;
        r12 = com.google.android.gms.ads.internal.zzw.zzc(r2);
        r7 = r2;
        r8 = r15;
        r9 = r16;
        r10 = r17;
        r11 = r5;
        r6.<init>(r7, r8, r9, r10, r11, r12);
        r1 = r6;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.ClientApi.createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzjn, java.lang.String, com.google.android.gms.internal.ads.zzxn, int):com.google.android.gms.internal.ads.zzks");
    }

    public zzqa createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzpn((FrameLayout) ObjectWrapper.unwrap(iObjectWrapper), (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper2));
    }

    public zzqf createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzpp((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public zzagz createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzxn com_google_android_gms_internal_ads_zzxn, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzek();
        return new zzagq(context, zzw.zzc(context), com_google_android_gms_internal_ads_zzxn, new zzang(12451000, i, true, zzakk.zzav(context)));
    }

    public zzks createSearchAdManager(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, String str, int i) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzek();
        return new zzbp(context, com_google_android_gms_internal_ads_zzjn, str, new zzang(12451000, i, true, zzakk.zzav(context)));
    }

    @Nullable
    public zzlj getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public zzlj getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzek();
        return zzay.zza(context, new zzang(12451000, i, true, zzakk.zzav(context)));
    }
}

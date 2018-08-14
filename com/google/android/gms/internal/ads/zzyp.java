package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzyp<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzxr {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbvb;
    private final NETWORK_EXTRAS zzbvc;

    public zzyp(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzbvb = mediationAdapter;
        this.zzbvc = network_extras;
    }

    private final SERVER_PARAMETERS zza(String str, int i, String str2) throws RemoteException {
        Map hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    hashMap.put(str3, jSONObject.getString(str3));
                }
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class serverParametersType = this.zzbvb.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        MediationServerParameters mediationServerParameters = (MediationServerParameters) serverParametersType.newInstance();
        mediationServerParameters.load(hashMap);
        return mediationServerParameters;
    }

    private static boolean zzm(zzjj com_google_android_gms_internal_ads_zzjj) {
        if (!com_google_android_gms_internal_ads_zzjj.zzapz) {
            zzkb.zzif();
            if (!zzamu.zzsg()) {
                return false;
            }
        }
        return true;
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzbvb.destroy();
        } catch (Throwable th) {
            zzane.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public final zzlo getVideoController() {
        return null;
    }

    public final IObjectWrapper getView() throws RemoteException {
        if (this.zzbvb instanceof MediationBannerAdapter) {
            try {
                return ObjectWrapper.wrap(((MediationBannerAdapter) this.zzbvb).getBannerView());
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() {
        return true;
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void showInterstitial() throws RemoteException {
        if (this.zzbvb instanceof MediationInterstitialAdapter) {
            zzane.zzck("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbvb).showInterstitial();
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void showVideo() {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaic com_google_android_gms_internal_ads_zzaic, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzaic com_google_android_gms_internal_ads_zzaic, String str2) throws RemoteException {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        zza(iObjectWrapper, com_google_android_gms_internal_ads_zzjj, str, null, com_google_android_gms_internal_ads_zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        if (this.zzbvb instanceof MediationInterstitialAdapter) {
            zzane.zzck("Requesting interstitial ad from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbvb).requestInterstitialAd(new zzyq(com_google_android_gms_internal_ads_zzxt), (Activity) ObjectWrapper.unwrap(iObjectWrapper), zza(str, com_google_android_gms_internal_ads_zzjj.zzaqa, str2), zzzc.zza(com_google_android_gms_internal_ads_zzjj, zzm(com_google_android_gms_internal_ads_zzjj)), this.zzbvc);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt, zzpl com_google_android_gms_internal_ads_zzpl, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        zza(iObjectWrapper, com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzjj, str, null, com_google_android_gms_internal_ads_zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        int i = 0;
        if (this.zzbvb instanceof MediationBannerAdapter) {
            zzane.zzck("Requesting banner ad from adapter.");
            try {
                AdSize adSize;
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbvb;
                MediationBannerListener com_google_android_gms_internal_ads_zzyq = new zzyq(com_google_android_gms_internal_ads_zzxt);
                Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
                MediationServerParameters zza = zza(str, com_google_android_gms_internal_ads_zzjj.zzaqa, str2);
                AdSize[] adSizeArr = new AdSize[]{AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
                while (i < 6) {
                    if (adSizeArr[i].getWidth() == com_google_android_gms_internal_ads_zzjn.width && adSizeArr[i].getHeight() == com_google_android_gms_internal_ads_zzjn.height) {
                        adSize = adSizeArr[i];
                        break;
                    }
                    i++;
                }
                adSize = new AdSize(zzb.zza(com_google_android_gms_internal_ads_zzjn.width, com_google_android_gms_internal_ads_zzjn.height, com_google_android_gms_internal_ads_zzjn.zzarb));
                mediationBannerAdapter.requestBannerAd(com_google_android_gms_internal_ads_zzyq, activity, zza, adSize, zzzc.zza(com_google_android_gms_internal_ads_zzjj, zzm(com_google_android_gms_internal_ads_zzjj)), this.zzbvc);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2) {
    }

    public final void zzc(zzjj com_google_android_gms_internal_ads_zzjj, String str) {
    }

    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzxz zzmo() {
        return null;
    }

    public final zzyc zzmp() {
        return null;
    }

    public final Bundle zzmq() {
        return new Bundle();
    }

    public final Bundle zzmr() {
        return new Bundle();
    }

    public final boolean zzms() {
        return false;
    }

    public final zzqs zzmt() {
        return null;
    }

    public final zzyf zzmu() {
        return null;
    }
}

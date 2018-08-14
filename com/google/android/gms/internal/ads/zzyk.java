package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzyk extends zzxr {
    private final MediationAdapter zzbus;
    private zzyl zzbut;

    public zzyk(MediationAdapter mediationAdapter) {
        this.zzbus = mediationAdapter;
    }

    private final Bundle zza(String str, zzjj com_google_android_gms_internal_ads_zzjj, String str2) throws RemoteException {
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    valueOf = (String) keys.next();
                    bundle2.putString(valueOf, jSONObject.getString(valueOf));
                }
                bundle = bundle2;
            }
            if (this.zzbus instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (com_google_android_gms_internal_ads_zzjj != null) {
                    bundle.putInt("tagForChildDirectedTreatment", com_google_android_gms_internal_ads_zzjj.zzaqa);
                }
            }
            return bundle;
        } catch (Throwable th) {
            zzane.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
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
            this.zzbus.onDestroy();
        } catch (Throwable th) {
            zzane.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        if (this.zzbus instanceof zzatm) {
            return ((zzatm) this.zzbus).getInterstitialAdapterInfo();
        }
        String str = "Not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
        zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final zzlo getVideoController() {
        if (!(this.zzbus instanceof zza)) {
            return null;
        }
        try {
            return ((zza) this.zzbus).getVideoController();
        } catch (Throwable th) {
            zzane.zzb("", th);
            return null;
        }
    }

    public final IObjectWrapper getView() throws RemoteException {
        if (this.zzbus instanceof MediationBannerAdapter) {
            try {
                return ObjectWrapper.wrap(((MediationBannerAdapter) this.zzbus).getBannerView());
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        if (this.zzbus instanceof MediationRewardedVideoAdAdapter) {
            zzane.zzck("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzbus).isInitialized();
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        try {
            this.zzbus.onPause();
        } catch (Throwable th) {
            zzane.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void resume() throws RemoteException {
        try {
            this.zzbus.onResume();
        } catch (Throwable th) {
            zzane.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        if (this.zzbus instanceof OnImmersiveModeUpdatedListener) {
            try {
                ((OnImmersiveModeUpdatedListener) this.zzbus).onImmersiveModeUpdated(z);
                return;
            } catch (Throwable th) {
                zzane.zzb("", th);
                return;
            }
        }
        String str = "Not an OnImmersiveModeUpdatedListener: ";
        String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
        zzane.zzdj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public final void showInterstitial() throws RemoteException {
        if (this.zzbus instanceof MediationInterstitialAdapter) {
            zzane.zzck("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbus).showInterstitial();
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        if (this.zzbus instanceof MediationRewardedVideoAdAdapter) {
            zzane.zzck("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzbus).showVideo();
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaic com_google_android_gms_internal_ads_zzaic, List<String> list) throws RemoteException {
        String str;
        if (this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter) {
            zzane.zzck("Initialize rewarded video adapter.");
            try {
                InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzbus;
                List arrayList = new ArrayList();
                for (String str2 : list) {
                    arrayList.add(zza(str2, null, null));
                }
                initializableMediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzaif(com_google_android_gms_internal_ads_zzaic), arrayList);
            } catch (Throwable th) {
                zzane.zzc("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            str2 = "Not an InitializableMediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzaic com_google_android_gms_internal_ads_zzaic, String str2) throws RemoteException {
        if (this.zzbus instanceof MediationRewardedVideoAdAdapter) {
            zzane.zzck("Initialize rewarded video adapter.");
            try {
                Bundle bundle;
                MediationAdRequest mediationAdRequest;
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbus;
                Bundle zza = zza(str2, com_google_android_gms_internal_ads_zzjj, null);
                if (com_google_android_gms_internal_ads_zzjj != null) {
                    zzyj com_google_android_gms_internal_ads_zzyj = new zzyj(com_google_android_gms_internal_ads_zzjj.zzapw == -1 ? null : new Date(com_google_android_gms_internal_ads_zzjj.zzapw), com_google_android_gms_internal_ads_zzjj.zzapx, com_google_android_gms_internal_ads_zzjj.zzapy != null ? new HashSet(com_google_android_gms_internal_ads_zzjj.zzapy) : null, com_google_android_gms_internal_ads_zzjj.zzaqe, zzm(com_google_android_gms_internal_ads_zzjj), com_google_android_gms_internal_ads_zzjj.zzaqa, com_google_android_gms_internal_ads_zzjj.zzaql);
                    if (com_google_android_gms_internal_ads_zzjj.zzaqg != null) {
                        bundle = com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                        mediationAdRequest = com_google_android_gms_internal_ads_zzyj;
                    } else {
                        bundle = null;
                        Object obj = com_google_android_gms_internal_ads_zzyj;
                    }
                } else {
                    bundle = null;
                    mediationAdRequest = null;
                }
                mediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), mediationAdRequest, str, new zzaif(com_google_android_gms_internal_ads_zzaic), zza, bundle);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        zza(iObjectWrapper, com_google_android_gms_internal_ads_zzjj, str, null, com_google_android_gms_internal_ads_zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        if (this.zzbus instanceof MediationInterstitialAdapter) {
            zzane.zzck("Requesting interstitial ad from adapter.");
            try {
                MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzbus;
                mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzyl(com_google_android_gms_internal_ads_zzxt), zza(str, com_google_android_gms_internal_ads_zzjj, str2), new zzyj(com_google_android_gms_internal_ads_zzjj.zzapw == -1 ? null : new Date(com_google_android_gms_internal_ads_zzjj.zzapw), com_google_android_gms_internal_ads_zzjj.zzapx, com_google_android_gms_internal_ads_zzjj.zzapy != null ? new HashSet(com_google_android_gms_internal_ads_zzjj.zzapy) : null, com_google_android_gms_internal_ads_zzjj.zzaqe, zzm(com_google_android_gms_internal_ads_zzjj), com_google_android_gms_internal_ads_zzjj.zzaqa, com_google_android_gms_internal_ads_zzjj.zzaql), com_google_android_gms_internal_ads_zzjj.zzaqg != null ? com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt, zzpl com_google_android_gms_internal_ads_zzpl, List<String> list) throws RemoteException {
        if (this.zzbus instanceof MediationNativeAdapter) {
            try {
                MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzbus;
                zzyo com_google_android_gms_internal_ads_zzyo = new zzyo(com_google_android_gms_internal_ads_zzjj.zzapw == -1 ? null : new Date(com_google_android_gms_internal_ads_zzjj.zzapw), com_google_android_gms_internal_ads_zzjj.zzapx, com_google_android_gms_internal_ads_zzjj.zzapy != null ? new HashSet(com_google_android_gms_internal_ads_zzjj.zzapy) : null, com_google_android_gms_internal_ads_zzjj.zzaqe, zzm(com_google_android_gms_internal_ads_zzjj), com_google_android_gms_internal_ads_zzjj.zzaqa, com_google_android_gms_internal_ads_zzpl, list, com_google_android_gms_internal_ads_zzjj.zzaql);
                Bundle bundle = com_google_android_gms_internal_ads_zzjj.zzaqg != null ? com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle(mediationNativeAdapter.getClass().getName()) : null;
                this.zzbut = new zzyl(com_google_android_gms_internal_ads_zzxt);
                mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzbut, zza(str, com_google_android_gms_internal_ads_zzjj, str2), com_google_android_gms_internal_ads_zzyo, bundle);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, zzjj com_google_android_gms_internal_ads_zzjj, String str, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        zza(iObjectWrapper, com_google_android_gms_internal_ads_zzjn, com_google_android_gms_internal_ads_zzjj, str, null, com_google_android_gms_internal_ads_zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn com_google_android_gms_internal_ads_zzjn, zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        if (this.zzbus instanceof MediationBannerAdapter) {
            zzane.zzck("Requesting banner ad from adapter.");
            try {
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbus;
                mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzyl(com_google_android_gms_internal_ads_zzxt), zza(str, com_google_android_gms_internal_ads_zzjj, str2), zzb.zza(com_google_android_gms_internal_ads_zzjn.width, com_google_android_gms_internal_ads_zzjn.height, com_google_android_gms_internal_ads_zzjn.zzarb), new zzyj(com_google_android_gms_internal_ads_zzjj.zzapw == -1 ? null : new Date(com_google_android_gms_internal_ads_zzjj.zzapw), com_google_android_gms_internal_ads_zzjj.zzapx, com_google_android_gms_internal_ads_zzjj.zzapy != null ? new HashSet(com_google_android_gms_internal_ads_zzjj.zzapy) : null, com_google_android_gms_internal_ads_zzjj.zzaqe, zzm(com_google_android_gms_internal_ads_zzjj), com_google_android_gms_internal_ads_zzjj.zzaqa, com_google_android_gms_internal_ads_zzjj.zzaql), com_google_android_gms_internal_ads_zzjj.zzaqg != null ? com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle(mediationBannerAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zza(zzjj com_google_android_gms_internal_ads_zzjj, String str, String str2) throws RemoteException {
        if (this.zzbus instanceof MediationRewardedVideoAdAdapter) {
            zzane.zzck("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbus;
                mediationRewardedVideoAdAdapter.loadAd(new zzyj(com_google_android_gms_internal_ads_zzjj.zzapw == -1 ? null : new Date(com_google_android_gms_internal_ads_zzjj.zzapw), com_google_android_gms_internal_ads_zzjj.zzapx, com_google_android_gms_internal_ads_zzjj.zzapy != null ? new HashSet(com_google_android_gms_internal_ads_zzjj.zzapy) : null, com_google_android_gms_internal_ads_zzjj.zzaqe, zzm(com_google_android_gms_internal_ads_zzjj), com_google_android_gms_internal_ads_zzjj.zzaqa, com_google_android_gms_internal_ads_zzjj.zzaql), zza(str, com_google_android_gms_internal_ads_zzjj, str2), com_google_android_gms_internal_ads_zzjj.zzaqg != null ? com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzane.zzb("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            zzane.zzdk(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public final void zzc(zzjj com_google_android_gms_internal_ads_zzjj, String str) throws RemoteException {
        zza(com_google_android_gms_internal_ads_zzjj, str, null);
    }

    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzbus).onContextChanged((Context) ObjectWrapper.unwrap(iObjectWrapper));
        } catch (Throwable th) {
            zzane.zzc("Failed", th);
        }
    }

    public final zzxz zzmo() {
        NativeAdMapper zzmx = this.zzbut.zzmx();
        return zzmx instanceof NativeAppInstallAdMapper ? new zzym((NativeAppInstallAdMapper) zzmx) : null;
    }

    public final zzyc zzmp() {
        NativeAdMapper zzmx = this.zzbut.zzmx();
        return zzmx instanceof NativeContentAdMapper ? new zzyn((NativeContentAdMapper) zzmx) : null;
    }

    public final Bundle zzmq() {
        if (this.zzbus instanceof zzatl) {
            return ((zzatl) this.zzbus).zzmq();
        }
        String str = "Not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
        zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final Bundle zzmr() {
        return new Bundle();
    }

    public final boolean zzms() {
        return this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final zzqs zzmt() {
        NativeCustomTemplateAd zzmz = this.zzbut.zzmz();
        return zzmz instanceof zzqv ? ((zzqv) zzmz).zzku() : null;
    }

    public final zzyf zzmu() {
        UnifiedNativeAdMapper zzmy = this.zzbut.zzmy();
        return zzmy != null ? new zzze(zzmy) : null;
    }
}

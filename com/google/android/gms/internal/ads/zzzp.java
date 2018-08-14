package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Iterator;
import org.json.JSONObject;

public final class zzzp extends zzzk {
    private final zzatg zzbvi;

    public zzzp(zzatg com_google_android_gms_internal_ads_zzatg) {
        this.zzbvi = com_google_android_gms_internal_ads_zzatg;
    }

    private static Bundle zzbt(String str) throws RemoteException {
        String str2 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzane.zzdk(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                valueOf = (String) keys.next();
                bundle2.putString(valueOf, jSONObject.getString(valueOf));
            }
            return bundle2;
        } catch (Throwable e) {
            zzane.zzb("", e);
            throw new RemoteException();
        }
    }

    public final zzlo getVideoController() {
        if (!(this.zzbvi instanceof zza)) {
            return null;
        }
        try {
            return ((zza) this.zzbvi).getVideoController();
        } catch (Throwable th) {
            zzane.zzb("", th);
            return null;
        }
    }

    public final void showInterstitial() throws RemoteException {
        zzate com_google_android_gms_internal_ads_zzate = null;
        try {
            com_google_android_gms_internal_ads_zzate.zzoy();
        } catch (Throwable th) {
            zzane.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, zzzm com_google_android_gms_internal_ads_zzzm) throws RemoteException {
        try {
            int i;
            Object com_google_android_gms_internal_ads_zzzs = new zzzs(this, com_google_android_gms_internal_ads_zzzm);
            zzatg com_google_android_gms_internal_ads_zzatg = this.zzbvi;
            Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            Object obj = -1;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals("banner")) {
                        obj = null;
                        break;
                    }
                    break;
                case -1052618729:
                    if (str.equals("native")) {
                        obj = 3;
                        break;
                    }
                    break;
                case -239580146:
                    if (str.equals("rewarded")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 604727084:
                    if (str.equals("interstitial")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    i = zzath.zzdgn;
                    break;
                case 1:
                    i = zzath.zzdgo;
                    break;
                case 2:
                    i = zzath.zzdgp;
                    break;
                case 3:
                    i = zzath.zzdgq;
                    break;
                default:
                    throw new IllegalArgumentException("Internal Error");
            }
            com_google_android_gms_internal_ads_zzatg.zza(new zzati(context, i, bundle), com_google_android_gms_internal_ads_zzzs);
        } catch (Throwable th) {
            zzane.zzb("Error generating signals for RTB", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzf com_google_android_gms_internal_ads_zzzf, zzxt com_google_android_gms_internal_ads_zzxt, zzjn com_google_android_gms_internal_ads_zzjn) throws RemoteException {
        try {
            zzatd com_google_android_gms_internal_ads_zzzq = new zzzq(this, com_google_android_gms_internal_ads_zzzf, com_google_android_gms_internal_ads_zzxt);
            zzatg com_google_android_gms_internal_ads_zzatg = this.zzbvi;
            zzatf com_google_android_gms_internal_ads_zzatf = new zzatf((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr, zzbt(str), bundle);
            zzb.zza(com_google_android_gms_internal_ads_zzjn.width, com_google_android_gms_internal_ads_zzjn.height, com_google_android_gms_internal_ads_zzjn.zzarb);
            com_google_android_gms_internal_ads_zzzq.zzau(String.valueOf(com_google_android_gms_internal_ads_zzatg.getClass().getSimpleName()).concat(" does not support banner."));
        } catch (Throwable th) {
            zzane.zzb("Adapter failed to render banner ad.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzh com_google_android_gms_internal_ads_zzzh, zzxt com_google_android_gms_internal_ads_zzxt) throws RemoteException {
        try {
            zzatd com_google_android_gms_internal_ads_zzzr = new zzzr(this, com_google_android_gms_internal_ads_zzzh, com_google_android_gms_internal_ads_zzxt);
            zzatg com_google_android_gms_internal_ads_zzatg = this.zzbvi;
            zzatf com_google_android_gms_internal_ads_zzatf = new zzatf((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr, zzbt(str), bundle);
            com_google_android_gms_internal_ads_zzzr.zzau(String.valueOf(com_google_android_gms_internal_ads_zzatg.getClass().getSimpleName()).concat(" does not support interstitial."));
        } catch (Throwable th) {
            zzane.zzb("Adapter failed to render interstitial ad.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final zzzt zznc() throws RemoteException {
        return zzzt.zza(this.zzbvi.zzwa());
    }

    public final zzzt zznd() throws RemoteException {
        return zzzt.zza(this.zzbvi.zzvz());
    }
}

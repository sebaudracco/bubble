package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzadh
public final class zzahi extends RemoteCreator<zzahc> {
    public zzahi() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        return queryLocalInterface instanceof zzahc ? (zzahc) queryLocalInterface : new zzahd(iBinder);
    }

    public final zzagz zza(Context context, zzxn com_google_android_gms_internal_ads_zzxn) {
        Throwable e;
        try {
            IBinder zza = ((zzahc) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), com_google_android_gms_internal_ads_zzxn, 12451000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            return queryLocalInterface instanceof zzagz ? (zzagz) queryLocalInterface : new zzahb(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzane.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        } catch (RemoteCreatorException e3) {
            e = e3;
            zzane.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }
}

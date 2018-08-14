package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzadh
public final class zzjg extends RemoteCreator<zzkq> {
    public zzjg() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        return queryLocalInterface instanceof zzkq ? (zzkq) queryLocalInterface : new zzkr(iBinder);
    }

    public final zzkn zza(Context context, String str, zzxn com_google_android_gms_internal_ads_zzxn) {
        Throwable e;
        try {
            IBinder zza = ((zzkq) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), str, com_google_android_gms_internal_ads_zzxn, 12451000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return queryLocalInterface instanceof zzkn ? (zzkn) queryLocalInterface : new zzkp(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzane.zzc("Could not create remote builder for AdLoader.", e);
            return null;
        } catch (RemoteCreatorException e3) {
            e = e3;
            zzane.zzc("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }
}

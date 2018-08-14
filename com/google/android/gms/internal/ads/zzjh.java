package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzadh
public final class zzjh extends RemoteCreator<zzkv> {
    @VisibleForTesting
    public zzjh() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return queryLocalInterface instanceof zzkv ? (zzkv) queryLocalInterface : new zzkw(iBinder);
    }

    public final zzks zza(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, int i) {
        Throwable e;
        try {
            IBinder zza = ((zzkv) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, 12451000, i);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return queryLocalInterface instanceof zzks ? (zzks) queryLocalInterface : new zzku(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzane.zza("Could not create remote AdManager.", e);
            return null;
        } catch (RemoteCreatorException e3) {
            e = e3;
            zzane.zza("Could not create remote AdManager.", e);
            return null;
        }
    }
}

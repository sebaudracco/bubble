package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import java.util.HashMap;

@zzadh
public final class zzrw extends RemoteCreator<zzqi> {
    @VisibleForTesting
    public zzrw() {
        super("com.google.android.gms.ads.NativeAdViewHolderDelegateCreatorImpl");
    }

    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegateCreator");
        return queryLocalInterface instanceof zzqi ? (zzqi) queryLocalInterface : new zzqj(iBinder);
    }

    public final zzqf zzb(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        Throwable e;
        try {
            IBinder zza = ((zzqi) getRemoteCreatorInstance(view.getContext())).zza(ObjectWrapper.wrap(view), ObjectWrapper.wrap(hashMap), ObjectWrapper.wrap(hashMap2));
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
            return queryLocalInterface instanceof zzqf ? (zzqf) queryLocalInterface : new zzqh(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzane.zzc("Could not create remote NativeAdViewHolderDelegate.", e);
            return null;
        } catch (RemoteCreatorException e3) {
            e = e3;
            zzane.zzc("Could not create remote NativeAdViewHolderDelegate.", e);
            return null;
        }
    }
}

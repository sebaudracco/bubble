package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzadh
public final class zzrv extends RemoteCreator<zzqd> {
    @VisibleForTesting
    public zzrv() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        return queryLocalInterface instanceof zzqd ? (zzqd) queryLocalInterface : new zzqe(iBinder);
    }

    public final zzqa zzb(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        Throwable e;
        try {
            IBinder zza = ((zzqd) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), ObjectWrapper.wrap(frameLayout), ObjectWrapper.wrap(frameLayout2), 12451000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
            return queryLocalInterface instanceof zzqa ? (zzqa) queryLocalInterface : new zzqc(zza);
        } catch (RemoteException e2) {
            e = e2;
            zzane.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        } catch (RemoteCreatorException e3) {
            e = e3;
            zzane.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }
}

package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzaao extends RemoteCreator<zzaas> {
    public zzaao() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        return queryLocalInterface instanceof zzaas ? (zzaas) queryLocalInterface : new zzaat(iBinder);
    }

    public final zzaap zze(Activity activity) {
        try {
            IBinder zzp = ((zzaas) getRemoteCreatorInstance(activity)).zzp(ObjectWrapper.wrap(activity));
            if (zzp == null) {
                return null;
            }
            IInterface queryLocalInterface = zzp.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            return queryLocalInterface instanceof zzaap ? (zzaap) queryLocalInterface : new zzaar(zzp);
        } catch (Throwable e) {
            zzane.zzc("Could not create remote AdOverlay.", e);
            return null;
        } catch (Throwable e2) {
            zzane.zzc("Could not create remote AdOverlay.", e2);
            return null;
        }
    }
}

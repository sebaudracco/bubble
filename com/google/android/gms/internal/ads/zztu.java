package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Random;

final class zztu extends zzki {
    private final zzkh zzboi;

    zztu(zzkh com_google_android_gms_internal_ads_zzkh) {
        this.zzboi = com_google_android_gms_internal_ads_zzkh;
    }

    public final void onAdClicked() throws RemoteException {
        this.zzboi.onAdClicked();
    }

    public final void onAdClosed() throws RemoteException {
        if (zzud.zzlv()) {
            int intValue = ((Integer) zzkb.zzik().zzd(zznk.zzazg)).intValue();
            int intValue2 = ((Integer) zzkb.zzik().zzd(zznk.zzazh)).intValue();
            if (intValue <= 0 || intValue2 < 0) {
                zzbv.zzex().zzld();
            } else {
                zzakk.zzcrm.postDelayed(zztv.zzboj, (long) (new Random().nextInt(intValue2 + 1) + intValue));
            }
        }
        this.zzboi.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzboi.onAdFailedToLoad(i);
    }

    public final void onAdImpression() throws RemoteException {
        this.zzboi.onAdImpression();
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.zzboi.onAdLeftApplication();
    }

    public final void onAdLoaded() throws RemoteException {
        this.zzboi.onAdLoaded();
    }

    public final void onAdOpened() throws RemoteException {
        this.zzboi.onAdOpened();
    }
}

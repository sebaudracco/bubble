package com.google.android.gms.ads.identifier;

import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
class AdvertisingIdClient$zza extends Thread {
    private WeakReference<AdvertisingIdClient> zzm;
    private long zzn;
    CountDownLatch zzo = new CountDownLatch(1);
    boolean zzp = false;

    public AdvertisingIdClient$zza(AdvertisingIdClient advertisingIdClient, long j) {
        this.zzm = new WeakReference(advertisingIdClient);
        this.zzn = j;
        start();
    }

    private final void disconnect() {
        AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.zzm.get();
        if (advertisingIdClient != null) {
            advertisingIdClient.finish();
            this.zzp = true;
        }
    }

    public final void run() {
        try {
            if (!this.zzo.await(this.zzn, TimeUnit.MILLISECONDS)) {
                disconnect();
            }
        } catch (InterruptedException e) {
            disconnect();
        }
    }
}

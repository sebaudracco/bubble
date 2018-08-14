package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
final class zzatr implements BaseGmsClient$BaseConnectionCallbacks, BaseGmsClient$BaseOnConnectionFailedListener {
    private final String packageName;
    @VisibleForTesting
    private zzats zzdgz;
    private final String zzdha;
    private final LinkedBlockingQueue<zzba> zzdhb;
    private final HandlerThread zzdhc = new HandlerThread("GassClient");

    public zzatr(Context context, String str, String str2) {
        this.packageName = str;
        this.zzdha = str2;
        this.zzdhc.start();
        this.zzdgz = new zzats(context, this.zzdhc.getLooper(), this, this);
        this.zzdhb = new LinkedBlockingQueue();
        this.zzdgz.checkAvailabilityAndConnect();
    }

    private final void zznz() {
        if (this.zzdgz == null) {
            return;
        }
        if (this.zzdgz.isConnected() || this.zzdgz.isConnecting()) {
            this.zzdgz.disconnect();
        }
    }

    private final zzatx zzwb() {
        try {
            return this.zzdgz.zzwd();
        } catch (IllegalStateException e) {
            return null;
        } catch (DeadObjectException e2) {
            return null;
        }
    }

    @VisibleForTesting
    private static zzba zzwc() {
        zzba com_google_android_gms_internal_ads_zzba = new zzba();
        com_google_android_gms_internal_ads_zzba.zzdu = Long.valueOf(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        return com_google_android_gms_internal_ads_zzba;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r5) {
        /*
        r4 = this;
        r0 = r4.zzwb();
        if (r0 == 0) goto L_0x0024;
    L_0x0006:
        r1 = new com.google.android.gms.internal.ads.zzatt;	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r2 = r4.packageName;	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r3 = r4.zzdha;	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r0 = r0.zza(r1);	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r0 = r0.zzwe();	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r1 = r4.zzdhb;	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r1.put(r0);	 Catch:{ Throwable -> 0x0025, all -> 0x0038 }
        r4.zznz();
        r0 = r4.zzdhc;
        r0.quit();
    L_0x0024:
        return;
    L_0x0025:
        r0 = move-exception;
        r0 = r4.zzdhb;	 Catch:{ InterruptedException -> 0x0042, all -> 0x0038 }
        r1 = zzwc();	 Catch:{ InterruptedException -> 0x0042, all -> 0x0038 }
        r0.put(r1);	 Catch:{ InterruptedException -> 0x0042, all -> 0x0038 }
    L_0x002f:
        r4.zznz();
        r0 = r4.zzdhc;
        r0.quit();
        goto L_0x0024;
    L_0x0038:
        r0 = move-exception;
        r4.zznz();
        r1 = r4.zzdhc;
        r1.quit();
        throw r0;
    L_0x0042:
        r0 = move-exception;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatr.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzdhb.put(zzwc());
        } catch (InterruptedException e) {
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            this.zzdhb.put(zzwc());
        } catch (InterruptedException e) {
        }
    }

    public final zzba zzak(int i) {
        zzba com_google_android_gms_internal_ads_zzba;
        try {
            com_google_android_gms_internal_ads_zzba = (zzba) this.zzdhb.poll(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            com_google_android_gms_internal_ads_zzba = null;
        }
        return com_google_android_gms_internal_ads_zzba == null ? zzwc() : com_google_android_gms_internal_ads_zzba;
    }
}

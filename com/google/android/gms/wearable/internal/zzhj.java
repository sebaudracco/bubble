package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzhj implements Runnable {
    private final /* synthetic */ Uri zzco;
    private final /* synthetic */ long zzcq;
    private final /* synthetic */ long zzcr;
    private final /* synthetic */ String zzcs;
    private final /* synthetic */ ResultHolder zzfh;
    private final /* synthetic */ zzhg zzfi;

    zzhj(zzhg com_google_android_gms_wearable_internal_zzhg, Uri uri, ResultHolder resultHolder, String str, long j, long j2) {
        this.zzfi = com_google_android_gms_wearable_internal_zzhg;
        this.zzco = uri;
        this.zzfh = resultHolder;
        this.zzcs = str;
        this.zzcq = j;
        this.zzcr = j2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r8 = this;
        r0 = "WearableClient";
        r1 = 2;
        r0 = android.util.Log.isLoggable(r0, r1);
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = "WearableClient";
        r1 = "Executing sendFileToChannelTask";
        android.util.Log.v(r0, r1);
    L_0x0013:
        r0 = "file";
        r1 = r8.zzco;
        r1 = r1.getScheme();
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x003b;
    L_0x0022:
        r0 = "WearableClient";
        r1 = "Channel.sendFile used with non-file URI";
        android.util.Log.w(r0, r1);
        r0 = r8.zzfh;
        r1 = new com.google.android.gms.common.api.Status;
        r2 = 10;
        r3 = "Channel.sendFile used with non-file URI";
        r1.<init>(r2, r3);
        r0.setFailedResult(r1);
    L_0x003a:
        return;
    L_0x003b:
        r0 = new java.io.File;
        r1 = r8.zzco;
        r1 = r1.getPath();
        r0.<init>(r1);
        r1 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r3 = android.os.ParcelFileDescriptor.open(r0, r1);	 Catch:{ FileNotFoundException -> 0x0073 }
        r0 = r8.zzfi;	 Catch:{ RemoteException -> 0x00a9 }
        r0 = r0.getService();	 Catch:{ RemoteException -> 0x00a9 }
        r0 = (com.google.android.gms.wearable.internal.zzep) r0;	 Catch:{ RemoteException -> 0x00a9 }
        r1 = new com.google.android.gms.wearable.internal.zzhc;	 Catch:{ RemoteException -> 0x00a9 }
        r2 = r8.zzfh;	 Catch:{ RemoteException -> 0x00a9 }
        r1.<init>(r2);	 Catch:{ RemoteException -> 0x00a9 }
        r2 = r8.zzcs;	 Catch:{ RemoteException -> 0x00a9 }
        r4 = r8.zzcq;	 Catch:{ RemoteException -> 0x00a9 }
        r6 = r8.zzcr;	 Catch:{ RemoteException -> 0x00a9 }
        r0.zza(r1, r2, r3, r4, r6);	 Catch:{ RemoteException -> 0x00a9 }
        r3.close();	 Catch:{ IOException -> 0x0068 }
        goto L_0x003a;
    L_0x0068:
        r0 = move-exception;
        r1 = "WearableClient";
        r2 = "Failed to close sourceFd";
        android.util.Log.w(r1, r2, r0);
        goto L_0x003a;
    L_0x0073:
        r1 = move-exception;
        r1 = "WearableClient";
        r0 = java.lang.String.valueOf(r0);
        r2 = java.lang.String.valueOf(r0);
        r2 = r2.length();
        r2 = r2 + 46;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "File couldn't be opened for Channel.sendFile: ";
        r2 = r3.append(r2);
        r0 = r2.append(r0);
        r0 = r0.toString();
        android.util.Log.w(r1, r0);
        r0 = r8.zzfh;
        r1 = new com.google.android.gms.common.api.Status;
        r2 = 13;
        r1.<init>(r2);
        r0.setFailedResult(r1);
        goto L_0x003a;
    L_0x00a9:
        r0 = move-exception;
        r1 = "WearableClient";
        r2 = "Channel.sendFile failed.";
        android.util.Log.w(r1, r2, r0);	 Catch:{ all -> 0x00d0 }
        r0 = r8.zzfh;	 Catch:{ all -> 0x00d0 }
        r1 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x00d0 }
        r2 = 8;
        r1.<init>(r2);	 Catch:{ all -> 0x00d0 }
        r0.setFailedResult(r1);	 Catch:{ all -> 0x00d0 }
        r3.close();	 Catch:{ IOException -> 0x00c4 }
        goto L_0x003a;
    L_0x00c4:
        r0 = move-exception;
        r1 = "WearableClient";
        r2 = "Failed to close sourceFd";
        android.util.Log.w(r1, r2, r0);
        goto L_0x003a;
    L_0x00d0:
        r0 = move-exception;
        r3.close();	 Catch:{ IOException -> 0x00d5 }
    L_0x00d4:
        throw r0;
    L_0x00d5:
        r1 = move-exception;
        r2 = "WearableClient";
        r3 = "Failed to close sourceFd";
        android.util.Log.w(r2, r3, r1);
        goto L_0x00d4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wearable.internal.zzhj.run():void");
    }
}

package com.google.android.gms.iid;

final /* synthetic */ class zzw implements Runnable {
    private final zzt zzch;

    zzw(zzt com_google_android_gms_iid_zzt) {
        this.zzch = com_google_android_gms_iid_zzt;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r8 = this;
        r7 = 2;
        r1 = r8.zzch;
    L_0x0003:
        monitor-enter(r1);
        r0 = r1.state;	 Catch:{ all -> 0x0017 }
        if (r0 == r7) goto L_0x000a;
    L_0x0008:
        monitor-exit(r1);	 Catch:{ all -> 0x0017 }
    L_0x0009:
        return;
    L_0x000a:
        r0 = r1.zzce;	 Catch:{ all -> 0x0017 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x001a;
    L_0x0012:
        r1.zzs();	 Catch:{ all -> 0x0017 }
        monitor-exit(r1);	 Catch:{ all -> 0x0017 }
        goto L_0x0009;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0017 }
        throw r0;
    L_0x001a:
        r0 = r1.zzce;	 Catch:{ all -> 0x0017 }
        r0 = r0.poll();	 Catch:{ all -> 0x0017 }
        r0 = (com.google.android.gms.iid.zzz) r0;	 Catch:{ all -> 0x0017 }
        r2 = r1.zzcf;	 Catch:{ all -> 0x0017 }
        r3 = r0.zzck;	 Catch:{ all -> 0x0017 }
        r2.put(r3, r0);	 Catch:{ all -> 0x0017 }
        r2 = r1.zzcg;	 Catch:{ all -> 0x0017 }
        r2 = r2.zzbz;	 Catch:{ all -> 0x0017 }
        r3 = new com.google.android.gms.iid.zzx;	 Catch:{ all -> 0x0017 }
        r3.<init>(r1, r0);	 Catch:{ all -> 0x0017 }
        r4 = 30;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ all -> 0x0017 }
        r2.schedule(r3, r4, r6);	 Catch:{ all -> 0x0017 }
        monitor-exit(r1);	 Catch:{ all -> 0x0017 }
        r2 = "MessengerIpcClient";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);
        if (r2 == 0) goto L_0x006e;
    L_0x0046:
        r2 = "MessengerIpcClient";
        r3 = java.lang.String.valueOf(r0);
        r4 = java.lang.String.valueOf(r3);
        r4 = r4.length();
        r4 = r4 + 8;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r4);
        r4 = "Sending ";
        r4 = r5.append(r4);
        r3 = r4.append(r3);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
    L_0x006e:
        r2 = r1.zzcg;
        r2 = r2.zzk;
        r3 = r1.zzcc;
        r4 = android.os.Message.obtain();
        r5 = r0.what;
        r4.what = r5;
        r5 = r0.zzck;
        r4.arg1 = r5;
        r4.replyTo = r3;
        r3 = new android.os.Bundle;
        r3.<init>();
        r5 = "oneWay";
        r6 = r0.zzu();
        r3.putBoolean(r5, r6);
        r5 = "pkg";
        r2 = r2.getPackageName();
        r3.putString(r5, r2);
        r2 = "data";
        r0 = r0.zzcm;
        r3.putBundle(r2, r0);
        r4.setData(r3);
        r0 = r1.zzcd;	 Catch:{ RemoteException -> 0x00b5 }
        r2 = r0.zzab;	 Catch:{ RemoteException -> 0x00b5 }
        if (r2 == 0) goto L_0x00bf;
    L_0x00ae:
        r0 = r0.zzab;	 Catch:{ RemoteException -> 0x00b5 }
        r0.send(r4);	 Catch:{ RemoteException -> 0x00b5 }
        goto L_0x0003;
    L_0x00b5:
        r0 = move-exception;
        r0 = r0.getMessage();
        r1.zzd(r7, r0);
        goto L_0x0003;
    L_0x00bf:
        r2 = r0.zzcj;	 Catch:{ RemoteException -> 0x00b5 }
        if (r2 == 0) goto L_0x00ca;
    L_0x00c3:
        r0 = r0.zzcj;	 Catch:{ RemoteException -> 0x00b5 }
        r0.send(r4);	 Catch:{ RemoteException -> 0x00b5 }
        goto L_0x0003;
    L_0x00ca:
        r0 = new java.lang.IllegalStateException;	 Catch:{ RemoteException -> 0x00b5 }
        r2 = "Both messengers are null";
        r0.<init>(r2);	 Catch:{ RemoteException -> 0x00b5 }
        throw r0;	 Catch:{ RemoteException -> 0x00b5 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzw.run():void");
    }
}

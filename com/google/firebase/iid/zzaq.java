package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

final class zzaq implements Runnable {
    private final zzae zzal;
    private final FirebaseInstanceId zzaw;
    private final long zzcw;
    private final WakeLock zzcx = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");

    @VisibleForTesting
    zzaq(FirebaseInstanceId firebaseInstanceId, zzae com_google_firebase_iid_zzae, long j) {
        this.zzaw = firebaseInstanceId;
        this.zzal = com_google_firebase_iid_zzae;
        this.zzcw = j;
        this.zzcx.setReferenceCounted(false);
    }

    @VisibleForTesting
    private final boolean zzah() {
        String zzh;
        Exception e;
        String str;
        String valueOf;
        zzap zzg = this.zzaw.zzg();
        if (zzg != null && !zzg.zzj(this.zzal.zzy())) {
            return true;
        }
        try {
            zzh = this.zzaw.zzh();
            if (zzh == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzg != null && (zzg == null || zzh.equals(zzg.zzcu))) {
                return true;
            }
            Context context = getContext();
            Parcelable intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
            Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
            intent2.setClass(context, FirebaseInstanceIdReceiver.class);
            intent2.putExtra("wrapped_intent", intent);
            context.sendBroadcast(intent2);
            return true;
        } catch (IOException e2) {
            e = e2;
            str = "FirebaseInstanceId";
            zzh = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            Log.e(str, valueOf.length() == 0 ? zzh.concat(valueOf) : new String(zzh));
            return false;
        } catch (SecurityException e3) {
            e = e3;
            str = "FirebaseInstanceId";
            zzh = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            Log.e(str, valueOf.length() == 0 ? zzh.concat(valueOf) : new String(zzh));
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.util.VisibleForTesting
    private final boolean zzai() {
        /*
        r3 = this;
    L_0x0000:
        r1 = r3.zzaw;
        monitor-enter(r1);
        r0 = com.google.firebase.iid.FirebaseInstanceId.zzi();	 Catch:{ all -> 0x0022 }
        r0 = r0.zzaf();	 Catch:{ all -> 0x0022 }
        if (r0 != 0) goto L_0x0019;
    L_0x000d:
        r0 = "FirebaseInstanceId";
        r2 = "topic sync succeeded";
        android.util.Log.d(r0, r2);	 Catch:{ all -> 0x0022 }
        r0 = 1;
        monitor-exit(r1);	 Catch:{ all -> 0x0022 }
    L_0x0018:
        return r0;
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x0022 }
        r1 = r3.zzk(r0);
        if (r1 != 0) goto L_0x0025;
    L_0x0020:
        r0 = 0;
        goto L_0x0018;
    L_0x0022:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0022 }
        throw r0;
    L_0x0025:
        r1 = com.google.firebase.iid.FirebaseInstanceId.zzi();
        r1.zzf(r0);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzaq.zzai():boolean");
    }

    private final boolean zzk(String str) {
        String str2;
        String valueOf;
        String[] split = str.split("!");
        if (split.length != 2) {
            return true;
        }
        String str3 = split[0];
        String str4 = split[1];
        int i = -1;
        try {
            switch (str3.hashCode()) {
                case 83:
                    if (str3.equals("S")) {
                        i = 0;
                        break;
                    }
                    break;
                case 85:
                    if (str3.equals("U")) {
                        boolean z = true;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    this.zzaw.zzb(str4);
                    if (!FirebaseInstanceId.zzj()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    return true;
                case 1:
                    this.zzaw.zzc(str4);
                    if (!FirebaseInstanceId.zzj()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    return true;
                default:
                    return true;
            }
        } catch (IOException e) {
            str2 = "FirebaseInstanceId";
            str3 = "Topic sync failed: ";
            valueOf = String.valueOf(e.getMessage());
            Log.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf));
            return false;
        }
        str2 = "FirebaseInstanceId";
        str3 = "Topic sync failed: ";
        valueOf = String.valueOf(e.getMessage());
        if (valueOf.length() == 0) {
        }
        Log.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf));
        return false;
    }

    final Context getContext() {
        return this.zzaw.zze().getApplicationContext();
    }

    public final void run() {
        Object obj = 1;
        this.zzcx.acquire();
        try {
            this.zzaw.zza(true);
            if (this.zzal.zzx() == 0) {
                obj = null;
            }
            if (obj == null) {
                this.zzaw.zza(false);
            } else if (zzaj()) {
                if (zzah() && zzai()) {
                    this.zzaw.zza(false);
                } else {
                    this.zzaw.zza(this.zzcw);
                }
                this.zzcx.release();
            } else {
                new zzar(this).zzak();
                this.zzcx.release();
            }
        } finally {
            this.zzcx.release();
        }
    }

    final boolean zzaj() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

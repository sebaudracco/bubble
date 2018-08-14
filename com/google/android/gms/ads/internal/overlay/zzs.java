package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzaaq;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzs extends zzaaq {
    private AdOverlayInfoParcel zzbza;
    private boolean zzbzb = false;
    private boolean zzbzc = false;
    private Activity zztk;

    public zzs(Activity activity, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzbza = adOverlayInfoParcel;
        this.zztk = activity;
    }

    private final synchronized void zznr() {
        if (!this.zzbzc) {
            if (this.zzbza.zzbyn != null) {
                this.zzbza.zzbyn.zzcb();
            }
            this.zzbzc = true;
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
    }

    public final void onBackPressed() throws RemoteException {
    }

    public final void onCreate(Bundle bundle) {
        boolean z = false;
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        if (this.zzbza == null) {
            this.zztk.finish();
        } else if (z) {
            this.zztk.finish();
        } else {
            if (bundle == null) {
                if (this.zzbza.zzbym != null) {
                    this.zzbza.zzbym.onAdClicked();
                }
                if (!(this.zztk.getIntent() == null || !this.zztk.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) || this.zzbza.zzbyn == null)) {
                    this.zzbza.zzbyn.zzcc();
                }
            }
            zzbv.zzeh();
            if (!zza.zza(this.zztk, this.zzbza.zzbyl, this.zzbza.zzbyt)) {
                this.zztk.finish();
            }
        }
    }

    public final void onDestroy() throws RemoteException {
        if (this.zztk.isFinishing()) {
            zznr();
        }
    }

    public final void onPause() throws RemoteException {
        if (this.zzbza.zzbyn != null) {
            this.zzbza.zzbyn.onPause();
        }
        if (this.zztk.isFinishing()) {
            zznr();
        }
    }

    public final void onRestart() throws RemoteException {
    }

    public final void onResume() throws RemoteException {
        if (this.zzbzb) {
            this.zztk.finish();
            return;
        }
        this.zzbzb = true;
        if (this.zzbza.zzbyn != null) {
            this.zzbza.zzbyn.onResume();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbzb);
    }

    public final void onStart() throws RemoteException {
    }

    public final void onStop() throws RemoteException {
        if (this.zztk.isFinishing()) {
            zznr();
        }
    }

    public final void zzax() throws RemoteException {
    }

    public final boolean zznj() throws RemoteException {
        return false;
    }

    public final void zzo(IObjectWrapper iObjectWrapper) throws RemoteException {
    }
}

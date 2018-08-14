package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzyi extends zzlp {
    private final Object mLock = new Object();
    private volatile zzlr zzbuq;

    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }

    public final void mute(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void play() throws RemoteException {
        throw new RemoteException();
    }

    public final void zza(zzlr com_google_android_gms_internal_ads_zzlr) throws RemoteException {
        synchronized (this.mLock) {
            this.zzbuq = com_google_android_gms_internal_ads_zzlr;
        }
    }

    public final float zzim() throws RemoteException {
        throw new RemoteException();
    }

    public final float zzin() throws RemoteException {
        throw new RemoteException();
    }

    public final zzlr zzio() throws RemoteException {
        zzlr com_google_android_gms_internal_ads_zzlr;
        synchronized (this.mLock) {
            com_google_android_gms_internal_ads_zzlr = this.zzbuq;
        }
        return com_google_android_gms_internal_ads_zzlr;
    }
}

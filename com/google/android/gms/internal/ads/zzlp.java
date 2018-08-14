package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzlp extends zzek implements zzlo {
    public zzlp() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzlo zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return queryLocalInterface instanceof zzlo ? (zzlo) queryLocalInterface : new zzlq(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean isMuted;
        float zzim;
        IInterface queryLocalInterface;
        switch (i) {
            case 1:
                play();
                parcel2.writeNoException();
                break;
            case 2:
                pause();
                parcel2.writeNoException();
                break;
            case 3:
                mute(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            case 4:
                isMuted = isMuted();
                parcel2.writeNoException();
                zzel.zza(parcel2, isMuted);
                break;
            case 5:
                int playbackState = getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(playbackState);
                break;
            case 6:
                zzim = zzim();
                parcel2.writeNoException();
                parcel2.writeFloat(zzim);
                break;
            case 7:
                zzim = zzin();
                parcel2.writeNoException();
                parcel2.writeFloat(zzim);
                break;
            case 8:
                zzlr com_google_android_gms_internal_ads_zzlr;
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    com_google_android_gms_internal_ads_zzlr = null;
                } else {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    com_google_android_gms_internal_ads_zzlr = queryLocalInterface instanceof zzlr ? (zzlr) queryLocalInterface : new zzlt(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzlr);
                parcel2.writeNoException();
                break;
            case 9:
                zzim = getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(zzim);
                break;
            case 10:
                isMuted = isCustomControlsEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, isMuted);
                break;
            case 11:
                queryLocalInterface = zzio();
                parcel2.writeNoException();
                zzel.zza(parcel2, queryLocalInterface);
                break;
            case 12:
                isMuted = isClickToExpandEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, isMuted);
                break;
            default:
                return false;
        }
        return true;
    }
}

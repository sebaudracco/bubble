package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class zzkt extends zzek implements zzks {
    public zzkt() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzks zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return queryLocalInterface instanceof zzks ? (zzks) queryLocalInterface : new zzku(iBinder);
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzkx com_google_android_gms_internal_ads_zzkx = null;
        IInterface zzbj;
        boolean isReady;
        IBinder readStrongBinder;
        Parcelable zzbk;
        String mediationAdapterClassName;
        switch (i) {
            case 1:
                zzbj = zzbj();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzbj);
                break;
            case 2:
                destroy();
                parcel2.writeNoException();
                break;
            case 3:
                isReady = isReady();
                parcel2.writeNoException();
                zzel.zza(parcel2, isReady);
                break;
            case 4:
                isReady = zzb((zzjj) zzel.zza(parcel, zzjj.CREATOR));
                parcel2.writeNoException();
                zzel.zza(parcel2, isReady);
                break;
            case 5:
                pause();
                parcel2.writeNoException();
                break;
            case 6:
                resume();
                parcel2.writeNoException();
                break;
            case 7:
                zzkh com_google_android_gms_internal_ads_zzkj;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzbj = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    com_google_android_gms_internal_ads_zzkj = zzbj instanceof zzkh ? (zzkh) zzbj : new zzkj(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzkj);
                parcel2.writeNoException();
                break;
            case 8:
                zzla com_google_android_gms_internal_ads_zzlc;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzbj = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    com_google_android_gms_internal_ads_zzlc = zzbj instanceof zzla ? (zzla) zzbj : new zzlc(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzlc);
                parcel2.writeNoException();
                break;
            case 9:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 10:
                stopLoading();
                parcel2.writeNoException();
                break;
            case 11:
                zzbm();
                parcel2.writeNoException();
                break;
            case 12:
                zzbk = zzbk();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzbk);
                break;
            case 13:
                zza((zzjn) zzel.zza(parcel, zzjn.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                zza(zzaax.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 15:
                zza(zzabd.zzx(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 18:
                mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 19:
                zza(zzoe.zzf(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 20:
                zzke com_google_android_gms_internal_ads_zzkg;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzbj = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    com_google_android_gms_internal_ads_zzkg = zzbj instanceof zzke ? (zzke) zzbj : new zzkg(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzkg);
                parcel2.writeNoException();
                break;
            case 21:
                zzlg com_google_android_gms_internal_ads_zzli;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzbj = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    com_google_android_gms_internal_ads_zzli = zzbj instanceof zzlg ? (zzlg) zzbj : new zzli(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzli);
                parcel2.writeNoException();
                break;
            case 22:
                setManualImpressionsEnabled(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            case 23:
                isReady = isLoading();
                parcel2.writeNoException();
                zzel.zza(parcel2, isReady);
                break;
            case 24:
                zza(zzahf.zzz(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 25:
                setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            case 26:
                zzbj = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzbj);
                break;
            case 29:
                zza((zzmu) zzel.zza(parcel, zzmu.CREATOR));
                parcel2.writeNoException();
                break;
            case 30:
                zza((zzlu) zzel.zza(parcel, zzlu.CREATOR));
                parcel2.writeNoException();
                break;
            case 31:
                mediationAdapterClassName = getAdUnitId();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 32:
                zzbj = zzbw();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzbj);
                break;
            case 33:
                zzbj = zzbx();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzbj);
                break;
            case 34:
                setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            case 35:
                mediationAdapterClassName = zzck();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 36:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzbj = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    com_google_android_gms_internal_ads_zzkx = zzbj instanceof zzkx ? (zzkx) zzbj : new zzkz(readStrongBinder);
                }
                zza(com_google_android_gms_internal_ads_zzkx);
                parcel2.writeNoException();
                break;
            case 37:
                zzbk = zzba();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzbk);
                break;
            default:
                return false;
        }
        return true;
    }
}

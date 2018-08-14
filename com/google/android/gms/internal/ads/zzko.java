package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public abstract class zzko extends zzek implements zzkn {
    public zzko() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzlg com_google_android_gms_internal_ads_zzlg = null;
        IInterface zzdh;
        IBinder readStrongBinder;
        switch (i) {
            case 1:
                zzdh = zzdh();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzdh);
                break;
            case 2:
                zzkh com_google_android_gms_internal_ads_zzkj;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzdh = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    com_google_android_gms_internal_ads_zzkj = zzdh instanceof zzkh ? (zzkh) zzdh : new zzkj(readStrongBinder);
                }
                zzb(com_google_android_gms_internal_ads_zzkj);
                parcel2.writeNoException();
                break;
            case 3:
                zza(zzqx.zzl(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 4:
                zza(zzra.zzm(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 5:
                zza(parcel.readString(), zzrg.zzo(parcel.readStrongBinder()), zzrd.zzn(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 6:
                zza((zzpl) zzel.zza(parcel, zzpl.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzdh = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    com_google_android_gms_internal_ads_zzlg = zzdh instanceof zzlg ? (zzlg) zzdh : new zzli(readStrongBinder);
                }
                zzb(com_google_android_gms_internal_ads_zzlg);
                parcel2.writeNoException();
                break;
            case 8:
                zza(zzrj.zzp(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR));
                parcel2.writeNoException();
                break;
            case 9:
                zza((PublisherAdViewOptions) zzel.zza(parcel, PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                break;
            case 10:
                zza(zzrm.zzq(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}

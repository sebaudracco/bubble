package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.internal.plus.zzr.zzb;
import com.google.android.gms.internal.plus.zzr.zzb.zza;
import java.util.HashSet;
import java.util.Set;

public final class zzu implements Creator<zzb> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzb.zzb com_google_android_gms_internal_plus_zzr_zzb_zzb = null;
        int i = 0;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Set hashSet = new HashSet();
        zza com_google_android_gms_internal_plus_zzr_zzb_zza = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    zza com_google_android_gms_internal_plus_zzr_zzb_zza2 = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    com_google_android_gms_internal_plus_zzr_zzb_zza = com_google_android_gms_internal_plus_zzr_zzb_zza2;
                    break;
                case 3:
                    zzb.zzb com_google_android_gms_internal_plus_zzr_zzb_zzb2 = (zzb.zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.zzb.CREATOR);
                    hashSet.add(Integer.valueOf(3));
                    com_google_android_gms_internal_plus_zzr_zzb_zzb = com_google_android_gms_internal_plus_zzr_zzb_zzb2;
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzb(hashSet, i2, com_google_android_gms_internal_plus_zzr_zzb_zza, com_google_android_gms_internal_plus_zzr_zzb_zzb, i);
        }
        throw new SafeParcelReader$ParseException("Overread allowed size end=" + validateObjectHeader, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzb[i];
    }
}

package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.internal.plus.zzr.zza;
import com.google.android.gms.internal.plus.zzr.zzb;
import com.google.android.gms.internal.plus.zzr.zzc;
import com.google.android.gms.internal.plus.zzr.zzd;
import com.google.android.gms.internal.plus.zzr.zze;
import com.google.android.gms.internal.plus.zzr.zzf;
import com.google.android.gms.internal.plus.zzr.zzg;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class zzs implements Creator<zzr> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str = null;
        zza com_google_android_gms_internal_plus_zzr_zza = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        zzb com_google_android_gms_internal_plus_zzr_zzb = null;
        String str4 = null;
        String str5 = null;
        int i3 = 0;
        String str6 = null;
        zzc com_google_android_gms_internal_plus_zzr_zzc = null;
        boolean z = false;
        String str7 = null;
        zzd com_google_android_gms_internal_plus_zzr_zzd = null;
        String str8 = null;
        int i4 = 0;
        List list = null;
        List list2 = null;
        int i5 = 0;
        int i6 = 0;
        String str9 = null;
        String str10 = null;
        List list3 = null;
        boolean z2 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    zza com_google_android_gms_internal_plus_zzr_zza2 = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    hashSet.add(Integer.valueOf(3));
                    com_google_android_gms_internal_plus_zzr_zza = com_google_android_gms_internal_plus_zzr_zza2;
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case 7:
                    zzb com_google_android_gms_internal_plus_zzr_zzb2 = (zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.CREATOR);
                    hashSet.add(Integer.valueOf(7));
                    com_google_android_gms_internal_plus_zzr_zzb = com_google_android_gms_internal_plus_zzr_zzb2;
                    break;
                case 8:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case 9:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case 12:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(12));
                    break;
                case 14:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(14));
                    break;
                case 15:
                    zzc com_google_android_gms_internal_plus_zzr_zzc2 = (zzc) SafeParcelReader.createParcelable(parcel, readHeader, zzc.CREATOR);
                    hashSet.add(Integer.valueOf(15));
                    com_google_android_gms_internal_plus_zzr_zzc = com_google_android_gms_internal_plus_zzr_zzc2;
                    break;
                case 16:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    hashSet.add(Integer.valueOf(16));
                    break;
                case 18:
                    str7 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(18));
                    break;
                case 19:
                    zzd com_google_android_gms_internal_plus_zzr_zzd2 = (zzd) SafeParcelReader.createParcelable(parcel, readHeader, zzd.CREATOR);
                    hashSet.add(Integer.valueOf(19));
                    com_google_android_gms_internal_plus_zzr_zzd = com_google_android_gms_internal_plus_zzr_zzd2;
                    break;
                case 20:
                    str8 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(20));
                    break;
                case 21:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(21));
                    break;
                case 22:
                    list = SafeParcelReader.createTypedList(parcel, readHeader, zze.CREATOR);
                    hashSet.add(Integer.valueOf(22));
                    break;
                case 23:
                    list2 = SafeParcelReader.createTypedList(parcel, readHeader, zzf.CREATOR);
                    hashSet.add(Integer.valueOf(23));
                    break;
                case 24:
                    i5 = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(24));
                    break;
                case 25:
                    i6 = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(25));
                    break;
                case 26:
                    str9 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(26));
                    break;
                case 27:
                    str10 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(27));
                    break;
                case 28:
                    list3 = SafeParcelReader.createTypedList(parcel, readHeader, zzg.CREATOR);
                    hashSet.add(Integer.valueOf(28));
                    break;
                case 29:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    hashSet.add(Integer.valueOf(29));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzr(hashSet, i, str, com_google_android_gms_internal_plus_zzr_zza, str2, str3, i2, com_google_android_gms_internal_plus_zzr_zzb, str4, str5, i3, str6, com_google_android_gms_internal_plus_zzr_zzc, z, str7, com_google_android_gms_internal_plus_zzr_zzd, str8, i4, list, list2, i5, i6, str9, str10, list3, z2);
        }
        throw new SafeParcelReader$ParseException("Overread allowed size end=" + validateObjectHeader, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}

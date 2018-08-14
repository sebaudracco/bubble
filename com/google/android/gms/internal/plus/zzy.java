package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.internal.plus.zzr.zzd;
import java.util.HashSet;
import java.util.Set;

public final class zzy implements Creator<zzd> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case 7:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(7));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzd(hashSet, i, str6, str5, str4, str3, str2, str);
        }
        throw new SafeParcelReader$ParseException("Overread allowed size end=" + validateObjectHeader, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzd[i];
    }
}

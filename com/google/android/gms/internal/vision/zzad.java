package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzad implements Creator<zzac> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f = 0.0f;
        boolean z = false;
        String str2 = null;
        zzn com_google_android_gms_internal_vision_zzn = null;
        zzn com_google_android_gms_internal_vision_zzn2 = null;
        zzx[] com_google_android_gms_internal_vision_zzxArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    com_google_android_gms_internal_vision_zzxArr = (zzx[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzx.CREATOR);
                    break;
                case 3:
                    com_google_android_gms_internal_vision_zzn2 = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
                    break;
                case 4:
                    com_google_android_gms_internal_vision_zzn = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
                    break;
                case 5:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 7:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzac(com_google_android_gms_internal_vision_zzxArr, com_google_android_gms_internal_vision_zzn2, com_google_android_gms_internal_vision_zzn, str2, f, str, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzac[i];
    }
}

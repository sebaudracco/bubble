package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzu implements Creator<zzt> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f = 0.0f;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        String str2 = null;
        zzn com_google_android_gms_internal_vision_zzn = null;
        zzn com_google_android_gms_internal_vision_zzn2 = null;
        zzn com_google_android_gms_internal_vision_zzn3 = null;
        zzac[] com_google_android_gms_internal_vision_zzacArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    com_google_android_gms_internal_vision_zzacArr = (zzac[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzac.CREATOR);
                    break;
                case 3:
                    com_google_android_gms_internal_vision_zzn3 = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
                    break;
                case 4:
                    com_google_android_gms_internal_vision_zzn2 = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
                    break;
                case 5:
                    com_google_android_gms_internal_vision_zzn = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 12:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzt(com_google_android_gms_internal_vision_zzacArr, com_google_android_gms_internal_vision_zzn3, com_google_android_gms_internal_vision_zzn2, com_google_android_gms_internal_vision_zzn, str2, f, str, i3, z, i2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzt[i];
    }
}

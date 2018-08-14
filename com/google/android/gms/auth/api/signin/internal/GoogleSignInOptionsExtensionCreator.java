package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class GoogleSignInOptionsExtensionCreator implements Creator<GoogleSignInOptionsExtensionParcelable> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GoogleSignInOptionsExtensionParcelable createFromParcel(Parcel parcel) {
        int i = 0;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoogleSignInOptionsExtensionParcelable(i2, i, bundle);
    }

    public GoogleSignInOptionsExtensionParcelable[] newArray(int i) {
        return new GoogleSignInOptionsExtensionParcelable[i];
    }
}

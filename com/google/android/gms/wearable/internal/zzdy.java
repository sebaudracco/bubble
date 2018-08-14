package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.wearable.ConnectionConfiguration;

@Class(creator = "GetConfigsResponseCreator")
@Reserved({1})
public final class zzdy extends AbstractSafeParcelable {
    public static final Creator<zzdy> CREATOR = new zzdz();
    @Field(id = 2)
    private final int statusCode;
    @Field(id = 3)
    private final ConnectionConfiguration[] zzdw;

    @Constructor
    public zzdy(@Param(id = 2) int i, @Param(id = 3) ConnectionConfiguration[] connectionConfigurationArr) {
        this.statusCode = i;
        this.zzdw = connectionConfigurationArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzdw, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

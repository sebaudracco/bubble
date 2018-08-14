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

@Class(creator = "DeleteDataItemsResponseCreator")
@Reserved({1})
public final class zzdg extends AbstractSafeParcelable {
    public static final Creator<zzdg> CREATOR = new zzdh();
    @Field(id = 2)
    public final int statusCode;
    @Field(id = 3)
    public final int zzdh;

    @Constructor
    public zzdg(@Param(id = 2) int i, @Param(id = 3) int i2) {
        this.statusCode = i;
        this.zzdh = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

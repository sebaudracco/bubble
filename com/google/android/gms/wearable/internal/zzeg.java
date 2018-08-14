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

@Class(creator = "GetLocalNodeResponseCreator")
@Reserved({1})
public final class zzeg extends AbstractSafeParcelable {
    public static final Creator<zzeg> CREATOR = new zzeh();
    @Field(id = 2)
    public final int statusCode;
    @Field(id = 3)
    public final zzfo zzea;

    @Constructor
    public zzeg(@Param(id = 2) int i, @Param(id = 3) zzfo com_google_android_gms_wearable_internal_zzfo) {
        this.statusCode = i;
        this.zzea = com_google_android_gms_wearable_internal_zzfo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzea, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

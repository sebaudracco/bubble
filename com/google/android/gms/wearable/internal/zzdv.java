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

@Class(creator = "GetCloudSyncSettingCreator")
@Reserved({1})
public final class zzdv extends AbstractSafeParcelable {
    public static final Creator<zzdv> CREATOR = new zzdu();
    @Field(id = 3)
    private final boolean enabled;
    @Field(id = 2)
    private final int statusCode;

    @Constructor
    public zzdv(@Param(id = 2) int i, @Param(id = 3) boolean z) {
        this.statusCode = i;
        this.enabled = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeBoolean(parcel, 3, this.enabled);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

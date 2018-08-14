package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@Class(creator = "RtbVersionInfoParcelCreator")
@ParametersAreNonnullByDefault
public final class zzzt extends AbstractSafeParcelable {
    public static final Creator<zzzt> CREATOR = new zzzu();
    @Field(id = 1)
    private final int major;
    @Field(id = 2)
    private final int minor;
    @Field(id = 3)
    private final int zzbvo;

    @Constructor
    zzzt(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) int i3) {
        this.major = i;
        this.minor = i2;
        this.zzbvo = i3;
    }

    public static zzzt zza(zzatk com_google_android_gms_internal_ads_zzatk) {
        return new zzzt(com_google_android_gms_internal_ads_zzatk.zzdgt, com_google_android_gms_internal_ads_zzatk.zzdgu, com_google_android_gms_internal_ads_zzatk.zzdgv);
    }

    public final String toString() {
        int i = this.major;
        int i2 = this.minor;
        return i + "." + i2 + "." + this.zzbvo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.major);
        SafeParcelWriter.writeInt(parcel, 2, this.minor);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbvo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

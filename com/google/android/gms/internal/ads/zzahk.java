package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzadh
@Class(creator = "RewardedVideoAdRequestParcelCreator")
@Reserved({1})
public final class zzahk extends AbstractSafeParcelable {
    public static final Creator<zzahk> CREATOR = new zzahl();
    @Field(id = 3)
    public final String zzacp;
    @Field(id = 2)
    public final zzjj zzccv;

    @Constructor
    public zzahk(@Param(id = 2) zzjj com_google_android_gms_internal_ads_zzjj, @Param(id = 3) String str) {
        this.zzccv = com_google_android_gms_internal_ads_zzjj;
        this.zzacp = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzccv, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzacp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzadh
@Class(creator = "VideoOptionsParcelCreator")
@Reserved({1})
public final class zzmu extends AbstractSafeParcelable {
    public static final Creator<zzmu> CREATOR = new zzmv();
    @Field(id = 2)
    public final boolean zzato;
    @Field(id = 3)
    public final boolean zzatp;
    @Field(id = 4)
    public final boolean zzatq;

    public zzmu(VideoOptions videoOptions) {
        this(videoOptions.getStartMuted(), videoOptions.getCustomControlsRequested(), videoOptions.getClickToExpandRequested());
    }

    @Constructor
    public zzmu(@Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) boolean z3) {
        this.zzato = z;
        this.zzatp = z2;
        this.zzatq = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzato);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzatp);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzatq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

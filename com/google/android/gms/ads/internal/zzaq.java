package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@Class(creator = "InterstitialAdParameterParcelCreator")
@Reserved({1})
public final class zzaq extends AbstractSafeParcelable {
    public static final Creator<zzaq> CREATOR = new zzar();
    @Field(id = 2)
    public final boolean zzze;
    @Field(id = 3)
    public final boolean zzzf;
    @Field(id = 4)
    private final String zzzg;
    @Field(id = 5)
    public final boolean zzzh;
    @Field(id = 6)
    public final float zzzi;
    @Field(id = 7)
    public final int zzzj;
    @Field(id = 8)
    public final boolean zzzk;
    @Field(id = 9)
    public final boolean zzzl;
    @Field(id = 10)
    public final boolean zzzm;

    @Constructor
    zzaq(@Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) String str, @Param(id = 5) boolean z3, @Param(id = 6) float f, @Param(id = 7) int i, @Param(id = 8) boolean z4, @Param(id = 9) boolean z5, @Param(id = 10) boolean z6) {
        this.zzze = z;
        this.zzzf = z2;
        this.zzzg = str;
        this.zzzh = z3;
        this.zzzi = f;
        this.zzzj = i;
        this.zzzk = z4;
        this.zzzl = z5;
        this.zzzm = z6;
    }

    public zzaq(boolean z, boolean z2, boolean z3, float f, int i, boolean z4, boolean z5, boolean z6) {
        this(z, z2, null, z3, f, i, z4, z5, z6);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzze);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzzf);
        SafeParcelWriter.writeString(parcel, 4, this.zzzg, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzzh);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzzi);
        SafeParcelWriter.writeInt(parcel, 7, this.zzzj);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzzk);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzzl);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzzm);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

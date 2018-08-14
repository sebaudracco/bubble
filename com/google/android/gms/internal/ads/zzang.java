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
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
@Class(creator = "VersionInfoParcelCreator")
@Reserved({1})
public final class zzang extends AbstractSafeParcelable {
    public static final Creator<zzang> CREATOR = new zzanh();
    @Field(id = 3)
    public int zzcve;
    @Field(id = 4)
    public int zzcvf;
    @Field(id = 5)
    public boolean zzcvg;
    @Field(id = 6)
    public boolean zzcvh;
    @Field(id = 2)
    public String zzcw;

    public zzang(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzang(int i, int i2, boolean z, boolean z2) {
        this(12451000, i2, true, false, z2);
    }

    private zzang(int i, int i2, boolean z, boolean z2, boolean z3) {
        String str = z ? SchemaSymbols.ATTVAL_FALSE_0 : SchemaSymbols.ATTVAL_TRUE_1;
        this(new StringBuilder(String.valueOf(str).length() + 36).append("afma-sdk-a-v").append(i).append(".").append(i2).append(".").append(str).toString(), i, i2, z, z3);
    }

    @Constructor
    zzang(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) boolean z, @Param(id = 6) boolean z2) {
        this.zzcw = str;
        this.zzcve = i;
        this.zzcvf = i2;
        this.zzcvg = z;
        this.zzcvh = z2;
    }

    public static zzang zzsl() {
        return new zzang(12451009, 12451009, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcw, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcve);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcvf);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcvg);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcvh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

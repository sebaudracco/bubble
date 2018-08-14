package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@zzadh
@Class(creator = "HttpResponseParcelCreator")
public final class zzsi extends AbstractSafeParcelable {
    public static final Creator<zzsi> CREATOR = new zzsj();
    @Field(id = 4)
    public final byte[] data;
    @Field(id = 3)
    public final int statusCode;
    @Field(id = 7)
    public final boolean zzac;
    @Field(id = 8)
    public final long zzad;
    @Field(id = 5)
    public final String[] zzbnh;
    @Field(id = 6)
    public final String[] zzbni;
    @Field(id = 1)
    public final boolean zzbnj;
    @Field(id = 2)
    public final String zzbnk;

    @Constructor
    zzsi(@Param(id = 1) boolean z, @Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) byte[] bArr, @Param(id = 5) String[] strArr, @Param(id = 6) String[] strArr2, @Param(id = 7) boolean z2, @Param(id = 8) long j) {
        this.zzbnj = z;
        this.zzbnk = str;
        this.statusCode = i;
        this.data = bArr;
        this.zzbnh = strArr;
        this.zzbni = strArr2;
        this.zzac = z2;
        this.zzad = j;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzbnj);
        SafeParcelWriter.writeString(parcel, 2, this.zzbnk, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 4, this.data, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzbnh, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzbni, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzac);
        SafeParcelWriter.writeLong(parcel, 8, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

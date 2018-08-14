package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@KeepName
@Class(creator = "PlusCommonExtrasCreator")
public class PlusCommonExtras extends AbstractSafeParcelable {
    public static final Creator<PlusCommonExtras> CREATOR = new zzl();
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int zzw;
    @Field(getter = "getGpsrc", id = 1)
    private String zzx;
    @Field(getter = "getClientCallingPackage", id = 2)
    private String zzy;

    public PlusCommonExtras() {
        this.zzw = 1;
        this.zzx = "";
        this.zzy = "";
    }

    @Constructor
    PlusCommonExtras(@Param(id = 1000) int i, @Param(id = 1) String str, @Param(id = 2) String str2) {
        this.zzw = i;
        this.zzx = str;
        this.zzy = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.zzw == plusCommonExtras.zzw && Objects.equal(this.zzx, plusCommonExtras.zzx) && Objects.equal(this.zzy, plusCommonExtras.zzy);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzw), this.zzx, this.zzy});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zzw)).add("Gpsrc", this.zzx).add("ClientCallingPackage", this.zzy).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzx, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzy, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzw);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

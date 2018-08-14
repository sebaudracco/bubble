package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GassResponseParcelCreator")
public final class zzatv extends AbstractSafeParcelable {
    public static final Creator<zzatv> CREATOR = new zzatw();
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getAfmaSignalsAsBytes", id = 2, type = "byte[]")
    private zzba zzdhe = null;
    private byte[] zzdhf;

    @Constructor
    zzatv(@Param(id = 1) int i, @Param(id = 2) byte[] bArr) {
        this.versionCode = i;
        this.zzdhf = bArr;
        zzwf();
    }

    private final void zzwf() {
        if (this.zzdhe == null && this.zzdhf != null) {
            return;
        }
        if (this.zzdhe != null && this.zzdhf == null) {
            return;
        }
        if (this.zzdhe != null && this.zzdhf != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.zzdhe == null && this.zzdhf == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzdhf != null ? this.zzdhf : zzbfi.zzb(this.zzdhe), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzba zzwe() {
        if ((this.zzdhe != null ? 1 : null) == null) {
            try {
                this.zzdhe = (zzba) zzbfi.zza(new zzba(), this.zzdhf);
                this.zzdhf = null;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        zzwf();
        return this.zzdhe;
    }
}

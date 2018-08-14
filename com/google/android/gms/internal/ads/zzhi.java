package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@Class(creator = "CacheEntryParcelCreator")
@Reserved({1})
public final class zzhi extends AbstractSafeParcelable {
    public static final Creator<zzhi> CREATOR = new zzhj();
    @Nullable
    @GuardedBy("this")
    @Field(getter = "getContentFileDescriptor", id = 2)
    private ParcelFileDescriptor zzaju;

    public zzhi() {
        this(null);
    }

    @Constructor
    public zzhi(@Nullable @Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzaju = parcelFileDescriptor;
    }

    private final synchronized ParcelFileDescriptor zzhk() {
        return this.zzaju;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, zzhk(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final synchronized boolean zzhi() {
        return this.zzaju != null;
    }

    @Nullable
    public final synchronized InputStream zzhj() {
        InputStream inputStream = null;
        synchronized (this) {
            if (this.zzaju != null) {
                inputStream = new AutoCloseInputStream(this.zzaju);
                this.zzaju = null;
            }
        }
        return inputStream;
    }
}

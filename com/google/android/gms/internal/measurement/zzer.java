package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Iterator;

@Class(creator = "EventParamsCreator")
@Reserved({1})
public final class zzer extends AbstractSafeParcelable implements Iterable<String> {
    public static final Creator<zzer> CREATOR = new zzet();
    @Field(getter = "z", id = 2)
    private final Bundle zzafy;

    @Constructor
    zzer(@Param(id = 2) Bundle bundle) {
        this.zzafy = bundle;
    }

    final Object get(String str) {
        return this.zzafy.get(str);
    }

    final Long getLong(String str) {
        return Long.valueOf(this.zzafy.getLong(str));
    }

    final String getString(String str) {
        return this.zzafy.getString(str);
    }

    public final Iterator<String> iterator() {
        return new zzes(this);
    }

    public final int size() {
        return this.zzafy.size();
    }

    public final String toString() {
        return this.zzafy.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzif(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    final Double zzbh(String str) {
        return Double.valueOf(this.zzafy.getDouble(str));
    }

    public final Bundle zzif() {
        return new Bundle(this.zzafy);
    }
}

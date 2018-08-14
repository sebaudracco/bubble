package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;

@Class(creator = "LocationAvailabilityCreator")
@Reserved({1000})
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<LocationAvailability> CREATOR = new zzaa();
    @Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 1)
    @Deprecated
    private int zzar;
    @Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 2)
    @Deprecated
    private int zzas;
    @Field(defaultValueUnchecked = "0", id = 3)
    private long zzat;
    @Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNSUCCESSFUL", id = 4)
    private int zzau;
    @Field(id = 5)
    private zzaj[] zzav;

    @Constructor
    LocationAvailability(@Param(id = 4) int i, @Param(id = 1) int i2, @Param(id = 2) int i3, @Param(id = 3) long j, @Param(id = 5) zzaj[] com_google_android_gms_location_zzajArr) {
        this.zzau = i;
        this.zzar = i2;
        this.zzas = i3;
        this.zzat = j;
        this.zzav = com_google_android_gms_location_zzajArr;
    }

    public static LocationAvailability extractLocationAvailability(Intent intent) {
        return !hasLocationAvailability(intent) ? null : (LocationAvailability) intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public static boolean hasLocationAvailability(Intent intent) {
        return intent == null ? false : intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocationAvailability locationAvailability = (LocationAvailability) obj;
        return this.zzar == locationAvailability.zzar && this.zzas == locationAvailability.zzas && this.zzat == locationAvailability.zzat && this.zzau == locationAvailability.zzau && Arrays.equals(this.zzav, locationAvailability.zzav);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzau), Integer.valueOf(this.zzar), Integer.valueOf(this.zzas), Long.valueOf(this.zzat), this.zzav);
    }

    public final boolean isLocationAvailable() {
        return this.zzau < 1000;
    }

    public final String toString() {
        return "LocationAvailability[isLocationAvailable: " + isLocationAvailable() + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzar);
        SafeParcelWriter.writeInt(parcel, 2, this.zzas);
        SafeParcelWriter.writeLong(parcel, 3, this.zzat);
        SafeParcelWriter.writeInt(parcel, 4, this.zzau);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzav, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

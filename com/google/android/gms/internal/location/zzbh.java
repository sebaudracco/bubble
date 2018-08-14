package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

@Class(creator = "ParcelableGeofenceCreator")
@Reserved({1000})
@VisibleForTesting
public final class zzbh extends AbstractSafeParcelable implements Geofence {
    public static final Creator<zzbh> CREATOR = new zzbi();
    @Field(getter = "getRequestId", id = 1)
    private final String zzad;
    @Field(getter = "getTransitionTypes", id = 7)
    private final int zzae;
    @Field(getter = "getType", id = 3)
    private final short zzag;
    @Field(getter = "getLatitude", id = 4)
    private final double zzah;
    @Field(getter = "getLongitude", id = 5)
    private final double zzai;
    @Field(getter = "getRadius", id = 6)
    private final float zzaj;
    @Field(defaultValue = "0", getter = "getNotificationResponsiveness", id = 8)
    private final int zzak;
    @Field(defaultValue = "-1", getter = "getLoiteringDelay", id = 9)
    private final int zzal;
    @Field(getter = "getExpirationTime", id = 2)
    private final long zzdo;

    @Constructor
    public zzbh(@Param(id = 1) String str, @Param(id = 7) int i, @Param(id = 3) short s, @Param(id = 4) double d, @Param(id = 5) double d2, @Param(id = 6) float f, @Param(id = 2) long j, @Param(id = 8) int i2, @Param(id = 9) int i3) {
        if (str == null || str.length() > 100) {
            String str2 = "requestId is null or too long: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        } else if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        } else {
            int i4 = i & 7;
            if (i4 == 0) {
                throw new IllegalArgumentException("No supported transition specified: " + i);
            }
            this.zzag = s;
            this.zzad = str;
            this.zzah = d;
            this.zzai = d2;
            this.zzaj = f;
            this.zzdo = j;
            this.zzae = i4;
            this.zzak = i2;
            this.zzal = i3;
        }
    }

    public static zzbh zza(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzbh com_google_android_gms_internal_location_zzbh = (zzbh) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return com_google_android_gms_internal_location_zzbh;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzbh)) {
            return false;
        }
        zzbh com_google_android_gms_internal_location_zzbh = (zzbh) obj;
        return this.zzaj != com_google_android_gms_internal_location_zzbh.zzaj ? false : this.zzah != com_google_android_gms_internal_location_zzbh.zzah ? false : this.zzai != com_google_android_gms_internal_location_zzbh.zzai ? false : this.zzag == com_google_android_gms_internal_location_zzbh.zzag;
    }

    public final String getRequestId() {
        return this.zzad;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzah);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzai);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.zzaj)) * 31) + this.zzag) * 31) + this.zzae;
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        String str2 = "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]";
        Object[] objArr = new Object[9];
        switch (this.zzag) {
            case (short) 1:
                str = "CIRCLE";
                break;
            default:
                str = null;
                break;
        }
        objArr[0] = str;
        objArr[1] = this.zzad.replaceAll("\\p{C}", "?");
        objArr[2] = Integer.valueOf(this.zzae);
        objArr[3] = Double.valueOf(this.zzah);
        objArr[4] = Double.valueOf(this.zzai);
        objArr[5] = Float.valueOf(this.zzaj);
        objArr[6] = Integer.valueOf(this.zzak / 1000);
        objArr[7] = Integer.valueOf(this.zzal);
        objArr[8] = Long.valueOf(this.zzdo);
        return String.format(locale, str2, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getRequestId(), false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzdo);
        SafeParcelWriter.writeShort(parcel, 3, this.zzag);
        SafeParcelWriter.writeDouble(parcel, 4, this.zzah);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzai);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzaj);
        SafeParcelWriter.writeInt(parcel, 7, this.zzae);
        SafeParcelWriter.writeInt(parcel, 8, this.zzak);
        SafeParcelWriter.writeInt(parcel, 9, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

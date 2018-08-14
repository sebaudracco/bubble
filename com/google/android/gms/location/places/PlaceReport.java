package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zza();
    @Field(getter = "getTag", id = 3)
    private final String tag;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getPlaceId", id = 2)
    private final String zza;
    @Field(getter = "getSource", id = 4)
    private final String zzb;

    @Constructor
    PlaceReport(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3) {
        this.versionCode = i;
        this.zza = str;
        this.tag = str2;
        this.zzb = str3;
    }

    @VisibleForTesting
    public static PlaceReport create(String str, String str2) {
        boolean z = false;
        String str3 = "unknown";
        Preconditions.checkNotNull(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        boolean z2 = true;
        switch (str3.hashCode()) {
            case -1436706272:
                if (str3.equals("inferredGeofencing")) {
                    z2 = true;
                    break;
                }
                break;
            case -1194968642:
                if (str3.equals("userReported")) {
                    z2 = true;
                    break;
                }
                break;
            case -284840886:
                if (str3.equals("unknown")) {
                    z2 = false;
                    break;
                }
                break;
            case -262743844:
                if (str3.equals("inferredReverseGeocoding")) {
                    z2 = true;
                    break;
                }
                break;
            case 1164924125:
                if (str3.equals("inferredSnappedToRoad")) {
                    z2 = true;
                    break;
                }
                break;
            case 1287171955:
                if (str3.equals("inferredRadioSignals")) {
                    z2 = true;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
                z = true;
                break;
        }
        Preconditions.checkArgument(z, "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return Objects.equal(this.zza, placeReport.zza) && Objects.equal(this.tag, placeReport.tag) && Objects.equal(this.zzb, placeReport.zzb);
    }

    public String getPlaceId() {
        return this.zza;
    }

    public String getTag() {
        return this.tag;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.tag, this.zzb});
    }

    public String toString() {
        Objects$ToStringHelper toStringHelper = Objects.toStringHelper(this);
        toStringHelper.add("placeId", this.zza);
        toStringHelper.add("tag", this.tag);
        if (!"unknown".equals(this.zzb)) {
            toStringHelper.add("source", this.zzb);
        }
        return toStringHelper.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, getPlaceId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTag(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

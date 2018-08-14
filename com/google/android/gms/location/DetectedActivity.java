package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Comparator;

@Class(creator = "DetectedActivityCreator")
@Reserved({1000})
public class DetectedActivity extends AbstractSafeParcelable {
    public static final Creator<DetectedActivity> CREATOR = new zzi();
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    private static final Comparator<DetectedActivity> zzo = new zzh();
    private static final int[] zzp = new int[]{9, 10};
    private static final int[] zzq = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17, 18, 19};
    private static final int[] zzr = new int[]{0, 1, 2, 3, 7, 8, 16, 17};
    @Field(id = 1)
    private int zzi;
    @Field(id = 2)
    private int zzs;

    @Constructor
    public DetectedActivity(@Param(id = 1) int i, @Param(id = 2) int i2) {
        this.zzi = i;
        this.zzs = i2;
    }

    public static void zzb(int i) {
        Object obj = null;
        for (int i2 : zzr) {
            if (i2 == i) {
                obj = 1;
            }
        }
        if (obj == null) {
            Log.w("DetectedActivity", i + " is not a valid DetectedActivity supported by Activity Transition API.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.zzi == detectedActivity.zzi && this.zzs == detectedActivity.zzs;
    }

    public int getConfidence() {
        return this.zzs;
    }

    public int getType() {
        int i = this.zzi;
        return (i > 19 || i < 0) ? 4 : i;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzi), Integer.valueOf(this.zzs)});
    }

    public String toString() {
        String str;
        int type = getType();
        switch (type) {
            case 0:
                str = "IN_VEHICLE";
                break;
            case 1:
                str = "ON_BICYCLE";
                break;
            case 2:
                str = "ON_FOOT";
                break;
            case 3:
                str = "STILL";
                break;
            case 4:
                str = C1404b.f2123a;
                break;
            case 5:
                str = "TILTING";
                break;
            case 7:
                str = "WALKING";
                break;
            case 8:
                str = "RUNNING";
                break;
            case 16:
                str = "IN_ROAD_VEHICLE";
                break;
            case 17:
                str = "IN_RAIL_VEHICLE";
                break;
            case 18:
                str = "IN_TWO_WHEELER_VEHICLE";
                break;
            case 19:
                str = "IN_FOUR_WHEELER_VEHICLE";
                break;
            default:
                str = Integer.toString(type);
                break;
        }
        return new StringBuilder(String.valueOf(str).length() + 48).append("DetectedActivity [type=").append(str).append(", confidence=").append(this.zzs).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzi);
        SafeParcelWriter.writeInt(parcel, 2, this.zzs);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

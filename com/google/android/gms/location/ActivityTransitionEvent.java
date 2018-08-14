package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ActivityTransitionEventCreator")
@Reserved({1000})
public class ActivityTransitionEvent extends AbstractSafeParcelable {
    public static final Creator<ActivityTransitionEvent> CREATOR = new zzd();
    @Field(getter = "getActivityType", id = 1)
    private final int zzi;
    @Field(getter = "getTransitionType", id = 2)
    private final int zzj;
    @Field(getter = "getElapsedRealTimeNanos", id = 3)
    private final long zzk;

    @Constructor
    public ActivityTransitionEvent(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) long j) {
        DetectedActivity.zzb(i);
        ActivityTransition.zza(i2);
        this.zzi = i;
        this.zzj = i2;
        this.zzk = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityTransitionEvent)) {
            return false;
        }
        ActivityTransitionEvent activityTransitionEvent = (ActivityTransitionEvent) obj;
        return this.zzi == activityTransitionEvent.zzi && this.zzj == activityTransitionEvent.zzj && this.zzk == activityTransitionEvent.zzk;
    }

    public int getActivityType() {
        return this.zzi;
    }

    public long getElapsedRealTimeNanos() {
        return this.zzk;
    }

    public int getTransitionType() {
        return this.zzj;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzi), Integer.valueOf(this.zzj), Long.valueOf(this.zzk)});
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ActivityType " + this.zzi);
        stringBuilder.append(" ");
        stringBuilder.append("TransitionType " + this.zzj);
        stringBuilder.append(" ");
        stringBuilder.append("ElapsedRealTimeNanos " + this.zzk);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, getTransitionType());
        SafeParcelWriter.writeLong(parcel, 3, getElapsedRealTimeNanos());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

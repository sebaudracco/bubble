package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzed extends AbstractSafeParcelable {
    public static final Creator<zzed> CREATOR = new zzee();
    @Field(id = 6)
    public boolean active;
    @Field(id = 5)
    public long creationTimestamp;
    @Field(id = 3)
    public String origin;
    @Field(id = 2)
    public String packageName;
    @Field(id = 11)
    public long timeToLive;
    @Field(id = 7)
    public String triggerEventName;
    @Field(id = 9)
    public long triggerTimeout;
    @Field(id = 4)
    public zzjx zzaep;
    @Field(id = 8)
    public zzeu zzaeq;
    @Field(id = 10)
    public zzeu zzaer;
    @Field(id = 12)
    public zzeu zzaes;

    zzed(zzed com_google_android_gms_internal_measurement_zzed) {
        Preconditions.checkNotNull(com_google_android_gms_internal_measurement_zzed);
        this.packageName = com_google_android_gms_internal_measurement_zzed.packageName;
        this.origin = com_google_android_gms_internal_measurement_zzed.origin;
        this.zzaep = com_google_android_gms_internal_measurement_zzed.zzaep;
        this.creationTimestamp = com_google_android_gms_internal_measurement_zzed.creationTimestamp;
        this.active = com_google_android_gms_internal_measurement_zzed.active;
        this.triggerEventName = com_google_android_gms_internal_measurement_zzed.triggerEventName;
        this.zzaeq = com_google_android_gms_internal_measurement_zzed.zzaeq;
        this.triggerTimeout = com_google_android_gms_internal_measurement_zzed.triggerTimeout;
        this.zzaer = com_google_android_gms_internal_measurement_zzed.zzaer;
        this.timeToLive = com_google_android_gms_internal_measurement_zzed.timeToLive;
        this.zzaes = com_google_android_gms_internal_measurement_zzed.zzaes;
    }

    @Constructor
    zzed(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) zzjx com_google_android_gms_internal_measurement_zzjx, @Param(id = 5) long j, @Param(id = 6) boolean z, @Param(id = 7) String str3, @Param(id = 8) zzeu com_google_android_gms_internal_measurement_zzeu, @Param(id = 9) long j2, @Param(id = 10) zzeu com_google_android_gms_internal_measurement_zzeu2, @Param(id = 11) long j3, @Param(id = 12) zzeu com_google_android_gms_internal_measurement_zzeu3) {
        this.packageName = str;
        this.origin = str2;
        this.zzaep = com_google_android_gms_internal_measurement_zzjx;
        this.creationTimestamp = j;
        this.active = z;
        this.triggerEventName = str3;
        this.zzaeq = com_google_android_gms_internal_measurement_zzeu;
        this.triggerTimeout = j2;
        this.zzaer = com_google_android_gms_internal_measurement_zzeu2;
        this.timeToLive = j3;
        this.zzaes = com_google_android_gms_internal_measurement_zzeu3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.origin, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzaep, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.creationTimestamp);
        SafeParcelWriter.writeBoolean(parcel, 6, this.active);
        SafeParcelWriter.writeString(parcel, 7, this.triggerEventName, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzaeq, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.triggerTimeout);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzaer, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.timeToLive);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzaes, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

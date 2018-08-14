package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

@Class(creator = "ActivityTransitionResultCreator")
@Reserved({1000})
public class ActivityTransitionResult extends AbstractSafeParcelable {
    public static final Creator<ActivityTransitionResult> CREATOR = new zzg();
    @Field(getter = "getTransitionEvents", id = 1)
    private final List<ActivityTransitionEvent> zzn;

    @Constructor
    public ActivityTransitionResult(@Param(id = 1) List<ActivityTransitionEvent> list) {
        Preconditions.checkNotNull(list, "transitionEvents list can't be null.");
        if (!list.isEmpty()) {
            for (int i = 1; i < list.size(); i++) {
                Preconditions.checkArgument(((ActivityTransitionEvent) list.get(i)).getElapsedRealTimeNanos() >= ((ActivityTransitionEvent) list.get(i + -1)).getElapsedRealTimeNanos());
            }
        }
        this.zzn = Collections.unmodifiableList(list);
    }

    @Nullable
    public static ActivityTransitionResult extractResult(Intent intent) {
        return !hasResult(intent) ? null : (ActivityTransitionResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", CREATOR);
    }

    public static boolean hasResult(@Nullable Intent intent) {
        return intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
    }

    public boolean equals(Object obj) {
        return this == obj ? true : (obj == null || getClass() != obj.getClass()) ? false : this.zzn.equals(((ActivityTransitionResult) obj).zzn);
    }

    public List<ActivityTransitionEvent> getTransitionEvents() {
        return this.zzn;
    }

    public int hashCode() {
        return this.zzn.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getTransitionEvents(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

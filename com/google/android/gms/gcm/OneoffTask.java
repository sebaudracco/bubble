package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.gcm.Task.Builder;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR = new zzi();
    private final long zzaj;
    private final long zzak;

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzaj = parcel.readLong();
        this.zzak = parcel.readLong();
    }

    private OneoffTask(Builder builder) {
        super((Builder) builder);
        this.zzaj = Builder.zzd(builder);
        this.zzak = Builder.zze(builder);
    }

    public long getWindowEnd() {
        return this.zzak;
    }

    public long getWindowStart() {
        return this.zzaj;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzaj);
        bundle.putLong("window_end", this.zzak);
    }

    public String toString() {
        String obj = super.toString();
        long windowStart = getWindowStart();
        return new StringBuilder(String.valueOf(obj).length() + 64).append(obj).append(" windowStart=").append(windowStart).append(" windowEnd=").append(getWindowEnd()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.zzaj);
        parcel.writeLong(this.zzak);
    }
}

package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.zzj;
import java.util.Collections;
import java.util.List;

@Class(creator = "DeviceOrientationRequestInternalCreator")
public final class zzm extends AbstractSafeParcelable {
    public static final Creator<zzm> CREATOR = new zzn();
    @VisibleForTesting
    static final List<ClientIdentity> zzcd = Collections.emptyList();
    static final zzj zzce = new zzj();
    @Nullable
    @Field(defaultValueUnchecked = "null", id = 3)
    private String tag;
    @Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id = 1)
    private zzj zzcf;
    @Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id = 2)
    private List<ClientIdentity> zzm;

    @Constructor
    zzm(@Param(id = 1) zzj com_google_android_gms_location_zzj, @Param(id = 2) List<ClientIdentity> list, @Param(id = 3) String str) {
        this.zzcf = com_google_android_gms_location_zzj;
        this.zzm = list;
        this.tag = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm com_google_android_gms_internal_location_zzm = (zzm) obj;
        return Objects.equal(this.zzcf, com_google_android_gms_internal_location_zzm.zzcf) && Objects.equal(this.zzm, com_google_android_gms_internal_location_zzm.zzm) && Objects.equal(this.tag, com_google_android_gms_internal_location_zzm.tag);
    }

    public final int hashCode() {
        return this.zzcf.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzcf, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

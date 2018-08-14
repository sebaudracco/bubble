package com.google.android.gms.internal.ads;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@zzadh
public final class zzms extends zzjn {
    public zzms(zzjn com_google_android_gms_internal_ads_zzjn) {
        super(com_google_android_gms_internal_ads_zzjn.zzarb, com_google_android_gms_internal_ads_zzjn.height, com_google_android_gms_internal_ads_zzjn.heightPixels, com_google_android_gms_internal_ads_zzjn.zzarc, com_google_android_gms_internal_ads_zzjn.width, com_google_android_gms_internal_ads_zzjn.widthPixels, com_google_android_gms_internal_ads_zzjn.zzard, com_google_android_gms_internal_ads_zzjn.zzare, com_google_android_gms_internal_ads_zzjn.zzarf, com_google_android_gms_internal_ads_zzjn.zzarg);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@zzadh
@Class(creator = "NativeAdOptionsParcelCreator")
public final class zzpl extends AbstractSafeParcelable {
    public static final Creator<zzpl> CREATOR = new zzpm();
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 2)
    public final boolean zzbjn;
    @Field(id = 3)
    public final int zzbjo;
    @Field(id = 4)
    public final boolean zzbjp;
    @Field(id = 5)
    public final int zzbjq;
    @Nullable
    @Field(id = 6)
    public final zzmu zzbjr;

    @Constructor
    public zzpl(@Param(id = 1) int i, @Param(id = 2) boolean z, @Param(id = 3) int i2, @Param(id = 4) boolean z2, @Param(id = 5) int i3, @Param(id = 6) zzmu com_google_android_gms_internal_ads_zzmu) {
        this.versionCode = i;
        this.zzbjn = z;
        this.zzbjo = i2;
        this.zzbjp = z2;
        this.zzbjq = i3;
        this.zzbjr = com_google_android_gms_internal_ads_zzmu;
    }

    public zzpl(NativeAdOptions nativeAdOptions) {
        this(3, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzmu(nativeAdOptions.getVideoOptions()) : null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbjn);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbjo);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzbjp);
        SafeParcelWriter.writeInt(parcel, 5, this.zzbjq);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzbjr, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

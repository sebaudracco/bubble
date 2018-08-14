package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.List;

@zzadh
@Class(creator = "AdRequestParcelCreator")
public final class zzjj extends AbstractSafeParcelable {
    public static final Creator<zzjj> CREATOR = new zzjl();
    @Field(id = 3)
    public final Bundle extras;
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 2)
    public final long zzapw;
    @Field(id = 4)
    public final int zzapx;
    @Field(id = 5)
    public final List<String> zzapy;
    @Field(id = 6)
    public final boolean zzapz;
    @Field(id = 7)
    public final int zzaqa;
    @Field(id = 8)
    public final boolean zzaqb;
    @Field(id = 9)
    public final String zzaqc;
    @Field(id = 10)
    public final zzmq zzaqd;
    @Field(id = 11)
    public final Location zzaqe;
    @Field(id = 12)
    public final String zzaqf;
    @Field(id = 13)
    public final Bundle zzaqg;
    @Field(id = 14)
    public final Bundle zzaqh;
    @Field(id = 15)
    public final List<String> zzaqi;
    @Field(id = 16)
    public final String zzaqj;
    @Field(id = 17)
    public final String zzaqk;
    @Field(id = 18)
    public final boolean zzaql;

    @Constructor
    public zzjj(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 3) Bundle bundle, @Param(id = 4) int i2, @Param(id = 5) List<String> list, @Param(id = 6) boolean z, @Param(id = 7) int i3, @Param(id = 8) boolean z2, @Param(id = 9) String str, @Param(id = 10) zzmq com_google_android_gms_internal_ads_zzmq, @Param(id = 11) Location location, @Param(id = 12) String str2, @Param(id = 13) Bundle bundle2, @Param(id = 14) Bundle bundle3, @Param(id = 15) List<String> list2, @Param(id = 16) String str3, @Param(id = 17) String str4, @Param(id = 18) boolean z3) {
        this.versionCode = i;
        this.zzapw = j;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.extras = bundle;
        this.zzapx = i2;
        this.zzapy = list;
        this.zzapz = z;
        this.zzaqa = i3;
        this.zzaqb = z2;
        this.zzaqc = str;
        this.zzaqd = com_google_android_gms_internal_ads_zzmq;
        this.zzaqe = location;
        this.zzaqf = str2;
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        this.zzaqg = bundle2;
        this.zzaqh = bundle3;
        this.zzaqi = list2;
        this.zzaqj = str3;
        this.zzaqk = str4;
        this.zzaql = z3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjj)) {
            return false;
        }
        zzjj com_google_android_gms_internal_ads_zzjj = (zzjj) obj;
        return this.versionCode == com_google_android_gms_internal_ads_zzjj.versionCode && this.zzapw == com_google_android_gms_internal_ads_zzjj.zzapw && Objects.equal(this.extras, com_google_android_gms_internal_ads_zzjj.extras) && this.zzapx == com_google_android_gms_internal_ads_zzjj.zzapx && Objects.equal(this.zzapy, com_google_android_gms_internal_ads_zzjj.zzapy) && this.zzapz == com_google_android_gms_internal_ads_zzjj.zzapz && this.zzaqa == com_google_android_gms_internal_ads_zzjj.zzaqa && this.zzaqb == com_google_android_gms_internal_ads_zzjj.zzaqb && Objects.equal(this.zzaqc, com_google_android_gms_internal_ads_zzjj.zzaqc) && Objects.equal(this.zzaqd, com_google_android_gms_internal_ads_zzjj.zzaqd) && Objects.equal(this.zzaqe, com_google_android_gms_internal_ads_zzjj.zzaqe) && Objects.equal(this.zzaqf, com_google_android_gms_internal_ads_zzjj.zzaqf) && Objects.equal(this.zzaqg, com_google_android_gms_internal_ads_zzjj.zzaqg) && Objects.equal(this.zzaqh, com_google_android_gms_internal_ads_zzjj.zzaqh) && Objects.equal(this.zzaqi, com_google_android_gms_internal_ads_zzjj.zzaqi) && Objects.equal(this.zzaqj, com_google_android_gms_internal_ads_zzjj.zzaqj) && Objects.equal(this.zzaqk, com_google_android_gms_internal_ads_zzjj.zzaqk) && this.zzaql == com_google_android_gms_internal_ads_zzjj.zzaql;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzapw), this.extras, Integer.valueOf(this.zzapx), this.zzapy, Boolean.valueOf(this.zzapz), Integer.valueOf(this.zzaqa), Boolean.valueOf(this.zzaqb), this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf, this.zzaqg, this.zzaqh, this.zzaqi, this.zzaqj, this.zzaqk, Boolean.valueOf(this.zzaql));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.zzapw);
        SafeParcelWriter.writeBundle(parcel, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzapx);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzapy, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzapz);
        SafeParcelWriter.writeInt(parcel, 7, this.zzaqa);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzaqb);
        SafeParcelWriter.writeString(parcel, 9, this.zzaqc, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzaqd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzaqe, i, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzaqf, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzaqg, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzaqh, false);
        SafeParcelWriter.writeStringList(parcel, 15, this.zzaqi, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzaqj, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzaqk, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzaql);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzjj zzhv() {
        Bundle bundle = this.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle == null) {
            bundle = this.extras;
            this.zzaqg.putBundle("com.google.ads.mediation.admob.AdMobAdapter", this.extras);
        }
        return new zzjj(this.versionCode, this.zzapw, bundle, this.zzapx, this.zzapy, this.zzapz, this.zzaqa, this.zzaqb, this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf, this.zzaqg, this.zzaqh, this.zzaqi, this.zzaqj, this.zzaqk, this.zzaql);
    }
}

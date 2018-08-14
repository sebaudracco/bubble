package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@Class(creator = "NonagonRequestParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaey extends AbstractSafeParcelable {
    public static final Creator<zzaey> CREATOR = new zzaez();
    @Field(id = 3)
    private final ApplicationInfo applicationInfo;
    @Field(id = 4)
    private final String packageName;
    @Field(id = 6)
    @Nullable
    private final PackageInfo zzccw;
    @Field(id = 5)
    private final List<String> zzcdj;
    @Field(id = 7)
    private final String zzcds;
    @Field(id = 1)
    private final Bundle zzcfy;
    @Field(id = 2)
    private final zzang zzcfz;
    @Field(id = 8)
    private final boolean zzcga;
    @Field(id = 9)
    private final String zzcgb;

    @Constructor
    public zzaey(@Param(id = 1) Bundle bundle, @Param(id = 2) zzang com_google_android_gms_internal_ads_zzang, @Param(id = 3) ApplicationInfo applicationInfo, @Param(id = 4) String str, @Param(id = 5) List<String> list, @Param(id = 6) @Nullable PackageInfo packageInfo, @Param(id = 7) String str2, @Param(id = 8) boolean z, @Param(id = 9) String str3) {
        this.zzcfy = bundle;
        this.zzcfz = com_google_android_gms_internal_ads_zzang;
        this.packageName = str;
        this.applicationInfo = applicationInfo;
        this.zzcdj = list;
        this.zzccw = packageInfo;
        this.zzcds = str2;
        this.zzcga = z;
        this.zzcgb = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzcfy, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcfz, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.applicationInfo, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.packageName, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzcdj, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzccw, i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcds, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzcga);
        SafeParcelWriter.writeString(parcel, 9, this.zzcgb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

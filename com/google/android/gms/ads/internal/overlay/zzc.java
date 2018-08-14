package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@Class(creator = "AdLauncherIntentInfoCreator")
@Reserved({1})
public final class zzc extends AbstractSafeParcelable {
    public static final Creator<zzc> CREATOR = new zzb();
    @Field(id = 9)
    public final Intent intent;
    @Field(id = 4)
    public final String mimeType;
    @Field(id = 5)
    public final String packageName;
    @Field(id = 3)
    public final String url;
    @Field(id = 2)
    private final String zzbxi;
    @Field(id = 6)
    public final String zzbxj;
    @Field(id = 7)
    public final String zzbxk;
    @Field(id = 8)
    private final String zzbxl;

    public zzc(Intent intent) {
        this(null, null, null, null, null, null, null, intent);
    }

    public zzc(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, str7, null);
    }

    @Constructor
    public zzc(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7, @Param(id = 9) Intent intent) {
        this.zzbxi = str;
        this.url = str2;
        this.mimeType = str3;
        this.packageName = str4;
        this.zzbxj = str5;
        this.zzbxk = str6;
        this.zzbxl = str7;
        this.intent = intent;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbxi, false);
        SafeParcelWriter.writeString(parcel, 3, this.url, false);
        SafeParcelWriter.writeString(parcel, 4, this.mimeType, false);
        SafeParcelWriter.writeString(parcel, 5, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbxj, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbxk, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzbxl, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.intent, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.Map;
import java.util.Map.Entry;

@zzadh
@Class(creator = "HttpRequestParcelCreator")
public final class zzsg extends AbstractSafeParcelable {
    public static final Creator<zzsg> CREATOR = new zzsh();
    @Field(id = 1)
    private final String url;
    @Field(id = 2)
    private final String[] zzbnh;
    @Field(id = 3)
    private final String[] zzbni;

    @Constructor
    zzsg(@Param(id = 1) String str, @Param(id = 2) String[] strArr, @Param(id = 3) String[] strArr2) {
        this.url = str;
        this.zzbnh = strArr;
        this.zzbni = strArr2;
    }

    public static zzsg zzh(zzr com_google_android_gms_internal_ads_zzr) throws zza {
        Map headers = com_google_android_gms_internal_ads_zzr.getHeaders();
        int size = headers.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Entry entry : headers.entrySet()) {
            strArr[i] = (String) entry.getKey();
            strArr2[i] = (String) entry.getValue();
            i++;
        }
        return new zzsg(com_google_android_gms_internal_ads_zzr.getUrl(), strArr, strArr2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzbnh, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzbni, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

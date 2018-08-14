package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzadh
@Class(creator = "SearchAdRequestParcelCreator")
@Reserved({1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
public final class zzmq extends AbstractSafeParcelable {
    public static final Creator<zzmq> CREATOR = new zzmr();
    @Field(id = 15)
    public final String zzatn;

    public zzmq(SearchAdRequest searchAdRequest) {
        this.zzatn = searchAdRequest.getQuery();
    }

    @Constructor
    zzmq(@Param(id = 15) String str) {
        this.zzatn = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 15, this.zzatn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

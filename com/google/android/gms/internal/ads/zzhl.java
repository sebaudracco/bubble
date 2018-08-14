package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
@Class(creator = "CacheOfferingCreator")
@Reserved({1})
public final class zzhl extends AbstractSafeParcelable {
    public static final Creator<zzhl> CREATOR = new zzhm();
    @Nullable
    @Field(id = 2)
    public final String url;
    @Field(id = 3)
    private final long zzajv;
    @Field(id = 4)
    private final String zzajw;
    @Field(id = 5)
    private final String zzajx;
    @Field(id = 6)
    private final String zzajy;
    @Field(id = 7)
    private final Bundle zzajz;
    @Field(id = 8)
    private final boolean zzaka;
    @Field(id = 9)
    private long zzakb;

    @Constructor
    zzhl(@Nullable @Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 6) String str4, @Param(id = 7) Bundle bundle, @Param(id = 8) boolean z, @Param(id = 9) long j2) {
        this.url = str;
        this.zzajv = j;
        if (str2 == null) {
            str2 = "";
        }
        this.zzajw = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.zzajx = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.zzajy = str4;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzajz = bundle;
        this.zzaka = z;
        this.zzakb = j2;
    }

    @Nullable
    public static zzhl zzaa(String str) {
        return zzd(Uri.parse(str));
    }

    @Nullable
    public static zzhl zzd(Uri uri) {
        Throwable e;
        long j = 0;
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                zzane.zzdk("Expected 2 path parts for namespace and id, found :" + pathSegments.size());
                return null;
            }
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = SchemaSymbols.ATTVAL_TRUE_1.equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            if (queryParameter2 != null) {
                j = Long.parseLong(queryParameter2);
            }
            Bundle bundle = new Bundle();
            for (String queryParameter22 : zzbv.zzem().zzh(uri)) {
                if (queryParameter22.startsWith("tag.")) {
                    bundle.putString(queryParameter22.substring(4), uri.getQueryParameter(queryParameter22));
                }
            }
            return new zzhl(queryParameter, j, host, str, str2, bundle, equals, 0);
        } catch (NullPointerException e2) {
            e = e2;
            zzane.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        } catch (NumberFormatException e3) {
            e = e3;
            zzane.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzajv);
        SafeParcelWriter.writeString(parcel, 4, this.zzajw, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzajx, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzajy, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzajz, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzaka);
        SafeParcelWriter.writeLong(parcel, 9, this.zzakb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

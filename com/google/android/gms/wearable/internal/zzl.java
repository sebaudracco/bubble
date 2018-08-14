package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
@Class(creator = "AncsNotificationParcelableCreator")
@Reserved({1})
public final class zzl extends AbstractSafeParcelable {
    public static final Creator<zzl> CREATOR = new zzm();
    @Field(getter = "getId", id = 2)
    private int id;
    @Field(getter = "getPackageName", id = 13)
    @Nullable
    private final String packageName;
    @Field(getter = "getAppId", id = 3)
    private final String zzbf;
    @Field(getter = "getDateTime", id = 4)
    @Nullable
    private final String zzbg;
    @Field(getter = "getNotificationText", id = 5)
    private final String zzbh;
    @Field(getter = "getTitle", id = 6)
    private final String zzbi;
    @Field(getter = "getSubtitle", id = 7)
    private final String zzbj;
    @Field(getter = "getDisplayName", id = 8)
    @Nullable
    private final String zzbk;
    @Field(getter = "getEventId", id = 9)
    private final byte zzbl;
    @Field(getter = "getEventFlags", id = 10)
    private final byte zzbm;
    @Field(getter = "getCategoryId", id = 11)
    private final byte zzbn;
    @Field(getter = "getCategoryCount", id = 12)
    private final byte zzbo;

    @Constructor
    public zzl(@Param(id = 2) int i, @Param(id = 3) String str, @Param(id = 4) @Nullable String str2, @Param(id = 5) String str3, @Param(id = 6) String str4, @Param(id = 7) String str5, @Param(id = 8) @Nullable String str6, @Param(id = 9) byte b, @Param(id = 10) byte b2, @Param(id = 11) byte b3, @Param(id = 12) byte b4, @Param(id = 13) @Nullable String str7) {
        this.id = i;
        this.zzbf = str;
        this.zzbg = str2;
        this.zzbh = str3;
        this.zzbi = str4;
        this.zzbj = str5;
        this.zzbk = str6;
        this.zzbl = b;
        this.zzbm = b2;
        this.zzbn = b3;
        this.zzbo = b4;
        this.packageName = str7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzl com_google_android_gms_wearable_internal_zzl = (zzl) obj;
        return this.id != com_google_android_gms_wearable_internal_zzl.id ? false : this.zzbl != com_google_android_gms_wearable_internal_zzl.zzbl ? false : this.zzbm != com_google_android_gms_wearable_internal_zzl.zzbm ? false : this.zzbn != com_google_android_gms_wearable_internal_zzl.zzbn ? false : this.zzbo != com_google_android_gms_wearable_internal_zzl.zzbo ? false : !this.zzbf.equals(com_google_android_gms_wearable_internal_zzl.zzbf) ? false : (this.zzbg == null ? com_google_android_gms_wearable_internal_zzl.zzbg != null : !this.zzbg.equals(com_google_android_gms_wearable_internal_zzl.zzbg)) ? false : !this.zzbh.equals(com_google_android_gms_wearable_internal_zzl.zzbh) ? false : !this.zzbi.equals(com_google_android_gms_wearable_internal_zzl.zzbi) ? false : !this.zzbj.equals(com_google_android_gms_wearable_internal_zzl.zzbj) ? false : (this.zzbk == null ? com_google_android_gms_wearable_internal_zzl.zzbk != null : !this.zzbk.equals(com_google_android_gms_wearable_internal_zzl.zzbk)) ? false : this.packageName != null ? this.packageName.equals(com_google_android_gms_wearable_internal_zzl.packageName) : com_google_android_gms_wearable_internal_zzl.packageName == null;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((((this.zzbk != null ? this.zzbk.hashCode() : 0) + (((((((((this.zzbg != null ? this.zzbg.hashCode() : 0) + ((((this.id + 31) * 31) + this.zzbf.hashCode()) * 31)) * 31) + this.zzbh.hashCode()) * 31) + this.zzbi.hashCode()) * 31) + this.zzbj.hashCode()) * 31)) * 31) + this.zzbl) * 31) + this.zzbm) * 31) + this.zzbn) * 31) + this.zzbo) * 31;
        if (this.packageName != null) {
            i = this.packageName.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        int i = this.id;
        String str = this.zzbf;
        String str2 = this.zzbg;
        String str3 = this.zzbh;
        String str4 = this.zzbi;
        String str5 = this.zzbj;
        String str6 = this.zzbk;
        byte b = this.zzbl;
        byte b2 = this.zzbm;
        byte b3 = this.zzbn;
        byte b4 = this.zzbo;
        String str7 = this.packageName;
        return new StringBuilder(((((((String.valueOf(str).length() + 211) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(str5).length()) + String.valueOf(str6).length()) + String.valueOf(str7).length()).append("AncsNotificationParcelable{, id=").append(i).append(", appId='").append(str).append('\'').append(", dateTime='").append(str2).append('\'').append(", notificationText='").append(str3).append('\'').append(", title='").append(str4).append('\'').append(", subtitle='").append(str5).append('\'').append(", displayName='").append(str6).append('\'').append(", eventId=").append(b).append(", eventFlags=").append(b2).append(", categoryId=").append(b3).append(", categoryCount=").append(b4).append(", packageName='").append(str7).append('\'').append('}').toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.id);
        SafeParcelWriter.writeString(parcel, 3, this.zzbf, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbg, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbh, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbi, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbj, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzbk == null ? this.zzbf : this.zzbk, false);
        SafeParcelWriter.writeByte(parcel, 9, this.zzbl);
        SafeParcelWriter.writeByte(parcel, 10, this.zzbm);
        SafeParcelWriter.writeByte(parcel, 11, this.zzbn);
        SafeParcelWriter.writeByte(parcel, 12, this.zzbo);
        SafeParcelWriter.writeString(parcel, 13, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

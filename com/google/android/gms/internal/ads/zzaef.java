package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@Class(creator = "AdRequestInfoParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaef extends AbstractSafeParcelable {
    public static final Creator<zzaef> CREATOR = new zzaeh();
    @Field(id = 6)
    public final ApplicationInfo applicationInfo;
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 28)
    public final String zzaco;
    @Field(id = 5)
    public final String zzacp;
    @Field(id = 11)
    public final zzang zzacr;
    @Field(id = 4)
    public final zzjn zzacv;
    @Field(id = 29)
    public final zzpl zzadj;
    @Nullable
    @Field(id = 46)
    public final zzlu zzadl;
    @Field(id = 53)
    public final List<Integer> zzadn;
    @Field(id = 14)
    public final List<String> zzads;
    @Field(id = 20)
    public final float zzagu;
    @Field(id = 42)
    public final boolean zzbss;
    @Nullable
    @Field(id = 2)
    public final Bundle zzccu;
    @Field(id = 3)
    public final zzjj zzccv;
    @Nullable
    @Field(id = 7)
    public final PackageInfo zzccw;
    @Field(id = 8)
    public final String zzccx;
    @Field(id = 9)
    public final String zzccy;
    @Field(id = 10)
    public final String zzccz;
    @Field(id = 12)
    public final Bundle zzcda;
    @Field(id = 13)
    public final int zzcdb;
    @Field(id = 15)
    public final Bundle zzcdc;
    @Field(id = 16)
    public final boolean zzcdd;
    @Field(id = 18)
    public final int zzcde;
    @Field(id = 19)
    public final int zzcdf;
    @Field(id = 21)
    public final String zzcdg;
    @Field(id = 25)
    public final long zzcdh;
    @Field(id = 26)
    public final String zzcdi;
    @Nullable
    @Field(id = 27)
    public final List<String> zzcdj;
    @Field(id = 30)
    public final List<String> zzcdk;
    @Field(id = 31)
    public final long zzcdl;
    @Field(id = 33)
    public final String zzcdm;
    @Field(id = 34)
    public final float zzcdn;
    @Field(id = 35)
    public final int zzcdo;
    @Field(id = 36)
    public final int zzcdp;
    @Field(id = 37)
    public final boolean zzcdq;
    @Field(id = 38)
    public final boolean zzcdr;
    @Field(id = 39)
    public final String zzcds;
    @Field(id = 40)
    public final boolean zzcdt;
    @Field(id = 41)
    public final String zzcdu;
    @Field(id = 43)
    public final int zzcdv;
    @Field(id = 44)
    public final Bundle zzcdw;
    @Field(id = 45)
    public final String zzcdx;
    @Field(id = 47)
    public final boolean zzcdy;
    @Field(id = 48)
    public final Bundle zzcdz;
    @Nullable
    @Field(id = 49)
    public final String zzcea;
    @Nullable
    @Field(id = 50)
    public final String zzceb;
    @Nullable
    @Field(id = 51)
    public final String zzcec;
    @Field(id = 52)
    public final boolean zzced;
    @Field(id = 54)
    public final String zzcee;
    @Field(id = 55)
    public final List<String> zzcef;
    @Field(id = 56)
    public final int zzceg;
    @Field(id = 57)
    public final boolean zzceh;
    @Field(id = 58)
    public final boolean zzcei;
    @Field(id = 59)
    public final boolean zzcej;
    @Field(id = 60)
    public final ArrayList<String> zzcek;

    @Constructor
    zzaef(@Param(id = 1) int i, @Param(id = 2) Bundle bundle, @Param(id = 3) zzjj com_google_android_gms_internal_ads_zzjj, @Param(id = 4) zzjn com_google_android_gms_internal_ads_zzjn, @Param(id = 5) String str, @Param(id = 6) ApplicationInfo applicationInfo, @Param(id = 7) PackageInfo packageInfo, @Param(id = 8) String str2, @Param(id = 9) String str3, @Param(id = 10) String str4, @Param(id = 11) zzang com_google_android_gms_internal_ads_zzang, @Param(id = 12) Bundle bundle2, @Param(id = 13) int i2, @Param(id = 14) List<String> list, @Param(id = 15) Bundle bundle3, @Param(id = 16) boolean z, @Param(id = 18) int i3, @Param(id = 19) int i4, @Param(id = 20) float f, @Param(id = 21) String str5, @Param(id = 25) long j, @Param(id = 26) String str6, @Param(id = 27) List<String> list2, @Param(id = 28) String str7, @Param(id = 29) zzpl com_google_android_gms_internal_ads_zzpl, @Param(id = 30) List<String> list3, @Param(id = 31) long j2, @Param(id = 33) String str8, @Param(id = 34) float f2, @Param(id = 40) boolean z2, @Param(id = 35) int i5, @Param(id = 36) int i6, @Param(id = 37) boolean z3, @Param(id = 38) boolean z4, @Param(id = 39) String str9, @Param(id = 41) String str10, @Param(id = 42) boolean z5, @Param(id = 43) int i7, @Param(id = 44) Bundle bundle4, @Param(id = 45) String str11, @Param(id = 46) zzlu com_google_android_gms_internal_ads_zzlu, @Param(id = 47) boolean z6, @Param(id = 48) Bundle bundle5, @Param(id = 49) String str12, @Param(id = 50) String str13, @Param(id = 51) String str14, @Param(id = 52) boolean z7, @Param(id = 53) List<Integer> list4, @Param(id = 54) String str15, @Param(id = 55) List<String> list5, @Param(id = 56) int i8, @Param(id = 57) boolean z8, @Param(id = 58) boolean z9, @Param(id = 59) boolean z10, @Param(id = 60) ArrayList<String> arrayList) {
        this.versionCode = i;
        this.zzccu = bundle;
        this.zzccv = com_google_android_gms_internal_ads_zzjj;
        this.zzacv = com_google_android_gms_internal_ads_zzjn;
        this.zzacp = str;
        this.applicationInfo = applicationInfo;
        this.zzccw = packageInfo;
        this.zzccx = str2;
        this.zzccy = str3;
        this.zzccz = str4;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzcda = bundle2;
        this.zzcdb = i2;
        this.zzads = list;
        this.zzcdk = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzcdc = bundle3;
        this.zzcdd = z;
        this.zzcde = i3;
        this.zzcdf = i4;
        this.zzagu = f;
        this.zzcdg = str5;
        this.zzcdh = j;
        this.zzcdi = str6;
        this.zzcdj = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzaco = str7;
        this.zzadj = com_google_android_gms_internal_ads_zzpl;
        this.zzcdl = j2;
        this.zzcdm = str8;
        this.zzcdn = f2;
        this.zzcdt = z2;
        this.zzcdo = i5;
        this.zzcdp = i6;
        this.zzcdq = z3;
        this.zzcdr = z4;
        this.zzcds = str9;
        this.zzcdu = str10;
        this.zzbss = z5;
        this.zzcdv = i7;
        this.zzcdw = bundle4;
        this.zzcdx = str11;
        this.zzadl = com_google_android_gms_internal_ads_zzlu;
        this.zzcdy = z6;
        this.zzcdz = bundle5;
        this.zzcea = str12;
        this.zzceb = str13;
        this.zzcec = str14;
        this.zzced = z7;
        this.zzadn = list4;
        this.zzcee = str15;
        this.zzcef = list5;
        this.zzceg = i8;
        this.zzceh = z8;
        this.zzcei = z9;
        this.zzcej = z10;
        this.zzcek = arrayList;
    }

    private zzaef(@Nullable Bundle bundle, zzjj com_google_android_gms_internal_ads_zzjj, zzjn com_google_android_gms_internal_ads_zzjn, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, String str4, zzang com_google_android_gms_internal_ads_zzang, Bundle bundle2, int i, List<String> list, List<String> list2, Bundle bundle3, boolean z, int i2, int i3, float f, String str5, long j, String str6, @Nullable List<String> list3, String str7, zzpl com_google_android_gms_internal_ads_zzpl, long j2, String str8, float f2, boolean z2, int i4, int i5, boolean z3, boolean z4, String str9, String str10, boolean z5, int i6, Bundle bundle4, String str11, @Nullable zzlu com_google_android_gms_internal_ads_zzlu, boolean z6, Bundle bundle5, String str12, String str13, String str14, boolean z7, List<Integer> list4, String str15, List<String> list5, int i7, boolean z8, boolean z9, boolean z10, ArrayList<String> arrayList) {
        this(24, bundle, com_google_android_gms_internal_ads_zzjj, com_google_android_gms_internal_ads_zzjn, str, applicationInfo, packageInfo, str2, str3, str4, com_google_android_gms_internal_ads_zzang, bundle2, i, list, bundle3, z, i2, i3, f, str5, j, str6, list3, str7, com_google_android_gms_internal_ads_zzpl, list2, j2, str8, f2, z2, i4, i5, z3, z4, str9, str10, z5, i6, bundle4, str11, com_google_android_gms_internal_ads_zzlu, z6, bundle5, str12, str13, str14, z7, list4, str15, list5, i7, z8, z9, z10, arrayList);
    }

    public zzaef(zzaeg com_google_android_gms_internal_ads_zzaeg, long j, String str, String str2, String str3) {
        this(com_google_android_gms_internal_ads_zzaeg.zzccu, com_google_android_gms_internal_ads_zzaeg.zzccv, com_google_android_gms_internal_ads_zzaeg.zzacv, com_google_android_gms_internal_ads_zzaeg.zzacp, com_google_android_gms_internal_ads_zzaeg.applicationInfo, com_google_android_gms_internal_ads_zzaeg.zzccw, (String) zzano.zza(com_google_android_gms_internal_ads_zzaeg.zzcem, (Object) ""), com_google_android_gms_internal_ads_zzaeg.zzccy, com_google_android_gms_internal_ads_zzaeg.zzccz, com_google_android_gms_internal_ads_zzaeg.zzacr, com_google_android_gms_internal_ads_zzaeg.zzcda, com_google_android_gms_internal_ads_zzaeg.zzcdb, com_google_android_gms_internal_ads_zzaeg.zzads, com_google_android_gms_internal_ads_zzaeg.zzcdk, com_google_android_gms_internal_ads_zzaeg.zzcdc, com_google_android_gms_internal_ads_zzaeg.zzcdd, com_google_android_gms_internal_ads_zzaeg.zzcde, com_google_android_gms_internal_ads_zzaeg.zzcdf, com_google_android_gms_internal_ads_zzaeg.zzagu, com_google_android_gms_internal_ads_zzaeg.zzcdg, com_google_android_gms_internal_ads_zzaeg.zzcdh, com_google_android_gms_internal_ads_zzaeg.zzcdi, com_google_android_gms_internal_ads_zzaeg.zzcdj, com_google_android_gms_internal_ads_zzaeg.zzaco, com_google_android_gms_internal_ads_zzaeg.zzadj, j, com_google_android_gms_internal_ads_zzaeg.zzcdm, com_google_android_gms_internal_ads_zzaeg.zzcdn, com_google_android_gms_internal_ads_zzaeg.zzcdt, com_google_android_gms_internal_ads_zzaeg.zzcdo, com_google_android_gms_internal_ads_zzaeg.zzcdp, com_google_android_gms_internal_ads_zzaeg.zzcdq, com_google_android_gms_internal_ads_zzaeg.zzcdr, (String) zzano.zza(com_google_android_gms_internal_ads_zzaeg.zzcel, (Object) "", 1, TimeUnit.SECONDS), com_google_android_gms_internal_ads_zzaeg.zzcdu, com_google_android_gms_internal_ads_zzaeg.zzbss, com_google_android_gms_internal_ads_zzaeg.zzcdv, com_google_android_gms_internal_ads_zzaeg.zzcdw, com_google_android_gms_internal_ads_zzaeg.zzcdx, com_google_android_gms_internal_ads_zzaeg.zzadl, com_google_android_gms_internal_ads_zzaeg.zzcdy, com_google_android_gms_internal_ads_zzaeg.zzcdz, str, str2, str3, com_google_android_gms_internal_ads_zzaeg.zzced, com_google_android_gms_internal_ads_zzaeg.zzadn, com_google_android_gms_internal_ads_zzaeg.zzcee, com_google_android_gms_internal_ads_zzaeg.zzcef, com_google_android_gms_internal_ads_zzaeg.zzceg, com_google_android_gms_internal_ads_zzaeg.zzceh, com_google_android_gms_internal_ads_zzaeg.zzcei, com_google_android_gms_internal_ads_zzaeg.zzcej, com_google_android_gms_internal_ads_zzaeg.zzcek);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzccu, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzccv, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzacv, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzacp, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.applicationInfo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzccw, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzccx, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzccy, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzccz, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzacr, i, false);
        SafeParcelWriter.writeBundle(parcel, 12, this.zzcda, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzcdb);
        SafeParcelWriter.writeStringList(parcel, 14, this.zzads, false);
        SafeParcelWriter.writeBundle(parcel, 15, this.zzcdc, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzcdd);
        SafeParcelWriter.writeInt(parcel, 18, this.zzcde);
        SafeParcelWriter.writeInt(parcel, 19, this.zzcdf);
        SafeParcelWriter.writeFloat(parcel, 20, this.zzagu);
        SafeParcelWriter.writeString(parcel, 21, this.zzcdg, false);
        SafeParcelWriter.writeLong(parcel, 25, this.zzcdh);
        SafeParcelWriter.writeString(parcel, 26, this.zzcdi, false);
        SafeParcelWriter.writeStringList(parcel, 27, this.zzcdj, false);
        SafeParcelWriter.writeString(parcel, 28, this.zzaco, false);
        SafeParcelWriter.writeParcelable(parcel, 29, this.zzadj, i, false);
        SafeParcelWriter.writeStringList(parcel, 30, this.zzcdk, false);
        SafeParcelWriter.writeLong(parcel, 31, this.zzcdl);
        SafeParcelWriter.writeString(parcel, 33, this.zzcdm, false);
        SafeParcelWriter.writeFloat(parcel, 34, this.zzcdn);
        SafeParcelWriter.writeInt(parcel, 35, this.zzcdo);
        SafeParcelWriter.writeInt(parcel, 36, this.zzcdp);
        SafeParcelWriter.writeBoolean(parcel, 37, this.zzcdq);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzcdr);
        SafeParcelWriter.writeString(parcel, 39, this.zzcds, false);
        SafeParcelWriter.writeBoolean(parcel, 40, this.zzcdt);
        SafeParcelWriter.writeString(parcel, 41, this.zzcdu, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzbss);
        SafeParcelWriter.writeInt(parcel, 43, this.zzcdv);
        SafeParcelWriter.writeBundle(parcel, 44, this.zzcdw, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzcdx, false);
        SafeParcelWriter.writeParcelable(parcel, 46, this.zzadl, i, false);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzcdy);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzcdz, false);
        SafeParcelWriter.writeString(parcel, 49, this.zzcea, false);
        SafeParcelWriter.writeString(parcel, 50, this.zzceb, false);
        SafeParcelWriter.writeString(parcel, 51, this.zzcec, false);
        SafeParcelWriter.writeBoolean(parcel, 52, this.zzced);
        SafeParcelWriter.writeIntegerList(parcel, 53, this.zzadn, false);
        SafeParcelWriter.writeString(parcel, 54, this.zzcee, false);
        SafeParcelWriter.writeStringList(parcel, 55, this.zzcef, false);
        SafeParcelWriter.writeInt(parcel, 56, this.zzceg);
        SafeParcelWriter.writeBoolean(parcel, 57, this.zzceh);
        SafeParcelWriter.writeBoolean(parcel, 58, this.zzcei);
        SafeParcelWriter.writeBoolean(parcel, 59, this.zzcej);
        SafeParcelWriter.writeStringList(parcel, 60, this.zzcek, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

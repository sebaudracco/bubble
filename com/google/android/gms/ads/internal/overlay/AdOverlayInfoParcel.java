package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.zzaq;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzjd;

@zzadh
@Class(creator = "AdOverlayInfoCreator")
@Reserved({1})
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<AdOverlayInfoParcel> CREATOR = new zzm();
    @Field(id = 11)
    public final int orientation;
    @Field(id = 13)
    public final String url;
    @Field(id = 14)
    public final zzang zzacr;
    @Field(id = 2)
    public final zzc zzbyl;
    @Field(getter = "getAdClickListenerAsBinder", id = 3, type = "android.os.IBinder")
    public final zzjd zzbym;
    @Field(getter = "getAdOverlayListenerAsBinder", id = 4, type = "android.os.IBinder")
    public final zzn zzbyn;
    @Field(getter = "getAdWebViewAsBinder", id = 5, type = "android.os.IBinder")
    public final zzaqw zzbyo;
    @Field(getter = "getAppEventGmsgListenerAsBinder", id = 6, type = "android.os.IBinder")
    public final zzd zzbyp;
    @Field(id = 7)
    public final String zzbyq;
    @Field(id = 8)
    public final boolean zzbyr;
    @Field(id = 9)
    public final String zzbys;
    @Field(getter = "getLeaveApplicationListenerAsBinder", id = 10, type = "android.os.IBinder")
    public final zzt zzbyt;
    @Field(id = 12)
    public final int zzbyu;
    @Field(id = 16)
    public final String zzbyv;
    @Field(id = 17)
    public final zzaq zzbyw;
    @Field(getter = "getAdMetadataGmsgListenerAsBinder", id = 18, type = "android.os.IBinder")
    public final zzb zzbyx;

    @Constructor
    AdOverlayInfoParcel(@Param(id = 2) zzc com_google_android_gms_ads_internal_overlay_zzc, @Param(id = 3) IBinder iBinder, @Param(id = 4) IBinder iBinder2, @Param(id = 5) IBinder iBinder3, @Param(id = 6) IBinder iBinder4, @Param(id = 7) String str, @Param(id = 8) boolean z, @Param(id = 9) String str2, @Param(id = 10) IBinder iBinder5, @Param(id = 11) int i, @Param(id = 12) int i2, @Param(id = 13) String str3, @Param(id = 14) zzang com_google_android_gms_internal_ads_zzang, @Param(id = 16) String str4, @Param(id = 17) zzaq com_google_android_gms_ads_internal_zzaq, @Param(id = 18) IBinder iBinder6) {
        this.zzbyl = com_google_android_gms_ads_internal_overlay_zzc;
        this.zzbym = (zzjd) ObjectWrapper.unwrap(Stub.asInterface(iBinder));
        this.zzbyn = (zzn) ObjectWrapper.unwrap(Stub.asInterface(iBinder2));
        this.zzbyo = (zzaqw) ObjectWrapper.unwrap(Stub.asInterface(iBinder3));
        this.zzbyx = (zzb) ObjectWrapper.unwrap(Stub.asInterface(iBinder6));
        this.zzbyp = (zzd) ObjectWrapper.unwrap(Stub.asInterface(iBinder4));
        this.zzbyq = str;
        this.zzbyr = z;
        this.zzbys = str2;
        this.zzbyt = (zzt) ObjectWrapper.unwrap(Stub.asInterface(iBinder5));
        this.orientation = i;
        this.zzbyu = i2;
        this.url = str3;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzbyv = str4;
        this.zzbyw = com_google_android_gms_ads_internal_zzaq;
    }

    public AdOverlayInfoParcel(zzc com_google_android_gms_ads_internal_overlay_zzc, zzjd com_google_android_gms_internal_ads_zzjd, zzn com_google_android_gms_ads_internal_overlay_zzn, zzt com_google_android_gms_ads_internal_overlay_zzt, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzbyl = com_google_android_gms_ads_internal_overlay_zzc;
        this.zzbym = com_google_android_gms_internal_ads_zzjd;
        this.zzbyn = com_google_android_gms_ads_internal_overlay_zzn;
        this.zzbyo = null;
        this.zzbyx = null;
        this.zzbyp = null;
        this.zzbyq = null;
        this.zzbyr = false;
        this.zzbys = null;
        this.zzbyt = com_google_android_gms_ads_internal_overlay_zzt;
        this.orientation = -1;
        this.zzbyu = 4;
        this.url = null;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzbyv = null;
        this.zzbyw = null;
    }

    public AdOverlayInfoParcel(zzjd com_google_android_gms_internal_ads_zzjd, zzn com_google_android_gms_ads_internal_overlay_zzn, zzb com_google_android_gms_ads_internal_gmsg_zzb, zzd com_google_android_gms_ads_internal_gmsg_zzd, zzt com_google_android_gms_ads_internal_overlay_zzt, zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z, int i, String str, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_internal_ads_zzjd;
        this.zzbyn = com_google_android_gms_ads_internal_overlay_zzn;
        this.zzbyo = com_google_android_gms_internal_ads_zzaqw;
        this.zzbyx = com_google_android_gms_ads_internal_gmsg_zzb;
        this.zzbyp = com_google_android_gms_ads_internal_gmsg_zzd;
        this.zzbyq = null;
        this.zzbyr = z;
        this.zzbys = null;
        this.zzbyt = com_google_android_gms_ads_internal_overlay_zzt;
        this.orientation = i;
        this.zzbyu = 3;
        this.url = str;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzbyv = null;
        this.zzbyw = null;
    }

    public AdOverlayInfoParcel(zzjd com_google_android_gms_internal_ads_zzjd, zzn com_google_android_gms_ads_internal_overlay_zzn, zzb com_google_android_gms_ads_internal_gmsg_zzb, zzd com_google_android_gms_ads_internal_gmsg_zzd, zzt com_google_android_gms_ads_internal_overlay_zzt, zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z, int i, String str, String str2, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_internal_ads_zzjd;
        this.zzbyn = com_google_android_gms_ads_internal_overlay_zzn;
        this.zzbyo = com_google_android_gms_internal_ads_zzaqw;
        this.zzbyx = com_google_android_gms_ads_internal_gmsg_zzb;
        this.zzbyp = com_google_android_gms_ads_internal_gmsg_zzd;
        this.zzbyq = str2;
        this.zzbyr = z;
        this.zzbys = str;
        this.zzbyt = com_google_android_gms_ads_internal_overlay_zzt;
        this.orientation = i;
        this.zzbyu = 3;
        this.url = null;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzbyv = null;
        this.zzbyw = null;
    }

    public AdOverlayInfoParcel(zzjd com_google_android_gms_internal_ads_zzjd, zzn com_google_android_gms_ads_internal_overlay_zzn, zzt com_google_android_gms_ads_internal_overlay_zzt, zzaqw com_google_android_gms_internal_ads_zzaqw, int i, zzang com_google_android_gms_internal_ads_zzang, String str, zzaq com_google_android_gms_ads_internal_zzaq) {
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_internal_ads_zzjd;
        this.zzbyn = com_google_android_gms_ads_internal_overlay_zzn;
        this.zzbyo = com_google_android_gms_internal_ads_zzaqw;
        this.zzbyx = null;
        this.zzbyp = null;
        this.zzbyq = null;
        this.zzbyr = false;
        this.zzbys = null;
        this.zzbyt = com_google_android_gms_ads_internal_overlay_zzt;
        this.orientation = i;
        this.zzbyu = 1;
        this.url = null;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzbyv = str;
        this.zzbyw = com_google_android_gms_ads_internal_zzaq;
    }

    public AdOverlayInfoParcel(zzjd com_google_android_gms_internal_ads_zzjd, zzn com_google_android_gms_ads_internal_overlay_zzn, zzt com_google_android_gms_ads_internal_overlay_zzt, zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z, int i, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_internal_ads_zzjd;
        this.zzbyn = com_google_android_gms_ads_internal_overlay_zzn;
        this.zzbyo = com_google_android_gms_internal_ads_zzaqw;
        this.zzbyx = null;
        this.zzbyp = null;
        this.zzbyq = null;
        this.zzbyr = z;
        this.zzbys = null;
        this.zzbyt = com_google_android_gms_ads_internal_overlay_zzt;
        this.orientation = i;
        this.zzbyu = 2;
        this.url = null;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzbyv = null;
        this.zzbyw = null;
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzc(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzbyl, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, ObjectWrapper.wrap(this.zzbym).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(this.zzbyn).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 5, ObjectWrapper.wrap(this.zzbyo).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 6, ObjectWrapper.wrap(this.zzbyp).asBinder(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbyq, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbyr);
        SafeParcelWriter.writeString(parcel, 9, this.zzbys, false);
        SafeParcelWriter.writeIBinder(parcel, 10, ObjectWrapper.wrap(this.zzbyt).asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 11, this.orientation);
        SafeParcelWriter.writeInt(parcel, 12, this.zzbyu);
        SafeParcelWriter.writeString(parcel, 13, this.url, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzacr, i, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzbyv, false);
        SafeParcelWriter.writeParcelable(parcel, 17, this.zzbyw, i, false);
        SafeParcelWriter.writeIBinder(parcel, 18, ObjectWrapper.wrap(this.zzbyx).asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

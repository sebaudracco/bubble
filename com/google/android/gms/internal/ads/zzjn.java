package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import cz.msebera.android.httpclient.HttpStatus;

@zzadh
@Class(creator = "AdSizeParcelCreator")
@Reserved({1})
public class zzjn extends AbstractSafeParcelable {
    public static final Creator<zzjn> CREATOR = new zzjo();
    @Field(id = 3)
    public final int height;
    @Field(id = 4)
    public final int heightPixels;
    @Field(id = 6)
    public final int width;
    @Field(id = 7)
    public final int widthPixels;
    @Field(id = 2)
    public final String zzarb;
    @Field(id = 5)
    public final boolean zzarc;
    @Field(id = 8)
    public final zzjn[] zzard;
    @Field(id = 9)
    public final boolean zzare;
    @Field(id = 10)
    public final boolean zzarf;
    @Field(id = 11)
    public boolean zzarg;

    public zzjn() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zzjn(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    public zzjn(Context context, AdSize[] adSizeArr) {
        int i;
        int i2;
        AdSize adSize = adSizeArr[0];
        this.zzarc = false;
        this.zzarf = adSize.isFluid();
        if (this.zzarf) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        } else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            double d;
            zzkb.zzif();
            if (zzamu.zzbi(context)) {
                zzkb.zzif();
                if (zzamu.zzbj(context)) {
                    i = displayMetrics.widthPixels;
                    zzkb.zzif();
                    this.widthPixels = i - zzamu.zzbk(context);
                    d = (double) (((float) this.widthPixels) / displayMetrics.density);
                    i = (int) d;
                    if (d - ((double) ((int) d)) >= 0.01d) {
                        i++;
                    }
                    i2 = i;
                }
            }
            this.widthPixels = displayMetrics.widthPixels;
            d = (double) (((float) this.widthPixels) / displayMetrics.density);
            i = (int) d;
            if (d - ((double) ((int) d)) >= 0.01d) {
                i++;
            }
            i2 = i;
        } else {
            i = this.width;
            zzkb.zzif();
            this.widthPixels = zzamu.zza(displayMetrics, this.width);
            i2 = i;
        }
        i = z2 ? zzd(displayMetrics) : this.height;
        zzkb.zzif();
        this.heightPixels = zzamu.zza(displayMetrics, i);
        if (z || z2) {
            this.zzarb = i2 + "x" + i + "_as";
        } else if (this.zzarf) {
            this.zzarb = "320x50_mb";
        } else {
            this.zzarb = adSize.toString();
        }
        if (adSizeArr.length > 1) {
            this.zzard = new zzjn[adSizeArr.length];
            for (int i3 = 0; i3 < adSizeArr.length; i3++) {
                this.zzard[i3] = new zzjn(context, adSizeArr[i3]);
            }
        } else {
            this.zzard = null;
        }
        this.zzare = false;
        this.zzarg = false;
    }

    public zzjn(zzjn com_google_android_gms_internal_ads_zzjn, zzjn[] com_google_android_gms_internal_ads_zzjnArr) {
        this(com_google_android_gms_internal_ads_zzjn.zzarb, com_google_android_gms_internal_ads_zzjn.height, com_google_android_gms_internal_ads_zzjn.heightPixels, com_google_android_gms_internal_ads_zzjn.zzarc, com_google_android_gms_internal_ads_zzjn.width, com_google_android_gms_internal_ads_zzjn.widthPixels, com_google_android_gms_internal_ads_zzjnArr, com_google_android_gms_internal_ads_zzjn.zzare, com_google_android_gms_internal_ads_zzjn.zzarf, com_google_android_gms_internal_ads_zzjn.zzarg);
    }

    @Constructor
    zzjn(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) boolean z, @Param(id = 6) int i3, @Param(id = 7) int i4, @Param(id = 8) zzjn[] com_google_android_gms_internal_ads_zzjnArr, @Param(id = 9) boolean z2, @Param(id = 10) boolean z3, @Param(id = 11) boolean z4) {
        this.zzarb = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzarc = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzard = com_google_android_gms_internal_ads_zzjnArr;
        this.zzare = z2;
        this.zzarf = z3;
        this.zzarg = z4;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return i <= HttpStatus.SC_BAD_REQUEST ? 32 : i <= 720 ? 50 : 90;
    }

    public static zzjn zzf(Context context) {
        return new zzjn("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }

    public static zzjn zzhx() {
        return new zzjn("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzarc);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzard, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzarg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final AdSize zzhy() {
        return zzb.zza(this.width, this.height, this.zzarb);
    }
}

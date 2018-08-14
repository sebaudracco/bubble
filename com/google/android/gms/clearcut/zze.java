package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

@Class(creator = "LogEventParcelableCreator")
@Reserved({1})
public final class zze extends AbstractSafeParcelable {
    public static final Creator<zze> CREATOR = new zzf();
    public final zzha zzaa;
    @Field(id = 2)
    public zzr zzag;
    @Field(id = 3)
    public byte[] zzah;
    @Field(id = 4)
    private int[] zzai;
    @Field(id = 5)
    private String[] zzaj;
    @Field(id = 6)
    private int[] zzak;
    @Field(id = 7)
    private byte[][] zzal;
    @Field(id = 9)
    private ExperimentTokens[] zzam;
    public final ClearcutLogger$zzb zzan;
    public final ClearcutLogger$zzb zzt;
    @Field(defaultValue = "true", id = 8)
    private boolean zzz;

    public zze(zzr com_google_android_gms_internal_clearcut_zzr, zzha com_google_android_gms_internal_clearcut_zzha, ClearcutLogger$zzb clearcutLogger$zzb, ClearcutLogger$zzb clearcutLogger$zzb2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, ExperimentTokens[] experimentTokensArr, boolean z) {
        this.zzag = com_google_android_gms_internal_clearcut_zzr;
        this.zzaa = com_google_android_gms_internal_clearcut_zzha;
        this.zzt = clearcutLogger$zzb;
        this.zzan = null;
        this.zzai = iArr;
        this.zzaj = null;
        this.zzak = iArr2;
        this.zzal = null;
        this.zzam = null;
        this.zzz = z;
    }

    @Constructor
    zze(@Param(id = 2) zzr com_google_android_gms_internal_clearcut_zzr, @Param(id = 3) byte[] bArr, @Param(id = 4) int[] iArr, @Param(id = 5) String[] strArr, @Param(id = 6) int[] iArr2, @Param(id = 7) byte[][] bArr2, @Param(id = 8) boolean z, @Param(id = 9) ExperimentTokens[] experimentTokensArr) {
        this.zzag = com_google_android_gms_internal_clearcut_zzr;
        this.zzah = bArr;
        this.zzai = iArr;
        this.zzaj = strArr;
        this.zzaa = null;
        this.zzt = null;
        this.zzan = null;
        this.zzak = iArr2;
        this.zzal = bArr2;
        this.zzam = experimentTokensArr;
        this.zzz = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze com_google_android_gms_clearcut_zze = (zze) obj;
        return Objects.equal(this.zzag, com_google_android_gms_clearcut_zze.zzag) && Arrays.equals(this.zzah, com_google_android_gms_clearcut_zze.zzah) && Arrays.equals(this.zzai, com_google_android_gms_clearcut_zze.zzai) && Arrays.equals(this.zzaj, com_google_android_gms_clearcut_zze.zzaj) && Objects.equal(this.zzaa, com_google_android_gms_clearcut_zze.zzaa) && Objects.equal(this.zzt, com_google_android_gms_clearcut_zze.zzt) && Objects.equal(this.zzan, com_google_android_gms_clearcut_zze.zzan) && Arrays.equals(this.zzak, com_google_android_gms_clearcut_zze.zzak) && Arrays.deepEquals(this.zzal, com_google_android_gms_clearcut_zze.zzal) && Arrays.equals(this.zzam, com_google_android_gms_clearcut_zze.zzam) && this.zzz == com_google_android_gms_clearcut_zze.zzz;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzag, this.zzah, this.zzai, this.zzaj, this.zzaa, this.zzt, this.zzan, this.zzak, this.zzal, this.zzam, Boolean.valueOf(this.zzz)});
    }

    public final String toString() {
        return "LogEventParcelable[" + this.zzag + ", LogEventBytes: " + (this.zzah == null ? null : new String(this.zzah)) + ", TestCodes: " + Arrays.toString(this.zzai) + ", MendelPackages: " + Arrays.toString(this.zzaj) + ", LogEvent: " + this.zzaa + ", ExtensionProducer: " + this.zzt + ", VeProducer: " + this.zzan + ", ExperimentIDs: " + Arrays.toString(this.zzak) + ", ExperimentTokens: " + Arrays.toString(this.zzal) + ", ExperimentTokensParcelables: " + Arrays.toString(this.zzam) + ", AddPhenotypeExperimentTokens: " + this.zzz + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzag, i, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzah, false);
        SafeParcelWriter.writeIntArray(parcel, 4, this.zzai, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzaj, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.zzak, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, this.zzal, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzz);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzam, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

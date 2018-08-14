package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adcolony.sdk.AdColonyUserMetadata;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.AgeRange;
import com.google.android.gms.plus.model.people.Person.Cover;
import com.google.android.gms.plus.model.people.Person.Cover.CoverInfo;
import com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.Person.Name;
import com.google.android.gms.plus.model.people.Person.Organizations;
import com.google.android.gms.plus.model.people.Person.PlacesLived;
import com.google.android.gms.plus.model.people.Person.Urls;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.mopub.common.Constants;
import com.silvermob.sdk.Const.BannerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@Class(creator = "PersonEntityCreator")
@VisibleForTesting
public final class zzr extends FastSafeParcelableJsonResponse implements Person {
    public static final Creator<zzr> CREATOR = new zzs();
    private static final HashMap<String, Field<?, ?>> zzao;
    @Indicator
    private final Set<Integer> zzap;
    @SafeParcelable.Field(id = 2)
    private String zzaq;
    @SafeParcelable.Field(id = 3)
    private zza zzar;
    @SafeParcelable.Field(id = 4)
    private String zzas;
    @SafeParcelable.Field(id = 5)
    private String zzat;
    @SafeParcelable.Field(id = 6)
    private int zzau;
    @SafeParcelable.Field(id = 7)
    private zzb zzav;
    @SafeParcelable.Field(id = 8)
    private String zzaw;
    @SafeParcelable.Field(id = 9)
    private String zzax;
    @SafeParcelable.Field(id = 12)
    private int zzay;
    @SafeParcelable.Field(id = 14)
    private String zzaz;
    @SafeParcelable.Field(id = 15)
    private zzc zzba;
    @SafeParcelable.Field(id = 16)
    private boolean zzbb;
    @SafeParcelable.Field(id = 18)
    private String zzbc;
    @SafeParcelable.Field(id = 19)
    private zzd zzbd;
    @SafeParcelable.Field(id = 20)
    private String zzbe;
    @SafeParcelable.Field(id = 21)
    private int zzbf;
    @SafeParcelable.Field(id = 22)
    private List<zze> zzbg;
    @SafeParcelable.Field(id = 23)
    private List<zzf> zzbh;
    @SafeParcelable.Field(id = 24)
    private int zzbi;
    @SafeParcelable.Field(id = 25)
    private int zzbj;
    @SafeParcelable.Field(id = 26)
    private String zzbk;
    @SafeParcelable.Field(id = 28)
    private List<zzg> zzbl;
    @SafeParcelable.Field(id = 29)
    private boolean zzbm;
    @SafeParcelable.Field(id = 27)
    private String zzk;
    @VersionField(id = 1)
    private final int zzw;

    @Class(creator = "PersonEntity_AgeRangeEntityCreator")
    @VisibleForTesting
    public static final class zza extends FastSafeParcelableJsonResponse implements AgeRange {
        public static final Creator<zza> CREATOR = new zzt();
        private static final HashMap<String, Field<?, ?>> zzao;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 2)
        private int zzbn;
        @SafeParcelable.Field(id = 3)
        private int zzbo;
        @VersionField(id = 1)
        private final int zzw;

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put("max", Field.forInteger("max", 2));
            zzao.put("min", Field.forInteger("min", 3));
        }

        public zza() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @Constructor
        zza(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) int i3) {
            this.zzap = set;
            this.zzw = i;
            this.zzbn = i2;
            this.zzbo = i3;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zza com_google_android_gms_internal_plus_zzr_zza = (zza) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zza.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zza.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zza.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return Integer.valueOf(this.zzbn);
                case 3:
                    return Integer.valueOf(this.zzbo);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final int getMax() {
            return this.zzbn;
        }

        public final int getMin() {
            return this.zzbo;
        }

        public final boolean hasMax() {
            return this.zzap.contains(Integer.valueOf(2));
        }

        public final boolean hasMin() {
            return this.zzap.contains(Integer.valueOf(3));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzbn = i;
                    break;
                case 3:
                    this.zzbo = i;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(2))) {
                SafeParcelWriter.writeInt(parcel, 2, this.zzbn);
            }
            if (set.contains(Integer.valueOf(3))) {
                SafeParcelWriter.writeInt(parcel, 3, this.zzbo);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonEntity_CoverEntityCreator")
    @VisibleForTesting
    public static final class zzb extends FastSafeParcelableJsonResponse implements Cover {
        public static final Creator<zzb> CREATOR = new zzu();
        private static final HashMap<String, Field<?, ?>> zzao;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 2)
        private zza zzbp;
        @SafeParcelable.Field(id = 3)
        private zzb zzbq;
        @SafeParcelable.Field(id = 4)
        private int zzbr;
        @VersionField(id = 1)
        private final int zzw;

        @Class(creator = "PersonEntity_CoverEntity_CoverInfoEntityCreator")
        @VisibleForTesting
        public static final class zza extends FastSafeParcelableJsonResponse implements CoverInfo {
            public static final Creator<zza> CREATOR = new zzv();
            private static final HashMap<String, Field<?, ?>> zzao;
            @Indicator
            private final Set<Integer> zzap;
            @SafeParcelable.Field(id = 2)
            private int zzbs;
            @SafeParcelable.Field(id = 3)
            private int zzbt;
            @VersionField(id = 1)
            private final int zzw;

            static {
                HashMap hashMap = new HashMap();
                zzao = hashMap;
                hashMap.put("leftImageOffset", Field.forInteger("leftImageOffset", 2));
                zzao.put("topImageOffset", Field.forInteger("topImageOffset", 3));
            }

            public zza() {
                this.zzw = 1;
                this.zzap = new HashSet();
            }

            @Constructor
            zza(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) int i3) {
                this.zzap = set;
                this.zzw = i;
                this.zzbs = i2;
                this.zzbt = i3;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof zza)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                zza com_google_android_gms_internal_plus_zzr_zzb_zza = (zza) obj;
                for (Field field : zzao.values()) {
                    if (isFieldSet(field)) {
                        if (!com_google_android_gms_internal_plus_zzr_zzb_zza.isFieldSet(field)) {
                            return false;
                        }
                        if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzb_zza.getFieldValue(field))) {
                            return false;
                        }
                    } else if (com_google_android_gms_internal_plus_zzr_zzb_zza.isFieldSet(field)) {
                        return false;
                    }
                }
                return true;
            }

            public final /* bridge */ /* synthetic */ Object freeze() {
                if (this != null) {
                    return this;
                }
                throw null;
            }

            public final /* synthetic */ Map getFieldMappings() {
                return zzao;
            }

            protected final Object getFieldValue(Field field) {
                switch (field.getSafeParcelableFieldId()) {
                    case 2:
                        return Integer.valueOf(this.zzbs);
                    case 3:
                        return Integer.valueOf(this.zzbt);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
                }
            }

            public final int getLeftImageOffset() {
                return this.zzbs;
            }

            public final int getTopImageOffset() {
                return this.zzbt;
            }

            public final boolean hasLeftImageOffset() {
                return this.zzap.contains(Integer.valueOf(2));
            }

            public final boolean hasTopImageOffset() {
                return this.zzap.contains(Integer.valueOf(3));
            }

            public final int hashCode() {
                int i = 0;
                for (Field field : zzao.values()) {
                    int hashCode;
                    if (isFieldSet(field)) {
                        hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            public final boolean isDataValid() {
                return true;
            }

            protected final boolean isFieldSet(Field field) {
                return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                switch (safeParcelableFieldId) {
                    case 2:
                        this.zzbs = i;
                        break;
                    case 3:
                        this.zzbt = i;
                        break;
                    default:
                        throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
                }
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            }

            public final void writeToParcel(Parcel parcel, int i) {
                int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                Set set = this.zzap;
                if (set.contains(Integer.valueOf(1))) {
                    SafeParcelWriter.writeInt(parcel, 1, this.zzw);
                }
                if (set.contains(Integer.valueOf(2))) {
                    SafeParcelWriter.writeInt(parcel, 2, this.zzbs);
                }
                if (set.contains(Integer.valueOf(3))) {
                    SafeParcelWriter.writeInt(parcel, 3, this.zzbt);
                }
                SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            }
        }

        @Class(creator = "PersonEntity_CoverEntity_CoverPhotoEntityCreator")
        @VisibleForTesting
        public static final class zzb extends FastSafeParcelableJsonResponse implements CoverPhoto {
            public static final Creator<zzb> CREATOR = new zzw();
            private static final HashMap<String, Field<?, ?>> zzao;
            @Indicator
            private final Set<Integer> zzap;
            @SafeParcelable.Field(id = 2)
            private int zzbu;
            @SafeParcelable.Field(id = 4)
            private int zzbv;
            @SafeParcelable.Field(id = 3)
            private String zzk;
            @VersionField(id = 1)
            private final int zzw;

            static {
                HashMap hashMap = new HashMap();
                zzao = hashMap;
                hashMap.put("height", Field.forInteger("height", 2));
                zzao.put("url", Field.forString("url", 3));
                zzao.put("width", Field.forInteger("width", 4));
            }

            public zzb() {
                this.zzw = 1;
                this.zzap = new HashSet();
            }

            @Constructor
            zzb(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) String str, @Param(id = 4) int i3) {
                this.zzap = set;
                this.zzw = i;
                this.zzbu = i2;
                this.zzk = str;
                this.zzbv = i3;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof zzb)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                zzb com_google_android_gms_internal_plus_zzr_zzb_zzb = (zzb) obj;
                for (Field field : zzao.values()) {
                    if (isFieldSet(field)) {
                        if (!com_google_android_gms_internal_plus_zzr_zzb_zzb.isFieldSet(field)) {
                            return false;
                        }
                        if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzb_zzb.getFieldValue(field))) {
                            return false;
                        }
                    } else if (com_google_android_gms_internal_plus_zzr_zzb_zzb.isFieldSet(field)) {
                        return false;
                    }
                }
                return true;
            }

            public final /* bridge */ /* synthetic */ Object freeze() {
                if (this != null) {
                    return this;
                }
                throw null;
            }

            public final /* synthetic */ Map getFieldMappings() {
                return zzao;
            }

            protected final Object getFieldValue(Field field) {
                switch (field.getSafeParcelableFieldId()) {
                    case 2:
                        return Integer.valueOf(this.zzbu);
                    case 3:
                        return this.zzk;
                    case 4:
                        return Integer.valueOf(this.zzbv);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
                }
            }

            public final int getHeight() {
                return this.zzbu;
            }

            public final String getUrl() {
                return this.zzk;
            }

            public final int getWidth() {
                return this.zzbv;
            }

            public final boolean hasHeight() {
                return this.zzap.contains(Integer.valueOf(2));
            }

            public final boolean hasUrl() {
                return this.zzap.contains(Integer.valueOf(3));
            }

            public final boolean hasWidth() {
                return this.zzap.contains(Integer.valueOf(4));
            }

            public final int hashCode() {
                int i = 0;
                for (Field field : zzao.values()) {
                    int hashCode;
                    if (isFieldSet(field)) {
                        hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            public final boolean isDataValid() {
                return true;
            }

            protected final boolean isFieldSet(Field field) {
                return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
            }

            protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                switch (safeParcelableFieldId) {
                    case 2:
                        this.zzbu = i;
                        break;
                    case 4:
                        this.zzbv = i;
                        break;
                    default:
                        throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
                }
                this.zzap.add(Integer.valueOf(safeParcelableFieldId));
            }

            protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                switch (safeParcelableFieldId) {
                    case 3:
                        this.zzk = str2;
                        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                        return;
                    default:
                        throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
                }
            }

            public final void writeToParcel(Parcel parcel, int i) {
                int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                Set set = this.zzap;
                if (set.contains(Integer.valueOf(1))) {
                    SafeParcelWriter.writeInt(parcel, 1, this.zzw);
                }
                if (set.contains(Integer.valueOf(2))) {
                    SafeParcelWriter.writeInt(parcel, 2, this.zzbu);
                }
                if (set.contains(Integer.valueOf(3))) {
                    SafeParcelWriter.writeString(parcel, 3, this.zzk, true);
                }
                if (set.contains(Integer.valueOf(4))) {
                    SafeParcelWriter.writeInt(parcel, 4, this.zzbv);
                }
                SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            }
        }

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put("coverInfo", Field.forConcreteType("coverInfo", 2, zza.class));
            zzao.put("coverPhoto", Field.forConcreteType("coverPhoto", 3, zzb.class));
            zzao.put("layout", Field.withConverter("layout", 4, new StringToIntConverter().add("banner", 0), false));
        }

        public zzb() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @Constructor
        zzb(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) zza com_google_android_gms_internal_plus_zzr_zzb_zza, @Param(id = 3) zzb com_google_android_gms_internal_plus_zzr_zzb_zzb, @Param(id = 4) int i2) {
            this.zzap = set;
            this.zzw = i;
            this.zzbp = com_google_android_gms_internal_plus_zzr_zzb_zza;
            this.zzbq = com_google_android_gms_internal_plus_zzr_zzb_zzb;
            this.zzbr = i2;
        }

        public final <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzbp = (zza) t;
                    break;
                case 3:
                    this.zzbq = (zzb) t;
                    break;
                default:
                    String canonicalName = t.getClass().getCanonicalName();
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(canonicalName).length() + 62).append("Field with id=").append(safeParcelableFieldId).append(" is not a known custom type.  Found ").append(canonicalName).append(".").toString());
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzb com_google_android_gms_internal_plus_zzr_zzb = (zzb) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zzb.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzb.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zzb.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final CoverInfo getCoverInfo() {
            return this.zzbp;
        }

        public final CoverPhoto getCoverPhoto() {
            return this.zzbq;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzbp;
                case 3:
                    return this.zzbq;
                case 4:
                    return Integer.valueOf(this.zzbr);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final int getLayout() {
            return this.zzbr;
        }

        public final boolean hasCoverInfo() {
            return this.zzap.contains(Integer.valueOf(2));
        }

        public final boolean hasCoverPhoto() {
            return this.zzap.contains(Integer.valueOf(3));
        }

        public final boolean hasLayout() {
            return this.zzap.contains(Integer.valueOf(4));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 4:
                    this.zzbr = i;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
            }
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(2))) {
                SafeParcelWriter.writeParcelable(parcel, 2, this.zzbp, i, true);
            }
            if (set.contains(Integer.valueOf(3))) {
                SafeParcelWriter.writeParcelable(parcel, 3, this.zzbq, i, true);
            }
            if (set.contains(Integer.valueOf(4))) {
                SafeParcelWriter.writeInt(parcel, 4, this.zzbr);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonEntity_ImageEntityCreator")
    @VisibleForTesting
    public static final class zzc extends FastSafeParcelableJsonResponse implements Image {
        public static final Creator<zzc> CREATOR = new zzx();
        private static final HashMap<String, Field<?, ?>> zzao;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 2)
        private String zzk;
        @VersionField(id = 1)
        private final int zzw;

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put("url", Field.forString("url", 2));
        }

        public zzc() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        public zzc(String str) {
            this.zzap = new HashSet();
            this.zzw = 1;
            this.zzk = str;
            this.zzap.add(Integer.valueOf(2));
        }

        @Constructor
        zzc(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) String str) {
            this.zzap = set;
            this.zzw = i;
            this.zzk = str;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc com_google_android_gms_internal_plus_zzr_zzc = (zzc) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zzc.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzc.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zzc.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzk;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final String getUrl() {
            return this.zzk;
        }

        public final boolean hasUrl() {
            return this.zzap.contains(Integer.valueOf(2));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzk = str2;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(2))) {
                SafeParcelWriter.writeString(parcel, 2, this.zzk, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonEntity_NameEntityCreator")
    @VisibleForTesting
    public static final class zzd extends FastSafeParcelableJsonResponse implements Name {
        public static final Creator<zzd> CREATOR = new zzy();
        private static final HashMap<String, Field<?, ?>> zzao;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 2)
        private String zzbw;
        @SafeParcelable.Field(id = 3)
        private String zzbx;
        @SafeParcelable.Field(id = 4)
        private String zzby;
        @SafeParcelable.Field(id = 5)
        private String zzbz;
        @SafeParcelable.Field(id = 6)
        private String zzca;
        @SafeParcelable.Field(id = 7)
        private String zzcb;
        @VersionField(id = 1)
        private final int zzw;

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put("familyName", Field.forString("familyName", 2));
            zzao.put("formatted", Field.forString("formatted", 3));
            zzao.put("givenName", Field.forString("givenName", 4));
            zzao.put("honorificPrefix", Field.forString("honorificPrefix", 5));
            zzao.put("honorificSuffix", Field.forString("honorificSuffix", 6));
            zzao.put("middleName", Field.forString("middleName", 7));
        }

        public zzd() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @Constructor
        zzd(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6) {
            this.zzap = set;
            this.zzw = i;
            this.zzbw = str;
            this.zzbx = str2;
            this.zzby = str3;
            this.zzbz = str4;
            this.zzca = str5;
            this.zzcb = str6;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzd com_google_android_gms_internal_plus_zzr_zzd = (zzd) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zzd.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzd.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zzd.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final String getFamilyName() {
            return this.zzbw;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzbw;
                case 3:
                    return this.zzbx;
                case 4:
                    return this.zzby;
                case 5:
                    return this.zzbz;
                case 6:
                    return this.zzca;
                case 7:
                    return this.zzcb;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final String getFormatted() {
            return this.zzbx;
        }

        public final String getGivenName() {
            return this.zzby;
        }

        public final String getHonorificPrefix() {
            return this.zzbz;
        }

        public final String getHonorificSuffix() {
            return this.zzca;
        }

        public final String getMiddleName() {
            return this.zzcb;
        }

        public final boolean hasFamilyName() {
            return this.zzap.contains(Integer.valueOf(2));
        }

        public final boolean hasFormatted() {
            return this.zzap.contains(Integer.valueOf(3));
        }

        public final boolean hasGivenName() {
            return this.zzap.contains(Integer.valueOf(4));
        }

        public final boolean hasHonorificPrefix() {
            return this.zzap.contains(Integer.valueOf(5));
        }

        public final boolean hasHonorificSuffix() {
            return this.zzap.contains(Integer.valueOf(6));
        }

        public final boolean hasMiddleName() {
            return this.zzap.contains(Integer.valueOf(7));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzbw = str2;
                    break;
                case 3:
                    this.zzbx = str2;
                    break;
                case 4:
                    this.zzby = str2;
                    break;
                case 5:
                    this.zzbz = str2;
                    break;
                case 6:
                    this.zzca = str2;
                    break;
                case 7:
                    this.zzcb = str2;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(2))) {
                SafeParcelWriter.writeString(parcel, 2, this.zzbw, true);
            }
            if (set.contains(Integer.valueOf(3))) {
                SafeParcelWriter.writeString(parcel, 3, this.zzbx, true);
            }
            if (set.contains(Integer.valueOf(4))) {
                SafeParcelWriter.writeString(parcel, 4, this.zzby, true);
            }
            if (set.contains(Integer.valueOf(5))) {
                SafeParcelWriter.writeString(parcel, 5, this.zzbz, true);
            }
            if (set.contains(Integer.valueOf(6))) {
                SafeParcelWriter.writeString(parcel, 6, this.zzca, true);
            }
            if (set.contains(Integer.valueOf(7))) {
                SafeParcelWriter.writeString(parcel, 7, this.zzcb, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonEntity_OrganizationsEntityCreator")
    @VisibleForTesting
    public static final class zze extends FastSafeParcelableJsonResponse implements Organizations {
        public static final Creator<zze> CREATOR = new zzz();
        private static final HashMap<String, Field<?, ?>> zzao;
        @SafeParcelable.Field(id = 6)
        private String mName;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 2)
        private String zzcc;
        @SafeParcelable.Field(id = 3)
        private String zzcd;
        @SafeParcelable.Field(id = 4)
        private String zzce;
        @SafeParcelable.Field(id = 5)
        private String zzcf;
        @SafeParcelable.Field(id = 7)
        private boolean zzcg;
        @SafeParcelable.Field(id = 8)
        private String zzch;
        @SafeParcelable.Field(id = 9)
        private String zzci;
        @SafeParcelable.Field(id = 10)
        private int zzcj;
        @VersionField(id = 1)
        private final int zzw;

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put("department", Field.forString("department", 2));
            zzao.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, Field.forString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, 3));
            zzao.put("endDate", Field.forString("endDate", 4));
            zzao.put("location", Field.forString("location", 5));
            zzao.put("name", Field.forString("name", 6));
            zzao.put("primary", Field.forBoolean("primary", 7));
            zzao.put("startDate", Field.forString("startDate", 8));
            zzao.put("title", Field.forString("title", 9));
            zzao.put("type", Field.withConverter("type", 10, new StringToIntConverter().add("work", 0).add("school", 1), false));
        }

        public zze() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @Constructor
        zze(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) boolean z, @Param(id = 8) String str6, @Param(id = 9) String str7, @Param(id = 10) int i2) {
            this.zzap = set;
            this.zzw = i;
            this.zzcc = str;
            this.zzcd = str2;
            this.zzce = str3;
            this.zzcf = str4;
            this.mName = str5;
            this.zzcg = z;
            this.zzch = str6;
            this.zzci = str7;
            this.zzcj = i2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zze)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zze com_google_android_gms_internal_plus_zzr_zze = (zze) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zze.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zze.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zze.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final String getDepartment() {
            return this.zzcc;
        }

        public final String getDescription() {
            return this.zzcd;
        }

        public final String getEndDate() {
            return this.zzce;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return this.zzcc;
                case 3:
                    return this.zzcd;
                case 4:
                    return this.zzce;
                case 5:
                    return this.zzcf;
                case 6:
                    return this.mName;
                case 7:
                    return Boolean.valueOf(this.zzcg);
                case 8:
                    return this.zzch;
                case 9:
                    return this.zzci;
                case 10:
                    return Integer.valueOf(this.zzcj);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final String getLocation() {
            return this.zzcf;
        }

        public final String getName() {
            return this.mName;
        }

        public final String getStartDate() {
            return this.zzch;
        }

        public final String getTitle() {
            return this.zzci;
        }

        public final int getType() {
            return this.zzcj;
        }

        public final boolean hasDepartment() {
            return this.zzap.contains(Integer.valueOf(2));
        }

        public final boolean hasDescription() {
            return this.zzap.contains(Integer.valueOf(3));
        }

        public final boolean hasEndDate() {
            return this.zzap.contains(Integer.valueOf(4));
        }

        public final boolean hasLocation() {
            return this.zzap.contains(Integer.valueOf(5));
        }

        public final boolean hasName() {
            return this.zzap.contains(Integer.valueOf(6));
        }

        public final boolean hasPrimary() {
            return this.zzap.contains(Integer.valueOf(7));
        }

        public final boolean hasStartDate() {
            return this.zzap.contains(Integer.valueOf(8));
        }

        public final boolean hasTitle() {
            return this.zzap.contains(Integer.valueOf(9));
        }

        public final boolean hasType() {
            return this.zzap.contains(Integer.valueOf(10));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public final boolean isPrimary() {
            return this.zzcg;
        }

        protected final void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 7:
                    this.zzcg = z;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
        }

        protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 10:
                    this.zzcj = i;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
            }
        }

        protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzcc = str2;
                    break;
                case 3:
                    this.zzcd = str2;
                    break;
                case 4:
                    this.zzce = str2;
                    break;
                case 5:
                    this.zzcf = str2;
                    break;
                case 6:
                    this.mName = str2;
                    break;
                case 8:
                    this.zzch = str2;
                    break;
                case 9:
                    this.zzci = str2;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(2))) {
                SafeParcelWriter.writeString(parcel, 2, this.zzcc, true);
            }
            if (set.contains(Integer.valueOf(3))) {
                SafeParcelWriter.writeString(parcel, 3, this.zzcd, true);
            }
            if (set.contains(Integer.valueOf(4))) {
                SafeParcelWriter.writeString(parcel, 4, this.zzce, true);
            }
            if (set.contains(Integer.valueOf(5))) {
                SafeParcelWriter.writeString(parcel, 5, this.zzcf, true);
            }
            if (set.contains(Integer.valueOf(6))) {
                SafeParcelWriter.writeString(parcel, 6, this.mName, true);
            }
            if (set.contains(Integer.valueOf(7))) {
                SafeParcelWriter.writeBoolean(parcel, 7, this.zzcg);
            }
            if (set.contains(Integer.valueOf(8))) {
                SafeParcelWriter.writeString(parcel, 8, this.zzch, true);
            }
            if (set.contains(Integer.valueOf(9))) {
                SafeParcelWriter.writeString(parcel, 9, this.zzci, true);
            }
            if (set.contains(Integer.valueOf(10))) {
                SafeParcelWriter.writeInt(parcel, 10, this.zzcj);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonEntity_PlacesLivedEntityCreator")
    @VisibleForTesting
    public static final class zzf extends FastSafeParcelableJsonResponse implements PlacesLived {
        public static final Creator<zzf> CREATOR = new zzaa();
        private static final HashMap<String, Field<?, ?>> zzao;
        @SafeParcelable.Field(id = 3)
        private String mValue;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 2)
        private boolean zzcg;
        @VersionField(id = 1)
        private final int zzw;

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put("primary", Field.forBoolean("primary", 2));
            zzao.put(FirebaseAnalytics$Param.VALUE, Field.forString(FirebaseAnalytics$Param.VALUE, 3));
        }

        public zzf() {
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @Constructor
        zzf(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) boolean z, @Param(id = 3) String str) {
            this.zzap = set;
            this.zzw = i;
            this.zzcg = z;
            this.mValue = str;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzf)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzf com_google_android_gms_internal_plus_zzr_zzf = (zzf) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zzf.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzf.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zzf.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 2:
                    return Boolean.valueOf(this.zzcg);
                case 3:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final String getValue() {
            return this.mValue;
        }

        public final boolean hasPrimary() {
            return this.zzap.contains(Integer.valueOf(2));
        }

        public final boolean hasValue() {
            return this.zzap.contains(Integer.valueOf(3));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        public final boolean isPrimary() {
            return this.zzcg;
        }

        protected final void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 2:
                    this.zzcg = z;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
        }

        protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 3:
                    this.mValue = str2;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(2))) {
                SafeParcelWriter.writeBoolean(parcel, 2, this.zzcg);
            }
            if (set.contains(Integer.valueOf(3))) {
                SafeParcelWriter.writeString(parcel, 3, this.mValue, true);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonEntity_UrlsEntityCreator")
    @VisibleForTesting
    public static final class zzg extends FastSafeParcelableJsonResponse implements Urls {
        public static final Creator<zzg> CREATOR = new zzab();
        private static final HashMap<String, Field<?, ?>> zzao;
        @SafeParcelable.Field(id = 4)
        private String mValue;
        @Indicator
        private final Set<Integer> zzap;
        @SafeParcelable.Field(id = 6)
        private int zzcj;
        @SafeParcelable.Field(id = 5)
        private String zzck;
        @SafeParcelable.Field(getter = "getType_DEPRECATED_FENACHO", id = 3)
        private final int zzcl;
        @VersionField(id = 1)
        private final int zzw;

        static {
            HashMap hashMap = new HashMap();
            zzao = hashMap;
            hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, Field.forString(PlusShare.KEY_CALL_TO_ACTION_LABEL, 5));
            zzao.put("type", Field.withConverter("type", 6, new StringToIntConverter().add("home", 0).add("work", 1).add("blog", 2).add(Scopes.PROFILE, 3).add("other", 4).add("otherProfile", 5).add("contributor", 6).add("website", 7), false));
            zzao.put(FirebaseAnalytics$Param.VALUE, Field.forString(FirebaseAnalytics$Param.VALUE, 4));
        }

        public zzg() {
            this.zzcl = 4;
            this.zzw = 1;
            this.zzap = new HashSet();
        }

        @Constructor
        zzg(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 5) String str, @Param(id = 6) int i2, @Param(id = 4) String str2, @Param(id = 3) int i3) {
            this.zzcl = 4;
            this.zzap = set;
            this.zzw = i;
            this.zzck = str;
            this.zzcj = i2;
            this.mValue = str2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zzg)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzg com_google_android_gms_internal_plus_zzr_zzg = (zzg) obj;
            for (Field field : zzao.values()) {
                if (isFieldSet(field)) {
                    if (!com_google_android_gms_internal_plus_zzr_zzg.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr_zzg.getFieldValue(field))) {
                        return false;
                    }
                } else if (com_google_android_gms_internal_plus_zzr_zzg.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ Object freeze() {
            if (this != null) {
                return this;
            }
            throw null;
        }

        public final /* synthetic */ Map getFieldMappings() {
            return zzao;
        }

        protected final Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case 4:
                    return this.mValue;
                case 5:
                    return this.zzck;
                case 6:
                    return Integer.valueOf(this.zzcj);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public final String getLabel() {
            return this.zzck;
        }

        public final int getType() {
            return this.zzcj;
        }

        public final String getValue() {
            return this.mValue;
        }

        public final boolean hasLabel() {
            return this.zzap.contains(Integer.valueOf(5));
        }

        public final boolean hasType() {
            return this.zzap.contains(Integer.valueOf(6));
        }

        public final boolean hasValue() {
            return this.zzap.contains(Integer.valueOf(4));
        }

        public final int hashCode() {
            int i = 0;
            for (Field field : zzao.values()) {
                int hashCode;
                if (isFieldSet(field)) {
                    hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public final boolean isDataValid() {
            return true;
        }

        protected final boolean isFieldSet(Field field) {
            return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 6:
                    this.zzcj = i;
                    this.zzap.add(Integer.valueOf(safeParcelableFieldId));
                    return;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
            }
        }

        protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case 4:
                    this.mValue = str2;
                    break;
                case 5:
                    this.zzck = str2;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.zzap.add(Integer.valueOf(safeParcelableFieldId));
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            Set set = this.zzap;
            if (set.contains(Integer.valueOf(1))) {
                SafeParcelWriter.writeInt(parcel, 1, this.zzw);
            }
            if (set.contains(Integer.valueOf(3))) {
                SafeParcelWriter.writeInt(parcel, 3, 4);
            }
            if (set.contains(Integer.valueOf(4))) {
                SafeParcelWriter.writeString(parcel, 4, this.mValue, true);
            }
            if (set.contains(Integer.valueOf(5))) {
                SafeParcelWriter.writeString(parcel, 5, this.zzck, true);
            }
            if (set.contains(Integer.valueOf(6))) {
                SafeParcelWriter.writeInt(parcel, 6, this.zzcj);
            }
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        zzao = hashMap;
        hashMap.put("aboutMe", Field.forString("aboutMe", 2));
        zzao.put("ageRange", Field.forConcreteType("ageRange", 3, zza.class));
        zzao.put(AdMobMediationAdapter.BIRTHDAY_KEY, Field.forString(AdMobMediationAdapter.BIRTHDAY_KEY, 4));
        zzao.put("braggingRights", Field.forString("braggingRights", 5));
        zzao.put("circledByCount", Field.forInteger("circledByCount", 6));
        zzao.put("cover", Field.forConcreteType("cover", 7, zzb.class));
        zzao.put("currentLocation", Field.forString("currentLocation", 8));
        zzao.put("displayName", Field.forString("displayName", 9));
        zzao.put(AdMobMediationAdapter.GENDER_KEY, Field.withConverter(AdMobMediationAdapter.GENDER_KEY, 12, new StringToIntConverter().add(AdColonyUserMetadata.USER_MALE, 0).add(AdColonyUserMetadata.USER_FEMALE, 1).add("other", 2), false));
        zzao.put("id", Field.forString("id", 14));
        zzao.put(BannerType.IMAGE, Field.forConcreteType(BannerType.IMAGE, 15, zzc.class));
        zzao.put("isPlusUser", Field.forBoolean("isPlusUser", 16));
        zzao.put(SchemaSymbols.ATTVAL_LANGUAGE, Field.forString(SchemaSymbols.ATTVAL_LANGUAGE, 18));
        zzao.put("name", Field.forConcreteType("name", 19, zzd.class));
        zzao.put("nickname", Field.forString("nickname", 20));
        zzao.put("objectType", Field.withConverter("objectType", 21, new StringToIntConverter().add("person", 0).add("page", 1), false));
        zzao.put("organizations", Field.forConcreteTypeArray("organizations", 22, zze.class));
        zzao.put("placesLived", Field.forConcreteTypeArray("placesLived", 23, zzf.class));
        zzao.put("plusOneCount", Field.forInteger("plusOneCount", 24));
        zzao.put("relationshipStatus", Field.withConverter("relationshipStatus", 25, new StringToIntConverter().add(AdColonyUserMetadata.USER_SINGLE, 0).add("in_a_relationship", 1).add("engaged", 2).add(AdColonyUserMetadata.USER_MARRIED, 3).add("its_complicated", 4).add("open_relationship", 5).add("widowed", 6).add("in_domestic_partnership", 7).add("in_civil_union", 8), false));
        zzao.put("tagline", Field.forString("tagline", 26));
        zzao.put("url", Field.forString("url", 27));
        zzao.put(Constants.VIDEO_TRACKING_URLS_KEY, Field.forConcreteTypeArray(Constants.VIDEO_TRACKING_URLS_KEY, 28, zzg.class));
        zzao.put("verified", Field.forBoolean("verified", 29));
    }

    public zzr() {
        this.zzw = 1;
        this.zzap = new HashSet();
    }

    public zzr(String str, String str2, zzc com_google_android_gms_internal_plus_zzr_zzc, int i, String str3) {
        this.zzw = 1;
        this.zzap = new HashSet();
        this.zzax = str;
        this.zzap.add(Integer.valueOf(9));
        this.zzaz = str2;
        this.zzap.add(Integer.valueOf(14));
        this.zzba = com_google_android_gms_internal_plus_zzr_zzc;
        this.zzap.add(Integer.valueOf(15));
        this.zzbf = i;
        this.zzap.add(Integer.valueOf(21));
        this.zzk = str3;
        this.zzap.add(Integer.valueOf(27));
    }

    @Constructor
    zzr(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) zza com_google_android_gms_internal_plus_zzr_zza, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 6) int i2, @Param(id = 7) zzb com_google_android_gms_internal_plus_zzr_zzb, @Param(id = 8) String str4, @Param(id = 9) String str5, @Param(id = 12) int i3, @Param(id = 14) String str6, @Param(id = 15) zzc com_google_android_gms_internal_plus_zzr_zzc, @Param(id = 16) boolean z, @Param(id = 18) String str7, @Param(id = 19) zzd com_google_android_gms_internal_plus_zzr_zzd, @Param(id = 20) String str8, @Param(id = 21) int i4, @Param(id = 22) List<zze> list, @Param(id = 23) List<zzf> list2, @Param(id = 24) int i5, @Param(id = 25) int i6, @Param(id = 26) String str9, @Param(id = 27) String str10, @Param(id = 28) List<zzg> list3, @Param(id = 29) boolean z2) {
        this.zzap = set;
        this.zzw = i;
        this.zzaq = str;
        this.zzar = com_google_android_gms_internal_plus_zzr_zza;
        this.zzas = str2;
        this.zzat = str3;
        this.zzau = i2;
        this.zzav = com_google_android_gms_internal_plus_zzr_zzb;
        this.zzaw = str4;
        this.zzax = str5;
        this.zzay = i3;
        this.zzaz = str6;
        this.zzba = com_google_android_gms_internal_plus_zzr_zzc;
        this.zzbb = z;
        this.zzbc = str7;
        this.zzbd = com_google_android_gms_internal_plus_zzr_zzd;
        this.zzbe = str8;
        this.zzbf = i4;
        this.zzbg = list;
        this.zzbh = list2;
        this.zzbi = i5;
        this.zzbj = i6;
        this.zzbk = str9;
        this.zzk = str10;
        this.zzbl = list3;
        this.zzbm = z2;
    }

    public static zzr zza(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzr com_google_android_gms_internal_plus_zzr = (zzr) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return com_google_android_gms_internal_plus_zzr;
    }

    public final <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 22:
                this.zzbg = arrayList;
                break;
            case 23:
                this.zzbh = arrayList;
                break;
            case 28:
                this.zzbl = arrayList;
                break;
            default:
                String canonicalName = arrayList.getClass().getCanonicalName();
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(canonicalName).length() + 71).append("Field with id=").append(safeParcelableFieldId).append(" is not a known array of custom type.  Found ").append(canonicalName).append(".").toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 3:
                this.zzar = (zza) t;
                break;
            case 7:
                this.zzav = (zzb) t;
                break;
            case 15:
                this.zzba = (zzc) t;
                break;
            case 19:
                this.zzbd = (zzd) t;
                break;
            default:
                String canonicalName = t.getClass().getCanonicalName();
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(canonicalName).length() + 62).append("Field with id=").append(safeParcelableFieldId).append(" is not a known custom type.  Found ").append(canonicalName).append(".").toString());
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzr)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzr com_google_android_gms_internal_plus_zzr = (zzr) obj;
        for (Field field : zzao.values()) {
            if (isFieldSet(field)) {
                if (!com_google_android_gms_internal_plus_zzr.isFieldSet(field)) {
                    return false;
                }
                if (!getFieldValue(field).equals(com_google_android_gms_internal_plus_zzr.getFieldValue(field))) {
                    return false;
                }
            } else if (com_google_android_gms_internal_plus_zzr.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        if (this != null) {
            return this;
        }
        throw null;
    }

    public final String getAboutMe() {
        return this.zzaq;
    }

    public final AgeRange getAgeRange() {
        return this.zzar;
    }

    public final String getBirthday() {
        return this.zzas;
    }

    public final String getBraggingRights() {
        return this.zzat;
    }

    public final int getCircledByCount() {
        return this.zzau;
    }

    public final Cover getCover() {
        return this.zzav;
    }

    public final String getCurrentLocation() {
        return this.zzaw;
    }

    public final String getDisplayName() {
        return this.zzax;
    }

    public final /* synthetic */ Map getFieldMappings() {
        return zzao;
    }

    protected final Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 2:
                return this.zzaq;
            case 3:
                return this.zzar;
            case 4:
                return this.zzas;
            case 5:
                return this.zzat;
            case 6:
                return Integer.valueOf(this.zzau);
            case 7:
                return this.zzav;
            case 8:
                return this.zzaw;
            case 9:
                return this.zzax;
            case 12:
                return Integer.valueOf(this.zzay);
            case 14:
                return this.zzaz;
            case 15:
                return this.zzba;
            case 16:
                return Boolean.valueOf(this.zzbb);
            case 18:
                return this.zzbc;
            case 19:
                return this.zzbd;
            case 20:
                return this.zzbe;
            case 21:
                return Integer.valueOf(this.zzbf);
            case 22:
                return this.zzbg;
            case 23:
                return this.zzbh;
            case 24:
                return Integer.valueOf(this.zzbi);
            case 25:
                return Integer.valueOf(this.zzbj);
            case 26:
                return this.zzbk;
            case 27:
                return this.zzk;
            case 28:
                return this.zzbl;
            case 29:
                return Boolean.valueOf(this.zzbm);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
        }
    }

    public final int getGender() {
        return this.zzay;
    }

    public final String getId() {
        return this.zzaz;
    }

    public final Image getImage() {
        return this.zzba;
    }

    public final String getLanguage() {
        return this.zzbc;
    }

    public final Name getName() {
        return this.zzbd;
    }

    public final String getNickname() {
        return this.zzbe;
    }

    public final int getObjectType() {
        return this.zzbf;
    }

    public final List<Organizations> getOrganizations() {
        return (ArrayList) this.zzbg;
    }

    public final List<PlacesLived> getPlacesLived() {
        return (ArrayList) this.zzbh;
    }

    public final int getPlusOneCount() {
        return this.zzbi;
    }

    public final int getRelationshipStatus() {
        return this.zzbj;
    }

    public final String getTagline() {
        return this.zzbk;
    }

    public final String getUrl() {
        return this.zzk;
    }

    public final List<Urls> getUrls() {
        return (ArrayList) this.zzbl;
    }

    public final boolean hasAboutMe() {
        return this.zzap.contains(Integer.valueOf(2));
    }

    public final boolean hasAgeRange() {
        return this.zzap.contains(Integer.valueOf(3));
    }

    public final boolean hasBirthday() {
        return this.zzap.contains(Integer.valueOf(4));
    }

    public final boolean hasBraggingRights() {
        return this.zzap.contains(Integer.valueOf(5));
    }

    public final boolean hasCircledByCount() {
        return this.zzap.contains(Integer.valueOf(6));
    }

    public final boolean hasCover() {
        return this.zzap.contains(Integer.valueOf(7));
    }

    public final boolean hasCurrentLocation() {
        return this.zzap.contains(Integer.valueOf(8));
    }

    public final boolean hasDisplayName() {
        return this.zzap.contains(Integer.valueOf(9));
    }

    public final boolean hasGender() {
        return this.zzap.contains(Integer.valueOf(12));
    }

    public final boolean hasId() {
        return this.zzap.contains(Integer.valueOf(14));
    }

    public final boolean hasImage() {
        return this.zzap.contains(Integer.valueOf(15));
    }

    public final boolean hasIsPlusUser() {
        return this.zzap.contains(Integer.valueOf(16));
    }

    public final boolean hasLanguage() {
        return this.zzap.contains(Integer.valueOf(18));
    }

    public final boolean hasName() {
        return this.zzap.contains(Integer.valueOf(19));
    }

    public final boolean hasNickname() {
        return this.zzap.contains(Integer.valueOf(20));
    }

    public final boolean hasObjectType() {
        return this.zzap.contains(Integer.valueOf(21));
    }

    public final boolean hasOrganizations() {
        return this.zzap.contains(Integer.valueOf(22));
    }

    public final boolean hasPlacesLived() {
        return this.zzap.contains(Integer.valueOf(23));
    }

    public final boolean hasPlusOneCount() {
        return this.zzap.contains(Integer.valueOf(24));
    }

    public final boolean hasRelationshipStatus() {
        return this.zzap.contains(Integer.valueOf(25));
    }

    public final boolean hasTagline() {
        return this.zzap.contains(Integer.valueOf(26));
    }

    public final boolean hasUrl() {
        return this.zzap.contains(Integer.valueOf(27));
    }

    public final boolean hasUrls() {
        return this.zzap.contains(Integer.valueOf(28));
    }

    public final boolean hasVerified() {
        return this.zzap.contains(Integer.valueOf(29));
    }

    public final int hashCode() {
        int i = 0;
        for (Field field : zzao.values()) {
            int hashCode;
            if (isFieldSet(field)) {
                hashCode = getFieldValue(field).hashCode() + (i + field.getSafeParcelableFieldId());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    public final boolean isDataValid() {
        return true;
    }

    protected final boolean isFieldSet(Field field) {
        return this.zzap.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    public final boolean isPlusUser() {
        return this.zzbb;
    }

    public final boolean isVerified() {
        return this.zzbm;
    }

    protected final void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 16:
                this.zzbb = z;
                break;
            case 29:
                this.zzbm = z;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    protected final void setIntegerInternal(Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 6:
                this.zzau = i;
                break;
            case 12:
                this.zzay = i;
                break;
            case 21:
                this.zzbf = i;
                break;
            case 24:
                this.zzbi = i;
                break;
            case 25:
                this.zzbj = i;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    protected final void setStringInternal(Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 2:
                this.zzaq = str2;
                break;
            case 4:
                this.zzas = str2;
                break;
            case 5:
                this.zzat = str2;
                break;
            case 8:
                this.zzaw = str2;
                break;
            case 9:
                this.zzax = str2;
                break;
            case 14:
                this.zzaz = str2;
                break;
            case 18:
                this.zzbc = str2;
                break;
            case 20:
                this.zzbe = str2;
                break;
            case 26:
                this.zzbk = str2;
                break;
            case 27:
                this.zzk = str2;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
        }
        this.zzap.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set set = this.zzap;
        if (set.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzw);
        }
        if (set.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeString(parcel, 2, this.zzaq, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeParcelable(parcel, 3, this.zzar, i, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeString(parcel, 4, this.zzas, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeString(parcel, 5, this.zzat, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeInt(parcel, 6, this.zzau);
        }
        if (set.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeParcelable(parcel, 7, this.zzav, i, true);
        }
        if (set.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeString(parcel, 8, this.zzaw, true);
        }
        if (set.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeString(parcel, 9, this.zzax, true);
        }
        if (set.contains(Integer.valueOf(12))) {
            SafeParcelWriter.writeInt(parcel, 12, this.zzay);
        }
        if (set.contains(Integer.valueOf(14))) {
            SafeParcelWriter.writeString(parcel, 14, this.zzaz, true);
        }
        if (set.contains(Integer.valueOf(15))) {
            SafeParcelWriter.writeParcelable(parcel, 15, this.zzba, i, true);
        }
        if (set.contains(Integer.valueOf(16))) {
            SafeParcelWriter.writeBoolean(parcel, 16, this.zzbb);
        }
        if (set.contains(Integer.valueOf(18))) {
            SafeParcelWriter.writeString(parcel, 18, this.zzbc, true);
        }
        if (set.contains(Integer.valueOf(19))) {
            SafeParcelWriter.writeParcelable(parcel, 19, this.zzbd, i, true);
        }
        if (set.contains(Integer.valueOf(20))) {
            SafeParcelWriter.writeString(parcel, 20, this.zzbe, true);
        }
        if (set.contains(Integer.valueOf(21))) {
            SafeParcelWriter.writeInt(parcel, 21, this.zzbf);
        }
        if (set.contains(Integer.valueOf(22))) {
            SafeParcelWriter.writeTypedList(parcel, 22, this.zzbg, true);
        }
        if (set.contains(Integer.valueOf(23))) {
            SafeParcelWriter.writeTypedList(parcel, 23, this.zzbh, true);
        }
        if (set.contains(Integer.valueOf(24))) {
            SafeParcelWriter.writeInt(parcel, 24, this.zzbi);
        }
        if (set.contains(Integer.valueOf(25))) {
            SafeParcelWriter.writeInt(parcel, 25, this.zzbj);
        }
        if (set.contains(Integer.valueOf(26))) {
            SafeParcelWriter.writeString(parcel, 26, this.zzbk, true);
        }
        if (set.contains(Integer.valueOf(27))) {
            SafeParcelWriter.writeString(parcel, 27, this.zzk, true);
        }
        if (set.contains(Integer.valueOf(28))) {
            SafeParcelWriter.writeTypedList(parcel, 28, this.zzbl, true);
        }
        if (set.contains(Integer.valueOf(29))) {
            SafeParcelWriter.writeBoolean(parcel, 29, this.zzbm);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

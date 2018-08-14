package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

public final class FirebaseOptions {
    private final String zzr;
    private final String zzs;
    private final String zzt;
    private final String zzu;
    private final String zzv;
    private final String zzw;
    private final String zzx;

    public static final class Builder {
        private String zzr;
        private String zzs;
        private String zzt;
        private String zzu;
        private String zzv;
        private String zzw;
        private String zzx;

        public Builder(FirebaseOptions firebaseOptions) {
            this.zzs = firebaseOptions.zzs;
            this.zzr = firebaseOptions.zzr;
            this.zzt = firebaseOptions.zzt;
            this.zzu = firebaseOptions.zzu;
            this.zzv = firebaseOptions.zzv;
            this.zzw = firebaseOptions.zzw;
            this.zzx = firebaseOptions.zzx;
        }

        public final FirebaseOptions build() {
            return new FirebaseOptions(this.zzs, this.zzr, this.zzt, this.zzu, this.zzv, this.zzw, this.zzx);
        }

        public final Builder setApiKey(@NonNull String str) {
            this.zzr = Preconditions.checkNotEmpty(str, "ApiKey must be set.");
            return this;
        }

        public final Builder setApplicationId(@NonNull String str) {
            this.zzs = Preconditions.checkNotEmpty(str, "ApplicationId must be set.");
            return this;
        }

        public final Builder setDatabaseUrl(@Nullable String str) {
            this.zzt = str;
            return this;
        }

        public final Builder setGcmSenderId(@Nullable String str) {
            this.zzv = str;
            return this;
        }

        public final Builder setProjectId(@Nullable String str) {
            this.zzx = str;
            return this;
        }

        public final Builder setStorageBucket(@Nullable String str) {
            this.zzw = str;
            return this;
        }
    }

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(str), "ApplicationId must be set.");
        this.zzs = str;
        this.zzr = str2;
        this.zzt = str3;
        this.zzu = str4;
        this.zzv = str5;
        this.zzw = str6;
        this.zzx = str7;
    }

    public static FirebaseOptions fromResource(Context context) {
        StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        Object string = stringResourceValueReader.getString("google_app_id");
        return TextUtils.isEmpty(string) ? null : new FirebaseOptions(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return Objects.equal(this.zzs, firebaseOptions.zzs) && Objects.equal(this.zzr, firebaseOptions.zzr) && Objects.equal(this.zzt, firebaseOptions.zzt) && Objects.equal(this.zzu, firebaseOptions.zzu) && Objects.equal(this.zzv, firebaseOptions.zzv) && Objects.equal(this.zzw, firebaseOptions.zzw) && Objects.equal(this.zzx, firebaseOptions.zzx);
    }

    public final String getApiKey() {
        return this.zzr;
    }

    public final String getApplicationId() {
        return this.zzs;
    }

    public final String getDatabaseUrl() {
        return this.zzt;
    }

    public final String getGcmSenderId() {
        return this.zzv;
    }

    public final String getProjectId() {
        return this.zzx;
    }

    public final String getStorageBucket() {
        return this.zzw;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzs, this.zzr, this.zzt, this.zzu, this.zzv, this.zzw, this.zzx});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.zzs).add(C1404b.f2097A, this.zzr).add("databaseUrl", this.zzt).add("gcmSenderId", this.zzv).add("storageBucket", this.zzw).add("projectId", this.zzx).toString();
    }
}

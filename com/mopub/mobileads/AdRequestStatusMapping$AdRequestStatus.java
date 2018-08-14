package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;

class AdRequestStatusMapping$AdRequestStatus {
    @Nullable
    private String mClickUrl;
    @Nullable
    private String mFailUrl;
    @Nullable
    private String mImpressionUrl;
    @NonNull
    private AdRequestStatusMapping$LoadingStatus mLoadingStatus;

    public AdRequestStatusMapping$AdRequestStatus(@NonNull AdRequestStatusMapping$LoadingStatus loadingStatus) {
        this(loadingStatus, null, null, null);
    }

    public AdRequestStatusMapping$AdRequestStatus(@NonNull AdRequestStatusMapping$LoadingStatus loadingStatus, @Nullable String failUrl, @Nullable String impressionUrl, @Nullable String clickUrl) {
        Preconditions.checkNotNull(loadingStatus);
        this.mLoadingStatus = loadingStatus;
        this.mFailUrl = failUrl;
        this.mImpressionUrl = impressionUrl;
        this.mClickUrl = clickUrl;
    }

    @NonNull
    private AdRequestStatusMapping$LoadingStatus getStatus() {
        return this.mLoadingStatus;
    }

    private void setStatus(@NonNull AdRequestStatusMapping$LoadingStatus loadingStatus) {
        this.mLoadingStatus = loadingStatus;
    }

    @Nullable
    private String getFailurl() {
        return this.mFailUrl;
    }

    @Nullable
    private String getImpressionUrl() {
        return this.mImpressionUrl;
    }

    private void setImpressionUrl(@Nullable String impressionUrl) {
        this.mImpressionUrl = impressionUrl;
    }

    @Nullable
    private String getClickUrl() {
        return this.mClickUrl;
    }

    private void setClickUrl(@Nullable String clickUrl) {
        this.mClickUrl = clickUrl;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdRequestStatusMapping$AdRequestStatus)) {
            return false;
        }
        AdRequestStatusMapping$AdRequestStatus that = (AdRequestStatusMapping$AdRequestStatus) o;
        if (!(this.mLoadingStatus.equals(that.mLoadingStatus) && TextUtils.equals(this.mFailUrl, that.mFailUrl) && TextUtils.equals(this.mImpressionUrl, that.mImpressionUrl) && TextUtils.equals(this.mClickUrl, that.mClickUrl))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int ordinal = (((this.mLoadingStatus.ordinal() + 899) * 31) + (this.mFailUrl != null ? this.mFailUrl.hashCode() : 0)) * 31;
        if (this.mImpressionUrl != null) {
            hashCode = this.mImpressionUrl.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (ordinal + hashCode) * 31;
        if (this.mClickUrl != null) {
            i = this.mClickUrl.hashCode();
        }
        return hashCode + i;
    }
}

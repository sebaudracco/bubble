package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;

final class JobInvocation$Builder {
    @NonNull
    private int[] mConstraints;
    @NonNull
    private final Bundle mExtras = new Bundle();
    private int mLifetime;
    private boolean mRecurring;
    private boolean mReplaceCurrent;
    private RetryStrategy mRetryStrategy;
    @NonNull
    private String mService;
    @NonNull
    private String mTag;
    @NonNull
    private JobTrigger mTrigger;

    JobInvocation$Builder() {
    }

    JobInvocation build() {
        if (this.mTag != null && this.mService != null && this.mTrigger != null) {
            return new JobInvocation(this, null);
        }
        throw new IllegalArgumentException("Required fields were not populated.");
    }

    public JobInvocation$Builder setTag(@NonNull String mTag) {
        this.mTag = mTag;
        return this;
    }

    public JobInvocation$Builder setService(@NonNull String mService) {
        this.mService = mService;
        return this;
    }

    public JobInvocation$Builder setTrigger(@NonNull JobTrigger mTrigger) {
        this.mTrigger = mTrigger;
        return this;
    }

    public JobInvocation$Builder setRecurring(boolean mRecurring) {
        this.mRecurring = mRecurring;
        return this;
    }

    public JobInvocation$Builder setLifetime(int mLifetime) {
        this.mLifetime = mLifetime;
        return this;
    }

    public JobInvocation$Builder setConstraints(@NonNull int[] mConstraints) {
        this.mConstraints = mConstraints;
        return this;
    }

    public JobInvocation$Builder addExtras(@NonNull Bundle bundle) {
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        return this;
    }

    public JobInvocation$Builder setRetryStrategy(RetryStrategy mRetryStrategy) {
        this.mRetryStrategy = mRetryStrategy;
        return this;
    }

    public JobInvocation$Builder setReplaceCurrent(boolean mReplaceCurrent) {
        this.mReplaceCurrent = mReplaceCurrent;
        return this;
    }
}

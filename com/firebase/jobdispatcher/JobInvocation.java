package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.support.annotation.NonNull;

final class JobInvocation implements JobParameters {
    @NonNull
    private final int[] mConstraints;
    @NonNull
    private final Bundle mExtras;
    private final int mLifetime;
    private final boolean mRecurring;
    private final boolean mReplaceCurrent;
    private final RetryStrategy mRetryStrategy;
    @NonNull
    private final String mService;
    @NonNull
    private final String mTag;
    @NonNull
    private final JobTrigger mTrigger;

    private JobInvocation(Builder builder) {
        this.mTag = Builder.access$000(builder);
        this.mService = Builder.access$100(builder);
        this.mTrigger = Builder.access$200(builder);
        this.mRetryStrategy = Builder.access$300(builder);
        this.mRecurring = Builder.access$400(builder);
        this.mLifetime = Builder.access$500(builder);
        this.mConstraints = Builder.access$600(builder);
        this.mExtras = Builder.access$700(builder);
        this.mReplaceCurrent = Builder.access$800(builder);
    }

    @NonNull
    public String getService() {
        return this.mService;
    }

    @NonNull
    public String getTag() {
        return this.mTag;
    }

    @NonNull
    public JobTrigger getTrigger() {
        return this.mTrigger;
    }

    public int getLifetime() {
        return this.mLifetime;
    }

    public boolean isRecurring() {
        return this.mRecurring;
    }

    @NonNull
    public int[] getConstraints() {
        return this.mConstraints;
    }

    @NonNull
    public Bundle getExtras() {
        return this.mExtras;
    }

    @NonNull
    public RetryStrategy getRetryStrategy() {
        return this.mRetryStrategy;
    }

    public boolean shouldReplaceCurrent() {
        return this.mReplaceCurrent;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }
        JobInvocation jobInvocation = (JobInvocation) o;
        if (this.mTag.equals(jobInvocation.mTag) && this.mService.equals(jobInvocation.mService)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.mTag.hashCode() * 31) + this.mService.hashCode();
    }
}

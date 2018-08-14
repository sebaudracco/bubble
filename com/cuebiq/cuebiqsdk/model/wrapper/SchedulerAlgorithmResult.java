package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.model.manager.AlgorithmScheduler.AlgorithmSchedulerStatus;

public class SchedulerAlgorithmResult {
    private AlgorithmSchedulerStatus mNewStatus;
    private long mNextAcquisitionMills;
    private boolean mRemoveGeofences;
    private int mSleepDwellCounter;
    private int mSleepHighCounter;

    public SchedulerAlgorithmResult(boolean z, long j, AlgorithmSchedulerStatus algorithmSchedulerStatus, int i, int i2) {
        this.mRemoveGeofences = z;
        this.mNextAcquisitionMills = j;
        this.mNewStatus = algorithmSchedulerStatus;
        this.mSleepDwellCounter = i;
        this.mSleepHighCounter = i2;
    }

    public AlgorithmSchedulerStatus getNewStatus() {
        return this.mNewStatus;
    }

    public long getNextAcquisitionMills() {
        return this.mNextAcquisitionMills;
    }

    public int getSleepDwellCounter() {
        return this.mSleepDwellCounter;
    }

    public int getSleepHighCounter() {
        return this.mSleepHighCounter;
    }

    public boolean shouldRemoveGeofences() {
        return this.mRemoveGeofences;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remove geofences: ").append(this.mRemoveGeofences).append(" NextAcquisitionMills: ").append(this.mNextAcquisitionMills).append(" Status: ").append(this.mNewStatus.toString()).append(" cSleepDwellCounter: ").append(this.mSleepDwellCounter).append(" SleepHighCounter: ").append(this.mSleepHighCounter);
        return stringBuilder.toString();
    }
}

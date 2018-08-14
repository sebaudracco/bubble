package com.cuebiq.cuebiqsdk.model;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;

public class InfoAnalysisResult {
    private long mNextAcquisitionMills;
    private TrackRequest mTrackRequest;

    public long getNextAcquisitionMills() {
        return this.mNextAcquisitionMills;
    }

    public TrackRequest getTrackRequest() {
        return this.mTrackRequest;
    }

    public void setNextAcquisitionMills(long j) {
        this.mNextAcquisitionMills = j;
    }

    public void setTrackRequest(TrackRequest trackRequest) {
        this.mTrackRequest = trackRequest;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson(this);
    }
}

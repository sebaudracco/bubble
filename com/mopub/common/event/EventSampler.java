package com.mopub.common.event;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

public class EventSampler {
    private static final int CAPACITY = 135;
    private static final float LOAD_FACTOR = 0.75f;
    @VisibleForTesting
    static final int MAX_SIZE = 100;
    @NonNull
    private Random mRandom;
    @NonNull
    private LinkedHashMap<String, Boolean> mSampleDecisionsCache;

    public EventSampler() {
        this(new Random());
    }

    @VisibleForTesting
    public EventSampler(@NonNull Random random) {
        this.mRandom = random;
        this.mSampleDecisionsCache = new LinkedHashMap<String, Boolean>(135, 0.75f, true) {
            protected boolean removeEldestEntry(Entry<String, Boolean> entry) {
                return size() > 100;
            }
        };
    }

    boolean sample(@NonNull BaseEvent baseEvent) {
        Preconditions.checkNotNull(baseEvent);
        String requestId = baseEvent.getRequestId();
        if (requestId != null) {
            Boolean existingSample = (Boolean) this.mSampleDecisionsCache.get(requestId);
            if (existingSample != null) {
                return existingSample.booleanValue();
            }
            boolean newSample;
            if (this.mRandom.nextDouble() < baseEvent.getSamplingRate()) {
                newSample = true;
            } else {
                newSample = false;
            }
            this.mSampleDecisionsCache.put(requestId, Boolean.valueOf(newSample));
            return newSample;
        } else if (this.mRandom.nextDouble() < baseEvent.getSamplingRate()) {
            return true;
        } else {
            return false;
        }
    }

    @VisibleForTesting
    int getCacheSize() {
        return this.mSampleDecisionsCache.size();
    }
}

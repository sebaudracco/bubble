package com.integralads.avid.library.mopub.walking;

import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask.StateProvider;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTaskQueue;
import com.integralads.avid.library.mopub.walking.async.AvidCleanupAsyncTask;
import com.integralads.avid.library.mopub.walking.async.AvidEmptyPublishAsyncTask;
import com.integralads.avid.library.mopub.walking.async.AvidPublishAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidStatePublisher implements StateProvider {
    private final AvidAdSessionRegistry adSessionRegistry;
    private JSONObject previousState;
    private final AvidAsyncTaskQueue taskQueue;

    public AvidStatePublisher(AvidAdSessionRegistry adSessionRegistry, AvidAsyncTaskQueue taskQueue) {
        this.adSessionRegistry = adSessionRegistry;
        this.taskQueue = taskQueue;
    }

    public void publishState(JSONObject state, HashSet<String> sessionIds, double timestamp) {
        this.taskQueue.submitTask(new AvidPublishAsyncTask(this, this.adSessionRegistry, sessionIds, state, timestamp));
    }

    public void publishEmptyState(JSONObject emptyState, HashSet<String> sessionIds, double timestamp) {
        this.taskQueue.submitTask(new AvidEmptyPublishAsyncTask(this, this.adSessionRegistry, sessionIds, emptyState, timestamp));
    }

    public void cleanupCache() {
        this.taskQueue.submitTask(new AvidCleanupAsyncTask(this));
    }

    @VisibleForTesting
    public JSONObject getPreviousState() {
        return this.previousState;
    }

    @VisibleForTesting
    public void setPreviousState(JSONObject previousState) {
        this.previousState = previousState;
    }
}

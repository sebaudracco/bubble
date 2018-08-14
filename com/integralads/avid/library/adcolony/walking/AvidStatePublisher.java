package com.integralads.avid.library.adcolony.walking;

import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask.StateProvider;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTaskQueue;
import com.integralads.avid.library.adcolony.walking.async.AvidCleanupAsyncTask;
import com.integralads.avid.library.adcolony.walking.async.AvidEmptyPublishAsyncTask;
import com.integralads.avid.library.adcolony.walking.async.AvidPublishAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidStatePublisher implements StateProvider {
    private final AvidAdSessionRegistry f8409a;
    private JSONObject f8410b;
    private final AvidAsyncTaskQueue f8411c;

    public AvidStatePublisher(AvidAdSessionRegistry adSessionRegistry, AvidAsyncTaskQueue taskQueue) {
        this.f8409a = adSessionRegistry;
        this.f8411c = taskQueue;
    }

    public void publishState(JSONObject state, HashSet<String> sessionIds, double timestamp) {
        this.f8411c.submitTask(new AvidPublishAsyncTask(this, this.f8409a, sessionIds, state, timestamp));
    }

    public void publishEmptyState(JSONObject emptyState, HashSet<String> sessionIds, double timestamp) {
        this.f8411c.submitTask(new AvidEmptyPublishAsyncTask(this, this.f8409a, sessionIds, emptyState, timestamp));
    }

    public void cleanupCache() {
        this.f8411c.submitTask(new AvidCleanupAsyncTask(this));
    }

    @VisibleForTesting
    public JSONObject getPreviousState() {
        return this.f8410b;
    }

    @VisibleForTesting
    public void setPreviousState(JSONObject previousState) {
        this.f8410b = previousState;
    }
}

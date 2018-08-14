package com.integralads.avid.library.adcolony.walking.async;

import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask.StateProvider;
import java.util.HashSet;
import org.json.JSONObject;

public abstract class AbstractAvidPublishAsyncTask extends AvidAsyncTask {
    protected final AvidAdSessionRegistry adSessionRegistry;
    protected final HashSet<String> sessionIds;
    protected final JSONObject state;
    protected final double timestamp;

    public AvidAdSessionRegistry getAdSessionRegistry() {
        return this.adSessionRegistry;
    }

    public HashSet<String> getSessionIds() {
        return this.sessionIds;
    }

    public JSONObject getState() {
        return this.state;
    }

    public double getTimestamp() {
        return this.timestamp;
    }

    public AbstractAvidPublishAsyncTask(StateProvider stateProvider, AvidAdSessionRegistry adSessionRegistry, HashSet<String> sessionIds, JSONObject state, double timestamp) {
        super(stateProvider);
        this.adSessionRegistry = adSessionRegistry;
        this.sessionIds = new HashSet(sessionIds);
        this.state = state;
        this.timestamp = timestamp;
    }
}

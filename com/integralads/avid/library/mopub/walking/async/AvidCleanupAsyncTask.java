package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask.StateProvider;

public class AvidCleanupAsyncTask extends AvidAsyncTask {
    public AvidCleanupAsyncTask(StateProvider stateProvider) {
        super(stateProvider);
    }

    protected String doInBackground(Object... params) {
        this.stateProvider.setPreviousState(null);
        return null;
    }
}

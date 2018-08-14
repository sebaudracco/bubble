package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidCommand;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask.StateProvider;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidEmptyPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidEmptyPublishAsyncTask(StateProvider stateProvider, AvidAdSessionRegistry adSessionRegistry, HashSet<String> sessionIds, JSONObject state, double timestamp) {
        super(stateProvider, adSessionRegistry, sessionIds, state, timestamp);
    }

    protected String doInBackground(Object... params) {
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    protected void onPostExecute(String result) {
        for (InternalAvidAdSession adSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(adSession.getAvidAdSessionId())) {
                adSession.publishEmptyNativeViewStateCommand(result, this.timestamp);
            }
        }
        super.onPostExecute(result);
    }
}

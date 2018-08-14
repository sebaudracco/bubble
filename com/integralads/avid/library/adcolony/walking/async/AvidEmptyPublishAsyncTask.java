package com.integralads.avid.library.adcolony.walking.async;

import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask.StateProvider;
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
        for (InternalAvidAdSession internalAvidAdSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(internalAvidAdSession.getAvidAdSessionId())) {
                internalAvidAdSession.publishEmptyNativeViewStateCommand(result, this.timestamp);
            }
        }
        super.onPostExecute(result);
    }
}

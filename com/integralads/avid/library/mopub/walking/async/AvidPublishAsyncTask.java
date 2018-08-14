package com.integralads.avid.library.mopub.walking.async;

import android.text.TextUtils;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidCommand;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask.StateProvider;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidPublishAsyncTask(StateProvider stateProvider, AvidAdSessionRegistry adSessionRegistry, HashSet<String> sessionIds, JSONObject state, double timestamp) {
        super(stateProvider, adSessionRegistry, sessionIds, state, timestamp);
    }

    protected String doInBackground(Object... params) {
        if (AvidJSONUtil.equalStates(this.state, this.stateProvider.getPreviousState())) {
            return null;
        }
        this.stateProvider.setPreviousState(this.state);
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    protected void onPostExecute(String result) {
        if (!TextUtils.isEmpty(result)) {
            injectCommand(result);
        }
        super.onPostExecute(result);
    }

    private void injectCommand(String command) {
        for (InternalAvidAdSession adSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(adSession.getAvidAdSessionId())) {
                adSession.publishNativeViewStateCommand(command, this.timestamp);
            }
        }
    }
}

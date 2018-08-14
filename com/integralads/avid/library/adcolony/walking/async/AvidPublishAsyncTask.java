package com.integralads.avid.library.adcolony.walking.async;

import android.text.TextUtils;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask.StateProvider;
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
            m11178b(result);
        }
        super.onPostExecute(result);
    }

    private void m11178b(String str) {
        for (InternalAvidAdSession internalAvidAdSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(internalAvidAdSession.getAvidAdSessionId())) {
                internalAvidAdSession.publishNativeViewStateCommand(str, this.timestamp);
            }
        }
    }
}

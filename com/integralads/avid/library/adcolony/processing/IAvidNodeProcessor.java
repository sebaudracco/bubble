package com.integralads.avid.library.adcolony.processing;

import android.view.View;
import org.json.JSONObject;

public interface IAvidNodeProcessor {

    public interface IAvidViewWalker {
        void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject);
    }

    JSONObject getState(View view);

    void iterateChildren(View view, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker, boolean z);
}

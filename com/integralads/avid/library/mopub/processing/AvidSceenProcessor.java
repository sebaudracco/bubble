package com.integralads.avid.library.mopub.processing;

import android.view.View;
import com.integralads.avid.library.mopub.activity.AvidActivityStack;
import com.integralads.avid.library.mopub.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import org.json.JSONObject;

public class AvidSceenProcessor implements IAvidNodeProcessor {
    private final IAvidNodeProcessor childrenProcessor;

    public AvidSceenProcessor(IAvidNodeProcessor childrenProcessor) {
        this.childrenProcessor = childrenProcessor;
    }

    public JSONObject getState(View view) {
        return AvidJSONUtil.getViewState(0, 0, 0, 0);
    }

    public void iterateChildren(View view, JSONObject viewState, IAvidViewWalker walker, boolean sortByZ) {
        for (View childView : AvidActivityStack.getInstance().getRootViews()) {
            walker.walkView(childView, this.childrenProcessor, viewState);
        }
    }
}

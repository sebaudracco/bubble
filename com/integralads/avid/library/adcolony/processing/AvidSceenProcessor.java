package com.integralads.avid.library.adcolony.processing;

import android.view.View;
import com.integralads.avid.library.adcolony.activity.AvidActivityStack;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import org.json.JSONObject;

public class AvidSceenProcessor implements IAvidNodeProcessor {
    private final IAvidNodeProcessor f8334a;

    public AvidSceenProcessor(IAvidNodeProcessor childrenProcessor) {
        this.f8334a = childrenProcessor;
    }

    public JSONObject getState(View view) {
        return AvidJSONUtil.getViewState(0, 0, 0, 0);
    }

    public void iterateChildren(View view, JSONObject viewState, IAvidViewWalker walker, boolean sortByZ) {
        for (View walkView : AvidActivityStack.getInstance().getRootViews()) {
            walker.walkView(walkView, this.f8334a, viewState);
        }
    }
}

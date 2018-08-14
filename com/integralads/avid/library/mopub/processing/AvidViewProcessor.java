package com.integralads.avid.library.mopub.processing;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.mopub.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidViewProcessor implements IAvidNodeProcessor {
    private final int[] xyAxisCoordinates = new int[2];

    public JSONObject getState(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.xyAxisCoordinates);
        return AvidJSONUtil.getViewState(this.xyAxisCoordinates[0], this.xyAxisCoordinates[1], width, height);
    }

    public void iterateChildren(View view, JSONObject viewState, IAvidViewWalker walker, boolean sortByZ) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!sortByZ || VERSION.SDK_INT < 21) {
                iterateChilren(viewGroup, viewState, walker);
            } else {
                sortAndIterateChilren(viewGroup, viewState, walker);
            }
        }
    }

    private void iterateChilren(ViewGroup viewGroup, JSONObject viewState, IAvidViewWalker walker) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            walker.walkView(viewGroup.getChildAt(i), this, viewState);
        }
    }

    @TargetApi(21)
    private void sortAndIterateChilren(ViewGroup viewGroup, JSONObject viewState, IAvidViewWalker walker) {
        HashMap<Float, ArrayList<View>> map = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            ArrayList<View> list = (ArrayList) map.get(Float.valueOf(view.getZ()));
            if (list == null) {
                list = new ArrayList();
                map.put(Float.valueOf(view.getZ()), list);
            }
            list.add(view);
        }
        ArrayList<Float> keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) map.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                walker.walkView((View) it2.next(), this, viewState);
            }
        }
    }
}

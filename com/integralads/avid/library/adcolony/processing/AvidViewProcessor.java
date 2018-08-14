package com.integralads.avid.library.adcolony.processing;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidViewProcessor implements IAvidNodeProcessor {
    private final int[] f8335a = new int[2];

    public JSONObject getState(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.f8335a);
        return AvidJSONUtil.getViewState(this.f8335a[0], this.f8335a[1], width, height);
    }

    public void iterateChildren(View view, JSONObject viewState, IAvidViewWalker walker, boolean sortByZ) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!sortByZ || VERSION.SDK_INT < 21) {
                m11136a(viewGroup, viewState, walker);
            } else {
                m11137b(viewGroup, viewState, walker);
            }
        }
    }

    private void m11136a(ViewGroup viewGroup, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            iAvidViewWalker.walkView(viewGroup.getChildAt(i), this, jSONObject);
        }
    }

    @TargetApi(21)
    private void m11137b(ViewGroup viewGroup, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
            }
            arrayList.add(childAt);
        }
        Object arrayList2 = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) hashMap.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                iAvidViewWalker.walkView((View) it2.next(), this, jSONObject);
            }
        }
    }
}

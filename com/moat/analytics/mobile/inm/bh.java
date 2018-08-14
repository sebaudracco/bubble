package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class bh implements bg {
    bh() {
    }

    public C3378a<WebView> mo6496a(ViewGroup viewGroup) {
        if (viewGroup instanceof WebView) {
            return C3378a.m11559a((WebView) viewGroup);
        }
        Queue linkedList = new LinkedList();
        linkedList.add(viewGroup);
        Set hashSet = new HashSet();
        int i = 0;
        Object obj = null;
        while (!linkedList.isEmpty() && i < 100) {
            int i2 = i + 1;
            Iterator it = new bj((ViewGroup) linkedList.poll()).iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (view instanceof WebView) {
                    if (obj != null) {
                        Log.e("MoatWebViewHound", "Ambiguous ad container: multiple WebViews reside within it.");
                        obj = null;
                        break;
                    }
                    obj = (WebView) view;
                }
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) view;
                    if (!hashSet.contains(viewGroup2)) {
                        hashSet.add(viewGroup2);
                        linkedList.add(viewGroup2);
                    }
                }
            }
            i = i2;
        }
        return C3378a.m11560b(obj);
    }
}

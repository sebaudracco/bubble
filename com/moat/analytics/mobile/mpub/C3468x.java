package com.moat.analytics.mobile.mpub;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.base.functional.Optional;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

final class C3468x {
    private static final LinkedHashSet<String> f8851 = new LinkedHashSet();

    C3468x() {
    }

    @NonNull
    static Optional<WebView> m11826(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            try {
                return Optional.empty();
            } catch (Exception e) {
                return Optional.empty();
            }
        } else if (viewGroup instanceof WebView) {
            return Optional.of((WebView) viewGroup);
        } else {
            Queue linkedList = new LinkedList();
            linkedList.add(viewGroup);
            int i = 0;
            Object obj = null;
            while (!linkedList.isEmpty() && i < 100) {
                int i2 = i + 1;
                ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                int childCount = viewGroup2.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = viewGroup2.getChildAt(i3);
                    if (childAt instanceof WebView) {
                        C3412a.m11642(3, "WebViewHound", childAt, "Found WebView");
                        if (z || C3468x.m11827(String.valueOf(childAt.hashCode()))) {
                            if (obj != null) {
                                C3412a.m11642(3, "WebViewHound", childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                C3412a.m11639("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                i = i2;
                                obj = null;
                                break;
                            }
                            obj = (WebView) childAt;
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                }
                i = i2;
            }
            return Optional.ofNullable(obj);
        }
    }

    private static boolean m11827(String str) {
        try {
            boolean add = f8851.add(str);
            if (f8851.size() > 50) {
                Iterator it = f8851.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            C3412a.m11642(3, "WebViewHound", null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            C3442o.m11756(e);
            return false;
        }
    }
}

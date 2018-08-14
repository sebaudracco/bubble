package com.moat.analytics.mobile.vng;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.p130a.p132b.C3474a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class ab {

    private static class C3478a implements Iterable<View> {
        private final ViewGroup f8872a;

        private class C3477a implements Iterator<View> {
            final /* synthetic */ C3478a f8870a;
            private int f8871b;

            private C3477a(C3478a c3478a) {
                this.f8870a = c3478a;
                this.f8871b = -1;
            }

            public View m11855a() {
                this.f8871b++;
                return this.f8870a.f8872a.getChildAt(this.f8871b);
            }

            public boolean hasNext() {
                return this.f8871b + 1 < this.f8870a.f8872a.getChildCount();
            }

            public /* synthetic */ Object next() {
                return m11855a();
            }

            public void remove() {
                throw new UnsupportedOperationException("Not implemented. Under development.");
            }
        }

        private C3478a(ViewGroup viewGroup) {
            this.f8872a = viewGroup;
        }

        public Iterator<View> iterator() {
            return new C3477a();
        }
    }

    ab() {
    }

    @NonNull
    static C3474a<WebView> m11857a(ViewGroup viewGroup) {
        if (viewGroup instanceof WebView) {
            return C3474a.m11841a((WebView) viewGroup);
        }
        Queue linkedList = new LinkedList();
        linkedList.add(viewGroup);
        Set hashSet = new HashSet();
        int i = 0;
        Object obj = null;
        while (!linkedList.isEmpty() && i < 100) {
            int i2 = i + 1;
            Iterator it = new C3478a((ViewGroup) linkedList.poll()).iterator();
            while (it.hasNext()) {
                Object obj2 = (View) it.next();
                if (obj2 instanceof WebView) {
                    if (obj != null) {
                        C3511p.m11976a(3, "WebViewHound", obj2, "Ambiguous ad container: multiple WebViews reside within it.");
                        C3511p.m11978a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                        obj = null;
                        break;
                    }
                    obj = (WebView) obj2;
                }
                if (obj2 instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) obj2;
                    if (!hashSet.contains(viewGroup2)) {
                        hashSet.add(viewGroup2);
                        linkedList.add(viewGroup2);
                    }
                }
            }
            i = i2;
        }
        return C3474a.m11842b(obj);
    }
}

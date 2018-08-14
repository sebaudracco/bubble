package com.integralads.avid.library.inmobi.session.internal;

import android.view.View;
import com.integralads.avid.library.inmobi.p128h.C3322b;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ObstructionsWhiteList */
public class C3340i {
    private final ArrayList<C3322b> f8496a = new ArrayList();

    public void m11434a(View view) {
        this.f8496a.add(new C3322b(view));
    }

    public boolean m11435b(View view) {
        Iterator it = this.f8496a.iterator();
        while (it.hasNext()) {
            if (((C3322b) it.next()).m11357b(view)) {
                return true;
            }
        }
        return false;
    }
}

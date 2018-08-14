package com.integralads.avid.library.adcolony.session.internal;

import android.view.View;
import com.integralads.avid.library.adcolony.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Iterator;

public class ObstructionsWhiteList {
    private final ArrayList<AvidView> f8368a = new ArrayList();

    public void add(View view) {
        this.f8368a.add(new AvidView(view));
    }

    public boolean contains(View view) {
        Iterator it = this.f8368a.iterator();
        while (it.hasNext()) {
            if (((AvidView) it.next()).contains(view)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AvidView> getWhiteList() {
        return this.f8368a;
    }
}

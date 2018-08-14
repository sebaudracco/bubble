package com.integralads.avid.library.mopub.session.internal;

import android.view.View;
import com.integralads.avid.library.mopub.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Iterator;

public class ObstructionsWhiteList {
    private final ArrayList<AvidView> whiteList = new ArrayList();

    public void add(View view) {
        this.whiteList.add(new AvidView(view));
    }

    public boolean contains(View view) {
        Iterator it = this.whiteList.iterator();
        while (it.hasNext()) {
            if (((AvidView) it.next()).contains(view)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AvidView> getWhiteList() {
        return this.whiteList;
    }
}

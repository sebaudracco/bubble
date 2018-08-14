package com.moat.analytics.mobile.inm;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;

class bj implements Iterable<View> {
    private final ViewGroup f8587a;

    private bj(ViewGroup viewGroup) {
        this.f8587a = viewGroup;
    }

    public Iterator<View> iterator() {
        return new bk();
    }
}

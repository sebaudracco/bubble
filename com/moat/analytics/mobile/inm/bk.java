package com.moat.analytics.mobile.inm;

import android.view.View;
import java.util.Iterator;

class bk implements Iterator<View> {
    final /* synthetic */ bj f8588a;
    private int f8589b;

    private bk(bj bjVar) {
        this.f8588a = bjVar;
        this.f8589b = -1;
    }

    public View m11591a() {
        this.f8589b++;
        return this.f8588a.f8587a.getChildAt(this.f8589b);
    }

    public boolean hasNext() {
        return this.f8589b + 1 < this.f8588a.f8587a.getChildCount();
    }

    public /* synthetic */ Object next() {
        return m11591a();
    }

    public void remove() {
        throw new UnsupportedOperationException("Not implemented. Under development.");
    }
}

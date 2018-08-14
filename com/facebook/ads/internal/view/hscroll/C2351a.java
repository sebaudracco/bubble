package com.facebook.ads.internal.view.hscroll;

import android.util.SparseArray;

public class C2351a {
    private final SparseArray<int[]> f5793a = new SparseArray();

    public void m7439a(int i, int[] iArr) {
        this.f5793a.put(i, iArr);
    }

    public int[] m7440a(int i) {
        return (int[]) this.f5793a.get(i);
    }

    public boolean m7441b(int i) {
        return this.f5793a.indexOfKey(i) >= 0;
    }
}

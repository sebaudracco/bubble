package com.moat.analytics.mobile.inm;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

class ba {
    final /* synthetic */ ax f8571a;
    private final WeakReference[] f8572b;
    private final LinkedList<Object> f8573c;
    private final Method f8574d;

    private ba(ax axVar, Method method, Object... objArr) {
        int i = 0;
        this.f8571a = axVar;
        this.f8573c = new LinkedList();
        if (objArr == null) {
            objArr = ax.f8561a;
        }
        WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
        int length = objArr.length;
        int i2 = 0;
        while (i < length) {
            Object obj = objArr[i];
            if ((obj instanceof Map) || (obj instanceof Integer)) {
                this.f8573c.add(obj);
            }
            int i3 = i2 + 1;
            weakReferenceArr[i2] = new WeakReference(obj);
            i++;
            i2 = i3;
        }
        this.f8572b = weakReferenceArr;
        this.f8574d = method;
    }
}

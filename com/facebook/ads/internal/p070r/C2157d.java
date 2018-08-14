package com.facebook.ads.internal.p070r;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.p056q.p057a.C2107a;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class C2157d {
    @Nullable
    static Float m6928a(View view) {
        Activity a = C2107a.m6763a();
        if (a == null) {
            return null;
        }
        View findViewById = a.findViewById(16908290);
        if (findViewById == null) {
            findViewById = a.getWindow().getDecorView().findViewById(16908290);
        }
        return (findViewById == null || view == null || view.getId() == -1) ? null : findViewById.findViewById(view.getId()) == null ? Float.valueOf(-1.0f) : C2157d.m6929a(findViewById, view);
    }

    @Nullable
    @VisibleForTesting
    static Float m6929a(View view, View view2) {
        if (view == null || view2 == null) {
            return null;
        }
        if (view2.getVisibility() != 0) {
            return Float.valueOf(0.0f);
        }
        List<View> b = C2157d.m6931b(view, view2);
        if (b.isEmpty()) {
            return Float.valueOf(1.0f);
        }
        Rect rect = new Rect();
        if (!view2.getGlobalVisibleRect(rect)) {
            return Float.valueOf(0.0f);
        }
        int height = rect.height() * rect.width();
        Set hashSet = new HashSet();
        hashSet.add(rect);
        Set set = hashSet;
        for (View view3 : b) {
            Rect rect2 = new Rect();
            if (view3.getGlobalVisibleRect(rect2)) {
                Set hashSet2 = new HashSet();
                for (Rect a : r1) {
                    hashSet2.addAll(C2157d.m6930a(a, rect2));
                }
                set = hashSet2;
            }
        }
        int i = 0;
        for (Rect a2 : r1) {
            i = (a2.height() * a2.width()) + i;
        }
        return Float.valueOf(((float) i) / ((float) height));
    }

    @VisibleForTesting
    static Set<Rect> m6930a(Rect rect, Rect rect2) {
        Set<Rect> hashSet = new HashSet();
        if (rect2.intersect(rect)) {
            hashSet.add(new Rect(rect.left, rect.top, rect2.left, rect.bottom));
            hashSet.add(new Rect(rect2.left, rect.top, rect2.right, rect2.top));
            hashSet.add(new Rect(rect2.right, rect.top, rect.right, rect.bottom));
            hashSet.add(new Rect(rect2.left, rect2.bottom, rect2.right, rect.bottom));
            Set<Rect> hashSet2 = new HashSet();
            for (Rect rect3 : hashSet) {
                if (rect3.width() > 0 && rect3.height() > 0) {
                    hashSet2.add(rect3);
                }
            }
            return hashSet2;
        }
        hashSet.add(rect);
        return hashSet;
    }

    private static List<View> m6931b(View view, View view2) {
        List<View> linkedList = new LinkedList();
        Stack stack = new Stack();
        stack.push(view);
        Object obj = null;
        while (!stack.empty()) {
            View view3 = (View) stack.pop();
            if (view3.getVisibility() == 0) {
                if (view3 == view2) {
                    obj = 1;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        stack.push(viewGroup.getChildAt(childCount));
                    }
                } else if (obj != null || (VERSION.SDK_INT >= 21 && view3.getZ() > view2.getZ())) {
                    linkedList.add(view3);
                }
            }
        }
        return linkedList;
    }
}

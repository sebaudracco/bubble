package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class NativeStrandContainerLayout extends ViewGroup {
    private static final String f6784a = NativeStrandContainerLayout.class.getSimpleName();

    public static class C2934a extends LayoutParams {
        public int f6782a;
        public int f6783b;

        public C2934a(int i, int i2) {
            super(i, i2);
        }

        public void m8857a(int i, int i2) {
            this.f6782a = i;
            this.f6783b = i2;
        }

        public C2934a(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public NativeStrandContainerLayout(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < childCount) {
            int measuredHeight;
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                C2934a c2934a = (C2934a) childAt.getLayoutParams();
                int measuredWidth = c2934a.f6782a + childAt.getMeasuredWidth();
                measuredHeight = c2934a.f6783b + childAt.getMeasuredHeight();
                i5 = Math.max(i5, measuredWidth);
                measuredHeight = Math.max(i4, measuredHeight);
                i4 = i5;
            } else {
                measuredHeight = i4;
                i4 = i5;
            }
            i3++;
            i5 = i4;
            i4 = measuredHeight;
        }
        setMeasuredDimension(resolveSize(Math.max(i5, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i4, getSuggestedMinimumHeight()), i2));
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C2934a(-2, -2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                C2934a c2934a = (C2934a) childAt.getLayoutParams();
                childAt.layout(c2934a.f6782a, c2934a.f6783b, c2934a.f6782a + childAt.getMeasuredWidth(), c2934a.f6783b + childAt.getMeasuredHeight());
            }
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof C2934a;
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new C2934a(layoutParams);
    }
}

package com.adcolony.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class bh {
    private static int f849a = 0;
    private static int f850b = 0;
    private static int f851c = 0;
    private static int f852d = 0;

    private static class C0662a {
        private C0662a() {
        }

        @SuppressLint({"NewApi"})
        public static int m1014a(WindowManager windowManager) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.y;
        }
    }

    private static class C0663b {
        private C0663b() {
        }

        @SuppressLint({"NewApi"})
        public static int m1015a(WindowManager windowManager) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.x;
        }
    }

    bh() {
    }

    public static float m1017a(View view, Context context, boolean z, boolean z2, boolean z3) {
        if (view == null || context == null) {
            return 0.0f;
        }
        if (view.getVisibility() != 0 || m1016a(view) == 0.0f) {
            return 0.0f;
        }
        if (!z) {
            return 0.0f;
        }
        if (z3 && (context instanceof Activity) && !((Activity) context).hasWindowFocus()) {
            return 0.0f;
        }
        float height;
        Rect rect;
        float height2;
        if (view.getHeight() > 0 && view.getWidth() > 0) {
            height = (float) (view.getHeight() * view.getWidth());
            rect = new Rect();
            boolean globalVisibleRect = view.getGlobalVisibleRect(rect);
            int[] iArr = new int[]{-1, -1};
            view.getLocationInWindow(iArr);
            int[] iArr2 = new int[]{-1, -1};
            iArr2[0] = iArr[0] + view.getWidth();
            iArr2[1] = iArr[1] + view.getHeight();
            int a = m1020a(context);
            int b = m1022b(context);
            if (iArr2[0] < 0 || iArr2[1] < 0 || iArr[0] > b || iArr[1] > a || (rect.top == 0 && iArr[1] > a / 2)) {
                return 0.0f;
            }
            if (globalVisibleRect) {
                height2 = (float) (rect.height() * rect.width());
                if (height2 > 0.0f) {
                    if (z2) {
                        try {
                            float a2 = m1018a(view, rect, height2, false);
                            if (a2 > 0.0f && a2 <= height2) {
                                height2 -= a2;
                            }
                        } catch (Exception e) {
                        }
                    }
                    height2 = (height2 / height) * 100.0f;
                    if (height2 < 0.0f) {
                        return 0.0f;
                    }
                    if (height2 > 100.0f) {
                        return 100.0f;
                    }
                    return height2;
                }
            }
        } else if (view.getLayoutParams().height == -2) {
            int[] iArr3 = new int[]{-1, -1};
            view.getLocationInWindow(iArr3);
            int[] iArr4 = new int[]{-1, -1};
            iArr4[0] = iArr3[0] + view.getWidth();
            iArr4[1] = iArr3[1] + 1;
            rect = new Rect(iArr3[0], iArr3[1], iArr4[0], iArr4[1]);
            int a3 = m1020a(context);
            int b2 = m1022b(context);
            if (iArr4[0] < 0 || iArr4[1] < 0 || iArr3[0] > b2 || iArr3[1] > a3 || (rect.top == 0 && iArr3[1] > a3 / 2)) {
                return 0.0f;
            }
            height = (float) (rect.height() * rect.width());
            if (z2) {
                try {
                    height2 = m1018a(view, rect, height, true);
                    if (height2 > 0.0f && height2 <= height) {
                        height2 = height - height2;
                        height2 = (height2 / height) * 100.0f;
                        if (height2 < 0.0f) {
                            return 0.0f;
                        }
                        if (height2 <= 100.0f) {
                            return 100.0f;
                        }
                        return height2;
                    }
                } catch (Exception e2) {
                    height2 = height;
                }
            }
            height2 = height;
            height2 = (height2 / height) * 100.0f;
            if (height2 < 0.0f) {
                return 0.0f;
            }
            if (height2 <= 100.0f) {
                return height2;
            }
            return 100.0f;
        }
        return 0.0f;
    }

    static float m1018a(View view, Rect rect, float f, boolean z) {
        View findViewById;
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        LinkedList linkedList4 = new LinkedList();
        ArrayList arrayList = new ArrayList();
        linkedList3.add(rect);
        View view2 = (ViewGroup) view.getParent();
        ViewParent rootView = view.getRootView();
        try {
            findViewById = ((Activity) view.getContext()).findViewById(16908290);
        } catch (Exception e) {
            findViewById = null;
        }
        while (view2 != null && view2.getParent() != rootView) {
            if (view2.getVisibility() != 0 || m1016a(view2) == 0.0f) {
                return f;
            }
            if (findViewById != null && z && view2 != findViewById && (view2.getLayoutParams().height == 0 || view2.getLayoutParams().width == 0)) {
                return f;
            }
            linkedList.addFirst(view2);
            ViewGroup viewGroup = (ViewGroup) view2.getParent();
        }
        if (view2 == null) {
            return f;
        }
        Object obj;
        float f2;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            view2 = (View) it.next();
            ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
            if (viewGroup2 == null) {
                return f;
            }
            int indexOfChild;
            if (!"viewpager".equalsIgnoreCase(viewGroup2.getClass().getSimpleName())) {
                indexOfChild = viewGroup2.indexOfChild(view2);
                if (indexOfChild < viewGroup2.getChildCount() - 1) {
                    for (indexOfChild++; indexOfChild < viewGroup2.getChildCount(); indexOfChild++) {
                        View childAt = viewGroup2.getChildAt(indexOfChild);
                        if (m1024c(childAt)) {
                            Collection b = m1023b(childAt);
                            if (b != null) {
                                linkedList4.addAll(0, b);
                            }
                        } else if (childAt.getVisibility() == 0 && m1016a(childAt) != 0.0f) {
                            linkedList4.addFirst(childAt);
                        }
                    }
                }
            }
        }
        Iterator it2 = linkedList4.iterator();
        float f3 = 0.0f;
        while (it2.hasNext()) {
            view2 = (View) it2.next();
            if (f3 >= f) {
                obj = null;
                f2 = f3;
                break;
            }
            if (view2 != null) {
                try {
                    if (view2.getTag() != null && ((String) view2.getTag()).contains("BTN_CLOSE")) {
                    }
                } catch (Exception e2) {
                }
            }
            Rect rect2 = new Rect();
            if (view2.getGlobalVisibleRect(rect2)) {
                float width;
                if (z) {
                    rect2.top++;
                }
                if (rect2.intersect(rect)) {
                    linkedList2.add(rect2);
                    width = (float) (rect2.width() * rect2.height());
                    if (width >= f) {
                        f2 = width;
                        indexOfChild = 1;
                        break;
                    }
                }
                width = f3;
                f3 = width;
            } else {
                continue;
            }
        }
        obj = null;
        f2 = f3;
        if (obj != null) {
            return f;
        }
        if (!linkedList2.isEmpty()) {
            if (linkedList2.size() == 1) {
                return f2;
            }
            Rect rect3;
            it2 = linkedList2.iterator();
            while (it2.hasNext()) {
                rect3 = (Rect) it2.next();
                arrayList.clear();
                arrayList.addAll(linkedList3);
                for (int i = 0; i < arrayList.size(); i++) {
                    rect2 = (Rect) arrayList.get(i);
                    if (rect3.intersect(rect2)) {
                        linkedList3.remove(arrayList.get(i));
                        for (int i2 = 1; i2 < 9; i2++) {
                            Rect a = m1021a(rect2, rect3, i2);
                            if (a.height() > 0 && a.width() > 0) {
                                linkedList3.add(a);
                            }
                        }
                    }
                }
            }
            if (!linkedList3.isEmpty()) {
                it = linkedList3.iterator();
                float f4 = 0.0f;
                while (it.hasNext()) {
                    rect3 = (Rect) it.next();
                    f4 = ((float) (rect3.height() * rect3.width())) + f4;
                }
                if (f4 < f) {
                    return f - f4;
                }
            }
        }
        return f2;
    }

    static Rect m1021a(Rect rect, Rect rect2, int i) {
        Rect rect3 = new Rect();
        switch (i) {
            case 1:
                rect3.set(rect.left, rect.top, rect2.left, rect2.top);
                break;
            case 2:
                rect3.set(rect2.left, rect.top, rect2.right, rect2.top);
                break;
            case 3:
                rect3.set(rect2.right, rect.top, rect.right, rect2.top);
                break;
            case 4:
                rect3.set(rect2.right, rect2.top, rect.right, rect2.bottom);
                break;
            case 5:
                rect3.set(rect2.right, rect2.bottom, rect.right, rect.bottom);
                break;
            case 6:
                rect3.set(rect2.left, rect2.bottom, rect2.right, rect.bottom);
                break;
            case 7:
                rect3.set(rect.left, rect2.bottom, rect2.left, rect.bottom);
                break;
            case 8:
                rect3.set(rect.left, rect2.top, rect2.left, rect2.bottom);
                break;
        }
        return rect3;
    }

    @SuppressLint({"NewApi"})
    static float m1016a(View view) {
        if (view == null) {
            return 0.0f;
        }
        if (m1019a() < 11) {
            return 1.0f;
        }
        return view.getAlpha();
    }

    private static ArrayList<View> m1023b(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view.getVisibility() != 0 || m1016a(view) == 0.0f) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        ArrayList<View> arrayList = new ArrayList();
        linkedList.add((ViewGroup) view);
        ListIterator listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            ViewGroup viewGroup = (ViewGroup) listIterator.next();
            listIterator.remove();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0 && m1016a(childAt) != 0.0f) {
                    if (childAt instanceof ViewGroup) {
                        if (m1024c(childAt)) {
                            listIterator.add((ViewGroup) childAt);
                            listIterator.previous();
                        } else {
                            arrayList.add(childAt);
                        }
                    } else if (!m1024c(childAt)) {
                        arrayList.add(childAt);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    @SuppressLint({"NewApi"})
    private static boolean m1024c(View view) {
        if (view == null) {
            return false;
        }
        if (view.getBackground() == null || (m1019a() > 18 && view.getBackground().getAlpha() == 0)) {
            return true;
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static int m1020a(Context context) {
        int i;
        if (context != null) {
            i = context.getResources().getConfiguration().orientation;
        } else {
            i = -1;
        }
        if (i == 2 && f849a > 0) {
            return f849a;
        }
        if (i == 1 && f851c > 0) {
            return f851c;
        }
        try {
            int a;
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (m1019a() >= 13) {
                a = C0662a.m1014a(windowManager);
            } else {
                a = windowManager.getDefaultDisplay().getHeight();
            }
            if (i == 2) {
                f849a = a;
                return a;
            } else if (i != 1) {
                return a;
            } else {
                f851c = a;
                return a;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    public static int m1022b(Context context) {
        int i;
        if (context != null) {
            i = context.getResources().getConfiguration().orientation;
        } else {
            i = -1;
        }
        if (i == 2 && f850b > 0) {
            return f850b;
        }
        if (i == 1 && f852d > 0) {
            return f852d;
        }
        try {
            int a;
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (m1019a() >= 13) {
                a = C0663b.m1015a(windowManager);
            } else {
                a = windowManager.getDefaultDisplay().getWidth();
            }
            if (i == 2) {
                f850b = a;
                return a;
            } else if (i != 1) {
                return a;
            } else {
                f852d = a;
                return a;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public static int m1019a() {
        return VERSION.SDK_INT;
    }
}

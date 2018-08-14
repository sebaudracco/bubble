package com.facebook.ads.internal.p070r;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.ads.internal.p056q.p057a.C1866w;
import com.facebook.ads.internal.p056q.p057a.C2129r;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p057a.C2134x;
import com.facebook.ads.internal.p056q.p078e.C2151a;
import com.facebook.ads.internal.p056q.p078e.C2152b;
import com.facebook.ads.internal.p068l.C2005a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

public class C2154a {
    private static final String f5139a = C2154a.class.getSimpleName();
    private final View f5140b;
    private final int f5141c;
    private final int f5142d;
    private final Handler f5143e;
    private final WeakReference<C2025a> f5144f;
    private final boolean f5145g;
    private Runnable f5146h;
    private int f5147i;
    private int f5148j;
    private boolean f5149k;
    private C2155b f5150l;
    private Map<String, Integer> f5151m;
    private long f5152n;
    private int f5153o;

    public static abstract class C2025a {
        public abstract void mo3725a();

        public void mo3776b() {
        }
    }

    private static final class C2153b extends C1866w<C2154a> {
        C2153b(C2154a c2154a) {
            super(c2154a);
        }

        public void run() {
            int i = 0;
            C2154a c2154a = (C2154a) m5564a();
            if (c2154a != null) {
                View a = c2154a.f5140b;
                C2025a c2025a = (C2025a) c2154a.f5144f.get();
                if (a != null && c2025a != null) {
                    C2155b a2 = C2154a.m6899a(a, c2154a.f5141c);
                    if (a2.m6923a()) {
                        c2154a.f5153o = c2154a.f5153o + 1;
                    } else {
                        c2154a.f5153o = 0;
                    }
                    int i2 = c2154a.f5153o > c2154a.f5142d ? 1 : 0;
                    int i3 = (c2154a.f5150l == null || !c2154a.f5150l.m6923a()) ? 0 : 1;
                    if (!(i2 == 0 && a2.m6923a())) {
                        c2154a.f5150l = a2;
                    }
                    String valueOf = String.valueOf(a2.m6924b());
                    synchronized (c2154a) {
                        if (c2154a.f5151m.containsKey(valueOf)) {
                            i = ((Integer) c2154a.f5151m.get(valueOf)).intValue();
                        }
                        c2154a.f5151m.put(valueOf, Integer.valueOf(i + 1));
                    }
                    if (i2 != 0 && i3 == 0) {
                        c2154a.f5152n = System.currentTimeMillis();
                        c2025a.mo3725a();
                        if (!c2154a.f5145g) {
                            return;
                        }
                    } else if (i2 == 0 && i3 != 0) {
                        c2025a.mo3776b();
                    }
                    if (!c2154a.f5149k && c2154a.f5146h != null) {
                        c2154a.f5143e.postDelayed(c2154a.f5146h, (long) c2154a.f5148j);
                    }
                }
            }
        }
    }

    public C2154a(View view, int i, int i2, boolean z, C2025a c2025a) {
        this.f5143e = new Handler();
        this.f5147i = 0;
        this.f5148j = 1000;
        this.f5149k = true;
        this.f5150l = new C2155b(C2156c.f5157a);
        this.f5151m = new HashMap();
        this.f5152n = 0;
        this.f5153o = 0;
        this.f5140b = view;
        if (view.getId() == -1) {
            C2133v.m6832a(view);
        }
        this.f5141c = i;
        this.f5144f = new WeakReference(c2025a);
        this.f5145g = z;
        if (i2 < 0) {
            i2 = 0;
        }
        this.f5142d = i2;
    }

    public C2154a(View view, int i, C2025a c2025a) {
        this(view, i, 0, false, c2025a);
    }

    public C2154a(View view, int i, boolean z, C2025a c2025a) {
        this(view, i, 0, z, c2025a);
    }

    private static int m6895a(Vector<Rect> vector) {
        int i;
        int i2;
        int size = vector.size();
        int[] iArr = new int[(size * 2)];
        int[] iArr2 = new int[(size * 2)];
        boolean[][] zArr = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{size * 2, size * 2});
        int i3 = 0;
        int i4 = 0;
        for (i = 0; i < size; i++) {
            Rect rect = (Rect) vector.elementAt(i);
            int i5 = i4 + 1;
            iArr[i4] = rect.left;
            int i6 = i3 + 1;
            iArr2[i3] = rect.bottom;
            i4 = i5 + 1;
            iArr[i5] = rect.right;
            i3 = i6 + 1;
            iArr2[i6] = rect.top;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (i3 = 0; i3 < size; i3++) {
            rect = (Rect) vector.elementAt(i3);
            i = C2154a.m6896a(iArr, rect.left);
            i4 = C2154a.m6896a(iArr, rect.right);
            i5 = C2154a.m6896a(iArr2, rect.top);
            i6 = C2154a.m6896a(iArr2, rect.bottom);
            for (i++; i <= i4; i++) {
                for (i2 = i5 + 1; i2 <= i6; i2++) {
                    zArr[i][i2] = true;
                }
            }
        }
        i4 = 0;
        i2 = 0;
        while (i4 < size * 2) {
            i3 = i2;
            for (i = 0; i < size * 2; i++) {
                i3 += zArr[i4][i] ? (iArr[i4] - iArr[i4 - 1]) * (iArr2[i] - iArr2[i - 1]) : 0;
            }
            i4++;
            i2 = i3;
        }
        return i2;
    }

    private static int m6896a(int[] iArr, int i) {
        int i2 = 0;
        int length = iArr.length;
        while (i2 < length) {
            int i3 = ((length - i2) / 2) + i2;
            if (iArr[i3] == i) {
                return i3;
            }
            if (iArr[i3] > i) {
                length = i2;
            } else {
                int i4 = length;
                length = i3 + 1;
                i3 = i4;
            }
            i2 = length;
            length = i3;
        }
        return -1;
    }

    public static C2155b m6899a(View view, int i) {
        if (view == null) {
            C2154a.m6902a(null, false, "adView is null.");
            return new C2155b(C2156c.AD_IS_NULL);
        } else if (view.getParent() == null) {
            C2154a.m6902a(view, false, "adView has no parent.");
            return new C2155b(C2156c.INVALID_PARENT);
        } else if (!view.isShown()) {
            C2154a.m6902a(view, false, "adView parent is not set to VISIBLE.");
            return new C2155b(C2156c.INVALID_PARENT);
        } else if (view.getWindowVisibility() != 0) {
            C2154a.m6902a(view, false, "adView window is not set to VISIBLE.");
            return new C2155b(C2156c.INVALID_WINDOW);
        } else if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            C2154a.m6902a(view, false, "adView has invisible dimensions (w=" + view.getMeasuredWidth() + ", h=" + view.getMeasuredHeight());
            return new C2155b(C2156c.INVALID_DIMENSIONS);
        } else if (view.getAlpha() < 0.9f) {
            C2154a.m6902a(view, false, "adView is too transparent.");
            return new C2155b(C2156c.AD_IS_TRANSPARENT);
        } else {
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                if (!view.getGlobalVisibleRect(rect)) {
                    return new C2155b(C2156c.AD_IS_NOT_VISIBLE);
                }
                DisplayMetrics displayMetrics;
                Context context = view.getContext();
                if (VERSION.SDK_INT >= 17) {
                    Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    displayMetrics = new DisplayMetrics();
                    defaultDisplay.getRealMetrics(displayMetrics);
                } else {
                    displayMetrics = context.getResources().getDisplayMetrics();
                }
                float f = 0.0f;
                if (C2005a.m6355p(context)) {
                    rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
                    if (rect.intersect(iArr[0], iArr[1], iArr[0] + width, iArr[1] + height)) {
                        f = (((float) (rect.width() * rect.height())) * 1.0f) / ((float) (width * height));
                    }
                } else {
                    Vector a = C2154a.m6901a(view);
                    int a2 = C2154a.m6895a(a);
                    a.add(rect);
                    f = (((float) (C2154a.m6895a(a) - a2)) * 1.0f) / ((float) (view.getMeasuredHeight() * view.getMeasuredWidth()));
                }
                float o = ((float) C2005a.m6354o(context)) / 100.0f;
                if (C2005a.m6353n(context)) {
                    if (f < o) {
                        C2154a.m6902a(view, false, String.format(Locale.US, "adView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{Float.valueOf(f), Float.valueOf(o)}));
                        return new C2155b(C2156c.AD_INSUFFICIENT_VISIBLE_AREA, f);
                    }
                } else if (iArr[0] < 0 || displayMetrics.widthPixels - iArr[0] < width) {
                    C2154a.m6902a(view, false, "adView is not fully on screen horizontally.");
                    return new C2155b(C2156c.AD_OFFSCREEN_HORIZONTALLY, f);
                } else {
                    width = (int) ((((double) height) * (100.0d - ((double) i))) / 100.0d);
                    if (iArr[1] < 0 && Math.abs(iArr[1]) > width) {
                        C2154a.m6902a(view, false, "adView is not visible from the top.");
                        return new C2155b(C2156c.AD_OFFSCREEN_TOP, f);
                    } else if ((height + iArr[1]) - displayMetrics.heightPixels > width) {
                        C2154a.m6902a(view, false, "adView is not visible from the bottom.");
                        return new C2155b(C2156c.AD_OFFSCREEN_BOTTOM, f);
                    }
                }
                if (C2151a.m6890b(context)) {
                    Map a3 = C2152b.m6891a(context);
                    if (C2134x.m6839b(a3)) {
                        C2154a.m6902a(view, false, "Keyguard is obstructing view.");
                        return new C2155b(C2156c.AD_IS_OBSTRUCTED_BY_KEYGUARD, f);
                    } else if (C2005a.m6342c(context) && C2134x.m6838a(a3)) {
                        C2154a.m6902a(view, false, "Ad is on top of the Lockscreen.");
                        return new C2155b(C2156c.AD_IN_LOCKSCREEN, f, a3);
                    } else {
                        Float f2 = null;
                        if (C2005a.m6356q(context)) {
                            f2 = C2157d.m6928a(view);
                        }
                        if (f2 != null) {
                            if (f2.floatValue() == -1.0f) {
                                C2154a.m6902a(view, false, "adView is not in the top activity");
                                return new C2155b(C2156c.AD_IS_NOT_IN_ACTIVITY);
                            } else if (f2.floatValue() == 0.0f) {
                                C2154a.m6902a(view, false, "adView is not visible");
                                return new C2155b(C2156c.AD_IS_NOT_VISIBLE);
                            }
                        }
                        if (!C2005a.m6357r(context) || f2 == null || f2.floatValue() >= o) {
                            C2154a.m6902a(view, true, "adView is visible.");
                            return new C2155b(C2156c.IS_VIEWABLE, f, a3);
                        }
                        C2154a.m6902a(view, false, String.format(Locale.US, "adView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{f2, Float.valueOf(o)}));
                        return new C2155b(C2156c.AD_INSUFFICIENT_VISIBLE_AREA, f, a3);
                    }
                }
                C2154a.m6902a(view, false, "Screen is not interactive.");
                return new C2155b(C2156c.SCREEN_NOT_INTERACTIVE, f);
            } catch (NullPointerException e) {
                C2154a.m6902a(view, false, "Cannot get location on screen.");
                return new C2155b(C2156c.INVALID_DIMENSIONS);
            }
        }
    }

    private static Vector<Rect> m6901a(View view) {
        Vector<Rect> vector = new Vector();
        if (!(view.getParent() instanceof ViewGroup)) {
            return vector;
        }
        View view2 = (ViewGroup) view.getParent();
        for (int indexOfChild = view2.indexOfChild(view) + 1; indexOfChild < view2.getChildCount(); indexOfChild++) {
            vector.addAll(C2154a.m6904b(view2.getChildAt(indexOfChild)));
        }
        vector.addAll(C2154a.m6901a(view2));
        return vector;
    }

    private static void m6902a(View view, boolean z, String str) {
    }

    private static Vector<Rect> m6904b(View view) {
        Vector<Rect> vector = new Vector();
        if (!view.isShown() || (VERSION.SDK_INT >= 11 && view.getAlpha() <= 0.0f)) {
            return vector;
        }
        if ((view instanceof ViewGroup) && C2154a.m6906c(view)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                vector.addAll(C2154a.m6904b(viewGroup.getChildAt(i)));
            }
            return vector;
        }
        Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect)) {
            vector.add(rect);
        }
        return vector;
    }

    private static boolean m6906c(View view) {
        return view.getBackground() == null || (VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0);
    }

    public synchronized void m6917a() {
        if (this.f5146h != null) {
            m6920b();
        }
        this.f5146h = new C2153b(this);
        this.f5143e.postDelayed(this.f5146h, (long) this.f5147i);
        this.f5149k = false;
        this.f5153o = 0;
        this.f5150l = new C2155b(C2156c.f5157a);
        this.f5151m = new HashMap();
    }

    public void m6918a(int i) {
        this.f5147i = i;
    }

    public synchronized void m6919a(Map<String, String> map) {
        map.put("vrc", String.valueOf(this.f5150l.m6924b()));
        map.put("vp", String.valueOf(this.f5150l.m6925c()));
        map.put("vh", new JSONObject(this.f5151m).toString());
        map.put("vt", C2129r.m6819a(this.f5152n));
        map.putAll(this.f5150l.m6926d());
    }

    public synchronized void m6920b() {
        this.f5143e.removeCallbacks(this.f5146h);
        this.f5146h = null;
        this.f5149k = true;
        this.f5153o = 0;
    }

    public void m6921b(int i) {
        this.f5148j = i;
    }

    public synchronized String m6922c() {
        return C2156c.values()[this.f5150l.m6924b()].toString() + String.format(" (%.1f%%)", new Object[]{Float.valueOf(this.f5150l.m6925c() * 100.0f)});
    }
}

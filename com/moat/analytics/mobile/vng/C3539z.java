package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobfox.sdk.networking.RequestParams;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class C3539z {
    String f9021a = "{}";
    private C3538a f9022b = new C3538a();
    private JSONObject f9023c;
    private Rect f9024d;
    private Rect f9025e;
    private JSONObject f9026f;
    private JSONObject f9027g;
    private Location f9028h;
    private Map<String, Object> f9029i = new HashMap();

    static class C35371 implements Comparator<Rect> {
        C35371() {
        }

        public int m12038a(Rect rect, Rect rect2) {
            return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m12038a((Rect) obj, (Rect) obj2);
        }
    }

    private static class C3538a {
        Rect f9018a = new Rect(0, 0, 0, 0);
        double f9019b = 0.0d;
        double f9020c = 0.0d;

        C3538a() {
        }
    }

    C3539z() {
    }

    @VisibleForTesting
    static int m12039a(Rect rect, Set<Rect> set) {
        int i = 0;
        if (set.isEmpty()) {
            return 0;
        }
        List<Rect> arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new C35371());
        List arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i2 = 0;
        while (i < arrayList2.size() - 1) {
            if (!((Integer) arrayList2.get(i)).equals(arrayList2.get(i + 1))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i)).intValue(), rect.top, ((Integer) arrayList2.get(i + 1)).intValue(), rect.bottom);
                int i3 = rect.top;
                int i4 = i2;
                i2 = i3;
                for (Rect rect22 : arrayList) {
                    if (Rect.intersects(rect22, rect3)) {
                        if (rect22.bottom > i2) {
                            i4 += (rect22.bottom - Math.max(i2, rect22.top)) * rect3.width();
                            i2 = rect22.bottom;
                        }
                        if (rect22.bottom == rect3.bottom) {
                            i2 = i4;
                            break;
                        }
                    }
                    i4 = i4;
                    i2 = i2;
                }
                i2 = i4;
            }
            i++;
        }
        return i2;
    }

    private static Rect m12040a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static Rect m12041a(View view) {
        return view != null ? C3539z.m12056h(view) : new Rect(0, 0, 0, 0);
    }

    private static C3538a m12042a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        C3538a c3538a = new C3538a();
        int b = C3539z.m12047b(rect);
        if (view != null && z && z2 && !z3 && b > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (view.getGlobalVisibleRect(rect2)) {
                int b2 = C3539z.m12047b(rect2);
                if (b2 < b) {
                    C3511p.m11979b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                Set hashSet = new HashSet();
                if (view.getRootView() instanceof ViewGroup) {
                    c3538a.f9018a = rect2;
                    boolean a = C3539z.m12046a(rect2, view, hashSet);
                    if (a) {
                        c3538a.f9020c = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
                    }
                    if (!a) {
                        int a2 = C3539z.m12039a(rect2, hashSet);
                        if (a2 > 0) {
                            c3538a.f9020c = ((double) a2) / (((double) b2) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
                        }
                        c3538a.f9019b = ((double) (b2 - a2)) / (((double) b) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
                    }
                }
            }
        }
        return c3538a;
    }

    private static Map<String, String> m12043a(Rect rect) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("x", String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put(RequestParams.f9035H, String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    private static Map<String, String> m12044a(Rect rect, DisplayMetrics displayMetrics) {
        return C3539z.m12043a(C3539z.m12048b(rect, displayMetrics));
    }

    private static JSONObject m12045a(Location location) {
        Map b = C3539z.m12049b(location);
        return b == null ? null : new JSONObject(b);
    }

    private static boolean m12046a(Rect rect, View view, Set<Rect> set) {
        View rootView = view.getRootView();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(rootView);
        C3511p.m11979b(2, "VisibilityInfo", view, "starting covering rect search");
        boolean z = false;
        int i = 0;
        while (!arrayDeque.isEmpty() && i < 250) {
            int i2 = i + 1;
            rootView = (View) arrayDeque.pollLast();
            if (rootView.equals(view)) {
                C3511p.m11979b(2, "VisibilityInfo", rect, "found target");
                z = true;
                i = i2;
            } else if (C3539z.m12055g(rootView)) {
                boolean z2;
                if ((rootView instanceof ViewGroup) && !(rootView instanceof ListView)) {
                    ViewGroup viewGroup = (ViewGroup) rootView;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        arrayDeque.add(viewGroup.getChildAt(childCount));
                    }
                }
                if (VERSION.SDK_INT < 21) {
                    if (z) {
                        z2 = true;
                    }
                    z2 = false;
                } else if (rootView.getElevation() > view.getElevation()) {
                    z2 = true;
                } else {
                    if (z && rootView.getElevation() == view.getElevation()) {
                        z2 = true;
                    }
                    z2 = false;
                }
                if (z2) {
                    Rect h = C3539z.m12056h(rootView);
                    if (h.setIntersect(rect, h)) {
                        C3511p.m11979b(2, "VisibilityInfo", rootView, "Covered by " + rootView.getClass().getSimpleName() + "-" + h.toString());
                        set.add(h);
                        if (h.contains(rect)) {
                            return true;
                        }
                    }
                }
                i = i2;
            } else {
                i = i2;
            }
        }
        return false;
    }

    private static int m12047b(Rect rect) {
        return rect.width() * rect.height();
    }

    private static Rect m12048b(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    private static Map<String, String> m12049b(Location location) {
        if (location == null) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        hashMap.put("latitude", Double.toString(location.getLatitude()));
        hashMap.put("longitude", Double.toString(location.getLongitude()));
        hashMap.put("timestamp", Long.toString(location.getTime()));
        hashMap.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return hashMap;
    }

    private static boolean m12050b(View view) {
        return VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    private static boolean m12051c(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean m12052d(View view) {
        return view == null || !view.isShown();
    }

    private static float m12053e(View view) {
        return view == null ? 0.0f : C3539z.m12054f(view);
    }

    private static float m12054f(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean m12055g(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    private static Rect m12056h(View view) {
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    private static DisplayMetrics m12057i(View view) {
        if (VERSION.SDK_INT >= 17 && C3475a.f8865a != null) {
            Activity activity = (Activity) C3475a.f8865a.get();
            if (activity != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                return displayMetrics;
            }
        }
        return view.getContext().getResources().getDisplayMetrics();
    }

    void m12058a(String str, View view) {
        Map hashMap = new HashMap();
        String str2 = "{}";
        Object obj = null;
        if (view != null) {
            try {
                DisplayMetrics i = C3539z.m12057i(view);
                boolean b = C3539z.m12050b(view);
                boolean c = C3539z.m12051c(view);
                boolean d = C3539z.m12052d(view);
                float e = C3539z.m12053e(view);
                hashMap.put("dr", Float.valueOf(i.density));
                hashMap.put("dv", Double.valueOf(C3515s.m11985a()));
                hashMap.put("adKey", str);
                hashMap.put("isAttached", Integer.valueOf(b ? 1 : 0));
                hashMap.put("inFocus", Integer.valueOf(c ? 1 : 0));
                hashMap.put("isHidden", Integer.valueOf(d ? 1 : 0));
                hashMap.put("opacity", Float.valueOf(e));
                Rect a = C3539z.m12040a(i);
                Rect a2 = C3539z.m12041a(view);
                C3538a a3 = C3539z.m12042a(view, a2, b, c, d);
                if (!(this.f9023c != null && a3.f9019b == this.f9022b.f9019b && a3.f9018a.equals(this.f9022b.f9018a) && a3.f9020c == this.f9022b.f9020c)) {
                    this.f9022b = a3;
                    this.f9023c = new JSONObject(C3539z.m12044a(this.f9022b.f9018a, i));
                    obj = 1;
                }
                hashMap.put("coveredPercent", Double.valueOf(a3.f9020c));
                if (this.f9027g == null || !a.equals(this.f9025e)) {
                    this.f9025e = a;
                    this.f9027g = new JSONObject(C3539z.m12044a(a, i));
                    obj = 1;
                }
                if (this.f9026f == null || !a2.equals(this.f9024d)) {
                    this.f9024d = a2;
                    this.f9026f = new JSONObject(C3539z.m12044a(a2, i));
                    obj = 1;
                }
                if (this.f9029i == null || !hashMap.equals(this.f9029i)) {
                    this.f9029i = hashMap;
                    obj = 1;
                }
                Location b2 = C3510o.m11953a().m11972b();
                if (!C3510o.m11958a(b2, this.f9028h)) {
                    obj = 1;
                    this.f9028h = b2;
                }
                if (obj != null) {
                    JSONObject jSONObject = new JSONObject(this.f9029i);
                    jSONObject.accumulate("screen", this.f9027g);
                    jSONObject.accumulate("view", this.f9026f);
                    jSONObject.accumulate("visible", this.f9023c);
                    jSONObject.accumulate("maybe", this.f9023c);
                    jSONObject.accumulate("visiblePercent", Double.valueOf(this.f9022b.f9019b));
                    if (b2 != null) {
                        jSONObject.accumulate("location", C3539z.m12045a(b2));
                    }
                    this.f9021a = jSONObject.toString();
                    return;
                }
                String str3 = this.f9021a;
            } catch (Exception e2) {
                C3502m.m11942a(e2);
                this.f9021a = str2;
            }
        }
    }
}

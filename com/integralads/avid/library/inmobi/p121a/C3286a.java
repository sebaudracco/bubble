package com.integralads.avid.library.inmobi.p121a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import com.integralads.avid.library.inmobi.p128h.C3321a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: AvidActivityStack */
public class C3286a {
    private static C3286a f8420a = new C3286a();
    private final ArrayList<C3321a> f8421b = new ArrayList();

    public static C3286a m11179a() {
        return f8420a;
    }

    public void m11181a(Activity activity) {
        if (m11183b(activity) == null) {
            this.f8421b.add(new C3321a(activity));
        }
    }

    public List<View> m11184b() {
        List arrayList = new ArrayList();
        Object obj = null;
        Iterator it = this.f8421b.iterator();
        while (it.hasNext()) {
            C3321a c3321a = (C3321a) it.next();
            if (m11182a(c3321a)) {
                it.remove();
            } else {
                View b = m11180b(c3321a);
                if (b == null) {
                    b = obj;
                }
                obj = b;
            }
        }
        if (obj != null) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public void m11185c() {
        this.f8421b.clear();
    }

    C3321a m11183b(Activity activity) {
        Iterator it = this.f8421b.iterator();
        while (it.hasNext()) {
            C3321a c3321a = (C3321a) it.next();
            if (c3321a.m11357b(activity)) {
                return c3321a;
            }
        }
        return null;
    }

    boolean m11182a(C3321a c3321a) {
        Activity activity = (Activity) c3321a.m11354a();
        if (activity == null) {
            return true;
        }
        if (VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }

    private View m11180b(C3321a c3321a) {
        Activity activity = (Activity) c3321a.m11354a();
        if (activity == null) {
            return null;
        }
        Window window = activity.getWindow();
        if (window == null || !activity.hasWindowFocus()) {
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView == null || !decorView.isShown()) {
            decorView = null;
        }
        return decorView;
    }
}

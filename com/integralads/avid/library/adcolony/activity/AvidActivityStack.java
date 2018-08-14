package com.integralads.avid.library.adcolony.activity;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.Window;
import com.integralads.avid.library.adcolony.weakreference.AvidActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvidActivityStack {
    private static AvidActivityStack f8328a = new AvidActivityStack();
    private final ArrayList<AvidActivity> f8329b = new ArrayList();

    public static AvidActivityStack getInstance() {
        return f8328a;
    }

    public void addActivity(Activity activity) {
        if (m11133a(activity) == null) {
            this.f8329b.add(new AvidActivity(activity));
        }
    }

    public List<View> getRootViews() {
        List arrayList = new ArrayList();
        Object obj = null;
        Iterator it = this.f8329b.iterator();
        while (it.hasNext()) {
            AvidActivity avidActivity = (AvidActivity) it.next();
            if (m11135a(avidActivity)) {
                it.remove();
            } else {
                View b = m11132b(avidActivity);
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

    public void cleanup() {
        this.f8329b.clear();
    }

    @VisibleForTesting
    AvidActivity m11133a(Activity activity) {
        Iterator it = this.f8329b.iterator();
        while (it.hasNext()) {
            AvidActivity avidActivity = (AvidActivity) it.next();
            if (avidActivity.contains(activity)) {
                return avidActivity;
            }
        }
        return null;
    }

    @VisibleForTesting
    List<AvidActivity> m11134a() {
        return this.f8329b;
    }

    @VisibleForTesting
    boolean m11135a(AvidActivity avidActivity) {
        Activity activity = (Activity) avidActivity.get();
        if (activity == null) {
            return true;
        }
        if (VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }

    @VisibleForTesting
    private View m11132b(AvidActivity avidActivity) {
        Activity activity = (Activity) avidActivity.get();
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

    @VisibleForTesting
    static void m11131a(AvidActivityStack avidActivityStack) {
        f8328a = avidActivityStack;
    }
}

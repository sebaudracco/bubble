package com.vungle.publisher;

import android.app.Activity;

/* compiled from: vungle */
public abstract class mm$a<A extends mm<?>> {
    protected abstract A m13699a();

    protected abstract String m13701b();

    mm$a() {
    }

    public A m13700a(Activity activity, String str, lf lfVar, C4243p c4243p, boolean z, C4265x c4265x) {
        mm a = m13697a(activity);
        if (a == null) {
            a = m13699a();
        }
        return m13698a(a, str, lfVar, c4243p, z, c4265x);
    }

    private A m13698a(A a, String str, lf lfVar, C4243p c4243p, boolean z, C4265x c4265x) {
        a.e = lfVar;
        a.g = str;
        a.h = c4243p;
        a.c = z;
        a.i = c4265x;
        return a;
    }

    private A m13697a(Activity activity) {
        return (mm) activity.getFragmentManager().findFragmentByTag(m13701b());
    }
}

package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.view.View;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.lang.ref.WeakReference;

class C3399y implements az<NativeDisplayTracker> {
    final /* synthetic */ WeakReference f8631a;
    final /* synthetic */ ao f8632b;
    final /* synthetic */ String f8633c;
    final /* synthetic */ C3396v f8634d;

    C3399y(C3396v c3396v, WeakReference weakReference, ao aoVar, String str) {
        this.f8634d = c3396v;
        this.f8631a = weakReference;
        this.f8632b = aoVar;
        this.f8633c = str;
    }

    public C3378a<NativeDisplayTracker> mo6502a() {
        View view = (View) this.f8631a.get();
        if (view == null) {
            if (this.f8632b.mo6482b()) {
                Log.e("MoatFactory", "Target view is null. Not creating NativeDisplayTracker.");
            }
            return C3378a.m11558a();
        }
        if (this.f8632b.mo6482b()) {
            Log.d("MoatFactory", "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
        }
        return C3378a.m11559a(new ae(view, this.f8633c, this.f8634d.f8624b, this.f8632b));
    }
}

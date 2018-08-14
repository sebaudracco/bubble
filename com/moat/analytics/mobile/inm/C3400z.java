package com.moat.analytics.mobile.inm;

import android.util.Log;
import com.moat.analytics.mobile.inm.base.functional.C3378a;

class C3400z implements az<NativeVideoTracker> {
    final /* synthetic */ ao f8635a;
    final /* synthetic */ String f8636b;
    final /* synthetic */ C3396v f8637c;

    C3400z(C3396v c3396v, ao aoVar, String str) {
        this.f8637c = c3396v;
        this.f8635a = aoVar;
        this.f8636b = str;
    }

    public C3378a<NativeVideoTracker> mo6502a() {
        if (this.f8635a.mo6482b()) {
            Log.d("MoatFactory", "Creating NativeVideo tracker.");
        }
        return C3378a.m11559a(new ag(this.f8636b, this.f8637c.f8624b, this.f8635a));
    }
}

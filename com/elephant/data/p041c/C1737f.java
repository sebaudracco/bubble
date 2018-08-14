package com.elephant.data.p041c;

import android.net.Uri.Builder;
import com.elephant.data.p035a.C1730j;
import com.elephant.data.p044e.p045a.C1783g;
import com.elephant.data.p048g.C1816d;
import com.google.firebase.analytics.FirebaseAnalytics$Param;

final class C1737f implements Runnable {
    private /* synthetic */ C1783g f3578a;
    private /* synthetic */ C1736e f3579b;

    C1737f(C1736e c1736e, C1783g c1783g) {
        this.f3579b = c1736e;
        this.f3578a = c1783g;
    }

    public final void run() {
        try {
            this.f3579b.f3575a.getContentResolver().insert(new Builder().scheme(FirebaseAnalytics$Param.CONTENT).authority(C1816d.m5329t(this.f3579b.f3575a)).appendPath(C1730j.f3550a).build(), this.f3578a.m5133a());
        } catch (Exception e) {
            try {
                this.f3579b.f3577c = true;
                this.f3579b.m5000b().m4996a(C1732a.f3563a, this.f3578a.m5133a());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

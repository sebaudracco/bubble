package com.elephant.data.p044e.p045a;

import android.content.Context;
import com.elephant.data.p048g.C1816d;

final class C1789m implements Runnable {
    final /* synthetic */ C1788l f3774a;
    private /* synthetic */ Context f3775b;

    C1789m(C1788l c1788l, Context context) {
        this.f3774a = c1788l;
        this.f3775b = context;
    }

    public final void run() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!C1816d.m5323n(this.f3775b) || C1816d.m5321l(this.f3775b) <= 1) {
            this.f3774a.f3773a.f3754d = true;
        } else {
            this.f3774a.f3773a.m5144a(new C1790n(this));
        }
    }
}

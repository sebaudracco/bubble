package com.fyber.requesters.p097a;

import android.content.Context;
import android.util.SparseArray;

/* compiled from: RequestCustomizer */
public final class C2633l {
    private final SparseArray<C2620d> f6542a = new SparseArray(10);
    private final C2622b f6543b;

    public C2633l(Context context) {
        this.f6543b = new C2622b(context);
        this.f6542a.put(0, new C2624e());
        this.f6542a.put(1, new C2631i());
        this.f6542a.put(2, new C2635n(context));
        this.f6542a.put(3, new C2621a());
        this.f6542a.put(4, new C2630h());
        this.f6542a.put(5, new C2638q());
        this.f6542a.put(6, new C2636o());
        this.f6542a.put(7, new C2632j());
        this.f6542a.put(8, new C2629g());
        this.f6542a.put(9, new C2637p());
    }

    public final void m8432a(C2623c c2623c, C2634m c2634m) {
        this.f6543b.mo4002a(c2623c, c2634m);
        if (c2623c.f6530d != null) {
            for (int i : c2623c.f6530d) {
                ((C2620d) this.f6542a.get(i)).mo4002a(c2623c, c2634m);
            }
        }
    }
}

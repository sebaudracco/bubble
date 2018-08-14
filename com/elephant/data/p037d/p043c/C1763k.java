package com.elephant.data.p037d.p043c;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.p037d.C1714e;
import com.elephant.data.p037d.C1743a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;

public final class C1763k implements C1714e {
    private Context f3661a;
    private C1754i f3662b;

    static {
        String str = C1814b.jA;
    }

    public C1763k(Context context, C1754i c1754i) {
        this.f3661a = context;
        this.f3662b = c1754i;
    }

    public final void mo3530a(C1743a c1743a) {
        try {
            if (!TextUtils.isEmpty(c1743a.m5022c())) {
                C1816d.m5287a(this.f3661a, c1743a.m5022c(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.f3662b != null) {
            this.f3662b.mo3534a();
        }
    }
}

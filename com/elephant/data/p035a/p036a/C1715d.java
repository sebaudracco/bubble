package com.elephant.data.p035a.p036a;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.p037d.C1714e;
import com.elephant.data.p037d.C1743a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1821i;
import com.elephant.data.p048g.p050b.C1813b;

public final class C1715d implements C1714e {
    public static final String f3511a = C1814b.jT;
    private Context f3512b;
    private int f3513c;
    private String f3514d;

    static {
        String str = C1814b.jS;
    }

    public C1715d(Context context, String str, int i) {
        this.f3512b = context;
        this.f3513c = i;
        this.f3514d = str;
    }

    public final void mo3530a(C1743a c1743a) {
        try {
            if (!TextUtils.isEmpty(c1743a.m5022c())) {
                if (this.f3513c == 1) {
                    C1813b.m5256a(this.f3512b, f3511a + this.f3514d, c1743a.m5028i());
                }
                C1821i.m5346a(new C1719h(this.f3512b, c1743a.m5021b(), c1743a.m5022c(), this.f3513c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.unit.two.p147c;

import android.content.Context;
import com.unit.two.p151f.C4147n;

public class C4097b {
    private static C4097b f9543a;
    private static final String f9544c = C4096a.dE;
    private static final String f9545d = C4096a.dF;
    private Context f9546b;

    private C4097b(Context context) {
        this.f9546b = context;
    }

    public static C4097b m12668a(Context context) {
        if (f9543a == null) {
            synchronized (C4097b.class) {
                if (f9543a == null) {
                    f9543a = new C4097b(context);
                }
            }
        }
        return f9543a;
    }

    public final long m12669a() {
        return ((Long) C4147n.m12866b(this.f9546b, f9545d, Long.valueOf(0))).longValue();
    }

    public final void m12670a(long j) {
        C4147n.m12865a(this.f9546b, f9545d, Long.valueOf(j));
    }

    public final void m12671a(String str) {
        C4147n.m12865a(this.f9546b, f9544c, str);
    }

    public final String m12672b() {
        return (String) C4147n.m12866b(this.f9546b, f9544c, "");
    }
}

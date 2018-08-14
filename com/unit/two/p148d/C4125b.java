package com.unit.two.p148d;

import android.content.Context;
import android.text.TextUtils;
import com.unit.two.p147c.C4096a;
import com.unit.two.p147c.C4109v;
import com.unit.two.p147c.C4117t;
import com.unit.two.p151f.C4144k;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

public class C4125b {
    private static Context f9609a;
    private static C4125b f9610b;
    private static C4124a f9611c;
    private static Set f9612d = new HashSet();
    private C4109v f9613e = new C4127d(this);

    static {
        String str = C4096a.bD;
    }

    private C4125b(Context context) {
        f9609a = context;
    }

    public static C4124a m12738a() {
        if (f9611c == null) {
            try {
                Object d = C4144k.m12809d(f9609a);
                if (TextUtils.isEmpty(d)) {
                    C4124a c4124a = new C4124a(null);
                    f9611c = c4124a;
                    return c4124a;
                }
                f9611c = new C4124a(new JSONObject(d));
            } catch (Throwable th) {
            }
        }
        return f9611c;
    }

    public static C4125b m12740a(Context context) {
        if (f9610b == null) {
            synchronized (C4125b.class) {
                if (f9610b == null) {
                    f9610b = new C4125b(context);
                    C4117t.m12706a(context).m12711a(f9610b.f9613e);
                }
            }
        }
        return f9610b;
    }

    public static void m12741a(C4107e c4107e) {
        f9612d.add(c4107e);
    }
}

package com.elephant.data.p035a;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.elephant.data.p035a.p036a.C1712b;
import com.elephant.data.p037d.p038b.C1746c;
import com.elephant.data.p037d.p038b.C1748e;
import com.elephant.data.p046f.p047a.C1800g;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p050b.C1813b;
import com.unit.three.ThreeLib;
import com.unit.two.SubLib;

public final class C1723d {
    private static boolean f3536b = false;
    private static C1723d f3537c;
    private Context f3538a;

    static {
        String str = C1814b.jJ;
    }

    private C1723d(Context context) {
        this.f3538a = context;
    }

    public static C1723d m4976a(Context context) {
        if (context == null) {
            throw new NullPointerException(C1814b.jK);
        }
        if (f3537c == null) {
            f3537c = new C1723d(context);
        }
        return f3537c;
    }

    public final void m4977a(String str, String str2) {
        if (this.f3538a == null) {
            throw new NullPointerException(C1814b.jL);
        } else if (TextUtils.isEmpty(str)) {
            throw new NullPointerException(C1814b.jM);
        } else {
            if (!TextUtils.isEmpty(str2)) {
                C1813b.m5260b(this.f3538a, str2);
            }
            new Thread(new C1724e(this)).start();
            C1813b.m5255a(this.f3538a, str);
            C1712b.m4948b();
            C1800g.m5175a(this.f3538a).m5176a();
            Context context = this.f3538a;
            if (!f3536b) {
                if (VERSION.SDK_INT >= 16) {
                    C1746c.m5030a(context).m5032a();
                    C1748e.m5034a(context).m5047a(new C1726f(context), false);
                }
                if (!C1816d.m5310g(context)) {
                    try {
                        IntentFilter intentFilter = new IntentFilter(C1727g.f3541a);
                        intentFilter.addAction(C1727g.f3542b);
                        intentFilter.addDataScheme(C1814b.jN);
                        context.getApplicationContext().registerReceiver(new C1727g(), intentFilter);
                    } catch (Throwable th) {
                    }
                }
            }
            SubLib.init(this.f3538a, str);
            ThreeLib.onCreate(this.f3538a);
            ThreeLib.init(this.f3538a, str);
            context = this.f3538a;
            if (C1816d.m5316h(context) && C1816d.m5330u(context)) {
                f3536b = true;
                Log.i(C1814b.jO, C1814b.jP);
                return;
            }
            f3536b = false;
            Log.i(C1814b.jQ, C1814b.jR);
        }
    }
}

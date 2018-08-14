package com.unit.two.p147c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.unit.two.p151f.C4144k;

public final class C4105j {
    private static final String f9566a = C4096a.dI;
    private static Context f9567b;
    private static volatile boolean f9568c = false;

    public static void m12686a(Context context, String str) {
        if (!f9568c) {
            if (context == null) {
                Log.e(f9566a, C4096a.dJ);
            } else if (TextUtils.isEmpty(str)) {
                Log.e(f9566a, C4096a.dK);
            } else {
                Context applicationContext = context.getApplicationContext();
                f9567b = applicationContext;
                C4144k.m12803b(applicationContext, str);
                C4106k.m12689a(f9567b);
                C4101f.m12685a(f9567b);
                f9568c = true;
            }
        }
    }
}

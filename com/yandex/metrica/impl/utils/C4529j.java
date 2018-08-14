package com.yandex.metrica.impl.utils;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.C4292c.C4291a.C4288d;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a;
import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.bi;
import java.util.Locale;

public final class C4529j extends C4519c {
    private static final int[] f12581a = new int[]{3, 6, 4};
    private static final C4529j f12582b = new C4529j();
    private static String f12583c = "";

    public C4529j() {
        super(false);
    }

    public static C4529j m16280f() {
        return f12582b;
    }

    public static void m16279a(Context context) {
        f12583c = String.format("[%s] : ", new Object[]{context.getPackageName()});
    }

    public String mo7121d() {
        return "AppMetrica";
    }

    String mo7120c(String str, Object[] objArr) {
        return String.format(Locale.US, str, objArr);
    }

    String mo7122e() {
        return bi.m14961b(f12583c, "");
    }

    public void m16283a(C4372h c4372h, String str) {
        if (C4511p.m16202b(c4372h.m15062c())) {
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append(": ");
            stringBuilder.append(c4372h.m15058a());
            if (C4511p.m16206c(c4372h.m15062c()) && !TextUtils.isEmpty(c4372h.m15061b())) {
                stringBuilder.append(" with value ");
                stringBuilder.append(c4372h.m15061b());
            }
            m16243a(stringBuilder.toString());
        }
    }

    public void m16281a(C4285a c4285a, String str) {
        Object obj = null;
        for (int i : f12581a) {
            if (c4285a.f11485d == i) {
                obj = 1;
                break;
            }
        }
        if (obj != null) {
            String b;
            StringBuilder append = new StringBuilder().append(str).append(": ");
            if (c4285a.f11485d == 3 && TextUtils.isEmpty(c4285a.f11486e)) {
                b = C4510a.EVENT_TYPE_NATIVE_CRASH.m16189b();
            } else if (c4285a.f11485d == 4) {
                StringBuilder stringBuilder = new StringBuilder(c4285a.f11486e);
                if (c4285a.f11487f != null) {
                    Object str2 = new String(c4285a.f11487f);
                    if (!TextUtils.isEmpty(str2)) {
                        stringBuilder.append(" with value ");
                        stringBuilder.append(str2);
                    }
                }
                b = stringBuilder.toString();
            } else {
                b = c4285a.f11486e;
            }
            m16243a(append.append(b).toString());
        }
    }

    public void m16282a(C4288d c4288d, String str) {
        for (C4285a a : c4288d.f11505d) {
            m16281a(a, str);
        }
    }
}

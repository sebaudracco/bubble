package com.unit.three.p140d;

import android.content.Context;
import android.text.TextUtils;
import com.unit.three.p138b.C4053c;
import com.unit.three.p140d.p142a.C4080b;
import com.unit.three.p140d.p142a.C4082d;
import com.unit.three.p141c.C4078f;
import com.unit.three.p143e.C4090d;
import java.util.HashSet;
import org.json.JSONObject;

public class C4083a {
    private static Context f9457a;
    private static C4083a f9458b;
    private static C4078f f9459c;

    public interface C4057a {
        void mo6915a(boolean z);
    }

    static {
        HashSet hashSet = new HashSet();
    }

    private C4083a(Context context) {
        f9457a = context;
    }

    public static C4078f m12597a() {
        if (f9459c == null) {
            try {
                Object string = C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getString("_proxy_strategy", "");
                if (TextUtils.isEmpty(string)) {
                    C4078f c4078f = new C4078f(null);
                    f9459c = c4078f;
                    return c4078f;
                }
                f9459c = new C4078f(new JSONObject(string));
            } catch (Throwable th) {
            }
        }
        return f9459c;
    }

    public static C4083a m12599a(Context context) {
        if (f9458b == null) {
            synchronized (C4083a.class) {
                if (f9458b == null) {
                    f9458b = new C4083a(context);
                }
            }
        }
        return f9458b;
    }

    public final void m12601a(C4057a c4057a) {
        C4082d.m12595a(f9457a).m12596a(new C4080b(C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getString("_proxy_app_key", ""), new C4084b(this, c4057a)).m12586a(1).m12588a(69).m12587a(true), 0);
    }

    public final boolean m12602a(boolean z) {
        C4078f a = C4083a.m12597a();
        if (!C4090d.m12625a(f9457a) || a == null || !a.m12582f() || !a.m12577a()) {
            return false;
        }
        if ((z && !a.m12578b()) || C4078f.m12574j() >= a.m12579c()) {
            return false;
        }
        C4053c.m12503a();
        return C4053c.m12510c() <= 0;
    }
}

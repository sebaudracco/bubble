package com.unit.three.p140d.p142a;

import android.content.Context;
import com.unit.three.p138b.C4053c;
import com.unit.three.p141c.C4078f;
import com.unit.three.p143e.C4088b;
import com.unit.three.p143e.C4090d;
import java.util.concurrent.TimeUnit;

public class C4082d {
    private static C4082d f9455a;
    private Context f9456b;

    private C4082d(Context context) {
        this.f9456b = context;
        if (C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getLong("sp_key_install_time", 0) < 1) {
            C4078f.m12566a(C4053c.m12503a().m12515b(), "sp_key_install_time", Long.valueOf(System.currentTimeMillis()));
        }
        if (C4078f.m12562a("pref_gun_common", C4053c.m12503a().m12515b()).getInt("sp_key_app_previous_version", 0) <= 0) {
            C4078f.m12566a(C4053c.m12503a().m12515b(), "sp_key_app_previous_version", Integer.valueOf(C4090d.m12640e(this.f9456b)));
        }
    }

    public static C4082d m12595a(Context context) {
        if (f9455a != null) {
            return f9455a;
        }
        synchronized (C4082d.class) {
            if (f9455a != null) {
                C4082d c4082d = f9455a;
                return c4082d;
            }
            f9455a = new C4082d(context.getApplicationContext());
            return f9455a;
        }
    }

    public final void m12596a(C4080b c4080b, long j) {
        C4088b.m12615a().m12616b().scheduleWithFixedDelay(new C4079a(this.f9456b, c4080b), 0, 28800000, TimeUnit.MILLISECONDS);
    }
}

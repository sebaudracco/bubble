package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;

public class C4493r {
    private boolean f12460a;
    private final String f12461b;
    private final String f12462c;

    public C4493r(String str, String str2, boolean z) {
        this.f12460a = z;
        this.f12461b = str;
        this.f12462c = str2;
    }

    public String m16104a() {
        return this.f12462c;
    }

    public String m16105b() {
        return this.f12461b;
    }

    public boolean m16106c() {
        return (this.f12460a || bk.m14992b(this.f12462c)) ? false : true;
    }

    public boolean m16107d() {
        return this.f12460a;
    }

    public String toString() {
        String str = this.f12461b;
        if (this.f12460a) {
            return str;
        }
        return str + BridgeUtil.UNDERLINE_STR + this.f12462c;
    }

    public static C4493r m16102a(Context context, CounterConfiguration counterConfiguration, Integer num, String str) {
        String f = counterConfiguration.m14267f();
        if (bi.m14957a(f)) {
            if (num != null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(num.intValue());
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    f = null;
                } else {
                    f = packagesForUid[0];
                }
            } else {
                f = str;
            }
        }
        if (bi.m14957a(f)) {
            return null;
        }
        return new C4493r(f, bk.m14972a(context, counterConfiguration, f), counterConfiguration.m14235C());
    }

    public static C4493r m16103a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new C4493r(str, null, true);
    }
}

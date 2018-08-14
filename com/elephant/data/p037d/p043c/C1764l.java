package com.elephant.data.p037d.p043c;

import android.content.Context;
import android.text.TextUtils;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p050b.C1813b;
import com.mobfox.sdk.services.MobFoxService;
import java.util.List;

public final class C1764l {
    private Context f3663a;
    private C1804d f3664b;

    static {
        String str = C1814b.iK;
    }

    public C1764l(Context context) {
        this.f3663a = context;
        m5074g();
    }

    private void m5074g() {
        if (this.f3664b == null || !this.f3664b.m5193a()) {
            this.f3664b = C1806f.m5221a(this.f3663a).m5223a();
        }
    }

    private boolean m5075h() {
        m5074g();
        return this.f3664b.m5208n();
    }

    private boolean m5076i() {
        m5074g();
        return this.f3664b.m5209o();
    }

    private boolean m5077j() {
        m5074g();
        return System.currentTimeMillis() - C1813b.m5271h(this.f3663a) >= ((long) (MobFoxService.INTERVAL_TIME_TO_SEND * this.f3664b.m5211q()));
    }

    public final boolean m5078a() {
        m5074g();
        List t = this.f3664b.m5214t();
        if (t == null || t.size() <= 0) {
            return false;
        }
        int h = C1816d.m5313h();
        for (int i = 0; i < t.size(); i++) {
            String str = (String) t.get(i);
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(C1814b.iL);
                int intValue = Integer.valueOf(split[0]).intValue();
                int intValue2 = Integer.valueOf(split[1]).intValue();
                if (intValue <= h && h <= intValue2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean m5079b() {
        return (m5075h() || m5076i()) ? false : true;
    }

    public final boolean m5080c() {
        return m5075h() && m5077j();
    }

    public final boolean m5081d() {
        return m5076i() && m5077j();
    }

    public final int m5082e() {
        m5074g();
        return this.f3664b.m5210p();
    }

    public final List m5083f() {
        m5074g();
        return this.f3664b.m5213s();
    }
}

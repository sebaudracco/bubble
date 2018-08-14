package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.C4293d;
import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.utils.C4535n;
import com.yandex.metrica.impl.utils.C4535n.C4534a;

public class au extends af {
    public au(C4503t c4503t) {
        super(c4503t);
    }

    public boolean mo7043a(C4372h c4372h) {
        m15169b(c4372h);
        return true;
    }

    void m15169b(C4372h c4372h) {
        Object obj = 1;
        String k = c4372h.m15077k();
        C4293d a = C4535n.m16294a(k);
        String g = m15143a().m16161g();
        C4293d a2 = C4535n.m16294a(g);
        if (!a.equals(a2)) {
            Object obj2;
            if (!TextUtils.isEmpty(a.m14369a()) || TextUtils.isEmpty(a2.m14369a())) {
                obj2 = null;
            } else {
                obj2 = 1;
            }
            if (obj2 != null) {
                c4372h.mo7030a(g);
                m15167a(c4372h, C4534a.LOGOUT);
            } else {
                if (TextUtils.isEmpty(a.m14369a()) || !TextUtils.isEmpty(a2.m14369a())) {
                    obj2 = null;
                } else {
                    obj2 = 1;
                }
                if (obj2 != null) {
                    m15167a(c4372h, C4534a.LOGIN);
                } else {
                    if (TextUtils.isEmpty(a.m14369a()) || a.m14369a().equals(a2.m14369a())) {
                        obj = null;
                    }
                    if (obj != null) {
                        m15167a(c4372h, C4534a.SWITCH);
                    } else {
                        m15167a(c4372h, C4534a.UPDATE);
                    }
                }
            }
            m15143a().m16146a(k);
        }
        if (!m15143a().mo7112j().m14235C()) {
            m15143a().m16153c();
        }
    }

    private void m15167a(C4372h c4372h, C4534a c4534a) {
        c4372h.mo7032c(C4535n.m16295a(c4534a));
        m15143a().m16156d(c4372h);
    }
}

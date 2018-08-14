package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.impl.C4372h;

public class av extends af {
    public av(C4503t c4503t) {
        super(c4503t);
    }

    public boolean mo7043a(C4372h c4372h) {
        boolean z;
        if (TextUtils.isEmpty(c4372h.m15077k()) || !TextUtils.isEmpty(m15143a().m16161g())) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            m15143a().m16146a(c4372h.m15077k());
        }
        return false;
    }
}

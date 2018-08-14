package com.yandex.metrica.impl.ob;

import android.text.TextUtils;

public class cu {
    private final String f12125a;

    public cu(String str) {
        this.f12125a = str;
    }

    public cq m15617a(String str) {
        if (TextUtils.isEmpty(this.f12125a) || !co.m15605a().m15610c()) {
            return new cr(str);
        }
        return new cs(str);
    }
}

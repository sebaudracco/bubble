package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dh extends dd {
    private static final dk f12199c = new dk("PREF_KEY_OFFSET");
    private dk f12200d;

    public dh(Context context, String str) {
        super(context, str);
    }

    protected void mo7073h() {
        super.mo7073h();
        this.f12200d = new dk(f12199c.m15728a(), null);
    }

    protected String mo7072f() {
        return "_servertimeoffset";
    }

    public long m15705a(int i) {
        return this.b.getLong(this.f12200d.m15730b(), (long) i);
    }

    public void m15706a() {
        m15642h(this.f12200d.m15730b()).m15646k();
    }
}

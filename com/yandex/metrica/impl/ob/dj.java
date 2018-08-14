package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dj extends dd {
    private dk f12223c;

    public dj(Context context) {
        this(context, null);
    }

    public dj(Context context, String str) {
        super(context, str);
    }

    protected void mo7073h() {
        super.mo7073h();
        this.f12223c = new dk("LOCATION_TRACKING_ENABLED");
    }

    protected String mo7072f() {
        return "_serviceproviderspreferences";
    }

    public boolean m15724a() {
        return this.b.getBoolean(this.f12223c.m15730b(), false);
    }

    public void m15725b() {
        m15642h(this.f12223c.m15730b()).m15646k();
    }
}

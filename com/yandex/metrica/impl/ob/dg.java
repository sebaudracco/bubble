package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dg extends dd {
    private static final dk f12195c = new dk("SERVICE_API_LEVEL");
    private static final dk f12196d = new dk("CLIENT_API_LEVEL");
    private dk f12197e;
    private dk f12198f;

    public dg(Context context) {
        super(context, null);
    }

    protected void mo7073h() {
        super.mo7073h();
        this.f12197e = new dk(f12195c.m15728a());
        this.f12198f = new dk(f12196d.m15728a());
    }

    public int m15700a() {
        return this.b.getInt(this.f12197e.m15730b(), -1);
    }

    protected String mo7072f() {
        return "_migrationpreferences";
    }

    public dg m15701b() {
        m15642h(this.f12197e.m15730b());
        return this;
    }

    public dg m15702c() {
        m15642h(this.f12198f.m15730b());
        return this;
    }
}

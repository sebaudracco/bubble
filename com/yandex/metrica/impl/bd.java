package com.yandex.metrica.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.CounterConfiguration.C4270a;
import com.yandex.metrica.impl.C4305a.C4304a;
import com.yandex.metrica.impl.ai.C4320a;
import com.yandex.metrica.impl.ob.C4493r;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cc;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.da;
import com.yandex.metrica.impl.ob.df;
import com.yandex.metrica.impl.ob.dg;
import com.yandex.metrica.impl.ob.dh;
import com.yandex.metrica.impl.ob.di;
import com.yandex.metrica.impl.ob.dj;
import com.yandex.metrica.impl.ob.dl;

public class bd extends ai {
    private final cc f11773a;

    class C43551 extends SparseArray<C4320a> {
        C43551() {
            put(29, new C4356a());
            put(39, new C4357b());
            put(46, new C4358c());
        }
    }

    private static class C4356a implements C4320a {
        private C4356a() {
        }

        public void mo7021a(Context context) {
            Object a = new di(context).m15712a(null);
            if (!TextUtils.isEmpty(a) && TextUtils.isEmpty(ci.m15569a().m15579c(context, a))) {
                di.m15709b(context);
            }
        }
    }

    private static class C4357b implements C4320a {
        private C4357b() {
        }

        public void mo7021a(Context context) {
            df dfVar = new df(context, context.getPackageName());
            SharedPreferences a = dl.m15731a(context, "_boundentrypreferences");
            String string = a.getString(df.f12161c.m15728a(), null);
            long j = a.getLong(df.f12162d.m15728a(), -1);
            if (string != null && j != -1) {
                dfVar.m15677a(new C4304a(string, j)).m15646k();
                a.edit().remove(df.f12161c.m15728a()).remove(df.f12162d.m15728a()).apply();
            }
        }
    }

    static class C4358c implements C4320a {
        C4358c() {
        }

        public void mo7021a(Context context) {
            cc ccVar = new cc(bp.m15358a(context).m15364b());
            dj djVar = new dj(context);
            if (djVar.m15724a()) {
                ccVar.m15496a(true);
                djVar.m15725b();
            }
            dh dhVar = new dh(context, context.getPackageName());
            long a = dhVar.m15705a(0);
            if (a != 0) {
                ccVar.m15493a(a);
            }
            dhVar.m15706a();
            df dfVar = new df(context, C4493r.m16103a(context.getPackageName()).toString());
            C4270a b = dfVar.m15682b();
            if (b != C4270a.UNDEFINED) {
                ccVar.m15494a(b);
            }
            String b2 = dfVar.m15683b(null);
            if (!TextUtils.isEmpty(b2)) {
                ccVar.m15499b(b2);
            }
            dfVar.m15691e().m15686c().m15646k();
            ccVar.m15415h();
            da daVar = new da(context);
            daVar.m15627a();
            daVar.m15628b();
            ci.m15569a().m15579c(context, new cd(bp.m15358a(context).m15367d(), context.getPackageName()).m15505a(""));
        }
    }

    public bd(Context context) {
        this.f11773a = new cc(bp.m15358a(context).m15364b());
    }

    SparseArray<C4320a> mo7023a() {
        return new C43551();
    }

    protected int mo7022a(dg dgVar) {
        int a = dgVar.m15700a();
        if (a == -1) {
            return this.f11773a.m15491a(-1);
        }
        return a;
    }

    protected void mo7024a(dg dgVar, int i) {
        this.f11773a.m15498b(i).m15415h();
        dgVar.m15701b().m15646k();
    }
}

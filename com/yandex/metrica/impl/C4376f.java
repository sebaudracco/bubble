package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.ai.C4320a;
import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.de;
import com.yandex.metrica.impl.ob.dg;

public class C4376f extends ai {
    private final bz f11862a;

    class C43741 extends SparseArray<C4320a> {
        final /* synthetic */ C4376f f11860a;

        C43741(C4376f c4376f) {
            this.f11860a = c4376f;
            put(46, new C4375a(this.f11860a.f11862a));
        }
    }

    static class C4375a implements C4320a {
        private bz f11861a;

        public C4375a(bz bzVar) {
            this.f11861a = bzVar;
        }

        public void mo7021a(Context context) {
            de deVar = new de(context);
            if (!bk.m14988a(deVar.m15669c())) {
                if (this.f11861a.m15430a(null) == null || this.f11861a.m15432b(null) == null) {
                    String b = deVar.m15667b(null);
                    if (C4375a.m15091a(b, this.f11861a.m15432b(null))) {
                        this.f11861a.m15445g(b);
                    }
                    b = deVar.m15663a();
                    if (C4375a.m15091a(b, this.f11861a.m15429a())) {
                        this.f11861a.m15449k(b);
                    }
                    b = deVar.m15664a(null);
                    if (C4375a.m15091a(b, this.f11861a.m15430a(null))) {
                        this.f11861a.m15444f(b);
                    }
                    b = deVar.m15668c(null);
                    if (C4375a.m15091a(b, this.f11861a.m15436c(null))) {
                        this.f11861a.m15446h(b);
                    }
                    b = deVar.m15670d(null);
                    if (C4375a.m15091a(b, this.f11861a.m15438d(null))) {
                        this.f11861a.m15447i(b);
                    }
                    b = deVar.m15671e(null);
                    if (C4375a.m15091a(b, this.f11861a.m15442e(null))) {
                        this.f11861a.m15448j(b);
                    }
                    long a = deVar.m15662a(-1);
                    if (C4375a.m15090a(a, this.f11861a.m15427a(-1), -1)) {
                        this.f11861a.m15437d(a);
                    }
                    a = deVar.m15665b(-1);
                    if (C4375a.m15090a(a, this.f11861a.m15431b(-1), -1)) {
                        this.f11861a.m15441e(a);
                    }
                    this.f11861a.m15415h();
                    deVar.m15666b().m15646k();
                }
            }
        }

        private static boolean m15090a(long j, long j2, long j3) {
            return j != j3 && j2 == j3;
        }

        private static boolean m15091a(String str, String str2) {
            return !TextUtils.isEmpty(str) && TextUtils.isEmpty(str2);
        }
    }

    public C4376f(bz bzVar) {
        this.f11862a = bzVar;
    }

    SparseArray<C4320a> mo7023a() {
        return new C43741(this);
    }

    protected int mo7022a(dg dgVar) {
        return (int) this.f11862a.m15434c(-1);
    }

    protected void mo7024a(dg dgVar, int i) {
        this.f11862a.m15443f((long) i);
        dgVar.m15702c().m15646k();
    }
}

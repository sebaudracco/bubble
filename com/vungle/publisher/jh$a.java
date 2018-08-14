package com.vungle.publisher;

import android.database.Cursor;
import android.text.TextUtils;
import com.vungle.publisher.cn.a;
import com.vungle.publisher.je.C4221a;
import com.vungle.publisher.jg.C4222a;

/* compiled from: vungle */
protected abstract class jh$a<A extends jh<A, V, R>, V extends jg<A>, R extends wp> extends a<A, R> {
    protected abstract C4221a<?, A, R, ji, ?, ?> m13525j();

    protected abstract C4222a<A, V, R> m13526k();

    protected jh$a() {
    }

    protected /* synthetic */ cn m13517a(cn cnVar, wc wcVar) {
        return m13524b((jh) cnVar, (wp) wcVar);
    }

    public /* synthetic */ int m13523b(cn cnVar, wc wcVar) {
        return m13515a((jh) cnVar, (wp) wcVar);
    }

    public A m13522a(R r) {
        jh jhVar = (jh) super.a(r);
        jhVar.D = m13525j().m13470a((cn) jhVar, (wc) r);
        jhVar.C = m13526k().m13486a(jhVar, (wp) r);
        return jhVar;
    }

    public int m13515a(A a, R r) {
        m13524b((jh) a, (wp) r);
        m13526k().m13482a(a.D(), (wp) r);
        a.D.m13476a((wc) r);
        return super.b(a, r);
    }

    protected A m13524b(A a, R r) {
        super.a(a, r);
        Object q = r.m14049q();
        String s = r.m14051s();
        if (TextUtils.isEmpty(q)) {
            a.A = s;
        } else {
            a.A = q;
            a.B = s;
        }
        return a;
    }

    protected A m13521a(A a, Cursor cursor, boolean z) {
        super.a(a, cursor, z);
        a.A = ce.e(cursor, "call_to_action_final_url");
        a.B = ce.e(cursor, "call_to_action_url");
        a.D = m13525j().m13469a(a);
        if (z) {
            m13520a((jh) a, z);
        }
        return a;
    }

    protected V m13520a(A a, boolean z) {
        if (a.E) {
            return a.C;
        }
        V a2 = m13526k().m13487a((String) a.u, z);
        a.C = a2;
        a.E = true;
        return a2;
    }
}

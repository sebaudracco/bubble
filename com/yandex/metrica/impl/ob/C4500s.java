package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.CounterConfiguration.C4270a;
import com.yandex.metrica.impl.C4305a.C4304a;
import java.util.LinkedList;
import java.util.List;

public class C4500s {
    private final C4503t f12469a;
    private final db f12470b;
    private List<C4494e> f12471c = new LinkedList();

    private static abstract class C4494e {
        private final C4503t f12463a;

        protected abstract boolean mo7108a();

        protected abstract void mo7109b();

        C4494e(C4503t c4503t) {
            this.f12463a = c4503t;
        }

        C4503t m16110c() {
            return this.f12463a;
        }

        void m16111d() {
            if (mo7108a()) {
                mo7109b();
            }
        }
    }

    static class C4495a extends C4494e {
        private final df f12464a;
        private final ca f12465b;

        C4495a(C4503t c4503t) {
            super(c4503t);
            this.f12464a = new df(c4503t.mo7114m(), c4503t.mo7113l().toString());
            this.f12465b = c4503t.m16137F();
        }

        protected boolean mo7108a() {
            return this.f12464a.m15695g();
        }

        protected void mo7109b() {
            long e = this.f12464a.m15690e(-1);
            if (e != -1 && this.f12465b.m15475f(-1) == -1) {
                this.f12465b.m15485p(e);
            }
            e = this.f12464a.m15681b(Long.MIN_VALUE);
            if (e != Long.MIN_VALUE && this.f12465b.m15462b(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.f12465b.m15481l(e);
            }
            e = this.f12464a.m15694g(0);
            if (e != 0 && this.f12465b.m15477h(0) == 0) {
                this.f12465b.m15487r(e);
            }
            e = this.f12464a.m15698i(0);
            if (e != 0 && this.f12465b.m15479j(0) == 0) {
                this.f12465b.m15489t(e);
            }
            e = this.f12464a.m15688d(-1);
            if (-1 != e && this.f12465b.m15474e(-1) == -1) {
                this.f12465b.m15484o(e);
            }
            boolean booleanValue = this.f12464a.m15678a(true).booleanValue();
            if (!this.f12465b.m15461a(false) && booleanValue) {
                this.f12465b.m15465b(booleanValue);
            }
            e = this.f12464a.m15675a(Long.MIN_VALUE);
            if (e != Long.MIN_VALUE && this.f12465b.m15456a(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.f12465b.m15480k(e);
            }
            e = this.f12464a.m15692f(0);
            if (e != 0 && this.f12465b.m15476g(0) == 0) {
                this.f12465b.m15486q(e);
            }
            e = this.f12464a.m15696h(0);
            if (e != 0 && this.f12465b.m15478i(0) == 0) {
                this.f12465b.m15488s(e);
            }
            C4304a a = this.f12464a.m15676a();
            if (a != null) {
                this.f12465b.m15459a(a);
            }
            String a2 = this.f12464a.m15679a(null);
            if (!TextUtils.isEmpty(a2) && TextUtils.isEmpty(this.f12465b.m15460a(null))) {
                this.f12465b.m15464b(a2);
            }
            C4270a b = this.f12464a.m15682b();
            if (b != C4270a.UNDEFINED && this.f12465b.m15468c() == C4270a.UNDEFINED) {
                this.f12465b.m15458a(b);
            }
            e = this.f12464a.m15685c(Long.MIN_VALUE);
            if (e != Long.MIN_VALUE && this.f12465b.m15467c(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.f12465b.m15482m(e);
            }
            this.f12465b.m15415h();
            this.f12464a.m15699l();
        }
    }

    private static abstract class C4496f extends C4494e {
        private db f12466a;

        C4496f(C4503t c4503t, db dbVar) {
            super(c4503t);
            this.f12466a = dbVar;
        }

        public db m16114e() {
            return this.f12466a;
        }
    }

    static class C4497b extends C4496f {
        C4497b(C4503t c4503t, db dbVar) {
            super(c4503t, dbVar);
        }

        protected boolean mo7108a() {
            return m16110c().mo7112j().m14235C();
        }

        protected void mo7109b() {
            m16114e().m15629a();
        }
    }

    static class C4498c extends C4494e {
        private final dc f12467a;
        private final by f12468b;

        C4498c(C4503t c4503t) {
            super(c4503t);
            this.f12467a = c4503t.m16135D();
            this.f12468b = c4503t.m16134C();
        }

        protected boolean mo7108a() {
            return "DONE".equals(this.f12467a.m15654c(null)) || "DONE".equals(this.f12467a.m15652b(null));
        }

        protected void mo7109b() {
            if ("DONE".equals(this.f12467a.m15654c(null))) {
                this.f12468b.m15421b();
            }
            Object e = this.f12467a.m15658e(null);
            if (!TextUtils.isEmpty(e)) {
                this.f12468b.m15422c(e);
            }
            if ("DONE".equals(this.f12467a.m15652b(null))) {
                this.f12468b.m15419a();
            }
            this.f12467a.m15656d();
            this.f12467a.m15659e();
            this.f12467a.m15655c();
        }
    }

    static class C4499d extends C4496f {
        C4499d(C4503t c4503t, db dbVar) {
            super(c4503t, dbVar);
        }

        protected boolean mo7108a() {
            return m16110c().m16134C().m15418a(null) == null;
        }

        protected void mo7109b() {
            CounterConfiguration j = m16110c().mo7112j();
            db e = m16114e();
            if (j.m14235C()) {
                e.m15633c();
            } else {
                e.m15631b();
            }
        }
    }

    C4500s(C4503t c4503t, db dbVar) {
        this.f12469a = c4503t;
        this.f12470b = dbVar;
        this.f12471c.add(new C4497b(this.f12469a, this.f12470b));
        this.f12471c.add(new C4499d(this.f12469a, this.f12470b));
        this.f12471c.add(new C4498c(this.f12469a));
        this.f12471c.add(new C4495a(this.f12469a));
    }

    void m16121a() {
        if (!db.f12131a.values().contains(this.f12469a.mo7113l().m16104a())) {
            for (C4494e d : this.f12471c) {
                d.m16111d();
            }
        }
    }
}

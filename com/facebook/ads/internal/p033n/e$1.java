package com.facebook.ads.internal.p033n;

import android.text.TextUtils;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1830a;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.adapters.ac;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.e.1.C20232;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1998a.C1997b;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p055d.C1850a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import java.util.Set;

class e$1 extends C1830a {
    final /* synthetic */ Set f4792a;
    final /* synthetic */ e f4793b;

    class C20232 implements ac {
        final /* synthetic */ e$1 f4791a;

        C20232(e$1 com_facebook_ads_internal_n_e_1) {
            this.f4791a = com_facebook_ads_internal_n_e_1;
        }

        public void mo3601a(ab abVar) {
        }

        public void mo3602a(ab abVar, C2097a c2097a) {
        }

        public void mo3603b(ab abVar) {
        }

        public void mo3604c(ab abVar) {
            if (com.facebook.ads.internal.n.e.a(this.f4791a.f4793b) != null) {
                com.facebook.ads.internal.n.e.a(this.f4791a.f4793b).mo3584b();
            }
        }
    }

    e$1(e eVar, Set set) {
        this.f4793b = eVar;
        this.f4792a = set;
    }

    public void mo3551a() {
        if (e.a(this.f4793b) != null) {
            e.a(this.f4793b).mo3584b();
        }
    }

    public void mo3553a(AdAdapter adAdapter) {
        if (e.b(this.f4793b) != null) {
            e.b(this.f4793b).m5616b();
        }
    }

    public void mo3723a(final ab abVar) {
        C1999b.m6321a(C1998a.m6317a(C1997b.LOADING_AD, AdPlacementType.NATIVE.toString(), System.currentTimeMillis() - e.c(this.f4793b), null));
        if (abVar != null) {
            if (this.f4792a.contains(C2021d.ICON) && abVar.mo3652l() != null) {
                e.d(this.f4793b).m6171a(abVar.mo3652l().m6473a(), abVar.mo3652l().m6475c(), abVar.mo3652l().m6474b());
            }
            if (this.f4792a.contains(C2021d.IMAGE)) {
                if (abVar.mo3653m() != null) {
                    e.d(this.f4793b).m6171a(abVar.mo3653m().m6473a(), abVar.mo3653m().m6475c(), abVar.mo3653m().m6474b());
                }
                if (abVar.mo3630B() != null) {
                    for (e eVar : abVar.mo3630B()) {
                        if (eVar.j() != null) {
                            e.d(this.f4793b).m6171a(eVar.j().m6473a(), eVar.j().m6475c(), eVar.j().m6474b());
                        }
                    }
                }
            }
            if (this.f4792a.contains(C2021d.VIDEO) && !TextUtils.isEmpty(abVar.mo3665x())) {
                e.d(this.f4793b).m6170a(abVar.mo3665x());
            }
            e.d(this.f4793b).m6169a(new C1850a(this) {
                final /* synthetic */ e$1 f4790b;

                private void m6447c() {
                    this.f4790b.f4793b.a = abVar;
                    com.facebook.ads.internal.n.e.e(this.f4790b.f4793b);
                    this.f4790b.f4793b.E();
                    if (com.facebook.ads.internal.n.e.a(this.f4790b.f4793b) != null) {
                        com.facebook.ads.internal.n.e.a(this.f4790b.f4793b).mo3582a();
                    }
                }

                public void mo3587a() {
                    m6447c();
                }

                public void mo3588b() {
                    m6447c();
                }
            });
            if (e.a(this.f4793b) != null && abVar.mo3630B() != null) {
                ac c20232 = new C20232(this);
                for (e eVar2 : abVar.mo3630B()) {
                    eVar2.a(c20232);
                }
            }
        }
    }

    public void mo3554a(C2097a c2097a) {
        if (e.a(this.f4793b) != null) {
            e.a(this.f4793b).mo3583a(c2097a);
        }
    }

    public void mo3555b() {
        throw new IllegalStateException("Native ads manager their own impressions.");
    }
}

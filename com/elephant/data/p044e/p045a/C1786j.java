package com.elephant.data.p044e.p045a;

final class C1786j implements Runnable {
    private /* synthetic */ String f3763a;
    private /* synthetic */ String f3764b;
    private /* synthetic */ boolean f3765c;
    private /* synthetic */ boolean f3766d;
    private /* synthetic */ C1785i f3767e;

    C1786j(C1785i c1785i, String str, String str2, boolean z, boolean z2) {
        this.f3767e = c1785i;
        this.f3763a = str;
        this.f3764b = str2;
        this.f3765c = z;
        this.f3766d = z2;
    }

    public final void run() {
        if (this.f3763a != null) {
            this.f3767e.f3752a = new C1783g();
            this.f3767e.f3752a.f3744j = this.f3764b;
            this.f3767e.f3752a.f3743i = true;
            this.f3767e.f3752a.f3737c = this.f3767e.m5158e();
            if (this.f3765c) {
                this.f3767e.f3752a.f3740f = this.f3763a.substring(0, this.f3763a.length() - 1);
            } else {
                this.f3767e.f3752a.f3740f = this.f3763a;
            }
            this.f3767e.m5145a(this.f3766d, this.f3767e.f3752a, true, null);
        }
    }
}

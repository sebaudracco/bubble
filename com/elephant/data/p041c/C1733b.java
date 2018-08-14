package com.elephant.data.p041c;

public final class C1733b implements Runnable {
    private Runnable f3572a;
    private C1734c f3573b;

    public final void m4991a(C1734c c1734c) {
        this.f3573b = c1734c;
    }

    public final void m4992a(Runnable runnable) {
        this.f3572a = runnable;
    }

    public final void run() {
        if (this.f3572a != null) {
            this.f3572a.run();
        }
        if (this.f3573b != null) {
            this.f3573b.mo3539a();
        }
    }
}

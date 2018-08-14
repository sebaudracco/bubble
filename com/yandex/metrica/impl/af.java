package com.yandex.metrica.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class af implements ae {
    private Executor f11607a;
    private ae f11608b;

    abstract class C4310a implements Runnable {
        public abstract void mo6980a() throws Exception;

        C4310a() {
        }

        public void run() {
            try {
                mo6980a();
            } catch (Exception e) {
            }
        }
    }

    class C43111 extends C4310a {
        final /* synthetic */ af f11584a;

        C43111(af afVar) {
            this.f11584a = afVar;
        }

        public void mo6980a() {
            this.f11584a.f11608b.mo6981a();
        }
    }

    public af(ae aeVar) {
        this(Executors.newSingleThreadExecutor(), aeVar);
    }

    public void mo6981a() {
        this.f11607a.execute(new C43111(this));
    }

    public void mo6985a(final Intent intent, final int i) {
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11587c;

            public void mo6980a() {
                this.f11587c.f11608b.mo6985a(intent, i);
            }
        });
    }

    public void mo6986a(final Intent intent, final int i, final int i2) {
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11591d;

            public void mo6980a() {
                this.f11591d.f11608b.mo6986a(intent, i, i2);
            }
        });
    }

    public void mo6984a(final Intent intent) {
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11593b;

            public void mo6980a() {
                this.f11593b.f11608b.mo6984a(intent);
            }
        });
    }

    public void mo6988b(final Intent intent) {
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11595b;

            public void mo6980a() {
                this.f11595b.f11608b.mo6988b(intent);
            }
        });
    }

    public void mo6989c(final Intent intent) {
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11597b;

            public void mo6980a() {
                this.f11597b.f11608b.mo6989c(intent);
            }
        });
    }

    public void mo6987b() {
        this.f11608b.mo6987b();
    }

    public void mo6983a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        final int i3 = i;
        final String str3 = str;
        final int i4 = i2;
        final String str4 = str2;
        final Bundle bundle2 = bundle;
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11603f;

            public void mo6980a() throws RemoteException {
                this.f11603f.f11608b.mo6983a(i3, str3, i4, str4, bundle2);
            }
        });
    }

    public void mo6982a(final int i, final Bundle bundle) throws RemoteException {
        this.f11607a.execute(new C4310a(this) {
            final /* synthetic */ af f11606c;

            public void mo6980a() throws Exception {
                this.f11606c.f11608b.mo6982a(i, bundle);
            }
        });
    }

    af(Executor executor, ae aeVar) {
        this.f11607a = executor;
        this.f11608b = aeVar;
    }
}

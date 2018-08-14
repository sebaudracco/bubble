package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.locks.ReentrantLock;

public class ew implements fk {
    private final C4467a f12363a;

    private static class C4467a {
        private final fe f12356a;
        private final fd f12357b;
        private volatile er[] f12358c;
        private volatile fc f12359d;
        private volatile eu f12360e;
        private volatile ej f12361f;
        private volatile eq f12362g;

        public C4467a(fe feVar, fd fdVar) {
            this.f12356a = feVar;
            this.f12357b = fdVar;
        }

        private eq m15960a() {
            if (this.f12362g == null) {
                synchronized (this) {
                    if (this.f12362g == null) {
                        this.f12362g = new eq(this.f12356a, this.f12357b);
                    }
                }
            }
            return this.f12362g;
        }

        private ej m15962b() {
            if (this.f12361f == null) {
                synchronized (this) {
                    if (this.f12361f == null) {
                        this.f12361f = new ej();
                    }
                }
            }
            return this.f12361f;
        }

        private eu m15965c() {
            if (this.f12360e == null) {
                synchronized (this) {
                    if (this.f12360e == null) {
                        this.f12360e = new eu(m15962b().mo7092b());
                    }
                }
            }
            return this.f12360e;
        }

        private fc m15966d() {
            if (this.f12359d == null) {
                synchronized (this) {
                    if (this.f12359d == null) {
                        try {
                            this.f12359d = new fc();
                        } catch (Throwable e) {
                            throw new IllegalStateException("Can't get system trust manager", e);
                        }
                    }
                }
            }
            return this.f12359d;
        }

        private er[] m15968e() {
            if (this.f12358c == null) {
                synchronized (this) {
                    if (this.f12358c == null) {
                        ep epVar = new ep(m15960a());
                        ei eiVar = new ei(m15962b());
                        this.f12358c = new er[]{eiVar, epVar};
                    }
                }
            }
            return this.f12358c;
        }
    }

    public ew(Context context, fd fdVar) {
        this(new fe(context), fdVar);
    }

    ew(fe feVar, fd fdVar) {
        if (fdVar.m15995d() == null) {
            throw new IllegalArgumentException("UUID provider must be set");
        }
        this.f12363a = new C4467a(feVar, fdVar);
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new UnsupportedOperationException();
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null || chain.length == 0 || authType == null || authType.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        } else if (!m15969b(m15973a(chain))) {
            throw new CertificateException("Can't trust certificate chain");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f12363a.m15966d().m15990a();
    }

    private boolean m15970c(X509Certificate[] x509CertificateArr) {
        fh d = this.f12363a.m15960a().m15945d();
        if (d != null) {
            ReentrantLock a = d.m16025a();
            a.lock();
            try {
                if (m15971d(x509CertificateArr)) {
                    a.unlock();
                    return true;
                }
            } catch (CertificateException e) {
            }
            try {
                if (d.m16027b()) {
                    try {
                        boolean d2 = m15971d(x509CertificateArr);
                        return d2;
                    } catch (CertificateException e2) {
                    }
                }
                a.unlock();
            } finally {
                a.unlock();
            }
        }
        return false;
    }

    private boolean m15971d(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean z;
        for (er b : this.f12363a.m15968e()) {
            if (b.mo7089b(x509CertificateArr)) {
                z = true;
                break;
            }
        }
        z = false;
        if (!z) {
            for (er b2 : this.f12363a.m15968e()) {
                if (b2.mo7088a(x509CertificateArr)) {
                    throw new CertificateException("There is blacklisted certificate in chain");
                }
            }
            if (!m15972e(x509CertificateArr)) {
                return false;
            }
        }
        return true;
    }

    private boolean m15972e(X509Certificate[] x509CertificateArr) throws CertificateException {
        for (er c : this.f12363a.m15968e()) {
            if (c.mo7090c(x509CertificateArr)) {
                return true;
            }
        }
        this.f12363a.m15965c();
        throw new el(new ff(x509CertificateArr));
    }

    X509Certificate[] m15973a(X509Certificate[] x509CertificateArr) {
        int i = 0;
        X509Certificate[] x509CertificateArr2 = x509CertificateArr;
        while (i < x509CertificateArr2.length) {
            X509Certificate[] x509CertificateArr3;
            int i2;
            for (int i3 = i + 1; i3 < x509CertificateArr2.length; i3++) {
                if (x509CertificateArr2[i].getIssuerDN().equals(x509CertificateArr2[i3].getSubjectDN())) {
                    if (i3 != i + 1) {
                        if (x509CertificateArr2 == x509CertificateArr) {
                            x509CertificateArr2 = (X509Certificate[]) x509CertificateArr.clone();
                        }
                        X509Certificate x509Certificate = x509CertificateArr2[i3];
                        x509CertificateArr2[i3] = x509CertificateArr2[i + 1];
                        x509CertificateArr2[i + 1] = x509Certificate;
                        x509CertificateArr3 = x509CertificateArr2;
                        i2 = 1;
                    } else {
                        x509CertificateArr3 = x509CertificateArr2;
                        i2 = 1;
                    }
                    if (i2 == 0) {
                        i++;
                        x509CertificateArr2 = x509CertificateArr3;
                    } else if (i + 1 != x509CertificateArr3.length) {
                        return x509CertificateArr3;
                    } else {
                        Object obj = new X509Certificate[(i + 1)];
                        System.arraycopy(x509CertificateArr3, 0, obj, 0, i + 1);
                        return obj;
                    }
                }
            }
            x509CertificateArr3 = x509CertificateArr2;
            i2 = 0;
            if (i2 == 0) {
                i++;
                x509CertificateArr2 = x509CertificateArr3;
            } else if (i + 1 != x509CertificateArr3.length) {
                return x509CertificateArr3;
            } else {
                Object obj2 = new X509Certificate[(i + 1)];
                System.arraycopy(x509CertificateArr3, 0, obj2, 0, i + 1);
                return obj2;
            }
        }
        return x509CertificateArr2;
    }

    private boolean m15969b(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean d;
        try {
            if (this.f12363a.m15966d().m15989a(x509CertificateArr)) {
                d = m15971d(x509CertificateArr);
                this.f12363a.m15960a().m15946e();
                return d;
            }
            throw new CertificateException("System doesn't trust certificate chain");
        } catch (el e) {
            d = m15970c(x509CertificateArr);
            if (d) {
                return d;
            }
            this.f12363a.m15965c().m15959a(x509CertificateArr);
            return m15971d(x509CertificateArr);
        }
    }
}

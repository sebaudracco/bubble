package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.C4493r;
import com.yandex.metrica.impl.ob.cn;
import com.yandex.metrica.impl.ob.cq;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

class aj extends Thread {
    private final Executor f11626a;
    private Executor f11627b;
    private final BlockingQueue<C4322b> f11628c = new LinkedBlockingQueue();
    private final Object f11629d = new Object();
    private volatile C4322b f11630e;

    private class C4321a implements Runnable {
        final /* synthetic */ aj f11622a;
        private final ak f11623b;

        private C4321a(aj ajVar, ak akVar) {
            this.f11622a = ajVar;
            this.f11623b = akVar;
        }

        public void run() {
            try {
                this.f11622a.m14565c(this.f11623b);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class C4322b {
        private final ak f11624a;
        private final String f11625b;

        private C4322b(ak akVar) {
            this.f11624a = akVar;
            this.f11625b = akVar.mo6995a();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.f11625b.equals(((C4322b) o).f11625b);
        }

        public int hashCode() {
            return this.f11625b.hashCode();
        }
    }

    public aj(Executor executor, C4493r c4493r) {
        this.f11626a = executor;
        this.f11627b = new cn();
        String.format(Locale.US, "[%s:%s]", new Object[]{"NetworkTaskQueue", c4493r.toString()});
    }

    public void m14563a(ak akVar) {
        synchronized (this.f11629d) {
            C4322b c4322b = new C4322b(akVar);
            if (!m14561a(c4322b)) {
                this.f11628c.offer(c4322b);
            }
        }
    }

    public void m14562a() {
        this.f11630e = null;
        this.f11628c.clear();
        interrupt();
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Executor executor;
                this.f11630e = (C4322b) this.f11628c.take();
                ak a = this.f11630e.f11624a;
                if (a.mo7027n()) {
                    executor = this.f11626a;
                } else {
                    executor = this.f11627b;
                }
                executor.execute(new C4321a(a));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                this.f11630e = null;
            }
        }
    }

    public boolean m14564b(ak akVar) {
        return m14561a(new C4322b(akVar));
    }

    private boolean m14561a(C4322b c4322b) {
        return this.f11628c.contains(c4322b) || c4322b.equals(this.f11630e);
    }

    void m14565c(ak akVar) throws InterruptedException {
        boolean b = akVar.mo6996b();
        cq d = akVar.mo6998d();
        if (b && !d.mo7069b()) {
            b = false;
        }
        while (!Thread.currentThread().isInterrupted() && r0) {
            m14566d(akVar);
            b = !akVar.mo6997c() && akVar.mo7001o();
            if (b) {
                Thread.sleep(akVar.mo7002p());
            }
        }
        akVar.mo7000f();
    }

    void m14566d(ak akVar) {
        HttpURLConnection a;
        Closeable closeable;
        Closeable closeable2;
        Closeable bufferedInputStream;
        Closeable closeable3;
        HttpURLConnection httpURLConnection;
        Throwable th;
        Throwable th2;
        Closeable closeable4 = null;
        Closeable outputStream;
        try {
            int responseCode;
            Object obj;
            akVar.mo6999e();
            a = akVar.mo6998d().mo7070a();
            try {
                if (2 == akVar.m14581i()) {
                    byte[] j = akVar.m14582j();
                    if (j != null && j.length > 0) {
                        String m = akVar.m14585m();
                        a.setDoOutput(true);
                        a.setRequestProperty("Accept-Encoding", m);
                        a.setRequestProperty("Content-Encoding", m);
                        outputStream = a.getOutputStream();
                        try {
                            OutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, j.length);
                            try {
                                bufferedOutputStream.write(akVar.m14582j());
                                bufferedOutputStream.flush();
                                bk.m14980a(outputStream);
                                closeable = outputStream;
                                closeable2 = bufferedOutputStream;
                                responseCode = a.getResponseCode();
                                akVar.m14568a(responseCode);
                                akVar.m14570a(a.getHeaderFields());
                                if (responseCode != HttpStatus.SC_BAD_REQUEST || responseCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                                    obj = null;
                                } else {
                                    obj = 1;
                                }
                                if (obj == null) {
                                    outputStream = a.getInputStream();
                                    try {
                                        bufferedInputStream = new BufferedInputStream(outputStream, 8000);
                                        try {
                                            akVar.m14573b(C4514r.m16224b((InputStream) bufferedInputStream));
                                            bk.m14980a(outputStream);
                                            closeable4 = bufferedInputStream;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            closeable4 = outputStream;
                                            th2 = th;
                                            bk.m14980a(closeable2);
                                            bk.m14980a(bufferedInputStream);
                                            bk.m14980a(closeable);
                                            bk.m14980a(closeable4);
                                            bk.m14984a(a);
                                            throw th2;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        bufferedInputStream = null;
                                        closeable4 = outputStream;
                                        th2 = th;
                                        bk.m14980a(closeable2);
                                        bk.m14980a(bufferedInputStream);
                                        bk.m14980a(closeable);
                                        bk.m14980a(closeable4);
                                        bk.m14984a(a);
                                        throw th2;
                                    }
                                }
                                outputStream = null;
                                bk.m14980a(closeable2);
                                bk.m14980a(closeable4);
                                bk.m14980a(closeable);
                                bk.m14980a(outputStream);
                                bk.m14984a(a);
                            } catch (Throwable th5) {
                                Object obj2 = bufferedOutputStream;
                                bufferedInputStream = null;
                                th = th5;
                                closeable = outputStream;
                                th2 = th;
                                bk.m14980a(closeable2);
                                bk.m14980a(bufferedInputStream);
                                bk.m14980a(closeable);
                                bk.m14980a(closeable4);
                                bk.m14984a(a);
                                throw th2;
                            }
                        } catch (Throwable th42) {
                            closeable = outputStream;
                            closeable2 = null;
                            th2 = th42;
                            bufferedInputStream = null;
                            bk.m14980a(closeable2);
                            bk.m14980a(bufferedInputStream);
                            bk.m14980a(closeable);
                            bk.m14980a(closeable4);
                            bk.m14984a(a);
                            throw th2;
                        }
                    }
                }
                closeable = null;
                closeable2 = null;
            } catch (Throwable th6) {
                th2 = th6;
                closeable = null;
                closeable2 = null;
                bufferedInputStream = null;
                bk.m14980a(closeable2);
                bk.m14980a(bufferedInputStream);
                bk.m14980a(closeable);
                bk.m14980a(closeable4);
                bk.m14984a(a);
                throw th2;
            }
            try {
                responseCode = a.getResponseCode();
                akVar.m14568a(responseCode);
                akVar.m14570a(a.getHeaderFields());
                if (responseCode != HttpStatus.SC_BAD_REQUEST) {
                }
                obj = null;
                if (obj == null) {
                    outputStream = null;
                } else {
                    outputStream = a.getInputStream();
                    bufferedInputStream = new BufferedInputStream(outputStream, 8000);
                    akVar.m14573b(C4514r.m16224b((InputStream) bufferedInputStream));
                    bk.m14980a(outputStream);
                    closeable4 = bufferedInputStream;
                }
                bk.m14980a(closeable2);
                bk.m14980a(closeable4);
                bk.m14980a(closeable);
                bk.m14980a(outputStream);
                bk.m14984a(a);
            } catch (Throwable th7) {
                th2 = th7;
                bufferedInputStream = null;
                bk.m14980a(closeable2);
                bk.m14980a(bufferedInputStream);
                bk.m14980a(closeable);
                bk.m14980a(closeable4);
                bk.m14984a(a);
                throw th2;
            }
        } catch (Throwable th8) {
            th2 = th8;
            closeable = null;
            a = null;
            closeable2 = null;
            bufferedInputStream = null;
            bk.m14980a(closeable2);
            bk.m14980a(bufferedInputStream);
            bk.m14980a(closeable);
            bk.m14980a(closeable4);
            bk.m14984a(a);
            throw th2;
        }
    }
}

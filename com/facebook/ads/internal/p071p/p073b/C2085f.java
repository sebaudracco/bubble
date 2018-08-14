package com.facebook.ads.internal.p071p.p073b;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.ads.internal.p071p.p073b.p074a.C2063a;
import com.facebook.ads.internal.p071p.p073b.p074a.C2066c;
import com.facebook.ads.internal.p071p.p073b.p074a.C2072f;
import com.facebook.ads.internal.p071p.p073b.p074a.C2073g;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class C2085f {
    private final Object f4941a;
    private final ExecutorService f4942b;
    private final Map<String, C2087g> f4943c;
    private final ServerSocket f4944d;
    private final int f4945e;
    private final Thread f4946f;
    private final C2075c f4947g;
    private boolean f4948h;

    public static final class C2080a {
        private File f4931a;
        private C2066c f4932b = new C2072f();
        private C2063a f4933c = new C2073g(67108864);

        public C2080a(Context context) {
            this.f4931a = C2096o.m6743a(context);
        }

        private C2075c m6688a() {
            return new C2075c(this.f4931a, this.f4932b, this.f4933c);
        }
    }

    private class C2081b implements Callable<Boolean> {
        final /* synthetic */ C2085f f4934a;

        private C2081b(C2085f c2085f) {
            this.f4934a = c2085f;
        }

        public Boolean m6690a() {
            return Boolean.valueOf(this.f4934a.m6701c());
        }

        public /* synthetic */ Object call() {
            return m6690a();
        }
    }

    private class C2082c implements Callable<Boolean> {
        final /* synthetic */ C2085f f4935a;
        private final String f4936b;

        public C2082c(C2085f c2085f, String str) {
            this.f4935a = c2085f;
            this.f4936b = str;
        }

        public Boolean m6691a() {
            return Boolean.valueOf(this.f4935a.m6702c(this.f4936b));
        }

        public /* synthetic */ Object call() {
            return m6691a();
        }
    }

    private final class C2083d implements Runnable {
        final /* synthetic */ C2085f f4937a;
        private final Socket f4938b;

        public C2083d(C2085f c2085f, Socket socket) {
            this.f4937a = c2085f;
            this.f4938b = socket;
        }

        public void run() {
            this.f4937a.m6695a(this.f4938b);
        }
    }

    private final class C2084e implements Runnable {
        final /* synthetic */ C2085f f4939a;
        private final CountDownLatch f4940b;

        public C2084e(C2085f c2085f, CountDownLatch countDownLatch) {
            this.f4939a = c2085f;
            this.f4940b = countDownLatch;
        }

        public void run() {
            this.f4940b.countDown();
            this.f4939a.m6707e();
        }
    }

    public C2085f(Context context) {
        this(new C2080a(context).m6688a());
    }

    private C2085f(C2075c c2075c) {
        Throwable e;
        this.f4941a = new Object();
        this.f4942b = Executors.newFixedThreadPool(8);
        this.f4943c = new ConcurrentHashMap();
        this.f4947g = (C2075c) C2092j.m6733a(c2075c);
        try {
            this.f4944d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.f4945e = this.f4944d.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f4946f = new Thread(new C2084e(this, countDownLatch));
            this.f4946f.start();
            countDownLatch.await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            m6697b();
        } catch (IOException e2) {
            e = e2;
            this.f4942b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        } catch (InterruptedException e3) {
            e = e3;
            this.f4942b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    private void m6694a(Throwable th) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", th);
    }

    private void m6695a(Socket socket) {
        Throwable e;
        try {
            C2076d a = C2076d.m6665a(socket.getInputStream());
            Log.i("ProxyCache", "Request to cache proxy:" + a);
            String c = C2095m.m6741c(a.f4917a);
            if ("ping".equals(c)) {
                m6698b(socket);
            } else {
                m6706e(c).m6719a(a, socket);
            }
            m6700c(socket);
            Log.d("ProxyCache", "Opened connections: " + m6709f());
        } catch (SocketException e2) {
            Log.d("ProxyCache", "Closing socket... Socket is closed by client.");
            m6700c(socket);
            Log.d("ProxyCache", "Opened connections: " + m6709f());
        } catch (C2090l e3) {
            e = e3;
            m6694a(new C2090l("Error processing request", e));
            m6700c(socket);
            Log.d("ProxyCache", "Opened connections: " + m6709f());
        } catch (IOException e4) {
            e = e4;
            m6694a(new C2090l("Error processing request", e));
            m6700c(socket);
            Log.d("ProxyCache", "Opened connections: " + m6709f());
        } catch (Throwable th) {
            m6700c(socket);
            Log.d("ProxyCache", "Opened connections: " + m6709f());
        }
    }

    private void m6697b() {
        int i = HttpStatus.SC_MULTIPLE_CHOICES;
        int i2 = 0;
        while (i2 < 3) {
            try {
                this.f4948h = ((Boolean) this.f4942b.submit(new C2081b()).get((long) i, TimeUnit.MILLISECONDS)).booleanValue();
                if (!this.f4948h) {
                    SystemClock.sleep((long) i);
                    i *= 2;
                    i2++;
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                Throwable e2 = e;
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
                i *= 2;
                i2++;
            } catch (ExecutionException e3) {
                e2 = e3;
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
                i *= 2;
                i2++;
            } catch (TimeoutException e4) {
                e2 = e4;
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
                i *= 2;
                i2++;
            }
        }
        Log.e("ProxyCache", "Shutdown server... Error pinging server [attempts: " + i2 + ", max timeout: " + (i / 2) + "].");
        m6711a();
    }

    private void m6698b(Socket socket) {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    private void m6700c(Socket socket) {
        m6705d(socket);
        m6708e(socket);
        m6710f(socket);
    }

    private boolean m6701c() {
        C2089h c2089h = new C2089h(m6703d("ping"));
        boolean equals;
        try {
            byte[] bytes = "ping ok".getBytes();
            c2089h.mo3759a(0);
            byte[] bArr = new byte[bytes.length];
            c2089h.mo3758a(bArr);
            equals = Arrays.equals(bytes, bArr);
            Log.d("ProxyCache", "Ping response: `" + new String(bArr) + "`, pinged? " + equals);
            return equals;
        } catch (C2090l e) {
            equals = e;
            Log.e("ProxyCache", "Error reading ping response", equals);
            return false;
        } finally {
            c2089h.mo3760b();
        }
    }

    private boolean m6702c(String str) {
        C2089h c2089h = new C2089h(m6703d(str));
        try {
            c2089h.mo3759a(0);
            while (true) {
                if (c2089h.mo3758a(new byte[8192]) == -1) {
                    break;
                }
            }
            return true;
        } catch (Throwable e) {
            Log.e("ProxyCache", "Error reading url", e);
            return false;
        } finally {
            c2089h.mo3760b();
        }
    }

    private String m6703d(String str) {
        return String.format("http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.f4945e), C2095m.m6740b(str)});
    }

    private void m6704d() {
        synchronized (this.f4941a) {
            for (C2087g a : this.f4943c.values()) {
                a.m6718a();
            }
            this.f4943c.clear();
        }
    }

    private void m6705d(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException e) {
            Log.d("ProxyCache", "Releasing input stream... Socket is closed by client.");
        } catch (Throwable e2) {
            m6694a(new C2090l("Error closing socket input stream", e2));
        }
    }

    private C2087g m6706e(String str) {
        C2087g c2087g;
        synchronized (this.f4941a) {
            c2087g = (C2087g) this.f4943c.get(str);
            if (c2087g == null) {
                c2087g = new C2087g(str, this.f4947g);
                this.f4943c.put(str, c2087g);
            }
        }
        return c2087g;
    }

    private void m6707e() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.f4944d.accept();
                Log.d("ProxyCache", "Accept new socket " + accept);
                this.f4942b.submit(new C2083d(this, accept));
            } catch (Throwable e) {
                m6694a(new C2090l("Error during waiting connection", e));
                return;
            }
        }
    }

    private void m6708e(Socket socket) {
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (Throwable e) {
            m6694a(new C2090l("Error closing socket output stream", e));
        }
    }

    private int m6709f() {
        int i;
        synchronized (this.f4941a) {
            i = 0;
            for (C2087g b : this.f4943c.values()) {
                i = b.m6720b() + i;
            }
        }
        return i;
    }

    private void m6710f(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (Throwable e) {
            m6694a(new C2090l("Error closing socket", e));
        }
    }

    public void m6711a() {
        Log.i("ProxyCache", "Shutdown proxy server");
        m6704d();
        this.f4946f.interrupt();
        try {
            if (!this.f4944d.isClosed()) {
                this.f4944d.close();
            }
        } catch (Throwable e) {
            m6694a(new C2090l("Error shutting down proxy server", e));
        }
    }

    public void m6712a(String str) {
        int i = HttpStatus.SC_MULTIPLE_CHOICES;
        int i2 = 0;
        while (i2 < 3) {
            try {
                if (!((Boolean) this.f4942b.submit(new C2082c(this, str)).get()).booleanValue()) {
                    SystemClock.sleep((long) i);
                    i *= 2;
                    i2++;
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                Throwable e2 = e;
                Log.e("ProxyCache", "Error precaching url [attempt: " + i2 + ", url: " + str + "]. ", e2);
                i *= 2;
                i2++;
            } catch (ExecutionException e3) {
                e2 = e3;
                Log.e("ProxyCache", "Error precaching url [attempt: " + i2 + ", url: " + str + "]. ", e2);
                i *= 2;
                i2++;
            }
        }
        Log.e("ProxyCache", "Shutdown server... Error precaching url [attempts: " + i2 + ", url: " + str + "].");
        m6711a();
    }

    public String m6713b(String str) {
        if (!this.f4948h) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        return this.f4948h ? m6703d(str) : str;
    }
}

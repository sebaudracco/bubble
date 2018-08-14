package com.bgjd.ici.p027g;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.bgjd.ici.p027g.C1500e.C1498a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import mf.org.apache.xml.serialize.LineSeparator;

public class C1505f implements C1494a {
    private static final String f2475b = "MKT";
    TrustManager[] f2476a = new TrustManager[]{new C15044(this)};
    private URI f2477c;
    private C1415b f2478d;
    private Socket f2479e;
    private Thread f2480f;
    private HandlerThread f2481g;
    private Handler f2482h;
    private C1500e f2483i;
    private boolean f2484j = false;
    private final Object f2485k = new Object();

    class C15011 implements Runnable {
        final /* synthetic */ C1505f f2470a;

        C15011(C1505f c1505f) {
            this.f2470a = c1505f;
        }

        public void run() {
            try {
                String str;
                String a = this.f2470a.m3246d();
                int port = this.f2470a.f2477c.getPort() != -1 ? this.f2470a.f2477c.getPort() : this.f2470a.f2477c.getScheme().equals("wss") ? 443 : 80;
                String path = TextUtils.isEmpty(this.f2470a.f2477c.getPath()) ? BridgeUtil.SPLIT_MARK : this.f2470a.f2477c.getPath();
                if (TextUtils.isEmpty(this.f2470a.f2477c.getQuery())) {
                    str = path;
                } else {
                    str = path + "?" + this.f2470a.f2477c.getQuery();
                }
                URI uri = new URI(this.f2470a.f2477c.getScheme().equals("wss") ? "https" : "http", "//" + this.f2470a.f2477c.getHost(), null);
                this.f2470a.f2479e = (this.f2470a.f2477c.getScheme().equals("wss") ? this.f2470a.m3249e() : SocketFactory.getDefault()).createSocket(this.f2470a.f2477c.getHost(), port);
                PrintWriter printWriter = new PrintWriter(this.f2470a.f2479e.getOutputStream());
                printWriter.print("GET " + str + " HTTP/1.1\r\n");
                printWriter.print("Upgrade: websocket\r\n");
                printWriter.print("Connection: Upgrade\r\n");
                printWriter.print("Host: " + this.f2470a.f2477c.getHost() + LineSeparator.Windows);
                printWriter.print("Origin: " + uri.toString() + LineSeparator.Windows);
                printWriter.print("Sec-WebSocket-Key: " + a + LineSeparator.Windows);
                printWriter.print("Sec-WebSocket-Version: 13\r\n");
                printWriter.print(LineSeparator.Windows);
                printWriter.flush();
                C1498a c1498a = new C1498a(this.f2470a.f2479e.getInputStream());
                Matcher matcher = Pattern.compile("(HTTP/(\\d)+\\.(\\d)+) (\\d{3}) ([\\x20\\x09[\\x00-\\xFF&&[^\\x00-\\x1F\\x7F]]]+)").matcher(this.f2470a.m3236a(c1498a));
                if (matcher.matches()) {
                    port = Integer.parseInt(matcher.group(4));
                    path = matcher.group(5);
                    if (port != 101) {
                        throw new Exception(path);
                    }
                    Pattern compile = Pattern.compile(": ");
                    int i = 0;
                    while (true) {
                        CharSequence a2 = this.f2470a.m3236a(c1498a);
                        if (TextUtils.isEmpty(a2)) {
                            break;
                        } else if (a2.indexOf(":") != -1) {
                            String[] split = compile.split(a2);
                            if (!split[0].equals("Sec-WebSocket-Accept")) {
                                continue;
                            } else if (this.f2470a.m3243b(a).equals(split[1].trim())) {
                                i = 1;
                            } else {
                                throw new Exception("Bad Sec-WebSocket-Accept header value.");
                            }
                        }
                    }
                    if (i == 0) {
                        throw new Exception("Invalid Sec-WebSocket-Accept header value.");
                    }
                    this.f2470a.f2478d.mo2304a();
                    this.f2470a.f2483i.m3231a(c1498a);
                    return;
                }
                throw new Exception("Received no reply from server.");
            } catch (Throwable e) {
                Log.d(C1505f.f2475b, "WebSocket EOF!", e);
                this.f2470a.f2478d.mo2305a(0, "EOF");
            } catch (Throwable e2) {
                Log.d(C1505f.f2475b, "Websocket SSL error!", e2);
                this.f2470a.f2478d.mo2305a(0, "SSL");
            } catch (Exception e3) {
                if (!this.f2470a.f2484j) {
                    this.f2470a.f2478d.mo2306a(e3);
                }
                if (this.f2470a.f2481g != null) {
                    try {
                        HandlerThread h = this.f2470a.f2481g;
                        this.f2470a.f2481g = null;
                        if (h != null) {
                            h.quit();
                        }
                    } catch (Exception e4) {
                    }
                }
            }
        }
    }

    class C15022 implements Runnable {
        final /* synthetic */ C1505f f2471a;

        C15022(C1505f c1505f) {
            this.f2471a = c1505f;
        }

        public void run() {
            try {
                this.f2471a.f2484j = true;
                if (this.f2471a.f2479e != null) {
                    this.f2471a.f2479e.close();
                    this.f2471a.f2479e = null;
                }
            } catch (Exception e) {
                Log.d(C1505f.f2475b, "Error while disconnecting", e);
                this.f2471a.f2478d.mo2306a(e);
            }
        }
    }

    class C15044 implements X509TrustManager {
        final /* synthetic */ C1505f f2474a;

        C15044(C1505f c1505f) {
            this.f2474a = c1505f;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static void m3241a(TrustManager[] trustManagerArr) {
    }

    public C1505f(URI uri, C1415b c1415b) {
        this.f2477c = uri;
        this.f2478d = c1415b;
        this.f2483i = new C1500e(this);
        this.f2481g = new HandlerThread("websocket-thread");
        this.f2481g.start();
        this.f2482h = new Handler(this.f2481g.getLooper());
    }

    public C1415b mo2334c() {
        return this.f2478d;
    }

    public void mo2330a() {
        if (this.f2480f == null || !this.f2480f.isAlive()) {
            this.f2480f = new Thread(new C15011(this));
            this.f2480f.start();
        }
    }

    public void mo2333b() {
        if (this.f2479e != null) {
            this.f2482h.post(new C15022(this));
        }
    }

    public void mo2331a(String str) {
        m3258b(this.f2483i.m3232a(str));
    }

    public void mo2332a(byte[] bArr) {
        m3258b(this.f2483i.m3233a(bArr));
    }

    private String m3236a(C1498a c1498a) throws IOException {
        int read = c1498a.read();
        if (read == -1) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        while (read != 10) {
            if (read != 13) {
                stringBuilder.append((char) read);
            }
            read = c1498a.read();
            if (read == -1) {
                return null;
            }
        }
        return stringBuilder.toString();
    }

    private String m3246d() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((int) (Math.random() * 256.0d));
        }
        return Base64.encodeToString(bArr, 0).trim();
    }

    private String m3243b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update((str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes());
            return Base64.encodeToString(instance.digest(), 0).trim();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    void m3258b(final byte[] bArr) {
        this.f2482h.post(new Runnable(this) {
            final /* synthetic */ C1505f f2473b;

            public void run() {
                try {
                    synchronized (this.f2473b.f2485k) {
                        if (this.f2473b.f2479e == null) {
                            throw new IllegalStateException("Socket not connected");
                        }
                        OutputStream outputStream = this.f2473b.f2479e.getOutputStream();
                        outputStream.write(bArr);
                        outputStream.flush();
                    }
                } catch (Exception e) {
                    this.f2473b.f2478d.mo2306a(e);
                }
            }
        });
    }

    private SSLSocketFactory m3249e() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, this.f2476a, null);
        return instance.getSocketFactory();
    }
}

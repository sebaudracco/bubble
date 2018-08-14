package com.yandex.metrica.impl.ob;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.C4518b;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mf.org.apache.xml.serialize.LineSeparator;
import org.json.JSONException;
import org.slf4j.Marker;

public class dp implements Runnable {
    private final ServiceConnection f12247a = new C44391();
    private final Handler f12248b = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ dp f12236a;

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    this.f12236a.m15775e();
                    try {
                        this.f12236a.f12250d.unbindService(this.f12236a.f12247a);
                        return;
                    } catch (Exception e) {
                        YandexMetrica.getReporter(this.f12236a.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_unbind_has_thrown_exception");
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private HashMap<String, C4442c> f12249c = new C44444(this);
    private final Context f12250d;
    private boolean f12251e;
    private ServerSocket f12252f;
    private final dq f12253g = new dq();
    private ds f12254h;
    private Thread f12255i;

    class C44391 implements ServiceConnection {
        C44391() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    }

    class C44402 extends HashMap<String, Object> {
        final /* synthetic */ String f12235a;

        C44402(String str) {
            this.f12235a = str;
            put("uri", this.f12235a);
        }
    }

    static abstract class C4442c {
        public abstract C4452b mo7075a(Uri uri, Socket socket);

        C4442c() {
        }
    }

    class C44444 extends HashMap<String, C4442c> {
        final /* synthetic */ dp f12238a;

        class C44431 extends C4442c {
            final /* synthetic */ C44444 f12237a;

            C44431(C44444 c44444) {
                this.f12237a = c44444;
            }

            public C4452b mo7075a(Uri uri, Socket socket) {
                return new C4453a(this.f12237a.f12238a, uri, socket);
            }
        }

        C44444(dp dpVar) {
            this.f12238a = dpVar;
            put("p", new C44431(this));
        }
    }

    class C44465 implements C4445h<C4491p> {
        final /* synthetic */ dp f12239a;

        C44465(dp dpVar) {
            this.f12239a = dpVar;
        }

        public boolean m15744a(C4491p c4491p) {
            return !this.f12239a.f12250d.getPackageName().equals(c4491p.f12458b);
        }
    }

    class C44476 implements C4350j<C4491p> {
        final /* synthetic */ dp f12240a;

        C44476(dp dpVar) {
            this.f12240a = dpVar;
        }

        public void m15746a(C4491p c4491p) {
            this.f12240a.f12253g.m15779a(c4491p.f12457a);
        }
    }

    class C44487 implements C4350j<C4489n> {
        final /* synthetic */ dp f12241a;

        C44487(dp dpVar) {
            this.f12241a = dpVar;
        }

        public void m15748a(C4489n c4489n) {
            this.f12241a.f12253g.m15780b(c4489n.f12455a);
        }
    }

    class C44498 implements C4350j<C4487l> {
        final /* synthetic */ dp f12242a;

        C44498(dp dpVar) {
            this.f12242a = dpVar;
        }

        public void m15750a(C4487l c4487l) {
            this.f12242a.f12253g.m15781c(c4487l.f12453a);
        }
    }

    class C44509 implements C4350j<C4488m> {
        final /* synthetic */ dp f12243a;

        C44509(dp dpVar) {
            this.f12243a = dpVar;
        }

        public void m15752a(C4488m c4488m) {
            this.f12243a.f12253g.m15782d(c4488m.f12454a);
        }
    }

    abstract class C4452b {
        Uri f12244b;
        Socket f12245c;

        public abstract void mo7077a();

        C4452b(Uri uri, Map<String, String> map) {
            this.f12244b = uri;
            this.f12245c = map;
        }

        private static void m15753a(OutputStream outputStream) throws IOException {
            outputStream.write(LineSeparator.Windows.getBytes());
        }

        void m15756a(String str, Map<String, String> map, byte[] bArr) {
            Closeable bufferedOutputStream;
            Throwable e;
            Closeable closeable = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(this.f12245c.getOutputStream());
                try {
                    bufferedOutputStream.write(str.getBytes());
                    C4452b.m15753a((OutputStream) bufferedOutputStream);
                    for (Entry entry : map.entrySet()) {
                        C4452b.m15754a((OutputStream) bufferedOutputStream, (String) entry.getKey(), (String) entry.getValue());
                    }
                    C4452b.m15754a((OutputStream) bufferedOutputStream, "Content-Length", String.valueOf(bArr.length));
                    C4452b.m15753a((OutputStream) bufferedOutputStream);
                    bufferedOutputStream.write(bArr);
                    bufferedOutputStream.flush();
                    mo7079b();
                    bk.m14980a(bufferedOutputStream);
                } catch (IOException e2) {
                    e = e2;
                    closeable = bufferedOutputStream;
                    try {
                        mo7078a(e);
                        bk.m14980a(closeable);
                    } catch (Throwable th) {
                        e = th;
                        bufferedOutputStream = closeable;
                        bk.m14980a(bufferedOutputStream);
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    bk.m14980a(bufferedOutputStream);
                    throw e;
                }
            } catch (IOException e3) {
                e = e3;
                mo7078a(e);
                bk.m14980a(closeable);
            } catch (Throwable th3) {
                e = th3;
                bufferedOutputStream = null;
                bk.m14980a(bufferedOutputStream);
                throw e;
            }
        }

        protected void mo7079b() {
        }

        protected void mo7078a(Throwable th) {
        }

        private static void m15754a(OutputStream outputStream, String str, String str2) throws IOException {
            outputStream.write((str + ": " + str2).getBytes());
            C4452b.m15753a(outputStream);
        }
    }

    class C4453a extends C4452b {
        final /* synthetic */ dp f12246a;

        class C44511 extends HashMap<String, String> {
            C44511() {
                put("Content-Type", "text/plain; charset=utf-8");
                put("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                put("Access-Control-Allow-Methods", "GET");
            }
        }

        C4453a(dp dpVar, Uri uri, Map<String, String> map) {
            this.f12246a = dpVar;
            super(uri, map);
        }

        public void mo7077a() {
            if (this.f12246a.f12254h.m15788b().equals(this.b.getQueryParameter("t"))) {
                m15756a("HTTP/1.1 200 OK", new C44511(), m15762c());
            } else {
                YandexMetrica.getReporter(this.f12246a.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_request_with_wrong_token");
            }
        }

        protected void mo7079b() {
            YandexMetrica.getReporter(this.f12246a.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_sync_succeed", dp.m15767c(this.c.getLocalPort()));
        }

        protected void mo7078a(Throwable th) {
            YandexMetrica.getReporter(this.f12246a.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("socket_io_exception_during_sync", th);
        }

        protected byte[] m15762c() {
            try {
                return Base64.encode(new C4518b().m16236a(this.f12246a.f12253g.m15778a().getBytes()), 0);
            } catch (JSONException e) {
                return new byte[0];
            }
        }
    }

    dp(Context context) {
        this.f12250d = context;
        C4484g.m16084a().m16091a(this, C4491p.class, C4486k.m16096a(new C44476(this)).m16094a(new C44465(this)).m16095a());
        C4484g.m16084a().m16091a(this, C4489n.class, C4486k.m16096a(new C44487(this)).m16095a());
        C4484g.m16084a().m16091a(this, C4487l.class, C4486k.m16096a(new C44498(this)).m16095a());
        C4484g.m16084a().m16091a(this, C4488m.class, C4486k.m16096a(new C44509(this)).m16095a());
        C4484g.m16084a().m16091a(this, C4490o.class, C4486k.m16096a(new C4350j<C4490o>(this) {
            final /* synthetic */ dp f12234a;

            {
                this.f12234a = r1;
            }

            public void m15739a(C4490o c4490o) {
                this.f12234a.m15771a(c4490o.f12456a);
                this.f12234a.m15773c();
            }
        }).m16095a());
    }

    public void m15770a() {
        if (this.f12251e) {
            m15772b();
            this.f12248b.sendMessageDelayed(this.f12248b.obtainMessage(100), this.f12254h.m15787a() * 1000);
        }
    }

    public void m15772b() {
        this.f12248b.removeMessages(100);
    }

    public synchronized void m15773c() {
        if (!(this.f12251e || this.f12254h == null)) {
            this.f12251e = true;
            m15774d();
            this.f12255i = new Thread(this);
            this.f12255i.start();
        }
    }

    void m15771a(ds dsVar) {
        this.f12254h = dsVar;
    }

    void m15774d() {
        Intent intent = new Intent(this.f12250d, MetricaService.class);
        intent.setAction("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER");
        try {
            if (!this.f12250d.bindService(intent, this.f12247a, 1)) {
                YandexMetrica.getReporter(this.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_failed");
            }
        } catch (Exception e) {
            YandexMetrica.getReporter(this.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_thrown_exception");
        }
    }

    public synchronized void m15775e() {
        try {
            this.f12251e = false;
            if (this.f12255i != null) {
                this.f12255i.interrupt();
                this.f12255i = null;
            }
            if (this.f12252f != null) {
                this.f12252f.close();
                this.f12252f = null;
            }
        } catch (IOException e) {
        }
    }

    public void run() {
        Socket accept;
        BufferedReader bufferedReader;
        Throwable th;
        Socket socket;
        this.f12252f = m15776f();
        if (this.f12252f != null) {
            while (this.f12251e) {
                try {
                    accept = this.f12252f.accept();
                    try {
                        accept.setSoTimeout(1000);
                        HashMap hashMap = new HashMap();
                        bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                        try {
                            String readLine = bufferedReader.readLine();
                            if (!TextUtils.isEmpty(readLine)) {
                                String substring;
                                if (readLine.startsWith("GET /")) {
                                    int indexOf = readLine.indexOf(47) + 1;
                                    substring = readLine.substring(indexOf, readLine.indexOf(32, indexOf));
                                } else {
                                    substring = null;
                                }
                                Uri parse = Uri.parse(substring);
                                while (true) {
                                    readLine = bufferedReader.readLine();
                                    if (TextUtils.isEmpty(readLine)) {
                                        break;
                                    }
                                    int indexOf2 = readLine.indexOf(": ");
                                    hashMap.put(readLine.substring(0, indexOf2), readLine.substring(indexOf2 + 2));
                                }
                                C4442c c4442c = (C4442c) this.f12249c.get(parse.getPath());
                                if (c4442c != null) {
                                    c4442c.mo7075a(parse, accept).mo7077a();
                                } else {
                                    YandexMetrica.getReporter(this.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_request_to_unknown_path", new C44402(substring));
                                }
                            }
                            bufferedReader.close();
                            if (accept != null) {
                                try {
                                    accept.close();
                                } catch (IOException e) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    socket = accept;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = null;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    socket = null;
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    accept = null;
                }
            }
            return;
        }
        return;
        if (accept != null) {
            try {
                accept.close();
            } catch (IOException e5) {
            }
        }
        throw th;
        throw th;
    }

    ServerSocket m15776f() {
        Integer num;
        Iterator it = this.f12254h.m15789c().iterator();
        Integer num2 = null;
        ServerSocket serverSocket = null;
        while (serverSocket == null && it.hasNext()) {
            try {
                num = (Integer) it.next();
                if (num != null) {
                    try {
                        serverSocket = m15769a(num.intValue());
                    } catch (SocketException e) {
                        YandexMetrica.getReporter(this.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", m15767c(num.intValue()));
                        num2 = num;
                    } catch (IOException e2) {
                        num2 = num;
                    }
                }
                num2 = num;
            } catch (SocketException e3) {
                num = num2;
                YandexMetrica.getReporter(this.f12250d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", m15767c(num.intValue()));
                num2 = num;
            } catch (IOException e4) {
                num = num2;
                num2 = num;
            }
        }
        return serverSocket;
    }

    ServerSocket m15769a(int i) throws IOException {
        return new ServerSocket(i);
    }

    private static HashMap<String, Object> m15767c(int i) {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put(ClientCookie.PORT_ATTR, String.valueOf(i));
        return hashMap;
    }
}

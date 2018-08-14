package com.facebook.ads.internal.p071p.p073b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.ads.internal.p071p.p073b.p074a.C2065b;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

final class C2087g {
    private final AtomicInteger f4951a = new AtomicInteger(0);
    private final String f4952b;
    private volatile C2078e f4953c;
    private final List<C2074b> f4954d = new CopyOnWriteArrayList();
    private final C2074b f4955e;
    private final C2075c f4956f;

    private static final class C2086a extends Handler implements C2074b {
        private final String f4949a;
        private final List<C2074b> f4950b;

        public C2086a(String str, List<C2074b> list) {
            super(Looper.getMainLooper());
            this.f4949a = str;
            this.f4950b = list;
        }

        public void mo3756a(File file, String str, int i) {
            Message obtainMessage = obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.obj = file;
            sendMessage(obtainMessage);
        }

        public void handleMessage(Message message) {
            for (C2074b a : this.f4950b) {
                a.mo3756a((File) message.obj, this.f4949a, message.arg1);
            }
        }
    }

    public C2087g(String str, C2075c c2075c) {
        this.f4952b = (String) C2092j.m6733a(str);
        this.f4956f = (C2075c) C2092j.m6733a(c2075c);
        this.f4955e = new C2086a(str, this.f4954d);
    }

    private synchronized void m6715c() {
        this.f4953c = this.f4953c == null ? m6717e() : this.f4953c;
    }

    private synchronized void m6716d() {
        if (this.f4951a.decrementAndGet() <= 0) {
            this.f4953c.m6677a();
            this.f4953c = null;
        }
    }

    private C2078e m6717e() {
        C2078e c2078e = new C2078e(new C2089h(this.f4952b), new C2065b(this.f4956f.m6663a(this.f4952b), this.f4956f.f4914c));
        c2078e.m6686a(this.f4955e);
        return c2078e;
    }

    public void m6718a() {
        this.f4954d.clear();
        if (this.f4953c != null) {
            this.f4953c.m6686a(null);
            this.f4953c.m6677a();
            this.f4953c = null;
        }
        this.f4951a.set(0);
    }

    public void m6719a(C2076d c2076d, Socket socket) {
        m6715c();
        try {
            this.f4951a.incrementAndGet();
            this.f4953c.m6687a(c2076d, socket);
        } finally {
            m6716d();
        }
    }

    public int m6720b() {
        return this.f4951a.get();
    }
}

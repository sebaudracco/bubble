package com.yandex.metrica.impl.ob;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.yandex.metrica.impl.ob.fu.C4471b;
import com.yandex.metrica.impl.ob.fu.C4473a;
import java.lang.Thread.State;

public class fs {
    private fq f12424a;
    private HandlerThread f12425b;
    private C4478b f12426c;
    private volatile Handler f12427d;

    private static class C4477a implements Runnable {
        private C4473a f12419a;
        private fr f12420b;

        private C4477a(C4473a c4473a, fr frVar) {
            this.f12419a = c4473a;
            this.f12420b = frVar;
        }

        public void run() {
            if (this.f12419a != null) {
                this.f12419a.mo7102a(this.f12420b);
            }
        }
    }

    private class C4478b extends Handler {
        final /* synthetic */ fs f12421a;

        private C4478b(fs fsVar, Looper looper) {
            this.f12421a = fsVar;
            super(looper);
        }

        public void handleMessage(Message msg) {
            fu fuVar = (fu) msg.obj;
            C4471b e = fuVar.m16040e();
            try {
                this.f12421a.f12427d.post(new C4479c(e, fuVar.mo7103b(this.f12421a.f12424a.m16068a(fuVar))));
            } catch (fr e2) {
                this.f12421a.f12427d.post(new C4477a(fuVar.m16041f(), e2));
            }
        }

        public <T> void m16069a(fu<T> fuVar) {
            Message message = new Message();
            message.obj = fuVar;
            sendMessage(message);
        }
    }

    private static class C4479c<T> implements Runnable {
        private C4471b<T> f12422a;
        private T f12423b;

        private C4479c(C4471b c4471b, T t) {
            this.f12422a = c4471b;
            this.f12423b = t;
        }

        public void run() {
            if (this.f12422a != null) {
                this.f12422a.mo7101a(this.f12423b);
            }
        }
    }

    public fs(fq fqVar) {
        this(fqVar, null);
    }

    public fs(fq fqVar, Handler handler) {
        this.f12424a = fqVar;
        this.f12425b = new HandlerThread(fs.class.getSimpleName() + '@' + Integer.toHexString(hashCode()));
        this.f12427d = handler;
    }

    public <T> void m16073a(fu<T> fuVar, C4471b<T> c4471b, C4473a c4473a) {
        m16071a();
        fuVar.m16035a((C4471b) c4471b);
        fuVar.m16034a(c4473a);
        this.f12426c.m16069a(fuVar);
    }

    private synchronized void m16071a() {
        if (this.f12425b.getState() == State.NEW) {
            this.f12425b.start();
            Looper looper = this.f12425b.getLooper();
            this.f12426c = new C4478b(looper);
            if (this.f12427d == null) {
                this.f12427d = new Handler(looper);
            }
        }
    }
}

package com.unit.two.p146b;

import android.os.Looper;
import android.os.Message;
import com.unit.two.p147c.C4096a;
import com.unit.two.p151f.C4150q;

public abstract class C4094a implements Runnable {
    private static C4095b f9487a = null;
    private Object f9488b;
    private boolean f9489c = false;

    static {
        String str = C4096a.bv;
    }

    protected abstract Object mo6923a();

    protected abstract void mo6924a(Object obj);

    public final void m12667b() {
        boolean z = false;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            z = true;
        }
        this.f9489c = z;
        if (this.f9489c && f9487a == null) {
            synchronized (C4094a.class) {
                if (f9487a != null) {
                } else {
                    f9487a = new C4095b();
                }
            }
        }
        C4150q.m12871a(this);
    }

    public void run() {
        try {
            this.f9488b = mo6923a();
        } catch (Throwable th) {
        }
        try {
            if (!this.f9489c || f9487a == null) {
                mo6924a(this.f9488b);
                return;
            }
            Message message = new Message();
            message.obj = this;
            f9487a.sendMessage(message);
        } catch (Throwable th2) {
        }
    }
}

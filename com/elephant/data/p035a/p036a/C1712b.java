package com.elephant.data.p035a.p036a;

import android.os.Looper;
import android.os.Message;
import com.elephant.data.p048g.C1821i;

public abstract class C1712b implements Runnable {
    private static C1713c f3508a = null;
    private Object f3509b;
    private boolean f3510c = false;

    public static void m4948b() {
        if (f3508a == null) {
            synchronized (C1712b.class) {
                if (f3508a != null) {
                    return;
                }
                f3508a = new C1713c();
            }
        }
    }

    protected abstract Object mo3531a();

    protected abstract void mo3532a(Object obj);

    public final void m4951c() {
        this.f3510c = Looper.getMainLooper() == Looper.myLooper();
        if (this.f3510c) {
            C1712b.m4948b();
        }
        C1821i.m5346a(this);
    }

    public void run() {
        try {
            this.f3509b = mo3531a();
            if (!this.f3510c || f3508a == null) {
                mo3532a(this.f3509b);
                return;
            }
            Message message = new Message();
            message.obj = this;
            f3508a.sendMessage(message);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

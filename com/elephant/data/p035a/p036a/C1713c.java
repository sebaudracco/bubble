package com.elephant.data.p035a.p036a;

import android.os.Handler;
import android.os.Message;

final class C1713c extends Handler {
    private C1713c() {
    }

    public final void handleMessage(Message message) {
        try {
            C1712b c1712b = (C1712b) message.obj;
            if (c1712b != null) {
                c1712b.mo3532a(c1712b.f3509b);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

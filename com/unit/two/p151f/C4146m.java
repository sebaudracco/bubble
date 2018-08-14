package com.unit.two.p151f;

import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import com.unit.two.p147c.C4096a;
import java.lang.reflect.Constructor;

final class C4146m implements Runnable {
    private /* synthetic */ Context f9716a;

    C4146m(Context context) {
        this.f9716a = context;
    }

    public final void run() {
        try {
            if (VERSION.SDK_INT >= 17) {
                C4145l.f9706c = WebSettings.getDefaultUserAgent(this.f9716a);
                return;
            }
            Class cls = Class.forName(C4096a.f9502M);
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(C4096a.f9503N)});
            declaredConstructor.setAccessible(true);
            C4145l.f9706c = (String) cls.getMethod(C4096a.f9504O, new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{this.f9716a, null}), new Object[0]);
        } catch (Throwable th) {
        }
    }
}

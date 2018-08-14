package com.unit.two.p151f;

import android.content.SharedPreferences.Editor;
import com.unit.two.p147c.C4096a;
import java.lang.reflect.Method;

final class C4148o {
    private static final Method f9718a = C4148o.m12869b();

    static void m12868a(Editor editor) {
        C4150q.m12871a(new C4149p(editor));
    }

    private static Method m12869b() {
        try {
            return Editor.class.getMethod(C4096a.f9494E, new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}

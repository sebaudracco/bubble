package com.unit.two.p151f;

import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;

final class C4149p implements Runnable {
    private /* synthetic */ Editor f9719a;

    C4149p(Editor editor) {
        this.f9719a = editor;
    }

    public final void run() {
        try {
            if (C4148o.f9718a != null) {
                C4148o.f9718a.invoke(this.f9719a, new Object[0]);
                return;
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e2) {
        } catch (InvocationTargetException e3) {
        }
        this.f9719a.commit();
    }
}

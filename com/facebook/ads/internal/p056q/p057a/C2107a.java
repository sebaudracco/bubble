package com.facebook.ads.internal.p056q.p057a;

import android.app.Activity;
import android.support.annotation.Nullable;
import com.bgjd.ici.p025b.C1408j.C1404b;
import java.lang.reflect.Field;
import java.util.Map;

public class C2107a {
    @Nullable
    public static Activity m6763a() {
        try {
            return C2107a.m6764b();
        } catch (Exception e) {
            return null;
        }
    }

    private static Activity m6764b() {
        Class cls = Class.forName("android.app.ActivityThread");
        Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mActivities");
        declaredField.setAccessible(true);
        Map map = (Map) declaredField.get(invoke);
        if (map == null) {
            return null;
        }
        for (Object invoke2 : map.values()) {
            Class cls2 = invoke2.getClass();
            Field declaredField2 = cls2.getDeclaredField("paused");
            declaredField2.setAccessible(true);
            if (!declaredField2.getBoolean(invoke2)) {
                declaredField = cls2.getDeclaredField(C1404b.aw);
                declaredField.setAccessible(true);
                return (Activity) declaredField.get(invoke2);
            }
        }
        return null;
    }
}

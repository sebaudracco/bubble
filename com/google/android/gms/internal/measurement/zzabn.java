package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class zzabn implements PrivilegedExceptionAction<Unsafe> {
    zzabn() {
    }

    public final /* synthetic */ Object run() throws Exception {
        Class cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (cls.isInstance(obj)) {
                return (Unsafe) cls.cast(obj);
            }
        }
        return null;
    }
}

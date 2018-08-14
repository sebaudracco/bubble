package com.p000a.p001a.p003b;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class C0525i {
    private static final Map<Class<?>, Class<?>> f245a;
    private static final Map<Class<?>, Class<?>> f246b;

    static {
        Map hashMap = new HashMap(16);
        Map hashMap2 = new HashMap(16);
        C0525i.m379a(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        C0525i.m379a(hashMap, hashMap2, Byte.TYPE, Byte.class);
        C0525i.m379a(hashMap, hashMap2, Character.TYPE, Character.class);
        C0525i.m379a(hashMap, hashMap2, Double.TYPE, Double.class);
        C0525i.m379a(hashMap, hashMap2, Float.TYPE, Float.class);
        C0525i.m379a(hashMap, hashMap2, Integer.TYPE, Integer.class);
        C0525i.m379a(hashMap, hashMap2, Long.TYPE, Long.class);
        C0525i.m379a(hashMap, hashMap2, Short.TYPE, Short.class);
        C0525i.m379a(hashMap, hashMap2, Void.TYPE, Void.class);
        f245a = Collections.unmodifiableMap(hashMap);
        f246b = Collections.unmodifiableMap(hashMap2);
    }

    public static <T> Class<T> m378a(Class<T> cls) {
        Class<T> cls2 = (Class) f245a.get(C0497a.m308a((Object) cls));
        return cls2 == null ? cls : cls2;
    }

    private static void m379a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    public static boolean m380a(Type type) {
        return f245a.containsKey(type);
    }
}

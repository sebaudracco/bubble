package com.appnext.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class C1130h {

    public static class C1129a {
        private final Object lY;
        private final String lZ;
        private Class<?> ma;
        private List<Class<?>> mb = new ArrayList();
        private List<Object> mc = new ArrayList();
        private boolean md;
        private boolean me;

        public C1129a(Object obj, String str) {
            this.lY = obj;
            this.lZ = str;
            this.ma = obj != null ? obj.getClass() : null;
        }

        public <T> C1129a m2364a(Class<T> cls, T t) {
            this.mb.add(cls);
            this.mc.add(t);
            return this;
        }

        public C1129a cZ() {
            this.md = true;
            return this;
        }

        public C1129a m2365b(Class<?> cls) {
            this.me = true;
            this.ma = cls;
            return this;
        }

        public Object da() throws Exception {
            Method a = C1130h.m2366a(this.ma, this.lZ, (Class[]) this.mb.toArray(new Class[this.mb.size()]));
            if (this.md) {
                a.setAccessible(true);
            }
            Object[] toArray = this.mc.toArray();
            if (this.me) {
                return a.invoke(null, toArray);
            }
            return a.invoke(this.lY, toArray);
        }
    }

    public static Method m2366a(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

    public static boolean aR(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}

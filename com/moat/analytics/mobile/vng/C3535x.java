package com.moat.analytics.mobile.vng;

import android.support.annotation.VisibleForTesting;
import com.moat.analytics.mobile.vng.C3532w.C3499b;
import com.moat.analytics.mobile.vng.C3532w.C3531d;
import com.moat.analytics.mobile.vng.p130a.p131a.C3473a;
import com.moat.analytics.mobile.vng.p130a.p132b.C3474a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

class C3535x<T> implements InvocationHandler {
    private static final Object[] f9011a = new Object[0];
    private final C3469a<T> f9012b;
    private final Class<T> f9013c;
    private final LinkedList<C3534b> f9014d = new LinkedList();
    private boolean f9015e;
    private T f9016f;

    interface C3469a<T> {
        C3474a<T> mo6526a();
    }

    class C35331 implements C3499b {
        final /* synthetic */ C3535x f9006a;

        C35331(C3535x c3535x) {
            this.f9006a = c3535x;
        }

        public void mo6533b() {
            this.f9006a.m12034c();
        }

        public void mo6534c() {
        }
    }

    private class C3534b {
        final /* synthetic */ C3535x f9007a;
        private final WeakReference[] f9008b;
        private final LinkedList<Object> f9009c;
        private final Method f9010d;

        private C3534b(C3535x c3535x, Method method, Object... objArr) {
            int i = 0;
            this.f9007a = c3535x;
            this.f9009c = new LinkedList();
            if (objArr == null) {
                objArr = C3535x.f9011a;
            }
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f9009c.add(obj);
                }
                int i3 = i2 + 1;
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2 = i3;
            }
            this.f9008b = weakReferenceArr;
            this.f9010d = method;
        }
    }

    @VisibleForTesting
    C3535x(C3469a<T> c3469a, Class<T> cls) {
        C3473a.m11839a(c3469a);
        C3473a.m11839a(cls);
        this.f9012b = c3469a;
        this.f9013c = cls;
        C3532w.m12009a().m12021a(new C35331(this));
    }

    static <T> T m12027a(C3469a<T> c3469a, Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C3535x(c3469a, cls));
    }

    private Object m12028a(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
        return null;
    }

    private Object m12029a(Method method, Object[] objArr) {
        Class declaringClass = method.getDeclaringClass();
        C3532w a = C3532w.m12009a();
        if (Object.class.equals(declaringClass)) {
            String name = method.getName();
            if ("getClass".equals(name)) {
                return this.f9013c;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object invoke = method.invoke(this, objArr);
            return (invoke + "").replace(C3535x.class.getName(), this.f9013c.getName());
        } else if (this.f9015e && this.f9016f == null) {
            this.f9014d.clear();
            return m12028a(method);
        } else {
            if (a.f8995a == C3531d.ON) {
                m12034c();
                if (this.f9016f != null) {
                    return method.invoke(this.f9016f, objArr);
                }
            }
            if (a.f8995a == C3531d.OFF && !(this.f9015e && this.f9016f == null)) {
                m12033b(method, objArr);
            }
            return m12028a(method);
        }
    }

    private void m12032b() {
        if (!this.f9015e) {
            try {
                this.f9016f = this.f9012b.mo6526a().m11844c(null);
            } catch (Throwable e) {
                C3511p.m11977a("OnOffTrackerProxy", (Object) this, "Could not create instance", e);
                C3502m.m11942a(e);
            }
            this.f9015e = true;
        }
    }

    private void m12033b(Method method, Object[] objArr) {
        if (this.f9014d.size() >= 15) {
            this.f9014d.remove(5);
        }
        this.f9014d.add(new C3534b(method, objArr));
    }

    private void m12034c() {
        m12032b();
        if (this.f9016f != null) {
            Iterator it = this.f9014d.iterator();
            while (it.hasNext()) {
                C3534b c3534b = (C3534b) it.next();
                try {
                    Object[] objArr = new Object[c3534b.f9008b.length];
                    WeakReference[] a = c3534b.f9008b;
                    int length = a.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        objArr[i2] = a[i].get();
                        i++;
                        i2 = i3;
                    }
                    c3534b.f9010d.invoke(this.f9016f, objArr);
                } catch (Exception e) {
                    C3502m.m11942a(e);
                }
            }
            this.f9014d.clear();
        }
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return m12029a(method, objArr);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return m12028a(method);
        }
    }
}

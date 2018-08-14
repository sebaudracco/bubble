package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.ax$com.moat.analytics.mobile.inm.ba;
import com.moat.analytics.mobile.inm.base.asserts.C3375a;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ax<T> implements InvocationHandler {
    private static final Object[] f8561a = new Object[0];
    private final ao f8562b;
    private final az<T> f8563c;
    private final bb<T> f8564d;
    private final LinkedList<ba> f8565e = new LinkedList();
    private final LinkedList<ba> f8566f = new LinkedList();
    private boolean f8567g;
    private T f8568h;

    ax(ao aoVar, az<T> azVar, bb<T> bbVar) {
        C3375a.m11556a(aoVar);
        C3375a.m11556a(azVar);
        C3375a.m11556a(bbVar);
        this.f8562b = aoVar;
        this.f8563c = azVar;
        this.f8564d = bbVar;
        aoVar.mo6481a(new ay(this));
    }

    static <T> T m11540a(ao aoVar, az<T> azVar, bb<T> bbVar) {
        return Proxy.newProxyInstance(bbVar.mo6471a().getClassLoader(), new Class[]{r0}, new ax(aoVar, azVar, bbVar));
    }

    private Object m11541a(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
        return null;
    }

    private Object m11542a(Method method, Object[] objArr) {
        if (Object.class.equals(method.getDeclaringClass())) {
            String name = method.getName();
            Class a = this.f8564d.mo6471a();
            if ("getClass".equals(name)) {
                return a;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object invoke = method.invoke(this, objArr);
            return (invoke + "").replace(ax.class.getName(), a.getName());
        } else if (this.f8567g && this.f8568h == null) {
            m11548c();
            return m11541a(method);
        } else {
            if (this.f8562b.mo6480a() == aq.ON) {
                m11545b();
                if (this.f8568h != null) {
                    return method.invoke(this.f8568h, objArr);
                }
            }
            if (this.f8562b.mo6480a() == aq.OFF && !(this.f8567g && this.f8568h == null)) {
                m11546b(method, objArr);
            }
            return m11541a(method);
        }
    }

    private void m11545b() {
        if (!this.f8567g) {
            try {
                this.f8568h = this.f8563c.mo6502a().m11562c(null);
            } catch (Exception e) {
            }
            this.f8567g = true;
        }
        if (this.f8568h != null) {
            List<LinkedList> linkedList = new LinkedList();
            linkedList.add(this.f8565e);
            linkedList.add(this.f8566f);
            for (LinkedList linkedList2 : linkedList) {
                Iterator it = linkedList2.iterator();
                while (it.hasNext()) {
                    ba baVar = (ba) it.next();
                    try {
                        Object[] objArr = new Object[baVar.f8572b.length];
                        WeakReference[] a = baVar.f8572b;
                        int length = a.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = i2 + 1;
                            objArr[i2] = a[i].get();
                            i++;
                            i2 = i3;
                        }
                        baVar.f8574d.invoke(this.f8568h, objArr);
                    } catch (Exception e2) {
                    }
                }
                linkedList2.clear();
            }
        }
    }

    private void m11546b(Method method, Object[] objArr) {
        if (this.f8565e.size() < 5) {
            this.f8565e.add(new ba(this, method, objArr));
            return;
        }
        if (this.f8566f.size() >= 10) {
            this.f8566f.removeFirst();
        }
        this.f8566f.add(new ba(this, method, objArr));
    }

    private void m11548c() {
        this.f8565e.clear();
        this.f8566f.clear();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return m11542a(method, objArr);
        } catch (Exception e) {
            C3376a.m11557a(e);
            return m11541a(method);
        }
    }
}

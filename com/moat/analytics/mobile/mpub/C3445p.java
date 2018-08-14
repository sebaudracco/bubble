package com.moat.analytics.mobile.mpub;

import android.support.annotation.VisibleForTesting;
import com.moat.analytics.mobile.mpub.C3460t.C3418b;
import com.moat.analytics.mobile.mpub.C3460t.C3456a;
import com.moat.analytics.mobile.mpub.base.asserts.Asserts;
import com.moat.analytics.mobile.mpub.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

class C3445p<T> implements InvocationHandler {
    private static final Object[] f8776 = new Object[0];
    private final C3405c<T> f8777;
    private boolean f8778;
    private final LinkedList<C3444d> f8779 = new LinkedList();
    private final Class<T> f8780;
    private T f8781;

    interface C3405c<T> {
        Optional<T> mo6511() throws C3442o;
    }

    class C34431 implements C3418b {
        private /* synthetic */ C3445p f8771;

        C34431(C3445p c3445p) {
            this.f8771 = c3445p;
        }

        public final void mo6514() throws C3442o {
            this.f8771.m11763();
        }
    }

    class C3444d {
        private final WeakReference[] f8772;
        private final Method f8773;
        private final LinkedList<Object> f8774;
        private /* synthetic */ C3445p f8775;

        private C3444d(C3445p c3445p, Method method, Object... objArr) {
            int i = 0;
            this.f8775 = c3445p;
            this.f8774 = new LinkedList();
            if (objArr == null) {
                objArr = C3445p.f8776;
            }
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f8774.add(obj);
                }
                int i3 = i2 + 1;
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2 = i3;
            }
            this.f8772 = weakReferenceArr;
            this.f8773 = method;
        }
    }

    @VisibleForTesting
    private C3445p(C3405c<T> c3405c, Class<T> cls) throws C3442o {
        Asserts.checkNotNull(c3405c);
        Asserts.checkNotNull(cls);
        this.f8777 = c3405c;
        this.f8780 = cls;
        C3460t.m11803().m11808(new C34431(this));
    }

    static <T> T m11762(C3405c<T> c3405c, Class<T> cls) throws C3442o {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C3445p(c3405c, cls));
    }

    private void m11763() throws C3442o {
        if (!this.f8778) {
            try {
                this.f8781 = this.f8777.mo6511().orElse(null);
            } catch (Exception e) {
                C3412a.m11644("OnOffTrackerProxy", this, "Could not create instance", e);
                C3442o.m11756(e);
            }
            this.f8778 = true;
        }
        if (this.f8781 != null) {
            Iterator it = this.f8779.iterator();
            while (it.hasNext()) {
                C3444d c3444d = (C3444d) it.next();
                try {
                    Object[] objArr = new Object[c3444d.f8772.length];
                    WeakReference[] ˊ = c3444d.f8772;
                    int length = ˊ.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        objArr[i2] = ˊ[i].get();
                        i++;
                        i2 = i3;
                    }
                    c3444d.f8773.invoke(this.f8781, objArr);
                } catch (Exception e2) {
                    C3442o.m11756(e2);
                }
            }
            this.f8779.clear();
        }
    }

    private static Boolean m11761(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
        return null;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            Class declaringClass = method.getDeclaringClass();
            C3460t ˏ = C3460t.m11803();
            if (Object.class.equals(declaringClass)) {
                String name = method.getName();
                if ("getClass".equals(name)) {
                    return this.f8780;
                }
                if (!"toString".equals(name)) {
                    return method.invoke(this, objArr);
                }
                Object invoke = method.invoke(this, objArr);
                return String.valueOf(invoke).replace(C3445p.class.getName(), this.f8780.getName());
            } else if (this.f8778 && this.f8781 == null) {
                this.f8779.clear();
                return C3445p.m11761(method);
            } else {
                if (ˏ.f8824 == C3456a.f8806) {
                    m11763();
                    if (this.f8781 != null) {
                        return method.invoke(this.f8781, objArr);
                    }
                }
                if (ˏ.f8824 == C3456a.f8807 && !(this.f8778 && this.f8781 == null)) {
                    if (this.f8779.size() >= 15) {
                        this.f8779.remove(5);
                    }
                    this.f8779.add(new C3444d(method, objArr));
                }
                return C3445p.m11761(method);
            }
        } catch (Exception e) {
            C3442o.m11756(e);
            return C3445p.m11761(method);
        }
    }
}

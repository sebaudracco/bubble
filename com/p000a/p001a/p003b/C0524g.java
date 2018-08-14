package com.p000a.p001a.p003b;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class C0524g<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean f236f = (!C0524g.class.desiredAssertionStatus());
    private static final Comparator<Comparable> f237g = new C05171();
    Comparator<? super K> f238a;
    C0523d<K, V> f239b;
    int f240c;
    int f241d;
    final C0523d<K, V> f242e;
    private C0520a f243h;
    private C0522b f244i;

    static class C05171 implements Comparator<Comparable> {
        C05171() {
        }

        public int m363a(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m363a((Comparable) obj, (Comparable) obj2);
        }
    }

    private abstract class C0518c<T> implements Iterator<T> {
        C0523d<K, V> f220b = this.f223e.f242e.f231d;
        C0523d<K, V> f221c = null;
        int f222d = this.f223e.f241d;
        final /* synthetic */ C0524g f223e;

        C0518c(C0524g c0524g) {
            this.f223e = c0524g;
        }

        final C0523d<K, V> m364b() {
            C0523d<K, V> c0523d = this.f220b;
            if (c0523d == this.f223e.f242e) {
                throw new NoSuchElementException();
            } else if (this.f223e.f241d != this.f222d) {
                throw new ConcurrentModificationException();
            } else {
                this.f220b = c0523d.f231d;
                this.f221c = c0523d;
                return c0523d;
            }
        }

        public final boolean hasNext() {
            return this.f220b != this.f223e.f242e;
        }

        public final void remove() {
            if (this.f221c == null) {
                throw new IllegalStateException();
            }
            this.f223e.m376a(this.f221c, true);
            this.f221c = null;
            this.f222d = this.f223e.f241d;
        }
    }

    class C0520a extends AbstractSet<Entry<K, V>> {
        final /* synthetic */ C0524g f225a;

        class C05191 extends C0518c<Entry<K, V>> {
            final /* synthetic */ C0520a f224a;

            C05191(C0520a c0520a) {
                this.f224a = c0520a;
                super(c0520a.f225a);
            }

            public Entry<K, V> m365a() {
                return m364b();
            }

            public /* synthetic */ Object next() {
                return m365a();
            }
        }

        C0520a(C0524g c0524g) {
            this.f225a = c0524g;
        }

        public void clear() {
            this.f225a.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && this.f225a.m375a((Entry) obj) != null;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new C05191(this);
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            C0523d a = this.f225a.m375a((Entry) obj);
            if (a == null) {
                return false;
            }
            this.f225a.m376a(a, true);
            return true;
        }

        public int size() {
            return this.f225a.f240c;
        }
    }

    final class C0522b extends AbstractSet<K> {
        final /* synthetic */ C0524g f227a;

        class C05211 extends C0518c<K> {
            final /* synthetic */ C0522b f226a;

            C05211(C0522b c0522b) {
                this.f226a = c0522b;
                super(c0522b.f227a);
            }

            public K next() {
                return m364b().f233f;
            }
        }

        C0522b(C0524g c0524g) {
            this.f227a = c0524g;
        }

        public void clear() {
            this.f227a.clear();
        }

        public boolean contains(Object obj) {
            return this.f227a.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new C05211(this);
        }

        public boolean remove(Object obj) {
            return this.f227a.m377b(obj) != null;
        }

        public int size() {
            return this.f227a.f240c;
        }
    }

    static final class C0523d<K, V> implements Entry<K, V> {
        C0523d<K, V> f228a;
        C0523d<K, V> f229b;
        C0523d<K, V> f230c;
        C0523d<K, V> f231d;
        C0523d<K, V> f232e;
        final K f233f;
        V f234g;
        int f235h;

        C0523d() {
            this.f233f = null;
            this.f232e = this;
            this.f231d = this;
        }

        C0523d(C0523d<K, V> c0523d, K k, C0523d<K, V> c0523d2, C0523d<K, V> c0523d3) {
            this.f228a = c0523d;
            this.f233f = k;
            this.f235h = 1;
            this.f231d = c0523d2;
            this.f232e = c0523d3;
            c0523d3.f231d = this;
            c0523d2.f232e = this;
        }

        public C0523d<K, V> m366a() {
            C0523d<K, V> c0523d;
            for (C0523d<K, V> c0523d2 = this.f229b; c0523d2 != null; c0523d2 = c0523d2.f229b) {
                c0523d = c0523d2;
            }
            return c0523d;
        }

        public C0523d<K, V> m367b() {
            C0523d<K, V> c0523d;
            for (C0523d<K, V> c0523d2 = this.f230c; c0523d2 != null; c0523d2 = c0523d2.f230c) {
                c0523d = c0523d2;
            }
            return c0523d;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f233f == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.f233f.equals(entry.getKey())) {
                return false;
            }
            if (this.f234g == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.f234g.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public K getKey() {
            return this.f233f;
        }

        public V getValue() {
            return this.f234g;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f233f == null ? 0 : this.f233f.hashCode();
            if (this.f234g != null) {
                i = this.f234g.hashCode();
            }
            return hashCode ^ i;
        }

        public V setValue(V v) {
            V v2 = this.f234g;
            this.f234g = v;
            return v2;
        }

        public String toString() {
            return this.f233f + "=" + this.f234g;
        }
    }

    public C0524g() {
        this(f237g);
    }

    public C0524g(Comparator<? super K> comparator) {
        Comparator comparator2;
        this.f240c = 0;
        this.f241d = 0;
        this.f242e = new C0523d();
        if (comparator == null) {
            comparator2 = f237g;
        }
        this.f238a = comparator2;
    }

    private void m368a(C0523d<K, V> c0523d) {
        int i = 0;
        C0523d c0523d2 = c0523d.f229b;
        C0523d c0523d3 = c0523d.f230c;
        C0523d c0523d4 = c0523d3.f229b;
        C0523d c0523d5 = c0523d3.f230c;
        c0523d.f230c = c0523d4;
        if (c0523d4 != null) {
            c0523d4.f228a = c0523d;
        }
        m369a((C0523d) c0523d, c0523d3);
        c0523d3.f229b = c0523d;
        c0523d.f228a = c0523d3;
        c0523d.f235h = Math.max(c0523d2 != null ? c0523d2.f235h : 0, c0523d4 != null ? c0523d4.f235h : 0) + 1;
        int i2 = c0523d.f235h;
        if (c0523d5 != null) {
            i = c0523d5.f235h;
        }
        c0523d3.f235h = Math.max(i2, i) + 1;
    }

    private void m369a(C0523d<K, V> c0523d, C0523d<K, V> c0523d2) {
        C0523d c0523d3 = c0523d.f228a;
        c0523d.f228a = null;
        if (c0523d2 != null) {
            c0523d2.f228a = c0523d3;
        }
        if (c0523d3 == null) {
            this.f239b = c0523d2;
        } else if (c0523d3.f229b == c0523d) {
            c0523d3.f229b = c0523d2;
        } else if (f236f || c0523d3.f230c == c0523d) {
            c0523d3.f230c = c0523d2;
        } else {
            throw new AssertionError();
        }
    }

    private boolean m370a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void m371b(C0523d<K, V> c0523d) {
        int i = 0;
        C0523d c0523d2 = c0523d.f229b;
        C0523d c0523d3 = c0523d.f230c;
        C0523d c0523d4 = c0523d2.f229b;
        C0523d c0523d5 = c0523d2.f230c;
        c0523d.f229b = c0523d5;
        if (c0523d5 != null) {
            c0523d5.f228a = c0523d;
        }
        m369a((C0523d) c0523d, c0523d2);
        c0523d2.f230c = c0523d;
        c0523d.f228a = c0523d2;
        c0523d.f235h = Math.max(c0523d3 != null ? c0523d3.f235h : 0, c0523d5 != null ? c0523d5.f235h : 0) + 1;
        int i2 = c0523d.f235h;
        if (c0523d4 != null) {
            i = c0523d4.f235h;
        }
        c0523d2.f235h = Math.max(i2, i) + 1;
    }

    private void m372b(C0523d<K, V> c0523d, boolean z) {
        C0523d c0523d2;
        while (c0523d2 != null) {
            C0523d c0523d3 = c0523d2.f229b;
            C0523d c0523d4 = c0523d2.f230c;
            int i = c0523d3 != null ? c0523d3.f235h : 0;
            int i2 = c0523d4 != null ? c0523d4.f235h : 0;
            int i3 = i - i2;
            C0523d c0523d5;
            if (i3 == -2) {
                c0523d3 = c0523d4.f229b;
                c0523d5 = c0523d4.f230c;
                i2 = (c0523d3 != null ? c0523d3.f235h : 0) - (c0523d5 != null ? c0523d5.f235h : 0);
                if (i2 == -1 || (i2 == 0 && !z)) {
                    m368a(c0523d2);
                } else if (f236f || i2 == 1) {
                    m371b(c0523d4);
                    m368a(c0523d2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                c0523d4 = c0523d3.f229b;
                c0523d5 = c0523d3.f230c;
                i2 = (c0523d4 != null ? c0523d4.f235h : 0) - (c0523d5 != null ? c0523d5.f235h : 0);
                if (i2 == 1 || (i2 == 0 && !z)) {
                    m371b(c0523d2);
                } else if (f236f || i2 == -1) {
                    m368a(c0523d3);
                    m371b(c0523d2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                c0523d2.f235h = i + 1;
                if (z) {
                    return;
                }
            } else if (f236f || i3 == -1 || i3 == 1) {
                c0523d2.f235h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            c0523d2 = c0523d2.f228a;
        }
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    C0523d<K, V> m373a(Object obj) {
        C0523d<K, V> c0523d = null;
        if (obj != null) {
            try {
                c0523d = m374a(obj, false);
            } catch (ClassCastException e) {
            }
        }
        return c0523d;
    }

    C0523d<K, V> m374a(K k, boolean z) {
        int i;
        Comparator comparator = this.f238a;
        C0523d<K, V> c0523d = this.f239b;
        if (c0523d != null) {
            int compareTo;
            Comparable comparable = comparator == f237g ? (Comparable) k : null;
            while (true) {
                compareTo = comparable != null ? comparable.compareTo(c0523d.f233f) : comparator.compare(k, c0523d.f233f);
                if (compareTo == 0) {
                    return c0523d;
                }
                C0523d<K, V> c0523d2 = compareTo < 0 ? c0523d.f229b : c0523d.f230c;
                if (c0523d2 == null) {
                    break;
                }
                c0523d = c0523d2;
            }
            int i2 = compareTo;
            C0523d c0523d3 = c0523d;
            i = i2;
        } else {
            C0523d<K, V> c0523d4 = c0523d;
            i = 0;
        }
        if (!z) {
            return null;
        }
        C0523d<K, V> c0523d5;
        C0523d c0523d6 = this.f242e;
        if (c0523d3 != null) {
            c0523d5 = new C0523d(c0523d3, k, c0523d6, c0523d6.f232e);
            if (i < 0) {
                c0523d3.f229b = c0523d5;
            } else {
                c0523d3.f230c = c0523d5;
            }
            m372b(c0523d3, true);
        } else if (comparator != f237g || (k instanceof Comparable)) {
            c0523d5 = new C0523d(c0523d3, k, c0523d6, c0523d6.f232e);
            this.f239b = c0523d5;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.f240c++;
        this.f241d++;
        return c0523d5;
    }

    C0523d<K, V> m375a(Entry<?, ?> entry) {
        C0523d<K, V> a = m373a(entry.getKey());
        Object obj = (a == null || !m370a(a.f234g, entry.getValue())) ? null : 1;
        return obj != null ? a : null;
    }

    void m376a(C0523d<K, V> c0523d, boolean z) {
        int i = 0;
        if (z) {
            c0523d.f232e.f231d = c0523d.f231d;
            c0523d.f231d.f232e = c0523d.f232e;
        }
        C0523d c0523d2 = c0523d.f229b;
        C0523d c0523d3 = c0523d.f230c;
        C0523d c0523d4 = c0523d.f228a;
        if (c0523d2 == null || c0523d3 == null) {
            if (c0523d2 != null) {
                m369a((C0523d) c0523d, c0523d2);
                c0523d.f229b = null;
            } else if (c0523d3 != null) {
                m369a((C0523d) c0523d, c0523d3);
                c0523d.f230c = null;
            } else {
                m369a((C0523d) c0523d, null);
            }
            m372b(c0523d4, false);
            this.f240c--;
            this.f241d++;
            return;
        }
        int i2;
        c0523d2 = c0523d2.f235h > c0523d3.f235h ? c0523d2.m367b() : c0523d3.m366a();
        m376a(c0523d2, false);
        c0523d4 = c0523d.f229b;
        if (c0523d4 != null) {
            i2 = c0523d4.f235h;
            c0523d2.f229b = c0523d4;
            c0523d4.f228a = c0523d2;
            c0523d.f229b = null;
        } else {
            i2 = 0;
        }
        c0523d4 = c0523d.f230c;
        if (c0523d4 != null) {
            i = c0523d4.f235h;
            c0523d2.f230c = c0523d4;
            c0523d4.f228a = c0523d2;
            c0523d.f230c = null;
        }
        c0523d2.f235h = Math.max(i2, i) + 1;
        m369a((C0523d) c0523d, c0523d2);
    }

    C0523d<K, V> m377b(Object obj) {
        C0523d a = m373a(obj);
        if (a != null) {
            m376a(a, true);
        }
        return a;
    }

    public void clear() {
        this.f239b = null;
        this.f240c = 0;
        this.f241d++;
        C0523d c0523d = this.f242e;
        c0523d.f232e = c0523d;
        c0523d.f231d = c0523d;
    }

    public boolean containsKey(Object obj) {
        return m373a(obj) != null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set set = this.f243h;
        if (set != null) {
            return set;
        }
        set = new C0520a(this);
        this.f243h = set;
        return set;
    }

    public V get(Object obj) {
        C0523d a = m373a(obj);
        return a != null ? a.f234g : null;
    }

    public Set<K> keySet() {
        Set set = this.f244i;
        if (set != null) {
            return set;
        }
        set = new C0522b(this);
        this.f244i = set;
        return set;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        C0523d a = m374a((Object) k, true);
        V v2 = a.f234g;
        a.f234g = v;
        return v2;
    }

    public V remove(Object obj) {
        C0523d b = m377b(obj);
        return b != null ? b.f234g : null;
    }

    public int size() {
        return this.f240c;
    }
}

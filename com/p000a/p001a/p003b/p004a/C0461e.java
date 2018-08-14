package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0555g;
import com.p000a.p001a.C0559l;
import com.p000a.p001a.C0560m;
import com.p000a.p001a.C0561o;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0544b;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

public final class C0461e extends C0460a {
    private static final Reader f43b = new C04591();
    private static final Object f44c = new Object();
    private Object[] f45d;
    private int f46e;
    private String[] f47f;
    private int[] f48g;

    static class C04591 extends Reader {
        C04591() {
        }

        public void close() {
            throw new AssertionError();
        }

        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    private void m73a(C0544b c0544b) {
        if (mo1801f() != c0544b) {
            throw new IllegalStateException("Expected " + c0544b + " but was " + mo1801f() + m77v());
        }
    }

    private void m74a(Object obj) {
        if (this.f46e == this.f45d.length) {
            Object obj2 = new Object[(this.f46e * 2)];
            Object obj3 = new int[(this.f46e * 2)];
            Object obj4 = new String[(this.f46e * 2)];
            System.arraycopy(this.f45d, 0, obj2, 0, this.f46e);
            System.arraycopy(this.f48g, 0, obj3, 0, this.f46e);
            System.arraycopy(this.f47f, 0, obj4, 0, this.f46e);
            this.f45d = obj2;
            this.f48g = obj3;
            this.f47f = obj4;
        }
        Object[] objArr = this.f45d;
        int i = this.f46e;
        this.f46e = i + 1;
        objArr[i] = obj;
    }

    private Object m75t() {
        return this.f45d[this.f46e - 1];
    }

    private Object m76u() {
        Object[] objArr = this.f45d;
        int i = this.f46e - 1;
        this.f46e = i;
        Object obj = objArr[i];
        this.f45d[this.f46e] = null;
        return obj;
    }

    private String m77v() {
        return " at path " + mo1811p();
    }

    public void mo1795a() {
        m73a(C0544b.BEGIN_ARRAY);
        m74a(((C0555g) m75t()).iterator());
        this.f48g[this.f46e - 1] = 0;
    }

    public void mo1796b() {
        m73a(C0544b.END_ARRAY);
        m76u();
        m76u();
        if (this.f46e > 0) {
            int[] iArr = this.f48g;
            int i = this.f46e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void mo1797c() {
        m73a(C0544b.BEGIN_OBJECT);
        m74a(((C0560m) m75t()).m476o().iterator());
    }

    public void close() {
        this.f45d = new Object[]{f44c};
        this.f46e = 1;
    }

    public void mo1799d() {
        m73a(C0544b.END_OBJECT);
        m76u();
        m76u();
        if (this.f46e > 0) {
            int[] iArr = this.f48g;
            int i = this.f46e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean mo1800e() {
        C0544b f = mo1801f();
        return (f == C0544b.END_OBJECT || f == C0544b.END_ARRAY) ? false : true;
    }

    public C0544b mo1801f() {
        if (this.f46e == 0) {
            return C0544b.END_DOCUMENT;
        }
        Object t = m75t();
        if (t instanceof Iterator) {
            boolean z = this.f45d[this.f46e - 2] instanceof C0560m;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? C0544b.END_OBJECT : C0544b.END_ARRAY;
            } else {
                if (z) {
                    return C0544b.NAME;
                }
                m74a(it.next());
                return mo1801f();
            }
        } else if (t instanceof C0560m) {
            return C0544b.BEGIN_OBJECT;
        } else {
            if (t instanceof C0555g) {
                return C0544b.BEGIN_ARRAY;
            }
            if (t instanceof C0561o) {
                C0561o c0561o = (C0561o) t;
                if (c0561o.m489q()) {
                    return C0544b.STRING;
                }
                if (c0561o.m487o()) {
                    return C0544b.BOOLEAN;
                }
                if (c0561o.m488p()) {
                    return C0544b.NUMBER;
                }
                throw new AssertionError();
            } else if (t instanceof C0559l) {
                return C0544b.NULL;
            } else {
                if (t == f44c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    public String mo1802g() {
        m73a(C0544b.NAME);
        Entry entry = (Entry) ((Iterator) m75t()).next();
        String str = (String) entry.getKey();
        this.f47f[this.f46e - 1] = str;
        m74a(entry.getValue());
        return str;
    }

    public String mo1803h() {
        C0544b f = mo1801f();
        if (f == C0544b.STRING || f == C0544b.NUMBER) {
            String b = ((C0561o) m76u()).mo1835b();
            if (this.f46e > 0) {
                int[] iArr = this.f48g;
                int i = this.f46e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return b;
        }
        throw new IllegalStateException("Expected " + C0544b.STRING + " but was " + f + m77v());
    }

    public boolean mo1804i() {
        m73a(C0544b.BOOLEAN);
        boolean f = ((C0561o) m76u()).mo1839f();
        if (this.f46e > 0) {
            int[] iArr = this.f48g;
            int i = this.f46e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return f;
    }

    public void mo1805j() {
        m73a(C0544b.NULL);
        m76u();
        if (this.f46e > 0) {
            int[] iArr = this.f48g;
            int i = this.f46e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double mo1806k() {
        C0544b f = mo1801f();
        if (f == C0544b.NUMBER || f == C0544b.STRING) {
            double c = ((C0561o) m75t()).mo1836c();
            if (m70q() || !(Double.isNaN(c) || Double.isInfinite(c))) {
                m76u();
                if (this.f46e > 0) {
                    int[] iArr = this.f48g;
                    int i = this.f46e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return c;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + c);
        }
        throw new IllegalStateException("Expected " + C0544b.NUMBER + " but was " + f + m77v());
    }

    public long mo1807l() {
        C0544b f = mo1801f();
        if (f == C0544b.NUMBER || f == C0544b.STRING) {
            long d = ((C0561o) m75t()).mo1837d();
            m76u();
            if (this.f46e > 0) {
                int[] iArr = this.f48g;
                int i = this.f46e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return d;
        }
        throw new IllegalStateException("Expected " + C0544b.NUMBER + " but was " + f + m77v());
    }

    public int mo1808m() {
        C0544b f = mo1801f();
        if (f == C0544b.NUMBER || f == C0544b.STRING) {
            int e = ((C0561o) m75t()).mo1838e();
            m76u();
            if (this.f46e > 0) {
                int[] iArr = this.f48g;
                int i = this.f46e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return e;
        }
        throw new IllegalStateException("Expected " + C0544b.NUMBER + " but was " + f + m77v());
    }

    public void mo1809n() {
        if (mo1801f() == C0544b.NAME) {
            mo1802g();
            this.f47f[this.f46e - 2] = "null";
        } else {
            m76u();
            if (this.f46e > 0) {
                this.f47f[this.f46e - 1] = "null";
            }
        }
        if (this.f46e > 0) {
            int[] iArr = this.f48g;
            int i = this.f46e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void mo1810o() {
        m73a(C0544b.NAME);
        Entry entry = (Entry) ((Iterator) m75t()).next();
        m74a(entry.getValue());
        m74a(new C0561o((String) entry.getKey()));
    }

    public String mo1811p() {
        StringBuilder append = new StringBuilder().append('$');
        int i = 0;
        while (i < this.f46e) {
            if (this.f45d[i] instanceof C0555g) {
                i++;
                if (this.f45d[i] instanceof Iterator) {
                    append.append('[').append(this.f48g[i]).append(']');
                }
            } else if (this.f45d[i] instanceof C0560m) {
                i++;
                if (this.f45d[i] instanceof Iterator) {
                    append.append('.');
                    if (this.f47f[i] != null) {
                        append.append(this.f47f[i]);
                    }
                }
            }
            i++;
        }
        return append.toString();
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}

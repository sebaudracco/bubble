package com.p000a.p001a.p006d;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0463c implements Closeable, Flushable {
    private static final String[] f49a = new String[128];
    private static final String[] f50b = ((String[]) f49a.clone());
    private final Writer f51c;
    private int[] f52d = new int[32];
    private int f53e = 0;
    private String f54f;
    private String f55g;
    private boolean f56h;
    private boolean f57i;
    private String f58j;
    private boolean f59k;

    static {
        for (int i = 0; i <= 31; i++) {
            f49a[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        f49a[34] = "\\\"";
        f49a[92] = "\\\\";
        f49a[9] = "\\t";
        f49a[8] = "\\b";
        f49a[10] = "\\n";
        f49a[13] = "\\r";
        f49a[12] = "\\f";
        f50b[60] = "\\u003c";
        f50b[62] = "\\u003e";
        f50b[38] = "\\u0026";
        f50b[61] = "\\u003d";
        f50b[39] = "\\u0027";
    }

    public C0463c(Writer writer) {
        m97a(6);
        this.f55g = ":";
        this.f59k = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.f51c = writer;
    }

    private int mo1818a() {
        if (this.f53e != 0) {
            return this.f52d[this.f53e - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private C0463c m95a(int i, int i2, String str) {
        int a = mo1818a();
        if (a != i2 && a != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.f58j != null) {
            throw new IllegalStateException("Dangling name: " + this.f58j);
        } else {
            this.f53e--;
            if (a == i2) {
                m101k();
            }
            this.f51c.write(str);
            return this;
        }
    }

    private C0463c m96a(int i, String str) {
        m103m();
        m97a(i);
        this.f51c.write(str);
        return this;
    }

    private void m97a(int i) {
        if (this.f53e == this.f52d.length) {
            Object obj = new int[(this.f53e * 2)];
            System.arraycopy(this.f52d, 0, obj, 0, this.f53e);
            this.f52d = obj;
        }
        int[] iArr = this.f52d;
        int i2 = this.f53e;
        this.f53e = i2 + 1;
        iArr[i2] = i;
    }

    private void m98b(int i) {
        this.f52d[this.f53e - 1] = i;
    }

    private void m99d(String str) {
        int i = 0;
        String[] strArr = this.f57i ? f50b : f49a;
        this.f51c.write("\"");
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            String str2;
            if (charAt < '') {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
                if (i < i2) {
                    this.f51c.write(str, i, i2 - i);
                }
                this.f51c.write(str2);
                i = i2 + 1;
            } else {
                if (charAt == ' ') {
                    str2 = "\\u2028";
                } else if (charAt == ' ') {
                    str2 = "\\u2029";
                }
                if (i < i2) {
                    this.f51c.write(str, i, i2 - i);
                }
                this.f51c.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.f51c.write(str, i, length - i);
        }
        this.f51c.write("\"");
    }

    private void m100j() {
        if (this.f58j != null) {
            m102l();
            m99d(this.f58j);
            this.f58j = null;
        }
    }

    private void m101k() {
        if (this.f54f != null) {
            this.f51c.write("\n");
            int i = this.f53e;
            for (int i2 = 1; i2 < i; i2++) {
                this.f51c.write(this.f54f);
            }
        }
    }

    private void m102l() {
        int a = mo1818a();
        if (a == 5) {
            this.f51c.write(44);
        } else if (a != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        m101k();
        m98b(4);
    }

    private void m103m() {
        switch (mo1818a()) {
            case 1:
                m98b(2);
                m101k();
                return;
            case 2:
                this.f51c.append(',');
                m101k();
                return;
            case 4:
                this.f51c.append(this.f55g);
                m98b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.f56h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        m98b(7);
    }

    public C0463c mo1813a(long j) {
        m100j();
        m103m();
        this.f51c.write(Long.toString(j));
        return this;
    }

    public C0463c mo1814a(Boolean bool) {
        if (bool == null) {
            return mo1825f();
        }
        m100j();
        m103m();
        this.f51c.write(bool.booleanValue() ? SchemaSymbols.ATTVAL_TRUE : SchemaSymbols.ATTVAL_FALSE);
        return this;
    }

    public C0463c mo1815a(Number number) {
        if (number == null) {
            return mo1825f();
        }
        m100j();
        CharSequence obj = number.toString();
        if (this.f56h || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            m103m();
            this.f51c.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public C0463c mo1816a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.f58j != null) {
            throw new IllegalStateException();
        } else if (this.f53e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.f58j = str;
            return this;
        }
    }

    public C0463c mo1817a(boolean z) {
        m100j();
        m103m();
        this.f51c.write(z ? SchemaSymbols.ATTVAL_TRUE : SchemaSymbols.ATTVAL_FALSE);
        return this;
    }

    public C0463c mo1819b() {
        m100j();
        return m96a(1, "[");
    }

    public C0463c mo1820b(String str) {
        if (str == null) {
            return mo1825f();
        }
        m100j();
        m103m();
        m99d(str);
        return this;
    }

    public final void m111b(boolean z) {
        this.f56h = z;
    }

    public C0463c mo1821c() {
        return m95a(1, 2, "]");
    }

    public final void m113c(String str) {
        if (str.length() == 0) {
            this.f54f = null;
            this.f55g = ":";
            return;
        }
        this.f54f = str;
        this.f55g = ": ";
    }

    public final void m114c(boolean z) {
        this.f57i = z;
    }

    public void close() {
        this.f51c.close();
        int i = this.f53e;
        if (i > 1 || (i == 1 && this.f52d[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f53e = 0;
    }

    public C0463c mo1823d() {
        m100j();
        return m96a(3, "{");
    }

    public final void m116d(boolean z) {
        this.f59k = z;
    }

    public C0463c mo1824e() {
        return m95a(3, 5, "}");
    }

    public C0463c mo1825f() {
        if (this.f58j != null) {
            if (this.f59k) {
                m100j();
            } else {
                this.f58j = null;
                return this;
            }
        }
        m103m();
        this.f51c.write("null");
        return this;
    }

    public void flush() {
        if (this.f53e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f51c.flush();
    }

    public boolean m119g() {
        return this.f56h;
    }

    public final boolean m120h() {
        return this.f57i;
    }

    public final boolean m121i() {
        return this.f59k;
    }
}

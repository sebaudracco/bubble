package com.p000a.p001a.p003b;

import com.p000a.p001a.C0554j;
import com.p000a.p001a.C0558k;
import com.p000a.p001a.C0559l;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p003b.p004a.C0496n;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import java.io.Writer;

public final class C0528j {

    private static final class C0527a extends Writer {
        private final Appendable f248a;
        private final C0526a f249b = new C0526a();

        static class C0526a implements CharSequence {
            char[] f247a;

            C0526a() {
            }

            public char charAt(int i) {
                return this.f247a[i];
            }

            public int length() {
                return this.f247a.length;
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.f247a, i, i2 - i);
            }
        }

        C0527a(Appendable appendable) {
            this.f248a = appendable;
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(int i) {
            this.f248a.append((char) i);
        }

        public void write(char[] cArr, int i, int i2) {
            this.f249b.f247a = cArr;
            this.f248a.append(this.f249b, i, i + i2);
        }
    }

    public static C0554j m381a(C0460a c0460a) {
        Object obj = 1;
        try {
            c0460a.mo1801f();
            obj = null;
            return (C0554j) C0496n.f145X.mo1794b(c0460a);
        } catch (Throwable e) {
            if (obj != null) {
                return C0559l.f295a;
            }
            throw new C0563r(e);
        } catch (Throwable e2) {
            throw new C0563r(e2);
        } catch (Throwable e22) {
            throw new C0558k(e22);
        } catch (Throwable e222) {
            throw new C0563r(e222);
        }
    }

    public static Writer m382a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new C0527a(appendable);
    }

    public static void m383a(C0554j c0554j, C0463c c0463c) {
        C0496n.f145X.mo1793a(c0463c, c0554j);
    }
}

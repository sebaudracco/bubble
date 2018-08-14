package com.p000a.p001a.p006d;

import com.p000a.p001a.p003b.C0515e;
import com.p000a.p001a.p003b.p004a.C0461e;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0460a implements Closeable {
    private static final char[] f27b = ")]}'\n".toCharArray();
    int f28a = 0;
    private final Reader f29c;
    private boolean f30d = false;
    private final char[] f31e = new char[1024];
    private int f32f = 0;
    private int f33g = 0;
    private int f34h = 0;
    private int f35i = 0;
    private long f36j;
    private int f37k;
    private String f38l;
    private int[] f39m = new int[32];
    private int f40n = 0;
    private String[] f41o;
    private int[] f42p;

    static class C05431 extends C0515e {
        C05431() {
        }

        public void mo1833a(C0460a c0460a) {
            if (c0460a instanceof C0461e) {
                ((C0461e) c0460a).mo1810o();
                return;
            }
            int i = c0460a.f28a;
            if (i == 0) {
                i = c0460a.m71r();
            }
            if (i == 13) {
                c0460a.f28a = 9;
            } else if (i == 12) {
                c0460a.f28a = 8;
            } else if (i == 14) {
                c0460a.f28a = 10;
            } else {
                throw new IllegalStateException("Expected a name but was " + c0460a.mo1801f() + c0460a.m72s());
            }
        }
    }

    static {
        C0515e.f218a = new C05431();
    }

    public C0460a(Reader reader) {
        int[] iArr = this.f39m;
        int i = this.f40n;
        this.f40n = i + 1;
        iArr[i] = 6;
        this.f41o = new String[32];
        this.f42p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f29c = reader;
    }

    private void m38a(int i) {
        if (this.f40n == this.f39m.length) {
            Object obj = new int[(this.f40n * 2)];
            Object obj2 = new int[(this.f40n * 2)];
            Object obj3 = new String[(this.f40n * 2)];
            System.arraycopy(this.f39m, 0, obj, 0, this.f40n);
            System.arraycopy(this.f42p, 0, obj2, 0, this.f40n);
            System.arraycopy(this.f41o, 0, obj3, 0, this.f40n);
            this.f39m = obj;
            this.f42p = obj2;
            this.f41o = obj3;
        }
        int[] iArr = this.f39m;
        int i2 = this.f40n;
        this.f40n = i2 + 1;
        iArr[i2] = i;
    }

    private boolean m39a(char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                m50w();
                break;
            default:
                return true;
        }
        return false;
    }

    private boolean m40a(String str) {
        int length = str.length();
        while (true) {
            if (this.f32f + length > this.f33g && !m44b(length)) {
                return false;
            }
            if (this.f31e[this.f32f] == '\n') {
                this.f34h++;
                this.f35i = this.f32f + 1;
            } else {
                int i = 0;
                while (i < length) {
                    if (this.f31e[this.f32f + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.f32f++;
        }
    }

    private int m41b(boolean z) {
        char[] cArr = this.f31e;
        int i = this.f32f;
        int i2 = this.f33g;
        while (true) {
            if (i == i2) {
                this.f32f = i;
                if (m44b(1)) {
                    i = this.f32f;
                    i2 = this.f33g;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + m72s());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f34h++;
                this.f35i = i3;
                i = i3;
            } else if (c == ' ' || c == '\r') {
                i = i3;
            } else if (c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.f32f = i3;
                if (i3 == i2) {
                    this.f32f--;
                    boolean b = m44b(2);
                    this.f32f++;
                    if (!b) {
                        return c;
                    }
                }
                m50w();
                switch (cArr[this.f32f]) {
                    case '*':
                        this.f32f++;
                        if (m40a("*/")) {
                            i = this.f32f + 2;
                            i2 = this.f33g;
                            break;
                        }
                        throw m42b("Unterminated comment");
                    case '/':
                        this.f32f++;
                        m51x();
                        i = this.f32f;
                        i2 = this.f33g;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.f32f = i3;
                m50w();
                m51x();
                i = this.f32f;
                i2 = this.f33g;
            } else {
                this.f32f = i3;
                return c;
            }
        }
    }

    private IOException m42b(String str) {
        throw new C0545d(str + m72s());
    }

    private String m43b(char c) {
        char[] cArr = this.f31e;
        StringBuilder stringBuilder = null;
        do {
            int i = this.f32f;
            int i2 = this.f33g;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f32f = i4;
                    i2 = (i4 - i) - 1;
                    if (stringBuilder == null) {
                        return new String(cArr, i, i2);
                    }
                    stringBuilder.append(cArr, i, i2);
                    return stringBuilder.toString();
                }
                StringBuilder stringBuilder2;
                int i5;
                int i6;
                if (c2 == '\\') {
                    this.f32f = i4;
                    i2 = (i4 - i) - 1;
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder(Math.max((i2 + 1) * 2, 16));
                    }
                    stringBuilder.append(cArr, i, i2);
                    stringBuilder.append(m52y());
                    i = this.f32f;
                    stringBuilder2 = stringBuilder;
                    i5 = i;
                    i6 = i;
                    i = this.f33g;
                    i2 = i6;
                } else {
                    if (c2 == '\n') {
                        this.f34h++;
                        this.f35i = i4;
                    }
                    i6 = i;
                    i = i2;
                    i2 = i4;
                    stringBuilder2 = stringBuilder;
                    i5 = i6;
                }
                i3 = i2;
                i2 = i;
                i = i5;
                stringBuilder = stringBuilder2;
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder(Math.max((i3 - i) * 2, 16));
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.f32f = i3;
        } while (m44b(1));
        throw m42b("Unterminated string");
    }

    private boolean m44b(int i) {
        Object obj = this.f31e;
        this.f35i -= this.f32f;
        if (this.f33g != this.f32f) {
            this.f33g -= this.f32f;
            System.arraycopy(obj, this.f32f, obj, 0, this.f33g);
        } else {
            this.f33g = 0;
        }
        this.f32f = 0;
        do {
            int read = this.f29c.read(obj, this.f33g, obj.length - this.f33g);
            if (read == -1) {
                return false;
            }
            this.f33g = read + this.f33g;
            if (this.f34h == 0 && this.f35i == 0 && this.f33g > 0 && obj[0] == '﻿') {
                this.f32f++;
                this.f35i++;
                i++;
            }
        } while (this.f33g < i);
        return true;
    }

    private void m45c(char c) {
        char[] cArr = this.f31e;
        do {
            int i = this.f32f;
            int i2 = this.f33g;
            int i3 = i;
            while (i3 < i2) {
                i = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f32f = i;
                    return;
                }
                if (c2 == '\\') {
                    this.f32f = i;
                    m52y();
                    i = this.f32f;
                    i2 = this.f33g;
                } else if (c2 == '\n') {
                    this.f34h++;
                    this.f35i = i;
                }
                i3 = i;
            }
            this.f32f = i3;
        } while (m44b(1));
        throw m42b("Unterminated string");
    }

    private int mo1810o() {
        String str;
        int i;
        char c = this.f31e[this.f32f];
        String str2;
        if (c == 't' || c == 'T') {
            str = SchemaSymbols.ATTVAL_TRUE;
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = SchemaSymbols.ATTVAL_FALSE;
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.f32f + i2 >= this.f33g && !m44b(i2 + 1)) {
                return 0;
            }
            char c2 = this.f31e[this.f32f + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.f32f + length < this.f33g || m44b(length + 1)) && m39a(this.f31e[this.f32f + length])) {
            return 0;
        }
        this.f32f += length;
        this.f28a = i;
        return i;
    }

    private int m47t() {
        char[] cArr = this.f31e;
        int i = this.f32f;
        long j = 0;
        Object obj = null;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.f33g;
        int i6 = i;
        while (true) {
            Object obj2;
            if (i6 + i4 == i5) {
                if (i4 == cArr.length) {
                    return 0;
                }
                if (m44b(i4 + 1)) {
                    i6 = this.f32f;
                    i5 = this.f33g;
                } else if (i3 != 2 && i2 != 0 && ((j != Long.MIN_VALUE || obj != null) && (j != 0 || obj == null))) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.f36j = j;
                    this.f32f += i4;
                    this.f28a = 15;
                    return 15;
                } else if (i3 == 2 && i3 != 4 && i3 != 7) {
                    return 0;
                } else {
                    this.f37k = i4;
                    this.f28a = 16;
                    return 16;
                }
            }
            char c = cArr[i6 + i4];
            int i7;
            switch (c) {
                case '+':
                    if (i3 != 5) {
                        return 0;
                    }
                    i = 6;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case '-':
                    if (i3 == 0) {
                        i = 1;
                        i7 = i2;
                        obj2 = 1;
                        i3 = i7;
                        continue;
                    } else if (i3 == 5) {
                        i = 6;
                        i3 = i2;
                        obj2 = obj;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (i3 != 2) {
                        return 0;
                    }
                    i = 3;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case 'E':
                case 'e':
                    if (i3 != 2 && i3 != 4) {
                        return 0;
                    }
                    i = 5;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i3 != 1 && i3 != 0) {
                            if (i3 != 2) {
                                if (i3 != 3) {
                                    if (i3 != 5 && i3 != 6) {
                                        i = i3;
                                        i3 = i2;
                                        obj2 = obj;
                                        break;
                                    }
                                    i = 7;
                                    i3 = i2;
                                    obj2 = obj;
                                    break;
                                }
                                i = 4;
                                i3 = i2;
                                obj2 = obj;
                                break;
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - 48));
                                i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? 1 : 0;
                                i &= i2;
                                obj2 = obj;
                                j = j2;
                                i7 = i3;
                                i3 = i;
                                i = i7;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        j = (long) (-(c - 48));
                        i = 2;
                        i3 = i2;
                        obj2 = obj;
                        continue;
                    } else if (m39a(c)) {
                        return 0;
                    }
                    break;
            }
            if (i3 != 2) {
            }
            if (i3 == 2) {
            }
            this.f37k = i4;
            this.f28a = 16;
            return 16;
            i4++;
            obj = obj2;
            i2 = i3;
            i3 = i;
        }
    }

    private String m48u() {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            if (this.f32f + i < this.f33g) {
                switch (this.f31e[this.f32f + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m50w();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.f31e.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder(Math.max(i, 16));
                }
                stringBuilder.append(this.f31e, this.f32f, i);
                this.f32f = i + this.f32f;
                i = !m44b(1) ? 0 : 0;
            } else if (m44b(i + 1)) {
            }
            String str = stringBuilder == null ? new String(this.f31e, this.f32f, i) : stringBuilder.append(this.f31e, this.f32f, i).toString();
            this.f32f = i + this.f32f;
            return str;
        }
    }

    private void m49v() {
        do {
            int i = 0;
            while (this.f32f + i < this.f33g) {
                switch (this.f31e[this.f32f + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m50w();
                        break;
                    default:
                        i++;
                }
                this.f32f = i + this.f32f;
                return;
            }
            this.f32f = i + this.f32f;
        } while (m44b(1));
    }

    private void m50w() {
        if (!this.f30d) {
            throw m42b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void m51x() {
        char c;
        do {
            if (this.f32f < this.f33g || m44b(1)) {
                char[] cArr = this.f31e;
                int i = this.f32f;
                this.f32f = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.f34h++;
                    this.f35i = this.f32f;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private char m52y() {
        if (this.f32f != this.f33g || m44b(1)) {
            char[] cArr = this.f31e;
            int i = this.f32f;
            this.f32f = i + 1;
            char c = cArr[i];
            switch (c) {
                case '\n':
                    this.f34h++;
                    this.f35i = this.f32f;
                    return c;
                case '\"':
                case '\'':
                case '/':
                case '\\':
                    return c;
                case 'b':
                    return '\b';
                case 'f':
                    return '\f';
                case 'n':
                    return '\n';
                case 'r':
                    return '\r';
                case 't':
                    return '\t';
                case 'u':
                    if (this.f32f + 4 <= this.f33g || m44b(4)) {
                        int i2 = this.f32f;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.f31e[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 >= 'a' && c2 <= 'f') {
                                c = (char) (c + ((c2 - 97) + 10));
                            } else if (c2 < 'A' || c2 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.f31e, this.f32f, 4));
                            } else {
                                c = (char) (c + ((c2 - 65) + 10));
                            }
                        }
                        this.f32f += 4;
                        return c;
                    }
                    throw m42b("Unterminated escape sequence");
                default:
                    throw m42b("Invalid escape sequence");
            }
        }
        throw m42b("Unterminated escape sequence");
    }

    private void m53z() {
        m41b(true);
        this.f32f--;
        if (this.f32f + f27b.length <= this.f33g || m44b(f27b.length)) {
            int i = 0;
            while (i < f27b.length) {
                if (this.f31e[this.f32f + i] == f27b[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.f32f += f27b.length;
        }
    }

    public void mo1795a() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 3) {
            m38a(1);
            this.f42p[this.f40n - 1] = 0;
            this.f28a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + mo1801f() + m72s());
    }

    public final void m55a(boolean z) {
        this.f30d = z;
    }

    public void mo1796b() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 4) {
            this.f40n--;
            int[] iArr = this.f42p;
            int i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f28a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + mo1801f() + m72s());
    }

    public void mo1797c() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 1) {
            m38a(3);
            this.f28a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + mo1801f() + m72s());
    }

    public void close() {
        this.f28a = 0;
        this.f39m[0] = 8;
        this.f40n = 1;
        this.f29c.close();
    }

    public void mo1799d() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 2) {
            this.f40n--;
            this.f41o[this.f40n] = null;
            int[] iArr = this.f42p;
            int i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f28a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + mo1801f() + m72s());
    }

    public boolean mo1800e() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public C0544b mo1801f() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        switch (i) {
            case 1:
                return C0544b.BEGIN_OBJECT;
            case 2:
                return C0544b.END_OBJECT;
            case 3:
                return C0544b.BEGIN_ARRAY;
            case 4:
                return C0544b.END_ARRAY;
            case 5:
            case 6:
                return C0544b.BOOLEAN;
            case 7:
                return C0544b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return C0544b.STRING;
            case 12:
            case 13:
            case 14:
                return C0544b.NAME;
            case 15:
            case 16:
                return C0544b.NUMBER;
            case 17:
                return C0544b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public String mo1802g() {
        String u;
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 14) {
            u = m48u();
        } else if (i == 12) {
            u = m43b('\'');
        } else if (i == 13) {
            u = m43b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + mo1801f() + m72s());
        }
        this.f28a = 0;
        this.f41o[this.f40n - 1] = u;
        return u;
    }

    public String mo1803h() {
        String u;
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 10) {
            u = m48u();
        } else if (i == 8) {
            u = m43b('\'');
        } else if (i == 9) {
            u = m43b('\"');
        } else if (i == 11) {
            u = this.f38l;
            this.f38l = null;
        } else if (i == 15) {
            u = Long.toString(this.f36j);
        } else if (i == 16) {
            u = new String(this.f31e, this.f32f, this.f37k);
            this.f32f += this.f37k;
        } else {
            throw new IllegalStateException("Expected a string but was " + mo1801f() + m72s());
        }
        this.f28a = 0;
        int[] iArr = this.f42p;
        int i2 = this.f40n - 1;
        iArr[i2] = iArr[i2] + 1;
        return u;
    }

    public boolean mo1804i() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 5) {
            this.f28a = 0;
            int[] iArr = this.f42p;
            i = this.f40n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.f28a = 0;
            int[] iArr2 = this.f42p;
            int i2 = this.f40n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + mo1801f() + m72s());
        }
    }

    public void mo1805j() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 7) {
            this.f28a = 0;
            int[] iArr = this.f42p;
            int i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + mo1801f() + m72s());
    }

    public double mo1806k() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 15) {
            this.f28a = 0;
            int[] iArr = this.f42p;
            int i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.f36j;
        }
        if (i == 16) {
            this.f38l = new String(this.f31e, this.f32f, this.f37k);
            this.f32f += this.f37k;
        } else if (i == 8 || i == 9) {
            this.f38l = m43b(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.f38l = m48u();
        } else if (i != 11) {
            throw new IllegalStateException("Expected a double but was " + mo1801f() + m72s());
        }
        this.f28a = 11;
        double parseDouble = Double.parseDouble(this.f38l);
        if (this.f30d || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.f38l = null;
            this.f28a = 0;
            int[] iArr2 = this.f42p;
            int i3 = this.f40n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new C0545d("JSON forbids NaN and infinities: " + parseDouble + m72s());
    }

    public long mo1807l() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        if (i == 15) {
            this.f28a = 0;
            int[] iArr = this.f42p;
            int i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f36j;
        }
        long parseLong;
        if (i == 16) {
            this.f38l = new String(this.f31e, this.f32f, this.f37k);
            this.f32f += this.f37k;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.f38l = m48u();
            } else {
                this.f38l = m43b(i == 8 ? '\'' : '\"');
            }
            try {
                parseLong = Long.parseLong(this.f38l);
                this.f28a = 0;
                int[] iArr2 = this.f42p;
                int i3 = this.f40n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + mo1801f() + m72s());
        }
        this.f28a = 11;
        double parseDouble = Double.parseDouble(this.f38l);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            throw new NumberFormatException("Expected a long but was " + this.f38l + m72s());
        }
        this.f38l = null;
        this.f28a = 0;
        iArr2 = this.f42p;
        i3 = this.f40n - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    public int mo1808m() {
        int i = this.f28a;
        if (i == 0) {
            i = m71r();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.f36j;
            if (this.f36j != ((long) i)) {
                throw new NumberFormatException("Expected an int but was " + this.f36j + m72s());
            }
            this.f28a = 0;
            iArr = this.f42p;
            i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
        } else {
            if (i == 16) {
                this.f38l = new String(this.f31e, this.f32f, this.f37k);
                this.f32f += this.f37k;
            } else if (i == 8 || i == 9 || i == 10) {
                if (i == 10) {
                    this.f38l = m48u();
                } else {
                    this.f38l = m43b(i == 8 ? '\'' : '\"');
                }
                try {
                    i = Integer.parseInt(this.f38l);
                    this.f28a = 0;
                    iArr = this.f42p;
                    i2 = this.f40n - 1;
                    iArr[i2] = iArr[i2] + 1;
                } catch (NumberFormatException e) {
                }
            } else {
                throw new IllegalStateException("Expected an int but was " + mo1801f() + m72s());
            }
            this.f28a = 11;
            double parseDouble = Double.parseDouble(this.f38l);
            i = (int) parseDouble;
            if (((double) i) != parseDouble) {
                throw new NumberFormatException("Expected an int but was " + this.f38l + m72s());
            }
            this.f38l = null;
            this.f28a = 0;
            iArr = this.f42p;
            i2 = this.f40n - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return i;
    }

    public void mo1809n() {
        int i = 0;
        do {
            int i2 = this.f28a;
            if (i2 == 0) {
                i2 = m71r();
            }
            if (i2 == 3) {
                m38a(1);
                i++;
            } else if (i2 == 1) {
                m38a(3);
                i++;
            } else if (i2 == 4) {
                this.f40n--;
                i--;
            } else if (i2 == 2) {
                this.f40n--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                m49v();
            } else if (i2 == 8 || i2 == 12) {
                m45c('\'');
            } else if (i2 == 9 || i2 == 13) {
                m45c('\"');
            } else if (i2 == 16) {
                this.f32f += this.f37k;
            }
            this.f28a = 0;
        } while (i != 0);
        int[] iArr = this.f42p;
        int i3 = this.f40n - 1;
        iArr[i3] = iArr[i3] + 1;
        this.f41o[this.f40n - 1] = "null";
    }

    public String mo1811p() {
        StringBuilder append = new StringBuilder().append('$');
        int i = this.f40n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.f39m[i2]) {
                case 1:
                case 2:
                    append.append('[').append(this.f42p[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    append.append('.');
                    if (this.f41o[i2] == null) {
                        break;
                    }
                    append.append(this.f41o[i2]);
                    break;
                default:
                    break;
            }
        }
        return append.toString();
    }

    public final boolean m70q() {
        return this.f30d;
    }

    int m71r() {
        int b;
        int i = this.f39m[this.f40n - 1];
        if (i == 1) {
            this.f39m[this.f40n - 1] = 2;
        } else if (i == 2) {
            switch (m41b(true)) {
                case 44:
                    break;
                case 59:
                    m50w();
                    break;
                case 93:
                    this.f28a = 4;
                    return 4;
                default:
                    throw m42b("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.f39m[this.f40n - 1] = 4;
            if (i == 5) {
                switch (m41b(true)) {
                    case 44:
                        break;
                    case 59:
                        m50w();
                        break;
                    case 125:
                        this.f28a = 2;
                        return 2;
                    default:
                        throw m42b("Unterminated object");
                }
            }
            b = m41b(true);
            switch (b) {
                case 34:
                    this.f28a = 13;
                    return 13;
                case 39:
                    m50w();
                    this.f28a = 12;
                    return 12;
                case 125:
                    if (i != 5) {
                        this.f28a = 2;
                        return 2;
                    }
                    throw m42b("Expected name");
                default:
                    m50w();
                    this.f32f--;
                    if (m39a((char) b)) {
                        this.f28a = 14;
                        return 14;
                    }
                    throw m42b("Expected name");
            }
        } else if (i == 4) {
            this.f39m[this.f40n - 1] = 5;
            switch (m41b(true)) {
                case 58:
                    break;
                case 61:
                    m50w();
                    if ((this.f32f < this.f33g || m44b(1)) && this.f31e[this.f32f] == '>') {
                        this.f32f++;
                        break;
                    }
                default:
                    throw m42b("Expected ':'");
            }
        } else if (i == 6) {
            if (this.f30d) {
                m53z();
            }
            this.f39m[this.f40n - 1] = 7;
        } else if (i == 7) {
            if (m41b(false) == -1) {
                this.f28a = 17;
                return 17;
            }
            m50w();
            this.f32f--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (m41b(true)) {
            case 34:
                this.f28a = 9;
                return 9;
            case 39:
                m50w();
                this.f28a = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.f28a = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.f28a = 4;
                    return 4;
                }
                break;
            case 123:
                this.f28a = 1;
                return 1;
            default:
                this.f32f--;
                b = mo1810o();
                if (b != 0) {
                    return b;
                }
                b = m47t();
                if (b != 0) {
                    return b;
                }
                if (m39a(this.f31e[this.f32f])) {
                    m50w();
                    this.f28a = 10;
                    return 10;
                }
                throw m42b("Expected value");
        }
        if (i == 1 || i == 2) {
            m50w();
            this.f32f--;
            this.f28a = 7;
            return 7;
        }
        throw m42b("Unexpected value");
    }

    String m72s() {
        return " at line " + (this.f34h + 1) + " column " + ((this.f32f - this.f35i) + 1) + " path " + mo1811p();
    }

    public String toString() {
        return getClass().getSimpleName() + m72s();
    }
}

package com.altbeacon.beacon;

import android.annotation.TargetApi;
import android.support.v4.internal.view.SupportMenu;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C0842d implements Serializable, Comparable<C0842d> {
    private static final Pattern f1660a = Pattern.compile("^0x[0-9A-Fa-f]*$");
    private static final Pattern f1661b = Pattern.compile("^[0-9A-Fa-f]*$");
    private static final Pattern f1662c = Pattern.compile("^0|[1-9][0-9]*$");
    private static final Pattern f1663d = Pattern.compile("^[0-9A-Fa-f]{8}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{12}$");
    private final byte[] f1664e;

    protected C0842d(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"value\" is null.");
        }
        this.f1664e = bArr;
    }

    public static C0842d m1683a(int i) {
        if (i < 0 || i > SupportMenu.USER_MASK) {
            throw new IllegalArgumentException("Identifiers can only be constructed from integers between 0 and 65535 (inclusive).");
        }
        return new C0842d(new byte[]{(byte) (i >> 8), (byte) i});
    }

    public static C0842d m1684a(long j, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Identifier length must be > 0.");
        }
        byte[] bArr = new byte[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return new C0842d(bArr);
    }

    public static C0842d m1685a(String str) {
        return C0842d.m1686a(str, -1);
    }

    public static C0842d m1686a(String str, int i) {
        if (str == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"stringValue\" is null.");
        } else if (f1660a.matcher(str).matches()) {
            return C0842d.m1689b(str.substring(2), i);
        } else {
            if (f1663d.matcher(str).matches()) {
                return C0842d.m1689b(str.replace("-", ""), i);
            }
            if (f1662c.matcher(str).matches()) {
                try {
                    int intValue = Integer.valueOf(str).intValue();
                    return (i <= 0 || i == 2) ? C0842d.m1683a(intValue) : C0842d.m1684a((long) intValue, i);
                } catch (Throwable th) {
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Unable to parse Identifier in decimal format.", th);
                }
            } else if (f1661b.matcher(str).matches()) {
                return C0842d.m1689b(str, i);
            } else {
                throw new IllegalArgumentException("Unable to parse Identifier.");
            }
        }
    }

    @TargetApi(9)
    public static C0842d m1687a(byte[] bArr, int i, int i2, boolean z) {
        if (bArr == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"bytes\" is null.");
        } else if (i < 0 || i > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("start < 0 || start > bytes.length");
        } else if (i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("end > bytes.length");
        } else if (i > i2) {
            throw new IllegalArgumentException("start > end");
        } else {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
            if (z) {
                C0842d.m1688a(copyOfRange);
            }
            return new C0842d(copyOfRange);
        }
    }

    private static void m1688a(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            int length = (bArr.length - i) - 1;
            byte b = bArr[i];
            bArr[i] = bArr[length];
            bArr[length] = b;
        }
    }

    private static C0842d m1689b(String str, int i) {
        int length;
        String str2 = (str.length() % 2 == 0 ? "" : SchemaSymbols.ATTVAL_FALSE_0) + str.toUpperCase();
        if (i > 0 && i < str2.length() / 2) {
            str2 = str2.substring(str2.length() - (i * 2));
        }
        if (i > 0 && i > str2.length() / 2) {
            length = (i * 2) - str2.length();
            StringBuilder stringBuilder = new StringBuilder();
            while (stringBuilder.length() < length) {
                stringBuilder.append(SchemaSymbols.ATTVAL_FALSE_0);
            }
            str2 = stringBuilder.toString() + str2;
        }
        byte[] bArr = new byte[(str2.length() / 2)];
        for (length = 0; length < bArr.length; length++) {
            bArr[length] = (byte) (Integer.parseInt(str2.substring(length * 2, (length * 2) + 2), 16) & 255);
        }
        return new C0842d(bArr);
    }

    public int m1690a() {
        int i = 0;
        if (this.f1664e.length > 2) {
            throw new UnsupportedOperationException("Only supported for Identifiers with max byte length of 2");
        }
        int i2 = 0;
        while (i < this.f1664e.length) {
            i2 |= (this.f1664e[i] & 255) << (((this.f1664e.length - i) - 1) * 8);
            i++;
        }
        return i2;
    }

    public int m1691a(C0842d c0842d) {
        if (this.f1664e.length != c0842d.f1664e.length) {
            return this.f1664e.length < c0842d.f1664e.length ? -1 : 1;
        } else {
            int i = 0;
            while (i < this.f1664e.length) {
                if (this.f1664e[i] != c0842d.f1664e[i]) {
                    return this.f1664e[i] >= c0842d.f1664e[i] ? 1 : -1;
                } else {
                    i++;
                }
            }
            return 0;
        }
    }

    public String m1692b() {
        StringBuilder stringBuilder = new StringBuilder((this.f1664e.length * 2) + 2);
        stringBuilder.append("0x");
        int length = this.f1664e.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(r3[i])}));
        }
        return stringBuilder.toString();
    }

    public UUID m1693c() {
        if (this.f1664e.length != 16) {
            throw new UnsupportedOperationException("Only Identifiers backed by a byte array with length of exactly 16 can be UUIDs.");
        }
        LongBuffer asLongBuffer = ByteBuffer.wrap(this.f1664e).asLongBuffer();
        return new UUID(asLongBuffer.get(), asLongBuffer.get());
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m1691a((C0842d) obj);
    }

    public byte[] m1694d() {
        return (byte[]) this.f1664e.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0842d)) {
            return false;
        }
        return Arrays.equals(this.f1664e, ((C0842d) obj).f1664e);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f1664e);
    }

    public String toString() {
        return this.f1664e.length == 2 ? Integer.toString(m1690a()) : this.f1664e.length == 16 ? m1693c().toString() : m1692b();
    }
}

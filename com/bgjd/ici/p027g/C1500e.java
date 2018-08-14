package com.bgjd.ici.p027g;

import android.support.v4.internal.view.SupportMenu;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public final class C1500e {
    private static final int f2439A = 8;
    private static final int f2440B = 9;
    private static final int f2441C = 10;
    private static final List<Integer> f2442D = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10)});
    private static final List<Integer> f2443E = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)});
    private static final int f2444n = 255;
    private static final int f2445o = 128;
    private static final int f2446p = 128;
    private static final int f2447q = 64;
    private static final int f2448r = 32;
    private static final int f2449s = 16;
    private static final int f2450t = 15;
    private static final int f2451u = 127;
    private static final int f2452v = 1;
    private static final int f2453w = 2;
    private static final int f2454x = 0;
    private static final int f2455y = 1;
    private static final int f2456z = 2;
    private C1505f f2457a;
    private boolean f2458b = true;
    private int f2459c;
    private boolean f2460d;
    private boolean f2461e;
    private int f2462f;
    private int f2463g;
    private int f2464h;
    private int f2465i;
    private byte[] f2466j = new byte[0];
    private byte[] f2467k = new byte[0];
    private boolean f2468l = false;
    private ByteArrayOutputStream f2469m = new ByteArrayOutputStream();

    public static class C1498a extends DataInputStream {
        public C1498a(InputStream inputStream) {
            super(inputStream);
        }

        public byte[] m3214a(int i) throws IOException {
            byte[] bArr = new byte[i];
            readFully(bArr);
            return bArr;
        }
    }

    public static class C1499b extends IOException {
        private static final long f2438a = 8183115656443238658L;

        public C1499b(String str) {
            super(str);
        }
    }

    public C1500e(C1505f c1505f) {
        this.f2457a = c1505f;
    }

    private static byte[] m3221a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2.length != 0) {
            for (int i2 = 0; i2 < bArr.length - i; i2++) {
                bArr[i + i2] = (byte) (bArr[i + i2] ^ bArr2[i2 % 4]);
            }
        }
        return bArr;
    }

    public void m3231a(C1498a c1498a) throws IOException {
        while (c1498a.available() != -1) {
            switch (this.f2459c) {
                case 0:
                    m3216a(c1498a.readByte());
                    break;
                case 1:
                    m3224b(c1498a.readByte());
                    break;
                case 2:
                    m3225b(c1498a.m3214a(this.f2463g));
                    break;
                case 3:
                    this.f2466j = c1498a.m3214a(4);
                    this.f2459c = 4;
                    break;
                case 4:
                    this.f2467k = c1498a.m3214a(this.f2464h);
                    m3215a();
                    this.f2459c = 0;
                    break;
                default:
                    break;
            }
        }
        this.f2457a.mo2334c().mo2305a(0, "EOF");
    }

    private void m3216a(byte b) throws C1499b {
        int i = (b & 64) == 64 ? 1 : 0;
        int i2;
        if ((b & 32) == 32) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i3;
        if ((b & 16) == 16) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (i == 0 && r3 == 0 && r0 == 0) {
            this.f2460d = (b & 128) == 128;
            this.f2462f = b & 15;
            this.f2466j = new byte[0];
            this.f2467k = new byte[0];
            if (!f2442D.contains(Integer.valueOf(this.f2462f))) {
                throw new C1499b("Bad opcode");
            } else if (f2443E.contains(Integer.valueOf(this.f2462f)) || this.f2460d) {
                this.f2459c = 1;
                return;
            } else {
                throw new C1499b("Expected non-final packet");
            }
        }
        throw new C1499b("RSV not zero");
    }

    private void m3224b(byte b) {
        this.f2461e = (b & 128) == 128;
        this.f2464h = b & f2451u;
        if (this.f2464h < 0 || this.f2464h > 125) {
            this.f2463g = this.f2464h == 126 ? 2 : 8;
            this.f2459c = 2;
            return;
        }
        this.f2459c = this.f2461e ? 3 : 4;
    }

    private void m3225b(byte[] bArr) throws C1499b {
        this.f2464h = m3229d(bArr);
        this.f2459c = this.f2461e ? 3 : 4;
    }

    public byte[] m3232a(String str) {
        return m3218a(str, 1, -1);
    }

    public byte[] m3233a(byte[] bArr) {
        return m3220a(bArr, 2, -1);
    }

    private byte[] m3220a(byte[] bArr, int i, int i2) {
        return m3217a((Object) bArr, i, i2);
    }

    private byte[] m3218a(String str, int i, int i2) {
        return m3217a((Object) str, i, i2);
    }

    private byte[] m3217a(Object obj, int i, int i2) {
        if (this.f2468l) {
            return null;
        }
        if (obj instanceof String) {
            obj = m3228c((String) obj);
        } else {
            byte[] bArr = (byte[]) obj;
        }
        int i3 = i2 > 0 ? 2 : 0;
        int length = obj.length + i3;
        int i4 = length <= 125 ? 2 : length <= SupportMenu.USER_MASK ? 4 : 10;
        int i5 = i4 + (this.f2458b ? 4 : 0);
        int i6 = this.f2458b ? 128 : 0;
        byte[] bArr2 = new byte[(length + i5)];
        bArr2[0] = (byte) (((byte) i) | -128);
        if (length <= 125) {
            bArr2[1] = (byte) (i6 | length);
        } else if (length <= SupportMenu.USER_MASK) {
            bArr2[1] = (byte) (i6 | 126);
            bArr2[2] = (byte) ((int) Math.floor((double) (length / 256)));
            bArr2[3] = (byte) (length & 255);
        } else {
            bArr2[1] = (byte) (i6 | f2451u);
            bArr2[2] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 56.0d))) & 255);
            bArr2[3] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 48.0d))) & 255);
            bArr2[4] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 40.0d))) & 255);
            bArr2[5] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 32.0d))) & 255);
            bArr2[6] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 24.0d))) & 255);
            bArr2[7] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 16.0d))) & 255);
            bArr2[8] = (byte) (((int) Math.floor(((double) length) / Math.pow(2.0d, 8.0d))) & 255);
            bArr2[9] = (byte) (length & 255);
        }
        if (i2 > 0) {
            bArr2[i5] = (byte) (((int) Math.floor((double) (i2 / 256))) & 255);
            bArr2[i5 + 1] = (byte) (i2 & 255);
        }
        System.arraycopy(obj, 0, bArr2, i3 + i5, obj.length);
        if (this.f2458b) {
            byte[] bArr3 = new byte[]{(byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d)), (byte) ((int) Math.floor(Math.random() * 256.0d))};
            System.arraycopy(bArr3, 0, bArr2, i4, bArr3.length);
            C1500e.m3221a(bArr2, bArr3, i5);
        }
        return bArr2;
    }

    public void m3234b(String str) {
        this.f2457a.mo2332a(m3218a(str, 9, -1));
    }

    public void m3230a(int i, String str) {
        if (!this.f2468l) {
            this.f2457a.mo2332a(m3218a(str, 8, i));
            this.f2468l = true;
        }
    }

    private void m3215a() throws IOException {
        int i = 0;
        byte[] a = C1500e.m3221a(this.f2467k, this.f2466j, 0);
        int i2 = this.f2462f;
        if (i2 == 0) {
            if (this.f2465i == 0) {
                throw new C1499b("Mode was not set.");
            }
            this.f2469m.write(a);
            if (this.f2460d) {
                byte[] toByteArray = this.f2469m.toByteArray();
                if (this.f2465i == 1) {
                    this.f2457a.mo2334c().mo2307a(m3227c(toByteArray));
                } else {
                    this.f2457a.mo2334c().mo2308a(toByteArray);
                }
                m3223b();
            }
        } else if (i2 == 1) {
            if (this.f2460d) {
                this.f2457a.mo2334c().mo2307a(m3227c(a));
                return;
            }
            this.f2465i = 1;
            this.f2469m.write(a);
        } else if (i2 == 2) {
            if (this.f2460d) {
                this.f2457a.mo2334c().mo2308a(a);
                return;
            }
            this.f2465i = 2;
            this.f2469m.write(a);
        } else if (i2 == 8) {
            if (a.length >= 2) {
                i = (a[0] * 256) + a[1];
            }
            this.f2457a.mo2334c().mo2305a(i, a.length > 2 ? m3227c(m3226b(a, 2)) : null);
        } else if (i2 == 9) {
            if (a.length > 125) {
                throw new C1499b("Ping payload too large");
            }
            this.f2457a.m3258b(m3220a(a, 10, -1));
        } else if (i2 == 10) {
            m3227c(a);
        }
    }

    private void m3223b() {
        this.f2465i = 0;
        this.f2469m.reset();
    }

    private String m3227c(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] m3228c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private int m3229d(byte[] bArr) throws C1499b {
        long b = C1500e.m3222b(bArr, 0, bArr.length);
        if (b >= 0 && b <= 2147483647L) {
            return (int) b;
        }
        throw new C1499b("Bad integer: " + b);
    }

    public static byte[] m3219a(byte[] bArr, int i) {
        Object obj = new byte[i];
        System.arraycopy(bArr, 0, obj, 0, Math.min(bArr.length, i));
        return obj;
    }

    private byte[] m3226b(byte[] bArr, int i) {
        return C1500e.m3219a(bArr, bArr.length);
    }

    private static long m3222b(byte[] bArr, int i, int i2) {
        if (bArr.length < i2) {
            throw new IllegalArgumentException("length must be less than or equal to b.length");
        }
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j += (long) ((bArr[i3 + i] & 255) << (((i2 - 1) - i3) * 8));
        }
        return j;
    }
}

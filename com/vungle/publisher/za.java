package com.vungle.publisher;

/* compiled from: vungle */
public final class za {
    public static int m14186a(int i, int i2, int i3) {
        m14187a(i3);
        return i3 == 1 ? m14185a(i, i2) : m14188b(i, i2);
    }

    public static int m14185a(int i, int i2) {
        return m14190c(i2, 1) | i;
    }

    public static int m14188b(int i, int i2) {
        return m14190c(i2, 0) & i;
    }

    static int m14190c(int i, int i2) {
        m14189b(i);
        int i3 = 1 << i;
        if (i2 == 0) {
            return i3 ^ -1;
        }
        return i3;
    }

    public static void m14187a(int i) throws IllegalArgumentException {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("bit must be 0 or 1");
        }
    }

    static void m14189b(int i) throws IllegalArgumentException {
        if (i < 0 || i > 31) {
            throw new IllegalArgumentException("bit index must be 0-31");
        }
    }
}

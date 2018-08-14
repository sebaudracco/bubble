package com.p000a.p001a.p003b;

import java.math.BigDecimal;

public final class C0516f extends Number {
    private final String f219a;

    public C0516f(String str) {
        this.f219a = str;
    }

    private Object writeReplace() {
        return new BigDecimal(this.f219a);
    }

    public double doubleValue() {
        return Double.parseDouble(this.f219a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0516f)) {
            return false;
        }
        C0516f c0516f = (C0516f) obj;
        return this.f219a == c0516f.f219a || this.f219a.equals(c0516f.f219a);
    }

    public float floatValue() {
        return Float.parseFloat(this.f219a);
    }

    public int hashCode() {
        return this.f219a.hashCode();
    }

    public int intValue() {
        try {
            return Integer.parseInt(this.f219a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.f219a);
            } catch (NumberFormatException e2) {
                return new BigDecimal(this.f219a).intValue();
            }
        }
    }

    public long longValue() {
        try {
            return Long.parseLong(this.f219a);
        } catch (NumberFormatException e) {
            return new BigDecimal(this.f219a).longValue();
        }
    }

    public String toString() {
        return this.f219a;
    }
}

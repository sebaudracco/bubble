package com.p000a.p001a;

import com.p000a.p001a.p003b.C0497a;
import com.p000a.p001a.p003b.C0516f;
import java.math.BigInteger;

public final class C0561o extends C0554j {
    private static final Class<?>[] f297a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object f298b;

    public C0561o(Boolean bool) {
        m480a((Object) bool);
    }

    public C0561o(Number number) {
        m480a((Object) number);
    }

    public C0561o(String str) {
        m480a((Object) str);
    }

    private static boolean m477a(C0561o c0561o) {
        if (!(c0561o.f298b instanceof Number)) {
            return false;
        }
        Number number = (Number) c0561o.f298b;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    private static boolean m478b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class isAssignableFrom : f297a) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public Number mo1834a() {
        return this.f298b instanceof String ? new C0516f((String) this.f298b) : (Number) this.f298b;
    }

    void m480a(Object obj) {
        if (obj instanceof Character) {
            this.f298b = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z = (obj instanceof Number) || C0561o.m478b(obj);
        C0497a.m309a(z);
        this.f298b = obj;
    }

    public String mo1835b() {
        return m488p() ? mo1834a().toString() : m487o() ? mo1840n().toString() : (String) this.f298b;
    }

    public double mo1836c() {
        return m488p() ? mo1834a().doubleValue() : Double.parseDouble(mo1835b());
    }

    public long mo1837d() {
        return m488p() ? mo1834a().longValue() : Long.parseLong(mo1835b());
    }

    public int mo1838e() {
        return m488p() ? mo1834a().intValue() : Integer.parseInt(mo1835b());
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0561o c0561o = (C0561o) obj;
        if (this.f298b == null) {
            return c0561o.f298b == null;
        } else {
            if (C0561o.m477a(this) && C0561o.m477a(c0561o)) {
                return mo1834a().longValue() == c0561o.mo1834a().longValue();
            } else {
                if (!(this.f298b instanceof Number) || !(c0561o.f298b instanceof Number)) {
                    return this.f298b.equals(c0561o.f298b);
                }
                double doubleValue = mo1834a().doubleValue();
                double doubleValue2 = c0561o.mo1834a().doubleValue();
                if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
                    z = true;
                }
                return z;
            }
        }
    }

    public boolean mo1839f() {
        return m487o() ? mo1840n().booleanValue() : Boolean.parseBoolean(mo1835b());
    }

    public int hashCode() {
        if (this.f298b == null) {
            return 31;
        }
        long longValue;
        if (C0561o.m477a(this)) {
            longValue = mo1834a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.f298b instanceof Number)) {
            return this.f298b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(mo1834a().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    Boolean mo1840n() {
        return (Boolean) this.f298b;
    }

    public boolean m487o() {
        return this.f298b instanceof Boolean;
    }

    public boolean m488p() {
        return this.f298b instanceof Number;
    }

    public boolean m489q() {
        return this.f298b instanceof String;
    }
}
